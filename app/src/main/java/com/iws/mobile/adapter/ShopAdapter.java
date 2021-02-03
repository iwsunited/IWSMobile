package com.iws.mobile.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.iws.mobile.R;
import com.iws.mobile.activity.DetailBarangActivity;
import com.iws.mobile.model.ShopItem;

import java.util.ArrayList;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ViewHolder> {
    private Context context;
    private ArrayList<ShopItem> items;
    private ShopItem item;

    public ShopAdapter(Context context) {
        this.context = context;
    }

    public ShopAdapter(Context context, ArrayList<ShopItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_shop, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ShopItem item = items.get(position);
        holder.tvNama.setText(item.getNama());
        holder.tvCaption.setText(item.getCaption());
        holder.tvHarga.setText("Rp. " + String.format("%,d", item.getHarga()));
        holder.tvBv.setText(item.getBv());

        holder.id = item.getId();
        holder.baru = item.isBaru();
        holder.promo = item.isPromo();

        if (item.isBaru())
            holder.imgNew.setVisibility(View.VISIBLE);
        if (item.isPromo())
            holder.imgPromo.setVisibility(View.VISIBLE);

        Glide.with(context)
                .load(item.getImgUrl())
                .into(holder.imgThumbnail);

        holder.clContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do nothing
                Intent intent = new Intent(context, DetailBarangActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgThumbnail, imgPromo, imgNew;
        TextView tvNama, tvCaption, tvHarga, tvBv;
        String id;
        ConstraintLayout clContainer;
        Boolean promo, baru;

        public ViewHolder(@NonNull View v) {
            super(v);
            imgThumbnail = v.findViewById(R.id.img_c_shop_thumbnail);
            imgPromo = v.findViewById(R.id.img_c_shop_promo);
            imgNew = v.findViewById(R.id.img_c_shop_new);
            tvNama = v.findViewById(R.id.tv_c_shop_nama);
            tvCaption = v.findViewById(R.id.tv_c_shop_caption);
            tvHarga = v.findViewById(R.id.tv_c_shop_harga);
            tvBv = v.findViewById(R.id.tv_c_shop_bv);
            clContainer = v.findViewById(R.id.cl_c_shop_container);
        }
    }
}
