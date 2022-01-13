package com.mcuhq.projetfinaltest.ui.home

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mcuhq.projetfinaltest.databinding.ActivityHomeBinding
import com.mcuhq.projetfinaltest.ui.api.ActionActivity
import com.mcuhq.projetfinaltest.ui.data.LocalPreferences
import com.mcuhq.projetfinaltest.ui.scan.ScanActivity

class HomeActivity : AppCompatActivity() {

   private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.scanndevice.setOnClickListener {
            startActivity(ScanActivity.getStartIntent(this))
        }

        binding.buttoninternet.setOnClickListener {
            if (LocalPreferences.getInstance(this).lastConnectedDeviceName() == null) {
                Toast.makeText(this, "Connection à un device en BLE", Toast.LENGTH_SHORT).show()
            } else {
                startActivity(ActionActivity.getStartIntent(this, LocalPreferences.getInstance(this).lastConnectedDeviceName()))
            }
        }
    }
}
