package org.positive.daymotion.presentation.category.activity

import android.content.Context
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import org.positive.daymotion.R
import org.positive.daymotion.common.bundle
import org.positive.daymotion.databinding.ActivityCategoryDetailBinding
import org.positive.daymotion.extension.startWith
import org.positive.daymotion.presentation.base.BaseActivity
import org.positive.daymotion.presentation.base.util.viewModelOf
import org.positive.daymotion.presentation.category.viewmodel.CategoryDetailViewModel
import org.positive.daymotion.presentation.common.adapter.FeedThumbnailAdapter

@AndroidEntryPoint
class CategoryDetailActivity :
    BaseActivity<ActivityCategoryDetailBinding>(R.layout.activity_category_detail) {

    private val viewModel by viewModelOf<CategoryDetailViewModel>()
    private val feedThumbnailAdapter by lazy { FeedThumbnailAdapter() }
    private val handler by lazy { Handler() }

    private val title by bundle<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.title = title
        binding.handler = handler
        binding.viewModel = viewModel

        setupViews()
        setupObservers()

        viewModel.loadCategorizedFeed()
    }

    private fun setupViews() {
        binding.categoryDetailRecyclerView.apply {
            adapter = feedThumbnailAdapter
            layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
        }
    }

    private fun setupObservers() {
        viewModel.categorizedFeedThumbnails.observeNonNull {
            feedThumbnailAdapter.replaceAll(it)
        }
    }

    inner class Handler {
        fun finish() = this@CategoryDetailActivity.finish()
    }

    companion object {
        fun start(
            context: Context,
            title: String
        ) = context.startWith<CategoryDetailActivity>("title" to title)
    }
}