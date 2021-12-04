package com.pablodb.abc_ejercico.app

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class App : Application() {
    val TAG = "App"
    var realm : Realm? = null

    companion object {
        lateinit var instance: App
            private set

    }


    override fun onCreate() {
        super.onCreate()
        instance = this

        initRealm()
    }

    fun initRealm(){
        Realm.init( this )
        val config = RealmConfiguration.Builder()
            .name("kapital")
            .schemaVersion(5 )
            .deleteRealmIfMigrationNeeded()
            .allowQueriesOnUiThread(true)
            .allowWritesOnUiThread(true)
            .build()
        realm = Realm.getInstance(config)
    }
}