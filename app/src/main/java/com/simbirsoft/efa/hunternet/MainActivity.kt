package com.simbirsoft.efa.hunternet

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private val mAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loginBtn = findViewById<View>(R.id.LoginButton) as Button
        val registerTxt = findViewById<View>(R.id.RegisterTextView) as TextView

        loginBtn.setOnClickListener(View.OnClickListener {
            view -> login()
        })

        registerTxt.setOnClickListener(View.OnClickListener {
            view -> register()
        })

        }

    private fun login() {
        val emailTxt = findViewById<View>(R.id.EmailText) as EditText
        val passwordTxt = findViewById<View>(R.id.PasswordText) as EditText

        var email = emailTxt.text.toString()
        var password = passwordTxt.text.toString()

        if (!email.isEmpty() && !password.isEmpty()) {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, OnCompleteListener { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(this, BaseActivity :: class.java))
                    Toast.makeText(this, "Successfully logged in...", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "(Un)expected error...", Toast.LENGTH_LONG).show()
                }
            })
        } else {
            Toast.makeText(this, "Please enter email and/or password...", Toast.LENGTH_LONG).show()
        }

    }

    private fun register() {
        startActivity(Intent(this, RegisterActivity :: class.java))
    }
}
