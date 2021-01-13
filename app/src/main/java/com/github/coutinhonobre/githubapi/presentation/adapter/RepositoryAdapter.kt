package com.github.coutinhonobre.githubapi.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.coutinhonobre.githubapi.R
import com.github.coutinhonobre.githubapi.model.Repository
import kotlinx.android.synthetic.main.item_repository.view.*

class RepositoryAdapter(
    var repositories: List<Repository>,
    private val onItemClickListener: ((repository: Repository) -> Unit)
) : RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>() {
    class RepositoryViewHolder(
        view: View,
        private val onItemClickListener: ((repository: Repository) -> Unit)
    ) : RecyclerView.ViewHolder(view) {
        fun bindView(repository: Repository) {
            itemView.item_textView_nameRepository.text = repository.name
            itemView.item_textView_username.text = repository.owner?.login
            itemView.item_textView_forks.text = repository.forkCount.toString()

            itemView.setOnClickListener { onItemClickListener.invoke(repository) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = RepositoryViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_repository, parent, false),
        onItemClickListener
    )

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bindView(repository = repositories[position])
    }

    override fun getItemCount() = repositories.size
}