package com.auroraapps.auroraphotoeditor.AuroraDionLee;


import android.app.Dialog;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.auroraapps.auroraphotoeditor.AuroraCantista.AuroraMantid;
import com.auroraapps.auroraphotoeditor.AuroraDovCharney.AuroraDeanandDanCaten;
import com.auroraapps.auroraphotoeditor.AuroraDovCharney.AuroraPatrickCox;
import com.auroraapps.auroraphotoeditor.AuroraKayCohen.AuroraSusienChong;
import com.auroraapps.auroraphotoeditor.R;
import com.dd.processbutton.iml.ActionProcessButton;

import java.io.IOException;
import java.util.ArrayList;

public class AuroraRebeccaJudd extends Fragment {

    private boolean FrameDownload = false;
    private int Ads = -1;
    private Handler handler;
    private GetDownloadCompleted getDownloadCompleted;

    private PreviewAdapter adapter;

    public AuroraRebeccaJudd() {
    }

    String effectName[] = {"Adonias", "Bacchus", "Blackflower", "Boxingstar", "Browndown", "Dacey", "Ealasaid", "Earleen", "Flyingman", "fotospot", "Gotulost", "haddley", "Heriwarm", "jumpket", "kulamt", "librotus", "potruzone", "purpcore", "viscotta", "albormentio"};

    RecyclerView PreviewRecyclerView;

    private void FindControls(View view) {

        PreviewRecyclerView = (RecyclerView) view.findViewById(R.id.PreviewRecyclerView);

        AppData();

    }

    AuroraDeanandDanCaten appPrefs;
    ArrayList<AuroraSusienChong> arrayList;
    ArrayList<AuroraSusienChong> arrayList1;

