package com.miniweebs.reciboi.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.miniweebs.reciboi.R
import com.miniweebs.reciboi.data.api.User
import com.miniweebs.reciboi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding : ActivityMainBinding? = null
    private lateinit var database : DatabaseReference
    private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding!!.root
        setContentView(view)
        val bottomNavigationView = binding!!.bottomNavbar
        val navController = findNavController(R.id.main_fragment)
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.randomMealFragment,R.id.searchFragment,R.id.userPrefFragment))
        setupActionBarWithNavController(navController,appBarConfiguration)
        bottomNavigationView.setupWithNavController(navController)
        database = FirebaseDatabase.getInstance().reference
        auth = FirebaseAuth.getInstance()
    }

    override fun onStart() {
        super.onStart()
        database.child("Users").child(auth.uid!!).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                Log.d("check","check")
                if(!p0.exists())
                {
                    Log.d("check",p0.toString())
                    database.child("Users").child(auth.uid!!).setValue(User(auth.currentUser?.displayName!!,auth.currentUser?.email!!,auth.currentUser?.photoUrl.toString()!!))
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}