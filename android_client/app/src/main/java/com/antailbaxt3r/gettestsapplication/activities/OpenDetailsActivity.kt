package com.antailbaxt3r.gettestsapplication.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.antailbaxt3r.gettestsapplication.R
import com.antailbaxt3r.gettestsapplication.models.TestModel
import com.antailbaxt3r.gettestsapplication.retrofit.RetrofitClient
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import kotlinx.android.synthetic.main.activity_open_details.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class OpenDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open_details)

        test_title.text = intent.getStringExtra("title")
        pattern.text = intent.getStringExtra("pattern")
        type.text = intent.getStringExtra("type")
        date.text = intent.getStringExtra("date")

        val score: Int = intent.getIntExtra("score", 0)
        val max: Int = intent.getIntExtra("max", 1)
        val decimal: Float = score.toFloat()/max.toFloat()

        val entries: MutableList<PieEntry> = ArrayList()
        entries.add(PieEntry(decimal, "Marks Scored"))
        entries.add(PieEntry(1-decimal, "Remaining"))
        val set = PieDataSet(entries, "")
        val list: MutableList<Int> = mutableListOf()
        list.add(resources.getColor(R.color.light_green))
        list.add(resources.getColor(R.color.yellow))
        set.colors = list
        set.sliceSpace = 5f
        set.setDrawValues(false)
        val data = PieData(set)
        pie_chart.data = data
        pie_chart.invalidate()
        pie_chart.description = null
        pie_chart.setDrawEntryLabels(false)
        pie_chart.setUsePercentValues(true)
        pie_chart.centerText = "Results"
        pie_chart.setCenterTextSize(20F)
        pie_chart.setEntryLabelTextSize(10F)
        pie_chart.transparentCircleRadius = 0F
        pie_chart.legend.isEnabled = false
        pie_chart.animateXY(1000, 1000);

        delete_button.setOnClickListener{
            val builder = AlertDialog.Builder(this)
            //set title for alert dialog
            builder.setTitle("Confirm Delete")
            //set message for alert dialog
            builder.setMessage("Are you sure you want to delete this test?")

            //performing positive action
            builder.setPositiveButton("Yes"){ _, _ ->
                val call: Call<TestModel> = RetrofitClient.getClient().delete(intent.getIntExtra("id", 0))
                call.enqueue(object: Callback<TestModel> {
                    override fun onFailure(call: Call<TestModel>, t: Throwable) {
                        Toast.makeText(applicationContext, "Something went wrong!", Toast.LENGTH_SHORT).show()
                        t.printStackTrace()
                    }

                    override fun onResponse(call: Call<TestModel>, response: Response<TestModel>) {
                        if(response.isSuccessful && response.code() == 200){
                            finish()
                        }else{
                            Toast.makeText(applicationContext, "Something went wrong!", Toast.LENGTH_SHORT).show()
                        }
                    }

                })
            }

            //performing negative action
            builder.setNegativeButton("Cancel"){ _, _ ->
            }
            // Create the AlertDialog
            val alertDialog: AlertDialog = builder.create()
            alertDialog.show()
        }
    }
}
