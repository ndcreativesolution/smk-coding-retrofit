package com.example.smkcodingretrofit.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.smkcodingretrofit.POJO.NowPlaying
import com.example.smkcodingretrofit.R
import com.example.smkcodingretrofit.activities.DetailActivity
import com.example.smkcodingretrofit.utils.EndPoints
import com.example.smkcodingretrofit.utils.SquareLayout
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.nowplaying_thumbnail.view.*

class NowPlayingAdapter : RecyclerView.Adapter<NowPlayingAdapter.ViewHolder> {

    var mMovieData: List<NowPlaying.NowPlayingData>? = null
    var mContext: Context? = null

    constructor(c: FragmentActivity?, data: List<NowPlaying.NowPlayingData>?) {
        this.mContext = c
        this.mMovieData = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var inflater = LayoutInflater.from(mContext).inflate(R.layout.nowplaying_thumbnail, parent, false)
        return ViewHolder(inflater)
    }

    override fun getItemCount(): Int {
        return mMovieData!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val nowPlayingData: NowPlaying.NowPlayingData = mMovieData!![position]

        Picasso.get()
            .load(EndPoints.IMAGE_URL_POSTER + nowPlayingData.poster_path)
            .into(holder.movieThumb)

        holder.squareLayout.setOnClickListener {
            val intent = Intent(mContext?.applicationContext, DetailActivity::class.java)
            intent.putExtra("id_movie", nowPlayingData.id)
            intent.putExtra("title_movie", nowPlayingData.title)
            intent.putExtra("backdrop_movie", nowPlayingData.backdrop_path)
            intent.putExtra("overview_movie", nowPlayingData.overview)
            intent.putExtra("releasedate_movie", nowPlayingData.release_date)
            intent.putExtra("votesaverage_movie", nowPlayingData.vote_average)
            intent.putExtra("votecount_movie", nowPlayingData.vote_count)
            mContext!!.startActivity(intent)
        }
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        var movieThumb = itemView?.findViewById<View>(R.id.nowplaying_thumbnail) as ImageView
        var squareLayout = itemView?.findViewById<View>(R.id.sq_nowplaying) as SquareLayout
    }
}