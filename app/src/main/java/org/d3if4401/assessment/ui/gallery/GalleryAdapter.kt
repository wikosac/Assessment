package org.d3if4401.assessment.ui.gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.d3if4401.assessment.model.Hewan
import org.d3if4401.assessment.R
import org.d3if4401.assessment.databinding.ListItemBinding
import org.d3if4401.assessment.model.Gallery
import org.d3if4401.assessment.network.HewanApi

class GalleryAdapter : RecyclerView.Adapter<GalleryAdapter.ViewHolder>() {

    private val data = mutableListOf<Gallery>()

    fun updateData(newData: List<Gallery>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    class ViewHolder(
        private val binding: ListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(hewan: Gallery) = with(binding) {
            namaTextView.text = hewan.nama
            latinTextView.text = hewan.namaLatin
            Glide.with(imageView.context)
                .load(HewanApi.getHewanUrl(hewan.imageId))
                .error(R.drawable.ic_baseline_broken_image_24)
                .into(imageView)

            root.setOnClickListener {
                val message = root.context.getString(R.string.message, hewan.nama)
                Toast.makeText(root.context, message, Toast.LENGTH_LONG).show() }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
