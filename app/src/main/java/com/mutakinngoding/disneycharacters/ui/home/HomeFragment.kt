package com.mutakinngoding.disneycharacters.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.mutakinngoding.disneycharacters.DisneyApplication
import com.mutakinngoding.disneycharacters.core.data.Resource
import com.mutakinngoding.disneycharacters.ui.ViewModelFactory
import com.mutakinngoding.disneycharacters.databinding.FragmentHomeBinding
import com.mutakinngoding.disneycharacters.core.ui.adapter.CharactersAdapter
import javax.inject.Inject

class HomeFragment : Fragment() {
    @Inject
    lateinit var factory: ViewModelFactory

    private val homeViewModel: HomeViewModel by viewModels {
        factory
    }
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    override fun onAttach(context: Context) {
        (requireActivity().application as DisneyApplication).appComponent.inject(this)
        super.onAttach(context)
    }
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

        if (activity != null) {

            val charactersAdapter = CharactersAdapter().apply {
                onItemClickListener = {
                    val action = HomeFragmentDirections.actionNavigationHomeToDetailActivity(it)
                    view.findNavController().navigate(action)
                }
            }

            with(binding.characterList) {
                adapter = charactersAdapter
                layoutManager = GridLayoutManager(requireContext(), 2)
            }

            homeViewModel.listCharacter.observe(viewLifecycleOwner) { response ->
                when (response) {
                    is Resource.Success -> {
                        charactersAdapter.submitList(response.data)
                        showLoading(false)
                        showError(false)
                    }
                    is Resource.Error -> {
                        binding.tvErrorMessage.text = response.message
                        showError(true)
                    }
                    is Resource.Loading -> {
                        showLoading(true)
                    }
                }
            }
        }


    }

    private fun showError(isVisible: Boolean) {
        binding.tvErrorMessage.isVisible = isVisible
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