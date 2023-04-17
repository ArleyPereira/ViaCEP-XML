package com.example.viacep.domain.local.usecase

import com.example.viacep.data.local.entity.AddressEntity
import com.example.viacep.domain.local.repository.AddressLocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllAddressUseCase @Inject constructor(
    private val repository: AddressLocalRepository
) {

    operator fun invoke(): Flow<List<AddressEntity>> {
        return repository.getAllAddress()
    }

}