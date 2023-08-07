package com.chasev.trafflabtest.ui.addEditTransaction

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.chasev.trafflabtest.R
import java.util.Date

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditTransactionScreen(
    onTransactionUpdate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AddEditTransactionViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    var showDatePicker: Boolean by remember {
        mutableStateOf(false)
    }
    var showTimePicker: Boolean by remember {
        mutableStateOf(false)
    }

    val datePickerState = rememberDatePickerState()


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopAppBar(title = { Text(text = stringResource(id = R.string.app_name)) }) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { if (!uiState.isTransactionSaving) viewModel.saveTransaction() }
            ) {
                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = stringResource(id = R.string.save_transaction)
                )
            }
        }) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {

            AddEditTransactionContent(
                isLoading = uiState.isLoading,
                isSaving = uiState.isTransactionSaving,
                amount = uiState.transactionAmount,
                comment = uiState.transactionComment,
                isIncome = uiState.transactionIsIncome,
                whenAdded = uiState.transactionWhenAdded,
                onAmountChanged = { newAmount -> viewModel.setTransactionAmount(newAmount) },
                onCommentChanged = { newComment -> viewModel.setTransactionComment(newComment) },
                onIsIncomeChanged = { newValue -> viewModel.setIsIncome(newValue) },
                onDatePickerClick = { showDatePicker = true }
            )

            LaunchedEffect(uiState.isTransactionSaved) {
                if (uiState.isTransactionSaved) {
                    onTransactionUpdate()
                }
            }

            if (showDatePicker) {
                DatePickerDialog(
                    onDismissRequest = { showDatePicker = false },
                    confirmButton = {
                        TextButton(onClick = { showDatePicker = false; showTimePicker = true }) {
                            Text(text = "Готово")
                            if (datePickerState.selectedDateMillis != null) {
                                viewModel.setDateTime(Date(datePickerState.selectedDateMillis!!))
                            }

                        }
                    }
                ) {
                    DatePicker(state = datePickerState)
                }
            }
        }
    }


    val context = LocalContext.current
    val errorText = uiState.transactionSavingError?.let { stringResource(id = it) }
    LaunchedEffect(errorText) {
        if (errorText != null) {
            Toast.makeText(context, errorText, Toast.LENGTH_SHORT).show()
        }
    }
}

