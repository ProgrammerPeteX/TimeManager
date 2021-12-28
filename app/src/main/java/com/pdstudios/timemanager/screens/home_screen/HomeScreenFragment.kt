package com.pdstudios.timemanager.screens.home_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.pdstudios.timemanager.R
import com.pdstudios.timemanager.databinding.FragmentHomeScreenBinding
import com.pdstudios.timemanager.screens.to_do_list.ToDoListFragmentDirections


class HomeScreenFragment : Fragment() {
    private lateinit var binding: FragmentHomeScreenBinding
    private lateinit var viewModel: HomeScreenViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //BINDING
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_screen, container, false)

        //VIEW MODEL
        viewModel = ViewModelProvider(this).get(HomeScreenViewModel::class.java)
        binding.homeScreenViewModel = viewModel
        binding.lifecycleOwner = this

        //NAVIGATION
        viewModel.isNavigateToToDoList.observe(viewLifecycleOwner) { navigate ->
            navigate?.let {
                this.findNavController()
                    .navigate(HomeScreenFragmentDirections.actionHomeScreenToToDoListFragment())
            }
        }

        viewModel.isNavigateToTimeTable.observe(viewLifecycleOwner) { navigate ->
            navigate?.let {
                this.findNavController()
                    .navigate(HomeScreenFragmentDirections.actionHomeScreenToTimeTableFragment())
            }
        }

        return binding.root
    }

}