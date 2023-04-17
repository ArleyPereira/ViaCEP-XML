package com.example.viacep.presenter.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.viacep.R
import com.example.viacep.databinding.FragmentSearchAddressBinding
import com.example.viacep.domain.model.Address
import com.example.viacep.presenter.list.ListAddressViewModel
import com.example.viacep.util.Constants.ADDRESS_BUNDLE_KEY
import com.example.viacep.util.Constants.REQUEST_KEY
import com.example.viacep.util.StateView
import com.example.viacep.util.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchAddressFragment : Fragment() {

    private val viewModel: SearchAddressViewModel by viewModels()
    private val listAddressViewModel: ListAddressViewModel by activityViewModels()

    private var _binding: FragmentSearchAddressBinding? = null
    private val binding get() = _binding!!

    private var address: Address? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchAddressBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
    }

    private fun initListeners() {
        binding.editCep.addTextChangedListener {
            val text = it?.toString() ?: ""
            if (text.isNotEmpty()) {
                if (text.length == 8) {
                    hideKeyboard()
                    getAddress(cep = text)
                }
            }
        }

        binding.btnSalve.setOnClickListener {
            if (address != null) {
                insertAddress(address!!)
            } else {
                findNavController().popBackStack()
            }
        }
    }

    private fun getAddress(cep: String) {
        viewModel.getAddress(cep).observe(viewLifecycleOwner) { stateView ->
            when (stateView) {
                is StateView.Loading -> {
                    binding.btnSalve.isEnabled = false
                    binding.itemAddress.viewFlipper.displayedChild = 2
                }
                is StateView.Success -> {
                    if (stateView.data?.cep != null) {
                        address = stateView.data

                        binding.btnSalve.isEnabled = true
                        binding.itemAddress.viewFlipper.displayedChild = 1
                        binding.itemAddress.textAddress.text = stateView.data.getFullAddress()
                    } else {
                        binding.btnSalve.isEnabled = false
                        binding.itemAddress.viewFlipper.displayedChild = 0
                        binding.itemAddress.textEmptyAddress.text =
                            getString(R.string.label_address_empty_search_address_fragment)
                    }
                }
                is StateView.Error -> {
                    binding.btnSalve.isEnabled = false
                    binding.itemAddress.viewFlipper.displayedChild = 0
                    binding.itemAddress.textEmptyAddress.text =
                        getString(R.string.label_address_empty_search_address_fragment)
                }
            }
        }
    }

    private fun insertAddress(address: Address) {
        viewModel.insertAddress(address).observe(viewLifecycleOwner) { stateView ->
            when (stateView) {
                is StateView.Loading -> {

                }
                is StateView.Success -> {
                    listAddressViewModel.addressChanged()
                    findNavController().popBackStack()
                }
                is StateView.Error -> {
                    Toast.makeText(requireContext(), "Erro ao salvar endereço", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}