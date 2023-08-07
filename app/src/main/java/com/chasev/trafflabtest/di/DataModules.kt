package com.chasev.trafflabtest.di

import android.content.Context
import androidx.room.Room
import com.chasev.trafflabtest.data.repository.TransactionRepository
import com.chasev.trafflabtest.data.repository.TransactionRepositoryImpl
import com.chasev.trafflabtest.data.source.local.LocalDataSource
import com.chasev.trafflabtest.data.source.local.RoomLocalDataSource
import com.chasev.trafflabtest.data.source.local.TransactionDatabase
import com.chasev.trafflabtest.data.useCases.AddTransactionUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule{

    @Singleton
    @Provides
    fun provideAddTransactionUseCase(
        repository: TransactionRepository
    ): AddTransactionUseCase {
        return AddTransactionUseCase(repository)
    }

}

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule{

    @Singleton
    @Provides
    fun provideTransactionRepository(
        localDataSource: LocalDataSource,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ):TransactionRepository{
        return TransactionRepositoryImpl(localDataSource, ioDispatcher)
    }
}

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule{

    @Singleton
    @Provides
    fun provideLocalDataSource(
        database: TransactionDatabase,
        @IoDispatcher ioDispatcher:CoroutineDispatcher
    ):LocalDataSource{
        return RoomLocalDataSource(database.transactionDao(), ioDispatcher)
    }

}

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context:Context): TransactionDatabase{
        return Room.databaseBuilder(
            context.applicationContext,
            TransactionDatabase::class.java,
            "Transaction.db"
        ).build()
    }
}
