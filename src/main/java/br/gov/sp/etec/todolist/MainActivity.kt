package br.gov.sp.etec.todolist

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        todoAdapter = TodoAdapter(mutableListOf())

        val rvTodoList = findViewById<RecyclerView>(R.id.rvToDoList)
        rvTodoList.adapter = todoAdapter
        rvTodoList.layoutManager = LinearLayoutManager(this)

        val btnAdd = findViewById<Button>(R.id.btn_Add)
        val btnDelete = findViewById<Button>(R.id.btn_Delete)

        btnAdd.setOnClickListener {
            val todoTask = findViewById<EditText>(R.id.editText).text.toString()
            if (todoTask.isNotEmpty()) {
                val todo = Todo(todoTask)
                todoAdapter.addTodo(todo)
                findViewById<EditText>(R.id.editText).text.clear()
            }
        }

        btnDelete.setOnClickListener{
            todoAdapter.deleteDoneTodos()
        }

    }
}