package com.simbirsoft.efa.hunternet

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class BaseActivity : AppCompatActivity() {

    val mAuth = FirebaseAuth.getInstance()
    lateinit var mDatabase : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        val resultTxt = findViewById<View>(R.id.resulttextView) as TextView

        mDatabase = FirebaseDatabase.getInstance().getReference("Usernames")

        mDatabase.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
            override fun onDataChange(snapshot: DataSnapshot) {
                val result = snapshot.child("Username").toString()
                resultTxt.text = result
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == R.id.signOutMenu) {
            mAuth.signOut()
            startActivity(Intent(this, MainActivity :: class.java))
            Toast.makeText(this, "Sign out... ", Toast.LENGTH_LONG).show()
        }
        return super.onOptionsItemSelected(item)
    }
}
