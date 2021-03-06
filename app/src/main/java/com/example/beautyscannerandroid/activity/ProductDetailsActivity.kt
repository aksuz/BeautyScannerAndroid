package com.example.beautyscannerandroid.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.beautyscannerandroid.R
import com.example.beautyscannerandroid.helper.Constants
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
        val sharedPreferences: SharedPreferences? =
            this.getSharedPreferences(Constants.SHARED_INFO, Context.MODE_PRIVATE)
        val userId: Long = sharedPreferences!!.getLong(Constants.USER_ID, 0L)
        if (userId == 0L) {
            addProductToFavButton.visibility = View.GONE
        }


        intent.extras?.let {
            if (it.containsKey(Constants.PRODUCT_ID)) {
                viewModel.getProductDetails(intent.getLongExtra(Constants.PRODUCT_ID, 0))
            }
            if (it.containsKey(Constants.BARCODE)) {
                intent.getStringExtra(Constants.BARCODE)
                    ?.let { it1 -> viewModel.getProductDetails(it1) }
            }
        }

        setupObservers()
        productIngredients.movementMethod = LinkMovementMethod.getInstance();


        addProductToFavButton.setOnClickListener {
            val intent = Intent(this, AddProductToFavouritesActivity::class.java)
            intent.putExtra(Constants.PRODUCT_ID, viewModel.productDetails.value?.id)
            startActivity(intent)
        }
    }

    private fun setupObservers() {
        viewModel.productDetails.observe(
            this,
            Observer { product ->
                mapProductToLayout(product)
            })
        viewModel.shouldHideLoader.observe(
            this,
            Observer {
                categoryProductListLoader.visibility = View.GONE
            }
        )
    }

    private fun mapProductToLayout(product: Product) {
        productName.text = product.name
        productProducer.text = product.producer.name
        productCategory.text = product.category.name
        productDescription.text = product.description
        productIngredients.text = Html.fromHtml(getIngredients(product), Html.FROM_HTML_MODE_LEGACY)
        analyseIngredients(product)
        productRating.text = getRating(product)
        addImage(product)
    }

    private fun addImage(product: Product) {
        var picture: String = Constants.URL_NO_PICTURE
        if (!product.url.isNullOrEmpty()) {
            picture = product.url
        }
        Glide
            .with(this)
            .load(picture)
            .centerCrop()
            .into(productImage);
    }

    private fun analyseIngredients(product: Product) {
        var isAllergen: Int = 0
        var isIrritant: Int = 0
        var isComedogenic: Int = 0
        var isCarcinogenic: Int = 0
        var isForbiddenDuringPregnancy: Int = 0

        for (ingredient in product.ingredients!!) {
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
        val hyperlink: String = ""

        return if (product.ingredients.isNullOrEmpty()) {
            getString(R.string.missing_info)
        } else {
            product.ingredients.joinToString(", ") {
                if (!it.url.isNullOrEmpty()) {
                    "<a href=\"" + it.url + "\">" + it.name + "</a>"
                } else {
                    it.name
                }
            }
        }
    }


    private fun getRating(product: Product?): String? {
        return if (product?.noRatingVotes == 0.0) {
            "0.0"
        } else {
            val rating: Double? = product?.sumRainingVotes?.div(product.noRatingVotes)
            rating.toString()
        }
    }


}