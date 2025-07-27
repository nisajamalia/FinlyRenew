package com.nisa.finlyrenew

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.nisa.finlyrenew.databinding.FragmentPinjamanBinding

class PinjamanFragment : Fragment() {
    private var _binding: FragmentPinjamanBinding? = null
    private val binding get() = _binding!!

    private val loanTerms = arrayOf("3 bulan", "6 bulan", "9 bulan", "12 bulan")

    private val contractTypes = arrayOf("Musyarakah", "Mudharabah")

    private val bankNames = arrayOf("BCA", "BSI", "Mandiri", "BNI", "BRI")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPinjamanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupDropdowns()

        binding.submitButton.setOnClickListener {
            validateAndSubmit()
        }
    }

    private fun setupDropdowns() {
        // Loan term dropdown
        val termAdapter = ArrayAdapter(
            requireContext(),
            R.layout.dropdown_menu_item,
            loanTerms
        )
        (binding.loanTermDropdown as? AutoCompleteTextView)?.setAdapter(termAdapter)

        // Contract type dropdown
        val contractAdapter = ArrayAdapter(
            requireContext(),
            R.layout.dropdown_menu_item,
            contractTypes
        )
        (binding.contractTypeDropdown as? AutoCompleteTextView)?.setAdapter(contractAdapter)

        val bankAdapter = ArrayAdapter(
            requireContext(),
            R.layout.dropdown_menu_item,
            bankNames
        )
        (binding.bankDropdown as? AutoCompleteTextView)?.setAdapter(bankAdapter)
    }

    private fun validateAndSubmit() {
        val amount = binding.amountEditText.text.toString()
        val term = binding.loanTermDropdown.text.toString()
        val description = binding.descriptionEditText.text.toString()
        val contractType = binding.contractTypeDropdown.text.toString()
        val bankName = binding.bankDropdown.text.toString()
        val termsAccepted = binding.termsCheckbox.isChecked

        if (amount.isEmpty()) {
            Toast.makeText(requireContext(), "Masukkan jumlah pinjaman", Toast.LENGTH_SHORT).show()
            return
        }

        if (term.isEmpty()) {
            Toast.makeText(requireContext(), "Pilih jangka waktu pengembalian", Toast.LENGTH_SHORT).show()
            return
        }

        if (description.isEmpty()) {
            Toast.makeText(requireContext(), "Masukkan deskripsi usaha", Toast.LENGTH_SHORT).show()
            return
        }

        if (contractType.isEmpty()) {
            Toast.makeText(requireContext(), "Pilih jenis akad", Toast.LENGTH_SHORT).show()
            return
        }

        if (bankName.isEmpty()) {
            Toast.makeText(requireContext(), "Pilih Bank", Toast.LENGTH_SHORT).show()
            return
        }

        if (!termsAccepted) {
            Toast.makeText(requireContext(), "Anda harus menyetujui syarat dan ketentuan", Toast.LENGTH_SHORT).show()
            return
        }

        // Here you would typically submit to your backend
        Toast.makeText(
            requireContext(),
            "Pengajuan berhasil!\nJumlah: Rp$amount\nTenor: $term\nAkad: $contractType",
            Toast.LENGTH_LONG
        ).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}