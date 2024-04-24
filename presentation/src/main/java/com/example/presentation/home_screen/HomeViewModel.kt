package com.example.presentation.home_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Category
import com.example.domain.usecases.GetCategoriesUseCase
import com.example.utils.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val defaultDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _categories= MutableStateFlow<ApiResult<List<Category>>>(ApiResult.Loading())
    val categories= _categories.asStateFlow()

    init {
        fetchQuotes()
    }

    private fun fetchQuotes(){
        viewModelScope.launch {
            getCategoriesUseCase()
                .flowOn(defaultDispatcher)
                .catch {
                    _categories.value = ApiResult.Error(it.message ?: "Something went wrong")
                }
                .collect{
                    _categories.value = it
                }
        }
    }
}