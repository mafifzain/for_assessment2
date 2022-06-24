package org.d3if0060.assessment2.ui.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_home.view.*
import org.d3if0060.assessment2.R
import org.d3if0060.assessment2.data.*
import org.d3if0060.assessment2.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding
    private lateinit var homeAdapter: HomeAdapter
    private lateinit var recyclerView: RecyclerView

    private val fTokoViewModel: TokoViewModel by lazy{
        ViewModelProvider(this)[TokoViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.recyclerViewHome
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            homeAdapter = HomeAdapter()
            adapter = homeAdapter
        }
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
            R.id.image_menu -> {
                findNavController().navigate(
                    R.id.action_homeFragment_to_imageFragment
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
