package com.pajaga.ui.examples.files.pdf

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.pajaga.R
import com.pajaga.databinding.ExamplesPdfFragmentBinding
import com.pajaga.utils.other.showToast
import com.pajaga.utils.system.DocumentUtils

class ExamplesPdfFragment : Fragment(R.layout.examples_pdf_fragment) {

    private lateinit var viewModel: ExamplesPdfViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = ExamplesPdfFragmentBinding.bind(view)

        val linear = binding.linearId

        binding.mbExportPdf.setOnClickListener {
            val documentUtils = DocumentUtils(requireActivity())

            val bitmap = documentUtils.createBitmapFromLayout(linear, linear.width, linear.height)
            if (bitmap != null) {
                documentUtils.createPdf(bitmap)
            } else {
                showToast(requireContext(), "Bitmap not found")
            }
        }
    }


}