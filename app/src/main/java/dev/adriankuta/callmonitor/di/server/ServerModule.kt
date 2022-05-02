package dev.adriankuta.callmonitor.di.server

import com.sun.net.httpserver.HttpHandler
import com.sun.net.httpserver.HttpServer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ServiceComponent
import java.net.InetSocketAddress
import java.util.concurrent.Executor
import java.util.concurrent.Executors

@Module
@InstallIn(ServiceComponent::class)
class ServerModule {

    @Provides
    fun provideExecutor(): Executor =
        Executors.newCachedThreadPool()

    @Provides
    fun provideSocketAddress(): InetSocketAddress =
        InetSocketAddress(5000)

    @Provides
    fun provideHttpServer(
        socketAddress: InetSocketAddress,
        executor: Executor,
        endpointHandlers: Map<String, @JvmSuppressWildcards HttpHandler>
    ): HttpServer {
        return HttpServer.create(socketAddress, 0).apply {
            this.executor = executor
            endpointHandlers.forEach { (key, value) -> createContext(key, value) }
        }
    }
}