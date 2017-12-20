package com.android.firebasedemo.dagger;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Vishal on 19-12-2017.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomScope {
}
