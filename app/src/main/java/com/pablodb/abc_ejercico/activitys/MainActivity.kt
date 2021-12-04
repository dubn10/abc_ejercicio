package com.pablodb.abc_ejercico.activitys

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.pablodb.abc_ejercico.DataAdapter
import com.pablodb.abc_ejercico.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"
    var context : Activity? = null
    var adapter : DataAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        context = this

        initElements()
        initListeners()
    }

    fun initElements(){
        updateData()
    }

    fun initListeners(){
        btn_alta.setOnClickListener {
            ll_search.visibility = View.GONE
            ll_alta.visibility = View.VISIBLE
        }

        btn_search.setOnClickListener {
            updateData()
            ll_alta.visibility = View.GONE
            ll_search.visibility = View.VISIBLE
        }

        btn_guardar.setOnClickListener {
            MainPresenter.saveData( et_desc.text.toString(), s_estado.isChecked ){
                et_desc.setText("")
                s_estado.isChecked = true
                Toast.makeText( context, "Se guardo con exito", Toast.LENGTH_LONG).show()
            }
        }

        btn_toSearch.setOnClickListener {
            val dataset = ArrayList(MainPresenter
                .getData().filter { d -> d.description.contains( et_buscar.text.toString() ) })
            adapter = DataAdapter( context!!, dataset )
            lv_search.adapter = adapter
            adapter!!.notifyDataSetChanged()
        }
    }

    fun updateData(){
        runOnUiThread {
            et_buscar.setText("")
            adapter = DataAdapter( context!!, MainPresenter.getData() )
            lv_search.adapter = adapter
            adapter!!.notifyDataSetChanged()
        }
    }
}