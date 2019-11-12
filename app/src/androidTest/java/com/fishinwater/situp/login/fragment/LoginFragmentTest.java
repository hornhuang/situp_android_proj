package com.fishinwater.situp.login.fragment;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author fishinwater-1999
 * @version 2019-11-12
 */
public class LoginFragmentTest {

    private LoginFragment loginFragment;

    @Before
    public void ini() {
        loginFragment = new LoginFragment();
    }

    @Test
    public void login() {
        loginFragment.login("test", "123");
    }

}
