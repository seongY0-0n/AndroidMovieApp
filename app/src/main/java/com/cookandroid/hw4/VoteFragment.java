package com.cookandroid.hw4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class VoteFragment extends Fragment {
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.vote_fragment, container, false);

        GridView gridView = (GridView)view.findViewById(R.id.gridview);
        MyGridViewAdapter gridAdapter = new MyGridViewAdapter(getActivity());
        gridView.setAdapter(gridAdapter);

        return view;
    }
}
