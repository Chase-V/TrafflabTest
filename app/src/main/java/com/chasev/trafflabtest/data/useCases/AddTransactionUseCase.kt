package com.chasev.trafflabtest.data.useCases

import com.chasev.trafflabtest.data.repository.BudgetTransaction
import com.chasev.trafflabtest.data.repository.TransactionRepository
import javax.inject.Inject

class AddTransactionUseCase @Inject constructor(private val repository: TransactionRepository) {

    suspend operator fun invoke(transaction: BudgetTransaction){
        if (transaction.amount <= 0){
            throw Exception("Пожалуйста, введите положительное число")
        }
        repository.addTransaction(transaction)
    }

}