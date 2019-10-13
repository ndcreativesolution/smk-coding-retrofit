package com.example.smkcodingretrofit.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.smkcodingretrofit.POJO.Upcoming
import com.example.smkcodingretrofit.R
import com.example.smkcodingretrofit.adapter.UpcomingAdapter
import com.example.smkcodingretrofit.utils.EndPoints
import com.example.smkcodingretrofit.utils.InitRetrofit
import kotlinx.android.synthetic.main.fragment_upcoming.*
import retrofit2.Call
import retrofit2.Callback

class UpcomingFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_upcoming, container, false)

        var swipe = view.findViewById<View>(R.id.refresh_upcoming) as SwipeRefreshLayout
        swipe.setOnRefreshListener {
            swipe.isRefreshing = false
            getUpcomingData()
        }

        getUpcomingData()
        return view
    }

    private fun getUpcomingData() {

        var api = InitRetrofit().getInitInstance()
        var call = api.requestUpcoming(EndPoints.API_KEY)
        call.enqueue(object : Callback<Upcoming> {

            override fun onFailure(call: Call<Upcoming>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<Upcoming>?, response: retrofit2.Response<Upcoming>?) {
                if (response != null) {
                    if (response.isSuccessful) {
                        var data = response.body()?.data
                        val adapter = UpcomingAdapter(activity, data)
                        rv_upcoming.adapter = adapter
                        rv_upcoming.layoutManager = GridLayoutManager(activity, 2)
                    }
                }
            }

        })

    }
}