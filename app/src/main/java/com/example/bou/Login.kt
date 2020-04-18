package com.example.bou

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity(), View.OnClickListener {

    // [START declare_auth]
    private lateinit var auth: FirebaseAuth
    // [END declare_auth]

    private lateinit var emailView: EditText

    private lateinit var passwordView: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        // Buttons
        singInButton.setOnClickListener(this)
        // [START initialize_auth]
        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()
        // [END initialize_auth]
    }

    private fun signIn() {

        emailView = findViewById(R.id.username)
        passwordView = findViewById(R.id.password)

        Log.d("LoginActivity", "signIn:$emailView.text.toString().trim()")
        if (!validateForm()) {
            return
        }

        // [START sign_in_with_email]
        auth.signInWithEmailAndPassword(emailView.text.toString().trim(), passwordView.text.toString().trim())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("LoginActivity", "signInWithEmail:success")
                    val user = auth.currentUser

                    val intent = Intent(this, Home::class.java)
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("LoginActivity", "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }

            }
        // [END sign_in_with_email]
    }
    fun goRegistro(view: View) {

        val intent = Intent(this, Registro::class.java)
        startActivity(intent)
    }
    override fun onClick(v: View) {
        val i = v.id
        when (i) {
            R.id.singInButton -> signIn()

        }
    }
        private fun validateForm(): Boolean {
        var valid = true

        if (TextUtils.isEmpty(emailView.text)) {
            emailView.error = "Required."
            valid = false
        } else {
            emailView.error = null
        }

        val password = passwordView.text.toString()
        if (TextUtils.isEmpty(passwordView.text)) {
            passwordView.error = "Required."
            valid = false
        } else {
            passwordView.error = null
        }

        return valid
    }

}
