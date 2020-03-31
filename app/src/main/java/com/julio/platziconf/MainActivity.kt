package com.julio.platziconf

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.firebase.firestore.FirebaseFirestore
import com.julio.platziconf.model.Conference
import com.julio.platziconf.model.Speaker
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setActionBar(findViewById(R.id.tbMainToolbar))
        configNav()

    }
    fun configNav(){
        NavigationUI.setupWithNavController(bnMainMenu, Navigation.findNavController(this,R.id.fragMainContent))
    }
}
