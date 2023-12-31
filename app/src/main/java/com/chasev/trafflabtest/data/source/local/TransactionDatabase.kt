package com.chasev.trafflabtest.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.util.Date

@Database(entities = [BudgetTransactionEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class TransactionDatabase : RoomDatabase(){

    abstract fun transactionDao():TransactionDao
}

object Converters{
    @TypeConverter
    fun timestampToDate(value:Long?): Date?{
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date:Date?) = date?.time
}