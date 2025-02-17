package com.dunghn2792.assignmentserverandroid.api;

import com.dunghn2792.assignmentserverandroid.model.Sneaker;
import com.dunghn2792.assignmentserverandroid.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface API {
    //SNEAKER
    @GET("/api/sneakers")
    Call<List<Sneaker>> getSneakers();

    @GET("api/users")
    Call<List<User>> getUsers();

}
