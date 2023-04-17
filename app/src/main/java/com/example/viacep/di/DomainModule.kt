package com.example.viacep.di

import com.example.viacep.data.local.repository.AddressLocalRepositoryImpl
import com.example.viacep.data.repository.AddressRepositoryImpl
import com.example.viacep.domain.api.repository.AddressRepository
import com.example.viacep.domain.local.repository.AddressLocalRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DomainModule {

    @Binds
    abstract fun bindsAddressRepositoryImpl(
        addressRepositoryImpl: AddressRepositoryImpl
    ): AddressRepository

    @Binds
    abstract fun bindsAddressLocalRepositoryImpl(
        addressLocalRepositoryImpl: AddressLocalRepositoryImpl
    ): AddressLocalRepository

}