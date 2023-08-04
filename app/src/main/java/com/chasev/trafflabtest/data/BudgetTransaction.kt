package com.chasev.trafflabtest.data

import java.util.Date

data class BudgetTransaction(
    val id: String?,
    val isIncome: Boolean,
    val amount: Double,
    val comment: String?,
    val added: Date
)
