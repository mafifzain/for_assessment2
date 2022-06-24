package org.d3if0060.assessment2.ui.image

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.ViewModelProvider
import org.d3if0060.assessment2.R
import org.d3if0060.assessment2.data.TokoApplication
import org.d3if0060.assessment2.databinding.FragmentImageBinding
import org.d3if0060.assessment2.network.ApiStatus

class ImageFragment : Fragment() {

    private lateinit var binding : FragmentImageBinding
    private lateinit var imageAdapter: ImageAdapter
    private lateinit var notificationManagerCompat: NotificationManagerCompat

    private val viewModel: ImageViewModel by lazy {
        ViewModelProvider(this)[ImageViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentImageBinding.inflate(inflater, container, false)
        imageAdapter = ImageAdapter()
        with(binding.recyclerViewImage){
            adapter = imageAdapter
            setHasFixedSize(true)
        }
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        notificationManagerCompat = NotificationManagerCompat.from(requireActivity())

        viewModel.getData().observe(viewLifecycleOwner) {
            imageAdapter.updateData(it)
        }

        viewModel.getStatus().observe(viewLifecycleOwner) {
            updateProgress(it)
        }
    }

    private fun notifNetworkFailed(){
        val builder = NotificationCompat.Builder(requireActivity(), TokoApplication.CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_baseline_error_outline_24)
            .setContentTitle("Koneksi error")
            .setContentText("Tidak dapat terhubung dengan internet")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
        val notif = builder.build()
        notificationManagerCompat.notify(1, notif)
    }

    private fun updateProgress(status: ApiStatus) {
        when (status) {
            ApiStatus.LOADING -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            ApiStatus.SUCCESS -> {
                binding.progressBar.visibility = View.GONE
            }
            ApiStatus.FAILED -> {
                notifNetworkFailed()
                binding.progressBar.visibility = View.GONE
                binding.networkError.visibility = View.VISIBLE
            }
        }
    }
}