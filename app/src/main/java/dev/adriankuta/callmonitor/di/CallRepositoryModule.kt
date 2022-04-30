package dev.adriankuta.callmonitor.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dev.adriankuta.callmonitor.repositories.CallRepository
import dev.adriankuta.callmonitor.repositories.CallRepositoryImpl

@Module
@InstallIn(ActivityComponent::class)
abstract class CallRepositoryModule {

    @Binds
    abstract fun bindCallRepository(
        callRepositoryImpl: CallRepositoryImpl
    ): CallRepository
}