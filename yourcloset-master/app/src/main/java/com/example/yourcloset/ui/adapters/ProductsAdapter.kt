package com.example.yourcloset.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.yourcloset.ProductFragmentDirections
import com.example.yourcloset.R
import com.example.yourcloset.databinding.ProductItemBinding
import com.example.yourcloset.models.Producto

class ProductsAdapter : RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>(){
    var products :List<Producto> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val withDataBinding: ProductItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            ProductViewHolder.LAYOUT,
            parent,
            false)
        return ProductViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.producto = products[position]
        }
        holder.bind(products[position])
        holder.viewDataBinding.root.setOnClickListener {
            val action = ProductFragmentDirections.actionProductFragmentToProductDetailFragment(
                products[position].productoId,
                products[position].nombre,
                products[position].descripcion,
                products[position].precio,
                products[position].inventario,
                products[position].imagen
            )
            // Navegate
            holder.viewDataBinding.root.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return products.size
    }

    class ProductViewHolder(val viewDataBinding: ProductItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.product_item
        }
        fun bind(producto: Producto) {
            Glide.with(itemView)
                .load(producto.imagen.toUri().buildUpon().scheme("https").build())
                .apply(
                    RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(R.drawable.ic_product)
                        .error(R.drawable.camisetadeportiva1))
                .into(viewDataBinding.imagenproducto)
        }
    }

}