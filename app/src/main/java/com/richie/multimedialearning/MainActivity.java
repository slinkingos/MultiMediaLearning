package com.richie.multimedialearning;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.richie.multimedialearning.audiorecord.AudioRecordActivity;
import com.richie.multimedialearning.audiotrack.AudioTrackActivity;
import com.richie.multimedialearning.camera.CameraPreviewActivity;
import com.richie.multimedialearning.mp4.MediaCodecActivity;
import com.richie.multimedialearning.mp4.MediaMp4Activity;
import com.richie.multimedialearning.surfaceview.SurfaceActivity;

/**
 * Main 主界面
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_surface_view).setOnClickListener(this);
        findViewById(R.id.btn_audio_record).setOnClickListener(this);
        findViewById(R.id.btn_audio_track).setOnClickListener(this);
        findViewById(R.id.btn_camera_surface).setOnClickListener(this);
        findViewById(R.id.btn_media_mp4).setOnClickListener(this);
        findViewById(R.id.btn_media_codec).setOnClickListener(this);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.RECORD_AUDIO, Manifest.permission.CAMERA}, 0);
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.btn_surface_view:
                intent = new Intent(this, SurfaceActivity.class);
                break;
            case R.id.btn_audio_record:
                intent = new Intent(this, AudioRecordActivity.class);
                break;
            case R.id.btn_audio_track:
                intent = new Intent(this, AudioTrackActivity.class);
                break;
            case R.id.btn_camera_surface:
                String[] colors = {"SurfaceView Camera", "TextureView Camera", "SurfaceView Camera2"
                        , "TextureView Camera2"};
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Pick a preview type");
                builder.setItems(colors, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // the user clicked on colors[which]
                        int previewType;
                        if (which == 0) {
                            previewType = CameraPreviewActivity.TYPE_SURFACE_VIEW_CAMERA;
                        } else if (which == 1) {
                            previewType = CameraPreviewActivity.TYPE_TEXTURE_VIEW_CAMERA;
                        } else if (which == 2) {
                            previewType = CameraPreviewActivity.TYPE_SURFACE_VIEW_CAMER2;
                        } else {
                            previewType = CameraPreviewActivity.TYPE_TEXTURE_VIEW_CAMER2;
                        }
                        Intent intent = new Intent(MainActivity.this, CameraPreviewActivity.class);
                        intent.putExtra(CameraPreviewActivity.PREVIEW_TYPE, previewType);
                        startActivity(intent);
                    }
                });
                builder.show();
                break;
            case R.id.btn_media_mp4:
                intent = new Intent(this, MediaMp4Activity.class);
                break;
            case R.id.btn_media_codec:
                intent = new Intent(this, MediaCodecActivity.class);
                break;
            default:
        }
        if (intent != null) {
            startActivity(intent);
        }
    }
}
