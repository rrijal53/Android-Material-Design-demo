package com.sochware.e_agrovet;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.model.Image;
import com.theartofdev.edmodo.cropper.CropImage;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CameraActivity extends AppCompatActivity {

    @BindView(R.id.btn)
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        ButterKnife.bind(this);


    }

    @OnClick(R.id.btn)
    void openImagePicker() {
        ImagePicker.create(this)
                .returnAfterFirst(true)
                .single()
                .start(100);
    }

    @OnClick(R.id.camera)
    void openCamera() {
//        ImagePicker.create(this)
//                .returnAfterFirst(true)
//                .single()
//                .start(100);
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        if (result == PackageManager.PERMISSION_DENIED) {
            requestCameraPermission();
            return;
        }
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        startActivity(intent);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 111) {
            openCamera();
        }
    }

    private void requestCameraPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {

        }

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 111);
    }


    @Override
    protected void onActivityResult(int requestCode, final int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 100) {
                List<Image> images = ImagePicker.getImages(data);
                if (images != null) {
                    Utilities.log("Images " + images.size());
                }


            } else if (requestCode == 101) {
                Uri uri = data.getData();
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivity(intent);
                // TODO: 6/25/18 USE IMAGE FROM CAMERA

            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public void ImageCropFunction()
    {
        try
        {

        }
        catch (ActivityNotFoundException e)
        {

        }
    }
}
