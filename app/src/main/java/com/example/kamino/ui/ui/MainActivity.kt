package com.example.kamino.ui.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.request.RequestOptions
import com.example.kamino.R
import com.example.kamino.datamodel.KaminoModel
import com.example.kamino.utils.GlideApp
import io.reactivex.disposables.Disposable
import retrofit2.Response

class MainActivity : AppCompatActivity(), MainViewPresenterContract.ViewInterface {


    @BindView(R.id.img_planet)
    lateinit var imgPlanet: ImageView;
    @BindView(R.id.btn_residents)
    lateinit var btnResidents: Button;
    @BindView(R.id.text_name)
    lateinit var textName: TextView;
    @BindView(R.id.text_rotation_period)
    lateinit var textRotationPeriod: TextView;
    @BindView(R.id.text_orbital_period)
    lateinit var textOrbitalPeriod: TextView;
    @BindView(R.id.text_diameter)
    lateinit var textDiameter: TextView;
    @BindView(R.id.text_climate)
    lateinit var textClimate: TextView;
    @BindView(R.id.text_gravity)
    lateinit var textGravity: TextView;
    @BindView(R.id.text_terrain)
    lateinit var textTerrain: TextView;
    @BindView(R.id.text_surface_water)
    lateinit var textSurfaceWater: TextView;
    @BindView(R.id.text_population)
    lateinit var textPopulation: TextView;
    @BindView(R.id.text_likes)
    lateinit var textLikes: TextView;
    @BindView(R.id.container_like)
    lateinit var containerLike: LinearLayout;

    var disposable: Disposable? = null
    var planetData: KaminoModel.KaminoPlanet? = null
    var residentsList: ArrayList<String>? = null
    var isThumbnail: Boolean = true;
    var likes: Int? = 0;

    private var mainPresenterImplementation: MainPresenterImplementation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ButterKnife.bind(this);

        mainPresenterImplementation = MainPresenterImplementation(this)
        mainPresenterImplementation!!.getPlanetData()

        registerListeners()
    }

    private fun registerListeners() {
        imgPlanet.setOnClickListener {
            if (!isThumbnail) {
                loadThumbnailPicture();
                isThumbnail = true;
            } else {
                loadLargePicture()
                isThumbnail = false;
            }
        }

        btnResidents.setOnClickListener {
            val intent: Intent? = Intent(this, ResidentsListActivity::class.java);
            if (intent != null) {
                intent.putStringArrayListExtra("residents", residentsList)
                startActivity(intent);
            }
        }

        containerLike.setOnClickListener {
            if (isPlanetLiked()) {
                val toast = Toast.makeText(
                    applicationContext,
                    getString(R.string.already_liked_planet),
                    Toast.LENGTH_LONG
                )
                toast.show()
            } else {
                mainPresenterImplementation!!.likePlanet();
            }
        }
    }

    override fun onDestroy() {
        disposable?.dispose()
        super.onDestroy()
    }

    override fun displayPlanetData(responseModel: Response<KaminoModel.KaminoPlanet>) {
        if (responseModel.body() != null) {
            planetData = responseModel.body()
            residentsList = responseModel.body()!!.residents

            setFields()
            loadThumbnailPicture()
        }
    }

    override fun updateLikes(responseModel: Response<KaminoModel.Like>) {
        if (responseModel.body() != null) {
            likes = responseModel.body()!!.likes
            textLikes.text = likes.toString()

            val toast = Toast.makeText(
                applicationContext,
                getString(R.string.text_after_like_planet),
                Toast.LENGTH_LONG
            )
            toast.show()

            saveLikeToSharedPreferences()
        }
    }

    private fun saveLikeToSharedPreferences() {
        val sharedPref = this.getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putBoolean("likes", true)
            commit()
        }
    }

    private fun isPlanetLiked(): Boolean {
        val sharedPref = this.getPreferences(Context.MODE_PRIVATE)
        val isPlanedLiked = sharedPref.getBoolean("likes", false)
        return isPlanedLiked;
    }

    private fun setFields() {
        textName.text = planetData?.name
        textRotationPeriod.text = planetData?.rotationPeriod.toString()
        textOrbitalPeriod.text = planetData?.orbitalPeriod.toString()
        textDiameter.text = planetData?.diameter.toString()
        textClimate.text = planetData?.climate.toString()
        textGravity.text = planetData?.gravity.toString()
        textTerrain.text = planetData?.terrain.toString()
        textSurfaceWater.text = planetData?.surfaceWater.toString()
        textPopulation.text = planetData?.population.toString()

        likes = planetData?.likes
        textLikes.text = likes.toString()
    }

    private fun loadThumbnailPicture() {
        GlideApp.with(this)
            .load(planetData?.imageUrl)
            //.thumbnail(0.5f)
            //.override(150, 150)
            .apply(RequestOptions.overrideOf(400, 400))
            .into(imgPlanet);
    }

    private fun loadLargePicture() {
        GlideApp
            .with(this)
            .load(planetData?.imageUrl)
            .apply(RequestOptions.overrideOf(1000, 1000))
            .into(imgPlanet);
    }


}
