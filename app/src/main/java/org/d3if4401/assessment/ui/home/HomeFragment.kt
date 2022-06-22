package org.d3if4401.assessment.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import org.d3if4401.assessment.R
import org.d3if4401.assessment.databinding.HomeFragmentBinding
import org.d3if4401.assessment.db.HewanDb
import org.d3if4401.assessment.model.Hewan


class HomeFragment : Fragment() {

    private lateinit var binding: HomeFragmentBinding

    private val viewModel: HomeViewModel by lazy {
        val db = HewanDb.getInstance(requireContext())
        val factory = HomeViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[HomeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomeFragmentBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
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

        viewModel.getHasilHewan().observe(requireActivity(), { showResult(it) })
        viewModel.data.observe(viewLifecycleOwner, {
            if (it == null) return@observe
            Log.d("HomeFragment", "Data tersimpan. ID = ${it.id}") })
    }

    private fun cari() {
        val hasil = binding.bar.text.toString()

        viewModel.hasilInput(hasil,"",0)
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

    private fun showResult(result: Hewan?) {
        val forImg = binding.bar.text.toString().lowercase()
        val imgRes = resources.getIdentifier(forImg, "drawable", "org.d3if4401.assessment")

        if (imgRes > 0) {
            binding.result.text = getString(R.string.result, result!!.nama)
            binding.imageView.setImageResource(imgRes)
            binding.buttonGroup.visibility = View.VISIBLE
        } else {
            binding.result.setText(R.string.kosong)
            binding.imageView.setImageResource(imgRes)
            binding.buttonGroup.visibility = View.INVISIBLE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_histori -> {
                findNavController().navigate(R.id.action_homeFragment_to_historiFragment)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}