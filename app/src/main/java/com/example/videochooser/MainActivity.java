package com.example.videochooser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.VideoView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_TAKE_GALLERY_VIDEO = 1001;
    private VideoView mVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mVideoView = (VideoView) findViewById(R.id.videoView);
    }

    public void choosVideo(View view) {
        Intent intent = new Intent();
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Video"), REQUEST_TAKE_GALLERY_VIDEO);

    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_TAKE_GALLERY_VIDEO) {
                Uri selectedImageUri = data.getData();

                // OI FILE Manager
                String filemanagerstring = selectedImageUri.getPath();


                mVideoView.setVideoURI(selectedImageUri);
                mVideoView.start();
                // MEDIA GALLERY
                String selectedImagePath;
                try {
                    selectedImagePath = PathUtil.getPath(this, selectedImageUri);

                }catch (Exception e){
                    e.printStackTrace();
                    selectedImagePath=e.toString();
                }
                Log.d("selectedImagePath", ""+selectedImagePath);
            }
        }
    }

}
