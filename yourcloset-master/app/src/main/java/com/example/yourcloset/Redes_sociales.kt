package com.example.yourcloset

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment


class Redes_sociales : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var btnwhatsapp: Button
    private lateinit var btnfacebook: Button
    private lateinit var btninstagram: Button




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_redes_sociales, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btnwhatsapp = view.findViewById(R.id.buttonwhatsapp)
        btnfacebook = view.findViewById(R.id.buttonFacebook)
        btninstagram = view.findViewById(R.id.buttoninstagram)

        val uri: Uri = Uri.parse("https://www.instagram.com/german_c9905/")
        val instagram = Intent(Intent.ACTION_VIEW, uri)

        val uri2: Uri = Uri.parse("https://www.facebook.com/carlosgerman.ariasgarcia")
        val facebook = Intent(Intent.ACTION_VIEW, uri2)

        val uri3: Uri = Uri.parse("https://api.whatsapp.com/send?phone=573194793232")
        val whatsapp = Intent(Intent.ACTION_VIEW, uri3)



        instagram.setPackage("com.instagram.android")
        facebook.setPackage("com.facebook.katana")
        whatsapp.setPackage("com.whatsapp.android")


        btninstagram.setOnClickListener{
            try {
                startActivity(instagram)
            } catch (e: ActivityNotFoundException) {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.instagram.com/german_c9905/")
                    )
                )
            }
        }
        btnfacebook.setOnClickListener{
            try {
                startActivity(facebook)
            } catch (e: ActivityNotFoundException) {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.facebook.com/carlosgerman.ariasgarcia")
                    )
                )
            }
        }
        btnwhatsapp.setOnClickListener{
            try {
                startActivity(whatsapp)
            } catch (e: ActivityNotFoundException) {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://api.whatsapp.com/send?phone=573194793232")
                    )
                )
            }
        }

    }
}




