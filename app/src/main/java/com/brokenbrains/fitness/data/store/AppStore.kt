package com.brokenbrains.fitness.data.store

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppStore @Inject constructor(
    @ApplicationContext private val context: Context
) {
    // TODO probably won't use
}