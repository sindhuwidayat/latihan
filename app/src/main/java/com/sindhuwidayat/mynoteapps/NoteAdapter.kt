package com.sindhuwidayat.mynoteapps

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_note.view.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.sdk27.coroutines.onClick
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class NoteAdapter(var list: List<NoteModel>?) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {
            val listDate =
                parseDatePart(list?.get(position)?.createdAt)
            itemView.in_tv_day.text = listDate[0]
            itemView.in_tv_date.text = listDate[1]
            itemView.in_tv_monthyear.text = listDate[2] + " " +
                    listDate[3]
            itemView.in_tv_time.text = listDate[4]
            itemView.in_tv_title_notes.text =
                list?.get(position)?.title
            itemView.in_tv_message_notes.text =
                list?.get(position)?.message
            itemView.onClick {
                val intent =
                    itemView.context.intentFor<DetailActivity>(
                        "NOTE" to
                                list?.get(position)
                    )
                itemView.context.startActivity(intent)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.item_note,
                    parent,
                    false
                )
        )
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    override fun onBindViewHolder(holder: NoteAdapter.ViewHolder, position: Int) {
        holder?.bind(position)
    }

    fun parseDatePart(date: String?): ArrayList<String> {
        val locale = Locale("in", "ID")
        val dateFormat: SimpleDateFormat? = SimpleDateFormat("EEE ,dd MMM yyyy , hh:mm aaa", locale)
        val listDate: ArrayList<String> = ArrayList()
        val dateParse = dateFormat?.parse(date.toString())
        val fullday = SimpleDateFormat(
            "EEEE",
            locale
        ).format(dateParse)
        listDate.add(fullday)
        val dateNumber = SimpleDateFormat(
            "dd",
            locale
        ).format(dateParse)
        listDate.add(dateNumber)
        val halfMonth = SimpleDateFormat(
            "MMM",
            locale
        ).format(dateParse)
        listDate.add(halfMonth)
        val year = SimpleDateFormat("yyyy", locale).format(dateParse)
        listDate.add(year)
        val time = SimpleDateFormat(
            "hh:mm aaa",
            locale
        ).format(dateParse)
        listDate.add(time)
        27
        return listDate
    }

    fun setListOfNotes(listNote: List<NoteModel>?) {
        list = listNote
        notifyDataSetChanged()
    }


}