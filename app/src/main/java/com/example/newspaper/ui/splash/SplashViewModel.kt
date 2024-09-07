package com.example.newspaper.ui.splash

import androidx.lifecycle.MutableLiveData
import com.example.newspaper.BuildConfig
import com.example.newspaper.core.BaseViewModel
import com.example.newspaper.data.remote.firebase.RemoteConfigParameters
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : BaseViewModel() {

    private val remoteConfig: FirebaseRemoteConfig by lazy { FirebaseRemoteConfig.getInstance() }

    val maintenanceLiveData = MutableLiveData<Boolean>()
    val versionStatusLiveData = MutableLiveData<VersionStatus>()

    var updateMessage: String = ""
    var updateLink: String = ""

    init {
        remoteConfig.setConfigSettingsAsync(
            remoteConfigSettings {
                minimumFetchIntervalInSeconds = if (BuildConfig.DEBUG) 0 else 1800
                fetchTimeoutInSeconds = 30
            }
        )
    }

    fun fetchRemoteConfigValues() {
        remoteConfig.fetchAndActivate()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val isMaintenance = remoteConfig.getBoolean(RemoteConfigParameters.IS_MAINTENANCE)
                    updateMessage = remoteConfig.getString(RemoteConfigParameters.UPDATE_MESSAGE)
                    updateLink = remoteConfig.getString(RemoteConfigParameters.UPDATE_LINK)
                    maintenanceLiveData.postValue(isMaintenance)
                } else {
                    maintenanceLiveData.postValue(false)
                }
            }
    }

    fun checkVersion() {
        val minVersion = remoteConfig.getString(RemoteConfigParameters.MIN_VERSION).split(".").joinToString("").toInt()
        val maxVersion = remoteConfig.getString(RemoteConfigParameters.MAX_VERSION).split(".").joinToString("").toInt()
        val currentVersion = BuildConfig.VERSION_NAME.split(".").joinToString("").toInt()

        when {
            currentVersion >= maxVersion -> {
                versionStatusLiveData.postValue(VersionStatus.UP_TO_DATE)
            }
            currentVersion in minVersion..maxVersion -> {
                versionStatusLiveData.postValue(VersionStatus.OPTIONAL_UPDATE)
            }
            currentVersion < minVersion -> {
                versionStatusLiveData.postValue(VersionStatus.FORCE_UPDATE)
            }
        }
    }

}

enum class VersionStatus {
    UP_TO_DATE,
    OPTIONAL_UPDATE,
    FORCE_UPDATE
}