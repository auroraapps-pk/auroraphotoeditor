package com.auroraapps.auroraphotoeditor.AuroraDirkBikkembergs.AuroraRafSimons;


import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.auroraapps.auroraphotoeditor.AuroraClaudiaChanShaw.AuroraLindaBritten;
import com.auroraapps.auroraphotoeditor.AuroraDovCharney.AuroraDeanandDanCaten;
import com.auroraapps.auroraphotoeditor.AuroraDovCharney.AuroraPatrickCox;
import com.auroraapps.auroraphotoeditor.AuroraKayCohen.AuroraChristopherChronis;
import com.auroraapps.auroraphotoeditor.AuroraMaskableFrameLayout.AuroraMaskableFrameLayout;
import com.auroraapps.auroraphotoeditor.R;
import com.auroraapps.auroraphotoeditor.AuroramultiTouchLib.AuroraMultiTouchListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import jp.co.cyberagent.android.gpuimage.GPUImageAddBlendFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageColorBlendFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageColorBurnBlendFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageColorDodgeBlendFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageColorInvertFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageDarkenBlendFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageDifferenceBlendFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageDissolveBlendFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageDivideBlendFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageExclusionBlendFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageFilterGroup;
import jp.co.cyberagent.android.gpuimage.GPUImageGaussianBlurFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageHardLightBlendFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageHueBlendFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageLightenBlendFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageLinearBurnBlendFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageLuminosityBlendFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageMultiplyBlendFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageOverlayBlendFilter;
import jp.co.cyberagent.android.gpuimage.GPUImagePixelationFilter;
import jp.co.cyberagent.android.gpuimage.GPUImagePosterizeFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageSaturationBlendFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageSaturationFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageScreenBlendFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageSepiaFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageSketchFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageSoftLightBlendFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageSubtractBlendFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageView;

import static com.auroraapps.auroraphotoeditor.R.id.imgCroppdImage;


public class AuroraOlivierTheyskens extends Fragment implements View.OnClickListener {


    public AuroraOlivierTheyskens() {
        // Required empty public constructor
    }

    private static ImageView imgBackMain, imgBgMain;
    //private static MaskableFrameLayout imgMaskableFrameLayout;
    private static RelativeLayout FL_ImageFinal, RL_EditView, RL_GPU;
    private static GPUImageView GPUImageViewCroppedImage;

    public static AuroraDeanandDanCaten appPrefs;
    static String PipName;

//    Button menu Controls

    LinearLayout toolbar_area;

    static int DisplayWidth, DisplayHeight;
    private static Activity mContext;
    public static RelativeLayout RL_MagicEffect;

    //    Header Controls
    ImageView imgReset, imgButtonImage;

    LinearLayoutManager gridLayoutManager;
    RecyclerView recycler_view;
    AuroraLindaBritten databaseAdapter;

    ImageView imgEdit;
    static ImageView imgMain;
    static ImageView imgframe;

    JSONObject jsonObject;

    public static ArrayList<AuroraChristopherChronis> arrayList;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.imgButtonImage:
                new SaveImage().execute();
                break;

            case R.id.imgReset:

