package com.rachitbhutani.splitwiselite

import com.rachitbhutani.splitwiselite.data.Expense
import com.rachitbhutani.splitwiselite.data.User
import javax.inject.Singleton

@Singleton
class DummyExpenseProvider {

    private val _expenseList = mutableListOf<Expense>()

    fun addExpense(expense: Expense) {
        _expenseList.add(expense)
        val perUserAmount = expense.amount / users.size
        users = users.map {

            val newBalance = if (it.id == expense.paidBy.id) {
                (it.balance + expense.amount) - perUserAmount
            } else {
                it.balance - perUserAmount
            }
            it.copy(balance = newBalance)
        }
    }

    fun getExpenses(): List<Expense> = _expenseList

    fun getUsers(): List<User> = users

    private var users = listOf(
        User("abc", "Alice"),
        User("def", "Bob"),
        User("ghi", "Charlie"),
    )

}
