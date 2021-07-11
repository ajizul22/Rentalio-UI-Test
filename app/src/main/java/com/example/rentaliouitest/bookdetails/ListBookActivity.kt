package com.example.rentaliouitest.bookdetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rentaliouitest.R
import com.example.rentaliouitest.databinding.ActivityListBookBinding

class ListBookActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListBookBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvDriverService.text = intent.getStringExtra("driver")
        binding.tvTransmission.text = intent.getStringExtra("trans")
        binding.tvStartDate.text = intent.getStringExtra("start")
        binding.tvEndDate.text = intent.getStringExtra("end")
        binding.tvTotalDays.text = intent.getStringExtra("totalDays")
        binding.tvTotalPrice.text = intent.getStringExtra("totalPrice")


    }
}