package com.example.yourcloset

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import com.example.yourcloset.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private lateinit var btnCatalogo: ImageView
    private lateinit var btnLogin: ImageView
    private lateinit var btnEscanea: ImageView
    private lateinit var btnComentarios: ImageView
    private lateinit var btnEncuentranos: ImageView
    private lateinit var btnSiguenos: ImageView
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btnLogin = view.findViewById(R.id.loginImage)
        btnComentarios = view.findViewById(R.id.comentarioimage)
        btnEscanea = view.findViewById(R.id.escannerImage)
        btnEncuentranos = view.findViewById(R.id.mapsImage)
        btnCatalogo = view.findViewById(R.id.ropaImage)
        btnLogin.setOnClickListener() { view: View ->
            val action = HomeFragmentDirections.actionHomeFragmentToLoginFragment()
            findNavController().navigate(action)
        }
        btnComentarios.setOnClickListener() { view: View ->
            val action = HomeFragmentDirections.actionHomeFragmentToCommentsFragment()
            findNavController().navigate(action)
        }
        btnCatalogo.setOnClickListener() { view: View ->
            val action = HomeFragmentDirections.actionHomeFragmentToProductFragment()
            findNavController().navigate(action)
        }
        btnEncuentranos.setOnClickListener() { view: View ->
            val action = HomeFragmentDirections.actionHomeFragmentToLocationFragment()
            findNavController().navigate(action)
        }
        btnEscanea.setOnClickListener() { view: View ->
            val action = HomeFragmentDirections.actionHomeFragmentToScannerActivity()
            findNavController().navigate(action)
        }

        /* TODO: remover mas tarde.
         * Esta funcion fue creada para probar el resumen del producto.
         */
        btnSiguenos = view.findViewById(R.id.redesImage)
        btnSiguenos.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToRedessociales()
            findNavController().navigate(action)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}