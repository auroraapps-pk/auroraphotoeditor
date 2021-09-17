package com.auroraapps.auroraphotoeditor.AuroraPeterJackson.AuroraAnthonyPittorino;


import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.auroraapps.auroraphotoeditor.AuroraCantista.AuroraMantid;
import com.auroraapps.auroraphotoeditor.AuroraCantista.AuroraPrexissesta;
import com.auroraapps.auroraphotoeditor.AuroraDovCharney.AuroraPatrickCox;
import com.auroraapps.auroraphotoeditor.AuroraKayCohen.AuroraSusienChong;
import com.auroraapps.auroraphotoeditor.AuroraLocalBaseActivity;
import com.auroraapps.auroraphotoeditor.AuroraPeterJackson.AuroraJacobLuppino.AuroraDanniiMinogue;
import com.auroraapps.auroraphotoeditor.AuroraPeterJackson.AuroraJacobLuppino.AuroraKatiePerryuj;
import com.auroraapps.auroraphotoeditor.AuroraPeterJackson.AuroraJacobLuppino.AuroraPeterMorrissey;
import com.auroraapps.auroraphotoeditor.AuroraPeterJackson.AuroraJacobLuppino.AuroraToniMaticevski;
import com.auroraapps.auroraphotoeditor.AuroraPeterJackson.AuroraJennyKee.AuroraBentesta;
import com.auroraapps.auroraphotoeditor.R;
import com.auroraapps.auroraphotoeditor.AuroraUtil.AuroraAds;
import com.google.android.gms.ads.InterstitialAd;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import jp.co.cyberagent.android.gpuimage.GPUImageColorBlendFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageFilterGroup;
import jp.co.cyberagent.android.gpuimage.GPUImageLightenBlendFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageScreenBlendFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageSoftLightBlendFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageView;


public class AuroraNevilleQuist extends Fragment implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {


    private String FinalURI;

    public AuroraNevilleQuist() {
        // Required empty public constructor
    }

    public static int currentLens = -1, currentGradient = -1;
    RelativeLayout drawing_view_container;
    static GPUImageView MainGPUImageView;
    ImageView MainImageView;

    int DisplayWidth, DisplayHeight;

    public static SeekBar seekbar;
    ImageView imgButtonImage;

    LinearLayout FL_Lighten, FL_Screen, FL_Color, FL_SoftLight, FL_Ies;

    static ArrayList<AuroraSusienChong> arrayList, arrayList1;

    static GPUImageLightenBlendFilter lightenBlendFilter;
    static GPUImageSoftLightBlendFilter softLightBlendFilter;
    static GPUImageColorBlendFilter colorBlendFilter;
    //    GPUImageLi
    static GPUImageScreenBlendFilter screenBlendFilter;

    static RecyclerView recyclerView;
    static LinearLayout LL_Recycler;
    static RecyclerView recyclerView2;

    public void HeaderControl(View rootView) {
        imgButtonImage = (ImageView) rootView.findViewById(R.id.imgButtonImage);
        imgButtonImage.setVisibility(View.VISIBLE);
        imgButtonImage.setImageResource(R.drawable.ic_next);
        imgButtonImage.setOnClickListener(this);

        TextView txtHeaderName = (TextView) rootView.findViewById(R.id.txtHeaderName);

        AllBlendFilters();
    }

