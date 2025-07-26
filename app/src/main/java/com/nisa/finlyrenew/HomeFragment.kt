package com.nisa.finlyrenew

import PengajuanFragment
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.nisa.finlyrenew.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    companion object {
        fun defaultFragment(): HomeFragment {
            val home_fragment = HomeFragment()
            //ngirim ke oncreate
            val bundle = Bundle()
            //arguments default function u ngirim data
            home_fragment.arguments = bundle
            return home_fragment
        }
    }
    private lateinit var btnPengajuan: LinearLayout
    private lateinit var btnKomunitas: LinearLayout
    private lateinit var btnKelola: LinearLayout
    private lateinit var btnKonsultasi: LinearLayout

    private var _binding: FragmentHomeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        btnPengajuan = view.findViewById(R.id.btnpengajuan)
        btnKomunitas = view.findViewById(R.id.btnkomunitas)
        btnKelola = view.findViewById(R.id.btnkelola)
        btnKonsultasi = view.findViewById(R.id.btnkonsultasi)

        btnPengajuan.setOnClickListener {
            val intent = Intent(requireContext(), PengajuanFragment::class.java)
            startActivity(intent)
        }

        btnKomunitas.setOnClickListener {
            val intent = Intent(requireContext(), KomunitasFragment::class.java)
            startActivity(intent)
        }

        btnKelola.setOnClickListener {
            val intent = Intent(requireContext(), KomunitasFragment::class.java)
            startActivity(intent)
        }

        btnKonsultasi.setOnClickListener {
            Toast.makeText(requireContext(), "Konsultasi diklik", Toast.LENGTH_SHORT).show()
            val intent = Intent(requireContext(), DetailActivity::class.java)
            startActivity(intent)
        }
    }
}