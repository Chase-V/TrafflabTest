package com.chasev.trafflabtest.data.repository

import kotlinx.coroutines.flow.Flow
import java.util.Date

interface TransactionRepository {

    fun getTransactionFlow(transactionId:String): Flow<WorkResult<BudgetTransaction?>>
    fun getListOfTransactionFlow(): Flow<WorkResult<List<BudgetTransaction>>>
    fun getSortedListOfTransactionsFlow(startDate: Date, endDate: Date):Flow<WorkResult<List<BudgetTransaction>>>

    suspend fun addTransaction(transaction: BudgetTransaction)
    suspend fun editTransaction(transaction: BudgetTransaction)
    suspend fun deleteTransaction(transactionId: String)

}