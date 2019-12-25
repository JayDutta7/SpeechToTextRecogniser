package com.jdhome.mvvmkotlin.ui.activity.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.speech.RecognizerIntent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.jdhome.mvvmkotlin.R
import com.jdhome.mvvmkotlin.networking.WebService
import com.jdhome.mvvmkotlin.networking.model.ResponseData
import com.jdhome.mvvmkotlin.ui.adapter.DictionaryAdapter
import com.jdhome.mvvmkotlin.utility.UtilComparison
import com.jdhome.mvvmkotlin.viewmodel.home.HomeViewModel
import kotlinx.android.synthetic.main.activity_home.*
import timber.log.Timber
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


class HomeActivity : AppCompatActivity() {


    private lateinit var homeViewModel: HomeViewModel
    private lateinit var parameters: HashMap<Int, String>
    private lateinit var dictionarydapter: DictionaryAdapter

    private lateinit var tempData: ArrayList<ResponseData>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        parameters = HashMap()

        parameters[0] = WebService.dictionary

        //creates a vertical Layout Manager
        recyclerView_Dictonary.layoutManager = LinearLayoutManager(this)



        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        homeViewModel.getAllImages(
            parameters
        )



        homeViewModel.mutableLiveData.observe(this, Observer {

            Timber.e("""Size${it?.size}""")
            if (it.isNotEmpty())
                tempData = it
            //set Value
            dictionarydapter = DictionaryAdapter(context = this, apiData = it)
            recyclerView_Dictonary.adapter = dictionarydapter

            //Notify Adapter
            (recyclerView_Dictonary.adapter as DictionaryAdapter).notifyDataSetChanged()


            id_CardSpeak.setOnClickListener { _: View? ->
                val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
                //val extras = intent.extras
                intent.putExtra(
                    RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                    RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
                )
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault().toString())

                /*for (i in 0 until it.size) {
                    intent.putExtra(
                        RecognizerIntent.ACTION_GET_LANGUAGE_DETAILS,
                        arrayListOf(it[i].word + " " + it[i].frequency)
                    )
                }*/

                if (intent.resolveActivity(packageManager) != null) {


                    startActivityForResult(intent, 0)//extras


                } else {
                    Toast.makeText(
                        this,
                        "Your Device Don't Support Speech Input",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        })

        //show Progress Loader
        homeViewModel.isLoadingMutableLiveData.observe(this, Observer {

            if (it)
                progress_Bar.visibility = View.VISIBLE
            else
                progress_Bar.visibility = View.GONE

        })


    }//end of onCreate


    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            0 -> if (resultCode == Activity.RESULT_OK && data != null) {

                // val defaultValue =    data.getStringArrayListExtra(RecognizerIntent.ACTION_GET_LANGUAGE_DETAILS)
                val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                if (tempData.isNotEmpty()) {
                    for (i in 0 until tempData.size) {
                        while (UtilComparison.binarySearch(
                                mutableListOf(tempData[i].word?.trim()!!.toLowerCase(Locale.US)),
                                result[0].trim().toLowerCase(Locale.US)
                            ) != -1
                        ) {
                            dictionarydapter.setFilter(result[0], tempData[i].word, i)
                            break
                        }

                    }

                } else {
                    Timber.e("LogData is empty")
                }


            } else {
                Timber.e("Data is Null")
            }

            else -> {

            }

        }
    }
}



