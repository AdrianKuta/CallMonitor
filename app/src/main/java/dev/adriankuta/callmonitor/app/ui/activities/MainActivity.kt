package dev.adriankuta.callmonitor.app.ui.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.activity.viewModels
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import dev.adriankuta.callmonitor.R
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
            askPermissions()
        }
        binding.stopService.setOnClickListener {
            viewModel.stopServer()
        }
        viewModel.ipAddress.observe(this, binding.editTextTextPersonName::setText)
        viewModel.callLogs.observe(this, adapter::submitItems)
    }

    override fun onPermissionsGranted() {
        super.onPermissionsGranted()
        viewModel.startServer()
    }

    override fun onPermissionsDenied() {
        super.onPermissionsDenied()
        showPermissionsSnackbar()
    }

    override fun onResume() {
        super.onResume()
        viewModel.refreshCallLogs()
    }

    private fun showPermissionsSnackbar() {
        Snackbar.make(binding.root, R.string.snackbar_no_permissions, Snackbar.LENGTH_LONG)
            .setAction(R.string.settings) { openAppSettings() }
            .show()
    }

    private fun openAppSettings() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri: Uri = Uri.fromParts("package", packageName, null)
        intent.data = uri
        startActivity(intent)
    }
}