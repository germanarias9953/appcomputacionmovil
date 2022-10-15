package com.example.yourcloset

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.fragment.findNavController
import com.example.yourcloset.databinding.FragmentLoginBinding
import com.example.yourcloset.databinding.FragmentSignTelBinding
import com.google.firebase.auth.FirebaseAuth


class LoginFragment : Fragment() {

    private lateinit var txtUser: EditText
    private lateinit var txtPassword: EditText
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var btnIngreso: Button
    private lateinit var linkSignUp: TextView
    private lateinit var linkSignTel: TextView
    private lateinit var mAuth:FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        txtUser = view.findViewById(R.id.txtUser)
        txtPassword = view.findViewById(R.id.txtPassword)
        btnIngreso = view.findViewById(R.id.btnLogin)
        linkSignUp = view.findViewById(R.id.goSignUp)
        linkSignTel = view.findViewById(R.id.loginTel)
        mAuth = FirebaseAuth.getInstance()
        btnIngreso.setOnClickListener { view: View ->
            loginUser()
        }
        linkSignUp.setOnClickListener { view: View ->
            val action = LoginFragmentDirections.actionLoginFragmentToSignUpFragment()
            // Navigate using that action
            findNavController().navigate(action)
            print("BTN Link Register")
        }
        linkSignTel.setOnClickListener { view: View ->
            val action = LoginFragmentDirections.actionLoginFragmentToSignTelFragment()
            // Navigate using that action
            findNavController().navigate(action)
            print("BTN Link Telefono")
        }
    }

    private fun loginUser(){
        val user:String=txtUser.text.toString()
        val password:String=txtPassword.text.toString()

        if(!TextUtils.isEmpty(user) && !TextUtils.isEmpty(password)){
            mAuth.signInWithEmailAndPassword(user, password)
                .addOnCompleteListener(requireActivity()) { task ->
                if(task.isSuccessful) {
                    Toast.makeText(requireContext().applicationContext,"Ingreso con éxito", Toast.LENGTH_LONG).show()
                    val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
                    findNavController().navigate(action)
                }else {
                    Toast.makeText(requireContext().applicationContext,"Login Falló", Toast.LENGTH_LONG).show()
                }
            }

        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}