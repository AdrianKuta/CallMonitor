package dev.adriankuta.callmonitor.app.ui.activities

import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import dev.adriankuta.callmonitor.app.ui.adapters.CallLogAdapter
import dev.adriankuta.callmonitor.app.ui.base.BaseActivity
import dev.adriankuta.callmonitor.app.ui.viewmodels.MainViewModel
import dev.adriankuta.callmonitor.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setupView()
    }

    private fun setupView() {
        val adapter = CallLogAdapter()
        binding.callLogsRecyclerView.adapter = adapter
        binding.startService.setOnClickListener {
            viewModel.startServer()
        }
        binding.stopService.setOnClickListener {
            viewModel.stopServer()
        }
        viewModel.ipAddress.observe(this, binding.editTextTextPersonName::setText)
        viewModel.callLogs.observe(this, adapter::submitItems)
    }

    override fun onResume() {
        super.onResume()
        viewModel.refreshCallLogs()
    }

    override fun onPermissionsGranted() {
        /*val intent = Intent(this, ServerActivity::class.java)
        startActivity(intent)*/
    }
}