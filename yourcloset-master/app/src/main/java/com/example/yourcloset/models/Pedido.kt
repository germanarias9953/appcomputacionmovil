package com.example.yourcloset.models

class Pedido (
    val pedidoId: Int,
    val producto: Producto,
    val cantidad: Int,
    val precioTotal: Int,
    val nombreUsuario: String,
    val direccion: String,
    val email: String,
    val telefono: String
)