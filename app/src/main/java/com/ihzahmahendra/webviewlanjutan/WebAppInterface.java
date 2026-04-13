package com.ihzahmahendra.webviewlanjutan;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class WebAppInterface {
    Context mContext;

    WebAppInterface(Context c) {
        mContext = c;
    }

    @JavascriptInterface
    public void showToast(String toast) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
    }

    @JavascriptInterface
    public void showSms() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_APP_MESSAGING);
        mContext.startActivity(intent);
    }

    @JavascriptInterface
    public void showWhatsApp(String target, String message) {
        String url = "https://api.whatsapp.com/send?phone=" + target + "&text=" + message;
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        mContext.startActivity(i);
    }

    @JavascriptInterface
    public void showGoogleLens() {
        Intent lensIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("googleapp://lens"));
        lensIntent.setPackage("com.google.android.googlequicksearchbox");

        try {
            mContext.startActivity(lensIntent);
        } catch (Exception e) {
            try {
                Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://lens.google.com/"));
                mContext.startActivity(webIntent);
            } catch (Exception ex) {
                Intent playStoreIntent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=com.google.android.googlequicksearchbox")
                );
                mContext.startActivity(playStoreIntent);
            }
        }
    }
}
