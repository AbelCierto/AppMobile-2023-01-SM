package com.example.myormroom_week6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson

class ContactActivity : AppCompatActivity() {
    lateinit var contact: Contact

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

        loadContact()
    }

    private fun loadContact() {
        val gson = Gson()
        val stringObj = intent.getStringExtra("contact")

        contact = gson.fromJson(stringObj, Contact::class.java) ?: Contact(null,"", "")

        if (contact.id != null){
            val etName = findViewById<TextInputEditText>(R.id.etName)
            val etTelephone = findViewById<TextInputEditText>(R.id.etTelephone)
            etName.setText(contact.name)
            etTelephone.setText(contact.telephone)
        }
    }

    private fun saveContact() {
        val etName = findViewById<TextInputEditText>(R.id.etName)
        val etTelephone = findViewById<TextInputEditText>(R.id.etTelephone)
        //val name = etName.text.toString()
        //val telephone = etTelephone.text.toString()

        contact.name = etName.text.toString()
        contact.telephone = etTelephone.text.toString()

        if (contact.id == null){
            AppDatabase.getInstance(this).getDao().insertContact(contact)
        } else {
            AppDatabase.getInstance(this).getDao().updateContact(contact)
        }
        finish()
    }

    //Upload the menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.contact_menu, menu)

        return true
    }

    //The logic to the item add
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.itemAdd -> {
                saveContact()
                true
            }
            R.id.itemDelete -> {
                deleteContact()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun deleteContact(){
        AppDatabase.getInstance(this).getDao().deleteContact()
        finish()
    }
}