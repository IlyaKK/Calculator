package com.elijahcorp.calculator;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.material.switchmaterial.SwitchMaterial;

public class SettingsActivity extends AppCompatActivity {
    private SwitchMaterial changeThemeSwitch;

    public static void lunchSettingActivity(Context context) {
        Intent intent = new Intent(context, SettingsActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.getDefaultNightMode());
        setContentView(R.layout.activity_settings);
        initViews();
        initialiseTheme();
        initialiseChangeSwitchListener();
    }

    private void initViews() {
        changeThemeSwitch = findViewById(R.id.change_theme_switch);
    }

    private void initialiseTheme() {
        changeThemeSwitch.setChecked(ChangerTheme.initialiseTheme(this));
    }

    private void initialiseChangeSwitchListener() {
        changeThemeSwitch.setOnCheckedChangeListener((l, c) -> {
            if (c) {
                ChangerTheme.setTheme(AppCompatDelegate.MODE_NIGHT_YES, ChangerTheme.THEME_DARK);
            } else {
                ChangerTheme.setTheme(AppCompatDelegate.MODE_NIGHT_NO, ChangerTheme.THEME_LIGHT);
            }
        });
    }
}