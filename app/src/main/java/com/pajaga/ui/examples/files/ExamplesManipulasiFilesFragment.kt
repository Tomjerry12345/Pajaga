package com.pajaga.ui.examples.files

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.pajaga.R
import com.pajaga.databinding.ExamplesManipulasiFilesFragmentBinding

class ExamplesManipulasiFilesFragment : Fragment(R.layout.examples_manipulasi_files_fragment) {

    private val viewModel: ExamplesManipulasiFilesViewModel by viewModels {
        ExamplesManipulasiFilesViewModel.Factory()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = ExamplesManipulasiFilesFragmentBinding.bind(view)
        binding.viewModel = viewModel
    }

}