package br.gov.sp.etec.todolist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.TimerTask

class TodoAdapter(
    private val todos: MutableList<Todo>
): RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTask: TextView = itemView.findViewById(R.id.tvTask)
        val cbStatus: CheckBox = itemView.findViewById(R.id.cbStatus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return todos.size
    }

    private fun toggleStatus(tvTask: TextView, cbStatus: CheckBox) {
        if (cbStatus.isChecked) {
            tvTask.paintFlags = tvTask.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvTask.paintFlags = tvTask.paintFlags or STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    fun addTodo(todo: Todo) {
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
    }

    fun deleteDoneTodos() {
        todos.removeAll { todo ->
            todo.isChecked

        }

        notifyDataSetChanged()

    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val cTodo = todos[position]
        holder.apply{
            tvTask.text = cTodo.task
            cbStatus.isChecked = cTodo.isChecked
            toggleStatus(tvTask, cbStatus)
            cbStatus.setOnCheckedChangeListener { _, isChecked ->
                toggleStatus(tvTask, cbStatus)
                cTodo.isChecked = !cTodo.isChecked
            }
        }
    }
}
