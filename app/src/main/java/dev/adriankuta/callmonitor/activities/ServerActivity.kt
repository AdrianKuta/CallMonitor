package dev.adriankuta.callmonitor.activities

import android.os.Bundle
import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler
import com.sun.net.httpserver.HttpServer
import dev.adriankuta.callmonitor.databinding.ActivityServerBinding
import org.json.JSONObject
import java.io.InputStream
import java.net.InetSocketAddress
import java.util.*
import java.util.concurrent.Executors

class ServerActivity : BaseActivity() {

    private var httpServer: HttpServer? = null
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

    private fun streamToString(inputStream: InputStream): String {
        val s = Scanner(inputStream).useDelimiter("\\A")
        return if (s.hasNext()) s.toString() else ""
    }

    private fun startServer(port: Int) {
        try {
            val inetSocketAddress = InetSocketAddress(port)
            httpServer = HttpServer.create(inetSocketAddress, 0)
            httpServer?.executor = Executors.newCachedThreadPool()
            httpServer?.createContext("/", rootHandler)
            httpServer?.createContext("/index", rootHandler)
            httpServer?.createContext("/message", messageHandler)
            httpServer?.start()
            isServerRunning = true
            binding.serverStatus.text = "192.168.0.142:$port"
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun stopServer() {
        httpServer?.stop(0)
        binding.serverStatus.text = "Server off"

    }

    private fun sendResponse(httpExchange: HttpExchange, responseText: String) {
        httpExchange.sendResponseHeaders(200, responseText.length.toLong())
        val os = httpExchange.responseBody
        os.write(responseText.toByteArray())
        os.close()
    }

    private val rootHandler = HttpHandler { exchange ->
        run {
            when (exchange.requestMethod) {
                "GET" -> {
                    sendResponse(exchange, "Welcome to my server!")
                }
            }
        }
    }

    private val messageHandler = HttpHandler { exchange ->
        run {
            when (exchange.requestMethod) {
                "GET" -> {
                    sendResponse(exchange, "Would be all messages stringified json")
                }
                "POST" -> {
                    val inputStream = exchange.requestBody

                    val requestBody = streamToString(inputStream)
                    val jsonBody = JSONObject(requestBody)
                    // save message to database

                    //for testing
                    sendResponse(exchange, jsonBody.toString())
                }
            }
        }
    }
}