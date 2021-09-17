package com.auroraapps.auroraphotoeditor.AuroraRuthTarvydas.AuroraEricaWarde;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.auroraapps.auroraphotoeditor.AuroraDovCharney.AuroraDeanandDanCaten;
import com.auroraapps.auroraphotoeditor.AuroraDovCharney.AuroraPatrickCox;
import com.auroraapps.auroraphotoeditor.AuroraKayCohen.AuroraSusienChong;
import com.auroraapps.auroraphotoeditor.R;
import com.auroraapps.auroraphotoeditor.AuroraRuthTarvydas.AuroraAhedZanetti.AuroraVeroniqueBranquinho;

import java.util.ArrayList;

public class AuroraBowieWong extends RecyclerView.Adapter<AuroraBowieWong.MyViewHolder> {

    public ArrayList<AuroraSusienChong> dataSet;
    private Context mContext;

    AuroraDeanandDanCaten appPrefs;

    @Override
    public int getItemCount() {
        return dataSet.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageViewIcon;

        FrameLayout LL_Progress;

        public MyViewHolder(final View itemView) {
            super(itemView);
            this.imageViewIcon = (ImageView) itemView.findViewById(R.id.imgPIPFramePreview);
        }
    }
    public AuroraBowieWong(ArrayList<AuroraSusienChong> data, Context mContext) {
        this.dataSet = data;
        this.mContext = mContext;
        appPrefs = new AuroraDeanandDanCaten(mContext);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        holder.imageViewIcon.setTag("" + listPosition);
        holder.imageViewIcon.setColorFilter(R.color.black);

        holder.imageViewIcon.setImageBitmap(AuroraPatrickCox.getBitmapFromAsset(dataSet.get(listPosition).getDirName(), mContext));

        holder.imageViewIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int pos = Integer.parseInt(v.getTag().toString());

                    Bitmap bitmap = AuroraPatrickCox.getBitmapFromAsset(dataSet.get(pos).getDirName(), mContext);
                    AuroraVeroniqueBranquinho.AddSticker(bitmap);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_filter_abc_card_row, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }


}


