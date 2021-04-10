package com.example.todo.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import com.example.todo.adapters.Adapter
import com.example.todo.models.Note

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            NoteDetailsActivity.start(this,null)
        }
        listToDo.layoutManager=LinearLayoutManager(this,RecyclerView.VERTICAL,false)
        listToDo.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))

        val adapter = Adapter()
        listToDo.adapter=adapter

        val mainViewModel=ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel.liveData.observe(this, {
                notes->adapter.setItems(notes)
        })
    }
}