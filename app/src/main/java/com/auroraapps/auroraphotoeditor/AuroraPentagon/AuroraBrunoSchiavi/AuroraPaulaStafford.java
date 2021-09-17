package com.auroraapps.auroraphotoeditor.AuroraPentagon.AuroraBrunoSchiavi;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.auroraapps.auroraphotoeditor.AuroraKayCohen.AuroraSusienChong;
import com.auroraapps.auroraphotoeditor.AuroraPentagon.AuroraSophiaTolli.AuroraAndyTruong;
import com.auroraapps.auroraphotoeditor.R;

import java.util.ArrayList;


public class AuroraPaulaStafford extends RecyclerView.Adapter<AuroraPaulaStafford.MyViewHolder> {

    public static ArrayList<AuroraSusienChong> fontsaArrayList;
    private static Context mContext;

    public AuroraPaulaStafford(ArrayList<AuroraSusienChong> data, Context mContext) {
        this.fontsaArrayList = data;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.xpro_font_adapter, parent, false);


        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.textView = (TextView) itemView.findViewById(R.id.txtfonts);

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int possion = Integer.parseInt(v.getTag().toString());

                    try {
                        Typeface typeface = Typeface.createFromAsset(mContext.getAssets(), fontsaArrayList.get(possion).getDirName());
                        AuroraAndyTruong.textbubble.setTypeface(typeface);
                        AuroraAndyTruong.displayAds();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        holder.textView.setTag("" + listPosition);
        Typeface typeface = Typeface.createFromAsset(mContext.getAssets(), fontsaArrayList.get(listPosition).getDirName());
        holder.textView.setTypeface(typeface);
        holder.textView.setText("Abcd");

    }

    @Override
    public int getItemCount() {
        return fontsaArrayList.size();
    }
}