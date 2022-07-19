package com.hakancevik.currencyretrofit.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hakancevik.currencyretrofit.databinding.RecyclerRowBinding;
import com.hakancevik.currencyretrofit.model.CryptoModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CryptoAdapter extends RecyclerView.Adapter<CryptoAdapter.CryptoHolder> {

    ArrayList<CryptoModel> cryptoModelArrayList;

    public CryptoAdapter(ArrayList<CryptoModel> cryptoModelArrayList) {
        this.cryptoModelArrayList = cryptoModelArrayList;
    }

    @NonNull
    @Override
    public CryptoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerRowBinding recyclerRowBinding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CryptoHolder(recyclerRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CryptoHolder holder, int position) {
        holder.binding.recyclerViewNameText.setText(cryptoModelArrayList.get(position).name);
        holder.binding.recyclerViewCurrencyText.setText(cryptoModelArrayList.get(position).currency);
        holder.binding.recyclerViewPriceText.setText("$" + cryptoModelArrayList.get(position).price);
        Picasso.get().load(cryptoModelArrayList.get(position).logo_url).into(holder.binding.recyclerViewImage);
    }

    @Override
    public int getItemCount() {
        return cryptoModelArrayList.size();
    }

    public class CryptoHolder extends RecyclerView.ViewHolder {

        private RecyclerRowBinding binding;

        public CryptoHolder(RecyclerRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }


    }
}
