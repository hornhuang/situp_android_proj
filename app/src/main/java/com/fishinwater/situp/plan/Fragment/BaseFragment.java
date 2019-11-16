package com.fishinwater.situp.plan.Fragment;

import android.util.Log;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

/**
 * @author fishinwater-1999
 * @version 2019-11-16
 */
public class BaseFragment extends Fragment {

    public void toast(String content){
        Toast.makeText(getActivity(), content, Toast.LENGTH_SHORT).show();
    }

    public void log(String TAG, String content){
        Log.d(TAG, content);
    }

}