    public void ButtomMenuControl(View rootView) {

        LL_Recycler = (LinearLayout) rootView.findViewById(R.id.LL_Recycler);
        FL_Lighten = (LinearLayout) rootView.findViewById(R.id.FL_Lighten);
        FL_Screen = (LinearLayout) rootView.findViewById(R.id.FL_Screen);
        FL_Color = (LinearLayout) rootView.findViewById(R.id.FL_Color);
        FL_SoftLight = (LinearLayout) rootView.findViewById(R.id.FL_SoftLight);
        FL_Ies = (LinearLayout) rootView.findViewById(R.id.FL_Ies);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        recyclerView2 = (RecyclerView) rootView.findViewById(R.id.recyclerView2);

        FL_SoftLight.setOnClickListener(this);
        FL_Lighten.setOnClickListener(this);
        FL_Screen.setOnClickListener(this);
        FL_Color.setOnClickListener(this);
        FL_Ies.setOnClickListener(this);

        seekbar = (SeekBar) rootView.findViewById(R.id.seekbar);

        seekbar.setMax(255);
        seekbar.setProgress(255);
        seekbar.setOnSeekBarChangeListener(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_next, container, false);
        if(AuroraAds.mInterstitialAd == null){
            AuroraAds.LoadAd(getContext());
        }


        try {

            mContext = getActivity();

            findControls(rootView);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return rootView;
    }

    private void findControls(View rootView) {
        drawing_view_container = (RelativeLayout) rootView.findViewById(R.id.drawing_view_container);

        Display display = getActivity().getWindowManager().getDefaultDisplay();
        DisplayWidth = display.getWidth();
        DisplayHeight = display.getHeight();

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(DisplayWidth, DisplayWidth);
        drawing_view_container.setLayoutParams(layoutParams);

        MainGPUImageView = (GPUImageView) rootView.findViewById(R.id.MainGPUImageView);
        MainImageView = (ImageView) rootView.findViewById(R.id.MainImageView);

        ButtomMenuControl(rootView);
        HeaderControl(rootView);

        MainGPUImageView.setImage(AuroraPatrickCox.bitmap);
    }

    public void AllBlendFilters() {
        lightenBlendFilter = new GPUImageLightenBlendFilter();
        softLightBlendFilter = new GPUImageSoftLightBlendFilter();
        colorBlendFilter = new GPUImageColorBlendFilter();
        screenBlendFilter = new GPUImageScreenBlendFilter();
        filterGroup = new GPUImageFilterGroup();
    }

    public static Bitmap blendImage = null;
    static GPUImageFilterGroup filterGroup;

    public static void ShowProgress() {
        progressDialog = new ProgressDialog(mContext);
        progressDialog.setMessage("Loading ...");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

    }

    AuroraPeterMorrissey screenAdapter;
    LinearLayoutManager linearLayoutManager;

    static ProgressDialog progressDialog;

    public static View SecondView = null, ThirdView = null;

    static int Counter = 0;

    public static String Value = "ColorBlend";

    static int AddCounter = 1;

    public static void displayAds() {
        AddCounter++;

    }


    public static void ApplyFilter(String Value) {
        switch (Value) {

            case "SoftLightBlend":
                softLightBlendFilter.setBitmap(blendImage);
                MainGPUImageView.setFilter(softLightBlendFilter);
                break;
            case "ColorBlend":
                colorBlendFilter.setBitmap(blendImage);
                MainGPUImageView.setFilter(colorBlendFilter);
                break;
            case "ScreenBlend":
                screenBlendFilter.setBitmap(blendImage);
                MainGPUImageView.setFilter(screenBlendFilter);
                break;
            case "LightenBlend":
                lightenBlendFilter.setBitmap(blendImage);
                MainGPUImageView.setFilter(lightenBlendFilter);
                break;
            case "IES":
                if (blendImage == null) {
//                    blendImage = BitmapFactory.decodeResource(mContext.getResources(), ArrayDataList.getIes[0]);
                }
                if (AuroraPatrickCox.BlurBitmapTemp == null) {
//                    CommonUtilities.BlurBitmapTemp = BitmapFactory.decodeResource(mContext.getResources(), ArrayDataList.getColor[0]);
                }
                screenBlendFilter.setBitmap(blendImage);
                colorBlendFilter.setBitmap(AuroraPatrickCox.BlurBitmapTemp);

                filterGroup.addFilter(screenBlendFilter);
                filterGroup.addFilter(colorBlendFilter);

                MainGPUImageView.setFilter(filterGroup);
                break;
        }
    }

    public void BindData(String Value) {

        switch (Value) {

            case "SoftLightBlend":
                recyclerView2.setVisibility(View.GONE);
                arrayList = new ArrayList<>();
                try {
                    arrayList = new ArrayList<>();
                    AssetManager assetManager = getActivity().getResources().getAssets();

                    String files[] = assetManager.list("softlight/soft");
                    if (files != null) {
                        for (String file : files) {
                            arrayList.add(new AuroraSusienChong("softlight/soft/" + file));
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }


                linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                recyclerView.setLayoutManager(linearLayoutManager);

                AuroraKatiePerryuj softLightAdapter = new AuroraKatiePerryuj(arrayList, getActivity());
                recyclerView.setAdapter(softLightAdapter);
                break;
            case "ColorBlend":
                recyclerView2.setVisibility(View.GONE);
                arrayList = new ArrayList<>();
                try {
                    arrayList = new ArrayList<>();
                    AssetManager assetManager = getActivity().getResources().getAssets();

                    String files[] = assetManager.list("color/color");
                    if (files != null) {
                        for (String file : files) {
                            arrayList.add(new AuroraSusienChong("color/color/" + file));
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                recyclerView.setLayoutManager(linearLayoutManager);

                AuroraToniMaticevski colorAdapter = new AuroraToniMaticevski(arrayList, getActivity());
                recyclerView.setAdapter(colorAdapter);
                break;
            case "ScreenBlend":
                recyclerView2.setVisibility(View.GONE);
                arrayList = new ArrayList<>();

                try {
                    arrayList = new ArrayList<>();
                    AssetManager assetManager = getActivity().getResources().getAssets();

                    String files[] = assetManager.list("screen/screen");
                    if (files != null) {
                        for (String file : files) {
                            arrayList.add(new AuroraSusienChong("screen/screen/" + file));
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                recyclerView.setLayoutManager(linearLayoutManager);

                screenAdapter = new AuroraPeterMorrissey(arrayList, getActivity());
                recyclerView.setAdapter(screenAdapter);

                break;
            case "LightenBlend":
                recyclerView2.setVisibility(View.GONE);
                arrayList = new ArrayList<>();
                try {
                    arrayList = new ArrayList<>();
                    AssetManager assetManager = getActivity().getResources().getAssets();

                    String files[] = assetManager.list("lighten/lighten");
                    if (files != null) {
                        for (String file : files) {
                            arrayList.add(new AuroraSusienChong("lighten/lighten/" + file));
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                recyclerView.setLayoutManager(linearLayoutManager);

                AuroraDanniiMinogue lightenAdapter = new AuroraDanniiMinogue(arrayList, getActivity());
                recyclerView.setAdapter(lightenAdapter);

                break;
        }
    }

    public static void ManageBackpraced() {
        if (Counter != 0) {
            if (Counter == 1) {
                Counter = 0;
//                SingleflyOut(LL_Recycler);
                SingleflyOut(SecondView);
                SecondView = null;
                ThirdView = null;
            } else if (Counter == 2) {
                Counter = 0;
                SingleflyOut(SecondView);
                SecondView = null;
                ThirdView = null;
            }
        } else {

            ((AuroraBentesta) mContext).showResetDialog(mContext, new AuroraLocalBaseActivity.OnResetListner() {
                @Override
                public void onReset() {
                    mContext.startActivity(new Intent(mContext, AuroraMantid.class));
                    AuroraBentesta.activity.finish();
                    AuroraBentesta.activity.overridePendingTransition(R.anim.right_in, R.anim.left_out);
                }
            });

        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onClick(View v) {

        displayAds();


        switch (v.getId()) {

            case R.id.FL_Lighten:
                Value = "LightenBlend";
                seekbar.setMax(255);
                seekbar.setProgress(255);
                BindData(Value);
                Counter = 1;
                recyclerView2.setVisibility(View.GONE);
//                flyOut(LL_Recycler1, LL_Recycler);
                if (SecondView == null) {
                    SingleflyIn(LL_Recycler);
                    SecondView = LL_Recycler;
                } else if (SecondView != LL_Recycler) {
                    SecondView = LL_Recycler;
                    flyOut(SecondView, LL_Recycler);
                } else {
                    SecondView = LL_Recycler;
                    flyOut(SecondView, LL_Recycler);
                }
//                ApplyFilter("LightenBlend");
                break;
            case R.id.FL_Screen:
                Value = "ScreenBlend";
                seekbar.setMax(255);
                seekbar.setProgress(255);
                BindData(Value);
                Counter = 1;
                recyclerView2.setVisibility(View.GONE);
//                flyOut(LL_Recycler1, LL_Recycler);
                if (SecondView == null) {
                    SingleflyIn(LL_Recycler);
                    SecondView = LL_Recycler;
                } else if (SecondView != LL_Recycler) {
                    SecondView = LL_Recycler;
                    flyOut(SecondView, LL_Recycler);
                } else {
                    SecondView = LL_Recycler;
                    flyOut(SecondView, LL_Recycler);
                }
//                ApplyFilter("ScreenBlend");
                break;
            case R.id.FL_SoftLight:
                Value = "SoftLightBlend";
                seekbar.setMax(255);
                seekbar.setProgress(255);
                BindData(Value);
                Counter = 1;
                recyclerView2.setVisibility(View.GONE);
//                flyOut(LL_Recycler1, LL_Recycler);
                if (SecondView == null) {
                    SingleflyIn(LL_Recycler);
                    SecondView = LL_Recycler;
                } else if (SecondView != LL_Recycler) {
                    SecondView = LL_Recycler;
                    flyOut(SecondView, LL_Recycler);
                } else {
                    SecondView = LL_Recycler;
                    flyOut(SecondView, LL_Recycler);
                }
//                ApplyFilter("SoftLightBlend");
                break;
            case R.id.FL_Color:
                Value = "ColorBlend";
                seekbar.setMax(255);
                seekbar.setProgress(255);
                BindData(Value);
                Counter = 1;
                recyclerView2.setVisibility(View.GONE);
//                flyOut(LL_Recycler1, LL_Recycler);
                if (SecondView == null) {
                    SingleflyIn(LL_Recycler);
                    SecondView = LL_Recycler;
                } else if (SecondView != LL_Recycler) {
                    SecondView = LL_Recycler;
                    flyOut(SecondView, LL_Recycler);
                } else {
                    SecondView = LL_Recycler;
                    flyOut(SecondView, LL_Recycler);
                }
//                ApplyFilter("ColorBlend");
                break;
            case R.id.FL_Ies:
                Value = "IES";
                seekbar.setMax(255);
                seekbar.setProgress(255);
                BindData(Value);
                Counter = 2;

//                flyOut(LL_Recycler, LL_Recycler1);
                if (SecondView == null) {
                    SingleflyIn(LL_Recycler);
                    SecondView = LL_Recycler;
                } else if (SecondView != LL_Recycler) {
                    SecondView = LL_Recycler;
                    flyOut(SecondView, LL_Recycler);
                } else {
                    SecondView = LL_Recycler;
                    flyOut(SecondView, LL_Recycler);
                }
//                ApplyFilter("ColorBlend");
                break;
            case R.id.imgButtonImage:
                SecondView = null;
                new SaveImage().execute();
                break;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    private class BlendOpacityImage1 extends AsyncTask<Object, Integer, String> {

        int progress;

        Bitmap bmp;

        public BlendOpacityImage1(int progress) {
            this.progress = progress;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ShowProgress();
        }

        @Override
        protected String doInBackground(Object... params) {

            try {
                bmp = adjustOpacity(AuroraPatrickCox.BlurBitmapTemp, progress);

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //dia.dismiss();
            switch (Value) {

                case "IES":
                    colorBlendFilter.setBitmap(bmp);
                    MainGPUImageView.setFilter(colorBlendFilter);
                    break;
            }

            progressDialog.dismiss();

        }

    }

    private class BlendOpacityImage extends AsyncTask<Object, Integer, String> {

        int progress;
        Bitmap bmp, getBmp;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ShowProgress();
        }

        public BlendOpacityImage(int progress, Bitmap bitmap) {
            this.progress = progress;
            this.getBmp = bitmap;
        }

        @Override
        protected String doInBackground(Object... params) {

            try {
                bmp = adjustOpacity(getBmp, progress);


            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //dia.dismiss();
            switch (Value) {

                case "LightenBlend":
                    lightenBlendFilter.setBitmap(bmp);
                    MainGPUImageView.setFilter(lightenBlendFilter);
                    break;

                case "ScreenBlend":
                    screenBlendFilter.setBitmap(bmp);
                    MainGPUImageView.setFilter(screenBlendFilter);
                    break;

                case "SoftLightBlend":
                    softLightBlendFilter.setBitmap(bmp);
                    MainGPUImageView.setFilter(softLightBlendFilter);
                    break;

                case "ColorBlend":
                    colorBlendFilter.setBitmap(bmp);
                    MainGPUImageView.setFilter(colorBlendFilter);
                    break;
                case "IES":
                    colorBlendFilter.setBitmap(bmp);
                    MainGPUImageView.setFilter(colorBlendFilter);
                    break;
            }

            progressDialog.dismiss();

        }
    }

    public String saveImageToSD(Bitmap bmp, String filename, Bitmap.CompressFormat format) {
        File file2 = null;
        try {
            String path1 = Environment.getExternalStorageDirectory()
                    .toString();
            FileOutputStream fos = null;
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            bmp.compress(format, 100, bytes);
            File file1 = new File(path1 + "/RPMovieFXPhoto/Gallery/");
            if (!file1.exists()) {
                file1.mkdirs();
            }
            // Log.e("TAG", "File name : " + file1.getAbsolutePath());
            file2 = new File(file1, filename);
            try {
                file2.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fos = new FileOutputStream(file2);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                fos.write(bytes.toByteArray());
                fos.close();
                Log.e("Success", "Final Image Saved - " + filename);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (dia.isShowing()) {
                dia.dismiss();
            }
            AuroraPatrickCox.FinalBitmap = bmp;
            FinalURI = "" + path1 + "/RPMovieFXPhoto/Gallery/" + filename;


            ContentValues image = new ContentValues();
            String dateStr = "04/05/2010";

            SimpleDateFormat curFormater = new SimpleDateFormat("dd/MM/yyyy");
            Date dateObj = curFormater.parse(dateStr);
            SimpleDateFormat postFormater = new SimpleDateFormat("MMMM dd, yyyy");

            String newDateStr = postFormater.format(dateObj);
            image.put(MediaStore.Images.Media.TITLE, filename);
            image.put(MediaStore.Images.Media.DISPLAY_NAME, filename);
            image.put(MediaStore.Images.Media.DESCRIPTION, filename);
            image.put(MediaStore.Images.Media.DATE_ADDED, newDateStr);
            image.put(MediaStore.Images.Media.DATE_TAKEN, "");
            image.put(MediaStore.Images.Media.DATE_MODIFIED, "");
            image.put(MediaStore.Images.Media.MIME_TYPE, "image/*");
            image.put(MediaStore.Images.Media.ORIENTATION, 0);

            File parent = file2.getParentFile();
            String path = parent.toString().toLowerCase();
            String name = parent.getName().toLowerCase();
            image.put(MediaStore.Images.ImageColumns.BUCKET_ID, path.hashCode());
            image.put(MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME, name);
            image.put(MediaStore.Images.Media.SIZE, file2.length());

            image.put(MediaStore.Images.Media.DATA, file2.getAbsolutePath());


            Uri result = getActivity().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, image);


            return file2.getPath().toString();
        } catch (NullPointerException e) {
            // TODO: handle exception
            Log.e("error", "SAve to disk");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "";
    }

    private Bitmap adjustOpacity(Bitmap bitmap, int opacity) {
        Bitmap mutableBitmap = bitmap.isMutable()
                ? bitmap
                : bitmap.copy(Bitmap.Config.ARGB_8888, true);
        Canvas canvas = new Canvas(mutableBitmap);
        int colour = (opacity & 0xFF) << 24;
        canvas.drawColor(colour, PorterDuff.Mode.DST_IN);
        return mutableBitmap;
    }


    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        if (seekBar.getId() == R.id.seekbar) {

            if (Value.equals("IES"))
                new BlendOpacityImage(seekBar.getProgress(), AuroraPatrickCox.BlurBitmapTemp).execute();
            else
                new BlendOpacityImage(seekBar.getProgress(), blendImage).execute();
        }

    }

    private class SaveImage extends AsyncTask<Object, Integer, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgress();
        }

        @Override
        protected String doInBackground(Object... params) {

            try {
                mergeAndSave();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            dia.dismiss();

            ((AuroraBentesta)getActivity()).showRatingDialog(true, new AuroraLocalBaseActivity.OnRateListner() {
                @Override
                public void onReminderLater() {
                    AuroraAds.Loadd(new AuroraAds.Ad_lisoner() {
                        @Override
                        public void onSucssec(InterstitialAd mInterstitialAd) {
                            Intent intent = new Intent(getActivity(), AuroraPrexissesta.class);
                            intent.putExtra("FinalURI", FinalURI);
                            startActivity(intent);
                            AuroraBentesta.activity.finish();
                            getActivity().overridePendingTransition(R.anim.right_in, R.anim.left_out);
                        }

                        @Override
                        public void onun() {
                            Intent intent = new Intent(getActivity(), AuroraPrexissesta.class);
                            intent.putExtra("FinalURI", FinalURI);
                            startActivity(intent);
                            AuroraBentesta.activity.finish();
                            getActivity().overridePendingTransition(R.anim.right_in, R.anim.left_out);
                        }
                    });
                }
            });

        }
    }

    //    Animation
    private static Context mContext;

    private static Animation animation;

    public static void flyOut(final View view, final View viewFlyIn) {

        animation = AnimationUtils.loadAnimation(mContext, R.anim.holder_bottom_back_fast);
        view.startAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation arg0) {
            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
            }

            @Override
            public void onAnimationEnd(Animation arg0) {
                animation.setAnimationListener(null);
                view.clearAnimation();
                view.setVisibility(View.GONE);
                flyIn(viewFlyIn);
                SecondView = viewFlyIn;
                viewFlyIn.setVisibility(View.VISIBLE);
            }

        });
    }

    public static void flyIn(final View view) {

        animation = AnimationUtils.loadAnimation(mContext, R.anim.holder_bottom_fast);
        view.startAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation arg0) {
            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
            }

            @Override
            public void onAnimationEnd(Animation arg0) {
                animation.setAnimationListener(null);
                view.clearAnimation();
            }

        });

    }

    public static void SingleflyOut(final View view) {

        animation = AnimationUtils.loadAnimation(mContext, R.anim.holder_bottom_back_fast);
        view.startAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation arg0) {
            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
            }

            @Override
            public void onAnimationEnd(Animation arg0) {
                animation.setAnimationListener(null);
                view.clearAnimation();
                view.setVisibility(View.GONE);
            }

        });
    }

    public static void SingleflyOutThirdView(final View view) {

        animation = AnimationUtils.loadAnimation(mContext, R.anim.holder_bottom_back_fast);
        view.startAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation arg0) {
            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
            }

            @Override
            public void onAnimationEnd(Animation arg0) {
                animation.setAnimationListener(null);
                view.clearAnimation();
                view.setVisibility(View.GONE);
                ThirdView = null;
            }

        });
    }

    private static void SingleflyIn(final View view) {

        animation = AnimationUtils.loadAnimation(mContext, R.anim.holder_bottom_fast);
        view.setVisibility(View.VISIBLE);
        view.startAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation arg0) {
            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
            }

            @Override
            public void onAnimationEnd(Animation arg0) {
                animation.setAnimationListener(null);
                view.clearAnimation();
            }

        });

    }

    // Save Image Code
    public void mergeAndSave() {
        Bitmap bmOverlay = Bitmap.createBitmap(drawing_view_container.getWidth(), drawing_view_container.getHeight(), Bitmap.Config.ARGB_8888);

        try {
            bmOverlay = MainGPUImageView.capture();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        saveImageToSD(bmOverlay, "photox_" + generateRandomName(1000000, 5000000) + ".jpg", Bitmap.CompressFormat.JPEG);
        Log.i("TAG", "Image Created");
    }

    private int generateRandomName(int LowerLimit, int UpperLimit) {

        Random r = new Random();
        return r.nextInt((UpperLimit - LowerLimit) + 1) + LowerLimit;
    }

    ProgressDialog dia;

    public void showProgress() {
        //pDialog.show();

        dia = new ProgressDialog(getActivity());
        dia.setMessage("Loading ...");
        dia.setIndeterminate(false);
        dia.setCancelable(false);
        dia.setCanceledOnTouchOutside(false);
        dia.show();
    }


}
