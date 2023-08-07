package com.chasev.trafflabtest.ui.transactionsList

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.chasev.trafflabtest.R
import com.chasev.trafflabtest.ui.elements.addCircleIcon
import com.chasev.trafflabtest.ui.elements.chartIcon

@Composable
fun TransactionsListScreen(
    addTransaction: () -> Unit,
    editTransaction: (String) -> Unit,
    openStatistics: () -> Unit,
    viewModel: TransactionListViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var showDialog by remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            TransactionsListTopAppBar(
                stringResource(id = R.string.app_name),
                onAddDefaultTransactionsButtonClick = {
                    showDialog = true
                },
                onChartButtonClick = openStatistics
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = addTransaction) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(id = R.string.add_transaction)
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = innerPadding)
        ) {

            if (uiState.transactions.isEmpty()) {
                NoTransactionsInfo()
            } else {
                TransactionsList(
                    transactions = uiState.transactions,
                    onDeleteClick = { viewModel.deleteTransaction(it) },
                    onEditClick = editTransaction
                )
            }
        }
    }

    if (showDialog) {
        ShowDialog(
            onConfirmClick = { viewModel.addSampleTransactions(); showDialog = false },
            onDismiss = { showDialog = false })
    }

}



@Composable
fun NoTransactionsInfo() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = stringResource(id = R.string.no_transactions_added))
    }
}

@Composable
fun ShowDialog(
    onConfirmClick: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        title = { Text(text = "Добавить 5 транзакций для примера?") },
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = onConfirmClick) {
                Text(text = "Да")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(text = "Нет")
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionsListTopAppBar(
    title: String,
    onAddDefaultTransactionsButtonClick: () -> Unit,
    onChartButtonClick: () -> Unit
) {

    TopAppBar(
        title = {
            Text(text = title)
        },
        actions = {
            IconButton(onClick = onChartButtonClick) {
                Icon(imageVector = chartIcon(), contentDescription = null)
            }

            IconButton(onClick = onAddDefaultTransactionsButtonClick) {
                Icon(imageVector = addCircleIcon(), contentDescription = null)
            }
        }
    )
}





