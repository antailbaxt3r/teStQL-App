package com.antailbaxt3r.gettestsapplication.retrofit;

import com.antailbaxt3r.gettestsapplication.models.TestModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface APIInterface {

    @GET("tests")
    Call<List<TestModel>> getAllTests();

    @GET("tests/{id}")
    Call<TestModel> getTest(@Path("id") int id);

    @POST("tests")
    Call<TestModel> addTest(@Body HashMap<String, String> map);

    @PUT("tests/{id}")
    Call<TestModel> update(@Path("id") int id, @Body Map<String, String> map);

    @DELETE("tests")
    Call<TestModel> deleteAll();

    @DELETE("tests/{id}")
    Call<TestModel> delete(@Path("id") int id);
}
