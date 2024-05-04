package com.example.attendance.ui.clockin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.attendance.databinding.FragmentClockInBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ClockInFragment : Fragment() {

//    private val clockInViewModel: ClockInViewModel by viewModels()

    private var _binding: FragmentClockInBinding? = null

    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): ConstraintLayout? {
        _binding = FragmentClockInBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}