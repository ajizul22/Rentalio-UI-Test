package com.example.rentaliouitest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rentaliouitest.databinding.ActivityMainBinding
import com.synnapps.carouselview.CarouselView
import com.synnapps.carouselview.ImageListener
import android.widget.Toast
import com.example.rentaliouitest.bookdetails.BookActivity

class MainActivity : AppCompatActivity(), CategoryAdapter.OnCategoryClickListener {

    private lateinit var binding: ActivityMainBinding

    var sampleImage = intArrayOf(
        R.drawable.banner_1,
        R.drawable.banner_2
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var sampleImage = intArrayOf(
            R.drawable.banner_1,
            R.drawable.banner_2
        )

        val carouselView = binding.bannerSlide as CarouselView
        carouselView.pageCount = sampleImage.size
        carouselView.setImageListener(imageListener)
        carouselView.radius

        setCategory()
    }

        var imageListener: ImageListener = object : ImageListener {
        override fun setImageForPosition(position: Int, imageView: ImageView?) {
            imageView?.setImageResource(sampleImage[position])
        }
    }

    private fun setCategory(){
        binding.rvCategory.apply {
            val rvAdapter = CategoryAdapter(this@MainActivity)
            val gridLayout = GridLayoutManager(this@MainActivity, 4, LinearLayoutManager.VERTICAL, false)
            layoutManager = gridLayout
            adapter = rvAdapter
        }
    }

    override fun onCategoryClicked(position: Int) {
        if (position == 0) {
            val intent = Intent(this, BookActivity::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show()
        }
    }
}