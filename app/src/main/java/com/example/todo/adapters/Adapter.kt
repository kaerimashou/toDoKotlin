package com.example.todo.adapters

import android.app.Activity
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SortedList
import androidx.recyclerview.widget.SortedListAdapterCallback
import com.example.todo.App
import com.example.todo.R
import com.example.todo.activities.NoteDetailsActivity
import com.example.todo.models.Note

class Adapter: RecyclerView.Adapter<Adapter.NoteViewHolder>() {

    private var list:SortedList<Note> = SortedList(Note::class.java,object : SortedListAdapterCallback<Note>(this){
        override fun compare(o1: Note?, o2: Note?): Int {
            if(!o2!!.isDone && o1!!.isDone){
                return 1
            }
            if(o2.isDone && !o1!!.isDone){
                return -1
            }
            return (o2.timestamp- o1!!.timestamp).toInt()
        }

        override fun areContentsTheSame(oldItem: Note?, newItem: Note?): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(item1: Note?, item2: Note?): Boolean {
            return item1!!.id==item2!!.id
        }

        override fun onChanged(position: Int, count: Int) {
            notifyItemChanged(position,count)
        }

        override fun onInserted(position: Int, count: Int) {
            notifyItemRangeInserted(position,count)
        }

        override fun onRemoved(position: Int, count: Int) {
            notifyItemRangeRemoved(position,count)
        }

        override fun onMoved(fromPosition: Int, toPosition: Int) {
            notifyItemMoved(fromPosition,toPosition)
        }
    })


    class NoteViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        lateinit var note:Note
        var noteText:TextView = itemView.findViewById(R.id.noteItemText)
        var isDone:CheckBox=itemView.findViewById(R.id.noteItemIsDone)
        var delete:View=itemView.findViewById(R.id.noteItemDelete)

        var silentUpdate:Boolean=true


        init{
            isDone.setOnCheckedChangeListener { _, isChecked ->
                if(!silentUpdate){
                    note.isDone=isChecked
                    App.getInstance().noteDAO.update(note)
                }
                updateStrokeOut()
            }

            itemView.setOnClickListener{
                NoteDetailsActivity.start(caller = itemView.context as Activity, note = note)
            }
            delete.setOnClickListener {
                App.getInstance().noteDAO.delete(note)
            }

        }

        fun bind(note: Note){
            this.note=note
            noteText.text=note.text
            updateStrokeOut()

            silentUpdate=true
            isDone.isChecked=note.isDone
            silentUpdate=false

        }

        private fun updateStrokeOut(){
            if(note.isDone){
                noteText.paintFlags=(noteText.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG)
            }else{
                noteText.paintFlags=(noteText.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv())
            }
        }
    }

    fun setItems(notes:List<Note>){
        list.replaceAll(notes)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_note_list,parent,false)
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size()
    }
}