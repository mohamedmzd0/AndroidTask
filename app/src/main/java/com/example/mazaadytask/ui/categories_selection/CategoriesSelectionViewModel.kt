package com.example.mazaadytask.ui.categories_selection

import com.example.base.BaseViewModel
import com.example.data.entity.NetWorkState
import com.example.data.remote.categories.AllCategoriesResponse
import com.example.domain.use_case.CategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

@HiltViewModel
class CategoriesSelectionViewModel @Inject constructor(private val categoriesUseCase: CategoriesUseCase) :
    BaseViewModel() {

    /******All categories*****/
    private val _allCategoriesFlow = MutableSharedFlow<NetWorkState>()
    val allCategoriesFlow get() = _allCategoriesFlow.asSharedFlow()

    fun getAllCategories() {
        createCoroutinesThread(_allCategoriesFlow) {
            executeApi(_allCategoriesFlow, categoriesUseCase.getMainCat())
        }
    }

    /*******sub categories*******/

    private val _subCategoriesFlow = MutableSharedFlow<NetWorkState>()
    val subCategoriesFlow get() = _subCategoriesFlow.asSharedFlow()

    fun getSubCategories(id: Int) {
        createCoroutinesThread(_subCategoriesFlow) {
            executeApi(_subCategoriesFlow, categoriesUseCase.getSubCategories(id))
        }
    }

    /*******child options*******/

    private val _childOptionsFlow = MutableSharedFlow<NetWorkState>()
    val childOptionsFlow get() = _childOptionsFlow.asSharedFlow()

    fun getChildOptions(id: Int) {
        createCoroutinesThread(_childOptionsFlow) {
            executeApi(_childOptionsFlow, categoriesUseCase.getOptionsChild(id))
        }
    }


}