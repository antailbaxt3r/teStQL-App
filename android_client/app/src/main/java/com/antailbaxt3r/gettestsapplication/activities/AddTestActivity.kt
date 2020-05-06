package com.antailbaxt3r.gettestsapplication.activities

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.CompoundButton
import android.widget.Toast
import com.antailbaxt3r.gettestsapplication.R
import com.antailbaxt3r.gettestsapplication.databinding.ActivityAddTestBinding
import java.util.*
import javax.xml.datatype.DatatypeConstants.MONTHS

class AddTestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddTestBinding
    private var pattern: Boolean = false
    private var type: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mains.setOnCheckedChangeListener{ _: CompoundButton, b: Boolean ->
            if(b){
                binding.mA.isChecked = false
            }
            pattern = b
        }
        binding.mA.setOnCheckedChangeListener{ _: CompoundButton, b: Boolean ->
            if(b){
                binding.mains.isChecked = false
            }
            pattern = !b
        }

        binding.objective.setOnCheckedChangeListener{ _: CompoundButton, b: Boolean ->
            if(b){
                binding.subjective.isChecked = false
            }
            type = b
        }

        binding.subjective.setOnCheckedChangeListener{ _: CompoundButton, b: Boolean ->
            if(b){
                binding.objective.isChecked = false
            }
            type = !b
        }

        binding.dateButton.setOnClickListener{

            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { _, year, _, dayOfMonth ->
                // Display Selected date in TextView
                val dayString = String.format("%02d", dayOfMonth)
                val monthString = String.format("%02d", month)
                val display = "$year-$monthString-$dayString"
                binding.date.text = display
                binding.date.setTextColor(resources.getColor(R.color.black))
            }, year, month, day)
            dpd.show()
        }

        binding.addButton.setOnClickListener{
            if(
                checkTitle() &&
                checkMaxMarks() &&
                checkScore() &&
                checkPattern() &&
                checkType() &&
                checkDate()
            ){
                Toast.makeText(this, "All fields correct", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkTitle(): Boolean {
        Log.i("CHECKING", "TITLE")
        return if(binding.testTitleTiet.text == null || binding.testTitleTiet.text.toString().isEmpty()){
            binding.testTitleTil.error = "This field cannot be empty"
            false
        }else{
            binding.testTitleTil.error = null
            true
        }
    }

    private fun checkMaxMarks(): Boolean {
        return if(binding.maxMarksTiet.text == null || binding.maxMarksTiet.text.toString().isEmpty()){
            binding.maxMarksTil.error = "This field cannot be empty"
            false
        }else{
            binding.maxMarksTil.error = null
            true
        }
    }

    private fun checkScore(): Boolean {
        if(binding.scoreTiet.text == null || binding.scoreTiet.text.toString().isEmpty()){
            binding.scoreTil.error = "This field cannot be empty"
            return false
        }else{
            binding.scoreTil.error = null
            val i = binding.scoreTiet.text.toString().toInt()
            if(i > binding.maxMarksTiet.text.toString().toInt()){
                binding.scoreTil.error = "Score cannot be greater than max marks"
                return false
            }
            return true
        }
    }

    private fun checkPattern(): Boolean {
        if(!binding.mains.isChecked && !binding.mA.isChecked){
            binding.mains.error = "Please select a pattern"
            return false
        }
        binding.mains.error = null
        return true
    }

    private fun checkType(): Boolean {
        if(!binding.objective.isChecked && !binding.subjective.isChecked){
            binding.objective.error = "Please select a pattern"
            return false
        }
        binding.objective.error = null
        return true
    }

    private fun checkDate(): Boolean {
        if(binding.date.text == null || binding.date.text.toString() == resources.getString(R.string.tap_to_select_date)){
            binding.date.error = "Please select date"
            return false
        }
        binding.date.error == null
        return true
    }

}
