package br.gov.sp.etec.todolist

data class Todo(
    val task: String,
    var isChecked: Boolean = false
)