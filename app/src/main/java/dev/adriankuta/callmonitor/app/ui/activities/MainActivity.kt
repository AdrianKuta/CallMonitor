package dev.adriankuta.callmonitor.app.ui.activities

import android.content.Intent
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import dev.adriankuta.callmonitor.app.tools.CallLogManager
import dev.adriankuta.callmonitor.app.ui.base.BaseActivity
import dev.adriankuta.callmonitor.databinding.ActivityMainBinding
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    @Inject
    lateinit var callLogManager: CallLogManager
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setupView()
    }

    private fun setupView() {

    }

    override fun onPermissionsGranted() {
        val intent = Intent(this, ServerActivity::class.java)
        startActivity(intent)
    }
}