package com.irfanirawansukirman.codebasemodularity.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.irfanirawansukirman.abstraction.util.ext.setOnSingleClickListener
import com.irfanirawansukirman.codebasemodularity.databinding.ItemMainBinding
import com.irfanirawansukirman.data.network.model.MoviesResult

class MainAdapter(private val selectedMovies: (MoviesResult, Int) -> Unit) :
    RecyclerView.Adapter<MainAdapter.ItemHolder>() {

    private val moviesList = arrayListOf<MoviesResult>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ItemHolder(
        ItemMainBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun getItemCount(): Int = moviesList.size

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        moviesList[position].let {
            holder.bindItem(it, position)
        }
    }

    inner class ItemHolder(private val itemMainBinding: ItemMainBinding) :
        RecyclerView.ViewHolder(itemMainBinding.root) {
        fun bindItem(item: MoviesResult, position: Int) {
            itemMainBinding.apply {
                txtTitle.text = item.originalTitle
                root.setOnSingleClickListener {
                    selectedMovies(item, position)
                }
            }
        }
    }

    fun setupMoviesList(data: List<MoviesResult>) {
        moviesList.apply {
            if (isNotEmpty()) clear()
            addAll(data)
        }
        notifyDataSetChanged()
    }

    fun resetMoviesList() {
        moviesList.clear()
        notifyDataSetChanged()
    }
}
