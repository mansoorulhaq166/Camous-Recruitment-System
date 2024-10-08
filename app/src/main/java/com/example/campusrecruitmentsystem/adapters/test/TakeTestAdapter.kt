package com.example.campusrecruitmentsystem.adapters.test

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.campusrecruitmentsystem.databinding.ItemTakeTestBinding
import com.example.campusrecruitmentsystem.listeners.OnItemClickListener
import com.example.campusrecruitmentsystem.listeners.TestItemClickListener
import com.example.campusrecruitmentsystem.models.recruiter.Test
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TakeTestAdapter(private val testList: MutableList<Test>) :
    RecyclerView.Adapter<TakeTestAdapter.ViewHolder>() {
    private var itemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemTakeTestBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentTest = testList[position]
        holder.bind(currentTest)
    }

    override fun getItemCount(): Int {
        return testList.size
    }

    fun addData(test: Test) {
        testList.add(test)
        notifyItemInserted(testList.size - 1)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        itemClickListener = listener
    }

    inner class ViewHolder(private val binding: ItemTakeTestBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(test: Test) {
            val creationTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(
                Date(test.creationTime)
            )

            var type = "Multiple Choice Test"
            when (test.testType) {
                1 -> {
                    type = "Multiple Choice Test"
                }

                2 -> {
                    type = "True/False Test"
                }

                3 -> {
                    type = "Short Answers Test"
                }
            }

            binding.textTestName.text = test.testName
            binding.textTestCreationDate.text = creationTime
            binding.textTestTime.text = test.testTime + " seconds"
            binding.textTestType.text = type

            binding.btnStartTest.setOnClickListener {
                itemClickListener?.onItemClick(adapterPosition)
            }
        }
    }
}