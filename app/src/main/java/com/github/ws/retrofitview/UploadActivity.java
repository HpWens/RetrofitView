package com.github.ws.retrofitview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.github.ws.retrofitview.rest.RestClient;
import com.upyun.library.common.Params;
import com.upyun.library.common.UploadManager;
import com.upyun.library.listener.SignatureListener;
import com.upyun.library.listener.UpCompleteListener;
import com.upyun.library.listener.UpProgressListener;
import com.upyun.library.utils.UpYunUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 5/18 0018.
 */
public class UploadActivity extends AppCompatActivity {

    private RestClient mClient;

    public static String KEY = "Rsg+8rx5M38xinuYepD+oQiUdUE=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mClient = new RestClient();
//
//        HashMap<String, String> hm = new HashMap<>();
//        hm.put("SN", "");
//        hm.put("Mac", "");
//        hm.put("CPUId", android.os.Build.SERIAL);
//        hm.put("HardDiskSN", "");
//        hm.put("DeviceType", "POS机");

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

        Map<String, Object> paramsMap = new HashMap<>();

        //http://v0.api.upyun.com/
        paramsMap.put(Params.BUCKET, "jms-pic");
        paramsMap.put(Params.SAVE_KEY, "33/photo/android-117d-85ed-4985-91f9-976849446348.jpg");
        // paramsMap.put(Params.X_GMKERL_THUMB, "/fw/100/quality/90");

        SignatureListener signatureListener = new SignatureListener() {
            @Override
            public String getSignature(String raw) {
                Log.e("---------------", "************----" + raw);
                return UpYunUtils.md5(raw + KEY);
            }
        };

        //结束回调，不可为空
        UpCompleteListener completeListener = new UpCompleteListener() {
            @Override
            public void onComplete(boolean isSuccess, String result) {
                Log.e("---------------", "************aaa" + result);
            }
        };

        UpProgressListener progressListener = new UpProgressListener() {
            @Override
            public void onRequestProgress(final long bytesWrite, final long contentLength) {
                Log.e("---------------", "************" + (100 * bytesWrite) / contentLength + "%");
            }
        };

        UploadManager.getInstance().formUpload(new File("/storage/emulated/0/MagazineUnlock/Balance(magazine)-04-2.3.001-bigpicture_04_2.jpg"),
                paramsMap, signatureListener, completeListener, progressListener
        );

        // UploadManager.getInstance().blockUpload(new File("/system/media/Pre-loaded/Pictures/Picture_09.jpg"), paramsMap, KEY, completeListener, progressListener);

    }
}
