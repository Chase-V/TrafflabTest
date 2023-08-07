package com.chasev.trafflabtest.data.repository

import com.chasev.trafflabtest.data.source.local.LocalDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import java.util.Date
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val ioCoroutineDispatcher: CoroutineDispatcher
) : TransactionRepository {
    override fun getTransactionFlow(transactionId: String): Flow<WorkResult<BudgetTransaction?>> =
        localDataSource.getTransactionFlow(transactionId)


    override fun getListOfTransactionFlow(): Flow<WorkResult<List<BudgetTransaction>>> =
        localDataSource.getListOfTransactionFlow()


    override fun getSortedListOfTransactionsFlow(
        startDate: Date,
        endDate: Date
    ): Flow<WorkResult<List<BudgetTransaction>>> =
        localDataSource.getSortedListOfTransactions(startDate, endDate)


    override suspend fun addTransaction(transaction: BudgetTransaction) {
        localDataSource.addTransaction(transaction)
    }

    override suspend fun editTransaction(transaction: BudgetTransaction) {
        localDataSource.editTransaction(transaction)
    }

    override suspend fun deleteTransaction(transactionId: String) {
        localDataSource.deleteTransaction(transactionId)
    }

}