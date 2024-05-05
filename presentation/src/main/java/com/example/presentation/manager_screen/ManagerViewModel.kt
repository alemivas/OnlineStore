package com.example.presentation.manager_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.CategoryRequest
import com.example.domain.models.ProductRequest
import com.example.domain.usecases.api_use_cases.CreateNewCategoryUseCase
import com.example.domain.usecases.api_use_cases.CreateNewProductUseCase
import com.example.domain.usecases.api_use_cases.DeleteCategoryUseCase
import com.example.domain.usecases.api_use_cases.DeleteProductUseCase
import com.example.domain.usecases.api_use_cases.UpdateCategoryUseCase
import com.example.domain.usecases.api_use_cases.UpdateProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
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
) : ViewModel() {

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