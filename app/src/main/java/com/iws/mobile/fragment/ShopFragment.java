package com.iws.mobile.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.iws.mobile.R;
import com.iws.mobile.adapter.ShopAdapter;
import com.iws.mobile.model.ShopItem;

import java.util.ArrayList;

public class ShopFragment extends Fragment {
    RecyclerView rv;
    ShopAdapter adapter;
    ArrayList<ShopItem> items;
    ScrollView sv;

    View v;

    public ShopFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v =  inflater.inflate(R.layout.fragment_shop, container, false);
        rv = v.findViewById(R.id.rv_shop);
        items = new ArrayList<>();
        sv = v.findViewById(R.id.sv_shop);
        sv.setNestedScrollingEnabled(false);
        rv.setNestedScrollingEnabled(false);

        setRecylerView();
        return v;
    }

    private void setRecylerView(){
        adapter = new ShopAdapter(v.getContext(), items);
        rv.setLayoutManager(new GridLayoutManager(v.getContext(), 2));
        rv.setAdapter(adapter);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getData();
    }

    private void getData(){
        String url = "https://images.theconversation.com/files/93616/original/image-20150902-6700-t2axrz.jpg";
        items.add(new ShopItem("001", "nama 1", "caption 1", "500 bv", url, 4404000, true, true));
        items.add(new ShopItem("002", "nama 2", "caption 2", "400 bv", url, 1050000, true, false));
        items.add(new ShopItem("003", "nama 3", "caption 3", "5500 bv", url, 4500000, false, true));
        items.add(new ShopItem("004", "nama 4", "caption 4", "800 bv", url, 1000000, false, false));
        items.add(new ShopItem("002", "nama 2", "caption 2", "400 bv", url, 1050000, true, false));
        items.add(new ShopItem("003", "nama 3", "caption 3", "5500 bv", url, 4500000, false, true));

        ShopAdapter adapter = new ShopAdapter(v.getContext(), items);
        rv.setAdapter(adapter);
    }
}