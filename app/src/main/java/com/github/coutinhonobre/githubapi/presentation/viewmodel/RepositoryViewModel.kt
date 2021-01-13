package com.github.coutinhonobre.githubapi.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.coutinhonobre.githubapi.model.Item
import com.github.coutinhonobre.githubapi.repository.ItemRepository
import com.github.coutinhonobre.githubapi.util.LoadingState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryViewModel(private val repository: ItemRepository): ViewModel() {

    private val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState>
        get() = _loadingState

    private val _data = MutableLiveData<Item>()
    val data: LiveData<Item>
        get() = _data

    init {
        fetchRepository()
    }

    fun restart() {
        fetchRepository()
    }

    fun fetchRepositorySearchName(query: String = DEFAULT_QUERY) {
        fetchRepository(query)
    }

    private fun fetchRepository(query: String = DEFAULT_QUERY) {
        _loadingState.postValue(LoadingState.LOADING)
        repository.getRepositories(query).enqueue(object : Callback<Item>{
            override fun onResponse(call: Call<Item>, response: Response<Item>) {
                if (response.isSuccessful){
                    _data.postValue(response.body())
                    _loadingState.postValue(LoadingState.LOADED)
                }else
                    _loadingState.postValue(LoadingState.error(response.errorBody().toString()))
            }

            override fun onFailure(call: Call<Item>, t: Throwable) {
                _loadingState.postValue(LoadingState.error(t.message))
            }

        })
    }

    companion object {
        const val DEFAULT_QUERY = "Android";
    }

}