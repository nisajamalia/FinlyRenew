package com.nisa.finlyrenew

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.nisa.finlyrenew.databinding.FragmentDokumenContentBinding
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class DokumenFragment : Fragment() {
    private val REQUEST_KTP_IMAGE_CAPTURE = 1
    private val REQUEST_SELFIE_IMAGE_CAPTURE = 2
    private val REQUEST_DOCUMENT_UPLOAD = 3
    private val REQUEST_PROPOSAL_UPLOAD = 4
    private val REQUEST_CAMERA_PERMISSION = 101
    private val REQUEST_STORAGE_PERMISSION = 102
    private val REQUEST_MEDIA_PERMISSION = 103

    private var currentImageUri: Uri? = null
    private var isTakingKtpPhoto = true
    private var _binding: FragmentDokumenContentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDokumenContentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // KTP Upload Button
        binding.uploadKtpButton.setOnClickListener {
            isTakingKtpPhoto = true
            checkAndRequestCameraPermission()
        }

        // Selfie Camera Button
        binding.captureButton.setOnClickListener {
            isTakingKtpPhoto = false
            checkAndRequestCameraPermission()
        }

        // Document Upload Button - FIXED
        binding.uploadSuratPernyataanButton.setOnClickListener {
            checkStoragePermissionAndOpenPicker()
        }

        binding.uploadSuratPernyataanButton.setOnClickListener {
            checkStoragePermissionAndOpenPicker()
        }

        binding.uploadProposalButton.setOnClickListener {
            checkStoragePermissionAndOpenPickerForProposal()
        }
    }

    private fun checkStoragePermissionAndOpenPickerForProposal() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            openDocumentPickerForProposal()
        } else {
            when {
                ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED -> {
                    openDocumentPickerForProposal()
                }
                shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE) -> {
                    showPermissionRationale(
                        "Storage Permission",
                        "Need storage access to upload proposal",
                        REQUEST_STORAGE_PERMISSION
                    )
                }
                else -> {
                    requestPermissions(
                        arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                        REQUEST_STORAGE_PERMISSION
                    )
                }
            }
        }
    }

    private fun openDocumentPickerForProposal() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "*/*" // Or specify "application/pdf" for PDF only
        }
        startActivityForResult(intent, REQUEST_PROPOSAL_UPLOAD)
    }

    private fun checkStoragePermissionAndOpenPicker() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            // Android 13+ - no permission needed for document picker
            openDocumentPicker()
        } else {
            // For older versions
            when {
                ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED -> {
                    openDocumentPicker()
                }
                shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE) -> {
                    showStoragePermissionRationale()
                }
                else -> {
                    requestPermissions(
                        arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                        REQUEST_STORAGE_PERMISSION
                    )
                }
            }
        }
    }

    private fun showStoragePermissionRationale() {
        AlertDialog.Builder(requireContext())
            .setTitle("Permission Needed")
            .setMessage("Need storage access to upload documents")
            .setPositiveButton("Allow") { _, _ ->
                requestPermissions(
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    REQUEST_STORAGE_PERMISSION
                )
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    // ==================== Camera Functions ====================
    private fun checkAndRequestCameraPermission() {
        when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED -> {
                dispatchTakePictureIntent()
            }
            shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) -> {
                showPermissionRationale(
                    "Camera Permission",
                    "This app needs camera permission to take photos of your documents",
                    REQUEST_CAMERA_PERMISSION
                )
            }
            else -> {
                requestPermissions(
                    arrayOf(Manifest.permission.CAMERA),
                    REQUEST_CAMERA_PERMISSION
                )
            }
        }
    }

    private fun dispatchTakePictureIntent() {
        try {
            Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { intent ->
                intent.resolveActivity(requireActivity().packageManager ?: return)?.also {
                    val photoFile = createImageFile()
                    val photoURI = FileProvider.getUriForFile(
                        requireContext(),
                        "${requireContext().packageName}.fileprovider",
                        photoFile
                    )
                    currentImageUri = photoURI
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(
                        intent,
                        if (isTakingKtpPhoto) REQUEST_KTP_IMAGE_CAPTURE else REQUEST_SELFIE_IMAGE_CAPTURE
                    )
                }
            }
        } catch (e: Exception) {
            Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    // ==================== Document Upload Functions ====================

    private fun openDocumentPicker() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "*/*" // All file types
            // For specific types: "application/pdf" or "application/msword"
        }
        startActivityForResult(intent, REQUEST_DOCUMENT_UPLOAD)
    }

    // ==================== Common Functions ====================
    private fun showPermissionRationale(title: String, message: String, requestCode: Int) {
        AlertDialog.Builder(requireContext())
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Allow") { _, _ ->
                requestPermissions(
                    arrayOf(
                        when (requestCode) {
                            REQUEST_CAMERA_PERMISSION -> Manifest.permission.CAMERA
                            else -> Manifest.permission.READ_EXTERNAL_STORAGE
                        }
                    ),
                    requestCode
                )
            }
            .setNegativeButton("Cancel") { dialog, _ -> dialog.dismiss() }
            .create()
            .show()
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir = requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_",
            ".jpg",
            storageDir
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_KTP_IMAGE_CAPTURE -> {
                    currentImageUri?.let { uri ->
                        val bitmap = BitmapFactory.decodeStream(
                            requireContext().contentResolver.openInputStream(uri)
                        )
                        binding.ktpPreview.setImageBitmap(bitmap)
                    }
                }
                REQUEST_SELFIE_IMAGE_CAPTURE -> {
                    currentImageUri?.let { uri ->
                        val bitmap = BitmapFactory.decodeStream(
                            requireContext().contentResolver.openInputStream(uri)
                        )
                        binding.textureView.visibility = View.GONE
                        binding.titleText.visibility = View.GONE
                        binding.captureButton.visibility = View.GONE

                        val selfieImage = ImageView(requireContext()).apply {
                            layoutParams = ViewGroup.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                170.dpToPx()
                            )
                            setImageBitmap(bitmap)
                            scaleType = ImageView.ScaleType.CENTER_CROP
                        }

                        (binding.ktpPreview.parent as ViewGroup).addView(selfieImage, 2)
                    }
                }
                REQUEST_DOCUMENT_UPLOAD -> {
                    data?.data?.let { uri ->
                        showSelectedDocument(uri)
                    }
                }

                REQUEST_PROPOSAL_UPLOAD -> {
                    data?.data?.let { uri ->
                        showSelectedProposal(uri)
                    }
                }
            }
        }
    }

    private fun showSelectedDocument(uri: Uri) {
        try {
            val fileName = getFileName(uri)

            binding.documentFileNameText.apply {
                text = fileName
                visibility = View.VISIBLE
            }

        } catch (e: Exception) {
            Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showSelectedProposal(uri: Uri) {
        try {
            val fileName = getFileName(uri)
            binding.proposalFileNameText.apply {
                text = fileName
                visibility = View.VISIBLE
            }
            // Here you would handle the actual file upload
        } catch (e: Exception) {
            Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }


    private fun getFileName(uri: Uri): String {
        var name = ""
        val cursor: Cursor? = requireContext().contentResolver.query(uri, null, null, null, null)
        cursor?.use {
            if (it.moveToFirst()) {
                val nameIndex = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                if (nameIndex != -1) {
                    name = it.getString(nameIndex)
                }
            }
        }
        return name
    }

    private fun getFileSize(uri: Uri): Long {
        var size = 0L
        val cursor: Cursor? = requireContext().contentResolver.query(uri, null, null, null, null)
        cursor?.use {
            if (it.moveToFirst()) {
                val sizeIndex = it.getColumnIndex(OpenableColumns.SIZE)
                if (sizeIndex != -1) {
                    size = it.getLong(sizeIndex)
                }
            }
        }
        return size
    }


    private fun Int.dpToPx(): Int {
        return (this * resources.displayMetrics.density).toInt()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_STORAGE_PERMISSION, REQUEST_MEDIA_PERMISSION -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openDocumentPicker()
                } else {
                    Toast.makeText(context, "Permission denied", Toast.LENGTH_SHORT).show()
                    // Optionally show explanation why permission is needed
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}