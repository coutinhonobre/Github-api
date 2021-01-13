package com.github.coutinhonobre.githubapi.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.github.coutinhonobre.githubapi.R
import com.github.coutinhonobre.githubapi.model.Repository

class DetailActivity : AppCompatActivity() {
    private lateinit var repository: Repository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        repository = intent.getParcelableExtra(REPOSITORY)!!

        Toast.makeText(applicationContext, repository.fullName, Toast.LENGTH_LONG).show()
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