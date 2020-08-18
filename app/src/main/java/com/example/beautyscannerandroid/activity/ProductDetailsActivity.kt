package com.example.beautyscannerandroid.activity

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
                //todo info dla usera że nie zwracamy listy
                //todo if connection fail inform categotyListLoader
            }
        )
    }

    private fun mapProductToLayout(product: Product) {
        productName.text = product.name
        productCategory.text = product.category.name
        productDescription.text = product.description
        productIngredients.text = Html.fromHtml(getIngredients(product), Html.FROM_HTML_MODE_LEGACY)
        analyseIngredients(product)
        productRating.text = getRating(product)
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