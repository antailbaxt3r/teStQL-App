package com.antailbaxt3r.gettestsapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager

import com.antailbaxt3r.gettestsapplication.R
import com.antailbaxt3r.gettestsapplication.activities.ViewTestsActivity
import com.antailbaxt3r.gettestsapplication.adapters.TestsRVAdapter
import com.antailbaxt3r.gettestsapplication.models.TestModel
import com.antailbaxt3r.gettestsapplication.retrofit.RetrofitClient
import kotlinx.android.synthetic.main.activity_view_tests.*
import kotlinx.android.synthetic.main.fragment_common.tests_recycler_view
import net.steamcrafted.loadtoast.LoadToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class CommonFragment : Fragment() {

    private lateinit var loadToast: LoadToast
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root: View? = inflater.inflate(R.layout.fragment_common, container, false)
        loadToast = LoadToast(context)
        loadToast.setText("Loading...").show()
        (context as ViewTestsActivity).progress_bar.visibility = View.VISIBLE
        sendCall()
        return root
    }

    override fun onResume() {
        super.onResume()
        sendCall()
    }

    private fun sendCall() {
        val call: Call<List<TestModel>> = RetrofitClient.getClient().allTests
        call.enqueue(object: Callback<List<TestModel>>{
            override fun onResponse(call: Call<List<TestModel>>, response: Response<List<TestModel>>) {
                if(response.isSuccessful && response.code() == 200) run {
                    val list: List<TestModel>? = response.body()
                    tests_recycler_view.adapter = TestsRVAdapter(list, context!!)
                    tests_recycler_view.layoutManager = LinearLayoutManager(context)
                    loadToast.success()
                }else{
                    loadToast.error()
                }
                (context as ViewTestsActivity).progress_bar.visibility = View.GONE
            }

            override fun onFailure(call: Call<List<TestModel>>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(context, "Something went wrong!", Toast.LENGTH_SHORT).show()
                loadToast.error()
                (context as ViewTestsActivity).progress_bar.visibility = View.GONE
            }

        })
    }
}
