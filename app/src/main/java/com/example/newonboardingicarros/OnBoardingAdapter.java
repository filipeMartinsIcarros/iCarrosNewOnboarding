package com.example.newonboardingicarros;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerAdapter;

public class OnBoardingAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public OnBoardingAdapter(Context context) {
        this.context = context;
    }

    int headings[] = {
            R.string.empty_slide_title,
            R.string.second_slide_title,
            R.string.third_slide_title,
            R.string.fourth_slide_title,
            R.string.empty_slide_title
    };

    int descriptions[] = {
            R.string.empty_slide_desc,
            R.string.second_slide_desc,
            R.string.third_slide_desc,
            R.string.fourth_slide_desc,
            R.string.empty_slide_desc
    };

    @Override
    public int getCount() {
        return headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (ConstraintLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.item_container_onboarding, container, false);

        View itemBackground = view.findViewById(R.id.itemBackground);

        if (position == 2) {
            itemBackground.setBackgroundColor(ContextCompat.getColor(context, R.color.color_blue_primary02));
           // viewGradient.setBackground(ContextCompat.getDrawable(context, R.drawable.gradient_fade_02));
        } else if (position == 3) {
            itemBackground.setBackgroundColor(ContextCompat.getColor(context, R.color.color_blue_primary03));
            //viewGradient.setBackground(ContextCompat.getDrawable(context, R.drawable.gradient_fade_03));
        } else {
            itemBackground.setBackgroundColor(ContextCompat.getColor(context, R.color.color_blue_primary));
           // viewGradient.setBackground(ContextCompat.getDrawable(context, R.drawable.gradient_fade));
        }

        TextView headingsView = view.findViewById(R.id.textTitle);
        TextView descriptionsView = view.findViewById(R.id.textDescription);

        headingsView.setText(headings[position]);
        descriptionsView.setText(descriptions[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout) object);
    }
}
