package com.chasev.trafflabtest.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import java.util.Date

@Dao
interface TransactionDao {

    @Query("SELECT * FROM Transactions")
    fun observeListOfTransactions(): Flow<List<BudgetTransactionEntity>>

    @Query("SELECT * FROM Transactions WHERE transactionId = :transactionId")
    fun observeTransactionById(transactionId: String): Flow<BudgetTransactionEntity?>

    @Query("SELECT * FROM Transactions")
    suspend fun getListOfTransactions(): List<BudgetTransactionEntity>

    @Query("SELECT * FROM Transactions WHERE whenAdded BETWEEN :startDate AND :endDate")
    fun getSortedListOfTransactions(
        startDate: Date,
        endDate: Date
    ): Flow<List<BudgetTransactionEntity>>

    @Query("SELECT * FROM Transactions WHERE transactionId = :transactionId")
    suspend fun getTransactionById(transactionId: String): BudgetTransactionEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transaction: BudgetTransactionEntity)

    @Update
    suspend fun updateTransaction(transaction: BudgetTransactionEntity)

    @Query("DELETE FROM Transactions WHERE transactionId = :transactionId")
    suspend fun deleteTransactionById(transactionId: String): Int

    @Query("DELETE FROM Transactions")
    suspend fun deleteTransactions()

    @Transaction
    suspend fun setTransactions(transactions: List<BudgetTransactionEntity>) {
        deleteTransactions()
        transactions.forEach { insertTransaction(it) }
    }
}