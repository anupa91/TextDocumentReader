package com.an.textdocreader.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.an.textdocreader.R;
import com.an.textdocreader.dto.BeanJson;
import com.an.textdocreader.interfaces.RxInterface;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Constants
    public static final String TAG = MainActivity.class.getSimpleName();
    private static final int REQUEST_RUNTIME_PERMISSION_EXTERNAL_STORAGE = 400;

    // UI Components
    private ProgressBar mPbProgressBar;
    private Button mBtnDownloadFile, mBtnReadAndWrite;
    private TextView mTvDownloadStatus, mTvReadWriteStatus;

    private String mDownloadedFilePath, mTestText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        requestRuntimePermissionForAccessStorage();
    }

    private void initView() {
        mPbProgressBar = findViewById(R.id.activity_main_pb_progress_bar);

        mBtnDownloadFile = findViewById(R.id.activity_main_btn_download_file);
        mBtnDownloadFile.setOnClickListener(this);
        mBtnReadAndWrite = findViewById(R.id.activity_main_btn_read_and_write);
        mBtnReadAndWrite.setOnClickListener(this);

        mTvDownloadStatus = findViewById(R.id.activity_main_tv_download_status);
        mTvReadWriteStatus = findViewById(R.id.activity_main_tv_read_write_status);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_main_btn_download_file:
                downloadFile();
                break;
            case R.id.activity_main_btn_read_and_write:
                createStringUsingTextFileAndCreateJsonObject(mDownloadedFilePath);
                break;
            default:
                break;
        }
    }

    private void downloadFile() {
        mPbProgressBar.setVisibility(View.VISIBLE);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://www.dropbox.com/").build();
        Call<ResponseBody> call = retrofit.create(RxInterface.class).downloadFileWithDynamicUrlSync("https://www.dropbox.com/s/z3xmrudcfnyxm63/response.txt?dl=1");

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    boolean writtenToDisk = writeResponseBodyToDisk(response.body());

                    Log.d(TAG, "File download process -> " + writtenToDisk);
                    if (writtenToDisk) {
                        mTvDownloadStatus.setText("Download Status: Completed\nPath: " + mDownloadedFilePath);
                    } else {
                        mTvDownloadStatus.setText("Download Status: Failed");
                    }
                } else {

                }

                mPbProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                Log.e(TAG, throwable.toString());
                mPbProgressBar.setVisibility(View.GONE);
            }
        });
    }

    private boolean writeResponseBodyToDisk(ResponseBody body) {

        try {
            File filesDir = new File(Environment.getExternalStorageDirectory(), "TextDocReader");

            // Create the storage directory if it does not exist
            if (!filesDir.exists()) {
                if (!filesDir.mkdirs()) {
                    Log.e(TAG, "Failed to create directory");
                }
            }

            File downloadedFile = new File(filesDir, "Response.txt");

            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];

                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(downloadedFile);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);

                    fileSizeDownloaded += read;

                    Log.d(TAG, "file download: " + fileSizeDownloaded + " of " + fileSize);
                }

                outputStream.flush();

                Log.i(TAG, "File saved: " + downloadedFile.getPath());
                mDownloadedFilePath = downloadedFile.getPath();

                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /*private void readAndWriteFile() {

        String jsonStringText = "{\"areas\":[{\"name\":\"Sinhala Text\",\"enabled\":true,\"contacts\":[{\"attribute1\":\"Attribute1 value\",\"attribute2\":\"Attribute2 value\",\"enabled\":true,\"images\":[\"abc\",\"pqr\"]},{\"attribute1\":\"Attribute1 value 1\",\"attribute2\":\"Attribute2 value 1\",\"enabled\":false,\"images\":[\"qwe\",\"rty\"]}]},{\"name\":\"Sinhala Text 2\",\"enabled\":false,\"contacts\":[{\"attribute1\":\"Attribute1 value 2\",\"attribute2\":\"Attribute2 value 2\",\"enabled\":true,\"images\":[\"lmn\",\"xyz\"]}]}]}";
        BeanJson convertedObject = new Gson().fromJson(jsonStringText, BeanJson.class);

        Log.d(TAG, "Test this");

    }*/

    private void createStringUsingTextFileAndCreateJsonObject(String responseTextFilePath) {
        Observable.just(responseTextFilePath)
                // map -> doInBackground (first arg input, second arg output)
                .map(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String filePath) {
                        try {


                            // String jsonStringText = new String(Files.readAllBytes(Paths.get(filePath)));
                            String jsonStringText = readUsingBufferedReader(filePath);
                            BeanJson convertedObject = new Gson().fromJson(jsonStringText, BeanJson.class);

                            Log.d(TAG, "Test this: " + convertedObject.getAreas().get(0).getName());

                            mTestText = convertedObject.getAreas().get(0).getName();

                            return true;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return false;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                // doOnSubscribe -> onPreExecute
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mPbProgressBar.setVisibility(View.VISIBLE);
                    }
                })
                // subscribe -> onPostExecute
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean workStatus) {
                        if (workStatus) {
                            mPbProgressBar.setVisibility(View.GONE);
                            setStatusText();
                        } else {
                            mPbProgressBar.setVisibility(View.GONE);
                            setStatusText();
                        }
                    }
                });
    }

    private void setStatusText() {
        if (mTestText != null && !mTestText.isEmpty()) {
            mTvReadWriteStatus.setText("Read Write Status: Successful\nTestText: " + mTestText);
        } else {
            mTvReadWriteStatus.setText("Read Write Status: Failed");
        }
    }

    private static String readUsingBufferedReader(String filePath) {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                contentBuilder.append(sCurrentLine).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }

    private void requestRuntimePermissionForAccessStorage() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_RUNTIME_PERMISSION_EXTERNAL_STORAGE);
            Toast.makeText(MainActivity.this, "Need this permission for save Text file", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(MainActivity.this, "Access Granted", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.i(TAG, "----> onRequestPermissionResult is called <----");

        switch (requestCode) {
            case REQUEST_RUNTIME_PERMISSION_EXTERNAL_STORAGE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.i(TAG, "----> External storage permission granted <----");
                    Toast.makeText(MainActivity.this, "Access Granted", Toast.LENGTH_SHORT).show();
                } else if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                    Log.i(TAG, "----> External storage permission denied <----");
                    boolean showRational = ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[0]);
                    if (showRational) {
                        Log.i(TAG, "----> Checkbox unchecked ---->");
                        requestRuntimePermissionForAccessStorage();
                        Toast.makeText(MainActivity.this, "Need this permission for save Text file", Toast.LENGTH_LONG).show();
                    } else {
                        Log.i(TAG, "----> Checkbox checked ---->");
                        Toast.makeText(MainActivity.this, "Please turn on storage permissions from settings", Toast.LENGTH_LONG).show();
                    }
                }
                break;

            default:
                break;
        }
    }

}
