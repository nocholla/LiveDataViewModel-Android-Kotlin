package com.nocholla.livedata.viewmodel.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nocholla.livedata.viewmodel.R
import com.nocholla.livedata.viewmodel.model.Blog

class BlogAdapter(private val blogList: List<Blog>) : RecyclerView.Adapter<BlogAdapter.ViewHolder>() {

    override fun getItemCount()=blogList.size

    private var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        this.context=parent.context;

        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.blog_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val blog = blogList[position]

        if (blog.thumbnail != null) {
            context?.let {
                Glide.with(it)
                    .load(blog.thumbnail)
                    .into(holder.ivThumbnail)
            }
        }
        if (blog.title != null) {
            holder.tvTitle.text = blog.title
        }

        if (blog.description != null) {
            holder.tvDescription.text = blog.description;
        }

        if (blog.link != null) {
            holder.tvLink.text = blog.link
        }

        holder.tvLink.setOnClickListener{
            if (blog.link != null) {
                try {
                    val intent = Intent()
                    intent.action = Intent.ACTION_VIEW
                    intent.addCategory(Intent.CATEGORY_BROWSABLE)
                    intent.data = Uri.parse(blog.link)
                    context?.startActivity(intent)
                } catch (e:Exception ) {

                }
            }
        }

    }

    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val ivThumbnail:ImageView = itemView.findViewById(R.id.ivThumbnail)
        val tvTitle:TextView = itemView.findViewById(R.id.tvTitle)
        val tvDescription:TextView = itemView.findViewById(R.id.tvDescription)
        val tvLink:TextView = itemView.findViewById(R.id.tvLink)
    }

}