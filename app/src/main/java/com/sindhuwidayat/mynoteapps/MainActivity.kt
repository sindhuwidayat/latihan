package com.sindhuwidayat.mynoteapps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.sdk27.coroutines.onClick

class MainActivity : AppCompatActivity() {

    var noteDatabase: NoteDatabase? = null

    lateinit var layoutManager : LinearLayoutManager
    lateinit var noteAdapter : NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        noteDatabase = NoteDatabase.getInstance(this)

        layoutManager = LinearLayoutManager(this)
        noteAdapter = NoteAdapter(ArrayList())
        ma_rv_note.layoutManager = layoutManager
        ma_rv_note.adapter = noteAdapter

        getAllNotes()

        ma_fab_add.onClick {
            startActivity(intentFor<AddUpdateActivity>())
        }


    }

    override fun onRestart() {
        super.onRestart()
        getAllNotes()
        30
    }
    private fun getAllNotes() {
        GlobalScope.launch {
            val list: List<NoteModel>? =
                noteDatabase?.noteDao()?.getAllNote()
            runOnUiThread {
                showAllNotes(list)
            }
        }
    }
    private fun showAllNotes(list: List<NoteModel>?) {
        noteAdapter.setListOfNotes(list)
    }

}
