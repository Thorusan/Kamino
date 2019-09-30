package com.example.kamino.ui.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.example.kamino.R
import java.util.ArrayList

class ResidentsListActivity : AppCompatActivity() {

    @BindView(R.id.recycler_view)
    lateinit var recyclerView: RecyclerView

    lateinit var residentsList: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_residents_list)

        ButterKnife.bind(this)

        getResidentsFromBundle()
        setResidentsListAdapter()

    }

    private fun getResidentsFromBundle() {
        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            residentsList = bundle.getStringArrayList("residents");
        }
    }

    private fun setResidentsListAdapter() {
        // Pass results to SearchBaseListAdapter Class
        val listAdapter = ResidentsListAdapter(this, ArrayList(residentsList))
        recyclerView.setLayoutManager(LinearLayoutManager(this))
        // Binds the Adapter to the RecyclerView
        recyclerView.setAdapter(listAdapter)

    }
}
