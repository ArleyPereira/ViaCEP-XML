package com.example.viacep.di

import android.content.Context
import androidx.room.Room
import com.example.viacep.R
import com.example.viacep.data.local.dao.AddressDao
import com.example.viacep.data.local.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun providesDatabase(
        @ApplicationContext context: Context
    ): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        context.getString(R.string.app_name_database)
    )
        .addMigrations(AppDatabase.MIGRATION_3_4)
        .build()

    @Provides
    fun providesAddressDao(database: AppDatabase): AddressDao = database.addressDao()

}