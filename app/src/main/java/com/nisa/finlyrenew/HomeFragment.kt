package com.nisa.finlyrenew

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import com.nisa.finlyrenew.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private lateinit var btnPengajuan: MaterialButton
    private lateinit var btnLearning: MaterialButton
    private lateinit var btnKelola: MaterialButton
    private lateinit var btnKonsultasi: MaterialButton
    private lateinit var btnDetail: TextView
    private lateinit var btnProfilePage: ImageButton

    companion object {
        fun defaultFragment(): HomeFragment {
            val home_fragment = HomeFragment()
            home_fragment.arguments = Bundle()
            return home_fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // Inisialisasi dengan tipe yang benar
        btnPengajuan = view.findViewById(R.id.btnPengajuan)
        btnLearning = view.findViewById(R.id.btnLearning)
        btnKelola = view.findViewById(R.id.btnKelola)
        btnKonsultasi = view.findViewById(R.id.btnKonsultasi)
        btnDetail = view.findViewById(R.id.btnCekDetail)
        btnProfilePage = view.findViewById(R.id.btnProfile)

        btnPengajuan.setOnClickListener {
            val intent = Intent(requireContext(), PengajuanActivity::class.java)
            startActivity(intent)
        }



        btnLearning.setOnClickListener {
            val intent = Intent(requireContext(), LearningActivity::class.java)
            startActivity(intent)
        }

        btnKelola.setOnClickListener {
            val intent = Intent(requireContext(), KelolaActivity::class.java)
            startActivity(intent)
        }

        btnKonsultasi.setOnClickListener {
            val intent = Intent(requireContext(), KonsultasiActivity::class.java)
            startActivity(intent)
        }
        btnDetail.setOnClickListener {
            val intent = Intent(requireContext(), DetailActivity::class.java)
            startActivity(intent)
        }

        btnProfilePage.setOnClickListener {
            val intent = Intent(requireContext(), ProfileActivity::class.java)
            startActivity(intent)
        }

    }
}
