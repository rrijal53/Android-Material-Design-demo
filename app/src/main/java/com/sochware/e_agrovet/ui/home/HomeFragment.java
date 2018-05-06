package com.sochware.e_agrovet.ui.home;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.sochware.e_agrovet.R;
import com.sochware.e_agrovet.pojo.HomeItem;
import com.sochware.e_agrovet.pojo.Slider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    @BindView(R.id.slider)
    SliderLayout mSliderLayout;
    @BindView(R.id.rv_home)
    RecyclerView rvHome;

    public HomeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        List<Slider> list = new ArrayList<>();
        String url="http://52.224.13.239/agrovet/img/";

        for (int in=4;in>=1;in--)
        {
            String final_url=url+"banner"+in+".jpg";
            list.add(new Slider("1","Test1",final_url));

        }
//        list.add(new Slider("1", "Test", "https://static.pexels.com/photos/248797/pexels-photo-248797.jpeg"));
//        list.add(new Slider("1", "Test", "http://www.dolcevitatravelmagazine.com/wp-content/uploads/2014/10/Cook-Islands-Background.jpg"));
//        list.add(new Slider("1", "Test", "https://static.pexels.com/photos/248797/pexels-photo-248797.jpeg"));
        setUpSlider(list);
        setUpRecyclerView();

    }

    private void setUpRecyclerView() {
        rvHome.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        List<HomeItem> list = new ArrayList<>();
        list.add(new HomeItem(getString(R.string.cam), R.mipmap.scan_leaf_round));
        list.add(new HomeItem(getString(R.string.history), R.mipmap.history));
        list.add(new HomeItem(getString(R.string.geo), R.mipmap.location_foreground));
        list.add(new HomeItem(getString(R.string.contact), R.mipmap.contact_round));
        list.add(new HomeItem(getString(R.string.help), R.mipmap.ic_launcher));
        list.add(new HomeItem(getString(R.string.query), R.mipmap.ic_launcher));
        rvHome.setAdapter(new AdapterHome(getActivity(), list));
    }

    public void setUpSlider(List<Slider> slides) {
        mSliderLayout.removeAllSliders();
        for (final Slider slide : slides) {
            TextSliderView textSliderView = new TextSliderView(getActivity());
            textSliderView
                    .description(slide.getName())
                    .image(slide.getImage_url())
                    .setScaleType(BaseSliderView.ScaleType.CenterCrop)
                    .setOnSliderClickListener(slider -> {

                    });
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", "Ic launcher");
            mSliderLayout.addSlider(textSliderView);
        }
        mSliderLayout.setPresetTransformer(SliderLayout.Transformer.Stack);
        mSliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mSliderLayout.setCustomAnimation(new DescriptionAnimation());
        mSliderLayout.setDuration(4000);
    }

}
