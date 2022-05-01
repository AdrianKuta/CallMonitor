package dev.adriankuta.callmonitor.di.repositories

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dev.adriankuta.callmonitor.app.tools.CallLogManager
import dev.adriankuta.callmonitor.app.tools.CallLogManagerImpl
import dev.adriankuta.callmonitor.repositories.calls.CallRepository
import dev.adriankuta.callmonitor.repositories.calls.CallRepositoryImpl

@Module
@InstallIn(ActivityComponent::class)
abstract class CallRepositoryModule {

    @Binds
    abstract fun bindCallLogManager(callLogManagerImpl: CallLogManagerImpl): CallLogManager

    @Binds
    abstract fun bindCallRepository(
        callRepositoryImpl: CallRepositoryImpl
    ): CallRepository
}