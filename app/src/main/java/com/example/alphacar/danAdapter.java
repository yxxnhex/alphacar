package com.example.alphacar;

import android.content.Context;
import android.util.Log;
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
        String latitude = dan.get(i).getLatitude();
        String longitude = dan.get(i).getLongitude();
        String loc = "";

        Log.e("latitude",latitude);
        Log.e("longitude",longitude);

        if (latitude.equals("null")) {
            if (longitude.equals("null")) {
                loc = " ";
            }
        } else {
            loc = latitude + "\n" + longitude;
        }



        tvDT.setText(dan.get(i).getEvent_time());
        tvLoc.setText(loc);
        tvColl.setText(acc(loc));

        // 사고 방향 텍스트 바꿔서 보내주기
        // FR : 전방
        // BK : 후방
        // LF : 좌측
        // RT : 우측
        if (dan.get(i).getEvent_type().equals("FR")) {
            tvEvent.setText("전방");
        } else if (dan.get(i).getEvent_type().equals("BK")) {
            tvEvent.setText("후방");
        } else if (dan.get(i).getEvent_type().equals("LF")) {
            tvEvent.setText("좌측");
        }else if (dan.get(i).getEvent_type().equals("RT")) {
            tvEvent.setText("우측");
        }
//        tvEvent.setText(dan.get(i).getEvent_type());

        return view;
    }
    // loc에 값이 있을 경우 '사고' 보내주기
    public String acc(String loc) {
        if (loc.equals(" ")) {
            Log.e("acc",loc);
            return " ";
        } else {
            return "사고";
        }
    }
}
