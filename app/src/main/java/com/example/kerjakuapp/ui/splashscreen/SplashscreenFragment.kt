package com.example.kerjakuapp.ui.splashscreen

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.kerjakuapp.R
import com.example.kerjakuapp.databinding.FragmentSplashscreenBinding

@SuppressLint("CustomSplashScreen")
class SplashscreenFragment : Fragment() {

    private val splashTimeOut: Long = 2000

    private var _binding: FragmentSplashscreenBinding? = null

    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): LinearLayout? {
        _binding = FragmentSplashscreenBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.root?.postDelayed({
            findNavController().navigate(R.id.action_splashscreenFragment_to_loginFragment)
        }, splashTimeOut)
    }
}