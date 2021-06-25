package com.nb.appplatziconf.view.ui.activities

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationMenu
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.firestore.FirebaseFirestore
import com.nb.appplatziconf.R
import com.nb.appplatziconf.model.Conference
import com.nb.appplatziconf.model.Speaker
import org.json.JSONArray
import org.json.JSONObject
import java.util.*

class MainActivity : AppCompatActivity() {


    private lateinit var bnv_menu: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        bnv_menu = findViewById<BottomNavigationView>(R.id.bnvMenu)
        configNav()

    }

    private fun configNav() {
        NavigationUI.setupWithNavController(bnv_menu, Navigation.findNavController(this,R.id.fragContent))
    }
}