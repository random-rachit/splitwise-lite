package com.rachitbhutani.splitwiselite

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.rachitbhutani.splitwiselite.data.Expense
import com.rachitbhutani.splitwiselite.data.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dummyExpenseProvider: DummyExpenseProvider
) : ViewModel() {

    val uiState: MutableState<ExpenseListUiState> = mutableStateOf(ExpenseListUiState.Empty)

    init {
        getUserBalance()
    }

    private fun getUserBalance() {
        uiState.value = ExpenseListUiState.UserList(dummyExpenseProvider.getUsers())
    }

    fun addExpense(expense: Expense) {
        dummyExpenseProvider.addExpense(expense)
        getUserBalance()
    }

    fun getUsers() = dummyExpenseProvider.getUsers()

}

sealed class ExpenseListUiState {

    object Empty : ExpenseListUiState()

    data class ExpenseList(val list: List<Expense>) : ExpenseListUiState()

    data class UserList(val list: List<User>) : ExpenseListUiState()

}

//TODO: Get expenses by particular user