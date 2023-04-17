package com.example.viacep.domain.local.usecase

import com.example.viacep.data.local.entity.AddressEntity
import com.example.viacep.domain.local.repository.AddressLocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAddressByIdUseCase @Inject constructor(
    private val repository: AddressLocalRepository
) {

    suspend operator fun invoke(id: Long): AddressEntity? {
        return repository.getAddressById(id)
    }

}