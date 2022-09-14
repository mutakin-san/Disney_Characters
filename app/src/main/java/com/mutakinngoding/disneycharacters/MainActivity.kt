package com.mutakinngoding.disneycharacters

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.mutakinngoding.disneycharacters.core.data.Resource
import com.mutakinngoding.disneycharacters.core.presentation.MainViewModel
import com.mutakinngoding.disneycharacters.core.presentation.ViewModelFactory
import com.mutakinngoding.disneycharacters.databinding.ActivityMainBinding
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: ViewModelFactory
    private val viewModel: MainViewModel by viewModels {
        factory
    }


    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        (application as DisneyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        viewModel.listCharacter.observe(this) { response ->
            when (response) {
                is Resource.Loading -> Log.d("Fetch Data", "Loading...")
                is Resource.Error -> Log.e("Fetch Data", "Error : ${response.message}")
                is Resource.Success -> {
                    Log.i("Fetch Data", response.data.toString())
                    binding.lvCharacters.adapter = ArrayAdapter(
                        this,
                        android.R.layout.simple_list_item_1,
                        response.data.orEmpty().map {
                            it.name
                        },
                    )
                }
            }
        }


    }
}