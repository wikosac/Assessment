package org.d3if4401.assessment.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import org.d3if4401.assessment.R
import org.d3if4401.assessment.databinding.HomeFragmentBinding


class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var binding: HomeFragmentBinding
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomeFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.search.setOnClickListener { cari() }
    }

    private fun cari() {
        val res = "HASIL PENCARIAN: "
        val kos = "HASIL TIDAK DITEMUKAN"
        val hasil = binding.bar.text.toString().uppercase()
        val forImg = binding.bar.text.toString().lowercase()
        val imgRes = resources.getIdentifier(forImg, "drawable", "org.d3if4401.assessment")

        if (imgRes > 0) {
            binding.result.setText(res + hasil)
            binding.imageView.setImageResource(imgRes)
        } else {
            binding.result.setText(kos)
            binding.imageView.setImageResource(imgRes)
        }
    }
}