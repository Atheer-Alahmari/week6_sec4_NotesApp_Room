package com.example.week6_sec4_notesapp_room

import android.app.AlertDialog
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.example.week6_sec4_notesapp_room.models.NoteDataBase
import com.example.week6_sec4_notesapp_room.models.Notes
import kotlinx.android.synthetic.main.item_row.view.*


class RV_Adapter (val activity: MainActivity,private val listOf_Note:List<Notes>): RecyclerView.Adapter<RV_Adapter.ItemViewHolder>() {
    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val note = listOf_Note[position].note
        val idn = listOf_Note[position].id

        holder.itemView.apply {
            text_View.text = note
            var myDBRoom= NoteDataBase.getInstance(activity)

            //-----------------------------AlertDialog-------------------------------------------
            editIcon.setOnClickListener {
                var alt = AlertDialog.Builder(activity)
                alt.setTitle("Update Note ")
                val mEtName = EditText(activity)
                alt.setView(mEtName)

                // Positive button text and action
                alt.setPositiveButton("Save", DialogInterface.OnClickListener { _, _ ->
                    var input = mEtName.text.toString()
                    myDBRoom.NoteDao().updateNote(Notes(idn,input))
                    //activity.updateList()
                })


                // negative button text and action
                alt.setNegativeButton("Cansel", DialogInterface.OnClickListener { dialog, _ ->
                    dialog.cancel()

                })

                val alt1: AlertDialog = alt.create()
                alt1.setCanceledOnTouchOutside(false)
                alt1.show()
            }
            //------------------------------------------------------------------------------------
            deleteIcon.setOnClickListener {
                myDBRoom.NoteDao().deleteNote(Notes(idn))
              //  activity.updateList()
            }

        }
    }

    override fun getItemCount() = listOf_Note.size
}
