package com.example.yourcloset.repositories

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.example.yourcloset.models.Producto

class ProductsRepository (val application: Application) {
    suspend fun refreshData(): List<Producto> {
        var producto1 = Producto(
            1,
            "Chaqueta de cuero",
            "prenda de uso habitual tanto para hombres como para mujeres. La cazadora es una prenda cómoda y ligera",
            190000,
            23,
            "https://m.media-amazon.com/images/I/81gPumH6e1L._AC_UX679_.jpg"
        )
        var producto2 = Producto(
            2,
            "Chaqueta",
            "chaqueta moderna de estilo mediterraneo que evoca las tradiciones antiguas de las elites europeas",
            350000,
            41,
            "https://m.media-amazon.com/images/I/61cmifc3sLL._AC_UX342_.jpg"
        )
        var producto3 = Producto(
            3,
            "Jeans",
            "pantalones cortos bavaros hechos de algodón, ideal para celebraciones como el oktoberfest",
            800000,
            17,
            "https://i.pinimg.com/736x/cd/12/7a/cd127af489242fbdd652090ae8d217d9.jpg"
        )
        var producto4 = Producto(
            4,
            "Camiseta de Colombia",
            "Es esencialmente la forma conocida como trilby. Están hechos de fieltro, con un cordón alrededor y llevan una pluma como adorno",
            500000,
            38,
            "https://falabella.scene7.com/is/image/FalabellaCO/4946063_b?wid=800&hei=800&qlt=70"
        )
        var producto5 = Producto(
            5,
            "Falda de mujer",
            "pantalones cortos bavaros hechos de algodón, ideal para celebraciones como el oktoberfest",
            800000,
            17,
            "https://m.media-amazon.com/images/I/719WpDBbXCL._AC_UX385_.jpg"
        )
        var producto6 = Producto(
            6,
            "Vestido de coctel retro ",
            "Vestido estadounidense  para dama retro 1950",
            800000,
            15,
            "https://upload.wikimedia.org/wikipedia/commons/a/a4/Cocktail_dress_MET_1979.424.1_F.jpg"
        )
        var producto7 = Producto(
            7,
            "Jean mujer",
            "prenda de vestir campestre por la clase alta como chaquetas de caza deportiva por su resistencia a la humedad y la lluvia",
            750000,
            11,
            "https://www.dhresource.com/0x0/f2/albu/g8/M00/86/47/rBVaVF2tXBqAE60gAADurSTzqw0241.jpg/women-jeans-new-arrival-slim-jeans-for-ladies.jpg"
        )
        var producto8 = Producto(
            8,
            "Blusa Mujer",
            "falda masculina más reconocible en el mundo entero, existe una gran variedad de trajes tradicionales masculinos",
            300000,
            20,
            "https://i.pinimg.com/474x/d9/c4/f1/d9c4f152efd0843170463de74af72df1.jpg"
        )
        var producto9 = Producto(
            9,
            "Polo hombre",
            "diseñada con el fin de calentarlo, absorber su sudor, protegerlo de suciedad y rasguños y aliviar el frotamiento con el calzado",
            40000,
            19,
            "https://ean-images.booztcdn.com/polo-ralph-lauren/1300x1700/raf710666998007_cjamaicaheather_v100200628au_10.jpg"
        )
        var producto10 = Producto(
            10,
            "Vestido enterizo",
            "ropa  interior modernas sexi femenina para toda ocasión que resalta la belleza cuerpos con silueta delgada",
            90000,
            15,
            "https://i.pinimg.com/474x/6c/e3/dc/6ce3dc275bbb5c1d753184888cd7d3dc.jpg"
        )
        val productos = mutableListOf(producto1, producto2, producto3, producto4,producto5,producto6,producto7,producto8,producto9,producto10)
        return productos
    }
}

