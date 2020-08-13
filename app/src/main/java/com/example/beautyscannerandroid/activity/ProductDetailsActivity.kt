package com.example.beautyscannerandroid.activity

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.beautyscannerandroid.R
import com.example.beautyscannerandroid.model.Product
import com.example.beautyscannerandroid.viewmodel.ProductDetailsListViewModel
import kotlinx.android.synthetic.main.activity_category_product_list.*
import kotlinx.android.synthetic.main.activity_product_details.*


class ProductDetailsActivity : AppCompatActivity() {
    private val viewModel: ProductDetailsListViewModel by lazy {
        ViewModelProvider(this).get(ProductDetailsListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)
        supportActionBar?.title = getString(R.string.product_details)
        viewModel.getProductDetails(intent.getLongExtra("productId", 0))
        setupObservers()
    }


    fun setupObservers() {
        viewModel.productDetails.observe(
            this,
            Observer { product ->
//                categoryProductListLoader.visibility = View.GONE
                mapProductToLayout(product)
            })
        viewModel.shouldHideLoader.observe(
            this,
            Observer {
                categoryProductListLoader.visibility = View.GONE
                //todo info dla usera że nie zwracamy listy
                //todo if connection fail inform categotyListLoader
            }
        )
    }

    private fun mapProductToLayout(product: Product) {
        productCategory.text = product.category?.name
        productDescription.text = product.description
        productIngredients.text = getIngredients(product)
        analyseIngredients(product)
        productRating.text = getRating(product)
        addImage(product)
    }

    private fun addImage(product: Product) {
        val imagePath: String
        val urlPath: String
        if (!product.url.isNullOrEmpty()) {
            urlPath = product.url
            //todo display from internet
        } else if (!product.picture.isNullOrEmpty()) {
            imagePath = product.picture
            val bmImg = BitmapFactory.decodeFile(imagePath)
            productImage.setImageBitmap(bmImg)

        }
    }

    private fun analyseIngredients(product: Product) {
        var isAllergen: Int = 0
        var isIrritant: Int = 0
        var isComedogenic: Int = 0
        var isCarcinogenic: Int = 0
        var isForbiddenDuringPregnancy: Int = 0

        for (ingredient in product?.ingredients) {
            if (ingredient.allergen) isAllergen++
            if (ingredient.irritant) isIrritant++
            if (ingredient.comedogenic) isComedogenic++
            if (ingredient.carcinogenic) isCarcinogenic++
            if (ingredient.forbiddenDuringPregnancy) isForbiddenDuringPregnancy++
        }

        productIsAllergen.text = isAllergen.toString()
        productIsIrritant.text = isIrritant.toString()
        productIsComodogenic.text = isComedogenic.toString()
        productIsCarcinogenic.text = isCarcinogenic.toString()
        productIsPregnantForbiden.text = isForbiddenDuringPregnancy.toString()
    }

    private fun getIngredients(product: Product): String {
        return if (product.ingredients.isNullOrEmpty()) {
            getString(R.string.missing_info)
        } else {
            product.ingredients?.joinToString(", ") {
                it.name
            }
        }
    }

    private fun getRating(product: Product?): String? {
        if (product?.noRatingVotes == 0.0) {
            return "0.0"
        } else {
            val raiting: Double? = product?.sumRainingVotes?.div(product?.noRatingVotes)
            return raiting.toString()
        }
    }


}