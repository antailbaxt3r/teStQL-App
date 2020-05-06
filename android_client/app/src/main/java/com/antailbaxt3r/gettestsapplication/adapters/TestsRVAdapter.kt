package com.antailbaxt3r.gettestsapplication.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.antailbaxt3r.gettestsapplication.R
import com.antailbaxt3r.gettestsapplication.models.TestModel
import com.antailbaxt3r.gettestsapplication.viewholders.TestsViewHolder

class TestsRVAdapter(private val itemList: List<TestModel>?, private val context: Context) : RecyclerView.Adapter<TestsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestsViewHolder {
        return TestsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_test, parent, false))
    }

    override fun onBindViewHolder(holder: TestsViewHolder, position: Int) {
        holder.populate(itemList!![position], context)
    }

    override fun getItemCount(): Int {
        return itemList!!.size
    }

}