package com.example.todo.fragments.add

import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.todo.R
import com.example.todo.data.models.ToDoData
import com.example.todo.data.viewmodel.ToDoViewModel
import com.example.todo.databinding.FragmentAddBinding
import com.example.todo.fragments.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class AddFragment : Fragment() {

    private val viewModel: ToDoViewModel by viewModels()
    private val mSharedViewModel: SharedViewModel by viewModels()

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        val view = binding.root

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            resources.getStringArray(R.array.priorities)
        )

        binding.spinnerPriorities.setAdapter(adapter)

        binding.spinnerPriorities.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                when (position) {
                    0 -> {
                        binding.spinnerPriorities.setTextColor(
                            ContextCompat.getColor(
                                requireContext(),
                                R.color.red
                            )
                        )
                    }
                    1 -> {
                        binding.spinnerPriorities.setTextColor(
                            ContextCompat.getColor(
                                requireContext(),
                                R.color.yellow
                            )
                        )
                    }
                    2 -> {
                        binding.spinnerPriorities.setTextColor(
                            ContextCompat.getColor(
                                requireContext(),
                                R.color.green
                            )
                        )
                    }
                }
            }

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
        val priority = binding.spinnerPriorities.text.toString()
        val description = binding.descriptionEt.text.toString()

        val validation = mSharedViewModel.verifyDataFromUser(title, description)
        if (validation) {
            val data = ToDoData(
                0,
                title,
                description,
                mSharedViewModel.parsePriority(priority),
                Calendar.getInstance().time
            )
            viewModel.insertData(data)
            Toast.makeText(requireContext(), "Successfully Added!", Toast.LENGTH_SHORT).show()

            findNavController().popBackStack()
        } else
            Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_SHORT)
                .show()


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}