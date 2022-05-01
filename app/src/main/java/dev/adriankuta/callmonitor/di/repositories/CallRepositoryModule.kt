package dev.adriankuta.callmonitor.di.repositories

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dev.adriankuta.callmonitor.repositories.calls.CallRepository
import dev.adriankuta.callmonitor.repositories.calls.CallRepositoryImpl

@Module
@InstallIn(ActivityComponent::class)
abstract class CallRepositoryModule {

    @Binds
    abstract fun bindCallRepository(
        callRepositoryImpl: CallRepositoryImpl
    ): CallRepository
}