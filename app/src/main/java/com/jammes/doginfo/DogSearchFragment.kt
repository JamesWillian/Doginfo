package com.jammes.doginfo

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.divider.MaterialDividerItemDecoration
import com.jammes.doginfo.core.model.DogDomain
import com.jammes.doginfo.databinding.FragmentDogSearchBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DogSearchFragment : Fragment() {

    private var _binding: FragmentDogSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: DogListAdapter
    private lateinit var viewModel: DogViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[DogViewModel::class.java]
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

        addingDividerDecoration()

        viewModel.stateOnceAndStream().observe(viewLifecycleOwner) { uiState ->
            when (uiState) {
                is DogViewModel.UiState.Loading -> {

                    binding.contentLoadingProgressBar.show()
                }
                is DogViewModel.UiState.Success -> {

                    updateUi(uiState.dogs)
                }
                is DogViewModel.UiState.Error -> {

                    updateUi(emptyList())
                }
            }
        }

        binding.button.setOnClickListener {
            if (binding.DogSearchEditText.editText?.text.toString().isBlank()) {
                Toast.makeText(requireContext(), R.string.enter_the_dog_breed, Toast.LENGTH_SHORT)
                    .show()
            } else {
                hideKeyboard()
                viewModel.getDogs(binding.DogSearchEditText.editText?.text.toString())
            }
        }

    }

    private fun updateUi(dogsList: List<DogDomain>) {
        if (dogsList.isEmpty()) {
            binding.placeholderImageView.visibility = View.VISIBLE
            binding.placeholderTextView.visibility = View.VISIBLE
        } else {
            binding.placeholderImageView.visibility = View.GONE
            binding.placeholderTextView.visibility = View.GONE
        }

        binding.contentLoadingProgressBar.hide()
        adapter.updateDogsList(dogsList)

    }

    private fun addingDividerDecoration() {
        val divider = MaterialDividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        val resources = requireContext().resources

        divider.isLastItemDecorated = true
        divider.dividerThickness = resources.getDimensionPixelSize(R.dimen.vertical_margin)
        divider.dividerColor = ContextCompat.getColor(
            requireContext(),
            com.google.android.material.R.color.mtrl_btn_transparent_bg_color
        )

        binding.DogsList.addItemDecoration(divider)
    }

    private fun hideKeyboard() {
        val view: View = this.requireView()

        (context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?)?.hideSoftInputFromWindow(
            view.windowToken,
            0
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}