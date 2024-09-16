package com.jdgohel.myapplication

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

object DB_CONSTANTS {
    const val TABLE_ID = "id"
    const val TABLE_NAME = "contacts"
    const val NAME_COLUMN = "name"
    const val NUMBER_COLUMN = "number"

}

class SqlDataBase(context: Context) : SQLiteOpenHelper(context, DB_CONSTANTS.TABLE_NAME, null, 1) {

    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("CREATE TABLE ${DB_CONSTANTS.TABLE_NAME} (${DB_CONSTANTS.TABLE_ID} INTEGER PRIMARY KEY AUTOINCREMENT, ${DB_CONSTANTS.NAME_COLUMN} TEXT , ${DB_CONSTANTS.NUMBER_COLUMN} TEXT)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    fun insertData(contactModel: ContactModel){
        val db = this.writableDatabase
        val value = ContentValues()

        value.put(DB_CONSTANTS.NAME_COLUMN, contactModel.name)
        value.put(DB_CONSTANTS.NUMBER_COLUMN, contactModel.number)

        db.insert(DB_CONSTANTS.TABLE_NAME,  null ,  value)
        db.close()
    }

    fun getAllData():ArrayList<ContactModel>{
        val list = ArrayList<ContactModel>()

        val db = this.readableDatabase
        val result = db.rawQuery("SELECT * FROM ${DB_CONSTANTS.TABLE_NAME}",null , null)

        while (result.moveToNext()){
            val id = result.getInt(0)
            val name = result.getString(1)
            val number = result.getString(2)
            list.add(ContactModel(id, name, number))
        }

        db.close()

        return  list
    }

    fun deleteData(id: Int){
        val db = this.writableDatabase
        db.delete(DB_CONSTANTS.TABLE_NAME, "${DB_CONSTANTS.TABLE_ID} = ?" , arrayOf(id.toString()))
        db.close()
    }

    fun updateData(contactModel: ContactModel){
        val db = this.writableDatabase
        val value = ContentValues()
        value.put(DB_CONSTANTS.NAME_COLUMN, contactModel.name)
        value.put(DB_CONSTANTS.NUMBER_COLUMN, contactModel.number)
        db.update(DB_CONSTANTS.TABLE_NAME,value, "${DB_CONSTANTS.TABLE_ID} = ?" , arrayOf(contactModel.id.toString()))

    }

}