package org.d3if4401.assessment.ui.gallery

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.d3if4401.assessment.R
import org.d3if4401.assessment.databinding.GalleryFragmentBinding
import org.d3if4401.assessment.databinding.HomeFragmentBinding
import org.d3if4401.assessment.model.Hewan

class GalleryFragment : Fragment() {

    companion object {
        fun newInstance() = GalleryFragment()
    }

    private val viewModel: GalleryViewModel by lazy {
        ViewModelProvider(this).get(GalleryViewModel::class.java)
    }

    private lateinit var binding: GalleryFragmentBinding
    private lateinit var myAdapter: GalleryAdapter
    private var isLinearLayoutManager = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = GalleryFragmentBinding.inflate(layoutInflater, container, false)
        myAdapter = GalleryAdapter()
        with(binding.recyclerView) {
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    RecyclerView.VERTICAL
                )
            )
            adapter = myAdapter
            setHasFixedSize(true)
        }
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getData().observe(viewLifecycleOwner, {
            myAdapter.updateData(it)
        })
    }

    private fun chooseLayout() {
        if (isLinearLayoutManager) {
            binding.recyclerView.layoutManager =
                LinearLayoutManager(this.requireContext())
        } else {
            binding.recyclerView.layoutManager =
                GridLayoutManager(this.requireContext(), 2)
        }
    }

    private fun setIcon(menuItem: MenuItem?) {
        if (menuItem == null) return
        menuItem.icon =
            if (isLinearLayoutManager)
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_baseline_view_module_24
                )
            else ContextCompat.getDrawable(
                requireContext(),
                R.drawable.ic_baseline_view_list_24
            )
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.layout_menu, menu)
        val layoutButton = menu?.findItem(R.id.action_switch_layout)
        setIcon(layoutButton)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_switch_layout -> {
// Sets isLinearLayoutManager to the opposite value
                isLinearLayoutManager = !isLinearLayoutManager
// Sets layout and icon
                chooseLayout()
                setIcon(item)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}