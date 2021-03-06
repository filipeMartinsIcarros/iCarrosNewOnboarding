package com.example.newonboardingicarros;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

public class OnboardingActivity extends AppCompatActivity {

    //Variables
    ViewPager viewPager;
    LinearLayout dotsLayout;
    OnBoardingAdapter onBoardingAdapter;
    TextView[] dots;

    Button skipButton;
    Button nextButton;
    Button previousButton;

    View viewGradient;
    TextView textOnBoarding1;
    TextView textOnBoarding2;
    ImageView imageOnBoarding1;
    ImageView imageOnBoarding2;
    ImageView imageOnBoarding3;
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
        previousButton = findViewById(R.id.previous_btn);

        //Hooks
        viewPager = findViewById(R.id.slider);
        dotsLayout = findViewById(R.id.dots);
        textOnBoarding1 = findViewById(R.id.textTitle);
        textOnBoarding2 = findViewById(R.id.textDescription);
        imageOnBoarding1 = findViewById(R.id.imageOnBoarding1);
        imageOnBoarding2 = findViewById(R.id.imageOnBoarding2);
        imageOnBoarding3 = findViewById(R.id.imageOnBoarding3);
        viewGradient = findViewById(R.id.viewGradient);

        //Call adapter
        onBoardingAdapter = new OnBoardingAdapter(this);
        viewPager.setAdapter(onBoardingAdapter);

        //Dots
        addDots(0);
        viewPager.addOnPageChangeListener(changeListener);

        //Erro ao matar activity
        animation = AnimationUtils.loadAnimation(OnboardingActivity.this, R.anim.image_anim_start);
        showTextViewAnimation(textOnBoarding1);
        showTextViewAnimation(textOnBoarding2);

        setupViews();
    }

    private void setupViews() {
        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Enviar para pagina come??ar
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                finish();
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentPos + 1 == 4){
                    //Enviar para pagina come??ar
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    finish();
                } else {
                    viewPager.setCurrentItem(currentPos + 1);
                }
            }
        });

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentPos != 0){
                    viewPager.setCurrentItem(currentPos - 1);
                }
            }
        });
    }

    private void addDots(int position) {

        dots = new TextView[5];
        dotsLayout.removeAllViews();

        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(30);
            dots[i].setPadding(6,0,6,0);

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
            animationReverse = AnimationUtils.loadAnimation(OnboardingActivity.this, R.anim.image_anim_out);
            animation = AnimationUtils.loadAnimation(OnboardingActivity.this, R.anim.image_anim_start);

            setColorViewGradient(position);

            if (position == 0) {
                previousButton.setVisibility(View.INVISIBLE);
                hideImageViewAnimation(imageOnBoarding1);
                showTextViewAnimation(textOnBoarding1);
                showTextViewAnimation(textOnBoarding2);
            } else if (position == 1) {
                previousButton.setVisibility(View.VISIBLE);
                hideImageViewAnimation(imageOnBoarding2);
                hideTextViewAnimation(textOnBoarding1);
                hideTextViewAnimation(textOnBoarding2);
                showImageViewAnimation(imageOnBoarding1);
            } else if (position == 2) {
                previousButton.setVisibility(View.VISIBLE);
                textOnBoarding1.setVisibility(View.INVISIBLE);
                textOnBoarding2.setVisibility(View.INVISIBLE);
                hideImageViewAnimation(imageOnBoarding1);
                hideImageViewAnimation(imageOnBoarding3);
                showImageViewAnimation(imageOnBoarding2);
            } else if (position == 3) {
                previousButton.setVisibility(View.VISIBLE);
                textOnBoarding1.setVisibility(View.INVISIBLE);
                textOnBoarding2.setVisibility(View.INVISIBLE);
                hideImageViewAnimation(imageOnBoarding2);
                showImageViewAnimation(imageOnBoarding3);
            } else {
                //Enviar para pagina come??ar
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                finish();
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private void hideImageViewAnimation(ImageView imageView) {
        if (imageView.getVisibility() == View.VISIBLE) {
            imageView.setAnimation(animationReverse);
            imageView.setVisibility(View.INVISIBLE);
        }
    }

    private void showImageViewAnimation(ImageView imageView) {
        imageView.setAnimation(animation);
        imageView.setVisibility(View.VISIBLE);
    }

    private void hideTextViewAnimation(TextView textView) {
        if (textView.getVisibility() == View.VISIBLE) {
            textView.setAnimation(animationReverse);
            textView.setVisibility(View.INVISIBLE);
        }
    }

    private void showTextViewAnimation(TextView textView) {
        textView.setAnimation(animation);
        textView.setVisibility(View.VISIBLE);
    }

    private void setColorViewGradient(int position) {
        if (position == 2) {
            viewGradient.setBackground(ContextCompat.getDrawable(this, R.drawable.gradient_fade_02));
        } else if (position == 3) {
            viewGradient.setBackground(ContextCompat.getDrawable(this, R.drawable.gradient_fade_03));
        } else {
            viewGradient.setBackground(ContextCompat.getDrawable(this, R.drawable.gradient_fade));
        }
    }

}
