package org.d3if4401.assessment.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import org.d3if4401.assessment.R
import org.d3if4401.assessment.databinding.HomeFragmentBinding


class HomeFragment : Fragment() {

    private lateinit var binding: HomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomeFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val hasil = binding.bar.text.toString().uppercase()
        val forImg = binding.bar.text.toString().lowercase()
        val imgRes = resources.getIdentifier(forImg, "drawable", "org.d3if4401.assessment")

        binding.search.setOnClickListener { cari() }
        binding.detailButton.setOnClickListener {
            view.findNavController().navigate(
                HomeFragmentDirections.actionHomeToDetail(hasil, imgRes) )
        }
        binding.shareButton.setOnClickListener { shareData() }
    }

    private fun cari() {
        val hasil = binding.bar.text.toString().uppercase()
        val forImg = binding.bar.text.toString().lowercase()
        val imgRes = resources.getIdentifier(forImg, "drawable", "org.d3if4401.assessment")

        if (imgRes > 0) {
            binding.result.text = getString(R.string.result, hasil)
            binding.imageView.setImageResource(imgRes)
            binding.buttonGroup.visibility = View.VISIBLE
        } else {
            binding.result.setText(R.string.kosong)
            binding.imageView.setImageResource(imgRes)
            binding.buttonGroup.visibility = View.INVISIBLE
        }
    }

    private fun shareData() {
        val message = getString(
            R.string.bagikan_template,
            binding.bar.text
        )
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType ("text/plain").putExtra(Intent.EXTRA_TEXT, message)
        if (shareIntent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(shareIntent)
        }
    }
}