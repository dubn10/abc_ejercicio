package com.pablodb.abc_ejercico

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import com.pablodb.abc_ejercico.activitys.MainActivity
import com.pablodb.abc_ejercico.activitys.MainPresenter
import com.pablodb.abc_ejercico.model.Data

class DataAdapter(context : Activity, dataSet : ArrayList<Data>) : BaseAdapter(){
    private var context : Context
    private var dataSet : List<Data>

    init {
        this.context = context
        this.dataSet = dataSet
    }

    override fun getCount(): Int {
        return dataSet.size
    }

    override fun getItem(p0: Int): Data {
        return dataSet[p0]
    }

    override fun getItemId(p0: Int): Long {
        return dataSet[p0].id.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var v = p1
        var data = getItem( p0 )
        if ( v == null) {
            v = LayoutInflater.from(context).inflate(R.layout.element_list_data, p2, false)
        }

        val id = v!!.findViewById<TextView>(R.id.tv_id)
        val desc = v!!.findViewById<EditText>(R.id.et_desc)
        val estado = v!!.findViewById<SwitchCompat>(R.id.s_estado)
        val editar = v!!.findViewById<Button>(R.id.btn_editar)
        val guardar = v!!.findViewById<Button>(R.id.btn_guardar)
        val eliminar = v!!.findViewById<Button>(R.id.btn_eliminar)

        id.setText( data.id.toString() )
        desc.setText(data.description)
        desc.isEnabled = false
        estado.isChecked = data.status
        estado.isEnabled = false

        editar.setOnClickListener {
            editar.visibility = View.GONE
            guardar.visibility = View.VISIBLE
            desc.isEnabled = true
            estado.isEnabled = true
        }

        guardar.setOnClickListener {
            MainPresenter.updateData( Data( data.id, desc.text.toString(), estado.isChecked ) ){
                guardar.visibility = View.GONE
                editar.visibility = View.VISIBLE
                (context as MainActivity).updateData()
            }
        }

        eliminar.setOnClickListener {
            MainPresenter.removeData( data ){
                (context as MainActivity).updateData()
            }
        }

        return v!!
    }
}