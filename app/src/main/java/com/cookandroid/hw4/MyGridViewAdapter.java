package com.cookandroid.hw4;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

public class MyGridViewAdapter extends BaseAdapter {
    Integer [] posterID = {
            R.drawable.mov01,R.drawable.mov02,R.drawable.mov03,R.drawable.mov04,R.drawable.mov05,R.drawable.mov06,R.drawable.mov07,R.drawable.mov08,R.drawable.mov09,R.drawable.mov10
    };
    String[] movieTitle = new String[]{"토이스토리4", "호빗", "제이슨본", "반지의제왕", "정직한 후보", "나쁜녀석들", "겨울왕국2", "알라딘", "극한직업", "스파이더맨"};
    Context context;
    myDBHelper helper;
    SQLControl sqlControl;
    String a,b;
    public MyGridViewAdapter(Context c){
        context = c;
    }
    @Override
    public int getCount() {
        return posterID.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView = new ImageView(context);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(200,300));
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView.setPadding(5,5,5,5);
        imageView.setImageResource(posterID[i]);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View dialogView = (View) View.inflate(context,R.layout.dialog,null);

                ImageView ivPoster = (ImageView) dialogView.findViewById(R.id.ivMovie);
                TextView movie_name = (TextView)dialogView.findViewById(R.id.movie_name);
                ImageButton btn_like = (ImageButton)dialogView.findViewById(R.id.btn_like);
                TextView likeNum = (TextView)dialogView.findViewById(R.id.likeNum);

                helper= new myDBHelper(context);
                sqlControl = new SQLControl(helper);
                a=sqlControl.select(i);
                likeNum.setText(a);

                btn_like.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        sqlControl.update(movieTitle[i],a);
                        b = sqlControl.select(i);
                        likeNum.setText(b);
                        a = b;

                    }
                });
                ivPoster.setImageResource(posterID[i]);
                movie_name.setText(movieTitle[i]);
                AlertDialog.Builder dlg = new AlertDialog.Builder(context);
                dlg.setTitle("Large Poster");
                dlg.setView(dialogView);
                dlg.setNegativeButton("close", null);
                dlg.show();
            }
        });
        return imageView;
    }
}
