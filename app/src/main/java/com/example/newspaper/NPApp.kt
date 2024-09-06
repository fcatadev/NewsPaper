package com.example.newspaper

import android.app.Application
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NPApp : Application() {


    override fun onCreate() {
        super.onCreate()
        initFirebaseAuth()
    }

    private fun initFirebaseAuth() {
        FirebaseApp.initializeApp(this)
    }

}