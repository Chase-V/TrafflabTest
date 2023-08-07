package com.chasev.trafflabtest.data.source.local

import com.chasev.trafflabtest.data.repository.BudgetTransaction
import com.chasev.trafflabtest.data.repository.WorkResult
import kotlinx.coroutines.flow.Flow
import java.util.Date

interface LocalDataSource {

    fun getTransactionFlow(transactionId:String): Flow<WorkResult<BudgetTransaction?>>
    fun getListOfTransactionFlow(): Flow<WorkResult<List<BudgetTransaction>>>
    fun getSortedListOfTransactions(startDate: Date, endDate: Date): Flow<WorkResult<List<BudgetTransaction>>>

    suspend fun addTransaction(transaction: BudgetTransaction)
    suspend fun editTransaction(transaction: BudgetTransaction)
    suspend fun deleteTransaction(transactionId: String)
}