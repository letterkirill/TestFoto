package com.example.test071121.ui.foto_view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.test071121.R

class FotoFragment: Fragment() {

    companion object{
        private const val EXTRA_URL = "EXTRA_URL"

        fun newInstance(url: String): FotoFragment {
            val args = Bundle()
            args.putString(EXTRA_URL, url)

            val fragment = FotoFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_foto, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val imageFoto = view.findViewById<ImageView>(R.id.image_full_foto)

        Glide.with(imageFoto.context)
            .load(arguments?.get(EXTRA_URL) as String?)
            .placeholder(R.drawable.ic_baseline_insert_photo_24)
            .error(R.drawable.ic_baseline_error_outline_24)
            .centerCrop()
            .into(imageFoto)
    }
}