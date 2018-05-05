package com.sochware.e_agrovet.ui.contact;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sochware.e_agrovet.R;
import com.sochware.e_agrovet.datamanager.ServerRequest;
import com.sochware.e_agrovet.pojo.Contacts;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactFragment extends Fragment implements ServerRequest.GetContactCallback {

    @BindView(R.id.rv_contact)
    RecyclerView rvContact;
    @BindView(R.id.nodata)
    TextView nodata;
    ServerRequest sq;
    public ContactFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_contact, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rvContact.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvContact.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        sq = new ServerRequest();
        showLoading();
        sq.getContacts(this);

    }

    void showLoading(){
        nodata.setText("Loading....");
        nodata.setVisibility(View.VISIBLE);
    }

    void hideLoading(){
        nodata.setVisibility(View.GONE);
    }

    @Override
    public void onSuccess(List<Contacts> res) {
            if (res!=null && res.size() > 0){
                hideLoading();
                rvContact.setAdapter(new AdapterContact(getActivity(), res));
            }else{
                nodata.setText("No data ...");
                nodata.setVisibility(View.VISIBLE);
            }
    }

    @Override
    public void onError(String error) {

    }
}
