package com.pdstudios.timemanager.screens.to_do_list

import android.content.Context
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.view.get
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.pdstudios.timemanager.databinding.ToDoListCardBinding

class ToDoListRecyclerAdapter(
    private val toDoList: LiveData<MutableList<String>>,
    private val callback: () -> Unit,
) : RecyclerView.Adapter<ToDoListRecyclerAdapter.ViewHolder>() {

    private lateinit var binding: ToDoListCardBinding
    private val adapterToDoList = listOf("A", "B", "C", "D")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ToDoListCardBinding.inflate(inflater, parent, false)

        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = toDoList.value!![position]
        holder.bind(item, position)
    }

    override fun getItemCount(): Int {
        return toDoList.value!!.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val viewSwitcher = binding.viewSwitcherToDoListItem
        val textView = binding.textViewToDoListItem
        val editText = binding.editTextToDoListItem
        val checkBox = binding.checkBoxToDoListItem
        val date = binding.textViewToDoListItemDate

        fun bind(item: String, position: Int) {
            textView.text = item

            val inputManager: InputMethodManager =
                itemView.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager


            Log.i("Test", "textView was set")
            itemView.setOnClickListener {
                toDoList.value!![position] = "Plus!"
                callback()
            }

            itemView.setOnLongClickListener {
                viewSwitcher.showNext()
                val isEditText = (viewSwitcher[1] == editText)
                if (isEditText) {

                    viewSwitcher.requestFocus()
                }
                inputManager.showSoftInput(editText, 0)
                true
            }

            editText.setOnKeyListener { view, keyCode, event ->
                var finish = false
                val isEnterPressedDown = (keyCode ==  KeyEvent.KEYCODE_ENTER)
                        && (event.action == KeyEvent.ACTION_DOWN)
                if (isEnterPressedDown) {
                    toDoList.value!![position] = editText.text.toString()
                    textView.text = editText.text.toString()
                    viewSwitcher.showNext()
                    inputManager.hideSoftInputFromWindow(editText.applicationWindowToken, 0)
                    finish = true
                }
                finish
            }


        }
    }
}