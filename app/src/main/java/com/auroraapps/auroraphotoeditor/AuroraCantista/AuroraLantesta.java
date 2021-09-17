package com.auroraapps.auroraphotoeditor.AuroraCantista;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;

import com.auroraapps.auroraphotoeditor.AuroraDovCharney.AuroraDeanandDanCaten;
import com.auroraapps.auroraphotoeditor.R;


public class AuroraLantesta extends Activity {

    private static final int PERMISSION_CALLBACK_CONSTANT = 100;
    private static final int REQUEST_PERMISSION_SETTING = 101;
    String[] permissionsRequired = new String[]{Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE};
    private SharedPreferences permissionStatus;
    private boolean sentToSettings = false;

    AuroraDeanandDanCaten objPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        objPref = new AuroraDeanandDanCaten(this);

        if (android.os.Build.VERSION.SDK_INT > 22) {
            permissionStatus = getSharedPreferences("permissionStatus", MODE_PRIVATE);

            if (ActivityCompat.checkSelfPermission(AuroraLantesta.this, permissionsRequired[0]) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.checkSelfPermission(AuroraLantesta.this, permissionsRequired[1]) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.checkSelfPermission(AuroraLantesta.this, permissionsRequired[2]) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(AuroraLantesta.this, permissionsRequired[0])
                        || ActivityCompat.shouldShowRequestPermissionRationale(AuroraLantesta.this, permissionsRequired[1])
                        || ActivityCompat.shouldShowRequestPermissionRationale(AuroraLantesta.this, permissionsRequired[2])) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(AuroraLantesta.this);
                    builder.setTitle("Need Multiple Permissions");
                    builder.setMessage("This app needs Camera and Location permissions.");
                    builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            ActivityCompat.requestPermissions(AuroraLantesta.this, permissionsRequired, PERMISSION_CALLBACK_CONSTANT);
                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.show();
                } else if (permissionStatus.getBoolean(permissionsRequired[0], false)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(AuroraLantesta.this);
                    builder.setTitle("Need Multiple Permissions");
                    builder.setMessage("This app needs Camera and Location permissions.");
                    builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            sentToSettings = true;
                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            Uri uri = Uri.fromParts("package", getPackageName(), null);
                            intent.setData(uri);
                            startActivityForResult(intent, REQUEST_PERMISSION_SETTING);
                            Toast.makeText(getBaseContext(), "Go to Permissions to Grant  Camera and Location", Toast.LENGTH_LONG).show();
                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.show();
                } else {
                    ActivityCompat.requestPermissions(AuroraLantesta.this, permissionsRequired, PERMISSION_CALLBACK_CONSTANT);
                }


                SharedPreferences.Editor editor = permissionStatus.edit();
                editor.putBoolean(permissionsRequired[0], true);
                editor.commit();
            } else {
                proceedAfterPermission();
            }
        } else {
            Log.e("Lower Version","Lower Version");
            this.finish();
            startActivity(new Intent(AuroraLantesta.this, AuroraLajavab.class));
            overridePendingTransition(R.anim.right_in, R.anim.left_out);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_CALLBACK_CONSTANT) {
            boolean allgranted = false;
            for (int grantResult : grantResults) {
                if (grantResult == PackageManager.PERMISSION_GRANTED) {
                    allgranted = true;
                } else {
                    allgranted = false;
                    break;
                }
            }

            if (allgranted) {
                proceedAfterPermission();
            } else if (ActivityCompat.shouldShowRequestPermissionRationale(AuroraLantesta.this, permissionsRequired[0])
                    || ActivityCompat.shouldShowRequestPermissionRationale(AuroraLantesta.this, permissionsRequired[1])
                    || ActivityCompat.shouldShowRequestPermissionRationale(AuroraLantesta.this, permissionsRequired[2])) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AuroraLantesta.this);
                builder.setTitle("Need Multiple Permissions");
                builder.setMessage("This app needs Camera and Location permissions.");
                builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        ActivityCompat.requestPermissions(AuroraLantesta.this, permissionsRequired, PERMISSION_CALLBACK_CONSTANT);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            } else {
                Toast.makeText(getBaseContext(), "Unable to get Permission", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_PERMISSION_SETTING) {
            if (ActivityCompat.checkSelfPermission(AuroraLantesta.this, permissionsRequired[0]) == PackageManager.PERMISSION_GRANTED) {
                proceedAfterPermission();
            }
        }
    }

    private void proceedAfterPermission() {
        this.finish();
        startActivity(new Intent(AuroraLantesta.this, AuroraLajavab.class));
        overridePendingTransition(R.anim.right_in, R.anim.left_out);

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        if (sentToSettings) {
            if (ActivityCompat.checkSelfPermission(AuroraLantesta.this, permissionsRequired[0]) == PackageManager.PERMISSION_GRANTED) {
                proceedAfterPermission();
            }
        }
    }
}
