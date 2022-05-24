package org.d3if0060.assessment2.ui.viewitem

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.fragment_viewitem.view.*
import org.d3if0060.assessment2.R
import org.d3if0060.assessment2.data.TokoViewModel
import org.d3if0060.assessment2.databinding.FragmentViewitemBinding

class ViewitemFragment : Fragment() {

    private lateinit var binding : FragmentViewitemBinding
    private lateinit var fTokoViewModel: TokoViewModel
    private val args by navArgs<ViewitemFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentViewitemBinding.inflate(inflater, container, false)
        val view = binding.root

        fTokoViewModel = ViewModelProvider(this).get(TokoViewModel::class.java)

        view.namaTokoViewItem.setText(args.currToko.namaToko)
        view.alamatTokoViewItem.setText(args.currToko.alamatToko)
        view.deskripsiViewItem.setText(args.currToko.deskripsiToko)

        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu_viewitem, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.deleteToko){
            actionDeleteToko()
        } else if(item.itemId == R.id.shareToko) {
            actionShareToko()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun actionDeleteToko(){
        MaterialAlertDialogBuilder(requireContext())
            .setMessage("Apakah anda yakin menghapus toko ini?")
            .setPositiveButton("Ya"){ _, _ ->
                fTokoViewModel.deleteToko(args.currToko)
                findNavController().navigate(R.id.action_viewitemFragment_to_homeFragment)
            }
            .setNegativeButton("Tidak"){ dialog, _ ->
                dialog.cancel()
            }
            .show()
    }

    private fun actionShareToko(){
        val message = getString(R.string.share_template,
            args.currToko.namaToko,
            args.currToko.alamatToko,
            args.currToko.deskripsiToko
        )

        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT, message)
        if (shareIntent.resolveActivity(requireActivity().packageManager) != null){
            startActivity(shareIntent)
        }
    }
}