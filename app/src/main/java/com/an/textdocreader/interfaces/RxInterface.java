package com.an.textdocreader.interfaces;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface RxInterface {

    // option 1: a resource relative to your base URL and endpoint
    @GET("s/z3xmrudcfnyxm63/response.txt?dl=1") // Base URL: https://www.dropbox.com/
    Call<ResponseBody> downloadFileWithFixedUrl();

    // option 2: using a dynamic URL
    @GET
    Call<ResponseBody> downloadFileWithDynamicUrlSync(@Url String fileUrl);

}
