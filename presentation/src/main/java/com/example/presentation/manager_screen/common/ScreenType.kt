package com.example.presentation.manager_screen.common

sealed class ScreenType(val label: String, val model: String) {
    class Create(model: String) : ScreenType("Create", model)
    class Update(model: String) : ScreenType("Update", model)
    class Delete(model: String) : ScreenType("Delete", model)
}