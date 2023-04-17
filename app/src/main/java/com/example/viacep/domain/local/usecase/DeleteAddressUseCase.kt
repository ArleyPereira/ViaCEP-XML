package com.example.viacep.domain.local.usecase

import com.example.viacep.data.mapper.toEntity
import com.example.viacep.domain.local.repository.AddressLocalRepository
import com.example.viacep.domain.model.Address
import javax.inject.Inject

class DeleteAddressUseCase @Inject constructor(
    private val repository: AddressLocalRepository
) {

    suspend operator fun invoke(address: Address) {
        return repository.deleteAddress(address.toEntity())
    }

}