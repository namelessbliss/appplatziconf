package com.nb.appplatziconf.view.ui.fragment

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.nb.appplatziconf.R
import com.nb.appplatziconf.model.Ubication

/**
 * A simple [Fragment] subclass.
 */
class UbicationDetailDialogFragment : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
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
        val toolbar: Toolbar = view.findViewById(R.id.toolbar)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.navigationIcon = ContextCompat.getDrawable(view.context, R.drawable.ic_close)
            toolbar.setTitleTextColor(Color.WHITE)
            toolbar.setNavigationOnClickListener {
                dismiss()
            }
        }

        val ubication = Ubication()
        toolbar.title = ubication.name

        val tvDetailNombreLugar = view.findViewById<TextView>(R.id.tvDetailNombreLugar)
        tvDetailNombreLugar.text = ubication.name
        val tvUbicationDireccion = view.findViewById<TextView>(R.id.tvUbicationDireccion)
        tvUbicationDireccion.text = ubication.address
        val tvUbicationTelefono = view.findViewById<TextView>(R.id.tvUbicationTelefono)
        tvUbicationTelefono.text = ubication.phone
        val tvUbicationWebsite = view.findViewById<TextView>(R.id.tvUbicationWebsite)
        tvUbicationWebsite.text = ubication.website

        val llTelefonoLugar = view.findViewById<LinearLayout>(R.id.llTelefonoLugar)
        llTelefonoLugar.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:${ubication.phone}")
            }
            startActivity(intent)
        }
        val llSitioWeb = view.findViewById<LinearLayout>(R.id.llSitioWeb)
        llSitioWeb.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(ubication.website)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }
}