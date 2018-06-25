package com.sochware.e_agrovet.ui;

import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.sochware.e_agrovet.R;

public class SettingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new SettingFragment()).commit();

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public static class SettingFragment extends PreferenceFragment {
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref);

            ListPreference f = (ListPreference) findPreference("pref_language");
            f.setOnPreferenceChangeListener((preference, o) -> {
                // TODO: 5/28/18 Change language here....... if o.toString() == English bho bhane change language to english other wise nepali
                return true;
            });
        }
    }
}
