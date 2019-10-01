package com.example.kamino.ui.ui

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import butterknife.BindView
import butterknife.ButterKnife
import com.example.kamino.R
import com.example.kamino.datamodel.KaminoModel
import com.example.kamino.utils.GlideApp
import retrofit2.Response
import java.util.*


class ResidentsListActivity : AppCompatActivity(),
    ResidentsListViewPresenterContract.ViewInterface {

    @BindView(R.id.container_list)
    lateinit var containerList: LinearLayout
    @BindView(R.id.recycler_view)
    lateinit var recyclerView: androidx.recyclerview.widget.RecyclerView
    @BindView(R.id.container_resident)
    lateinit var containerResident: NestedScrollView
    @BindView(R.id.img_profile)
    lateinit var imgProfile: ImageView
    @BindView(R.id.text_name)
    lateinit var textName: TextView
    @BindView(R.id.text_height)
    lateinit var textHeight: TextView;
    @BindView(R.id.text_mass)
    lateinit var textMass: TextView;
    @BindView(R.id.text_hair_color)
    lateinit var textHairColor: TextView;
    @BindView(R.id.text_skin_color)
    lateinit var textSkinColor: TextView;
    @BindView(R.id.text_eye_color)
    lateinit var textEyeColor: TextView;
    @BindView(R.id.text_birth_year)
    lateinit var textBirthYear: TextView;
    @BindView(R.id.text_gender)
    lateinit var textGender: TextView;
    @BindView(R.id.text_homeworld)
    lateinit var textHomeworld: TextView
    @BindView(R.id.btn_close_details)
    lateinit var btnCloseDetails: ImageButton

    lateinit var residentsList: ArrayList<String>

    private var residentsListPresenterImplementation: ResidentsListPresenterImplementation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_residents_list)

        ButterKnife.bind(this)

        residentsListPresenterImplementation = ResidentsListPresenterImplementation(this)

        getResidentsFromBundle()
        setResidentsListAdapter()

        registerListener();

    }

    private fun registerListener() {
        btnCloseDetails.setOnClickListener {
            containerResident.visibility = View.GONE;
            containerList.visibility = View.VISIBLE
        }

    }

    private fun getResidentsFromBundle() {
        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            residentsList = bundle.getStringArrayList("residents");
        }
    }

    private fun setResidentsListAdapter() {
        // Pass results to SearchBaseListAdapter Class
        val listAdapter = ResidentsListAdapter(
            this,
            ArrayList(residentsList),
            { item -> callApi_ResidentDetails(item) })
        recyclerView?.setLayoutManager(LinearLayoutManager(this))
        // Binds the Adapter to the RecyclerView
        recyclerView?.setAdapter(listAdapter)
    }

    /**
     * Call api for getting Resident data
     */
    private fun callApi_ResidentDetails(residentUrl: String) {
        residentsListPresenterImplementation?.getResidentData(residentUrl)
    }

    override fun displayResidentData(residentData: Response<KaminoModel.Resident>) {
        containerResident?.visibility = View.VISIBLE;
        containerList?.visibility = View.GONE;

        textName?.text = residentData.body()?.name
        textHeight?.text = residentData.body()?.height.toString()
        textMass?.text = residentData.body()?.mass
        textHairColor?.text = residentData.body()?.hairColor
        textSkinColor?.text = residentData.body()?.skinColor
        textEyeColor?.text = residentData.body()?.eyeColor
        textBirthYear?.text = residentData.body()?.birthYear
        textGender?.text = residentData.body()?.gender
        textHomeworld?.text = residentData.body()?.homeworld

        loadProfileImage(residentData.body()?.imageUrl)
    }

    private fun loadProfileImage(imageUrl: String?) {
        GlideApp.with(this)
            .load(imageUrl)
            .into(imgProfile);
    }
}
