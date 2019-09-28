package com.example.kamino.ui.ui

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.example.kamino.R
import kotlinx.android.synthetic.main.activity_residents_list.*

class ResidentsListActivity : AppCompatActivity() {
    var residentsList: Array<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_residents_list)

        getResidents()

    }

    private fun getResidents() {
        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            residentsList = bundle.getStringArray("residents");
        }
    }
}
