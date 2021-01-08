package com.example.eathaca.ui.community;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.eathaca.R;


import java.util.ArrayList;
import java.util.Objects;

public class CommunityFragment extends Fragment implements  CommunityAdapter.OnItemClickListener {
    private static CommunityAdapter adapter;
    private static ArrayList<CommunityItem> communityList=new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_community, container, false);

        //TODO: backend that returns a arraylist of Community Item

        ArrayList<CommunityItem> a=new ArrayList<>();
        a.add(new CommunityItem(" Indian Creek Farm", "PEACHES Ready in Three Days, Plan Your Trip!","Nestled in Finger Lakes wine country, and blessed with dreamy soil for growing fruit trees, Indian Creek Farm is a 100-year-old orchard and u-pick spot.  Come visit the farm for seasonal picking of apples, peaches, pumpkins, berries, and vegetables.  We are just 3 minutes from downtown Ithaca, New York.",R.drawable.community_peach));
        a.add(new CommunityItem("Hilker Haven Farm and Apiary", "Pick Flowers And Pristine Apples!","This is a hydroponic farm with produce grown in pots arranged on poles. There is no bending to pick fruit and no dirt to walk in. Season: May through October Days / Hours Open: Phone / email contact only; Saturday at Caroline Farmers Market (10 am 2 pm) and Thursday at Enfield Farmers Market (3pm-7pm) Products Available: Local NY raw honey, orange blossom and palm honey from our Florida location, beeswax, crop pollination",R.drawable.community_sheep));
        a.add(new CommunityItem("Kestrel Perch Berry CSA", "Tryout out Our New Stawberries!","A.J. Teeter Farm is the oldest family farm in the Town of Ithaca. We were one of the first CSA (Community Support Agriculture) farms in the county, and only gave that up, regretfully, when we assumed responsibility for the entire farm and those responsibilities no longer allowed for the time needed to run a CSA. We continued to grow our own vegetable seedlings for transplant and have been a regular vendor at the Annual Ithaca Plant Sale for many years. Starting in 2013, we will offer Custom Transplants, adapting a CSA model, to provide a wider range of varieties as well as the exact quantities of the varieties our customers want. If this intrigues you, please contact us and we will be in touch after the first of the year.",R.drawable.community_image_example));
        a.add(new CommunityItem("The Groundswell Center for Local Food & Farming", "Come! Don’t let the goats eat all of our fresh tomatos!","Groundswell offers beginning farmer training and food system education. Our programs include the Incubator Farm Business Program, Farm Tours, Food Justice Training, Farm Business Planning Course, Farmer Technical Courses, and one-on-one consultations for farmers and food entrepreneurs.  Please contact us about your farming interests.  Groundswell also hosts an online Event Calendar or regional farming & food events and Classified listings at www.groundswellcenter.org",R.drawable.community_fruits));


        adapter = new CommunityAdapter(getActivity(),a);
        adapter.setItemClickListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        RecyclerView mRecyclerView = (RecyclerView)   root.findViewById(R.id.community_list);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter);


        return root;
    }

    @Override
    public void onItemClick(int position) {
        String postTitle = adapter.getmList().get(position).getAdvertiseTitle();
        String postContent = adapter.getmList().get(position).getAdvertiseContent();
        String farmName = adapter.getmList().get(position).getFarmName();
        int imageUrl=adapter.getmList().get(position).getImageUrl();
        //TODO:Customized MaterialDialogue for the post
        final Dialog dialog = new Dialog(Objects.requireNonNull(getActivity()), R.style.FullHeightDialog);

        dialog.setContentView(R.layout.custom_dialogue_community2);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        Button orderButton = (Button) dialog.findViewById(R.id.order);
        orderButton.setOnClickListener(view -> dialog.dismiss());
        Button cancelButton = (Button) dialog.findViewById(R.id.cancel);
        cancelButton.setOnClickListener(view -> dialog.dismiss());
        dialog.show();

        EditText ed_farmName=dialog.findViewById(R.id.ET_farm_name);
        ed_farmName.setText(farmName);
        EditText ed_adTitle=dialog.findViewById(R.id.ET_ad_Title);
        ed_adTitle.setText(postTitle);
        EditText ed_adContent=dialog.findViewById(R.id.ET_ad_Content);
        ed_adContent.setText(postContent);
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        dialog.getWindow().setLayout((6* width)/7, (9* height)/10);


    }



    public static void saveAndUpdateNewFarmAd(){


        adapter.addData(new CommunityItem("HoneyRock Farm, LLC", " Giant Sweet Blueberries On The Branch!","HoneyRock Farm is a local family-owned apiary producing 100% pure unpasteurized honey. Our product is available in several varieties reflecting our land  and the seasonal changes in our local plant life. Our honey can be purchased at many locations in and around Ithaca: P&C Fresh (East Hill Plaza), Agway True Value (Fulton St), Agway Northeast (Triphammer Rd), Lively Run Goat Dairy (Interlaken NY) , Cafe Jennie (Cornell Bookstore), J.R. Dill Winery (Burdett NY), and directly from our Online Store. Please check out our website for more information. ",R.drawable.community_blueberries));
        adapter.notifyDataSetChanged();
    }
}