package com.chasev.trafflabtest.data.source.local

import com.chasev.trafflabtest.data.repository.BudgetTransaction
import com.chasev.trafflabtest.data.repository.WorkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.Date

class RoomLocalDataSource internal constructor(
    private val transactionDao: TransactionDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : LocalDataSource {
    override fun getTransactionFlow(transactionId: String): Flow<WorkResult<BudgetTransaction?>> =
        transactionDao.observeTransactionById(transactionId).map {
            WorkResult.Success(it?.toBudgetTransaction())
        }

    override fun getListOfTransactionFlow(): Flow<WorkResult<List<BudgetTransaction>>> =
        transactionDao.observeListOfTransactions().map {
            WorkResult.Success(
                it.map { budgetTransactionEntity ->
                    budgetTransactionEntity.toBudgetTransaction()
                }
            )
        }

    override fun getSortedListOfTransactions(startDate: Date, endDate: Date): Flow<WorkResult<List<BudgetTransaction>>> =
        transactionDao.getSortedListOfTransactions(startDate,endDate).map {
            WorkResult.Success(
                it.map { budgetTransactionEntity ->
                    budgetTransactionEntity.toBudgetTransaction()
                }
            )
        }

    override suspend fun addTransaction(transaction: BudgetTransaction) {
        transactionDao.insertTransaction(transaction.toBudgetTransactionEntity())
    }

    override suspend fun editTransaction(transaction: BudgetTransaction) {
        transactionDao.updateTransaction(transaction.toBudgetTransactionEntity())
    }

    override suspend fun deleteTransaction(transactionId: String) {
        transactionDao.deleteTransactionById(transactionId)
    }
}