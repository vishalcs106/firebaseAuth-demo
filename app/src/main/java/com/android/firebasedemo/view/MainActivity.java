package com.android.firebasedemo.view;



import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.design.widget.TextInputEditText;
import android.os.Bundle;


import com.android.firebasedemo.Constants;
import com.android.firebasedemo.R;
import com.android.firebasedemo.databinding.ActivityMainBinding;
import com.android.firebasedemo.model.UserLogin;
import com.android.firebasedemo.presenter.LoginPresenter;



import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.paperdb.Paper;

public class MainActivity extends BaseActivity implements LoginViewInterface{
    @BindView(R.id.username_editText)
    TextInputEditText usernameEditText;
    @BindView(R.id.password_editText)
    TextInputEditText passwordEditText;
    LoginPresenter loginPresenter;
    ActivityMainBinding binding;
    UserLogin userLogin = new UserLogin();
    static final int LOCATION_PERMISSION_REQUEST_CODE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setUserLogin(userLogin);
        ButterKnife.bind(this);
        loginPresenter = new LoginPresenter(MainActivity.this);
        Paper.init(this);
        if(Paper.book().contains(Constants.KEY_USER)){
            getToNextActivity();
        }

    }

    @OnClick(R.id.button_create)
    public void createAccount() {
       loginPresenter.createAccount(userLogin);
    }

    @OnClick(R.id.button_login)
    public void login() {
        loginPresenter.login(userLogin);
    }

    @Override
    public void onSuccess(UserLogin userLogin) {
       showToast("Success");
       Paper.book().write(Constants.KEY_USER, userLogin);
        getToNextActivity();
    }

    private void getToNextActivity() {
        Intent intent = new Intent(this, PlacesActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onFailure(String message) {
        showToast("Failed");
    }
}
