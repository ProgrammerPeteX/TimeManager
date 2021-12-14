package com.pdstudios.timemanager.screens.to_do_list.to_do_list_add_task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.pdstudios.timemanager.R
import com.pdstudios.timemanager.databinding.FragmentTDLAddTaskBinding


class TDLAddTaskFragment : Fragment() {
    private lateinit var binding: FragmentTDLAddTaskBinding
    private lateinit var viewModel: TDLAddTaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //BINDING
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_t_d_l_add_task, container, false)

        //VIEW MODEL
        viewModel = ViewModelProvider(this).get(TDLAddTaskViewModel::class.java)
        binding.tdlAddTaskViewModel = viewModel
        binding.lifecycleOwner = this

        //NAVIGATION
        viewModel.isNavigateToToDoList.observe(viewLifecycleOwner) { navigate ->
            navigate?.let {
                this.findNavController().navigate(TDLAddTaskFragmentDirections.
                actionTDLAddTaskFragmentToToDoListFragment())
            }
        }

        return binding.root
    }
}