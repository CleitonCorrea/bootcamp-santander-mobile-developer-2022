package com.example.news_api

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.news_api.databinding.ActivityMainBinding
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {


    //binding
    private lateinit var binding: ActivityMainBinding

    private var layoutManager: RecyclerView.LayoutManager? = null
    private  lateinit var  adapter: RecyclerAdapter;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //++++++++++++++++++++++++++++++++++++++++

        layoutManager = LinearLayoutManager(this)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.layoutManager = layoutManager


        adapter = RecyclerAdapter()
        recyclerView.adapter = adapter




    }
    //+++++++++++++++++++++++++++++++++++++++++++

    fun fetchNews()
    {
        val request = Volley.newRequestQueue(this)
        val url ="https://newsapi.org/v2/everything?q=Apple&from=2021-11-02&sortBy=popularity&apiKey=28f3351fcfe749e88e06b84c1aa660f1"
        //val url = "https://pokeapi.co/api/v2/pokemon/ditto"

        val jsonRequestNews = JsonObjectRequest(Request.Method.GET, url,null,

                Response.Listener {response->

                    try {

                        Log.v("response",""+response)

                        val objResponse: JSONObject = response
                        val arrayArticle = objResponse.getJSONArray("articles")
                        val newsArray = ArrayList<News>()

                        Log.v("newsArray",""+newsArray)

                        for (i in 0 until arrayArticle.length())
                        {
                            val article: JSONObject = arrayArticle.getJSONObject(i)


                            val news = News(
                                    article.getString("title"),
                                    article.getString("author"),
                                    article.getString("url"),
                                    article.getString("urlToImage")
                            )
                            newsArray.add(news)

                            Log.v("newsArray",""+newsArray)
                        }
                        adapter.updateNews(newsArray)
                    }
                    catch (e: JSONException)
                    {
                        Log.e("erreur",""+e)
                    }


                },
                Response.ErrorListener {
                    val toast = Toast.makeText(applicationContext, "Erreur ", LENGTH_LONG)
                    toast.show()

                })



        request.add(jsonRequestNews)

    }

}