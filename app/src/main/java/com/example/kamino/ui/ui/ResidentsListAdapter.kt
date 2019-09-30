package com.example.kamino.ui.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.example.kamino.R
import java.util.ArrayList

class ResidentsListAdapter(val context: Context, val residentsList: ArrayList<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var selectedPosition: Int = -1;


    fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflatedView = parent.inflate(R.layout.resident_row, false)
        return PackagesViewHolder(inflatedView)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val pricePackage = residentsList!![position]

        val holder = viewHolder as PackagesViewHolder
        holder.bindResident(pricePackage)

        holder.container.setOnClickListener {

            //adapterOnClick(pricePackage);
            selectedPosition = position;
            notifyDataSetChanged();
        }



       /* if(selectedPosition == position){
            holder.imgCheckPackage.setBackground(context.getDrawable(R.drawable.oval_right_booked))
        } else {
            holder.imgCheckPackage.setBackground(context.getDrawable(R.drawable.oval_right_disabled))
        }*/
    }

    override fun getItemCount(): Int {
        return residentsList!!.size
    }


    internal inner class PackagesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var view: View = itemView;
        //private var resident: KaminoModel.Resident? = null

        @BindView(R.id.container)
        lateinit var container: LinearLayout
        @BindView(R.id.test_resident_name)
        lateinit var textResidentName: TextView


        init {
            view.isClickable = true
            ButterKnife.bind(this, view)
        }

        fun bindResident(resident: String) {
            textResidentName.text = resident
        }
    }
}
