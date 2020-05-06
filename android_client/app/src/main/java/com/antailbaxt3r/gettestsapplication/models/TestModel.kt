package com.antailbaxt3r.gettestsapplication.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TestModel {
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("testType")
    @Expose
    var testType: String? = null

    @SerializedName("score")
    @Expose
    var score: Int? = null

    @SerializedName("maxMarks")
    @Expose
    var maxMarks: Int? = null

    @SerializedName("testDate")
    @Expose
    var testDate: String? = null

    @SerializedName("testPattern")
    @Expose
    var testPattern: String? = null

    @SerializedName("message")
    @Expose
    var message: String? = null
        private set

    fun setMessage(message: String?): TestModel {
        this.message = message
        return this
    }
}