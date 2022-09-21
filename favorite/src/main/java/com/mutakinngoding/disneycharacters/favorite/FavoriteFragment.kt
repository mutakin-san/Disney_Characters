package com.mutakinngoding.disneycharacters.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.mutakinngoding.disneycharacters.core.ui.adapter.CharactersAdapter
import com.mutakinngoding.disneycharacters.favorite.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment() {

    private val favoriteViewModel: FavoriteViewModel by viewModels()

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val charactersAdapter = CharactersAdapter().apply {
                onItemClickListener = {
                    val action = FavoriteFragmentDirections.actionNavigationFavoriteToDetailActivity(it)
                    view.findNavController().navigate(action)
                }
            }

            favoriteViewModel.favoriteCharacters.observe(viewLifecycleOwner) { character ->
                charactersAdapter.submitList(character)
                binding.viewEmpty.root.visibility =
                    if (character.isNotEmpty()) View.GONE else View.VISIBLE
            }

            with(binding.rvFavCharacters) {
                layoutManager = GridLayoutManager(context, 2)
                setHasFixedSize(true)
                adapter = charactersAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
