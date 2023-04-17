package com.example.viacep.presenter.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.viacep.data.mapper.toDomain
import com.example.viacep.domain.local.usecase.GetAllAddressUseCase
import com.example.viacep.domain.model.Address
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import okhttp3.internal.toImmutableList
import javax.inject.Inject

@HiltViewModel
class ListAddressViewModel @Inject constructor(
    private val getAllAddressUseCase: GetAllAddressUseCase
) : ViewModel() {

    private val _addressList = MutableLiveData(mutableListOf<Address>())
    val addressList: LiveData<MutableList<Address>> = _addressList

    private val _addressChanged = MutableLiveData(Unit)
    val addressChanged: LiveData<Unit> = _addressChanged

    init {
        getAllAddress()
    }

    fun getAllAddress() = viewModelScope.launch {
        getAllAddressUseCase.invoke().collect { addresses ->
            _addressList.value = addresses.map { it.toDomain() }.toMutableList()
        }
    }

    fun addressChanged() {
        _addressChanged.value = Unit
    }

}
















