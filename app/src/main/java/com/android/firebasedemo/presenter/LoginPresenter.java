package com.android.firebasedemo.presenter;

import android.support.annotation.NonNull;

import com.android.firebasedemo.dagger.Injector;
import com.android.firebasedemo.model.UserLogin;
import com.android.firebasedemo.view.LoginViewInterface;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;

/**
 * Created by Vishal on 19-12-2017.
 */

public class LoginPresenter {
    @Inject
    FirebaseAuth firebaseAuth;
    LoginViewInterface viewInterface;
    public LoginPresenter(LoginViewInterface viewInterface){
        this.viewInterface = viewInterface;
        Injector.getApiComponent().inject(LoginPresenter.this);
    }

    public void createAccount(final UserLogin userLogin) {
        firebaseAuth.createUserWithEmailAndPassword(userLogin.getUsername(), userLogin.getPassword())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            viewInterface.onSuccess(userLogin);
                        } else {
                            viewInterface.onFailure(task.getException().getMessage());
                        }
                    }
                });
    }

    public void login(final UserLogin userLogin) {
        firebaseAuth.signInWithEmailAndPassword(userLogin.getUsername(), userLogin.getPassword())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            viewInterface.onSuccess(userLogin);
                        } else {
                            viewInterface.onFailure(task.getException().getMessage());
                        }
                    }
                });
    }
}
