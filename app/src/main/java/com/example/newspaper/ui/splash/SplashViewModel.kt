package com.example.newspaper.ui.splash

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.newspaper.BuildConfig
import com.example.newspaper.core.BaseViewModel
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
                    val isMaintenance = remoteConfig.getBoolean("isMaintenance")
                    updateMessage = remoteConfig.getString("updateMessage")
                    updateLink = remoteConfig.getString("updateLink")
                    maintenanceLiveData.postValue(isMaintenance)
                } else {
                    maintenanceLiveData.postValue(false)
                }
            }
    }

    fun checkVersion() {
        val minVersion = remoteConfig.getString("minVersion").split(".").joinToString("").toInt()
        val maxVersion = remoteConfig.getString("maxVersion").split(".").joinToString("").toInt()
        val currentVersion = BuildConfig.VERSION_NAME.split(".").joinToString("").toInt()
        Log.d("178minVersion: ", minVersion.toString())
        Log.d("178maxVersion: ", maxVersion.toString())

        when {
            currentVersion >= maxVersion -> {
                versionStatusLiveData.postValue(VersionStatus.UP_TO_DATE)
            }
            currentVersion in minVersion..maxVersion -> {
                versionStatusLiveData.postValue(VersionStatus.OPTIONAL_UPDATE)
            }
            currentVersion < minVersion -> {
                versionStatusLiveData.postValue(VersionStatus.MANDATORY_UPDATE)
            }
        }
    }

}

enum class VersionStatus {
    UP_TO_DATE,
    OPTIONAL_UPDATE,
    MANDATORY_UPDATE
}