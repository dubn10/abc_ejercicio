package com.pablodb.abc_ejercico.activitys

import android.util.Log
import com.pablodb.abc_ejercico.app.App
import com.pablodb.abc_ejercico.model.Data

class MainPresenter {
    companion object{
        val TAG = "MainPresenter"

        fun saveData( desc : String, status : Boolean, next : () -> Unit ){
            App.instance.realm!!.beginTransaction()
            var max = App.instance.realm!!.where(Data::class.java).max("id")
            max = (if( max == null ) 0 else max.toString().toInt())+1
            App.instance.realm!!.copyToRealmOrUpdate( Data( max, desc, status ) )
            App.instance.realm!!.commitTransaction()

            next(  )
        }

        fun updateData( data : Data, next : () -> Unit ){
            App.instance.realm!!.beginTransaction()
            App.instance.realm!!.copyToRealmOrUpdate( data )
            App.instance.realm!!.commitTransaction()

            next(  )
        }

        fun removeData( data : Data, next : () -> Unit ){
            App.instance.realm!!.beginTransaction()
            data.deleteFromRealm()
            App.instance.realm!!.commitTransaction()

            next(  )
        }

        fun getData( ) : ArrayList<Data>{
            val res = ArrayList(App.instance.realm!!.where( Data::class.java ).findAll().toList())
            Log.i(TAG, "res = ${res.size}")
            return res
        }
    }
}