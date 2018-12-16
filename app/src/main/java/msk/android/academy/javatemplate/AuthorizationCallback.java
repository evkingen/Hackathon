package msk.android.academy.javatemplate;

import android.support.v4.app.Fragment;
import android.view.View;

interface AuthorizationCallback {
    void onChangeVisibility(int mode);

    void onReplace(Fragment fragment);
}
