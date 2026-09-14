package com.example.myapplication.fragment

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.domain.utils.GRVControlStepEnum
import com.example.myapplication.R
import com.example.myapplication.callback.ChildViewPagerGRVInterface
import com.example.myapplication.callback.GRVControlInterface
import com.example.myapplication.childfragment.ControlGRVViewPagerAdapter
import com.example.myapplication.model.ControlGRV
import com.example.myapplication.utils.GRV_CONTROL_TAG
import com.example.myapplication.utils.ZoomOutPageTransformer
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class GRVMainFragment : BaseFragment(), ChildViewPagerGRVInterface {
    override fun getLayout(): Int = R.layout.fragment_grv_main

    private lateinit var controlGRVViewPager: ViewPager2
    private lateinit var controlGRVViewPagerAdapter: ControlGRVViewPagerAdapter
    private val controls = mutableListOf<ControlGRV>()

    private var imageCapture: ImageCapture? = null

    companion object {
        fun newInstance() = GRVMainFragment()
        const val TAG = GRV_CONTROL_TAG
    }

    override fun getBody(view: View, savedInstanceState: Bundle?) {
        view.findViewById<ImageView>(R.id.imageView_alorem_grv_control).setOnClickListener {
            mCallback?.loadMenuFragment()
        }
        controlGRVViewPagerAdapter = ControlGRVViewPagerAdapter(this, controls)
        controlGRVViewPager = view.findViewById(R.id.viewPager_grv_control)
        controlGRVViewPager.setPageTransformer(ZoomOutPageTransformer())
        controlGRVViewPager.adapter = controlGRVViewPagerAdapter

        // photo
        view.findViewById<FloatingActionButton>(R.id.btn_take_photo).setOnClickListener {
            takePhoto()
        }

        view.findViewById<FloatingActionButton>(R.id.btn_close_camera).setOnClickListener {
            view.findViewById<View>(R.id.container_camera).visibility = View.GONE
        }
    }

    override fun createNewPage(serialNumber: Int, currentStep: GRVControlStepEnum) {
        val newPosition = controls.size
        controls.add(ControlGRV(pageId = System.nanoTime().toInt(), serialNumber = serialNumber, currentStep = currentStep))
        controlGRVViewPagerAdapter.notifyItemInserted(newPosition)

        controlGRVViewPager.post {
            controlGRVViewPager.setCurrentItem(newPosition, true)
        }
    }

    override fun getAddingPage(newList: List<ControlGRV>?) {
        val pos = controlGRVViewPager.currentItem

        if (pos < controls.size) {
            controls.removeAt(pos)
            controlGRVViewPagerAdapter.notifyItemRemoved(pos)
            controlGRVViewPager.post {
                controlGRVViewPager.setCurrentItem(controlGRVViewPagerAdapter.itemCount - 1, true)
                newList?.let {
                    childFragmentManager.setFragmentResult("REFRESH_ADDING_PAGE", Bundle())
                }
            }
        }
    }

    override fun saveControl() {
        mCallback?.loadMenuFragment()
    }

    private var photoName = ""

    override fun getCameraScreen(name: String) {
        photoName = name + LocalDateTime.now().format(DateTimeFormatter.ofPattern("_ddMMyyyy_HHmmss"))
        openCamera()
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())

        cameraProviderFuture.addListener({
            try {
                val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

                val preview = Preview.Builder()
                    .build()
                    .also {
                        val previewView = view?.findViewById<PreviewView>(R.id.viewFinder)
                        if (previewView != null) {
                            it.setSurfaceProvider(previewView.surfaceProvider)
                        } else {
                            android.util.Log.e("ReportFragment", "PreviewView is NULL")
                        }
                    }

                imageCapture = ImageCapture.Builder()
                    .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
                    .build()

                val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(viewLifecycleOwner, cameraSelector, preview, imageCapture)

            } catch (exc: Exception) {
                Toast.makeText(context, "Erreur caméra : ${exc.message}", Toast.LENGTH_SHORT).show()
            }

        }, ContextCompat.getMainExecutor(requireContext()))
    }

    private fun takePhoto() {
        val imageCapture = imageCapture ?: return

        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, photoName)
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/Alorem")
        }

        val outputOptions = ImageCapture.OutputFileOptions
            .Builder(requireContext().contentResolver, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
            .build()

        imageCapture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(requireContext()),
            object : ImageCapture.OnImageSavedCallback {
                override fun onError(exc: ImageCaptureException) {
                    view?.findViewById<View>(R.id.container_camera)?.visibility = View.GONE
                }

                override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                    val msg = "Photo enregistrée : ${output.savedUri}"
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                    view?.findViewById<View>(R.id.container_camera)?.visibility = View.GONE

                    output.savedUri?.let { uri ->
                        val mediaScanIntent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
                        mediaScanIntent.data = uri
                        requireContext().sendBroadcast(mediaScanIntent)
                    }
                }
            }
        )
    }

    fun openCamera() {
        if (ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.CAMERA)
            == android.content.pm.PackageManager.PERMISSION_GRANTED) {
            view?.findViewById<View>(R.id.container_camera)?.visibility = View.VISIBLE
            startCamera()
        } else {
            requestPermissions(arrayOf(android.Manifest.permission.CAMERA), 1001)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == 1001 && grantResults.isNotEmpty() && grantResults[0] == android.content.pm.PackageManager.PERMISSION_GRANTED) {
            openCamera()
        }
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