package com.github.coutinhonobre.githubapi.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import coil.api.load
import coil.transform.CircleCropTransformation
import com.github.coutinhonobre.githubapi.R
import com.github.coutinhonobre.githubapi.model.Repository
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    private lateinit var repository: Repository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        repository = intent.getParcelableExtra(REPOSITORY)!!

        setView()
    }

    private fun setView() {
        detail_imageView.load(repository.owner?.avatarUrl){
            crossfade(true)
            placeholder(R.drawable.ic_launcher_background)
            transformations(CircleCropTransformation())
        }
        detail_textView_username.text = repository.owner?.login
        detail_textView_repository.text = repository.fullName
        detail_textView_description.text = repository.description
    }

    companion object {
        private const val REPOSITORY = "REPOSITORY"

        fun getStartIntent(context: Context, repository: Repository): Intent {
            return Intent(context, DetailActivity::class.java).apply {
                putExtra(REPOSITORY, repository)
            }
        }
    }
}