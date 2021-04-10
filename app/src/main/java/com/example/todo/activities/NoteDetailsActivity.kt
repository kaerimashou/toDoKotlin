package com.example.todo.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.todo.App
import com.example.todo.R
import com.example.todo.models.Note
import kotlinx.android.synthetic.main.activity_note_details.*


class NoteDetailsActivity:AppCompatActivity() {
    val EXTRA_NOTE ="NoteDetailsActivity.EXTRA_NOTE"
    get()=field

    lateinit var note: Note

    companion object{
        @JvmStatic
        fun start(caller:Activity, note: Note?){
            val intent = Intent(caller, NoteDetailsActivity::class.java)
            if(note!=null){
                intent.putExtra("NoteDetailsActivity.EXTRA_NOTE",note)
            }
            caller.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_details)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        setTitle(R.string.note_title)

        if(intent.hasExtra(EXTRA_NOTE)){
            note= intent.getParcelableExtra(EXTRA_NOTE)!!
            noteText.setText(note.text)
        }else{
            note=Note()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_note, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home->finish()
            R.id.action_save->{
                if(noteText.text.isNotEmpty()){
                    note.text=noteText.text.toString()
                    note.isDone=false
                    note.timestamp=System.currentTimeMillis()
                    if(intent.hasExtra(EXTRA_NOTE)){
                        App.getInstance().noteDAO.update(note)
                    }else{
                        App.getInstance().noteDAO.insert(note)
                    }
                }
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}