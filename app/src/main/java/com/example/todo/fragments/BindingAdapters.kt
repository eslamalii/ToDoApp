package com.example.todo.fragments

import android.view.View
import android.widget.AdapterView
import android.widget.AutoCompleteTextView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.example.todo.R
import com.example.todo.data.models.Priority
import com.example.todo.data.models.ToDoData
import com.example.todo.fragments.list.ListFragmentDirections
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.util.*

class BindingAdapters {

    companion object {

        private val prioritiesList = listOf("High Priority", "Medium Priority", "Low Priority")

        @BindingAdapter("android:navigateToAddFragment")
        @JvmStatic
        fun navigateToAddFragment(view: FloatingActionButton, navigate: Boolean) {
            view.setOnClickListener {
                if (navigate) {
                    view.findNavController().navigate(R.id.action_listFragment_to_addFragment)
                }
            }
        }

        @BindingAdapter("android:emptyDatabase")
        @JvmStatic
        fun emptyDatabase(view: View, emptyDatabase: MutableLiveData<Boolean>) {
            when (emptyDatabase.value) {
                true -> view.visibility = View.VISIBLE
                false -> view.visibility = View.INVISIBLE
                null -> TODO()
            }
        }

        @BindingAdapter("android:parsePriorityColor")
        @JvmStatic
        fun parsePriorityColor(cardView: CardView, priority: Priority) {
            when (priority) {
                Priority.HIGH -> {
                    cardView.setCardBackgroundColor(cardView.context.getColor(R.color.red))
                }
                Priority.MEDIUM -> {
                    cardView.setCardBackgroundColor(cardView.context.getColor(R.color.yellow))
                }
                Priority.LOW -> {
                    cardView.setCardBackgroundColor(cardView.context.getColor(R.color.green))
                }
            }
        }

        @BindingAdapter("android:sendDataToFragment")
        @JvmStatic
        fun sendDataToFragment(view: ConstraintLayout, toDoData: ToDoData) {
            view.setOnClickListener {
                val action = ListFragmentDirections.actionListFragmentToUpdateFragment(toDoData)
                view.findNavController().navigate(action)
            }
        }

        @BindingAdapter("android:parseDate")
        @JvmStatic
        fun parseDate(view: TextView, date: Date) {
            val simpleFormatter = SimpleDateFormat("EEE dd/MM", Locale.ENGLISH)
            view.text = simpleFormatter.format(date)
        }


        @BindingAdapter("onItemClick")
        @JvmStatic
        fun onItemClick(textView: AutoCompleteTextView, note: ToDoData) {
            textView.onItemClickListener =
                AdapterView.OnItemClickListener { _, _, position, _ ->

                    when (position) {
                        0 -> {
                            textView.setTextColor(
                                ContextCompat.getColor(
                                    textView.context,
                                    R.color.red
                                )
                            )
                            note.priority = Priority.HIGH
                        }
                        1 -> {
                            textView.setTextColor(
                                ContextCompat.getColor(
                                    textView.context,
                                    R.color.yellow
                                )
                            )
                            note.priority = Priority.MEDIUM

                        }
                        2 -> {
                            textView.setTextColor(
                                ContextCompat.getColor(
                                    textView.context,
                                    R.color.green
                                )
                            )
                            note.priority = Priority.LOW
                        }
                    }
                }
        }


        @BindingAdapter("setAppropriateText")
        @JvmStatic
        fun setAppropriateText(textView: AutoCompleteTextView, priorityModel: Priority) {
            when (priorityModel) {
                Priority.HIGH -> {
                    textView.setTextColor(
                        ContextCompat.getColor(
                            textView.context,
                            R.color.red
                        )
                    )
                    textView.setText(prioritiesList[0], false)
                }

                Priority.MEDIUM -> {
                    textView.setTextColor(
                        ContextCompat.getColor(
                            textView.context,
                            R.color.yellow
                        )
                    )

                    textView.setText(prioritiesList[1], false)
                }
                Priority.LOW -> {
                    textView.setTextColor(
                        ContextCompat.getColor(
                            textView.context,
                            R.color.green
                        )
                    )

                    textView.setText(prioritiesList[2], false)
                }

            }

        }
    }

}