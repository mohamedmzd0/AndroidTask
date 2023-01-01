package com.example.mazaadytask.ui.categories_selection

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.base.BaseFragment
import com.example.data.entity.PropertiesModel
import com.example.data.remote.categories.AllCategoriesResponse
import com.example.data.remote.categories.Category
import com.example.data.utils.Constants
import com.example.mazaadytask.R
import com.example.mazaadytask.databinding.FragmentCategorySelectionBinding
import com.example.mazaadytask.ui.selection_bottom_sheet.SelectionBottomSheet
import com.example.utils.fromJson
import com.example.utils.toJson
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "CategoriesSelectionFrag"

@AndroidEntryPoint
class CategoriesSelectionFragment : BaseFragment(R.layout.fragment_category_selection) {

    private var _binding: FragmentCategorySelectionBinding? = null
    private val binding get() = _binding!!

    private val optionsAdapter by lazy { SubCatOptionsAdapter() }
    private val categoriesSelectionViewModel by viewModels<CategoriesSelectionViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCategorySelectionBinding.bind(view)

        sendRequestMainCat()
        handleObserver()
        resetActions()
        observeSelectionResult()
        setupRecyclerView()
        setupActions()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupActions(){
        binding.btnNext.setOnClickListener {
            /***no need to validate ***/
        findNavController().navigate(R.id.navigation_auctionsFragment)
        }
    }
    private fun setupRecyclerView() {
        binding.recyclerViewItems.setHasFixedSize(true)
        binding.recyclerViewItems.adapter = optionsAdapter
    }

    private fun resetActions() {
        optionsAdapter.onItemClicked = ::onOptionSelected
        with(binding) {
            edtMainCategory.setOnClickListener(null)
            edtSubCategory.setOnClickListener(null)
        }
    }

    private fun onOptionSelected(data: PropertiesModel) =
        findNavController().navigate(
            R.id.selectionBottomSheet, bundleOf(
                Constants.INTENT.TITLE to data.name,
                Constants.INTENT.PROPERTY_OPTION to data.toJson()
            )
        )

    private fun sendRequestMainCat() {
        categoriesSelectionViewModel.getAllCategories()
    }

    private fun sendRequestOptions(id: Int) {
        categoriesSelectionViewModel.getChildOptions(id)
    }

    private fun sendRequestSubCat(id: Int) {
        categoriesSelectionViewModel.getSubCategories(id)
        optionsAdapter.setAll(arrayListOf())
    }

    private fun handleObserver() {
        handleSharedFlow(categoriesSelectionViewModel.allCategoriesFlow, onSuccess = {
            if (it is AllCategoriesResponse)
                handleMainCategories(it.categories)
        })

        handleSharedFlow(categoriesSelectionViewModel.subCategoriesFlow, onSuccess = {
            if (it is ArrayList<*>)
                handleSubOptionsCategories(it as ArrayList<PropertiesModel>?)
        })

        handleSharedFlow(categoriesSelectionViewModel.childOptionsFlow, onSuccess = {
            Log.e(TAG, "handleObserver: $it")
        })
    }

    private fun handleSubOptionsCategories(propertiesModels: java.util.ArrayList<PropertiesModel>?) {
        optionsAdapter.setAll(propertiesModels)
    }


    private fun handleMainCategories(categories: ArrayList<Category>?) {
        categories?.firstOrNull()?.let { setSelectedMainCat(it,true) }
        binding.edtMainCategory.setOnClickListener { selectMainCat(categories) }
    }

    private fun setSelectedMainCat(cat: Category,isSubCatEmptyByDefault: Boolean=false) {
        binding.edtMainCategory.setText(cat.name)
        handleSubCategories(cat.children,isSubCatEmptyByDefault)
    }

    private fun selectMainCat(categories: java.util.ArrayList<Category>?) =
        findNavController().navigate(
            R.id.selectionBottomSheet, bundleOf(
                Constants.INTENT.TITLE to getString(R.string.main_category),
                Constants.INTENT.MAIN_CATEGORY to categories
            )
        )


    private fun handleSubCategories(
        categories: ArrayList<Category>?,
        isSubCatEmptyByDefault: Boolean
    ) {
        if (!isSubCatEmptyByDefault)
        categories?.firstOrNull()?.let { setSelectedSubCat(it) }
        binding.edtSubCategory.setOnClickListener { selectSubCategories(categories) }
    }

    private fun setSelectedSubCat(cat: Category) {
        binding.edtSubCategory.setText(cat.name)
        sendRequestSubCat(cat.id!!)
    }

    private fun selectSubCategories(categories: java.util.ArrayList<Category>?) =
        findNavController().navigate(
            R.id.selectionBottomSheet, bundleOf(
                Constants.INTENT.TITLE to getString(R.string.sub_category),
                Constants.INTENT.SUB_CATEGORY to categories
            )
        )


    private fun observeSelectionResult() {
        setFragmentResultListener(SelectionBottomSheet.CATEGORY_RESULT) { s: String, bundle: Bundle ->
            if (bundle.containsKey(Constants.INTENT.MAIN_CATEGORY)) {
                setSelectedMainCat(bundle.getParcelable<Category>(Constants.INTENT.MAIN_CATEGORY)!!)
                return@setFragmentResultListener
            }

            if (bundle.containsKey(Constants.INTENT.SUB_CATEGORY)) {
                setSelectedSubCat(bundle.getParcelable<Category>(Constants.INTENT.SUB_CATEGORY)!!)
                return@setFragmentResultListener
            }

            if (bundle.containsKey(Constants.INTENT.PROPERTY_OPTION)) {
                val selectedOption =
                    bundle.getParcelable<Category>(Constants.INTENT.SELECTED_PROPERTY_OPTION)
                val propertiesModel =
                    bundle.getString(Constants.INTENT.PROPERTY_OPTION)?.fromJson<PropertiesModel>()
                optionsAdapter.setSelectedValue(propertiesModel!!,selectedOption)
                return@setFragmentResultListener
            }
        }
    }
}