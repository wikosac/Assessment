package org.d3if4401.assessment.ui.gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import org.d3if4401.assessment.model.Hewan
import org.d3if4401.assessment.R
import org.d3if4401.assessment.databinding.ListItemBinding

class GalleryAdapter : RecyclerView.Adapter<GalleryAdapter.ViewHolder>() {

    private val data = mutableListOf<Hewan>()

    fun updateData(newData: List<Hewan>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    class ViewHolder(
        private val binding: ListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(hewan: Hewan) = with(binding) {
            namaTextView.text = hewan.nama
            latinTextView.text = hewan.namaLatin
            imageView.setImageResource(hewan.imageId)

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
