package com.nisa.finlyrenew

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button


class KomunitasFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_komunitas, container, false)

        val cardKomunitas1 = view.findViewById<View>(R.id.cardKomunitasSatu)
        val btnJoin1 = cardKomunitas1.findViewById<Button>(R.id.btnJoin)

        btnJoin1.setOnClickListener {
            startActivity(Intent(requireContext(), DetailKomunitasActivity::class.java))
        }

        val cardKomunitas2 = view.findViewById<View>(R.id.cardKomunitasDua)
        val btnJoin2 = cardKomunitas2.findViewById<Button>(R.id.btnJoindua)

        btnJoin2.setOnClickListener {
            startActivity(Intent(requireContext(), DetailKomunitasActivity::class.java))
        }

        return view
    }
}