                if (Counter == 0) {
                    Counter = 1;

                    RL_EditView.setVisibility(View.VISIBLE);
                    RL_MagicEffect.setVisibility(View.GONE);
                    imgBgMain.setVisibility(View.GONE);
                    imgBackMain.setVisibility(View.GONE);
                    imgMain.setVisibility(View.VISIBLE);
                    imgframe.setVisibility(View.VISIBLE);
                    imgButtonImage.setVisibility(View.GONE);
                    imgReset.setImageResource(R.drawable.ic_true);
                    imgMain.setLayoutParams(new RelativeLayout.LayoutParams(DisplayWidth, DisplayWidth));
                    imgMain.setOnTouchListener(new AuroraMultiTouchListener());
                    imgMain.setImageBitmap(AuroraPatrickCox.Orizanal);
                } else {
                    Counter = 0;
                    imgframe.setVisibility(View.GONE);
                    RL_EditView.setDrawingCacheEnabled(true);
                    RL_EditView.buildDrawingCache();
                    AuroraPatrickCox.bitmap = Bitmap.createBitmap(RL_EditView.getDrawingCache());
                    RL_MagicEffect.setVisibility(View.VISIBLE);
                    imgBgMain.setVisibility(View.VISIBLE);
                    imgBackMain.setVisibility(View.VISIBLE);
                    imgMain.setVisibility(View.GONE);
                    imgEdit.setImageResource(R.drawable.ic_edit);
                    imgMain.setOnTouchListener(null);
                    RL_EditView.destroyDrawingCache();
                    //showProgress();
                    try {
                        RL_MagicEffect.removeAllViews();
                    } catch (Exception e) {
                    }
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {

                                //startDecodingLatestCurveData();
                                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                AuroraOlivierTheyskens mainFragment = new AuroraOlivierTheyskens();
                                fragmentManager.beginTransaction().replace(R.id.Container, mainFragment).commit();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }, 200);
                }

                break;

        }
    }

    public static void doMasking(String PipId) throws IOException {

        try {
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    try {
                        imgBackMain.setImageBitmap(AuroraPatrickCox.bitmap);

                        new finalGPUAssignAsyncTask().execute();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, 1000);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startDecodingLatestCurveData() {
        try {
            if (arrayList.size() > 0)
                arrayList.clear();
            JSONObject responce = new JSONObject(loadJSONFromAsset());
            JSONArray jsonArray = responce.getJSONArray("Masks");
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);
                AuroraChristopherChronis Object = new AuroraChristopherChronis();

                try {
                    Object.setBlurStatus(jsonObject.getJSONObject("Blur").getString("Status"));
                    Object.setBlurValue(jsonObject.getJSONObject("Blur").getString("Value"));
                } catch (Exception e) {
                }

                try {
                    Object.setPixelationStatus(jsonObject.getJSONObject("Pixelation").getString("Status"));
                    Object.setPixelationValue(jsonObject.getJSONObject("Pixelation").getString("Value"));
                } catch (Exception e) {
                }

                try {
                    Object.setSepiaStatus(jsonObject.getJSONObject("Sepia").getString("Status"));
                } catch (Exception e) {
                }


                try {
                    Object.setGrayscaleStatus(jsonObject.getJSONObject("Grayscale").getString("Status"));
                } catch (Exception e) {
                }

                try {
                    Object.setInvertStatus(jsonObject.getJSONObject("InvertColor").getString("Status"));
                } catch (Exception e) {
                }

                try {
                    Object.setMonochromeStatus(jsonObject.getJSONObject("Monochrome").getString("Status"));
                } catch (Exception e) {
                }

                try {
                    Object.setSketchStatus(jsonObject.getJSONObject("Sketch").getString("Status"));
                } catch (Exception e) {
                }

                try {
                    Object.setMaskName(jsonObject.getString("MaskName"));
                } catch (Exception e) {
                }

                try {
                    Object.setPosterizeStatus(jsonObject.getJSONObject("Posterize").getString("Status"));
                    Object.setPosterizeValue(jsonObject.getJSONObject("Posterize").getString("Value"));
                } catch (Exception e) {
                }

                try {
                    Object.setCurveStatus(jsonObject.getJSONObject("Curve").getString("Status"));
                    Object.setCurveDefaultFile(jsonObject.getJSONObject("Curve").getString("DefaultFile"));
                } catch (Exception e) {
                }

                try {
                    Object.setBlendDifferenceStatus(jsonObject.getJSONObject("BlendDifference").getString("Status"));
                    Object.setBlendDifferenceOpacity(jsonObject.getJSONObject("BlendDifference").getString("Opacity"));
                    Object.setBlendDifferenceDefaultFile(jsonObject.getJSONObject("BlendDifference").getString("DefaultFile"));
                } catch (Exception e) {
                }


                try {
                    Object.setBlendColorBurnStatus(jsonObject.getJSONObject("BlendColorBurn").getString("Status"));
                    Object.setBlendColorBurnOpacity(jsonObject.getJSONObject("BlendColorBurn").getString("Opacity"));
                    Object.setBlendColorBurnDefaultFile(jsonObject.getJSONObject("BlendColorBurn").getString("DefaultFile"));
                } catch (Exception e) {
                }

                try {
                    Object.setBlendDodgeStatus(jsonObject.getJSONObject("BlendDodge").getString("Status"));
                    Object.setBlendDodgeOpacity(jsonObject.getJSONObject("BlendDodge").getString("Opacity"));
                    Object.setBlendDodgeDefaultFile(jsonObject.getJSONObject("BlendDodge").getString("DefaultFile"));
                } catch (Exception e) {
                }


                try {
                    Object.setBlendDarkenStatus(jsonObject.getJSONObject("BlendDarken").getString("Status"));
                    Object.setBlendDarkenOpacity(jsonObject.getJSONObject("BlendDarken").getString("Opacity"));
                    Object.setBlendDarkenDefaultFile(jsonObject.getJSONObject("BlendDarken").getString("DefaultFile"));
                } catch (Exception e) {
                }


                try {
                    Object.setBlendDisolveStatus(jsonObject.getJSONObject("BlendDisolve").getString("Status"));
                    Object.setBlendDisolveOpacity(jsonObject.getJSONObject("BlendDisolve").getString("Opacity"));
                    Object.setBlendDisolveDefaultFile(jsonObject.getJSONObject("BlendDisolve").getString("DefaultFile"));
                } catch (Exception e) {
                }


                try {
                    Object.setBlendExclusionStatus(jsonObject.getJSONObject("BlendExclusion").getString("Status"));
                    Object.setBlendExclusionOpacity(jsonObject.getJSONObject("BlendExclusion").getString("Opacity"));
                    Object.setBlendExclusionDefaultFile(jsonObject.getJSONObject("BlendExclusion").getString("DefaultFile"));
                } catch (Exception e) {
                }

                try {
                    Object.setBlendHeardLightStatus(jsonObject.getJSONObject("BlendHardLight").getString("Status"));
                    Object.setBlendHeardLightOpacity(jsonObject.getJSONObject("BlendHardLight").getString("Opacity"));
                    Object.setBlendHeardLightDefaultFile(jsonObject.getJSONObject("BlendHardLight").getString("DefaultFile"));
                } catch (Exception e) {
                }


                try {
                    Object.setBlendLightenStatus(jsonObject.getJSONObject("BlendLighten").getString("Status"));
                    Object.setBlendLightenOpacity(jsonObject.getJSONObject("BlendLighten").getString("Opacity"));
                    Object.setBlendLightenDefaultFile(jsonObject.getJSONObject("BlendLighten").getString("DefaultFile"));
                } catch (Exception e) {
                }


                try {
                    Object.setBlendAddStatus(jsonObject.getJSONObject("BlendAdd").getString("Status"));
                    Object.setBlendAddOpacity(jsonObject.getJSONObject("BlendAdd").getString("Opacity"));
                    Object.setBlendAddDefaultFile(jsonObject.getJSONObject("BlendAdd").getString("DefaultFile"));
                } catch (Exception e) {
                }


                try {
                    Object.setBlendDivideStatus(jsonObject.getJSONObject("BlendDivide").getString("Status"));
                    Object.setBlendDivideOpacity(jsonObject.getJSONObject("BlendDivide").getString("Opacity"));
                    Object.setBlendDivideDefaultFile(jsonObject.getJSONObject("BlendDivide").getString("DefaultFile"));
                } catch (Exception e) {
                }


                try {
                    Object.setBlendMultiplyStatus(jsonObject.getJSONObject("BlendMultiply").getString("Status"));
                    Object.setBlendMultiplyOpacity(jsonObject.getJSONObject("BlendMultiply").getString("Opacity"));
                    Object.setBlendMultiplyDefaultFile(jsonObject.getJSONObject("BlendMultiply").getString("DefaultFile"));
                } catch (Exception e) {
                }

                try {
                    Object.setBlendOverlayStatus(jsonObject.getJSONObject("BlendOverlay").getString("Status"));
                    Object.setBlendOverlayOpacity(jsonObject.getJSONObject("BlendOverlay").getString("Opacity"));
                    Object.setBlendOverlayDefaultFile(jsonObject.getJSONObject("BlendOverlay").getString("DefaultFile"));
                } catch (Exception e) {
                }


                try {
                    Object.setBlendScreenStatus(jsonObject.getJSONObject("BlendScreen").getString("Status"));
                    Object.setBlendScreenOpacity(jsonObject.getJSONObject("BlendScreen").getString("Opacity"));
                    Object.setBlendScreenDefaultFile(jsonObject.getJSONObject("BlendScreen").getString("DefaultFile"));
                } catch (Exception e) {
                }


                try {
                    Object.setBlendAlphaStatus(jsonObject.getJSONObject("BlendAlpha").getString("Status"));
                    Object.setBlendAlphaOpacity(jsonObject.getJSONObject("BlendAlpha").getString("Opacity"));
                    Object.setBlendAlphaDefaultFile(jsonObject.getJSONObject("BlendAlpha").getString("DefaultFile"));
                } catch (Exception e) {
                }

                try {
                    Object.setBlendColorStatus(jsonObject.getJSONObject("BlendColor").getString("Status"));
                    Object.setBlendColorOpacity(jsonObject.getJSONObject("BlendColor").getString("Opacity"));
                    Object.setBlendColorDefaultFile(jsonObject.getJSONObject("BlendColor").getString("DefaultFile"));
                } catch (Exception e) {
                }

                try {
                    Object.setBlendHueStatus(jsonObject.getJSONObject("BlendHue").getString("Status"));
                    Object.setBlendHueOpacity(jsonObject.getJSONObject("BlendHue").getString("Opacity"));
                    Object.setBlendHueDefaultFile(jsonObject.getJSONObject("BlendHue").getString("DefaultFile"));
                } catch (Exception e) {
                }


                try {
                    Object.setBlendSaturationStatus(jsonObject.getJSONObject("BlendSaturation").getString("Status"));
                    Object.setBlendSaturationOpacity(jsonObject.getJSONObject("BlendSaturation").getString("Opacity"));
                    Object.setBlendSaturationDefaultFile(jsonObject.getJSONObject("BlendSaturation").getString("DefaultFile"));
                } catch (Exception e) {
                }

                try {
                    Object.setBlendLuminosityStatus(jsonObject.getJSONObject("BlendLuminosity").getString("Status"));
                    Object.setBlendLuminosityOpacity(jsonObject.getJSONObject("BlendLuminosity").getString("Opacity"));
                    Object.setBlendLuminosityDefaultFile(jsonObject.getJSONObject("BlendLuminosity").getString("DefaultFile"));
                } catch (Exception e) {
                }


                try {
                    Object.setBlendLinearBurnStatus(jsonObject.getJSONObject("BlendLinearBurn").getString("Status"));
                    Object.setBlendLinearBurnOpacity(jsonObject.getJSONObject("BlendLinearBurn").getString("Opacity"));
                    Object.setBlendLinearBurnDefaultFile(jsonObject.getJSONObject("BlendLinearBurn").getString("DefaultFile"));
                } catch (Exception e) {
                }

                try {
                    Object.setBlendSoftLightStatus(jsonObject.getJSONObject("BlendSoftLight").getString("Status"));
                    Object.setBlendSoftLightOpacity(jsonObject.getJSONObject("BlendSoftLight").getString("Opacity"));
                    Object.setBlendSoftLightDefaultFile(jsonObject.getJSONObject("BlendSoftLight").getString("DefaultFile"));
                } catch (Exception e) {
                }

                try {
                    Object.setBlendSubtractStatus(jsonObject.getJSONObject("BlendSubtract").getString("Status"));
                    Object.setBlendSubtractOpacity(jsonObject.getJSONObject("BlendSubtract").getString("Opacity"));
                    Object.setBlendSubtractDefaultFile(jsonObject.getJSONObject("BlendSubtract").getString("DefaultFile"));
                } catch (Exception e) {
                }

                arrayList.add(Object);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            doMasking(appPrefs.getPipName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static GPUImageFilterGroup filterGroup;

    public static GPUImageGaussianBlurFilter blurFilter;
    public static GPUImagePixelationFilter pixelationFilter;
    public static GPUImageSepiaFilter sepiaFilter;
    public static GPUImageSketchFilter sketchFilter;
    public static GPUImagePosterizeFilter posterizeFilter;

    public static String readFileAsBase64String(String path) {
        String json = null;
        InputStream is = null;
        try {
            is = mContext.getAssets().open(path);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //kkk
        String co = appPrefs.getBIT128().toString().trim();//.substring(1, appPrefs.getText().toString().trim().length());
        //String sco = co.substring(0,co.length())+"";

        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append(co + "\r\n");
        return json.replaceAll(co.toString(), "");
    }

    int Counter = 0;

    public String loadJSONFromAsset() {
        String json = null;
        try {

            String PipName = appPrefs.getPipName().trim();
            InputStream is = getActivity().getAssets().open(PipName + "/def.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }

    public static class finalGPUAssignAsyncTask extends AsyncTask<Void, Void, Void> {
        AuroraChristopherChronis currentBean;

        public finalGPUAssignAsyncTask() {

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }


        @Override
        protected Void doInBackground(Void... params) {


            mContext.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Bitmap croppedFilteredBitmap = null;

                    GPUImageViewCroppedImage.setImage(Bitmap.createBitmap(AuroraPatrickCox.bitmap));
                    filterGroup = new GPUImageFilterGroup();
                    //MainGPUImageView.setImage(CommonUtilities.bitmap);
                    currentBean = arrayList.get(counter);
                    boolean flag = false;
                    try {

                        //Blur
                        try {
                            if (currentBean.getBlurStatus().equals("ON")) {
                                flag = true;
                                int value = Integer.parseInt(currentBean.getBlurValue());
                                float v = (float) value;
                                blurFilter = new GPUImageGaussianBlurFilter(v);
                                filterGroup.addFilter(blurFilter);
                            }
                        } catch (Exception e) {
                        }


                        //GrayScale
                        try {
                            if (currentBean.getGrayscaleStatus().equals("ON")) {
                                flag = true;
                                GPUImageSaturationFilter grayscaleFilter = new GPUImageSaturationFilter(0.0f);
                                filterGroup.addFilter(grayscaleFilter);
                            }
                        } catch (Exception e) {
                        }

                        //Blend Screen
                        try {
                            if (currentBean.getBlendScreenStatus().equals("ON")) {
                                flag = true;

                                BitmapFactory.Options options = new BitmapFactory.Options();
                                options.inPreferredConfig = Bitmap.Config.ARGB_8888;

                                String maskString = readFileAsBase64String(PipName + "/" + currentBean.getBlendScreenDefaultFile() + ".txt");
                                byte[] bytes = Base64.decode(maskString, Base64.DEFAULT);
                                Bitmap b = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);

                                b = Bitmap.createScaledBitmap(b, DisplayWidth, DisplayHeight, false);

                                GPUImageScreenBlendFilter overScreenOverlayFilter = new GPUImageScreenBlendFilter();
                                Bitmap bmp = adjustOpacity(b, Integer.parseInt(currentBean.getBlendScreenOpacity()));
                                overScreenOverlayFilter.setBitmap(bmp);
                                //overScreenOverlayFilter.setBitmap(b);
                                filterGroup.addFilter(overScreenOverlayFilter);

                            }
                        } catch (Exception e) {
                        }

                        //Pixalation
                        try {
                            if (currentBean.getPixelationStatus().equals("ON")) {
                                flag = true;
                                pixelationFilter = new GPUImagePixelationFilter();
                                pixelationFilter.setPixel(Float.parseFloat(currentBean.getPixelationValue()));
                                filterGroup.addFilter(pixelationFilter);
                            }

                        } catch (Exception e) {
                        }

                        //Sepia
                        try {
                            if (currentBean.getSepiaStatus().equals("ON")) {
                                flag = true;
                                sepiaFilter = new GPUImageSepiaFilter(1.0f);
                                filterGroup.addFilter(sepiaFilter);
                            }

                        } catch (Exception e) {
                        }


                        try {
                            if (currentBean.getSketchStatus().equals("ON")) {
                                flag = true;
                                sketchFilter = new GPUImageSketchFilter();
                                filterGroup.addFilter(sketchFilter);
                            }
                        } catch (Exception e) {
                        }

                        try {
                            if (currentBean.getPosterizeStatus().equals("ON")) {
                                flag = true;
                                posterizeFilter = new GPUImagePosterizeFilter(Integer.parseInt(currentBean.getPosterizeValue()));
                                filterGroup.addFilter(posterizeFilter);
                            }
                        } catch (Exception e) {
                        }


                        //Blend Difference
                        try {
                            if (currentBean.getBlendDifferenceStatus().equals("ON")) {
                                flag = true;
                                BitmapFactory.Options options = new BitmapFactory.Options();
                                options.inPreferredConfig = Bitmap.Config.ARGB_8888;

                                String maskString = readFileAsBase64String(PipName + "/" + currentBean.getBlendDifferenceDefaultFile() + ".txt");
                                byte[] bytes = Base64.decode(maskString, Base64.DEFAULT);
                                Bitmap b = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);

                                b = Bitmap.createScaledBitmap(b, DisplayWidth, DisplayHeight, false);

                                GPUImageDifferenceBlendFilter overScreenOverlayFilter = new GPUImageDifferenceBlendFilter();
                                Bitmap bmp = adjustOpacity(b, Integer.parseInt(currentBean.getBlendDifferenceOpacity()));
                                overScreenOverlayFilter.setBitmap(bmp);
                                //overScreenOverlayFilter.setBitmap(b);
                                filterGroup.addFilter(overScreenOverlayFilter);

                            }
                        } catch (Exception e) {
                        }

                        //Blend ColorBurn
                        try {
                            if (currentBean.getBlendColorBurnStatus().equals("ON")) {
                                flag = true;
                                BitmapFactory.Options options = new BitmapFactory.Options();
                                options.inPreferredConfig = Bitmap.Config.ARGB_8888;

                                String maskString = readFileAsBase64String(PipName + "/" + currentBean.getBlendColorBurnDefaultFile() + ".txt");
                                byte[] bytes = Base64.decode(maskString, Base64.DEFAULT);
                                Bitmap b = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);

                                b = Bitmap.createScaledBitmap(b, DisplayWidth, DisplayHeight, false);

                                GPUImageColorBurnBlendFilter overScreenOverlayFilter = new GPUImageColorBurnBlendFilter();
                                Bitmap bmp = adjustOpacity(b, Integer.parseInt(currentBean.getBlendColorBurnOpacity()));
                                overScreenOverlayFilter.setBitmap(bmp);
                                //overScreenOverlayFilter.setBitmap(b);
                                filterGroup.addFilter(overScreenOverlayFilter);

                            }
                        } catch (Exception e) {
                        }


                        //Blend Dodge
                        try {
                            if (currentBean.getBlendDodgeStatus().equals("ON")) {
                                flag = true;
                                BitmapFactory.Options options = new BitmapFactory.Options();
                                options.inPreferredConfig = Bitmap.Config.ARGB_8888;

                                String maskString = readFileAsBase64String(PipName + "/" + currentBean.getBlendDodgeDefaultFile() + ".txt");
                                byte[] bytes = Base64.decode(maskString, Base64.DEFAULT);
                                Bitmap b = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);

                                b = Bitmap.createScaledBitmap(b, DisplayWidth, DisplayHeight, false);

                                GPUImageColorDodgeBlendFilter overScreenOverlayFilter = new GPUImageColorDodgeBlendFilter();
                                Bitmap bmp = adjustOpacity(b, Integer.parseInt(currentBean.getBlendDodgeOpacity()));
                                overScreenOverlayFilter.setBitmap(bmp);
                                //overScreenOverlayFilter.setBitmap(b);
                                filterGroup.addFilter(overScreenOverlayFilter);

                            }
                        } catch (Exception e) {
                        }


                        //Blend Darken
                        try {
                            if (currentBean.getBlendDarkenStatus().equals("ON")) {
                                flag = true;
                                BitmapFactory.Options options = new BitmapFactory.Options();
                                options.inPreferredConfig = Bitmap.Config.ARGB_8888;

                                String maskString = readFileAsBase64String(PipName + "/" + currentBean.getBlendDarkenDefaultFile() + ".txt");
                                byte[] bytes = Base64.decode(maskString, Base64.DEFAULT);
                                Bitmap b = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);

                                b = Bitmap.createScaledBitmap(b, DisplayWidth, DisplayHeight, false);

                                GPUImageDarkenBlendFilter overScreenOverlayFilter = new GPUImageDarkenBlendFilter();
                                Bitmap bmp = adjustOpacity(b, Integer.parseInt(currentBean.getBlendDarkenOpacity()));
                                overScreenOverlayFilter.setBitmap(bmp);
                                //overScreenOverlayFilter.setBitmap(b);
                                filterGroup.addFilter(overScreenOverlayFilter);

                            }
                        } catch (Exception e) {
                        }


                        //Blend Disolve
                        try {
                            if (currentBean.getBlendDisolveStatus().equals("ON")) {
                                flag = true;
                                BitmapFactory.Options options = new BitmapFactory.Options();
                                options.inPreferredConfig = Bitmap.Config.ARGB_8888;

                                String maskString = readFileAsBase64String(PipName + "/" + currentBean.getBlendDisolveDefaultFile() + ".txt");
                                byte[] bytes = Base64.decode(maskString, Base64.DEFAULT);
                                Bitmap b = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);

                                b = Bitmap.createScaledBitmap(b, DisplayWidth, DisplayHeight, false);

                                GPUImageDissolveBlendFilter overScreenOverlayFilter = new GPUImageDissolveBlendFilter();
                                Bitmap bmp = adjustOpacity(b, Integer.parseInt(currentBean.getBlendDisolveOpacity()));
                                overScreenOverlayFilter.setBitmap(bmp);
                                //overScreenOverlayFilter.setBitmap(b);
                                filterGroup.addFilter(overScreenOverlayFilter);

                            }
                        } catch (Exception e) {
                        }


                        //Blend Exclusion
                        try {
                            if (currentBean.getBlendExclusionStatus().equals("ON")) {
                                flag = true;
                                BitmapFactory.Options options = new BitmapFactory.Options();
                                options.inPreferredConfig = Bitmap.Config.ARGB_8888;

                                String maskString = readFileAsBase64String(PipName + "/" + currentBean.getBlendExclusionDefaultFile() + ".txt");
                                byte[] bytes = Base64.decode(maskString, Base64.DEFAULT);
                                Bitmap b = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);

                                b = Bitmap.createScaledBitmap(b, DisplayWidth, DisplayHeight, false);

                                GPUImageExclusionBlendFilter overScreenOverlayFilter = new GPUImageExclusionBlendFilter();
                                Bitmap bmp = adjustOpacity(b, Integer.parseInt(currentBean.getBlendExclusionOpacity()));
                                overScreenOverlayFilter.setBitmap(bmp);
                                //overScreenOverlayFilter.setBitmap(b);
                                filterGroup.addFilter(overScreenOverlayFilter);

                            }
                        } catch (Exception e) {
                        }

                        //Blend HardLight
                        try {
                            if (currentBean.getBlendHeardLightStatus().equals("ON")) {
                                flag = true;
                                BitmapFactory.Options options = new BitmapFactory.Options();
                                options.inPreferredConfig = Bitmap.Config.ARGB_8888;

                                String maskString = readFileAsBase64String(PipName + "/" + currentBean.getBlendHeardLightDefaultFile() + ".txt");
                                byte[] bytes = Base64.decode(maskString, Base64.DEFAULT);
                                Bitmap b = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);

                                b = Bitmap.createScaledBitmap(b, DisplayWidth, DisplayHeight, false);

                                GPUImageHardLightBlendFilter overScreenOverlayFilter = new GPUImageHardLightBlendFilter();
                                Bitmap bmp = adjustOpacity(b, Integer.parseInt(currentBean.getBlendHeardLightOpacity()));
                                overScreenOverlayFilter.setBitmap(bmp);
                                //overScreenOverlayFilter.setBitmap(b);
                                filterGroup.addFilter(overScreenOverlayFilter);

                            }
                        } catch (Exception e) {
                        }


                        //Blend Lighten
                        try {
                            if (currentBean.getBlendLightenStatus().equals("ON")) {
                                flag = true;
                                BitmapFactory.Options options = new BitmapFactory.Options();
                                options.inPreferredConfig = Bitmap.Config.ARGB_8888;

                                String maskString = readFileAsBase64String(PipName + "/" + currentBean.getBlendLightenDefaultFile() + ".txt");
                                byte[] bytes = Base64.decode(maskString, Base64.DEFAULT);
                                Bitmap b = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);

                                b = Bitmap.createScaledBitmap(b, DisplayWidth, DisplayHeight, false);

                                GPUImageLightenBlendFilter overScreenOverlayFilter = new GPUImageLightenBlendFilter();
                                Bitmap bmp = adjustOpacity(b, Integer.parseInt(currentBean.getBlendLightenOpacity()));
                                overScreenOverlayFilter.setBitmap(bmp);
                                //overScreenOverlayFilter.setBitmap(b);
                                filterGroup.addFilter(overScreenOverlayFilter);

                            }
                        } catch (Exception e) {
                        }


                        //Blend Add
                        try {
                            if (currentBean.getBlendAddStatus().equals("ON")) {
                                flag = true;
                                BitmapFactory.Options options = new BitmapFactory.Options();
                                options.inPreferredConfig = Bitmap.Config.ARGB_8888;

                                String maskString = readFileAsBase64String(PipName + "/" + currentBean.getBlendAddDefaultFile() + ".txt");
                                byte[] bytes = Base64.decode(maskString, Base64.DEFAULT);
                                Bitmap b = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);

                                b = Bitmap.createScaledBitmap(b, DisplayWidth, DisplayHeight, false);

                                GPUImageAddBlendFilter overScreenOverlayFilter = new GPUImageAddBlendFilter();
                                Bitmap bmp = adjustOpacity(b, Integer.parseInt(currentBean.getBlendAddOpacity()));
                                overScreenOverlayFilter.setBitmap(bmp);
                                //overScreenOverlayFilter.setBitmap(b);
                                filterGroup.addFilter(overScreenOverlayFilter);

                            }
                        } catch (Exception e) {
                        }


                        //Blend Divide
                        try {
                            if (currentBean.getBlendDivideStatus().equals("ON")) {
                                flag = true;
                                BitmapFactory.Options options = new BitmapFactory.Options();
                                options.inPreferredConfig = Bitmap.Config.ARGB_8888;

                                String maskString = readFileAsBase64String(PipName + "/" + currentBean.getBlendDivideDefaultFile() + ".txt");
                                byte[] bytes = Base64.decode(maskString, Base64.DEFAULT);
                                Bitmap b = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);

                                b = Bitmap.createScaledBitmap(b, DisplayWidth, DisplayHeight, false);

                                GPUImageDivideBlendFilter overScreenOverlayFilter = new GPUImageDivideBlendFilter();
                                Bitmap bmp = adjustOpacity(b, Integer.parseInt(currentBean.getBlendDivideOpacity()));
                                overScreenOverlayFilter.setBitmap(bmp);
                                //overScreenOverlayFilter.setBitmap(b);
                                filterGroup.addFilter(overScreenOverlayFilter);

                            }
                        } catch (Exception e) {
                        }


                        //Blend Multiply
                        try {
                            if (currentBean.getBlendMultiplyStatus().equals("ON")) {
                                flag = true;
                                BitmapFactory.Options options = new BitmapFactory.Options();
                                options.inPreferredConfig = Bitmap.Config.ARGB_8888;

                                String maskString = readFileAsBase64String(PipName + "/" + currentBean.getBlendMultiplyDefaultFile() + ".txt");
                                byte[] bytes = Base64.decode(maskString, Base64.DEFAULT);
                                Bitmap b = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);

                                b = Bitmap.createScaledBitmap(b, DisplayWidth, DisplayHeight, false);

                                GPUImageMultiplyBlendFilter overScreenOverlayFilter = new GPUImageMultiplyBlendFilter();
                                Bitmap bmp = adjustOpacity(b, Integer.parseInt(currentBean.getBlendMultiplyOpacity()));
                                overScreenOverlayFilter.setBitmap(bmp);
                                //overScreenOverlayFilter.setBitmap(b);
                                filterGroup.addFilter(overScreenOverlayFilter);

                            }
                        } catch (Exception e) {
                        }

                        //Blend Overlay
                        try {
                            if (currentBean.getBlendOverlayStatus().equals("ON")) {
                                flag = true;
                                BitmapFactory.Options options = new BitmapFactory.Options();
                                options.inPreferredConfig = Bitmap.Config.ARGB_8888;

                                String maskString = readFileAsBase64String(PipName + "/" + currentBean.getBlendOverlayDefaultFile() + ".txt");
                                byte[] bytes = Base64.decode(maskString, Base64.DEFAULT);
                                Bitmap b = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);

                                b = Bitmap.createScaledBitmap(b, DisplayWidth, DisplayHeight, false);

                                GPUImageOverlayBlendFilter overScreenOverlayFilter = new GPUImageOverlayBlendFilter();
                                Bitmap bmp = adjustOpacity(b, Integer.parseInt(currentBean.getBlendOverlayOpacity()));
                                overScreenOverlayFilter.setBitmap(bmp);
                                //overScreenOverlayFilter.setBitmap(b);
                                filterGroup.addFilter(overScreenOverlayFilter);

                            }
                        } catch (Exception e) {
                        }


                        //Blend Color
                        try {
                            if (currentBean.getBlendColorStatus().equals("ON")) {
                                flag = true;
                                BitmapFactory.Options options = new BitmapFactory.Options();
                                options.inPreferredConfig = Bitmap.Config.ARGB_8888;

                                String maskString = readFileAsBase64String(PipName + "/" + currentBean.getBlendColorDefaultFile() + ".txt");
                                byte[] bytes = Base64.decode(maskString, Base64.DEFAULT);
                                Bitmap b = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);

                                b = Bitmap.createScaledBitmap(b, DisplayWidth, DisplayHeight, false);

                                GPUImageColorBlendFilter overScreenOverlayFilter = new GPUImageColorBlendFilter();
                                Bitmap bmp = adjustOpacity(b, Integer.parseInt(currentBean.getBlendColorOpacity()));
                                overScreenOverlayFilter.setBitmap(bmp);
                                //overScreenOverlayFilter.setBitmap(b);
                                filterGroup.addFilter(overScreenOverlayFilter);

                            }
                        } catch (Exception e) {
                        }

                        //HUE
                        try {
                            if (currentBean.getBlendHueStatus().equals("ON")) {
                                flag = true;
                                BitmapFactory.Options options = new BitmapFactory.Options();
                                options.inPreferredConfig = Bitmap.Config.ARGB_8888;

                                String maskString = readFileAsBase64String(PipName + "/" + currentBean.getBlendHueDefaultFile() + ".txt");
                                byte[] bytes = Base64.decode(maskString, Base64.DEFAULT);
                                Bitmap b = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);

                                b = Bitmap.createScaledBitmap(b, DisplayWidth, DisplayHeight, false);

                                GPUImageHueBlendFilter overScreenOverlayFilter = new GPUImageHueBlendFilter();
                                Bitmap bmp = adjustOpacity(b, Integer.parseInt(currentBean.getBlendHueOpacity()));
                                overScreenOverlayFilter.setBitmap(bmp);
                                //overScreenOverlayFilter.setBitmap(b);
                                filterGroup.addFilter(overScreenOverlayFilter);

                            }
                        } catch (Exception e) {
                        }


                        //Saturation
                        try {
                            if (currentBean.getBlendSaturationStatus().equals("ON")) {
                                flag = true;
                                BitmapFactory.Options options = new BitmapFactory.Options();
                                options.inPreferredConfig = Bitmap.Config.ARGB_8888;

                                String maskString = readFileAsBase64String(PipName + "/" + currentBean.getBlendSaturationDefaultFile() + ".txt");
                                byte[] bytes = Base64.decode(maskString, Base64.DEFAULT);
                                Bitmap b = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);

                                b = Bitmap.createScaledBitmap(b, DisplayWidth, DisplayHeight, false);

                                GPUImageSaturationBlendFilter overScreenOverlayFilter = new GPUImageSaturationBlendFilter();
                                Bitmap bmp = adjustOpacity(b, Integer.parseInt(currentBean.getBlendSaturationOpacity()));
                                overScreenOverlayFilter.setBitmap(bmp);
                                //overScreenOverlayFilter.setBitmap(b);
                                filterGroup.addFilter(overScreenOverlayFilter);

                            }
                        } catch (Exception e) {
                        }

                        //Luminosity
                        try {
                            if (currentBean.getBlendLuminosityStatus().equals("ON")) {
                                flag = true;
                                BitmapFactory.Options options = new BitmapFactory.Options();
                                options.inPreferredConfig = Bitmap.Config.ARGB_8888;

                                String maskString = readFileAsBase64String(PipName + "/" + currentBean.getBlendLuminosityDefaultFile() + ".txt");
                                byte[] bytes = Base64.decode(maskString, Base64.DEFAULT);
                                Bitmap b = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);

                                b = Bitmap.createScaledBitmap(b, DisplayWidth, DisplayHeight, false);

                                GPUImageLuminosityBlendFilter overScreenOverlayFilter = new GPUImageLuminosityBlendFilter();
                                Bitmap bmp = adjustOpacity(b, Integer.parseInt(currentBean.getBlendLuminosityOpacity()));
                                overScreenOverlayFilter.setBitmap(bmp);
                                //overScreenOverlayFilter.setBitmap(b);
                                filterGroup.addFilter(overScreenOverlayFilter);

                            }
                        } catch (Exception e) {
                        }


                        //Linear Burn
                        try {
                            if (currentBean.getBlendLinearBurnStatus().equals("ON")) {
                                flag = true;
                                BitmapFactory.Options options = new BitmapFactory.Options();
                                options.inPreferredConfig = Bitmap.Config.ARGB_8888;

                                String maskString = readFileAsBase64String(PipName + "/" + currentBean.getBlendLinearBurnDefaultFile() + ".txt");
                                byte[] bytes = Base64.decode(maskString, Base64.DEFAULT);
                                Bitmap b = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);

                                b = Bitmap.createScaledBitmap(b, DisplayWidth, DisplayHeight, false);

                                GPUImageLinearBurnBlendFilter overScreenOverlayFilter = new GPUImageLinearBurnBlendFilter();
                                Bitmap bmp = adjustOpacity(b, Integer.parseInt(currentBean.getBlendLinearBurnOpacity()));
                                overScreenOverlayFilter.setBitmap(bmp);
                                //overScreenOverlayFilter.setBitmap(b);
                                filterGroup.addFilter(overScreenOverlayFilter);

                            }
                        } catch (Exception e) {
                        }

                        //Softlight
                        try {
                            if (currentBean.getBlendSoftLightStatus().equals("ON")) {
                                flag = true;
                                BitmapFactory.Options options = new BitmapFactory.Options();
                                options.inPreferredConfig = Bitmap.Config.ARGB_8888;

                                String maskString = readFileAsBase64String(PipName + "/" + currentBean.getBlendSoftLightDefaultFile() + ".txt");
                                byte[] bytes = Base64.decode(maskString, Base64.DEFAULT);
                                Bitmap b = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);

                                b = Bitmap.createScaledBitmap(b, DisplayWidth, DisplayHeight, false);

                                GPUImageSoftLightBlendFilter overScreenOverlayFilter = new GPUImageSoftLightBlendFilter();
                                Bitmap bmp = adjustOpacity(b, Integer.parseInt(currentBean.getBlendSoftLightOpacity()));
                                overScreenOverlayFilter.setBitmap(bmp);
                                //overScreenOverlayFilter.setBitmap(b);
                                filterGroup.addFilter(overScreenOverlayFilter);

                            }
                        } catch (Exception e) {
                        }

                        //Subtract
                        try {
                            if (currentBean.getBlendSubtractStatus().equals("ON")) {
                                flag = true;
                                BitmapFactory.Options options = new BitmapFactory.Options();
                                options.inPreferredConfig = Bitmap.Config.ARGB_8888;

                                String maskString = readFileAsBase64String(PipName + "/" + currentBean.getBlendSubtractDefaultFile() + ".txt");
                                byte[] bytes = Base64.decode(maskString, Base64.DEFAULT);
                                Bitmap b = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);

                                b = Bitmap.createScaledBitmap(b, DisplayWidth, DisplayHeight, false);

                                GPUImageSubtractBlendFilter overScreenOverlayFilter = new GPUImageSubtractBlendFilter();
                                Bitmap bmp = adjustOpacity(b, Integer.parseInt(currentBean.getBlendSubtractOpacity()));
                                overScreenOverlayFilter.setBitmap(bmp);
                                //overScreenOverlayFilter.setBitmap(b);
                                filterGroup.addFilter(overScreenOverlayFilter);

                            }
                        } catch (Exception e) {
                        }


                        if (flag)
                            GPUImageViewCroppedImage.setFilter(filterGroup);
                        croppedFilteredBitmap = Bitmap.createBitmap(GPUImageViewCroppedImage.capture());

