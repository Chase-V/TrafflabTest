package com.chasev.trafflabtest.ui.addEditTransaction

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chasev.trafflabtest.R
import com.chasev.trafflabtest.data.repository.BudgetTransaction
import com.chasev.trafflabtest.data.repository.TransactionRepository
import com.chasev.trafflabtest.data.repository.WorkResult
import com.chasev.trafflabtest.data.useCases.AddTransactionUseCase
import com.chasev.trafflabtest.navigation.AppDestinationArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class AddEditTransactionViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val addTransactionUseCase: AddTransactionUseCase,
    private val transactionRepo: TransactionRepository
) : ViewModel() {
    private val transactionId: String? = savedStateHandle[AppDestinationArgs.TRANSACTION_ID_ARG]

    private val _uiState = MutableStateFlow(AddEditTransactionUiState())
    val uiState: StateFlow<AddEditTransactionUiState> = _uiState.asStateFlow()

    init {
        if (transactionId != null) {
            loadTransaction(transactionId)
        }
    }

    private fun loadTransaction(transactionId: String) {
        _uiState.update { it.copy(isLoading = true) }

        viewModelScope.launch {

            val result = transactionRepo.getTransactionFlow(transactionId).first()

            if (result !is WorkResult.Success || result.data == null) {
                _uiState.update { it.copy(isLoading = false) }
            } else {
                val transaction = result.data
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        transactionAmount = transaction.amount,
                        transactionIsIncome = transaction.isIncome,
                        transactionComment = transaction.comment.orEmpty(),
                        transactionWhenAdded = transaction.whenAdded,
                    )
                }
            }
        }
    }

    fun saveTransaction() {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(isTransactionSaving = true) }
                addTransactionUseCase(
                    BudgetTransaction(
                        transactionId = transactionId,
                        isIncome = _uiState.value.transactionIsIncome,
                        amount = _uiState.value.transactionAmount,
                        comment = _uiState.value.transactionComment,
                        whenAdded = _uiState.value.transactionWhenAdded
                    )
                )
                _uiState.update { it.copy(isTransactionSaved = true) }
            } catch (e: Exception) {
                _uiState.update { it.copy(transactionSavingError = R.string.transaction_saving_error) }
            } finally {
                _uiState.update { it.copy(isTransactionSaving = false) }
            }
        }
    }

    fun setDateTime(newDate:Date){
        _uiState.update { it.copy(transactionWhenAdded = newDate) }
    }

    fun setTransactionAmount(newAmount:Float){
        _uiState.update { it.copy(transactionAmount = newAmount) }
    }

    fun setTransactionComment(newComment:String){
        _uiState.update { it.copy(transactionComment = newComment) }
    }

    fun setIsIncome(newValue:Boolean){
        _uiState.update { it.copy(transactionIsIncome = newValue) }
    }
}

data class AddEditTransactionUiState(
    val transactionAmount: Float = 0f,
    val transactionIsIncome: Boolean = false,
    val transactionComment: String = "",
    val transactionWhenAdded: Date = Date(),
    val isLoading: Boolean = false,
    val isTransactionSaving: Boolean = false,
    val isTransactionSaved: Boolean = false,
    val transactionSavingError: Int? = null
)