package com.developtech.crony.util;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Divya on 13-05-2017.
 */
public interface UserApi {

    @POST(ALLURL.WS_BASE_URL_GETUSERDATA)
    public Call<ResultModel<UserBean>> getUserData(@Body UserBean objbean);

    @POST(ALLURL.WS_BASE_URL_CHANGEPASSWORD)
    public Call<ResultModel> changePassword(@Body UserBean objbean);

    @POST(ALLURL.WS_BASE_URL_LOGIN)
    public Call<ResultModel<UserBean>> login(@Body UserBean objbean);

    @POST(ALLURL.WS_BASE_URL_UPDATE)
    public Call<ResultModel> update(@Body UserBean objbean);

    @POST(ALLURL.WS_BASE_URL_SIGNUP)
    public Call<ResultModel> signUp(@Body UserBean objbean);


}
