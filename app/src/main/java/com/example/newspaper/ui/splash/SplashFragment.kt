package com.example.newspaper.ui.splash

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.newspaper.R
import com.example.newspaper.core.BaseFragment
import com.example.newspaper.data.remote.preferences.PreferencesConstants
import com.example.newspaper.databinding.FragmentSplashBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment :
    BaseFragment<SplashViewModel, FragmentSplashBinding>(R.layout.fragment_splash) {

    override val viewModel: SplashViewModel by viewModels()

    override fun onInitDataBinding() {
        viewLifecycleOwner.lifecycleScope.launch {
            delay(3000)
            setupObservers()
            viewModel.fetchRemoteConfigValues()
        }
    }

    private fun isUserLoggedIn(): Boolean {
        val sharedPrefs = requireContext().getSharedPreferences(
            PreferencesConstants.USER_PREFS,
            Context.MODE_PRIVATE
        )
        return sharedPrefs.getBoolean(PreferencesConstants.IS_LOGGED_IN, false)
    }

    private fun setupObservers() {
        viewModel.maintenanceLiveData.observe(viewLifecycleOwner) { isMaintenance ->
            if (isMaintenance) {
                findNavController().navigate(R.id.action_splashFragment_to_maintenanceFragment)
            } else {
                viewModel.checkVersion()
            }
        }

        viewModel.versionStatusLiveData.observe(viewLifecycleOwner) { status ->
            when (status) {
                VersionStatus.UP_TO_DATE -> proceedToAuthOrHome()
                VersionStatus.OPTIONAL_UPDATE -> showOptionalUpdateDialog()
                VersionStatus.FORCE_UPDATE -> showForceUpdateDialog()
            }
        }
    }

    private fun proceedToAuthOrHome() {
        if (isUserLoggedIn()) {
            findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
        } else {
            findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
        }
    }

    private fun showOptionalUpdateDialog() {
        val updateMessage = viewModel.updateMessage
        val updateLink = viewModel.updateLink

        AlertDialog.Builder(requireContext())
            .setMessage(updateMessage)
            .setPositiveButton(getString(R.string.update)) { _, _ ->
                openUpdateLink(updateLink)
            }
            .setNegativeButton(getString(R.string.then)) { _, _ ->
                proceedToAuthOrHome()
            }
            .show()
    }

    private fun showForceUpdateDialog() {
        val updateMessage = viewModel.updateMessage
        val updateLink = viewModel.updateLink

        AlertDialog.Builder(requireContext())
            .setMessage(updateMessage)
            .setPositiveButton(getString(R.string.update)) { _, _ ->
                openUpdateLink(updateLink)
            }
            .setCancelable(false)
            .show()
    }

    private fun openUpdateLink(updateLink: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(updateLink))
        startActivity(intent)
    }

}