    private void AppData() {

        StaggeredGridLayoutManager _sGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        PreviewRecyclerView.setLayoutManager(_sGridLayoutManager);

        try {
            arrayList = new ArrayList<>();
            arrayList1 = new ArrayList<>();
            AssetManager assetManager = getActivity().getResources().getAssets();

            try {
                String files[] = assetManager.list("effect");
                if (files != null) {
                    for (String file : files) {
                        arrayList1.add(new AuroraSusienChong("effect/" + file));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            String files[] = assetManager.list("prev");
            if (files != null) {
                for (int i = 0; i < files.length; i++) {
                    arrayList.add(new AuroraSusienChong("prev/" + files[i], effectName[i]));
                }

                adapter = new PreviewAdapter(getActivity());
                PreviewRecyclerView.setAdapter(adapter);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_preview, container, false);

        try {

            appPrefs = new AuroraDeanandDanCaten(getActivity());

            handler = new Handler();

            getDownloadCompleted = new GetDownloadCompleted();

            FindControls(rootView);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return rootView;
    }

    public class PreviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private Context mContext;
        private static final int RECIPE = 0;
        private static final int FB_NATIVE_AD = 1;
        private static final int AM_NATIVE_AD = 2;


        public PreviewAdapter(Context mContext) {
            this.mContext = mContext;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            ImageView ThemePreviewImage, down_icon;

            TextView txtEdit, txtImageName;

            public MyViewHolder(final View itemView) {
                super(itemView);
                this.ThemePreviewImage = (ImageView) itemView.findViewById(R.id.ThemePreviewImage);
                this.down_icon = (ImageView) itemView.findViewById(R.id.down_icon);

                this.txtImageName = (TextView) itemView.findViewById(R.id.txtImageName);
                this.txtEdit = (TextView) itemView.findViewById(R.id.txtEdit);
            }
        }


        @Override
        public int getItemViewType(int position) {
                return RECIPE;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);
                View recipeItem = inflater.inflate(R.layout.preview_adapter, parent, false);
                return new MyViewHolder(recipeItem);
        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }

        @Override
        public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int listPosition) {

            int itemType = getItemViewType(listPosition);


            final MyViewHolder recipeViewHolder = (MyViewHolder) holder;

            final AuroraSusienChong shimmerBean = (AuroraSusienChong) arrayList.get(listPosition);
            final AuroraSusienChong shimmerBean1 = (AuroraSusienChong) arrayList1.get(listPosition);

            recipeViewHolder.ThemePreviewImage.setTag("" + listPosition);

            recipeViewHolder.ThemePreviewImage.setImageBitmap(AuroraPatrickCox.getBitmapFromAsset(shimmerBean.getDirName(), mContext));

            recipeViewHolder.txtImageName.setText(shimmerBean.getFileName());

            recipeViewHolder.down_icon.setVisibility(View.VISIBLE);

            if (appPrefs.getDownloadArrayList() != null) {

                for (int i = 0; i < appPrefs.getDownloadArrayList().size(); i++) {

                    if (appPrefs.getDownloadArrayList().get(i).equals(shimmerBean.getDirName())) {
                        recipeViewHolder.down_icon.setVisibility(View.GONE);
                    }
                }
            }

            recipeViewHolder.ThemePreviewImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {

                        if (recipeViewHolder.down_icon.getVisibility() == View.VISIBLE) {

                            ArrayList<String> stringArrayList = appPrefs.getDownloadArrayList();
                            if (stringArrayList == null) {
                                stringArrayList = new ArrayList<>();
                            }
                            stringArrayList.add(shimmerBean.getDirName());
                            appPrefs.setDownloadArrayList(stringArrayList);

                            OpenDownloadDialog(listPosition);

                        } else {

                            appPrefs.setPipId("" + listPosition);
                            appPrefs.setPipName(shimmerBean1.getDirName());

                            AuroraMantid.Cat = 1;
                            AuroraMantid.counter = 1;
                            AuroraMantid.pickFromGallery();

                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            recipeViewHolder.txtEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        appPrefs.setPipId("" + listPosition);
                        appPrefs.setPipName(shimmerBean1.getDirName());

                        AuroraMantid.Cat = 1;
                        AuroraMantid.counter = 1;
                        AuroraMantid.pickFromGallery();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    }


    ActionProcessButton btnDownload;
    Dialog alertDialogBuilder;

    private void OpenDownloadDialog(final int listPosition) {

        final AuroraSusienChong susienChong1 = (AuroraSusienChong) arrayList1.get(listPosition);

        alertDialogBuilder = new Dialog(getActivity());
        alertDialogBuilder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialogBuilder.setContentView(R.layout.frame_download_dialog);

        final RelativeLayout nativeAdsLayout = (RelativeLayout) alertDialogBuilder.findViewById(R.id.nativeAdsLayout);
        btnDownload = (ActionProcessButton) alertDialogBuilder.findViewById(R.id.btnDownload);

        handler.postDelayed(getDownloadCompleted, 2000);

        btnDownload.setMode(ActionProcessButton.Mode.ENDLESS);

        btnDownload.setProgress(10);

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialogBuilder.dismiss();
                appPrefs.setPipId("" + listPosition);
                appPrefs.setPipName(susienChong1.getDirName());

                AuroraMantid.Cat = 1;
                AuroraMantid.counter = 1;
                AuroraMantid.pickFromGallery();

            }
        });

        alertDialogBuilder.setCancelable(false);

        alertDialogBuilder.show();
    }

    class GetDownloadCompleted implements Runnable {
        GetDownloadCompleted() {
        }

        public void run() {

            if (FrameDownload ) {
                Log.e("GetDownloadCompleted", "FrameDownload " + FrameDownload + " Ads " + Ads);
                btnDownload.setProgress(100);
                btnDownload.setEnabled(true);
                if (adapter != null) {
                    adapter.notifyDataSetChanged();
                }
                handler.removeCallbacks(getDownloadCompleted);
            } else {
                Log.e("GetDownloadCompleted", "FrameDownload " + FrameDownload + " Ads " + Ads);
                handler.removeCallbacks(getDownloadCompleted);
                FrameDownload = true;
                handler.postDelayed(getDownloadCompleted, 3000);
            }
        }
    }

}
