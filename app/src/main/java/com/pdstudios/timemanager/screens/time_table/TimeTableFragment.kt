package com.pdstudios.timemanager.screens.time_table

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.pdstudios.timemanager.R
import com.pdstudios.timemanager.databinding.FragmentTimeTableBinding

class TimeTableFragment : Fragment() {
    private lateinit var binding: FragmentTimeTableBinding
    private lateinit var viewModel: TimeTableViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //BINDING
        binding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_time_table, container, false)

        //VIEW MODEL
        val factory = TimeTableViewModelFactory()
        viewModel = ViewModelProvider(this, factory).get(TimeTableViewModel::class.java)
        binding.timeTableViewModel = viewModel
        binding.lifecycleOwner = this

        //OBSERVERS

        //NAVIGATION
        viewModel.isNavigateToHomeScreen.observe(viewLifecycleOwner) { navigate ->
            navigate?.let {
                this.findNavController()
                    .navigate(TimeTableFragmentDirections.actionTimeTableFragmentToHomeScreen())
            }
        }


        //INFLATE
        return binding.root
    }
}