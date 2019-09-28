package com.example.kamino.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kamino.R
import com.example.kamino.restconnection.KaminoApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    val kaminoApiservice by lazy {
        KaminoApiService.create()
    }
    var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       getKaminoPlanet();
    }

    override fun onDestroy() {
        disposable?.dispose()
        super.onDestroy()
    }


    private fun getKaminoPlanet() {
        disposable =
            kaminoApiservice.getKaminoPlanet()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { result ->
                        System.out.println(result)
                    },
                    { error ->
                        System.out.println(error)
                    }
                )
    }
}
