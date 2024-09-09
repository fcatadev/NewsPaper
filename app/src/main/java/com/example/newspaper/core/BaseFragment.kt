package com.example.newspaper.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer

abstract class BaseFragment<VM : BaseViewModel, VB : ViewDataBinding>(
    @LayoutRes private val layoutResId: Int
) : Fragment() {

    protected lateinit var binding: VB
    protected abstract val viewModel: VM
    abstract fun onInitDataBinding()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutResId, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onInitDataBinding()
        setupViewEventObserver()
    }

    private fun setupViewEventObserver() {
        viewModel.viewEvent.observe(viewLifecycleOwner, Observer { event ->
            event.getContentIfNotHandled()?.let { handleViewEvent(it) }
        })
    }

    open fun handleViewEvent(event: Any) {}
}