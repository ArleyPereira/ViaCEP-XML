package com.example.viacep.data.local.db

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RenameColumn
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.viacep.data.local.dao.AddressDao
import com.example.viacep.data.local.entity.AddressEntity

@Database(
    entities = [AddressEntity::class],
    version = 4,
    exportSchema = true,
    autoMigrations = [
        AutoMigration(from = 1, to = 2),
        AutoMigration(from = 2, to = 3, spec = AppDatabase.MyMigrationRenameUfVersion2To3::class)
    ]
)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        val MIGRATION_3_4 = object : Migration(3, 4) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE address_new (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, cep TEXT, logradouro TEXT, bairro TEXT, localidade TEXT, estado TEXT)")
                database.execSQL("INSERT INTO address_new (id, cep, logradouro, bairro, localidade, estado) SELECT id, cep, logradouro, bairro, localidade, estado FROM address")
                database.execSQL("DROP TABLE address")
                database.execSQL("ALTER TABLE address_new RENAME TO address")
            }
        }
    }

    abstract fun addressDao(): AddressDao

    @RenameColumn("address", "uf", "estado")
    class MyMigrationRenameUfVersion2To3 : AutoMigrationSpec

}