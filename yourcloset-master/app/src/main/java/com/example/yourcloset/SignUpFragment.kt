package com.example.yourcloset

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.yourcloset.databinding.FragmentSignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class SignUpFragment : Fragment() {
    private lateinit var email: EditText
    private lateinit var pass: EditText
    private lateinit var pass2: EditText
    private lateinit var btnRegistro: Button
    private lateinit var linkLogin: TextView
    private lateinit var linkTel: TextView
    private lateinit var auth: FirebaseAuth
    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!
    private lateinit var mAuth:FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        email = view.findViewById(R.id.signEmail)
        pass = view.findViewById(R.id.signPassword)
        pass2 = view.findViewById(R.id.signPassword2)
        btnRegistro = view.findViewById(R.id.btnRegistro)
        linkLogin = view.findViewById(R.id.linkEstasRegistrado)
        linkTel = view.findViewById(R.id.linkSignUpTel)
        mAuth = FirebaseAuth.getInstance()
        btnRegistro.setOnClickListener { view: View ->
            if(email.text.toString()=="" || pass.text.toString()=="" || pass2.text.toString()==""){
                Toast.makeText(requireContext().applicationContext,"Por favor ingrese los datos completos", Toast.LENGTH_LONG).show()
            }else if(pass.text.toString()!=pass2.text.toString()){
                Toast.makeText(requireContext().applicationContext,"Las contraseñas no coinciden", Toast.LENGTH_LONG).show()

            }
            else {
                createAccount(email.text.toString(), pass.text.toString())
            }
        }
        linkLogin.setOnClickListener { view: View ->
            val action = SignUpFragmentDirections.actionSignUpFragmentToLoginFragment()
            // Navigate using that action
            findNavController().navigate(action)
            print("BTN Link Login")
            }
        linkTel.setOnClickListener { view: View ->
            val action = SignUpFragmentDirections.actionSignUpFragmentToSignTelFragment()
            // Navigate using that action
            findNavController().navigate(action)
            print("BTN Link Login")
        }
    }

    override fun onStart() {
        super.onStart()
        auth = Firebase.auth
        val currentUser = auth.currentUser
        if(currentUser !=null){
            println("Ya hay usuario creado")
            Toast.makeText(requireContext().applicationContext,"usuario logueado", Toast.LENGTH_LONG).show()
        }else{
            println("No hay usuario logueado)")
            Toast.makeText(requireContext().applicationContext,"usuario no creado", Toast.LENGTH_LONG).show()
        }
    }
    fun createAccount(email:String, password:String){
        println(email)
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    Toast.makeText(requireContext().applicationContext,"Creación con éxito", Toast.LENGTH_LONG).show()
                    val action = SignUpFragmentDirections.actionSignUpFragmentToHomeFragment()
                    findNavController().navigate(action)
                } else {
                    Toast.makeText(requireContext().applicationContext,"Usuario no creado", Toast.LENGTH_LONG).show()
                }
            }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}