package com.chasev.trafflabtest.data.repository

import java.util.Date

data class BudgetTransaction(
    val transactionId: String? = null,
    val isIncome: Boolean,
    val amount: Float,
    val comment: String? = null,
    val whenAdded: Date
)
