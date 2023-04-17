package com.example.viacep.data.mapper

import com.example.viacep.data.local.entity.AddressEntity
import com.example.viacep.data.model.AddressResponse
import com.example.viacep.domain.model.Address

fun AddressResponse.toDomain(): Address {
    return Address(
        cep = cep,
        logradouro = logradouro,
        complemento = complemento,
        bairro = bairro,
        localidade = localidade,
        uf = uf,
        ddd = ddd
    )
}

fun Address.toEntity(): AddressEntity {
    return AddressEntity(
        id = id,
        cep = cep,
        logradouro = logradouro,
        bairro = bairro,
        localidade = localidade,
        uf = uf
    )
}

fun AddressEntity.toDomain(): Address {
    return Address(
        id = id,
        cep = cep,
        logradouro = logradouro,
        bairro = bairro,
        localidade = localidade,
        uf = uf,
        ddd = null,
        complemento = null
    )
}