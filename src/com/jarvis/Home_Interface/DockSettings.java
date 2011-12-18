/***
** Copyright 2006, The Android Open Source Project
**
** Licensed under the Apache License, Version 2.0 (the "License"); 
** you may not use this file except in compliance with the License. 
** You may obtain a copy of the License at 
**
**     http://www.apache.org/licenses/LICENSE-2.0 
**
** Unless required by applicable law or agreed to in writing, software 
** distributed under the License is distributed on an "AS IS" BASIS, 
** WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
** See the License for the specific language governing permissions and 
** limitations under the License.
*/
package com.jarvis.HUD;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.IPackageDataObserver;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceScreen;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceManager;		
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList

public class DockSettings extends PreferenceActivity
	implements SharedPreferences.OnSharedPreferenceChangeListener {
	
	private static final TAG = "DockSettings";

	private ArrayList<CharSequence> packageNames = new ArrayList<CharSequence>();
	private ArrayList<CharSequence> appNames = new ArrayList<CharSequence>();
	private static ArrayList<CharSequence> selectedApps = new ArrayList<CharSequence>();
	
	//The five shortcuts- shows a list of all apps per each shortcut
    private ListPreference shortcutOne;
	private ListPreference shortcutTwo;
	private ListPreference shortcutThree;
	private ListPreference shortcutFour;
	private ListPreference shortcutFive;

	@Override
	public void onCreate(Bundle savedInstanceState) {	
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.layout.dock_settings);
		setPreferences();
	}

	public void setPreferences() {
		PreferenceScreen dockPrefs = getPreferenceScreen();

		//Setting up the ListPreferences for selecting shortcuts
		for(PackageInfo pi : PackageManager.getInstalledPackages(0)) {
			packageNames.add(pi.packageName);
			appNames.add(PackageManager.getApplicationLabel(pi.applicationInfo));
		}

        //Setup the shortcut lists
		shortcutOne = (ListPreference)findPreference("dock_appone");
		shortcutOne.setEntries(appNames.toArray());
		shortcutOne.setEntryValues(packageNames.toArray());

		shortcutTwo = (ListPreference)findPreference("dock_apptwo");
		shortcutTwo.setEntries(appNames.toArray());
		shortcutshortcutTwoOne.setEntryValues(packageNames.toArray());

		shortcutThree = (ListPreference)findPreference("dock_appthree");
		shortcutThree.setEntries(appNames.toArray());
		shortcutThree.setEntryValues(packageNames.toArray());

		shortcutFour = (ListPreference)findPreference("dock_appfour");
		shortcutFour.setEntries(appNames.toArray());
		shortcutFour.setEntryValues(packageNames.toArray());

		shortcutFive = (ListPreference)findPreference("dock_appfive");
		shortcutFive.setEntries(appNames.toArray());
		shortcutFive.setEntryValues(packageNames.toArray());
	}

	public void onPreferenceChanged(Preference preference, Object objValue) {
        //Clear the current list of selected apps and add each new one
		selectedApps.clear();
		selectedApps.add(shortcutOne.getValue());
		selectedApps.add(shortcutTwo.getValue());
		selectedApps.add(shortcutThree.getValue());
		selectedApps.add(shortcutFour.getValue());
		selectedApps.add(shortcutFive.getValue());
	}

	//Returns the list of selected apps from each ListPreference
	public static ArrayList<CharSequence> getSelectedApps() {
		return selectedApps;
	}
}
