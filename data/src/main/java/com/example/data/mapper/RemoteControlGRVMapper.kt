package com.example.data.mapper

import com.example.data.api.modele.RemoteGRVControl
import com.example.data.api.modele.RemoteListGRVControl
import com.example.domain.model.BusinessRemoteListGRVControl
import com.example.domain.model.ControlGRVBusiness

object RemoteControlGRVMapper {

    fun getAllRemoteControlGRVToBusiness(remoteListGRVControl: RemoteListGRVControl): BusinessRemoteListGRVControl {
        val result = mutableListOf<ControlGRVBusiness>()
        remoteListGRVControl.list.forEach() {
            result.add(remoteControlGRVToBusiness(it))
        }
        return BusinessRemoteListGRVControl(list = result)
    }

    fun remoteControlGRVToBusiness(remoteControlGRV: RemoteGRVControl): ControlGRVBusiness = ControlGRVBusiness(
        uid = remoteControlGRV.uid,
        title = remoteControlGRV.title,
        serialNumber = remoteControlGRV.serialNumber,
        currentStep = remoteControlGRV.currentStep,
        currentlyGoingOn = remoteControlGRV.currentlyGoingOn,
        loaded = remoteControlGRV.loaded,
    )
}