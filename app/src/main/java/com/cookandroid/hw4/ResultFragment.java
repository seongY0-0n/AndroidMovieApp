package com.cookandroid.hw4;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ResultFragment extends Fragment {
    View view;
    myDBHelper helper;
    SQLiteDatabase sqLiteDatabase;

    public ResultFragment(myDBHelper Helper){this.helper = Helper;}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.result_fragment,container,false);

        helper = new myDBHelper(getActivity());
        sqLiteDatabase = helper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM voteMovie",null);

        TextView movie[] = new TextView[cursor.getCount()];
        RatingBar bar[]= new RatingBar[cursor.getCount()];
        Integer movieID[] = {R.id.movie1,R.id.movie2,R.id.movie3,R.id.movie4,R.id.movie5,R.id.movie6,R.id.movie7,R.id.movie8,R.id.movie9,R.id.movie10};
        Integer barID[] = {R.id.bar1,R.id.bar2,R.id.bar3,R.id.bar4,R.id.bar5,R.id.bar6,R.id.bar7,R.id.bar8,R.id.bar9,R.id.bar10};


        for (int i = 0; i<cursor.getCount(); i++){
            movie[i] = view.findViewById(movieID[i]);
            bar[i] = view.findViewById(barID[i]);
        }
        while (cursor.moveToNext()){
            movie[cursor.getPosition()].setText(cursor.getString(0));
            bar[cursor.getPosition()].setRating(Float.parseFloat(cursor.getString(1)));
        }
        cursor.close();
        sqLiteDatabase.close();
        return view;
    }
}
