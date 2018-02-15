package com.simbirsoft.efa.hunternet

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class RegisterActivity : AppCompatActivity() {

    private val mAuth = FirebaseAuth.getInstance()
    lateinit var mDatabase : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val registerBtn = findViewById<View>(R.id.registerButton) as Button

        mDatabase = FirebaseDatabase.getInstance().getReference("Usernames")

        registerBtn.setOnClickListener(View.OnClickListener {
            view -> register()
        })
    }

    private fun register() {
        val emailTxt = findViewById<View>(R.id.emailTextInput) as EditText
        val passwordTxt = findViewById<View>(R.id.passwordTextInput) as EditText
        val usernameTxt = findViewById<View>(R.id.usernameTextInput) as EditText

        var email = emailTxt.text.toString()
        var password = passwordTxt.text.toString()
        var username = usernameTxt.text.toString()

        if (!username.isEmpty() && !password.isEmpty() && !email.isEmpty()) {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, OnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = mAuth.currentUser
                    val uid = user!!.uid
                    mDatabase.child(uid).child("Username").setValue(username)
                    Toast.makeText(this, "Registration successful...", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "(Un)expected error...", Toast.LENGTH_LONG).show()
                }
            })
        } else {
            Toast.makeText(this, "Please enter username, email and password...", Toast.LENGTH_LONG).show()
        }
    }


}