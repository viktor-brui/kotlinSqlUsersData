package com.example.kotlinsqlusersdata.dbHelper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.kotlinsqlusersdata.model.Persons

class DBHelper(context: Context):SQLiteOpenHelper(context,DATABASE_NAME, null, DATABASE_VER) {
    companion object{
        private val DATABASE_VER = 1
        private val DATABASE_NAME = "EDMTDB.db"


        private val TABLE_NAME = "Person"
        private val COL_ID = "Id"
        private val COL_NAME = "Name"
        private val COL_EMAIL = "Email"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE_QUERY = ("CREATE TABLE $TABLE_NAME ($COL_ID INTEGER PRIMARY KEY, $COL_NAME TEXT, $COL_EMAIL TEXT)")
        db!!.execSQL(CREATE_TABLE_QUERY)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db!!)
    }

    //CRUD
    val allPersons: List<Persons>
    get(){
        val listPersons = ArrayList<Persons>()
        val selectQuery = "SELECT * FROM $TABLE_NAME"
        val db = this.writableDatabase
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor.moveToFirst()){
            do {
                val person = Persons()
                person.id = cursor.getInt(cursor.getColumnIndex(COL_ID))
                person.name = cursor.getString(cursor.getColumnIndex(COL_NAME))
                person.email = cursor.getString(cursor.getColumnIndex(COL_EMAIL))

                listPersons.add(person)
            }while (cursor.moveToNext())
        }
        db.close()
        return listPersons
    }

    fun addPerson(person: Persons){
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_ID, person.id)
        values.put(COL_NAME, person.name)
        values.put(COL_EMAIL, person.email)

        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun updatePerson(person: Persons):Int{
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_ID, person.id)
        values.put(COL_NAME, person.name)
        values.put(COL_EMAIL, person.email)

        return db.update(TABLE_NAME, values, "$COL_ID=?", arrayOf(person.id.toString()))
    }

    fun deletePerson(person: Persons){
        val db = this.writableDatabase
        val values = ContentValues()

        db.delete(TABLE_NAME,"$COL_ID=?", arrayOf(person.id.toString()))
        db.close()
    }
}