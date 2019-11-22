package com.example.sharbat.presentation.app

import android.app.Application
import com.example.sharbat.presentation.di.CustomProvider
import com.example.sharbat.presentation.di.CustomProviderImpl

class App : Application() {

    private lateinit var provider: CustomProvider

    override fun onCreate() {
        super.onCreate()

        provider = CustomProviderImpl(this)
    }

    fun getProvider() = provider
}