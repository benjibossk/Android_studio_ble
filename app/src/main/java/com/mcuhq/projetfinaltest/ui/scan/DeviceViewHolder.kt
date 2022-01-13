package com.mcuhq.projetfinaltest.ui.scan

import android.view.View
import android.widget.TextView
import com.afollestad.recyclical.ViewHolder
import com.mcuhq.projetfinaltest.R

class DeviceViewHolder(itemView: View) : ViewHolder(itemView) {
    val name: TextView = itemView.findViewById(R.id.title)
}