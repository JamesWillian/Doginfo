package com.jammes.doginfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.jammes.doginfo.core.repository.DogApiService
import com.jammes.doginfo.core.repository.DogRepository
import com.jammes.doginfo.databinding.FragmentDogSearchBinding
import kotlinx.coroutines.launch

class DogSearchFragment: Fragment() {

    private var _binding: FragmentDogSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: DogListAdapter
    private val viewModel: DogViewModel by activityViewModels {
        val dogRepository = DogRepository()
        DogViewModel.Factory(dogRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = DogListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDogSearchBinding.inflate(inflater, container, false)

        return (binding.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.DogsList.layoutManager = LinearLayoutManager(requireContext())
        binding.DogsList.adapter = adapter

        viewModel.uiState.observe(viewLifecycleOwner) {

        }

        viewModel.loadDogs(binding.DogSearchEditText.editText?.text.toString())
    }
}