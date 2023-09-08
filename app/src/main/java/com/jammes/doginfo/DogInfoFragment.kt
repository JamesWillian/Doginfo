package com.jammes.doginfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.jammes.doginfo.databinding.FragmentDogInfoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DogInfoFragment: Fragment() {

    private var _binding: FragmentDogInfoBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: DogViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[DogViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDogInfoBinding.inflate(inflater, container, false)

        return (binding.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: DogInfoFragmentArgs by navArgs()
        viewModel.currentDogName = args.dogName

    }

    override fun onResume() {
        super.onResume()
        viewModel.getCurrentDog()?.let { updateUI(it) }
    }

    private fun updateUI(dog: Dog) {
        Glide
            .with(this)
            .load(dog.image_link)
            .centerCrop()
            .into(binding.dogImageView)

        binding.apply {
            dogBreedTextView.text = dog.name

            energyRatingBar.rating = dog.energy.toFloat()
            energyNumberTextView.text = dog.energy.toString()

            trainabilityRatingBar.rating = dog.trainability.toFloat()
            trainabilityNumberTextView.text = dog.trainability.toString()

            protectivenessRatingBar.rating = dog.protectiveness.toFloat()
            protectivenessNumberTextView.text = dog.protectiveness.toString()

            playfulnessRatingBar.rating = dog.playfulness.toFloat()
            playfulnessNumberTextView.text = dog.playfulness.toString()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}