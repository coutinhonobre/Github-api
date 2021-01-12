package com.github.coutinhonobre.githubapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.github.coutinhonobre.githubapi.presentation.RepositoryViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<RepositoryViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.loadingState.observe(this, Observer {
            Toast.makeText(applicationContext, it.status.toString(), Toast.LENGTH_LONG).show()
        })

    }
}