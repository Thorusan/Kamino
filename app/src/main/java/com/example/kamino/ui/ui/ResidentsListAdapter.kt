package com.example.kamino.ui.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.example.kamino.R
import com.google.android.material.card.MaterialCardView
import java.util.*

class ResidentsListAdapter(
    val context: Context,
    val residentsList: ArrayList<String>,
    val onChooseResident: (String) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var selectedPosition: Int = -1;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.resident_row, parent, false)
        return ResidentsViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val resident = residentsList!![position]

        val holder = viewHolder as ResidentsViewHolder
        holder.bindResident(resident)

        holder.container.setOnClickListener {
            onChooseResident(resident);
            selectedPosition = position;
            notifyDataSetChanged();
        }

        highlightSelectedRow(position, holder)

    }

    private fun highlightSelectedRow(
        position: Int,
        holder: ResidentsViewHolder
    ) {
        if (getSelectedPosition() == position) {
            holder.container.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.color_selected
                )
            )    // selected
        } else {
            holder.container.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.color_normal
                )
            ) // not selected
        }
    }

    fun getSelectedPosition(): Int {
        return selectedPosition
    }

    fun setSelectedPosition(pos: Int) {
        selectedPosition = pos
    }


    override fun getItemCount(): Int {
        return residentsList.size
    }


    internal inner class ResidentsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @BindView(R.id.container)
        lateinit var container: MaterialCardView
        @BindView(R.id.text_resident_name)
        lateinit var textResidentName: TextView

        private var view: View = itemView;

        init {
            view.isClickable = true
            ButterKnife.bind(this, view)
        }

        fun bindResident(resident: String) {
            textResidentName.text = resident
        }
    }
}
