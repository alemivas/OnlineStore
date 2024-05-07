package com.example.presentation.manager_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Category
import com.example.domain.models.CategoryRequest
import com.example.domain.models.ProductRequest
import com.example.domain.usecases.api_use_cases.CreateNewCategoryUseCase
import com.example.domain.usecases.api_use_cases.CreateNewProductUseCase
import com.example.domain.usecases.api_use_cases.DeleteCategoryUseCase
import com.example.domain.usecases.api_use_cases.DeleteProductUseCase
import com.example.domain.usecases.api_use_cases.GetCategoriesUseCase
import com.example.domain.usecases.api_use_cases.UpdateCategoryUseCase
import com.example.domain.usecases.api_use_cases.UpdateProductUseCase
import com.example.utils.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ManagerViewModel @Inject constructor(
    private val createNewProductUseCase: CreateNewProductUseCase,
    private val updateProductUseCase: UpdateProductUseCase,
    private val deleteProductUseCase: DeleteProductUseCase,
    private val createNewCategoryUseCase: CreateNewCategoryUseCase,
    private val updateCategoryUseCase: UpdateCategoryUseCase,
    private val deleteCategoryUseCase: DeleteCategoryUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
) : ViewModel() {

    private val _categories = MutableStateFlow<ApiResult<List<Category>>>(ApiResult.Loading())
    val categories = _categories.asStateFlow()

    init {
        fetchCategories()
    }

    private fun fetchCategories() {
        viewModelScope.launch {
            getCategoriesUseCase()
                .flowOn(Dispatchers.IO)
                .catch {
                    _categories.value = ApiResult.Error(it.message ?: "Something went wrong")
                }
                .collect{
                    _categories.value = it
                }
        }
    }


    fun createNewProduct(request: ProductRequest) {
        viewModelScope.launch {
            createNewProductUseCase(request)
        }
    }

    fun updateProduct(id: Int, request: ProductRequest) {
        viewModelScope.launch {
            updateProductUseCase(id, request)
        }
    }

    fun deleteProduct(id: Int) {
        viewModelScope.launch {
            deleteProductUseCase(id)
        }
    }

    fun createNewCategory(request: CategoryRequest) {
        viewModelScope.launch {
            createNewCategoryUseCase(request)
        }
    }

    fun updateCategory(id: Int, request: CategoryRequest) {
        viewModelScope.launch {
            updateCategoryUseCase(id, request)
        }
    }

    fun deleteCategory(id: Int) {
        viewModelScope.launch {
            deleteCategoryUseCase(id)
        }
    }
}