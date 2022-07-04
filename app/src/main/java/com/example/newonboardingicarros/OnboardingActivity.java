package com.example.newonboardingicarros;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class OnboardingActivity extends AppCompatActivity {

    //Variables
    ViewPager viewPager;
    LinearLayout dotsLayout;
    OnBoardingAdapter onBoardingAdapter;
    TextView[] dots;

    Button skipButton;
    Button nextButton;

    Button letsGetStarted;
    Animation animation;
    Animation animationReverse;
    int currentPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_onboarding);

        //btn
        skipButton = findViewById(R.id.skip_btn);
        nextButton = findViewById(R.id.next_btn);

        //Hooks
        viewPager = findViewById(R.id.slider);
        dotsLayout = findViewById(R.id.dots);
        letsGetStarted = findViewById(R.id.get_started_btn);

        //Call adapter
        onBoardingAdapter = new OnBoardingAdapter(this);
        viewPager.setAdapter(onBoardingAdapter);

        //Dots
        addDots(0);
        viewPager.addOnPageChangeListener(changeListener);

        setupViews();
    }

    private void setupViews() {
        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                finish();
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(currentPos + 1);
            }
        });
    }

    private void addDots(int position) {

        dots = new TextView[4];
        dotsLayout.removeAllViews();

        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);

            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0) {
            dots[position].setTextColor(getResources().getColor(R.color.color_blue_primary));
        }

    }

    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);
            currentPos = position;
            animationReverse = AnimationUtils.loadAnimation(OnboardingActivity.this, R.anim.bottom_anim_reverse);
            animation = AnimationUtils.loadAnimation(OnboardingActivity.this, R.anim.bottom_anim);

            if (position == 0) {
                letsGetStarted.setVisibility(View.INVISIBLE);
                nextButton.setVisibility(View.VISIBLE);
            } else if (position == 1) {
                letsGetStarted.setVisibility(View.INVISIBLE);
                nextButton.setVisibility(View.VISIBLE);
            } else if (position == 2) {
                if (letsGetStarted.getVisibility() == View.VISIBLE) {
                    letsGetStarted.setAnimation(animationReverse);
                    nextButton.setAnimation(animation);
                }
                letsGetStarted.setVisibility(View.INVISIBLE);
                nextButton.setVisibility(View.VISIBLE);
            } else {
                letsGetStarted.setAnimation(animation);
                letsGetStarted.setVisibility(View.VISIBLE);
                nextButton.setAnimation(animationReverse);
                nextButton.setVisibility(View.INVISIBLE);
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

}
