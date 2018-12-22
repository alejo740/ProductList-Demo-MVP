package com.edwin.cobos.productlistdemo.home.view

import android.os.Build
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.edwin.cobos.productlistdemo.R
import com.edwin.cobos.productlistdemo.api.models.Content
import com.edwin.cobos.productlistdemo.api.models.Product
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_product.view.*

class ProductListAdapter(val clickListener: (String) -> Unit) :
    RecyclerView.Adapter<ProductListAdapter.ProductViewHolder>() {

    var items: ArrayList<Product> = ArrayList()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ProductListAdapter.ProductViewHolder {
        val layout = LayoutInflater.from(p0.context).inflate(R.layout.item_product, p0, false)
        return ProductViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(p0: ProductListAdapter.ProductViewHolder, p1: Int) {
        p0.bind(items[p1], clickListener)
    }

    fun addProduct(product: Product) {
        items.add(product)
        notifyItemInserted(items.size - 1)
    }

    fun setProducts(products: List<Product>) {
        items.clear()
        items.addAll(products)
        notifyDataSetChanged()
    }

    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val image = view.background_image
        private val topDescription = view.description
        private val title = view.title
        private val promoMessage = view.promo_message
        private val bottomDescription = view.bottom_description
        private val containerButtons = view.container_buttons

        fun bind(product: Product, clickListener: (String) -> Unit) {
            bindImage(product.backgroundImage)
            if (product.topDescription.isNullOrBlank())
                topDescription.visibility = View.GONE
            else {
                topDescription.visibility = View.VISIBLE
                topDescription.text = product.topDescription
            }

            if (product.title.isNullOrBlank())
                title.visibility = View.GONE
            else {
                title.visibility = View.VISIBLE
                title.text = product.title
            }

            if (product.promoMessage.isNullOrBlank())
                promoMessage.visibility = View.GONE
            else {
                promoMessage.visibility = View.VISIBLE
                promoMessage.text = product.promoMessage
            }

            if (product.bottomDescription.isNullOrBlank())
                bottomDescription.visibility = View.GONE
            else {
                bottomDescription.visibility = View.VISIBLE
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    bottomDescription.text = Html.fromHtml(product.bottomDescription, Html.FROM_HTML_MODE_COMPACT)
                } else {
                    bottomDescription.text = Html.fromHtml(product.bottomDescription)
                }
            }

            loadButtons(product.content, clickListener)

        }

        private fun loadButtons(
            content: List<Content>?,
            clickListener: (String) -> Unit
        ) {
            if (content == null || content.isEmpty()) {
                containerButtons.visibility = View.GONE
            } else {
                containerButtons.visibility = View.VISIBLE
                containerButtons.removeAllViews()
                for (item in content) {
                    val view: Button = LayoutInflater.from(containerButtons.context).inflate(
                        R.layout.item_button,
                        containerButtons,
                        false
                    ) as Button
                    view.text = item.title
                    containerButtons.addView(view)
                    view.setOnClickListener {
                        item.target?.let { it1 -> clickListener(it1) }
                    }
                }
            }
        }

        private fun bindImage(backgroundImage: String?) {
            if (backgroundImage.isNullOrBlank())
                image.visibility = View.GONE
            else {
                image.visibility = View.VISIBLE
                Picasso.get()
                    .load(backgroundImage)
                    .into(image)
            }

        }
    }
}