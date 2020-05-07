package com.antailbaxt3r.gettestsapplication.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentStatePagerAdapter
import com.antailbaxt3r.gettestsapplication.R
import com.antailbaxt3r.gettestsapplication.models.TestModel
import com.antailbaxt3r.gettestsapplication.retrofit.RetrofitClient
import com.antailbaxt3r.gettestsapplication.utils.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_view_tests.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewTestsActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_tests)

        //actionbar
        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "Report List"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

        setViewPager()

        delete_all.setOnClickListener{

            val builder = AlertDialog.Builder(this)
            //set title for alert dialog
            builder.setTitle("Confirm Delete")
            //set message for alert dialog
            builder.setMessage("Are you sure you want to delete all tests?")

            //performing positive action
            builder.setPositiveButton("Yes"){ _, _ ->
                val call: Call<TestModel> = RetrofitClient.getClient().deleteAll()
                call.enqueue(object: Callback<TestModel>{
                    override fun onFailure(call: Call<TestModel>, t: Throwable) {
                        Toast.makeText(applicationContext, "Something went wrong!", Toast.LENGTH_SHORT).show()
                        t.printStackTrace()
                    }

                    override fun onResponse(call: Call<TestModel>, response: Response<TestModel>) {
                        if(response.isSuccessful && response.code() == 200){
                            finish()
                            Toast.makeText(applicationContext, "Deleted Successfully", Toast.LENGTH_SHORT).show()
                            startActivity(intent)
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

    private fun setViewPager() {
        val adapter = ViewPagerAdapter(supportFragmentManager, FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
        view_pager.adapter = adapter
        dashboard_tab_layout.setupWithViewPager(view_pager)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
