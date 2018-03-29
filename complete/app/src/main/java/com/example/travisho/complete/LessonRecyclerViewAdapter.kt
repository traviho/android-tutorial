package com.example.travisho.complete

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_lesson_button.view.*

class LessonRecyclerViewAdapter(
        private val lessonNumbers:Array<Int>,
        private val mListener: LessonListFragment.OnLessonListFragmentInteractionListener?)
    : RecyclerView.Adapter<LessonRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Int
            mListener?.onLessonListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_lesson_button, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mLessonNumberView.text = "" + lessonNumbers[position]

        with(holder.mView) {
            tag = position
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = lessonNumbers.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mLessonNumberView: TextView = mView.lesson_number

        override fun toString(): String {
            return mLessonNumberView.text.toString()
        }
    }
}