package com.example.rentaliouitest.bookdetails

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.rentaliouitest.R
import com.example.rentaliouitest.databinding.ActivityBookBinding
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import com.synnapps.carouselview.CarouselView
import com.synnapps.carouselview.ImageListener
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList

class BookActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookBinding

    private var imageProduct = intArrayOf(
            R.drawable.product_1,
            R.drawable.product_2
    )

    var driver = "With Driver"
    var transmission = "AT"
    var startDate: String? = null
    var endDate:String? = null
    var totalDays:String? = null
    var totalPrice: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

        val carouselView = binding.bannerSlide as CarouselView
        carouselView.pageCount = imageProduct.size
        carouselView.setImageListener(imageListener)

        binding.btnWithoutDriver.setOnClickListener {
            binding.btnMt.isVisible = true

        }

        binding.btnWithDriver.setOnClickListener {
            binding.btnMt.isVisible = false
            binding.btnAt.isChecked = true
        }

        binding.checkbox.setOnClickListener {
            binding.btnBookNow.isVisible = true
            binding.checkbox.isChecked = true
        }

        binding.toDate.setOnClickListener {
            showDatePicker()
        }

        binding.fromDate.setOnClickListener {
            showDatePicker()
        }

        binding.btnBookNow.setOnClickListener {
            val btnRadioDriver = binding.radioGroup2.checkedRadioButtonId
            getDriver(btnRadioDriver)

            val btnRadioTransmission = binding.radioGroup.checkedRadioButtonId
            getTransmission(btnRadioTransmission)

            if (startDate != null && endDate != null && totalDays != null && totalPrice != null) {

                val intent = Intent(this, ListBookActivity::class.java)
                intent.putExtra("driver", driver)
                intent.putExtra("trans", transmission)
                intent.putExtra("start", startDate)
                intent.putExtra("end", endDate)
                intent.putExtra("totalDays", "$totalDays Days")
                intent.putExtra("totalPrice", totalPrice)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Please Fill the Page", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun showDatePicker() {
        val today = MaterialDatePicker.todayInUtcMilliseconds()
        val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))

        calendar.timeInMillis = today
        calendar[Calendar.MONTH] = Calendar.JULY
        val janThisYear = calendar.timeInMillis

        calendar.timeInMillis = today
        calendar[Calendar.MONTH] = Calendar.DECEMBER
        val decThisYear = calendar.timeInMillis

        // show bulan saat ini dan bulan berikutnya
        val constraintsBuilder =
                CalendarConstraints.Builder()
                        .setStart(janThisYear)
                        .setEnd(decThisYear)

        val dateRangePicker = MaterialDatePicker.Builder
                .dateRangePicker()
                .setTitleText("Select Date")
                .setCalendarConstraints(constraintsBuilder.build())
                .build()


        dateRangePicker.show(
                supportFragmentManager,
                "date_range_picker"
        )

        dateRangePicker.addOnPositiveButtonClickListener { datePicked ->

            val startDate = datePicked.first
            val endDate = datePicked.second

            // menghitung jumlah hari
            val msDiff = endDate - startDate
            val daysDiff = TimeUnit.MILLISECONDS.toDays(msDiff)
            val selectedDays = daysDiff.toInt()

            if (startDate != null && endDate != null) {
                binding.fromDate.text = convertLongDate(startDate)
                binding.toDate.text = convertLongDate(endDate)
                Log.d("datexxx", selectedDays.toString())
                binding.estimate.text = "RP. 350.000 / 24 HOUR\n X $selectedDays DAYS"

                val total = priceEstimate(selectedDays).toDouble()
                binding.total.text = convertRupiah(total)

                this.startDate = convertLongDate(startDate)
                this.endDate = convertLongDate(endDate)
                this.totalDays = selectedDays.toString()
                this.totalPrice = convertRupiah(total)
            }
        }
    }

    fun convertRupiah(number: Double): String{
        val localeID =  Locale("in", "ID")
        val numberFormat = NumberFormat.getCurrencyInstance(localeID)
        return numberFormat.format(number).toString()
    }

    private fun priceEstimate(day: Int): Int {
        return day * 350000
    }

    private fun convertLongDate(time:Long): String {
        val date = Date(time)
        val format = SimpleDateFormat(
                "EEE dd, MMM yyyy",
                Locale.getDefault()
        )

        return format.format(date)
    }

    private fun getDriver(id: Int) {
        when(id) {
            R.id.btn_with_driver -> driver = "With Driver"
            R.id.btn_without_driver -> driver = "Without Driver"
        }
    }

    private fun getTransmission(id: Int){
        when(id) {
            R.id.btn_at -> transmission = "AT"
            R.id.btn_mt -> transmission = "MT"
        }
    }

    var imageListener: ImageListener = object : ImageListener {
        override fun setImageForPosition(position: Int, imageView: ImageView?) {
            imageView?.setImageResource(imageProduct[position])
            imageView?.scaleType = ImageView.ScaleType.FIT_XY
        }
    }

}