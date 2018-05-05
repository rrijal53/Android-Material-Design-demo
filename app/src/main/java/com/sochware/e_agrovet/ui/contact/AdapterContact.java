package com.sochware.e_agrovet.ui.contact;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sochware.e_agrovet.R;
import com.sochware.e_agrovet.pojo.Contacts;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterContact extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Contacts> list;

    public AdapterContact(Context context, List<Contacts> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_contact, parent, false);
        return new ViewHolderContact(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolderContact) {
            ((ViewHolderContact) holder).name.setText(list.get(position).getName());
            ((ViewHolderContact) holder).address.setText(list.get(position).getAdd());
            ((ViewHolderContact) holder).orgn.setText(list.get(position).getOrg());
            ((ViewHolderContact) holder).phone.setText(list.get(position).getContact());
            ((ViewHolderContact) holder).contact.setOnClickListener(v -> {
                Intent inn = null;
                inn = new Intent(Intent.ACTION_DIAL);
                inn.setData(Uri.parse("tel:" + list.get(position).getContact()));
                context.startActivity(inn);

            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolderContact extends RecyclerView.ViewHolder {
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.address)
        TextView address;
        @BindView(R.id.orgn)
        TextView orgn;
        @BindView(R.id.phone)
        TextView phone;

        @BindView(R.id.contact)
        ImageView contact;
        @BindView(R.id.map)
        ImageView map;

        public ViewHolderContact(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
