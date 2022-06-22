package org.d3if4401.assessment.ui.histori

import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.d3if4401.assessment.R
import org.d3if4401.assessment.databinding.ItemHistoriBinding
import org.d3if4401.assessment.db.HewanEntity
import java.text.SimpleDateFormat
import java.util.*

class HistoriAdapter :
    ListAdapter<HewanEntity, HistoriAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<HewanEntity>() {
            override fun areItemsTheSame(
                oldData: HewanEntity, newData: HewanEntity
            ): Boolean {
                return oldData.id == newData.id
            }

            override fun areContentsTheSame(
                oldData: HewanEntity, newData: HewanEntity
            ): Boolean {
                return oldData == newData
            }
        }
    }

    class ViewHolder(
        private val binding: ItemHistoriBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val dateFormatter = SimpleDateFormat(
            "dd MMMM yyyy",
            Locale("id", "ID")
        )

        fun bind(item: HewanEntity) = with(binding) {
            tanggalTextView.text = dateFormatter.format(Date(item.tanggal))
            HewanTextView.text = root.context.getString(R.string.hasil_x, item.nama)
            dataTextView.text = root.context.getString(R.string.data_x, item.latin)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHistoriBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}