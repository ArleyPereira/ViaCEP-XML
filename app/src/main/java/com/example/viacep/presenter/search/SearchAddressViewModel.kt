package com.example.viacep.presenter.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.viacep.domain.local.usecase.InsertAddressUseCase
import com.example.viacep.domain.model.Address
import com.example.viacep.domain.usecase.GetAddressUseCase
import com.example.viacep.util.StateView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class SearchAddressViewModel @Inject constructor(
    private val getAddressUseCase: GetAddressUseCase,
    private val insertAddressUseCase: InsertAddressUseCase
) : ViewModel() {

    fun getAddress(cep: String) = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())

            val address = getAddressUseCase(cep)

            emit(StateView.Success(address))
        } catch (e: HttpException) {
            e.printStackTrace()
            emit(StateView.Error(message = e.message))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(StateView.Error(message = e.message))
        }
    }

    fun insertAddress(address: Address) = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())

            val id = insertAddressUseCase(address)

            emit(StateView.Success(id))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(StateView.Error(message = e.message))
        }
    }

}