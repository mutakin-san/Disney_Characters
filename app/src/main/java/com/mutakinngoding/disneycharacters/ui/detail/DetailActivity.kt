package com.mutakinngoding.disneycharacters.ui.detail

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.navigation.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.mutakinngoding.disneycharacters.R
import com.mutakinngoding.disneycharacters.core.domain.entity.Character
import com.mutakinngoding.disneycharacters.databinding.ActivityDetailBinding
import com.mutakinngoding.disneycharacters.core.ui.adapter.DetailAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val args: DetailActivityArgs by navArgs()
    private val detailViewModel: DetailViewModel by viewModels()

    private val rvTvShowsAdapter = DetailAdapter()
    private val rvFilmsAdapter = DetailAdapter()
    private val rvShortFilmsAdapter = DetailAdapter()
    private val rvPAttractionsAdapter = DetailAdapter()
    private val rvVideoGamesAdapter = DetailAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = args.character.name

        showDetailCharacter(args.character)
        initRecyclerView()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }



    private fun showDetailCharacter(character: Character?) {
        character?.let {
            Glide.with(this@DetailActivity)
                .load(it.imageUrl)
                .into(binding.ivDetailImage)

            var statusFavorite = character.isFavorite
            setStatusFavorite(statusFavorite)
            binding.fab.setOnClickListener {
                statusFavorite = !statusFavorite
                detailViewModel.setFavoriteCharacter(character, statusFavorite)
                setStatusFavorite(statusFavorite)
            }


            rvTvShowsAdapter.submitList(it.tvShows)
            rvFilmsAdapter.submitList(it.films)
            rvShortFilmsAdapter.submitList(it.shortFilms)
            rvPAttractionsAdapter.submitList(it.parkAttractions)
            rvVideoGamesAdapter.submitList(it.videoGames)

        }
    }

    private fun initRecyclerView() {
        with(binding) {
            rvTvShows.layoutManager = LinearLayoutManager(this@DetailActivity)
            rvFilms.layoutManager = LinearLayoutManager(this@DetailActivity)
            rvShortFilms.layoutManager = LinearLayoutManager(this@DetailActivity)
            rvPAttractions.layoutManager = LinearLayoutManager(this@DetailActivity)
            rvVideoGames.layoutManager = LinearLayoutManager(this@DetailActivity)

            rvTvShows.adapter = rvTvShowsAdapter
            rvFilms.adapter = rvFilmsAdapter
            rvShortFilms.adapter = rvShortFilmsAdapter
            rvPAttractions.adapter = rvPAttractionsAdapter
            rvVideoGames.adapter = rvVideoGamesAdapter

            rvTvShows.setHasFixedSize(true)
            rvFilms.setHasFixedSize(true)
            rvShortFilms.setHasFixedSize(true)
            rvPAttractions.setHasFixedSize(true)
            rvVideoGames.setHasFixedSize(true)
        }

    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_white))
        } else {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_not_favorite_white))
        }
    }
}