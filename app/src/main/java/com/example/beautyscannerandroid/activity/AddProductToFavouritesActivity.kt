package com.example.beautyscannerandroid.activity

import android.R
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.beautyscannerandroid.helper.Constants
import com.example.beautyscannerandroid.model.MyProduct
import com.example.beautyscannerandroid.model.Product
import com.example.beautyscannerandroid.viewmodel.AddProductToFavouritesViewModel
import kotlinx.android.synthetic.main.activity_add_product_to_favourites.*
import java.time.LocalDate


class AddProductToFavouritesActivity : AppCompatActivity() {
    private val viewModel: AddProductToFavouritesViewModel by lazy {
        ViewModelProvider(this).get(AddProductToFavouritesViewModel::class.java)
    }

    var expMonths: Int = 0
    var oDay: Int = 0
    var oMonth: Int = 0
    var oYear: Int = 0
    var expDate: LocalDate = LocalDate.now()
    var oDate: LocalDate = LocalDate.now()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.beautyscannerandroid.R.layout.activity_add_product_to_favourites)
        val sharedPreferences: SharedPreferences? =
            this.getSharedPreferences(Constants.SHARED_INFO, Context.MODE_PRIVATE)
        val userId: Long = sharedPreferences!!.getLong(Constants.USER_ID, 0L)

        viewModel.getProductDetails(intent.getLongExtra(Constants.PRODUCT_ID, 0L))
        setupObservers()

        adapterExpirationMonths()
        adapterOpeningDay()
        adapterOpeningMonth()
        adapterOpeningYear()


        saveButton.setOnClickListener {
            oDate = LocalDate.of(oYear, oMonth, oDay)
            expDate = oDate.plusMonths(expMonths.toLong())

            viewModel.addUserProduct(userId, createMyProduct(oDate, expDate))

            val intent = Intent(this, UserActivity::class.java)
            startActivity(intent)

        }


    }

    private fun createMyProduct(oDate: LocalDate?, expDate: LocalDate?): MyProduct {
        return MyProduct(
            productId = viewModel.productDetails.value!!,
            openingDate = oDate.toString(),
            expirationTime = expDate.toString(),
            id = null,
            productRating = null
        )
    }

    private fun setupObservers() {
        viewModel.productDetails.observe(
            this,
            Observer { product ->
                mapProductToLayout(product)
            }
        )
    }

    private fun adapterOpeningYear() {
        val adapterOpeningYear =
            ArrayAdapter(this, R.layout.simple_spinner_item, (2015..2030).toList().toTypedArray())
        adapterOpeningYear.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        openingYear.adapter = adapterOpeningYear

        openingYear.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    // either one will work as well
                    //                     val item = parent.getItemAtPosition(position) as String
                    oYear = adapterOpeningYear.getItem(position)!!
                }
            }
    }

    private fun adapterOpeningMonth() {
        val adapterOpeningMonth =
            ArrayAdapter(this, R.layout.simple_spinner_item, (1..12).toList().toTypedArray())
        adapterOpeningMonth.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        openingMonth.adapter = adapterOpeningMonth

        openingMonth.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    // either one will work as well
                    //                     val item = parent.getItemAtPosition(position) as String
                    oMonth = adapterOpeningMonth.getItem(position)!!
                }
            }
    }

    private fun adapterOpeningDay() {
        val adapterOpeningDay =
            ArrayAdapter(this, R.layout.simple_spinner_item, (1..31).toList().toTypedArray())
        adapterOpeningDay.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        openingDay.adapter = adapterOpeningDay

        openingDay.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    // either one will work as well
                    //                     val item = parent.getItemAtPosition(position) as String
                    oDay = adapterOpeningDay.getItem(position)!!
                }
            }
    }

    private fun adapterExpirationMonths() {
        val adapterExpirationMonths =
            ArrayAdapter(this, R.layout.simple_spinner_item, listOf(1, 3, 6, 12, 18, 24, 36))
        adapterExpirationMonths.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        userProductExpirationMonths.adapter = adapterExpirationMonths

        userProductExpirationMonths.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    // either one will work as well
                    //                     val item = parent.getItemAtPosition(position) as String
                    expMonths = adapterExpirationMonths.getItem(position)!!
                }
            }
    }

    private fun mapProductToLayout(product: Product) {
        productName.text = product.name
        productProducer.text = product.producer.name
        productCategory.text = product.category.name
        addImage(product)
    }

    private fun addImage(product: Product) {
        var picture: String = "https://www.saccon.it/img/coming-soon.jpg"
        if (!product.url.isNullOrEmpty()) {
            picture = product.url
        }
        Glide
            .with(this)
            .load(picture)
            .centerCrop()
            .into(productImage);
    }
}