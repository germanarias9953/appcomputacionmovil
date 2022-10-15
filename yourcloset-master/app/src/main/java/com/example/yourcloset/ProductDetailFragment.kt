package com.example.yourcloset

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.yourcloset.databinding.FragmentProductDetailBinding
import com.example.yourcloset.models.Producto

class ProductDetailFragment : Fragment() {
    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        activity.actionBar?.title = getString(R.string.detallesproducto)

        val args: ProductDetailFragmentArgs by navArgs()
        _binding?.producto = Producto(
            args.productoId,
            args.nombre,
            args.descripcion,
            args.precio,
            args.inventario,
            args.imagen
        )
        Glide.with(activity)
            .load(args.imagen)
            .apply(
                RequestOptions().diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .placeholder(R.drawable.ic_product)
                    .error(R.drawable.camisetadeportiva1))
            .into(_binding!!.imagenproducto)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
