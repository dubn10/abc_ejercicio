package com.pablodb.abc_ejercico.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Data (@PrimaryKey var id : Int = 0,
                 var description : String = "",
                 var status : Boolean = true ) : RealmObject()