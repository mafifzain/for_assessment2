package org.d3if0060.assessment2.ui.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_home.view.*
import org.d3if0060.assessment2.R
import org.d3if0060.assessment2.data.TokoViewModel
import org.d3if0060.assessment2.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding
    private lateinit var fTokoViewModel: TokoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        val view = binding.root

        //recycleView
        val adapter = HomeAdapter()
        val recyclerView = view.recyclerViewHome
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //TokoViewModel
        fTokoViewModel = ViewModelProvider(this)[TokoViewModel::class.java]
        fTokoViewModel.getAllDataToko.observe(viewLifecycleOwner, Observer { entity ->
            adapter.setData(entity)
        })

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu_home, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean{
        return when (item.itemId){
            R.id.tambah_menu -> {
                findNavController().navigate(
                    R.id.action_homeFragment_to_additemFragment
                )
                return true
            }
            R.id.about_menu -> {
                findNavController().navigate(
                    R.id.action_homeFragment_to_aboutFragment
                )
                return true
            } else -> super.onOptionsItemSelected(item)
        }
    }
}
