package org.d3if0060.assessment2.ui.image

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.d3if0060.assessment2.R
import org.d3if0060.assessment2.databinding.FragmentImageBinding

class ImageFragment : Fragment() {

    private lateinit var binding : FragmentImageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentImageBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
}