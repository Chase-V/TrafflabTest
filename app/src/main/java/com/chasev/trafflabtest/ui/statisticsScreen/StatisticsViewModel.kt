package com.chasev.trafflabtest.ui.statisticsScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chasev.trafflabtest.data.repository.BudgetTransaction
import com.chasev.trafflabtest.data.repository.TransactionRepository
import com.chasev.trafflabtest.data.repository.WorkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class StatisticsViewModel @Inject constructor(
    private val transactionsRepo: TransactionRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(StatisticsUiState())
    val uiState: StateFlow<StatisticsUiState> = _uiState.asStateFlow()

    init {
        loadSortedListOfTransactions(_uiState.value.startDate, _uiState.value.endDate)
    }

    private fun loadSortedListOfTransactions(startDate: Date, endDate: Date) {
        _uiState.update { it.copy(isLoading = true) }

        viewModelScope.launch {

            transactionsRepo.getSortedListOfTransactionsFlow(
                startDate = startDate,
                endDate = endDate
            ).collect { transactions ->

                when (transactions) {
                    is WorkResult.Loading -> {
                        _uiState.update { it.copy(isLoading = true) }
                    }

                    is WorkResult.Error -> {
                        _uiState.update { it.copy(isError = true) }
                    }

                    is WorkResult.Success -> {
                        var income = 0f
                        var expense = 0f
                        transactions.data.forEach {
                            if (it.isIncome) {
                                income += it.amount
                            } else {
                                expense += it.amount
                            }
                        }
                        _uiState.update {
                            it.copy(
                                transactions = transactions.data.reversed(),
                                income = income,
                                expense = expense,
                                difference = (income - expense),
                                isLoading = false
                            )
                        }
                    }
                }
            }
        }
    }

    fun setPickedDate(startDate: Date, endDate: Date) {
        _uiState.update { it.copy(startDate = startDate, endDate = endDate) }
        loadSortedListOfTransactions(startDate, endDate)
    }
}

data class StatisticsUiState(
    val transactions: List<BudgetTransaction> = emptyList(),
    val income: Float = 0f,
    val expense: Float = 0f,
    val difference: Float = 0f,
    val startDate: Date = Date.from(
        LocalDate.now()
            .withDayOfMonth(1)
            .atStartOfDay(ZoneId.systemDefault())
            .toInstant()
    ),
    val endDate: Date = Date(),
    val isLoading: Boolean = false,
    val isError: Boolean = false
)