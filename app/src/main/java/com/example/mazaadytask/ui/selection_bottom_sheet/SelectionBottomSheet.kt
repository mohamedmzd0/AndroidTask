package com.example.mazaadytask.ui.selection_bottom_sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.base.BaseBottomSheet
import com.example.data.entity.PropertiesModel
import com.example.data.remote.categories.Category
import com.example.data.utils.Constants
import com.example.mazaadytask.R
import com.example.mazaadytask.databinding.FragmentSelectionBottomSheetBinding
import com.example.utils.fromJson

private const val TAG = "SelectionBottomSheet"

class SelectionBottomSheet : BaseBottomSheet() {

    companion object {
        val CATEGORY_RESULT = "category-selection"
    }

    private var propertiesModel: PropertiesModel? = null

    private var _binding: FragmentSelectionBottomSheetBinding? = null
    private val binding get() = _binding!!
    private val categoriesAdapter by lazy { CategoriesAdapter() }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_selection_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSelectionBottomSheetBinding.bind(view)
        setActions()
        checkArguments()
        setupRecycler()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setActions() {
        categoriesAdapter.onItemSelected = ::onItemSelected
        binding.ivClose.setOnClickListener { findNavController().navigateUp() }
        binding.edtSearch.doOnTextChanged { text, start, before, count ->
            filterResults(text)
        }
    }

    private fun onItemSelected(category: Category) {
        if (arguments?.containsKey(Constants.INTENT.MAIN_CATEGORY) == true)
            setFragmentResult(CATEGORY_RESULT, bundleOf(Constants.INTENT.MAIN_CATEGORY to category))
        else if (arguments?.containsKey(Constants.INTENT.SUB_CATEGORY) == true)
            setFragmentResult(CATEGORY_RESULT, bundleOf(Constants.INTENT.SUB_CATEGORY to category))
        else if (arguments?.containsKey(Constants.INTENT.PROPERTY_OPTION) == true) {
            setFragmentResult(
                CATEGORY_RESULT,
                bundleOf(Constants.INTENT.PROPERTY_OPTION to category)
            )
        }
        findNavController().navigateUp()
    }

    private fun filterResults(text: CharSequence?) {
        categoriesAdapter.filter.filter(text)
    }

    private fun setupRecycler() =
        binding.recyclerViewItems.apply {
            setHasFixedSize(true)
            adapter = categoriesAdapter
            addItemDecoration(DividerItemDecoration(_context, DividerItemDecoration.VERTICAL))
        }


    private fun checkArguments() {
        if (arguments == null) return
        if (requireArguments().containsKey(Constants.INTENT.TITLE)) {
            binding.tvTitle.text = requireArguments().getString(Constants.INTENT.TITLE)
        }
        if (requireArguments().containsKey(Constants.INTENT.MAIN_CATEGORY)) {
            val arrayList =
                requireArguments().getParcelableArrayList<Category>(Constants.INTENT.MAIN_CATEGORY)
            categoriesAdapter.setAll(arrayList)
            return
        }
        if (requireArguments().containsKey(Constants.INTENT.SUB_CATEGORY)) {
            val arrayList =
                requireArguments().getParcelableArrayList<Category>(Constants.INTENT.SUB_CATEGORY)
            categoriesAdapter.setAll(arrayList)
            return
        }
        if (requireArguments().containsKey(Constants.INTENT.PROPERTY_OPTION)) {
            propertiesModel = requireArguments().getString(Constants.INTENT.PROPERTY_OPTION)
                ?.fromJson<PropertiesModel>()
            categoriesAdapter.setAll(propertiesModel?.options)
            return
        }
    }
}