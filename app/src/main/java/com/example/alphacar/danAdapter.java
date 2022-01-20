package com.example.alphacar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class danAdapter extends BaseAdapter {

    private ArrayList<danVO> dan = new ArrayList<>();
    private Context context;
    private int layout;

    public danAdapter(ArrayList<danVO> dan, Context context, int layout) {
        this.dan = dan;
        this.context = context;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return dan.size();
    }

    @Override
    public Object getItem(int i) {
        return dan.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dan_list,null);
        }

        TextView tvDT = view.findViewById(R.id.tvDT);
        TextView tvLoc = view.findViewById(R.id.tvLoc);
        TextView tvEvent = view.findViewById(R.id.tvEvent);
        TextView tvColl = view.findViewById(R.id.tvColl);

        // Date -> String
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String eventTime = format.format(dan.get(i).getEvent_time());

        // latitude + longitude
        String loc = dan.get(i).getLatitude() + "\n" + dan.get(i).getLongitude();



        tvDT.setText(dan.get(i).getEvent_time());
        tvLoc.setText(loc);
        tvEvent.setText(dan.get(i).getEvent_type());
        tvColl.setText(acc(loc));

        return view;
    }
    // loc에 값이 있을 경우 '사고' 보내주기
    public String acc(String loc) {
        if (loc != null) {
            return "사고";
        } else {
            return " ";
        }
    }
}
