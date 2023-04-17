package com.example.viacep.domain.api.repository

import com.example.viacep.data.model.AddressResponse

interface AddressRepository {

    suspend fun getAddress(cep: String): AddressResponse

}