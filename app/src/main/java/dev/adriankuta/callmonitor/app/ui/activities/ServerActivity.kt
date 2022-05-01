package dev.adriankuta.callmonitor.app.ui.activities

import android.os.Bundle
import com.sun.net.httpserver.HttpServer
import dagger.hilt.android.AndroidEntryPoint
import dev.adriankuta.callmonitor.app.ui.base.BaseActivity
import dev.adriankuta.callmonitor.databinding.ActivityServerBinding
import javax.inject.Inject

@AndroidEntryPoint
class ServerActivity : BaseActivity() {

    @Inject
    lateinit var httpServer: HttpServer
    private var isServerRunning = false
    private lateinit var binding: ActivityServerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityServerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
    }

    private fun setupView() {
        binding.serverToggle.setOnClickListener {
            if (isServerRunning) stopServer() else startServer(5000)
        }
    }

    override fun onPermissionsGranted() = Unit

    private fun startServer(port: Int) {
        try {
            httpServer.start()
            isServerRunning = true
            binding.serverStatus.text = "192.168.0.142:$port"
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun stopServer() {
        httpServer.stop(0)
        binding.serverStatus.text = "Server off"

    }
}