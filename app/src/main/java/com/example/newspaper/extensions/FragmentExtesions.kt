package com.example.newspaper.extensions

import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController

fun Fragment.navigate(navDirections: NavDirections) {
    findNavController().apply {
        currentDestination?.getAction(navDirections.actionId)?.run {
            navigate(navDirections.actionId, navDirections.arguments)
        }
    }
}