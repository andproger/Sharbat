package com.example.sharbat.presentation.features.details;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.browser.customtabs.CustomTabsIntent;

import com.example.sharbat.R;

import java.util.ArrayList;
import java.util.List;

import static androidx.browser.customtabs.CustomTabsService.ACTION_CUSTOM_TABS_CONNECTION;

public class WebUtil {
    public static void launchCustomTab(final Context context, final Uri url) {
        final CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        final CustomTabsIntent customTabsIntent = builder.build();
        builder.setToolbarColor(context.getResources().getColor(R.color.colorPrimary));
        builder.enableUrlBarHiding();
        builder.setShowTitle(true);
        customTabsIntent.launchUrl(context, url);
    }

    public static ArrayList getCustomTabsPackages(@NonNull final Context context) {
        final PackageManager pm = context.getPackageManager();
        // Get default VIEW intent handler.
        final Intent activityIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.example.com"));

        final List<ResolveInfo> resolvedActivityList = pm.queryIntentActivities(activityIntent, 0);
        final ArrayList packagesSupportingCustomTabs = new ArrayList<>();
        for (ResolveInfo info : resolvedActivityList) {
            final Intent serviceIntent = new Intent();
            serviceIntent.setAction(ACTION_CUSTOM_TABS_CONNECTION);
            serviceIntent.setPackage(info.activityInfo.packageName);
            // Check if this package also resolves the Custom Tabs service.
            if (pm.resolveService(serviceIntent, 0) != null) {
                packagesSupportingCustomTabs.add(info);
            }
        }

        return packagesSupportingCustomTabs;
    }
}
