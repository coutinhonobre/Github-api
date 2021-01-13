package com.github.coutinhonobre.githubapi.presentation

import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.coutinhonobre.githubapi.R
import com.github.coutinhonobre.githubapi.model.Item
import com.github.coutinhonobre.githubapi.presentation.adapter.RepositoryAdapter
import com.github.coutinhonobre.githubapi.presentation.viewmodel.RepositoryViewModel
import com.github.coutinhonobre.githubapi.util.LoadingState
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private val viewModel by viewModel<RepositoryViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.data.observe(this, Observer {
            listRepositories(it)
        })

        viewModel.loadingState.observe(this, Observer {
            // Observe the loading state
            loadAndStateUI(it)
        })

        swipeRefreshMain.setOnRefreshListener {
            viewModel.restart()
        }

    }

    private fun loadAndStateUI(it: LoadingState) {
        when (it.status) {
            LoadingState.Status.RUNNING -> {
                viewFlipperMain.displayedChild = 0
                swipeRefreshMain.isRefreshing = false
            }
            LoadingState.Status.SUCCESS -> {
                viewFlipperMain.displayedChild = 1
                swipeRefreshMain.isRefreshing = false
            }
            LoadingState.Status.FAILED -> {
                viewFlipperMain.displayedChild = 2
                textViewMainError.text = it.msg
                swipeRefreshMain.isRefreshing = false
            }
        }
    }


    private fun MainActivity.listRepositories(it: Item) {
        with(recyclerView) {
            layoutManager =
                LinearLayoutManager(this@MainActivity)
            adapter = RepositoryAdapter(it.repositories){ repository->
                val intent = repository.let {
                    DetailActivity.getStartIntent(
                        this@MainActivity,
                        repository = repository
                    )
                }
                this@MainActivity.startActivity(intent)
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu_main, menu)

        val searchItem = menu?.findItem(R.id.searchMenuSearch)

        val searchView = searchItem!!.actionView as SearchView
        searchView.queryHint = getString(R.string.search)
        searchView.setOnQueryTextListener(this);
        searchView.isIconified = false

        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(search: String?): Boolean {
        viewModel.fetchRepositorySearchName(query = search.toString())
        return true
    }
}