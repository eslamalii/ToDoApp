package com.example.todo.fragments.add

import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.todo.R
import com.example.todo.data.models.Priority
import com.example.todo.data.models.ToDoData
import com.example.todo.data.viewmodel.ToDoViewModel
import com.example.todo.databinding.FragmentAddBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddFragment : Fragment() {

    lateinit var viewModel: ToDoViewModel

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel = ViewModelProvider(this)[ToDoViewModel::class.java]
        //Set Menu
        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_add) {
            insertDataToDo()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun insertDataToDo() {
        val title = binding.titleEt.text.toString()
        val priority = binding.spinnerPriorities.selectedItem.toString()
        val description = binding.descriptionEt.text.toString()

        val validation = verifyDataFromUser(title, description)
        if (validation) {
            val data = ToDoData(
                0,
                title,
                description,
                parsePriority(priority)
            )
            viewModel.insertData(data)
            Toast.makeText(requireContext(), "Successfully Added!", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else
            Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_SHORT)
                .show()


    }

    private fun verifyDataFromUser(title: String, description: String): Boolean {
        return if (TextUtils.isEmpty(title) || TextUtils.isEmpty(description)) {
            false
        } else !(title.isEmpty() || description.isEmpty())
    }

    private fun parsePriority(priority: String): Priority {
        return when (priority) {
            "High Priority" -> Priority.HIGH
            "Medium Priority" -> Priority.MEDIUM
            "Low Priority" -> Priority.LOW
            else -> Priority.LOW
        }
    }


}