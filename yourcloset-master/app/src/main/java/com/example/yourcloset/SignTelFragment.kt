package com.example.yourcloset

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.yourcloset.databinding.FragmentSignTelBinding
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.concurrent.TimeUnit


class SignTelFragment : Fragment() {
    private lateinit var phone: EditText
    private lateinit var codigo: EditText
    private lateinit var btnGetCode: Button
    private lateinit var btnPostCode: Button
    private lateinit var auth: FirebaseAuth
    var verificationId = ""
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private var storedVerificationId: String? = ""
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    private var _binding: FragmentSignTelBinding? = null
    private val binding get() = _binding!!
    private lateinit var progress:ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSignTelBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        auth = Firebase.auth
        val currentUser = auth.currentUser
        if (currentUser != null) {
            Toast.makeText(requireContext().applicationContext, "usuario logueado", Toast.LENGTH_LONG)
                .show()
        } else {
            Toast.makeText(
                requireContext().applicationContext,
                "usuario no creado",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        auth.setLanguageCode("es")
        phone = view.findViewById(R.id.signPhone)
        codigo = view.findViewById(R.id.codigo)
        btnGetCode = view.findViewById(R.id.btnGetCode)
        btnPostCode = view.findViewById(R.id.btnPostCode)
        progress = view.findViewById(R.id.progress)
        btnGetCode.setOnClickListener { view: View ->
            if (phone.text.toString() == "") {
                Toast.makeText(
                    requireContext().applicationContext,
                    "Por favor ingrese un número de teléfono",
                    Toast.LENGTH_LONG
                ).show()
            } else if (phone.text.toString().length < 12 || phone.text.toString().length > 13) {
                Toast.makeText(
                    requireContext().applicationContext,
                    "Por favor ingrese un número de teléfono válido",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                progress.visibility = View.VISIBLE
                verify()
            }
        }

        btnPostCode.setOnClickListener { view: View ->
            progress.visibility = View.VISIBLE
            println("BTN enviar codigo")
            if (codigo.text.toString() == "") {
                Toast.makeText(
                    requireContext().applicationContext,
                    "Por favor ingrese un código",
                    Toast.LENGTH_LONG
                ).show()
            } else if (codigo.text.toString().length != 6) {
                Toast.makeText(
                    requireContext().applicationContext,
                    "Por favor ingrese un código válido",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                authenticate(codigo.text.toString())
            }
        }

    }
    private fun verificationCallbacks(){
        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                progress.visibility=View.INVISIBLE
                Log.d(TAG, "onVerificationCompleted:$credential")
                signInWithPhoneAuthCredential(credential)
            }

            override fun onVerificationFailed(p0: FirebaseException) {
                Log.w(TAG, "onVerificationFailed", p0)
                if (p0 is FirebaseAuthInvalidCredentialsException) {
                    Log.d(TAG, "invalid request")
                } else if (p0 is FirebaseTooManyRequestsException) {
                    Log.d(TAG, "Demasiadas solicitudes")
                }
            }

            override fun onCodeSent(verification: String, p1: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(verification, p1)
                verificationId = verification.toString()
                Log.d(TAG, "onCodeSent:$verificationId")
                progress.visibility = View.INVISIBLE
                // Save verification ID and resending token so we can use them later
                storedVerificationId = verificationId
                resendToken = p1
            }
        }
    }

    private fun verify() {
        verificationCallbacks()
        val phoneNumber =phone.text.toString()
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(requireActivity())                 // Activity (for callback binding)
            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }
    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(requireContext().applicationContext,"Registro exitoso", Toast.LENGTH_LONG).show()
                    val action = SignTelFragmentDirections.actionSignTelFragmentToHomeFragment()
                    findNavController().navigate(action)
                } else {
                    // Sign in failed, display a message and update the UI
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        Toast.makeText(requireContext().applicationContext,"El código es inválido por favor reintente", Toast.LENGTH_LONG).show()
                    }
                    progress.visibility = View.INVISIBLE
                }
            }
    }

    private fun authenticate(code:String) {
        val credential = PhoneAuthProvider.getCredential(verificationId!!, code)
        signInWithPhoneAuthCredential(credential)
    }

    private fun resendVerificationCode(
        phoneNumber: String,
        token: PhoneAuthProvider.ForceResendingToken?
    ) {
        val optionsBuilder = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(requireActivity())                 // Activity (for callback binding)
            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
        if (token != null) {
            optionsBuilder.setForceResendingToken(token) // callback's ForceResendingToken
        }
        PhoneAuthProvider.verifyPhoneNumber(optionsBuilder.build())
    }

        private fun updateUI(user: FirebaseUser? = auth.currentUser) {

        }

        companion object {
            private const val TAG = "PhoneAuthActivity"
        }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    }
