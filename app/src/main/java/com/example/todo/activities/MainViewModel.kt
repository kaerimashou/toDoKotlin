package com.example.todo.activities

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.todo.App
import com.example.todo.models.Note

class MainViewModel:ViewModel() {
    var liveData:LiveData<List<Note>> = App.getInstance().noteDAO.getAllLiveData()
    private set
}