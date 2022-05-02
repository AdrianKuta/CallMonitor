package dev.adriankuta.callmonitor.di.server

import com.sun.net.httpserver.HttpHandler
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ServiceComponent
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey
import dev.adriankuta.callmonitor.server.endpointHandlers.LogHandler
import dev.adriankuta.callmonitor.server.endpointHandlers.RootHandler
import dev.adriankuta.callmonitor.server.response.ResponseHandler
import dev.adriankuta.callmonitor.server.response.ResponseHandlerImpl

@Module
@InstallIn(ServiceComponent::class)
abstract class EndpointModule {

    @Binds
    abstract fun provideResponseHandler(responseHandlerImpl: ResponseHandlerImpl): ResponseHandler

    @Binds
    @IntoMap
    @StringKey("/")
    abstract fun provideRootHandler(rootHandler: RootHandler): HttpHandler

    @Binds
    @IntoMap
    @StringKey("/index")
    abstract fun provideIndexHandler(rootHandler: RootHandler): HttpHandler

    @Binds
    @IntoMap
    @StringKey("/log")
    abstract fun provideLogHandler(logHandler: LogHandler): HttpHandler
}