package com.android.firebasedemo.view;

import com.android.firebasedemo.model.UserLogin;

/**
 * Created by Vishal on 19-12-2017.
 */

public interface LoginViewInterface {
    void onSuccess(UserLogin userLogin);
    void onFailure(String message);
}