//                        Thread.sleep(2000);
                        View inflatedLayout = mContext.getLayoutInflater().inflate(R.layout.clg_layout_mask_raw, null, false);
                        ImageView img = (ImageView) inflatedLayout.findViewById(imgCroppdImage);
                        FrameLayout.LayoutParams frameparams = new FrameLayout.LayoutParams(DisplayWidth, DisplayHeight);
                        img.setLayoutParams(frameparams);
                        img.setAdjustViewBounds(true);
                        AuroraMaskableFrameLayout auroraMaskableFrameLayout = (AuroraMaskableFrameLayout) inflatedLayout.findViewById(R.id.imgMaskableFrameLayout);
                        frameparams = new FrameLayout.LayoutParams(DisplayWidth, DisplayHeight);
                        auroraMaskableFrameLayout.setLayoutParams(frameparams);
                        auroraMaskableFrameLayout.setDrawingCacheEnabled(true);
                        auroraMaskableFrameLayout.buildDrawingCache();
                        //new ApplyFilters(currentMask).execute();
                        //img.setImageBitmap(Bitmap.createBitmap(MainGPUImageView.getDrawingCache()));
                        img.setImageBitmap(croppedFilteredBitmap);


                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inPreferredConfig = Bitmap.Config.ARGB_8888;

                        String maskString = readFileAsBase64String(PipName + "/" + currentBean.MaskName + ".txt");
                        byte[] bytes = Base64.decode(maskString, Base64.DEFAULT);
                        AuroraPatrickCox.mBitmapBrush = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);

                        AuroraPatrickCox.mBitmapBrush = Bitmap.createScaledBitmap(AuroraPatrickCox.mBitmapBrush, DisplayWidth, DisplayHeight, false);

                        Drawable d = new BitmapDrawable(mContext.getResources(), AuroraPatrickCox.mBitmapBrush);
                        auroraMaskableFrameLayout.setMask(d);

                        AuroraPatrickCox.mBitmapBrush = null;


                        //arrayListBitmap.add(Bitmap.createBitmap(maskableFrameLayout.getDrawingCache()));
                        RL_MagicEffect.addView(inflatedLayout);

                        //Bitmap b = Bitmap.createBitmap(inflatedLayout.getDrawingCache());
