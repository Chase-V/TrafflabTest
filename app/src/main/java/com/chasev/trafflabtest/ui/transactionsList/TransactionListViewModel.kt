package com.chasev.trafflabtest.ui.transactionsList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chasev.trafflabtest.data.repository.BudgetTransaction
import com.chasev.trafflabtest.data.repository.TransactionRepository
import com.chasev.trafflabtest.data.repository.WorkResult
import com.chasev.trafflabtest.data.useCases.AddTransactionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class TransactionListViewModel @Inject constructor(
    private val addTransactionUseCase: AddTransactionUseCase,
    private val transactionsRepo: TransactionRepository
) : ViewModel() {

    private val transactionsList = transactionsRepo.getListOfTransactionFlow()

    val uiState = transactionsList.map { transactions ->
        when (transactions) {
            is WorkResult.Error -> TransactionListUiState(isError = true)
            is WorkResult.Loading -> TransactionListUiState(isLoading = true)
            is WorkResult.Success -> TransactionListUiState(transactions = transactions.data.reversed())
        }
    }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = TransactionListUiState(isLoading = true)
        )

    fun addSampleTransactions() {
        viewModelScope.launch {
            val transactions = arrayOf(
                BudgetTransaction(
                    isIncome = true,
                    amount = 1000f,
                    whenAdded = Date(),
                    comment = "Зарплата"
                ),
                BudgetTransaction(isIncome = false, amount = 100f, whenAdded = Date()),
                BudgetTransaction(
                    isIncome = false,
                    amount = 228.69f,
                    whenAdded = Date(),
                    comment = "Шоколадка"
                ),
                BudgetTransaction(
                    isIncome = true,
                    amount = 100f,
                    whenAdded = Date(),
                    comment = "Вернули долг"
                ),
                BudgetTransaction(
                    isIncome = false,
                    amount = 100f,
                    whenAdded = Date(),
                    comment = "Кофе"
                ),
            )

            transactions.forEach { addTransactionUseCase(it) }
        }
    }

    fun deleteTransaction(transactionId: String) {
        viewModelScope.launch {
            transactionsRepo.deleteTransaction(transactionId)
        }
    }

}

data class TransactionListUiState(
    val transactions: List<BudgetTransaction> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false
)