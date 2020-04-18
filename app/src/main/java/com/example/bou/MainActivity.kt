package com.example.bou

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import android.util.Log
import android.view.Menu
import android.view.MenuItem


class   MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkCurrentUser()
    }

    private fun checkCurrentUser() {
        // [START check_current_user]
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            Log.i(".MainActivity","User is not null")
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        } else {
            Log.i(".MainActivity","User is null")
            val intent2 = Intent(this, Login::class.java)
            startActivity(intent2)
        }
        // [END check_current_user]
    }

}