//                    }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if (arrayList.size() > (counter + 1)) {
                counter++;
                new finalGPUAssignAsyncTask().execute();
            } else {
                new finalGPUAssignSketchAsyncTask().execute();
            }

        }

    }

    public static int counter = 0;

    // Save Image Code
    public void mergeAndSave() {


        Bitmap bmOverlay = Bitmap.createBitmap(RL_MagicEffect.getWidth(), RL_MagicEffect.getHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bmOverlay);
        //canvas.drawBitmap(ThirdFinalBitmap, new Matrix(), null);
        canvas.drawBitmap(RL_MagicEffect.getDrawingCache(), 0, 0, null);
        //canvas.drawBitmap(bitmapframe, 0, 0, null);

        AuroraPatrickCox.BlurBitmap = bmOverlay;

        Log.i("TAG", "Image Created");
    }

    public static class finalGPUAssignSketchAsyncTask extends AsyncTask<Void, Void, Void> {
        Bitmap croppedFilteredBitmap = null;

        public finalGPUAssignSketchAsyncTask() {

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... params) {

            mContext.runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    GPUImageViewCroppedImage.setImage(Bitmap.createBitmap(AuroraPatrickCox.bitmap));
                    filterGroup = new GPUImageFilterGroup();
                    //MainGPUImageView.setImage(CommonUtilities.bitmap);
                    try {

                        //InvertColor
                        try {
                            GPUImageColorInvertFilter invertFilter = new GPUImageColorInvertFilter();
                            filterGroup.addFilter(invertFilter);
                        } catch (Exception e) {
                        }

                        //Blur
                        try {
                            int value = 6;
                            float v = (float) value;
                            blurFilter = new GPUImageGaussianBlurFilter(v);
                            filterGroup.addFilter(blurFilter);
                        } catch (Exception e) {
                        }

                        GPUImageViewCroppedImage.setFilter(filterGroup);
                        croppedFilteredBitmap = Bitmap.createBitmap(GPUImageViewCroppedImage.capture());


                        try {
                            BitmapFactory.Options options = new BitmapFactory.Options();
                            options.inPreferredConfig = Bitmap.Config.ARGB_8888;

                            croppedFilteredBitmap = Bitmap.createScaledBitmap(croppedFilteredBitmap, DisplayWidth, DisplayHeight, false);

                            GPUImageColorDodgeBlendFilter overScreenOverlayFilter = new GPUImageColorDodgeBlendFilter();
                            overScreenOverlayFilter.setBitmap(croppedFilteredBitmap);
                            GPUImageViewCroppedImage.setFilter(overScreenOverlayFilter);

                        } catch (Exception e) {
                        }

                        //final color sketch creating here
                        croppedFilteredBitmap = Bitmap.createBitmap(GPUImageViewCroppedImage.capture());

                        Bitmap bmp = adjustOpacity(croppedFilteredBitmap, Integer.parseInt("255"));

                        AuroraPatrickCox.mBitmapSketch = bmp;

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            counter = 0;

            try {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inPreferredConfig = Bitmap.Config.ARGB_8888;

                AuroraPatrickCox.FinalBitmap = Bitmap.createBitmap(RL_MagicEffect.getDrawingCache());
                // GPUImageViewCroppedImage.setImage(CommonUtilities.FinalBitmap);

//                GPUImageOverlayBlendFilter overScreenOverlayFilter = new GPUImageOverlayBlendFilter();
                //GPUImageSoftLightBlendFilter overScreenOverlayFilter = new GPUImageSoftLightBlendFilter();
                //overScreenOverlayFilter.setBitmap(CommonUtilities.mBitmapSketch);
                //GPUImageViewCroppedImage.setFilter(overScreenOverlayFilter);

                AuroraPatrickCox.mBitmapSketch = merge(AuroraPatrickCox.FinalBitmap, croppedFilteredBitmap);//GPUImageViewCroppedImage.capture();

                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(DisplayWidth, DisplayHeight);

                ImageView imgFrame = new ImageView(mContext);
                imgFrame.setLayoutParams(params);
                imgFrame.setScaleType(ImageView.ScaleType.CENTER_CROP);

                imgFrame.setImageBitmap(AuroraPatrickCox.mBitmapSketch);

                RL_MagicEffect.addView(imgFrame);

            } catch (Exception e) {
            }

            InputStream is = null;
            try {

                is = mContext.getAssets().open(PipName + "/" + "bg.txt");
                if (null != is) {
                    String maskString = readFileAsBase64String(PipName + "/" + "bg.txt");
                    byte[] bytes = Base64.decode(maskString, Base64.DEFAULT);

                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inPreferredConfig = Bitmap.Config.ARGB_8888;

                    Bitmap b = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
                    b = Bitmap.createScaledBitmap(b, DisplayWidth, DisplayHeight, false);

                    imgBgMain.setImageBitmap(b);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(DisplayWidth, DisplayHeight);
            RL_MagicEffect.setLayoutParams(params2);
            imgBackMain.setAdjustViewBounds(true);
            imgBackMain.setLayoutParams(params2);
            GPUImageViewCroppedImage.setVisibility(View.GONE);
            dismissProgress();

        }

    }

    private static Bitmap merge(Bitmap bitmapmain, Bitmap bitmapback) {

        Bitmap bmOverlay = Bitmap.createBitmap(bitmapback.getWidth(), bitmapback.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmOverlay);

        canvas.drawBitmap(bitmapback, new Matrix(), null);
        canvas.drawBitmap(bitmapmain, 0, 0, null);

        Log.i("TAG", "Image Created");

        return bmOverlay;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.magic_fragment_main, container, false);
        try {
            FindControls(rootView);
            HeaderControls(rootView);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rootView;
    }

    private void HeaderControls(View rootView) {
        imgButtonImage = (ImageView) rootView.findViewById(R.id.imgButtonImage);
        imgBgMain = (ImageView) rootView.findViewById(R.id.imgBgMain);
        imgButtonImage.setVisibility(View.VISIBLE);

        imgReset = (ImageView) rootView.findViewById(R.id.imgReset);
        imgReset.setVisibility(View.VISIBLE);
        imgReset.setImageResource(R.drawable.ic_edit);
        imgReset.setOnClickListener(this);
        LinearLayout LL_Done = (LinearLayout) rootView.findViewById(R.id.LL_Done);
        LL_Done.setVisibility(View.VISIBLE);

        imgButtonImage.setOnClickListener(this);

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
            dismissProgress();

            FragmentManager fragmentManager = getFragmentManager();
            AuroraOlivierStrelli photoEditorFiltersFragment = new AuroraOlivierStrelli();
            fragmentManager.beginTransaction().replace(R.id.Container, photoEditorFiltersFragment).commit();
        }
    }

    private void FindControls(View rootView) {
        try {
            mContext = getActivity();


            imgBackMain = (ImageView) rootView.findViewById(R.id.imgBackMain);
            imgEdit = (ImageView) rootView.findViewById(R.id.imgEdit);
            imgMain = (ImageView) rootView.findViewById(R.id.imgMain);
            imgframe = (ImageView) rootView.findViewById(R.id.imgframe);
            imgEdit.setOnClickListener(this);

            RL_MagicEffect = (RelativeLayout) rootView.findViewById(R.id.RL_MagicEffect);
            RL_MagicEffect.setDrawingCacheEnabled(true);
            RL_MagicEffect.buildDrawingCache();

            RL_GPU = (RelativeLayout) rootView.findViewById(R.id.RL_GPU);
            RL_GPU.setDrawingCacheEnabled(true);
            RL_GPU.buildDrawingCache();

            arrayList = new ArrayList<>();
            GPUImageViewCroppedImage = (GPUImageView) rootView.findViewById(R.id.GPUImageViewCroppedImage);

            Display display = getActivity().getWindowManager().getDefaultDisplay();
            DisplayWidth = display.getWidth();
            DisplayHeight = display.getHeight();

//            DisplayHeight = (DisplayWidth * 125) / 100;
            DisplayHeight = DisplayWidth;

            FL_ImageFinal = (RelativeLayout) rootView.findViewById(R.id.FL_ImageFinal);
            RL_EditView = (RelativeLayout) rootView.findViewById(R.id.RL_EditView);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(DisplayWidth, DisplayHeight);
            FL_ImageFinal.setLayoutParams(layoutParams);
            FL_ImageFinal.setDrawingCacheEnabled(true);
            FL_ImageFinal.buildDrawingCache();

            appPrefs = new AuroraDeanandDanCaten(getActivity());
            PipName = appPrefs.getPipName().trim();

            showProgress();
            startDecodingLatestCurveData();

            layoutParams = new RelativeLayout.LayoutParams(DisplayWidth, DisplayHeight);
            RL_EditView.setLayoutParams(layoutParams);
            RL_EditView.setVisibility(View.GONE);

            recycler_view = (RecyclerView) rootView.findViewById(R.id.recycler_view);
            databaseAdapter = new AuroraLindaBritten(getActivity());

            gridLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
            recycler_view.setLayoutManager(gridLayoutManager);

            stack = new ArrayList<>();

            // getPosterData();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static Bitmap adjustOpacity(Bitmap bitmap, int opacity) {
        Bitmap mutableBitmap = bitmap.isMutable()
                ? bitmap
                : bitmap.copy(Bitmap.Config.ARGB_8888, true);
        Canvas canvas = new Canvas(mutableBitmap);
        int colour = (opacity & 0xFF) << 24;
        canvas.drawColor(colour, PorterDuff.Mode.DST_IN);
        return mutableBitmap;
    }

    private static ProgressDialog progress;

    public static void showProgress() {
        progress = new ProgressDialog(mContext);
        progress.setMessage("Please Wait ...");
        progress.setIndeterminate(false);
        progress.setCancelable(false);
        progress.setCanceledOnTouchOutside(false);
        progress.show();
    }

    public static void dismissProgress() {
        try {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    try {
                        progress.dismiss();
                    } catch (Exception e) {
                    }
                }
            }, 1000);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //    Animation
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
                viewFlyIn.setVisibility(View.VISIBLE);
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

    private static void SingleflyIn(final View view) {

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
                view.setVisibility(View.VISIBLE);
            }

        });

    }

    ArrayList<Integer> stack;

}
