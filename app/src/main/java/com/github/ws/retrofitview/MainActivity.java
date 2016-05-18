package com.github.ws.retrofitview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.github.ws.retrofitview.model.ApiResponse;
import com.github.ws.retrofitview.model.Category;
import com.github.ws.retrofitview.rest.RestClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RestClient mClient;

    public static String KEY = "Rsg+8rx5M38xinuYepD+oQiUdUE=";

    public String mFileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mClient = new RestClient();

        Call<ApiResponse<Category>> data = mClient.getRectService().getCategory("1", "", "", "1", "20");

        data.enqueue(new Callback<ApiResponse<Category>>() {
            @Override
            public void onResponse(Call<ApiResponse<Category>> call, Response<ApiResponse<Category>> response) {
                 Log.e("---------------","************"+response.body().categoryList.get(0).name);
            }

            @Override
            public void onFailure(Call<ApiResponse<Category>> call, Throwable t) {

            }
        });


        /*******************************图片上传*******************************/

//        mFileName = "6348.jpg";
//
//        File file=new File(Environment.getExternalStorageDirectory(),mFileName);
//
//        //普通key/value
//        RequestBody username =
//                RequestBody.create(
//                        MediaType.parse("multipart/form-data"), "ws");
//
//        RequestBody address =
//                RequestBody.create(
//                        MediaType.parse("multipart/form-data"), "天府之都");
//
//        //file
//        RequestBody requestFile =
//                RequestBody.create(MediaType.parse("multipart/form-data"), file);
//
//        //包装RequestBody，在其内部实现上传进度监听
//        CountingRequestBody countingRequestBody=new CountingRequestBody(requestFile, new CountingRequestBody.Listener() {
//            @Override
//            public void onRequestProgress(long bytesWritten, long contentLength) {
//            }
//        });
//
//        MultipartBody.Part body =
//                MultipartBody.Part.createFormData("file", file.getName(), countingRequestBody);
//
//        Call<ResponseBody> userCall=mClient.getRectService().uploadFile(body);
//        userCall.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                   Log.e("---------------","************"+response.body());
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                Log.e("---------------","************1111"+t.getMessage());
//            }
//        });


        /**************************文件下载*********************************************************/

//        Call<ResponseBody> userCall = mClient.getRectService().downFile(mFileName);
//
//        userCall.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//
//                Log.e("---------------","************response="+response.body());
//
//                try {
//                    String fileName = Environment.getExternalStorageDirectory() + "/" + mFileName;
//
//                    FileOutputStream fos = null;
//
//                    fos = new FileOutputStream(fileName);
//                    InputStream is = response.body().byteStream();
//
//                    byte[] buf = new byte[1024];
//                    int len;
//                    while ((len = is.read(buf)) != -1) {
//                        fos.write(buf, 0, len);
//                    }
//                    is.close();
//                    fos.close();
//
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                Log.e("---------------","************success");
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//
//            }
//        });


        /*************************获取返回字符串***************************************************************/

//        HashMap<String, String> hm = new HashMap<>();
//        hm.put("SN", "");
//        hm.put("Mac", "");
//        hm.put("CPUId", android.os.Build.SERIAL);
//        hm.put("HardDiskSN", "");
//        hm.put("DeviceType", "POS机");
//
//        mClient.getRectService().commitBind(hm)
//                .enqueue(new Callback<ResponseBody>() {
//                    @Override
//                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//
//                        ResponseBody body = response.body();
//
//                        try {
//                            BufferedReader reader = new BufferedReader(new InputStreamReader(body.byteStream()));
//                            StringBuilder out = new StringBuilder();
//                            String newLine = System.getProperty("line.separator");//换行符号
//                            String line;
//                            while ((line = reader.readLine()) != null) {
//                                out.append(line);
//                                out.append(newLine);
//                            }
//
//                            // Prints the correct String representation of body.
//                            System.out.println(out);
//                            Log.e("---------------", "************" + out);
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<ResponseBody> call, Throwable t) {
//                    }
//                });

        /****************************加入RxJava************************************************/

//        mClient.getRectService().commitBinding(hm)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<ApiResponse>() {
//
//                    @Override
//                    public void onStart() {
//                        super.onStart();
//                    }
//
//                    @Override
//                    public void onCompleted() {
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                    }
//
//                    @Override
//                    public void onNext(ApiResponse user) {
//
//                        Log.e("---------------", "************" + user.response.msg);
//                    }
//                });

//        Call<ApiResponse> userCall= mClient.getRectService().findUserForPost("hello");
//
//        userCall.enqueue(new Callback<ApiResponse>() {
//            @Override
//            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
//             Log.e("---------------","************"+response.body().msg);
//            }
//
//            @Override
//            public void onFailure(Call<ApiResponse> call, Throwable t) {
//
//            }
//        });


        /*******************************************************************************/

    }
}
