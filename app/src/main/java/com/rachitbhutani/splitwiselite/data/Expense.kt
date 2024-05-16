package com.rachitbhutani.splitwiselite.data

data class Expense(
    val id: String,
    val name: String,
    val amount: Float,
    val paidBy: User
)