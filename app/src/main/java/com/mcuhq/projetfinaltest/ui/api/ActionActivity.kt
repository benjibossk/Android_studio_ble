package com.mcuhq.projetfinaltest.ui.api

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mcuhq.projetfinaltest.R
import com.mcuhq.projetfinaltest.databinding.ActivityActionBinding
import com.mcuhq.projetfinaltest.ui.led.LedStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ActionActivity : AppCompatActivity() {

    private lateinit var ledStatus: LedStatus
    private lateinit var binding: ActivityActionBinding

    companion object {
        const val PI_IDENTIFIER = "PI_IDENTIFIER"
        fun getStartIntent(context: Context, piIdentifier: String?): Intent {
            return Intent(context, ActionActivity::class.java).apply {
                putExtra(PI_IDENTIFIER, piIdentifier)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityActionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recharge.setOnClickListener {
            getStatus()
        }


        binding.toggle2.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                runCatching {
                    ledStatus = ApiService.instance.writeStatus(ledStatus.reverseStatus())
                    setVisualState()
                }
            }
        }
    }


    // Récupération de l'état depuis le serveur
    private fun getStatus() {
        CoroutineScope(Dispatchers.IO).launch {
            runCatching {
                ledStatus = ApiService.instance.readStatus(getIdentifier())
                setVisualState()
            }
        }
    }

    override fun onResume(){
        super.onResume()
        getStatus()
    }

    private fun setVisualState(){
        if ( ledStatus.status){
            binding.ledStatus2.setImageResource(R.drawable.led_on)
        } else {
            binding.ledStatus2.setImageResource(R.drawable.led_off)
        }
    }

    private fun getIdentifier():String{
        return intent.getStringExtra(PI_IDENTIFIER)!!
    }

}
