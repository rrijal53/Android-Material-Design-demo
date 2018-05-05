package com.sochware.e_agrovet.ui.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sochware.e_agrovet.R;
import com.sochware.e_agrovet.pojo.HomeItem;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterHome extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<HomeItem> list;

    public AdapterHome(Context context, List<HomeItem> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_home, parent, false);
        return new ViewHolderHome(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolderHome) {
            ((ViewHolderHome) holder).title.setText(list.get(position).getName());
            ((ViewHolderHome) holder).image.setImageResource(list.get(position).getImage());
//            if (!TextUtils.isEmpty(list.get(position).getIcon())) {
//                Picasso.with(context).load(list.get(position).getIcon()).into(((ViewHolderHome) holder).image);

//            } else {
//                ((ViewHolderHome) holder).image.setImageResource(R.mipmap.ic_launcher);
//            }

            holder.itemView.setOnClickListener(v ->{
                Log.d("Clicke", "onBindViewHolder: ");
            });
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class ViewHolderHome extends RecyclerView.ViewHolder {
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.title)
        TextView title;

        public ViewHolderHome(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
