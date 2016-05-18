package com.github.ws.retrofitview.serviece;

import com.github.ws.retrofitview.model.ApiResponse;
import com.github.ws.retrofitview.model.Category;

import java.util.HashMap;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import rx.Observable;

/**
 * Created by Administrator on 5/17 0017.
 */
public interface RestService {

    @GET("/Route.axd?method=vast.sync.category.issued&format=Json")
    Call<ApiResponse<Category>> getCategory(@Query("StoreId") String storeId,
                                            @Query("Condition") String condition,
                                            @Query("LastUpdateTime") String lastUpdateTime,
                                            @Query("PageIndex") String pageIndex,
                                            @Query("PageSize") String pageSize);


    @FormUrlEncoded
    @POST("/Route.axd?method=vast.Store.terminal.bind&format=Json")
    Observable<ApiResponse> findUserForPost(@Field("SN") String sn, @Field("Mac") String mac, @Field("CPUId") String cpuId
            , @Field("HardDiskSN") String hard, @Field("DeviceType") String type);

    //@Query注解不能丢
    @GET("rest/findUserForGet")
    Observable<ApiResponse> findUserForGet(@Query("id") int id, @Query("username") String username, @Query("address") String address);


    @POST("rest/findUserList")
    Observable<List<ApiResponse>> findUserList();

    //body的参数一定要是对象，String不可以，会把""也一起传过去 ,如服务器收到"dddd"
    //Content-Type: application/json; charset=UTF-8为自动加上
    @POST("rest/postBodyJson")
    Observable<ApiResponse> postBodyJson(@Body ApiResponse user);


    @POST("/Route.axd?method=vast.Store.terminal.bind&format=Json")
    Observable<ApiResponse> commitBinding(@Body HashMap<String, String> hm);


    @POST("/Route.axd?method=vast.Store.terminal.bind&format=Json")
    Call<ResponseBody> commitBind(@Body HashMap<String, String> hm);


    @Multipart
    @POST("")
    Call<ResponseBody> upload(@Part("username") RequestBody username, @Part("address") RequestBody address,
                              @Part MultipartBody.Part file);


    //文件下载
    @Streaming
    @GET("/33/photo/android-117d-85ed-4985-91f9-97684944{filename}")
    Call<ResponseBody> downFile(@Path("filename") String fileName);


    @Multipart
    @POST("/HpWens/ProgressDemo/")
    Call<ResponseBody> uploadFile(@Part MultipartBody.Part file);


}
