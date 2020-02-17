package com.example.kotlinsqlusersdata.adaper

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.EditText
import com.example.kotlinsqlusersdata.R
import com.example.kotlinsqlusersdata.model.Persons
import kotlinx.android.synthetic.main.row_layout.view.*

class ListPersonAdaper(internal var activity:Activity,
                       internal var listPersons: List<Persons>,
                       internal var edt_id: EditText,
                       internal var edt_name: EditText,
                       internal var edt_email: EditText) :BaseAdapter() {
    internal var inflater:LayoutInflater

    init {
        inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }




    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rowView:View
        rowView = inflater.inflate(R.layout.row_layout, null)

        rowView.txt_row_id.text = listPersons[position].id.toString()
        rowView.txt_name.text = listPersons[position].name.toString()
        rowView.txt_email.text = listPersons[position].email.toString()

        rowView.setOnClickListener {
            edt_id.setText(rowView.txt_row_id.text.toString())
            edt_name.setText(rowView.txt_name.text.toString())
            edt_email.setText(rowView.txt_email.text.toString())
        }
        return rowView
    }

    override fun getItem(position: Int): Any {
        return listPersons[position]
    }

    override fun getItemId(position: Int): Long {
        return listPersons[position].id.toLong()
    }

    override fun getCount(): Int {
        return listPersons.size
    }


}