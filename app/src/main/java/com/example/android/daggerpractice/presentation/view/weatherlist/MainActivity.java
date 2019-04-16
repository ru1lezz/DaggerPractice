package com.example.android.daggerpractice.presentation.view.weatherlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.android.daggerpractice.R;
import com.example.android.daggerpractice.presentation.WeatherApplication;
import com.example.android.daggerpractice.injector.component.ApplicationComponent;
import com.example.android.daggerpractice.injector.component.DaggerWeatherComponent;
import com.example.android.daggerpractice.injector.module.ActivityModule;
import com.example.android.daggerpractice.injector.module.SharefPrefModule;
import com.example.android.daggerpractice.injector.module.WeatherModule;
import com.example.android.daggerpractice.presentation.presenter.weatherlist.WeatherListPresenter;
import com.example.android.daggerpractice.presentation.view.model.Weather;
import com.example.android.daggerpractice.presentation.view.weatherlist.recycler.WeatherAdapter;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainView{

    @Inject
    WeatherListPresenter mPresenter;

    private RecyclerView mRecyclerView;
    private WeatherAdapter mAdapter;
    private EditText mCityEditText;
    private Spinner mSpinner;
    private Button mLoadButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initInjector(getApplicationComponent());
        initViews();
        initRecycler();
        initListeners();
        initSpinner();
        mPresenter.setView(this);
        mPresenter.onCreate();
    }

    private void initViews() {
        mRecyclerView = findViewById(R.id.recycler_view);
        mCityEditText = findViewById(R.id.city_edit_text);
        mSpinner = findViewById(R.id.spinner);
        mLoadButton = findViewById(R.id.load_button);
    }

    private void initRecycler() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        mAdapter = new WeatherAdapter();
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initListeners() {
        mLoadButton.setOnClickListener(view -> mPresenter.onLoad());
        mCityEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mPresenter.onCityEditTextChanged(s.toString());
            }
        });
    }

    private void initSpinner() {
        String[] options = getResources().getStringArray(R.array.days_array_options);
        int[] values = getResources().getIntArray(R.array.days_array_values);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, options);
        mSpinner.setAdapter(adapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mPresenter.onSpinnerItemSelected(values[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private ApplicationComponent getApplicationComponent() {
        return ((WeatherApplication) getApplication()).getApplicationComponent();
    }

    private void initInjector(ApplicationComponent applicationComponent) {
        applicationComponent.inject(this);
        DaggerWeatherComponent.builder()
                .applicationComponent(applicationComponent)
                .activityModule(new ActivityModule(this))
                .weatherModule(new WeatherModule())
                .sharefPrefModule(new SharefPrefModule())
                .build()
                .inject(this);
    }

    @Override
    public void displayWeatherList(List<Weather> weatherList) {
        mAdapter.setWeatherList(weatherList);
    }

    @Override
    public void setCity(String city) {
        mCityEditText.setText(city);
    }

    @Override
    public void setDays(int position) {
        mSpinner.setSelection(position);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }
}
