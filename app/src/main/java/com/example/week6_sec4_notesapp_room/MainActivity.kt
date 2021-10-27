package com.example.week6_sec4_notesapp_room

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.week6_sec4_notesapp_room.models.NoteDataBase
import com.example.week6_sec4_notesapp_room.models.Notes


class MainActivity : AppCompatActivity() {
    lateinit var note_ED: EditText
    lateinit var sub_btn: Button
    lateinit var list_RV: RecyclerView
    lateinit var listNote:List<Notes>
   lateinit var myDBRoom:NoteDataBase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        note_ED = findViewById(R.id.note_ED)
        sub_btn = findViewById(R.id.btn_submit)

        list_RV = findViewById(R.id.recycler_View)// Recycler View

         myDBRoom=NoteDataBase.getInstance(applicationContext)
       listNote= listOf()



        sub_btn.setOnClickListener {
            var input = note_ED.text.toString()


            //----------------Save DB--------------

            if(input.isNotEmpty()) {
                val n = Notes(0, input)
                    myDBRoom.NoteDao().insertNote(n)

                Toast.makeText(applicationContext, "data saved successfully! ", Toast.LENGTH_SHORT)
                    .show()



            }else{
                Toast.makeText(applicationContext, "Please Enter a Note ", Toast.LENGTH_SHORT).show()

            }
            //---------------------------------------------------
            //updateList()
            note_ED.text.clear()

        }
    }
    //--------------------------------------
    fun updateList() {

            listNote = myDBRoom.NoteDao().getAllNotes()


        list_RV.adapter = RV_Adapter(this,listNote)
        list_RV.layoutManager = LinearLayoutManager(this)

        list_RV.scrollToPosition(listNote.size - 1)

    }
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            updateList()
        }

    }


}
