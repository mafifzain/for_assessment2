package org.d3if0060.assessment2.ui.additem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import org.d3if0060.assessment2.R
import org.d3if0060.assessment2.data.TokoEntity
import org.d3if0060.assessment2.data.TokoViewModel
import org.d3if0060.assessment2.databinding.FragmentAdditemBinding

class AdditemFragment : Fragment(){

    private lateinit var fTokoViewModel: TokoViewModel
    private lateinit var binding: FragmentAdditemBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAdditemBinding.inflate(inflater, container, false)
        val view = binding.root

        fTokoViewModel = ViewModelProvider(this).get(TokoViewModel::class.java)

        binding.submitBtn.setOnClickListener {
            insertData()
        }

        return view
    }

    private fun insertData(){
        val namaToko = binding.editTextNamaToko.text.toString()
        val alamatToko = binding.editTextAlamatToko.text.toString()
        val deskripsiToko = binding.editTextDeskripsiToko.text.toString()

        if (checkData(namaToko, alamatToko, deskripsiToko)){
            val data = TokoEntity(0, namaToko, alamatToko, deskripsiToko)
            fTokoViewModel.addToko(data)
            Toast.makeText(requireContext(),"Berhasil ditambahkan!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_additemFragment_to_homeFragment)
        }else{
            Toast.makeText(requireContext(),"Harap mengisi semua kolom!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkData(
        namaToko: String,
        alamatToko: String,
        deskripsiToko: String
    ): Boolean {
        if (
            namaToko.isBlank() || alamatToko.isBlank() || deskripsiToko.isBlank()
        ){
            return false
        }
        return true
    }
}