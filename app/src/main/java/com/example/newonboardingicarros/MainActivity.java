package com.example.newonboardingicarros;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private OnBoardingAdapter onBoardingAdapter;
    private LinearLayout layoutOnBoardingIndicators;
    private Button buttonOnboardingAction;
    private Button buttonOnboardingSkip;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupOnboardingItems();

        layoutOnBoardingIndicators = findViewById(R.id.layoutOnBoardingIndicators);
        buttonOnboardingAction = findViewById(R.id.buttonOnboardingAction);
        buttonOnboardingSkip = findViewById(R.id.buttonOnboardingSkip);
        ViewPager2 onboardingViewPager = findViewById(R.id.onBoardingViewPager);
        onboardingViewPager.setAdapter(onBoardingAdapter);
        setupOnboardingIndicators();
        setCurrentOnboardingIndicator(0);
        setupViews();

        onboardingViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentOnboardingIndicator(position);
            }
        });
    }

    private void setupViews() {
        buttonOnboardingAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        buttonOnboardingSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void setupOnboardingItems(){

        List<OnboardingItem> onboardingItems = new ArrayList<>();

        OnboardingItem onboardingItem1 = new OnboardingItem();
        onboardingItem1.setTitle("Busque seu novo Carro");
        onboardingItem1.setDescription("Procure digitando pelo modelo ou faça uma pesquisa por voz");
        onboardingItem1.setImage(R.drawable.on_boarding_1);

        OnboardingItem onboardingItem2 = new OnboardingItem();
        onboardingItem2.setTitle("Filtros personalizados");
        onboardingItem2.setDescription("Encontre exatamente o que precisa refinando sua pesquisa por variso tipos de filtros (preço, ano, km)");
        onboardingItem2.setImage(R.drawable.on_boarding_2);

        OnboardingItem onboardingItem3 = new OnboardingItem();
        onboardingItem3.setTitle("Varias funcionalidades");
        onboardingItem3.setDescription("Diversas funcionalidades, Tabela FIPE, Okm, Revista icarros entre outros");
        onboardingItem3.setImage(R.drawable.on_boarding_3);

        OnboardingItem onboardingItem4 = new OnboardingItem();
        onboardingItem4.setTitle("Utilize sua localização");
        onboardingItem4.setDescription("Busque pela sua cidade, seu estado, seu CEP ou se prefirir utilize a busca por GPS");
        onboardingItem4.setImage(R.drawable.on_boarding_3);

        onboardingItems.add(onboardingItem1);
        onboardingItems.add(onboardingItem2);
        onboardingItems.add(onboardingItem4);

        onBoardingAdapter = new OnBoardingAdapter(onboardingItems);
    }

    private void setupOnboardingIndicators() {

        ImageView[] indicators = new ImageView[onBoardingAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8, 0, 8, 0);
        for(int i = 0; i < indicators.length; i++) {
            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(
                    getApplicationContext(),
                    R.drawable.on_boarding_indicator_inative
            ));
            indicators[i].setLayoutParams(layoutParams);
            layoutOnBoardingIndicators.addView(indicators[i]);
        }

    }

    private void setCurrentOnboardingIndicator(int index){
        int childCount = layoutOnBoardingIndicators.getChildCount();
        for (int i = 0; i < childCount; i++){
            ImageView imageView = (ImageView) layoutOnBoardingIndicators.getChildAt(i);
            if (i == index) {
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(), R.drawable.on_boarding_indicator_active)
                );
            } else {
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(), R.drawable.on_boarding_indicator_inative)
                );
            }
        }
    }

}
