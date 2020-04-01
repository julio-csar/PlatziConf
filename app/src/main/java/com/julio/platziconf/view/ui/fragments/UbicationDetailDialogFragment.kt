package com.julio.platziconf.view.ui.fragments

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

import com.julio.platziconf.R
import com.julio.platziconf.model.Ubication
import kotlinx.android.synthetic.main.fragment_ubication_detail_dialog.*

/**
 * A simple [Fragment] subclass.
 */
class UbicationDetailDialogFragment : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL,R.style.FullScreenDialogStyle)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ubication_detail_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbarUbicationDetailToolbar.navigationIcon =
            ContextCompat.getDrawable(view.context, R.drawable.ic_close);
        toolbarUbicationDetailToolbar.setTitleTextColor(Color.WHITE);
        toolbarUbicationDetailToolbar.setNavigationOnClickListener {
            dismiss()
        }


        val ubication = Ubication()

        toolbarUbicationDetailToolbar.title = ubication.name
        tvUbicationDetailTitle.text = ubication.name
        tvUbicationDetailConference.text=ubication.address
        tvUbicationDetailPhone.text = ubication.phone
        tvUbicationDetailLink.text = ubication.website

        Glide.with(this)
            .load(ubication.photo)
            .into(ivUbicationDetailBanner)

        lyUbicationDetailPhone.setOnClickListener{
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:${ubication.phone}")
            }
            startActivity(intent)
        }
        lyUbicationDetailWebsite.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(ubication.website)
            }
            startActivity(intent)
        }
    }
    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
    }
}
