package com.antailbaxt3r.gettestsapplication.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TestModel {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("testType")
    @Expose
    private String testType;
    @SerializedName("score")
    @Expose
    private Integer score;
    @SerializedName("maxMarks")
    @Expose
    private Integer maxMarks;
    @SerializedName("testDate")
    @Expose
    private String testDate;
    @SerializedName("testPattern")
    @Expose
    private String testPattern;
    @SerializedName("message")
    @Expose
    private String message;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getMaxMarks() {
        return maxMarks;
    }

    public void setMaxMarks(Integer maxMarks) {
        this.maxMarks = maxMarks;
    }

    public String getTestDate() {
        return testDate;
    }

    public void setTestDate(String testDate) {
        this.testDate = testDate;
    }

    public String getTestPattern() {
        return testPattern;
    }

    public void setTestPattern(String testPattern) {
        this.testPattern = testPattern;
    }

    public String getMessage() {
        return message;
    }

    public TestModel setMessage(String message) {
        this.message = message;
        return this;
    }
}
