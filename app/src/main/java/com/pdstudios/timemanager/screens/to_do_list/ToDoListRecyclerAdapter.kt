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
import com.pdstudios.timemanager.database.to_do_list.ToDoListForm
import com.pdstudios.timemanager.databinding.ToDoListCardBinding
import java.text.SimpleDateFormat
import java.util.*

class ToDoListRecyclerAdapter(
    private val toDoList: LiveData<List<ToDoListForm>>,
    private val updateTaskCallback: (ToDoListForm) -> Unit,
) : RecyclerView.Adapter<ToDoListRecyclerAdapter.ViewHolder>() {

    private lateinit var binding: ToDoListCardBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ToDoListCardBinding.inflate(inflater, parent, false)

        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = toDoList.value!![position]
        holder.bind(task)
    }

    override fun getItemCount(): Int {
        return toDoList.value?.size ?: 0
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val viewSwitcher = binding.viewSwitcherToDoListItem
        val textView = binding.textViewToDoListItem
        val editText = binding.editTextToDoListItem
        val checkBox = binding.checkBoxToDoListItem
        val date = binding.textViewToDoListItemDate

        fun bind(task: ToDoListForm) {
            textView.text = task.name
            date.text = getDateTime(task.dateTimeCreated)
            checkBox.isChecked = task.isChecked

            val inputManager: InputMethodManager =
                itemView.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

            itemView.setOnClickListener {
//                task.name = "Plus!"
//                updateTaskCallback(task)
            }

            itemView.setOnLongClickListener {
                viewSwitcher.showNext()
                editText.setText(textView.text)
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
                    task.name = editText.text.toString()
                    updateTaskCallback(task)
                    textView.text = editText.text.toString()
                    viewSwitcher.showNext()
                    inputManager.hideSoftInputFromWindow(editText.applicationWindowToken, 0)
                    finish = true
                }
                finish
            }
//
//            checkBox.setOnCheckedChangeListener { _, isChecked ->
//                task.isChecked = isChecked
//                updateTaskCallback(task)
//                Log.i("Test", "isChecked = $isChecked")
//            }
            checkBox.setOnClickListener {
                task.isChecked = checkBox.isChecked
                updateTaskCallback(task)
            }
        }

        private fun getDateTime(millis: Long): String {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = millis
            val formatter = SimpleDateFormat("dd.MM.yyyy 'at' hh:mm:ss", Locale.getDefault())
            return formatter.format(calendar.time)
        }
    }
}
