package com.example.turtleneckdiagnosticapplication.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.turtleneckdiagnosticapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class ResultActivity extends AppCompatActivity {
    private static final String TAG = "ResultActivity";
    private ImageView postureImageView;

    private ImageView whiteImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user == null) {
            myStartActivity(LoginActivity.class);
        } else {
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            DocumentReference docRef = db.collection("users").document(user.getUid());
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if(document != null){
                            if (document.exists()) {
                                Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                            } else {
                                Log.d(TAG, "No such document");
                                myStartActivity(MemberInitActivity.class);
                            }
                        }
                    } else {
                        Log.d(TAG, "get failed with ", task.getException());
                    }
                }
            });
        }

        postureImageView = findViewById(R.id.postureImage);
        postureImageView.setOnClickListener(onClickListener);
        findViewById(R.id.logoutButton).setOnClickListener(onClickListener);
        findViewById(R.id.cameraButton).setOnClickListener(onClickListener);
        findViewById(R.id.galleryButton).setOnClickListener(onClickListener);
        findViewById(R.id.stretchingButton).setOnClickListener(onClickListener);
        findViewById(R.id.chimaekButton).setOnClickListener(onClickListener);
        findViewById(R.id.diagnosticButton).setOnClickListener(onClickListener);

        whiteImageView = findViewById(R.id.whiteImage);
        whiteImageView.setOnClickListener(onClickListener);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 0: {
                if (resultCode == Activity.RESULT_OK) {
                    String posturePath = data.getStringExtra("posturePath");
                    Log.e("로그: ", "posturePath: " + posturePath);

                    Bitmap bmp = BitmapFactory.decodeFile(posturePath);

                    if (bmp != null) {
                        ExifInterface ei = null;
                        try {
                            ei = new ExifInterface(posturePath);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                                ExifInterface.ORIENTATION_UNDEFINED);

                        Bitmap rotatedBitmap = null;
                        switch (orientation) {
                            case ExifInterface.ORIENTATION_ROTATE_90:
                                rotatedBitmap = rotateImage(bmp, 90);
                                break;
                            case ExifInterface.ORIENTATION_ROTATE_180:
                                rotatedBitmap = rotateImage(bmp, 180);
                                break;
                            case ExifInterface.ORIENTATION_ROTATE_270:
                                rotatedBitmap = rotateImage(bmp, 270);
                                break;
                            case ExifInterface.ORIENTATION_NORMAL:
                            default:
                                rotatedBitmap = bmp;
                        }
                        postureImageView.setImageBitmap(rotatedBitmap);
                    }
                }
                break;
            }

            case 1: {
                if (resultCode == Activity.RESULT_OK) {
                    Uri selectedImageUri = data.getData();
                    postureImageView.setImageURI(selectedImageUri);
                }
                break;
            }
        }
    }

    public static Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(),
                matrix, true);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @SuppressLint("NonConstantResourceId")
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.logoutButton:
                    FirebaseAuth.getInstance().signOut();
                    myStartActivity(LoginActivity.class);
                    break;
                case R.id.postureImage:
                    CardView cardView1 = findViewById(R.id.buttonsCardView);
                    CardView cardView2 = findViewById(R.id.cardView2);
                    if(cardView1.getVisibility() == View.VISIBLE){
                        cardView2.setVisibility(View.GONE);
                        cardView1.setVisibility(View.GONE);
                    } else {
                        cardView2.setVisibility(View.GONE);
                        cardView1.setVisibility(View.VISIBLE);
                    }
                    break;
                case R.id.cameraButton:
                    cardView1 = findViewById(R.id.buttonsCardView);
                    cardView2 = findViewById(R.id.cardView2);
                    myStartActivity(CameraActivity.class);
                    cardView1.setVisibility(View.GONE);
                    cardView2.setVisibility(View.VISIBLE);
                    break;
                case R.id.galleryButton:
                    cardView1 = findViewById(R.id.buttonsCardView);
                    cardView2 = findViewById(R.id.cardView2);
                    if (ContextCompat.checkSelfPermission(ResultActivity.this,
                            Manifest.permission.READ_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(ResultActivity.this,
                                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                1);

                        if(ActivityCompat.shouldShowRequestPermissionRationale(ResultActivity.this,
                                Manifest.permission.READ_EXTERNAL_STORAGE)) {
                        } else {
                            startToast("권한을 허용해 주세요.");
                        }
                    } else {
                        myGalleryActivity();
                        cardView1.setVisibility(View.GONE);
                        cardView2.setVisibility(View.VISIBLE);
                    }
                    break;
                case R.id.stretchingButton:
                    myStartActivity(Video0Activity.class);
                    break;
                case R.id.chimaekButton:
                    myStartActivity(Video1Activity.class);
                    break;
                case R.id.diagnosticButton:
                    myStartActivity(Video2Activity.class);
                    break;
                case R.id.whiteImage:
                    CardView cardView3 = findViewById(R.id.resultCardView);
                    if(cardView3.getVisibility() == View.VISIBLE){
                        cardView3.setVisibility(View.GONE);
                    } else {
                        cardView3.setVisibility(View.VISIBLE);
                    }
                    break;
            }
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NotNull String permissions[], @NotNull int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // startToast("승인이 허가되어 있습니다.");
                    myGalleryActivity();
                } else {
                    // startToast("아직 승인받지 않았습니다.");
                    startToast("권한을 허용해 주세요.");
                }
                return;
            }

        }
    }

    private void startToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void myStartActivity(Class c) {
        Intent intent = new Intent(this, c);
        startActivityForResult(intent, 0);
    }

    private void myGalleryActivity() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent, 1);
    }
}