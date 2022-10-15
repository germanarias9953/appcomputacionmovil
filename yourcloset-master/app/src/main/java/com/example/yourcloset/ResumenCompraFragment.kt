package com.example.yourcloset

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TableLayout
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.yourcloset.models.Pedido
import com.example.yourcloset.models.Producto

class ResumenCompra : Fragment() {

    private lateinit var tableResumen: TableLayout
    private lateinit var tvTotal: TextView
    private lateinit var tvNombre: TextView
    private lateinit var tvPedido: TextView
    private lateinit var btnExit: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_resumen_compra, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        tableResumen =view.findViewById(R.id.table_resumen)
        tvTotal = view.findViewById(R.id.total_compra_resumen)
        tvNombre =view.findViewById(R.id.nombre_resumen)
        tvPedido =view.findViewById(R.id.pedido_resumen)
        btnExit = view.findViewById(R.id.exit_button)

        btnExit.setOnClickListener {
            val action = ResumenCompraDirections.actionResumenCompraToHomeFragment()
            findNavController().navigate(action)
        }
        testFragment()
    }

    //Metodos de la logica
    fun testFragment(){
        val producto = Producto(2001, "camisa moda","camisa moda para toda ocasion...",25000,100,"http://#")
        val pedido = Pedido(1001,producto,2,50000,"Pedrito Perez","calle 1 #2-34","pedroperez@gmail.com","3210987654")
        llenarDatos(pedido)
    }

    fun llenarDatos(pedido: Pedido){
        tvNombre.setText(pedido.nombreUsuario)
        tvPedido.setText(pedido.pedidoId.toString())
        tvTotal.setText(pedido.precioTotal.toString())
        var index = 1
        do{
            val registro=LayoutInflater.from(context).inflate(R.layout.item_table_resumen_compra,null,false)
            val tvCantidad = registro.findViewById<TextView>(R.id.cantidad_item_resumen) as TextView
            val tvProducto = registro.findViewById<TextView>(R.id.producto_item_resumen) as TextView
            val tvPrecio = registro.findViewById<TextView>(R.id.precio_item_resumen) as TextView
            tvCantidad.setText(pedido.cantidad.toString()+" ")
            tvProducto.setText(pedido.producto.nombre)
            tvPrecio.setText(pedido.precioTotal.toString())
            tableResumen.addView(registro,1)
            index++
        }while(index<4)
    }

    companion object {
    }
}