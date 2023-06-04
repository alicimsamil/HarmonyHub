package com.alicimsamil.harmonyhub.core.presentation

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ProgressBar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.alicimsamil.harmonyhub.R
import com.alicimsamil.harmonyhub.core.common.extensions.EMPTY

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T


/**
 * This fragment was written to gather the common operations of all other fragments in one place.
 */
abstract class BaseFragment<VB : ViewBinding, VM : BaseViewModel>(private val inflate: Inflate<VB>) :
    Fragment() {

    protected abstract val viewModel: VM
    protected abstract val state: UiState

    private var _binding: VB? = null
    protected val binding get() = _binding!!

    var isErrorMessageShowing = false

    private lateinit var constraintLayout: ConstraintLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    /**
     * This function is where the ui operations in the fragment should be start.
     */
    protected open fun initialize() {
        constraintLayout = ConstraintLayout(requireContext())
    }

    /**
     * This function handles the change of failure value in states.
     */
    protected fun handleFailure() {
        if (state.error.isNotEmpty()) {
            if (!isErrorMessageShowing) {
                AlertDialog.Builder(context)
                    .setTitle(getString(R.string.error))
                    .setMessage(state.error)
                    .create()
                    .show()
            }
            state.error = String.EMPTY
        }
    }

    /**
     * This function handles the change of loading value in states.
     */
    protected fun handleLoading() {
        val progressBar = ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal)
        progressBar.layoutParams = ConstraintLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        progressBar.isIndeterminate = true
        if (state.isLoading) {
            constraintLayout.addView(progressBar)
        } else {
            constraintLayout.removeView(progressBar)
        }
    }

    /**
     * This function takes an id and bundle parameter and redirect with navigation component
     * according to these parameters.
     */
    protected fun navigate(id: Int, bundle: Bundle? = null) {
        bundle?.let {
            findNavController().navigate(id, bundle)
        } ?: run {
            findNavController().navigate(id)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}