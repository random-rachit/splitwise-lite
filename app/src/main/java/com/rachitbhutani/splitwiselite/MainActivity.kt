package com.rachitbhutani.splitwiselite

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import com.rachitbhutani.splitwiselite.data.Expense
import com.rachitbhutani.splitwiselite.data.User
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ComposeView(this).apply {
            setContent {
                MaterialTheme {
                    ExpenseListScreen()
                }
            }
        })
    }


    @Composable
    fun ExpenseListScreen() {
        val state by remember { viewModel.uiState }
        Box(Modifier.fillMaxSize()) {
            when (state) {
                is ExpenseListUiState.UserList -> ExpenseList(
                    Modifier,
                    (state as? ExpenseListUiState.UserList)?.list.orEmpty()
                )

                else -> {}
            }

            ExpenseAdder(
                Modifier.fillMaxWidth().align(Alignment.BottomCenter)
            )
        }
    }

    @Composable
    private fun ExpenseAdder(modifier: Modifier) {
        val users = viewModel.getUsers()
        val selectedUser = remember {
            mutableStateOf(users.first())
        }

        fun addExpense(amount: Float) {
            viewModel.addExpense(
                Expense(
                    System.currentTimeMillis().toString(),
                    "Random",
                    amount,
                    selectedUser.value
                )
            )
        }

        Row(modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            LazyColumn {
                items(users) {
                    Text(
                        modifier = Modifier
                            .background(
                                if (selectedUser.value.id == it.id) Color.Yellow
                                else Color.White
                            )
                            .padding(16.dp)
                            .clickable { selectedUser.value = it }, text = it.name
                    )
                }
            }
            Column {
                Text(text = "3000")
                Button(onClick = { addExpense(3000f) }) {
                    Text(text = "Add Expense")
                }
            }
        }

    }

    @Composable
    fun ExpenseList(modifier: Modifier = Modifier, list: List<User>) {
        LazyColumn(
            modifier = modifier
                .background(Color.White)
                .padding(16.dp)
        ) {
            items(list) {
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = it.name, color = Color.Black)
                    Text(
                        text = it.balance.toString(),
                        color = if (it.balance >= 0f) Color.Green
                        else Color.Red
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}