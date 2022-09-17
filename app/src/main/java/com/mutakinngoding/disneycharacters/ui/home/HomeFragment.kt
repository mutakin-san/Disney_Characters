package com.mutakinngoding.disneycharacters.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.mutakinngoding.disneycharacters.core.data.Resource
import com.mutakinngoding.disneycharacters.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel : HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CharactersAdapter()
        binding.characterList.adapter = adapter
        binding.characterList.layoutManager = GridLayoutManager(requireActivity(), 2)

        homeViewModel.listCharacter.observe(viewLifecycleOwner) {
            when(it) {
                is Resource.Error -> {
                   showLoading(false)
                    showError(true)
                }
                is Resource.Loading -> {
                    showLoading(true)
                    showError(false)
                }
                is Resource.Success -> {
                    adapter.submitList(it.data)
                    showLoading(false)
                    showError(false)
                }
            }
        }
    }

    private fun showError(isVisible: Boolean) {
        binding.tvErrorMessage.isVisible = isVisible
        binding.characterList.isVisible = !isVisible
    }

    private fun showLoading(isVisible: Boolean) {
        binding.loadingList.isVisible = isVisible
        binding.characterList.isVisible = !isVisible
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}