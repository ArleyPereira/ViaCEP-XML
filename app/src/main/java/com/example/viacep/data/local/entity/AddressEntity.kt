package com.example.viacep.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "address")
data class AddressEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long =  0,

    @ColumnInfo("cep")
    val cep: String?,

    @ColumnInfo("logradouro")
    val logradouro: String?,

    @ColumnInfo("bairro")
    val bairro: String?,

    @ColumnInfo("localidade")
    val localidade: String?,

    @ColumnInfo("estado")
    val uf: String?,
)
