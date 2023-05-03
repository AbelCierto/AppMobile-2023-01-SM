package com.example.myormroom_week6

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson

class MainActivity : AppCompatActivity(), OnItemClickListener {
    override fun onItemClicked(contact: Contact) {
        val intent = Intent(this, ContactActivity::class.java)
        val gson = Gson()
        intent.putExtra("contact", gson.toJson(contact))
        startActivity(intent)
    }
    lateinit var contacts: List<Contact>
    lateinit var contactAdapter: ContactAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadContact()

        //Usar el recycle view
        contactAdapter = ContactAdapter(contacts, this)
        val rvContact = findViewById<RecyclerView>(R.id.rvContact)
        rvContact.adapter = contactAdapter
        rvContact.layoutManager = LinearLayoutManager(this)
    }

    override fun onResume() {
        super.onResume()
        loadContact()

        //Usar el recycle view
        contactAdapter = ContactAdapter(contacts, this)
        val rvContact = findViewById<RecyclerView>(R.id.rvContact)
        rvContact.adapter = contactAdapter
        rvContact.layoutManager = LinearLayoutManager(this)
    }



    private fun loadContact() {
        contacts = AppDatabase.getInstance(this).getDao().getAll()
    }

    //Upload the menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)

        return true
    }

    //The logic to the item add
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.itemAdd -> {
                val intent = Intent(this, ContactActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
