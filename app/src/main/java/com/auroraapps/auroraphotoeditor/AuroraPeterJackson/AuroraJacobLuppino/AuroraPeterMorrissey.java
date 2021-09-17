package com.auroraapps.auroraphotoeditor.AuroraPeterJackson.AuroraJacobLuppino;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.auroraapps.auroraphotoeditor.AuroraDovCharney.AuroraPatrickCox;
import com.auroraapps.auroraphotoeditor.AuroraKayCohen.AuroraSusienChong;
import com.auroraapps.auroraphotoeditor.AuroraPeterJackson.AuroraAnthonyPittorino.AuroraNevilleQuist;
import com.auroraapps.auroraphotoeditor.R;

import java.io.IOException;
import java.util.ArrayList;

public class AuroraPeterMorrissey extends RecyclerView.Adapter<AuroraPeterMorrissey.MyViewHolder> {

    ArrayList<AuroraSusienChong> arrayList;
    ArrayList<AuroraSusienChong> arrayListPrev;
    Context mContext;

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        try {
            final AuroraSusienChong item = arrayList.get(position);

            holder.imageViewIcon.setImageBitmap(AuroraPatrickCox.getBitmapFromAsset(arrayListPrev.get(position).getDirName(), mContext));
            holder.imageViewIcon.setTag("" + position);
            holder.imageViewIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    try {
                        int po = Integer.parseInt(v.getTag().toString());
                        Bitmap bmpBlend = AuroraPatrickCox.getBitmapFromAsset(arrayList.get(position).getDirName(), mContext);
                        AuroraNevilleQuist.blendImage = bmpBlend;
                        AuroraNevilleQuist.seekbar.setMax(255);
                        AuroraNevilleQuist.seekbar.setProgress(255);
                        AuroraNevilleQuist.ApplyFilter(AuroraNevilleQuist.Value);
                        AuroraNevilleQuist.displayAds();
                        AuroraNevilleQuist.currentLens = po;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.xpro_sticker_card, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    public AuroraPeterMorrissey(ArrayList<AuroraSusienChong> arrayList, Context mcContext) {
        this.arrayList = arrayList;
        this.mContext = mcContext;

        try {
            arrayListPrev = new ArrayList<>();
            AssetManager assetManager = mcContext.getResources().getAssets();

            String files[] = assetManager.list("screen/prev");
            if (files != null) {
                for (String file : files) {
                    arrayListPrev.add(new AuroraSusienChong("screen/prev/" + file));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageViewIcon;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.imageViewIcon = (ImageView) itemView.findViewById(R.id.imgStickerIcon);

        }
    }
}
