package com.johnhigginsmavila.coderswag.Controller

import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.johnhigginsmavila.coderswag.Adapters.ProductsAdapter
import com.johnhigginsmavila.coderswag.R
import com.johnhigginsmavila.coderswag.Services.DataService
import com.johnhigginsmavila.coderswag.Utilities.EXTRA_CATEGORY
import kotlinx.android.synthetic.main.activity_products.*

class ProductsActivity : AppCompatActivity() {

    lateinit var adapter: ProductsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        val categoryType = intent.getStringExtra(EXTRA_CATEGORY)
        println(categoryType)

        adapter = ProductsAdapter(this, DataService.getProducts(categoryType))
        productListView.adapter = adapter
        val orientation = resources.configuration.orientation
        var spanCount = 2
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            spanCount = 3
        }

        val screenSize = resources.configuration.screenWidthDp
        if (screenSize > 720) {
            spanCount = 3
        }
        val layoutManager = GridLayoutManager(this, spanCount)

        productListView.layoutManager = layoutManager
        headerTxt.text = categoryType
    }
}
