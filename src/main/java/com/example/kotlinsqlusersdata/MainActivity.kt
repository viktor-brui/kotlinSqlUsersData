package com.example.kotlinsqlusersdata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlinsqlusersdata.adaper.ListPersonAdaper
import com.example.kotlinsqlusersdata.dbHelper.DBHelper
import com.example.kotlinsqlusersdata.model.Persons
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    internal lateinit var db:DBHelper
    internal var listPersons:List<Persons> = ArrayList<Persons>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = DBHelper(this)

        refreshData()

        btn_add.setOnClickListener {
            val person = Persons(
                Integer.parseInt(edt_id.text.toString()),
                edt_name.text.toString(),
                edt_email.text.toString()
            )
            db.addPerson(person)
            refreshData()
        }
        btn_update.setOnClickListener {
            val person = Persons(
                Integer.parseInt(edt_id.text.toString()),
                edt_name.text.toString(),
                edt_email.text.toString()
            )
            db.updatePerson(person)
            refreshData()
        }
        btn_delete.setOnClickListener {
            val person = Persons(
                Integer.parseInt(edt_id.text.toString()),
                edt_name.text.toString(),
                edt_email.text.toString()
            )
            db.deletePerson(person)
            refreshData()
        }

    }

    private fun refreshData() {
        listPersons = db.allPersons
        val adapter = ListPersonAdaper(this@MainActivity, listPersons, edt_id, edt_name, edt_email)
        list_persons.adapter = adapter
    }
}
