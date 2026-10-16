package com.example.myapplication.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.callback.GRVControlInterface
import com.example.myapplication.recycler.RemoteListAdapter
import com.example.myapplication.utils.REMOTE_TAG
import com.example.myapplication.utils.getRemoteAddress
import com.example.myapplication.utils.saveRemoteAddress
import com.example.myapplication.viewmodel.ControlGRVViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.getValue

class RemoteFragment : BaseFragment() {

    override fun getLayout(): Int = R.layout.fragment_remote

    private val viewModel: ControlGRVViewModel by viewModel()
    private lateinit var tabRecycler: RecyclerView
    private lateinit var tabAdapter: RemoteListAdapter
    private lateinit var serverAdapter: RemoteListAdapter
    private lateinit var serverRecycler: RecyclerView

    override fun getBody(view: View, savedInstanceState: Bundle?) {
        view.findViewById<ImageView>(R.id.imageView_alorem_remote_grv).setOnClickListener {
            mCallback?.loadMenuFragment()
        }

        val addressInput = view.findViewById<TextView>(R.id.addressInput_remote)
        addressInput.text = getRemoteAddress(context = requireContext())

        view.findViewById<TextView>(R.id.changeBtn_remote).setOnClickListener {
            saveRemoteAddress(context = requireContext(), address = addressInput.text.toString())
        }

        serverRecycler = view.findViewById(R.id.server_recycler_remote_grv)
        tabRecycler = view.findViewById(R.id.tab_recycler_remote_grv)

        viewModel.getAllRemoteAndLocalControlGRV(baseUrl = getRemoteAddress(context = requireContext()))
        getObservations(view= view)
    }

    private fun getObservations(view: View) {
        val progressTab = view.findViewById<ProgressBar>(R.id.tab_progress_remote_grv)
        val progressServer = view.findViewById<ProgressBar>(R.id.remote_progress_remote_grv)

        viewModel.remoteControlGRVLiveData().observe(this) { lists ->

            tabAdapter = RemoteListAdapter(lists.second.toMutableList(), onItemClicked = { position, serialNumber ->
                progressAnimation(view = view, progressShow = true)
                tabAdapter.removeItem(position = position)
                viewModel.sendControlToPostgresSql(reference = serialNumber, list = lists.first, baseUrl = getRemoteAddress(context = requireContext()))
            })
            tabRecycler.adapter = tabAdapter
            tabRecycler.layoutManager = LinearLayoutManager(view.context)

            serverAdapter = RemoteListAdapter(lists.first.toMutableList(), onItemClicked = { position, serialNumber ->
                progressAnimation(view = view, progressShow = true)
                val item = lists.first[position]
                val uidToFetch = item.uid ?: serialNumber
                tabAdapter.addItem(item = item)
                viewModel.getControlFromRemote(id = uidToFetch, baseUrl = getRemoteAddress(context = requireContext()))
            })
            serverRecycler.adapter = serverAdapter
            serverRecycler.layoutManager = LinearLayoutManager(view.context)

        }

        viewModel.sendControlGRVToRemoteLiveData().observe(this) {
            progressAnimation(view = view, progressShow = false)
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }

        viewModel.importRemoteControlLiveData().observe(this) {
            progressAnimation(view = view, progressShow = false)
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun progressAnimation(view: View, progressShow: Boolean) {
        view.findViewById<ProgressBar>(R.id.tab_progress_remote_grv).visibility = if (progressShow) View.VISIBLE else View.GONE
        view.findViewById<ProgressBar>(R.id.remote_progress_remote_grv).visibility = if (progressShow) View.VISIBLE else View.GONE
        tabRecycler.visibility = if (progressShow) View.GONE else View.VISIBLE
        serverRecycler.visibility = if (progressShow) View.GONE else View.VISIBLE
    }


    companion object {
        fun newInstance() = RemoteFragment()
        const val TAG = REMOTE_TAG
    }

    /**
     *  LIFE CYCLE
     */

    private var mCallback: GRVControlInterface? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try { mCallback = activity as GRVControlInterface }
        catch (e: ClassCastException) { throw ClassCastException("$e must implemented MainInterface") }
    }
}
