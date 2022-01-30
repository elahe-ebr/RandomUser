package com.elahe.randomuser.feature.home

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.elahe.randomuser.R
import com.elahe.randomuser.common.implementSpringAnimationTrait
import com.elahe.randomuser.data.Result
import com.elahe.randomuser.services.ImageLoadingService
import kotlinx.android.synthetic.main.user_list_item.view.*

class UserListAdapter(
    val context: Context,
    val imageLoadingService: ImageLoadingService,
) :
    RecyclerView.Adapter<UserListAdapter.ViewHolder>() {
    var userOnClickListener: UserOnClickListener? = null
    var users: MutableList<Result> = ArrayList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bind(user: Result) {
            imageLoadingService.load(itemView.userImv, user.picture.large)
            itemView.userNameTv.text = "${user.name.title} ${user.name.first} ${user.name.last}"
            itemView.genderTv.text = "${context.getString(R.string.gender)} : ${user.gender}"
            itemView.locationTv.text =
                "${user.location.country}-${user.location.state}-${user.location.city}"

            itemView.implementSpringAnimationTrait()
            itemView.setOnClickListener {
                userOnClickListener?.onClick(user)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.user_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int = users.size

    interface UserOnClickListener {
        fun onClick(user: Result)
    }
}