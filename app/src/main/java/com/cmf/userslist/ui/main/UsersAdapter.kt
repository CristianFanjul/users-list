package com.cmf.userslist.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cmf.userslist.databinding.AdapterUserItemBinding
import com.cmf.userslist.misc.ImageLoader

class UsersAdapter(private val listener: OnUserClickListener) :
    RecyclerView.Adapter<UserViewHolder>() {

    private val items: MutableList<UserUiModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemBinding =
            AdapterUserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(itemBinding, listener)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user: UserUiModel = items[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int = items.size

    fun setItems(users: List<UserUiModel>) {
        items.clear()
        items.addAll(users)
        // TODO: improve adapter by updating only index
        notifyDataSetChanged()
    }

}

class UserViewHolder(
    private val itemBinding: AdapterUserItemBinding,
    private val listener: OnUserClickListener
) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(user: UserUiModel) {
        itemBinding.txtVwName.text = user.name
        itemBinding.txtVwBio.text = user.biography
        ImageLoader.loadImage(user.imageUri, itemBinding.imgVwAvatar)

        itemBinding.root.setOnClickListener {
            listener.onUserClicked(user)
        }

        itemBinding.imgVwRemove.setOnClickListener {
            listener.onUserDeleted(user)
        }
    }
}

interface OnUserClickListener {
    fun onUserClicked(user: UserUiModel)
    fun onUserDeleted(user: UserUiModel)
}