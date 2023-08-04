package com.chasev.trafflabtest.useCases

import com.chasev.trafflabtest.data.BudgetTransaction
import com.chasev.trafflabtest.repository.TransactionRepository
import javax.inject.Inject

class AddTransaction @Inject constructor(private val repository: TransactionRepository) {

    suspend operator fun invoke(transaction: BudgetTransaction){
        if (transaction.amount <= 0){
            throw Exception("Пожалуйста, введите положительное число")
        }
        repository.addTransaction(transaction)
    }

}