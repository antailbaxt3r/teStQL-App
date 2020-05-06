package com.antailbaxt3r.gettestsapplication.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.antailbaxt3r.gettestsapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.devCredits.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/antailbaxt3r"))
            startActivity(browserIntent)
        }
        binding.viewTestsCard.setOnClickListener{
            val view = Intent(this@MainActivity, ViewTestsActivity::class.java)
            startActivity(view)
        }
        binding.addTestCard.setOnClickListener{
            val add = Intent(this@MainActivity, AddTestActivity::class.java)
            startActivity(add)
        }
    }
}