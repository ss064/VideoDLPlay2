package com.example.suzukisusumu_sist.videodlplay;

import android.app.DownloadManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class VideoDLPlayActivity extends AppCompatActivity {
    DownloadManager  downloadManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_dlplay);

        //URIを生成する
        downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        Uri.Builder uriBuilder = new Uri.Builder();
        uriBuilder.scheme("http");
        uriBuilder.authority("j11000.sangi01.net");
        uriBuilder.path("/cakephp/iwish.mp4");

        DownloadManager.Request request = new DownloadManager.Request(uriBuilder.build());
        //保存場所、形式、ファイル名を指定
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_MOVIES,"/Signage/Test001.mp4");
        request.setTitle("Test.mp4");
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
        request.setMimeType("video/mp4");
        long id=downloadManager.enqueue(request);


        DownloadManager.Query query = new DownloadManager.Query();
        query.setFilterById(id);
        Cursor cursor = downloadManager.query(query);
        int idStatus = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS);
        cursor.moveToFirst();
        Log.d("DownloadManagerSample", cursor.getString(idStatus));

    }
}
