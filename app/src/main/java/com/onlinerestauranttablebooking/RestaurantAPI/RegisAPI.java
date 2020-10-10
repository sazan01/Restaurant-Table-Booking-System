package com.onlinerestauranttablebooking.RestaurantAPI;


       import com.onlinerestauranttablebooking.Model.RegisModel;
       import com.onlinerestauranttablebooking.Model.TokenAuth;
       import com.onlinerestauranttablebooking.Model.ViewDishModel;
       import retrofit2.Call;
       import retrofit2.http.*;

       import java.util.List;

public interface RegisAPI {


    @FormUrlEncoded
    @POST("register")
    Call<String> useradd(@Field("name") String name,@Field("email") String email,@Field("pass") String pass,@Field("re_pass") String re_pass,@Field("usertype") String usertype);

    @GET("users")
    Call<List<RegisModel>> getUser();

    @FormUrlEncoded
    @POST("login")
    Call<TokenAuth> Login(@Field("email") String email, @Field("pass") String pass);

    @GET("getdish")
    Call<List<ViewDishModel>>getDishes();

    @FormUrlEncoded
    @POST("/saveContact")
    Call<String> feedback(
            @Field("name")String name,
            @Field("email")String email,
            @Field("phone")String phone,
            @Field("sub")String sub,
            @Field("message")String message);

    @FormUrlEncoded
    @POST("/reserveTable")
    Call<String> reservetable(
            @Field("num")String num,
            @Field("date")String date,
            @Field("time")String time,
            @Field("name")String name);

    @GET("getUserById/{id}")
    Call<RegisModel> loadregis(@Path("id") String id);

    @FormUrlEncoded
    @PUT("/UserUpdateAnd")
    Call<String>  updatedetail(
         @Field("_id") String uid,
         @Field("name") String name,
         @Field("email") String email,
         @Field("pass") String pass
         );


}
