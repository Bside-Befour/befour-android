package org.positive.daymotion.presentation.category

import android.os.Bundle
import android.view.View
import android.widget.Toast
import dagger.hilt.android.AndroidEntryPoint
import org.positive.daymotion.R
import org.positive.daymotion.databinding.FragmentCategoryTabBinding
import org.positive.daymotion.presentation.base.BaseFragment
import org.positive.daymotion.presentation.base.util.viewModelOf
import org.positive.daymotion.presentation.category.adapter.CategoryTabPagerAdapter
import org.positive.daymotion.presentation.root.model.RootTabFragment

@AndroidEntryPoint
class CategoryTabFragment :
    BaseFragment<FragmentCategoryTabBinding>(R.layout.fragment_category_tab), RootTabFragment {

    private val viewModel by viewModelOf<CategoryTabViewModel>()
    private val pagerAdapter by lazy { CategoryTabPagerAdapter(this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        setupViews()
    }

    private fun setupViews() {
        binding.viewPager.adapter = pagerAdapter
    }

    override fun scrollToTop() {
        // TODO(yh): replace to scroll logic
        Toast.makeText(requireContext(), "Category", Toast.LENGTH_SHORT).show()
    }
}