package com.auroraapps.auroraphotoeditor.AuroraPentagon.AuroraSophiaTolli;


import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.PointF;
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
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.auroraapps.auroraphotoeditor.AuroraCantista.AuroraMantid;
import com.auroraapps.auroraphotoeditor.AuroraCantista.AuroraPrexissesta;
import com.auroraapps.auroraphotoeditor.AuroraChristopherEssex.AuroraJohnCrittle;
import com.auroraapps.auroraphotoeditor.AuroraChristopherEssex.AuroraLizDavenport;
import com.auroraapps.auroraphotoeditor.AuroraChristopherEssex.AuroraWayneCooper;
import com.auroraapps.auroraphotoeditor.AuroraDianaVonGrüning;
import com.auroraapps.auroraphotoeditor.AuroraDovCharney.AuroraPatrickCox;
import com.auroraapps.auroraphotoeditor.AuroraKayCohen.AuroraSusienChong;
import com.auroraapps.auroraphotoeditor.AuroraLocalBaseActivity;
import com.auroraapps.auroraphotoeditor.AuroraPentagon.AuroraAmberRenae.AuroraTempoll;
import com.auroraapps.auroraphotoeditor.AuroraPentagon.AuroraBrunoSchiavi.AuroraJoeSaba;
import com.auroraapps.auroraphotoeditor.AuroraPentagon.AuroraBrunoSchiavi.AuroraPaulaStafford;
import com.auroraapps.auroraphotoeditor.AuroraPentagon.AuroraBrunoSchiavi.AuroraSheilaScotter;
import com.auroraapps.auroraphotoeditor.R;
import com.auroraapps.auroraphotoeditor.AuroraUtil.AuroraAds;
import com.auroraapps.auroraphotoeditor.AuroramultiTouchLib.AuroraMultiTouchListener;
import com.auroraapps.auroraphotoeditor.AurorastickerView.AuroraStickerView;
import com.auroraapps.auroraphotoeditor.AuroratextviewBubble.AuroraBubbleInputDialog;
import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;
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
import java.util.List;
import java.util.Random;

import it.sephiroth.android.library.widget.AdapterView;
import it.sephiroth.android.library.widget.HListView;
import jp.co.cyberagent.android.gpuimage.GPUImageBrightnessFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageColorBalanceFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageContrastFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageExposureFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageFilterGroup;
import jp.co.cyberagent.android.gpuimage.GPUImageHueFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageLevelsFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageMonochromeFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageSaturationFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageSepiaFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageSharpenFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageToneCurveFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageView;
import jp.co.cyberagent.android.gpuimage.GPUImageVignetteFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageWhiteBalanceFilter;

public class AuroraAndyTruong extends Fragment implements View.OnClickListener, SeekBar.OnSeekBarChangeListener, AuroraBubbleInputDialog.CompleteCallBack {


    private String FinalURI;

    public AuroraAndyTruong() {
    }

    GPUImageView MainGPUImageView;

    public static AuroraStickerView sticker_view;

    FrameLayout FrameLayoutText;

    public static RelativeLayout MainContainer;

    public static ImageView imgTextClose;
    public static TextView textbubble;
    Boolean textflag = true;

    public static RecyclerView ABCsCategory, ABCFont;
    AuroraBubbleInputDialog mAuroraBubbleInputDialog;

    public static HListView curveList;
    LinearLayout MainMenuContainer;
    RelativeLayout drawing_view_container;

    private static int DisplayWidth, DisplayHeight;

    String flag = "";
    int PrevCurvePosition = 0, PrevBrightness = 50, PrevContrast = 50, PrevSaturation = 50, PrevVignette = 75, PrevSharpness = 50, PrevHue = 0, PrevSepia = 0, PrevMonochrome = 0, PrevWhiteBalance = 100, PrevColorBalance = 0, PrevLevels = 100, PrevExposure = 50, PrevOpacity = 255;

    public static View MainMenu;
    LinearLayout LL_Brightness, LL_Contrast, LL_Saturation, LL_Vignette, LL_Curve, LL_Sharpness, LL_hue, LL_Sepia, LL_Monochrome, LL_WhiteBalance, LL_ColorBalance, LL_Level, LL_Exposure, LL_Text, LL_AddText, LL_FontStyle, LL_TextColor, LL_TextOpacity, LL_ABCs;
    public static LinearLayout LL_MainMenu, fragment_Blur, LL_Sticker, LL_TextMainLayout;
    public static int Counter = 0, AddCounter = 0;
    ImageView ic_clear;
    SeekBar seekbar;
    TextView txtProgressValue;

    private Bitmap smallImageBackgroud;

    List<AuroraLizDavenport> filters;

    public static ImageView imgButtonImage, imgReset;

    static RelativeLayout adViewContainer;

    ImageView imgTemp;

