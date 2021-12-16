package com.pdstudios.timemanager.screens.to_do_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.LEFT
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pdstudios.timemanager.R
import com.pdstudios.timemanager.database.TimeManagerDatabase
import com.pdstudios.timemanager.database.to_do_list.ToDoListForm
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

        //DATABASE
        val application = requireNotNull(this.activity).application//WHY NOT APPLICATION CONTEXT?????????????????????
        val toDoListDao = TimeManagerDatabase.getInstance(application).toDoListDao

        //VIEW MODEL
        val viewModelFactory = ToDoListViewModelFactory(toDoListDao)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ToDoListViewModel::class.java)
        binding.toDoListViewModel = viewModel
        binding.lifecycleOwner = this


        //RECYCLER VIEW
        layoutManager = LinearLayoutManager(this.context)
        binding.recyclerViewToDoList.layoutManager = layoutManager
        adapter = ToDoListRecyclerAdapter(viewModel.toDoList) {task: ToDoListForm ->
            viewModel.updateTask(task)
        }

        binding.recyclerViewToDoList.adapter = adapter


        viewModel.toDoList.observe(viewLifecycleOwner) {
            adapter.notifyDataSetChanged()
        }

        val swipeLeftIcon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_delete_24)!!
        val swipeRightIcon = null
        val swipeDir = LEFT
        val swipeGesture = object : SwipeGesture(swipeLeftIcon, swipeRightIcon, swipeDir){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = viewModel.toDoList.value!![viewHolder.adapterPosition]
                when(direction) {
                    LEFT -> {
                        viewModel.deleteFromToDoList(item.id)
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        }

        ItemTouchHelper(swipeGesture).attachToRecyclerView(binding.recyclerViewToDoList)

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