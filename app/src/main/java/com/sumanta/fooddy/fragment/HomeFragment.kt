package com.sumanta.fooddy.fragment


import android.app.Activity
import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

import com.sumanta.fooddy.R
import com.sumanta.fooddy.adapter.HomeRecyclerAdapter
import com.sumanta.fooddy.model.Restaurant
import com.sumanta.fooddy.util.ConnectionManager
import kotlinx.android.synthetic.*
import org.json.JSONException

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    lateinit var recyclerHome : RecyclerView
    lateinit var layoutManager: RecyclerView.LayoutManager

    lateinit var recyclerAdapter: HomeRecyclerAdapter
    lateinit var progressLayout:RelativeLayout
    lateinit var progressBar:ProgressBar

    val restaurantInfoList = arrayListOf<Restaurant>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_home, container, false)
        progressLayout = view.findViewById(R.id.progressLayout)
        progressBar = view.findViewById(R.id.progressBar)

        progressLayout.visibility = View.VISIBLE
        layoutManager = LinearLayoutManager(activity)
        setRecycler(view)


        return view
    }

    private fun setRecycler(view: View) {
        recyclerHome = view.findViewById(R.id.homeRecyclerView) as RecyclerView

        val queue = Volley.newRequestQueue(activity as Context)
        val url = "http://13.235.250.119/v2/restaurants/fetch_result/"

           if (ConnectionManager().checkConnectivity(activity as Context)) {
              val jsonObjectRequest = object : JsonObjectRequest
                (Request.Method.GET, url,
                null, Response.Listener {

                 try {
                     progressLayout.visibility =View.GONE
                     val data = it.getJSONObject("data")
                     val success = data.getBoolean("success")

                     if (success) {
                         val resArray = data.getJSONArray("data")

                         for (i in 0 until resArray.length()) {

                             val resObject = resArray.getJSONObject(i)
                             val restaurantObject = Restaurant(
                                 resObject.getString("id").toInt(),
                                 resObject.getString("name"),
                                 resObject.getString("rating"),
                                 resObject.getString("cost_for_one"),
                                 resObject.getString("image_url")
                             )
                             restaurantInfoList.add(restaurantObject)
                             recyclerAdapter =
                                 HomeRecyclerAdapter(activity as Context, restaurantInfoList)
                             recyclerHome.adapter = recyclerAdapter
                             recyclerHome.layoutManager = layoutManager

                         }
                     } else {
                         Toast.makeText(
                             activity as Context,
                             "Some Error Occurred  !!!!", Toast.LENGTH_SHORT
                         ).show()
                     }
                 }catch (e: JSONException) {
                     Toast.makeText(
                         activity as Context,
                         "Some Error Occurred  !!!!", Toast.LENGTH_SHORT
                     ).show()
                 }
            }, Response.ErrorListener {
                Toast.makeText(
                    activity as Context,
                    "Some Error Occurred volley!!!!", Toast.LENGTH_SHORT
                ).show()

            }) {
                override fun getHeaders(): MutableMap<String, String> {
                    val headers = HashMap<String, String>()
                    headers["Content-type"] = "application/json"
                    headers["token"] = "4c3876bf8e7831"
                    return headers
                }
            }

            queue.add(jsonObjectRequest)
        } else {
            val dialog = android.app.AlertDialog.Builder(activity as Context)
            dialog.setTitle("Error")
            dialog.setMessage("Internet Connection is not Found")
            dialog.setPositiveButton("Open Settings") { text, listener ->
                val settingsIntent = Intent(Settings.ACTION_WIRELESS_SETTINGS)
                startActivity(settingsIntent)
                activity?.finish()
            }

            dialog.setNegativeButton("Exit") { text, listener ->
                ActivityCompat.finishAffinity(activity as Activity)
            }
            dialog.create()
            dialog.show()
        }
    }

}