    boolean flagimgtemp = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.photoeditor_fragment_filters, container, false);

        if(AuroraAds.mInterstitialAd == null){
            AuroraAds.LoadAd(getContext());
        }

        try {
            mContext = getActivity();
            showProgress();

            filters = AuroraWayneCooper.getInst().getLocalFilters();

            getActivity().setTitle("Filters");

            FindControls(rootView);
            HeaderControl(rootView);

            Display display = getActivity().getWindowManager().getDefaultDisplay();
            DisplayWidth = display.getWidth();
            DisplayHeight = display.getHeight();


            imgTemp = (ImageView) rootView.findViewById(R.id.imgTemp);

            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(AuroraPatrickCox.bitmap.getWidth(), AuroraPatrickCox.bitmap.getHeight());

            imgTemp.setImageBitmap(AuroraPatrickCox.bitmap);

            ViewTreeObserver vto = imgTemp.getViewTreeObserver();
            vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {

                    if (flagimgtemp) {
                        int finalHeight = imgTemp.getMeasuredHeight();
                        int finalWidth = imgTemp.getMeasuredWidth();
                        flagimgtemp = false;
                        RelativeLayout.LayoutParams imageLayoutParams = new RelativeLayout.LayoutParams(DisplayWidth, DisplayWidth);
                        imageLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
                        drawing_view_container.setLayoutParams(imageLayoutParams);
                        imgTemp.setVisibility(View.GONE);
                        MainGPUImageView.setImage(AuroraPatrickCox.bitmap);
                    }

                    return true;
                }
            });

            InflateBottomMenuLayout();

            CurveImage();

            setFilters();

            dia.dismiss();

        } catch (Exception e) {
            e.printStackTrace();
            dia.dismiss();
        }


        return rootView;
    }

    public void getFilesFromAssets(String FolderName) {
        ArrayList<AuroraSusienChong> arrayList;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        ABCsCategory.setLayoutManager(layoutManager);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        ABCFont.setLayoutManager(layoutManager1);

        switch (FolderName) {

            case "ABC":
                try {
                    arrayList = new ArrayList<>();
                    AssetManager assetManager = getActivity().getResources().getAssets();

                    String files[] = assetManager.list(FolderName.toLowerCase());
                    if (files != null) {
                        for (String file : files) {
                            arrayList.add(new AuroraSusienChong(FolderName.toLowerCase() + "/" + file));
                        }

                        AuroraJoeSaba adapter = new AuroraJoeSaba(arrayList, getActivity());
                        ABCsCategory.setAdapter(adapter);

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case "Sticker":
                try {
                    arrayList = new ArrayList<>();
                    AssetManager assetManager = getActivity().getResources().getAssets();

                    String files[] = assetManager.list(FolderName.toLowerCase());
                    if (files != null) {
                        for (String file : files) {
                            arrayList.add(new AuroraSusienChong(FolderName.toLowerCase() + "/" + file));
                        }

                        AuroraSheilaScotter adapter = new AuroraSheilaScotter(arrayList, getActivity());
                        ABCsCategory.setAdapter(adapter);

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case "Fonts":
                try {
                    arrayList = new ArrayList<>();
                    AssetManager assetManager = getActivity().getResources().getAssets();

                    String files[] = assetManager.list(FolderName.toLowerCase());
                    if (files != null) {
                        for (String file : files) {
                            arrayList.add(new AuroraSusienChong(FolderName.toLowerCase() + "/" + file));
                        }

                        AuroraPaulaStafford adapter = new AuroraPaulaStafford(arrayList, getActivity());
                        ABCFont.setAdapter(adapter);

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

        }

    }

    public void HeaderControl(View view) {
        imgButtonImage = (ImageView) view.findViewById(R.id.imgButtonImage);
        imgReset = (ImageView) view.findViewById(R.id.imgReset);
        imgReset.setVisibility(View.VISIBLE);
        imgButtonImage.setImageResource(R.drawable.ic_next);
        imgButtonImage.setOnClickListener(this);
        imgReset.setOnClickListener(this);
    }

    public void FindControls(View view) {

        MainGPUImageView = (GPUImageView) view.findViewById(R.id.MainGPUImageView);
        curveList = (HListView) view.findViewById(R.id.curve_List);
        MainMenuContainer = (LinearLayout) view.findViewById(R.id.toolbar_area);
        adViewContainer = (RelativeLayout) view.findViewById(R.id.adViewContainer);
        drawing_view_container = (RelativeLayout) view.findViewById(R.id.drawing_view_container);
        drawing_view_container.setDrawingCacheEnabled(true);
        drawing_view_container.buildDrawingCache();

        MainContainer = (RelativeLayout) view.findViewById(R.id.MainContainer);

        sticker_view = (AuroraStickerView) view.findViewById(R.id.sticker_view);
        FrameLayoutText = (FrameLayout) view.findViewById(R.id.FrameLayoutText);
        imgTextClose = (ImageView) view.findViewById(R.id.imgTextClose);
        textbubble = (TextView) view.findViewById(R.id.textbubble);

        FrameLayoutText.setOnTouchListener(new AuroraMultiTouchListener());
        imgTextClose.setOnClickListener(this);


    }

    public void CurveImage() {

        smallImageBackgroud = AuroraPatrickCox.bitmap;

        initCurveFilterToolBar();

    }

    public void InflateBottomMenuLayout() {
        try {

            LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            MainMenu = inflater.inflate(R.layout.photoeditor_final_image_menu, MainMenuContainer, false);
            MainMenuContainer.addView(MainMenu);

            LL_MainMenu = (LinearLayout) MainMenu.findViewById(R.id.LL_MainMenu);
            fragment_Blur = (LinearLayout) MainMenu.findViewById(R.id.fragment_Blur);

            LL_Sharpness = (LinearLayout) MainMenu.findViewById(R.id.LL_Sharpness);

            LL_Brightness = (LinearLayout) MainMenu.findViewById(R.id.LL_Brightness);
            LL_Contrast = (LinearLayout) MainMenu.findViewById(R.id.LL_Contrast);
            LL_Saturation = (LinearLayout) MainMenu.findViewById(R.id.LL_Saturation);
            LL_Vignette = (LinearLayout) MainMenu.findViewById(R.id.LL_Vignette);
            LL_hue = (LinearLayout) MainMenu.findViewById(R.id.LL_hue);
            LL_Sepia = (LinearLayout) MainMenu.findViewById(R.id.LL_Sepia);

            LL_Monochrome = (LinearLayout) MainMenu.findViewById(R.id.LL_Monochrome);
            LL_WhiteBalance = (LinearLayout) MainMenu.findViewById(R.id.LL_WhiteBalance);
            LL_ColorBalance = (LinearLayout) MainMenu.findViewById(R.id.LL_ColorBalance);
            LL_Level = (LinearLayout) MainMenu.findViewById(R.id.LL_Level);
            LL_Exposure = (LinearLayout) MainMenu.findViewById(R.id.LL_Exposure);

            LL_Curve = (LinearLayout) MainMenu.findViewById(R.id.LL_Curve);
            LL_ABCs = (LinearLayout) MainMenu.findViewById(R.id.LL_ABCs);

            LL_Sticker = (LinearLayout) MainMenu.findViewById(R.id.LL_Sticker);
            LL_Text = (LinearLayout) MainMenu.findViewById(R.id.LL_Text);

            LL_TextMainLayout = (LinearLayout) MainMenu.findViewById(R.id.LL_TextMainLayout);
            LL_AddText = (LinearLayout) MainMenu.findViewById(R.id.LL_AddText);
            LL_FontStyle = (LinearLayout) MainMenu.findViewById(R.id.LL_FontStyle);
            LL_TextColor = (LinearLayout) MainMenu.findViewById(R.id.LL_TextColor);
            LL_TextOpacity = (LinearLayout) MainMenu.findViewById(R.id.LL_TextOpacity);

            ABCsCategory = (RecyclerView) MainMenu.findViewById(R.id.ABCsCategory);
            ABCFont = (RecyclerView) MainMenu.findViewById(R.id.ABCFont);

            ic_clear = (ImageView) MainMenu.findViewById(R.id.ic_clear);
            seekbar = (SeekBar) MainMenu.findViewById(R.id.seekbar);
            txtProgressValue = (TextView) MainMenu.findViewById(R.id.txtProgressValue);

            LL_Curve.setOnClickListener(this);
            LL_Brightness.setOnClickListener(this);
            LL_Contrast.setOnClickListener(this);
            LL_Saturation.setOnClickListener(this);
            LL_Vignette.setOnClickListener(this);
            LL_Sharpness.setOnClickListener(this);
            LL_hue.setOnClickListener(this);
            LL_Sepia.setOnClickListener(this);

            LL_Text.setOnClickListener(this);
            LL_FontStyle.setOnClickListener(this);
            LL_TextColor.setOnClickListener(this);
            LL_TextOpacity.setOnClickListener(this);

            LL_Monochrome.setOnClickListener(this);
            LL_WhiteBalance.setOnClickListener(this);
            LL_ColorBalance.setOnClickListener(this);
            LL_Level.setOnClickListener(this);
            LL_Exposure.setOnClickListener(this);

            LL_Sticker.setOnClickListener(this);
            LL_ABCs.setOnClickListener(this);
            LL_AddText.setOnClickListener(this);

            ic_clear.setOnClickListener(this);

            seekbar.setOnSeekBarChangeListener(this);

            mAuroraBubbleInputDialog = new AuroraBubbleInputDialog(getActivity());
            mAuroraBubbleInputDialog.setCompleteCallBack(this);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void viewColorPicker() {

        ColorPickerDialogBuilder
                .with(getActivity())
                .setTitle("Border Color")
                .initialColor(Color.parseColor("#ffffff"))
                .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                .density(12)
                .setOnColorSelectedListener(new OnColorSelectedListener() {
                    @Override
                    public void onColorSelected(int selectedColor) {
                        AddTextColor(selectedColor);
                    }
                })
                .setPositiveButton("ok", new ColorPickerClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
                        AddTextColor(selectedColor);
                        if (allColors != null) {
                            StringBuilder sb = null;

                            for (Integer color : allColors) {
                                if (color == null)
                                    continue;
                                if (sb == null)
                                    sb = new StringBuilder("Color List:");
                                sb.append("\r\n#" + Integer.toHexString(color).toUpperCase());
                            }
                        }
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .showColorEdit(true)
                .setColorEditTextColor(ContextCompat.getColor(getActivity(), android.R.color.holo_blue_bright))
                .build()
                .show();
    }

    private void initCurveFilterToolBar() {

        final AuroraJohnCrittle adapter = new AuroraJohnCrittle(getActivity(), filters, smallImageBackgroud);
        curveList.setAdapter(adapter);
        curveList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                if (adapter.getSelectFilter() != arg2) {


                    adapter.setSelectFilter(arg2);
                    PrevCurvePosition = arg2;
                    curveFilter.setFromCurveFileInputStream(getResources().openRawResource(filters.get(arg2).getFilterfileRaw()));

                    MainGPUImageView.setFilter(filterGroup);
                    AuroraAndyTruong.displayAds();
                }
            }
        });
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        if (seekBar.getId() == R.id.seekbar) {

            switch (flag) {
                case "Brightness":
                    PrevBrightness = progress;
                    txtProgressValue.setText("" + PrevBrightness);

                    if (mBrightnessFilterAdjuster != null) {
                        mBrightnessFilterAdjuster.adjust(PrevBrightness);
                    }
                    MainGPUImageView.requestRender();

                    break;

                case "Contrast":
                    PrevContrast = progress;
                    txtProgressValue.setText("" + progress);

                    if (mContrastFilterAdjuster != null) {
                        mContrastFilterAdjuster.adjust(PrevContrast);
                    }
                    MainGPUImageView.requestRender();
                    break;

                case "Saturation":
                    PrevSaturation = progress;
                    txtProgressValue.setText("" + PrevSaturation);

                    if (mSaturationFilterAdjuster != null) {
                        mSaturationFilterAdjuster.adjust(PrevSaturation);
                    }

                    MainGPUImageView.requestRender();

                    break;

                case "Vignette":
                    PrevVignette = progress;
                    txtProgressValue.setText("" + PrevVignette);

                    if (mVignetteFilterAdjuster != null) {
                        mVignetteFilterAdjuster.adjust(PrevVignette);
                    }

                    MainGPUImageView.requestRender();

                    break;

                case "Sharpness":
                    PrevSharpness = progress;
                    txtProgressValue.setText("" + PrevSharpness);

                    if (mSharpnessFilterAdjuster != null) {
                        mSharpnessFilterAdjuster.adjust(PrevSharpness);
                    }

                    MainGPUImageView.requestRender();
                    break;

                case "Hue":
                    PrevHue = progress;
                    txtProgressValue.setText("" + PrevHue);

                    if (mHueFilterAdjuster != null) {
                        mHueFilterAdjuster.adjust(PrevHue);
                    }
                    MainGPUImageView.requestRender();
                    break;

                case "Sepia":
                    PrevSepia = progress;
                    txtProgressValue.setText("" + PrevSepia);

                    if (mSepiaFilterAdjuster != null) {
                        mSepiaFilterAdjuster.adjust(PrevSepia);
                    }
                    MainGPUImageView.requestRender();
                    break;

                case "Monochrome":
                    PrevMonochrome = progress;
                    txtProgressValue.setText("" + PrevMonochrome);

                    if (mMonochromeFilterAdjuster != null) {
                        mMonochromeFilterAdjuster.adjust(PrevMonochrome);
                    }
                    MainGPUImageView.requestRender();
                    break;

                case "WhiteBalance":
                    PrevWhiteBalance = progress;
                    txtProgressValue.setText("" + PrevWhiteBalance);

                    if (mWhiteBalanceFilterAdjuster != null) {
                        mWhiteBalanceFilterAdjuster.adjust(PrevWhiteBalance);
                    }
                    MainGPUImageView.requestRender();
                    break;

                case "ColorBalance":
                    PrevColorBalance = progress;
                    txtProgressValue.setText("" + PrevColorBalance);

                    if (mColorBalanceFilterAdjuster != null) {
                        mColorBalanceFilterAdjuster.adjust(PrevColorBalance);
                    }
                    MainGPUImageView.requestRender();
                    break;
                case "Level":
                    PrevLevels = progress;
                    txtProgressValue.setText("" + PrevLevels);

                    if (mLevelsFilterAdjuster != null) {
                        mLevelsFilterAdjuster.adjust(PrevLevels);
                    }
                    MainGPUImageView.requestRender();
                    break;

                case "Exposure":
                    PrevExposure = progress;
                    txtProgressValue.setText("" + PrevExposure);

                    if (mExposureFilterAdjuster != null) {
                        mExposureFilterAdjuster.adjust(PrevExposure);
                    }

                    MainGPUImageView.requestRender();
                    break;

                case "Opacity":
                    PrevOpacity = progress;
                    txtProgressValue.setText("" + ((PrevOpacity * 100)) / seekBar.getMax() + "%");
                    textbubble.setAlpha((float) (seekbar.getProgress()) / (float) (seekBar.getMax()));

                    break;
            }
        }
    }

    private void setFilters() {
        curveFilter = new GPUImageToneCurveFilter();
        curveFilter.setFromCurveFileInputStream(getResources().openRawResource(filters.get(0).getFilterfileRaw()));

        contrastFilter = new GPUImageContrastFilter(1.0f);
        mContrastFilterAdjuster = new AuroraDianaVonGrüning.FilterAdjuster(contrastFilter);

        brightnessFilter = new GPUImageBrightnessFilter(0f);
        mBrightnessFilterAdjuster = new AuroraDianaVonGrüning.FilterAdjuster(brightnessFilter);

        saturationFilter = new GPUImageSaturationFilter(1.0f);
        mSaturationFilterAdjuster = new AuroraDianaVonGrüning.FilterAdjuster(saturationFilter);

        PointF centerPoint = new PointF();
        centerPoint.x = 0.5f;
        centerPoint.y = 0.5f;
        vignetteFilter = new GPUImageVignetteFilter(centerPoint, new float[]{0.0f, 0.0f, 0.0f}, 0.0f, 1.0f);
        mVignetteFilterAdjuster = new AuroraDianaVonGrüning.FilterAdjuster(vignetteFilter);

        sharpnessFilter = new GPUImageSharpenFilter(0.0f);
        mSharpnessFilterAdjuster = new AuroraDianaVonGrüning.FilterAdjuster(sharpnessFilter);

        hueFilter = new GPUImageHueFilter(0.0f);
        mHueFilterAdjuster = new AuroraDianaVonGrüning.FilterAdjuster(hueFilter);


        whiteBalanceFilter = new GPUImageWhiteBalanceFilter(5000.0f, 0.0f);
        mWhiteBalanceFilterAdjuster = new AuroraDianaVonGrüning.FilterAdjuster(whiteBalanceFilter);

        colorBalanceFilter = new GPUImageColorBalanceFilter();
        mColorBalanceFilterAdjuster = new AuroraDianaVonGrüning.FilterAdjuster(colorBalanceFilter);

        exposureFilter = new GPUImageExposureFilter(0.0f);
        mExposureFilterAdjuster = new AuroraDianaVonGrüning.FilterAdjuster(exposureFilter);

        levelsFilter = new GPUImageLevelsFilter();
        levelsFilter.setMin(0.0f, 3.0f, 1.0f);
        mLevelsFilterAdjuster = new AuroraDianaVonGrüning.FilterAdjuster(levelsFilter);

        filterGroup = new GPUImageFilterGroup();
        filterGroup.addFilter(curveFilter);
        filterGroup.addFilter(contrastFilter);
        filterGroup.addFilter(brightnessFilter);
        filterGroup.addFilter(saturationFilter);
        filterGroup.addFilter(vignetteFilter);
        filterGroup.addFilter(sharpnessFilter);
        filterGroup.addFilter(hueFilter);

        filterGroup.addFilter(whiteBalanceFilter);
        filterGroup.addFilter(colorBalanceFilter);
        filterGroup.addFilter(exposureFilter);
        MainGPUImageView.setFilter(filterGroup);

    }

    public static void AddTextColor(int color) {
        textbubble.setTextColor(color);
    }

    @Override
    public void onClick(View v) {

        displayAds();
        AddCounter++;
        imgButtonImage.setImageResource(R.drawable.ic_true);
        switch (v.getId()) {

            case R.id.LL_Curve:

                flyOut((View) MainMenu, (View) curveList);
                if (mContrastFilterAdjuster != null) {
                    mContrastFilterAdjuster.adjust(PrevContrast);
                }

                if (mSaturationFilterAdjuster != null) {
                    mSaturationFilterAdjuster.adjust(PrevSaturation);
                }

                if (mVignetteFilterAdjuster != null) {
                    mVignetteFilterAdjuster.adjust(PrevVignette);
                }

                if (mBrightnessFilterAdjuster != null) {
                    mBrightnessFilterAdjuster.adjust(PrevBrightness);
                }
                if (mSharpnessFilterAdjuster != null) {
                    mSharpnessFilterAdjuster.adjust(PrevSharpness);
                }
                if (mHueFilterAdjuster != null) {
                    mHueFilterAdjuster.adjust(PrevHue);
                }
                if (mSepiaFilterAdjuster != null) {
                    mSepiaFilterAdjuster.adjust(PrevSepia);
                }
                if (mMonochromeFilterAdjuster != null) {
                    mMonochromeFilterAdjuster.adjust(PrevMonochrome);
                }
                if (mWhiteBalanceFilterAdjuster != null) {
                    mWhiteBalanceFilterAdjuster.adjust(PrevWhiteBalance);
                }
                if (mColorBalanceFilterAdjuster != null) {
                    mColorBalanceFilterAdjuster.adjust(PrevColorBalance);
                }
                if (mLevelsFilterAdjuster != null) {
                    mLevelsFilterAdjuster.adjust(PrevLevels);
                }
                if (mExposureFilterAdjuster != null) {
                    mExposureFilterAdjuster.adjust(PrevExposure);
                }
                MainGPUImageView.requestRender();

                Counter = 1;
                break;

            case R.id.LL_Brightness:

                flyOut((View) LL_MainMenu, (View) fragment_Blur);
                flag = "Brightness";

                seekbar.setProgress(PrevBrightness);
                seekbar.setMax(100);
                txtProgressValue.setText("" + PrevBrightness);

                Counter = 2;
                break;
            case R.id.LL_Contrast:

                flyOut((View) LL_MainMenu, (View) fragment_Blur);
                flag = "Contrast";

                seekbar.setProgress(PrevContrast);
                seekbar.setMax(100);
                txtProgressValue.setText("" + PrevContrast);

                Counter = 2;
                break;

            case R.id.LL_Saturation:

                flyOut((View) LL_MainMenu, (View) fragment_Blur);
                flag = "Saturation";

                seekbar.setProgress(PrevSaturation);
                seekbar.setMax(100);
                txtProgressValue.setText("" + PrevSaturation);

                Counter = 2;
                break;

            case R.id.LL_hue:
                flag = "Hue";
                flyOut((View) LL_MainMenu, (View) fragment_Blur);

                seekbar.setProgress(PrevHue);
                seekbar.setMax(100);
                txtProgressValue.setText("" + PrevHue);

                Counter = 2;
                break;

            case R.id.LL_Sepia:

                break;

            case R.id.LL_Monochrome:

                break;

            case R.id.LL_WhiteBalance:
                flag = "WhiteBalance";
                flyOut((View) LL_MainMenu, (View) fragment_Blur);

                seekbar.setProgress(PrevWhiteBalance);
                seekbar.setMax(100);
                txtProgressValue.setText("" + PrevWhiteBalance);

                Counter = 2;
                break;

            case R.id.LL_ColorBalance:
                flag = "ColorBalance";
                flyOut((View) LL_MainMenu, (View) fragment_Blur);

                seekbar.setProgress(PrevColorBalance);
                seekbar.setMax(100);
                txtProgressValue.setText("" + PrevColorBalance);

                Counter = 2;
                break;

            case R.id.LL_Level:
                flag = "Level";
                flyOut((View) LL_MainMenu, (View) fragment_Blur);

                seekbar.setProgress(PrevLevels);
                seekbar.setMax(100);
                txtProgressValue.setText("" + PrevLevels);

                Counter = 2;
                break;
            case R.id.LL_Exposure:
                flag = "Exposure";
                flyOut((View) LL_MainMenu, (View) fragment_Blur);

                seekbar.setProgress(PrevExposure);
                seekbar.setMax(100);
                txtProgressValue.setText("" + PrevExposure);

                Counter = 2;
                break;

            case R.id.LL_Vignette:
                flag = "Vignette";
                flyOut((View) LL_MainMenu, (View) fragment_Blur);

                seekbar.setProgress(PrevVignette);
                seekbar.setMax(75);
                txtProgressValue.setText("" + PrevVignette);

                Counter = 2;
                break;

            case R.id.LL_Sharpness:
                flag = "Sharpness";
                flyOut((View) LL_MainMenu, (View) fragment_Blur);

                seekbar.setProgress(PrevSharpness);
                seekbar.setMax(50);
                txtProgressValue.setText("" + PrevSharpness);

                Counter = 2;
                break;

            case R.id.LL_Text:

                flyOut((View) LL_MainMenu, (View) LL_TextMainLayout);
                Counter = 4;

                if (!textbubble.getText().toString().equals("")) {
                    imgTextClose.setVisibility(View.VISIBLE);
                }

                break;

            case R.id.LL_AddText:
                if (!textflag) {
                    FrameLayoutText.setVisibility(View.VISIBLE);
                    textbubble.setText("Hello World");
                    textflag = true;
                } else {
                    mAuroraBubbleInputDialog.setBubbleTextView(textbubble);
                    mAuroraBubbleInputDialog.show();
                }
                break;

            case R.id.LL_FontStyle:

                getFilesFromAssets("Fonts");
                flyOut((View) LL_TextMainLayout, (View) ABCFont);
                Counter = 5;
                break;

            case R.id.LL_TextColor:
                viewColorPicker();
                break;

            case R.id.LL_TextOpacity:
                flyOut((View) LL_TextMainLayout, (View) fragment_Blur);
                flag = "Opacity";
                seekbar.setProgress(PrevOpacity);
                seekbar.setMax(255);
                txtProgressValue.setText("" + ((PrevOpacity * 100)) / 255 + "%");
                Counter = 6;
                break;

            case R.id.imgTextClose:
                FrameLayoutText.setVisibility(View.GONE);
                textflag = false;

                break;

            case R.id.LL_Sticker:
                getFilesFromAssets("Sticker");
                flyOut((View) LL_MainMenu, (View) ABCsCategory);
                Counter = 7;

                break;
            case R.id.LL_ABCs:
                getFilesFromAssets("ABC");
                flyOut((View) LL_MainMenu, (View) ABCsCategory);
                Counter = 10;

                break;

            case R.id.ic_clear:

                switch (flag) {
                    case "Brightness":
                        seekbar.setProgress(50);
                        txtProgressValue.setText("50");
                        break;

                    case "Contrast":
                        seekbar.setProgress(50);
                        txtProgressValue.setText("50");
                        break;

                    case "Saturation":
                        seekbar.setProgress(50);
                        txtProgressValue.setText("50");
                        break;

                    case "Vignette":
                        seekbar.setProgress(75);
                        txtProgressValue.setText("75");
                        break;
                    case "Sharpness":
                        seekbar.setProgress(50);
                        txtProgressValue.setText("50");
                        break;
                    case "Hue":
                        seekbar.setProgress(100);
                        txtProgressValue.setText("100");
                        break;
                    case "Sepia":
                        seekbar.setProgress(0);
                        txtProgressValue.setText("0%");
                        break;
                    case "Monochrome":
                        seekbar.setProgress(0);
                        txtProgressValue.setText("0%");
                        break;
                    case "WhiteBalance":
                        seekbar.setProgress(100);
                        txtProgressValue.setText("100");
                        break;
                    case "ColorBalance":
                        seekbar.setProgress(0);
                        txtProgressValue.setText("0%");
                        break;
                    case "Level":
                        seekbar.setProgress(100);
                        txtProgressValue.setText("100%");
                        break;

                    case "Exposure":
                        seekbar.setProgress(50);
                        txtProgressValue.setText("50");
                        break;

                    case "Opacity":
                        seekbar.setProgress(255);
                        PrevOpacity = 255;
                        txtProgressValue.setText("" + ((PrevOpacity * 100)) / 255 + "%");
                        break;

                }

                break;

            case R.id.imgButtonImage:
                imgButtonImage.setImageResource(R.drawable.ic_next);
                if (Counter != 0) {
                    if (Counter == 1) {
                        flyOut((View) curveList, (View) MainMenu);
                        Counter = 0;
                    }
                    if (Counter == 2) {
                        flyOut((View) fragment_Blur, (View) LL_MainMenu);
                        Counter = 0;
                    }
                    if (Counter == 4) {
                        flyOut((View) LL_TextMainLayout, (View) LL_MainMenu);
                        Counter = 0;
                    }
                    if (Counter == 5) {
                        imgButtonImage.setImageResource(R.drawable.ic_true);
                        flyOut((View) ABCFont, (View) LL_TextMainLayout);
                        Counter = 4;
                    }
                    if (Counter == 6) {
                        imgButtonImage.setImageResource(R.drawable.ic_true);
                        flyOut((View) fragment_Blur, (View) LL_TextMainLayout);
                        Counter = 4;
                    }
                    if (Counter == 7) {
                        flyOut((View) ABCsCategory, (View) LL_MainMenu);
                        MainContainer.setVisibility(View.VISIBLE);
                        Counter = 0;
                    }

                    if (Counter == 10) {
                        flyOut((View) ABCsCategory, (View) LL_MainMenu);
                        Counter = 0;
                    }
                } else {
                    new SaveImage().execute();

                }
                break;

            case R.id.imgReset:

                ((AuroraTempoll) getActivity()).showResetDialog(getActivity(), new AuroraLocalBaseActivity.OnResetListner() {
                    @Override
                    public void onReset() {
                        MainGPUImageView.setImage(AuroraPatrickCox.bitmap);
                        PrevCurvePosition = 0;
                        PrevBrightness = 50;
                        PrevContrast = 50;
                        PrevSaturation = 50;
                        PrevVignette = 75;
                        PrevSharpness = 50;
                        PrevSepia = 0;
                        PrevHue = 100;

                        PrevExposure = 50;
                        PrevLevels = 100;
                        PrevColorBalance = 0;
                        PrevWhiteBalance = 100;
                        PrevMonochrome = 0;
                        setFilters();
                    }
                });

                break;

        }
    }


    public static void AddSticker(Bitmap bitmap) {
        sticker_view.addSticker(bitmap);
    }

    @Override
    public void onComplete(View bubbleTextView, String str) {
        ((TextView) bubbleTextView).setText(str);
        FrameLayoutText.setVisibility(View.VISIBLE);
    }

    public static void displayAds() {
        if (AddCounter == 2) {

        }
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    GPUImageBrightnessFilter brightnessFilter;
    GPUImageContrastFilter contrastFilter;
    GPUImageVignetteFilter vignetteFilter;
    GPUImageSaturationFilter saturationFilter;
    GPUImageToneCurveFilter curveFilter;
    GPUImageSharpenFilter sharpnessFilter;

    GPUImageHueFilter hueFilter;
    GPUImageSepiaFilter sepiaFilter;
    GPUImageMonochromeFilter monochromeFilter;
    GPUImageWhiteBalanceFilter whiteBalanceFilter;
    GPUImageColorBalanceFilter colorBalanceFilter;
    GPUImageLevelsFilter levelsFilter;
    GPUImageExposureFilter exposureFilter;

    GPUImageFilterGroup filterGroup;
    private AuroraDianaVonGrüning.FilterAdjuster mContrastFilterAdjuster;
    private AuroraDianaVonGrüning.FilterAdjuster mBrightnessFilterAdjuster;
    private AuroraDianaVonGrüning.FilterAdjuster mVignetteFilterAdjuster;
    private AuroraDianaVonGrüning.FilterAdjuster mSaturationFilterAdjuster;
    private AuroraDianaVonGrüning.FilterAdjuster mSharpnessFilterAdjuster;
    private AuroraDianaVonGrüning.FilterAdjuster mHueFilterAdjuster;
    private AuroraDianaVonGrüning.FilterAdjuster mSepiaFilterAdjuster;
    private AuroraDianaVonGrüning.FilterAdjuster mMonochromeFilterAdjuster;
    private AuroraDianaVonGrüning.FilterAdjuster mWhiteBalanceFilterAdjuster;
    private AuroraDianaVonGrüning.FilterAdjuster mColorBalanceFilterAdjuster;
    private AuroraDianaVonGrüning.FilterAdjuster mExposureFilterAdjuster;
    private AuroraDianaVonGrüning.FilterAdjuster mLevelsFilterAdjuster;



    private static Context mContext;

    private static Animation animation;
    static View SecoundView = null;

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
                SecoundView = viewFlyIn;
                viewFlyIn.setVisibility(View.VISIBLE);
            }

        });
    }

    private static void flyIn(final View view) {

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

    public static void ManageBackPrace() {
        imgButtonImage.setImageResource(R.drawable.ic_next);
        if (Counter != 0) {
            if (Counter == 1) {
                flyOut((View) curveList, (View) MainMenu);
                Counter = 0;
            }
            if (Counter == 2) {
                flyOut((View) fragment_Blur, (View) LL_MainMenu);
                Counter = 0;
            }
            if (Counter == 4) {
                flyOut((View) LL_TextMainLayout, (View) LL_MainMenu);
                Counter = 0;
                imgTextClose.setVisibility(View.GONE);
            }

            if (Counter == 5) {
                imgButtonImage.setImageResource(R.drawable.ic_true);
                flyOut((View) ABCFont, (View) LL_TextMainLayout);
                Counter = 4;
            }
            if (Counter == 6) {
                imgButtonImage.setImageResource(R.drawable.ic_true);
                flyOut((View) fragment_Blur, (View) LL_TextMainLayout);
                Counter = 4;
            }
            if (Counter == 7) {
                flyOut((View) ABCsCategory, (View) LL_MainMenu);
                Counter = 0;
            }
            if (Counter == 10) {
                flyOut((View) ABCsCategory, (View) LL_MainMenu);
                Counter = 0;
            }
        } else {

            ((AuroraTempoll) mContext).showResetDialog(mContext, new AuroraLocalBaseActivity.OnResetListner() {
                @Override
                public void onReset() {
                    AuroraTempoll.activity.finish();
                    mContext.startActivity(new Intent(mContext, AuroraMantid.class));
                    AuroraTempoll.activity.overridePendingTransition(R.anim.right_in, R.anim.left_out);
                }
            });


        }
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

    public static Bitmap merge(Bitmap bitmapmain, Bitmap bitmapback) {

        Bitmap bmOverlay = Bitmap.createBitmap(bitmapback.getWidth(), bitmapback.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmOverlay);

        canvas.drawBitmap(bitmapback, new Matrix(), null);
        canvas.drawBitmap(bitmapmain, 0, 0, null);

        Log.i("TAG", "Image Created");

        return bmOverlay;

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

            ((AuroraTempoll) getActivity()).showRatingDialog(true, new AuroraLocalBaseActivity.OnRateListner() {
                @Override
                public void onReminderLater() {
                    AuroraAds.Loadd(new AuroraAds.Ad_lisoner() {
                        @Override
                            public void onSucssec(InterstitialAd mInterstitialAd) {
                            Intent intent = new Intent(getActivity(), AuroraPrexissesta.class);
                            intent.putExtra("FinalURI", FinalURI);
                            startActivity(intent);
                            AuroraTempoll.activity.finish();
                        }

                        @Override
                        public void onun() {
                            Intent intent = new Intent(getActivity(), AuroraPrexissesta.class);
                            intent.putExtra("FinalURI", FinalURI);
                            startActivity(intent);
                            AuroraTempoll.activity.finish();
                        }
                    });
                }
            });

        }
    }

    public void mergeAndSave() {

        imgTextClose.setVisibility(View.GONE);

        Bitmap bmOverlay = Bitmap.createBitmap(drawing_view_container.getWidth(), drawing_view_container.getHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bmOverlay);
        canvas.drawBitmap(drawing_view_container.getDrawingCache(), 0, 0, null);
        try {
            Bitmap gpubitmap = MainGPUImageView.capture();

            bmOverlay = merge(bmOverlay, gpubitmap);

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

        dia = new ProgressDialog(getActivity());
        dia.setMessage("Loading ...");
        dia.setIndeterminate(false);
        dia.setCancelable(false);
        dia.setCanceledOnTouchOutside(false);
        dia.show();
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
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return "";
    }

}
