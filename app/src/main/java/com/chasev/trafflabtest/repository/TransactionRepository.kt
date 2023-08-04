package com.chasev.trafflabtest.repository

import com.chasev.trafflabtest.data.BudgetTransaction

interface TransactionRepository {

    suspend fun addTransaction(transaction:BudgetTransaction)

}