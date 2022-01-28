package com.example.application

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ProfileActivity : AppCompatActivity() {

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseDatabase.getInstance().getReference("UserInfo")

    private lateinit var imageView: ImageView
    private lateinit var textView: TextView
    private lateinit var editTextTextNickname: EditText
    private lateinit var buttonSave: Button
    private lateinit var buttonChangePassword: Button
    private lateinit var buttonLogout: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile)

        init()

        registerListeners()

        db.child(auth.currentUser?.uid!!).addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val userInfo: UserInfo = snapshot.getValue(UserInfo::class.java) ?: return
                textView.text = userInfo.nickname

            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

    }

    private fun init() {
        imageView = findViewById(R.id.imageView)
        textView = findViewById(R.id.textView)
        editTextTextNickname = findViewById(R.id.editTextTextNickname)
        buttonSave = findViewById(R.id.buttonSave)
        buttonChangePassword = findViewById(R.id.buttonChangePassword)
        buttonLogout = findViewById(R.id.buttonLogout)
    }

    private fun registerListeners() {
        buttonSave.setOnClickListener{
            val nickname = editTextTextNickname.text.toString()
            val userInfo = UserInfo(nickname)
            db.child(auth.currentUser?.uid!!).setValue(userInfo)
            startActivity(Intent(this, MainActivity::class.java))
        }
        buttonChangePassword.setOnClickListener {
            startActivity(Intent(this, PassChangeActivity::class.java))
        }
        buttonLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

}