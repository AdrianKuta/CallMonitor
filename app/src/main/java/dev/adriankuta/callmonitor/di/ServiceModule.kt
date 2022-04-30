package dev.adriankuta.callmonitor.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dev.adriankuta.callmonitor.services.CallService
import dev.adriankuta.callmonitor.services.CallServiceImpl

@Module
@InstallIn(ActivityComponent::class)
abstract class ServiceModule {

    @Binds
    abstract fun binCallService(
        analyticsCallServiceImpl: CallServiceImpl
    ): CallService
}