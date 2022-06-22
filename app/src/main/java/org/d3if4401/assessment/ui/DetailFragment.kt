package org.d3if4401.assessment.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.navArgs
import org.d3if4401.assessment.R
import org.d3if4401.assessment.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)

        var args = DetailFragmentArgs.fromBundle(requireArguments())
        binding.imageDetail.setImageResource(args.imgDetail)
        binding.textDetail.text = args.namaDetail
        return binding.root
    }
}