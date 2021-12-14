package com.pdstudios.timemanager.screens.to_do_list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pdstudios.timemanager.R
import com.pdstudios.timemanager.databinding.FragmentToDoListBinding


class ToDoListFragment : Fragment() {
    private lateinit var binding: FragmentToDoListBinding
    private lateinit var viewModel: ToDoListViewModel

    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var adapter: RecyclerView.Adapter<ToDoListRecyclerAdapter.ViewHolder>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //BINDING
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_to_do_list, container, false)

        //VIEW MODEL
        viewModel = ViewModelProvider(this).get(ToDoListViewModel::class.java)
        binding.toDoListViewModel = viewModel
        binding.lifecycleOwner = this


        //RECYCLER VIEW
        layoutManager = LinearLayoutManager(this.context)
        binding.recyclerViewToDoList.layoutManager = layoutManager
        adapter = ToDoListRecyclerAdapter(viewModel.toDoList) {
            Log.i("Test", "LambdaWorks OK")
            viewModel.notifyListChange()
        }
        binding.recyclerViewToDoList.adapter = adapter


        viewModel.notifyListChange.observe(viewLifecycleOwner) {
            adapter.notifyDataSetChanged()
            Log.i("Test", "notifyDatasetChanged OK")
        }

        //NAVIGATION
        viewModel.isNavigateToHomeScreen.observe(viewLifecycleOwner) { navigate ->
            navigate?.let {
                this.findNavController().navigate(ToDoListFragmentDirections.actionToDoListFragmentToHomeScreen())
            }
        }
        viewModel.isNavigateToTDLAddTask.observe(viewLifecycleOwner) { navigate ->
            navigate?.let {
                this.findNavController().navigate(ToDoListFragmentDirections
                    .actionToDoListFragmentToTDLAddTaskFragment())
            }
        }

        return binding.root
    }
}