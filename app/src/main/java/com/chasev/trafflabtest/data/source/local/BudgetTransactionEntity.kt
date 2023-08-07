package com.chasev.trafflabtest.data.source.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.chasev.trafflabtest.data.repository.BudgetTransaction
import java.util.Date
import java.util.UUID

@Entity(tableName = "transactions")
data class BudgetTransactionEntity(
    @PrimaryKey var transactionId: String,
    var isIncome: Boolean,
    var amount: Float,
    var comment: String,
    var whenAdded: Date
) {
    fun toBudgetTransaction(): BudgetTransaction = BudgetTransaction(
            transactionId = transactionId,
            isIncome = isIncome,
            amount = amount,
            comment = comment,
            whenAdded = whenAdded
        )
}

fun BudgetTransaction.toBudgetTransactionEntity(): BudgetTransactionEntity =
    BudgetTransactionEntity(
        transactionId = transactionId ?: UUID.randomUUID().toString(),
        isIncome = isIncome,
        amount = amount,
        comment = comment.orEmpty(),
        whenAdded = whenAdded
    )
