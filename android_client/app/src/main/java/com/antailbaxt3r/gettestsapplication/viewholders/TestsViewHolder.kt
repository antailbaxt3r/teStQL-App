package com.antailbaxt3r.gettestsapplication.viewholders

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.antailbaxt3r.gettestsapplication.models.TestModel
import kotlinx.android.synthetic.main.item_test.view.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class TestsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var testTitle: TextView = itemView.test_title
    private var testType: TextView = itemView.test_type
    private var testPattern: TextView = itemView.test_pattern
    private var maxMarks: TextView = itemView.max_marks
    private var score: TextView = itemView.score
    private var date: TextView = itemView.date


    @SuppressLint("SimpleDateFormat")
    fun populate(test: TestModel, context: Context?) {
        testTitle.text = test.title
        testType.text = test.testType
        testPattern.text = test.testPattern
        maxMarks.text = test.maxMarks.toString()
        score.text = test.score.toString()

        var dateString: String = ""
        dateString += test.testDate?.substring(8, 10) + " "
        var month: String = ""
        when(test.testDate?.substring(5, 7)){
            "01" -> month = "Jan, "
            "02" -> month = "Feb, "
            "03" -> month = "Mar, "
            "04" -> month = "Apr, "
            "05" -> month = "May, "
            "06" -> month = "Jun, "
            "07" -> month = "Jul, "
            "08" -> month = "Aug, "
            "09" -> month = "Sept, "
            "10" -> month = "Oct, "
            "11" -> month = "Nov, "
            "12" -> month = "Dec, "
        }
        dateString += month + test.testDate?.substring(0, 4)

        date.text = dateString
    }
}