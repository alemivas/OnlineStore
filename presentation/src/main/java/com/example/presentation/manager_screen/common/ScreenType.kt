package com.example.presentation.manager_screen.common

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class ScreenType(val label: String, open val model: String) {
    @Parcelize
    class Create(override val model: String) : ScreenType("Create", model), Parcelable

    @Parcelize
    class Update(override val model: String) : ScreenType("Update", model), Parcelable

    @Parcelize
    class Delete(override val model: String) : ScreenType("Delete", model), Parcelable
}