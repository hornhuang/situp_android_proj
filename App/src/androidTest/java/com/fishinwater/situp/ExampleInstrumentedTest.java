package com.fishinwater.situp;

import android.content.Context;
import android.util.Log;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import com.fishinwater.situp.util.PropertiesUtil;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.InputStream;
import java.util.Properties;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    private final String TAG = "ExampleInstrumentedTest";

    private Context instrumentationContext;

    @Before
    public void init(){
        instrumentationContext = InstrumentationRegistry.getInstrumentation().getContext();
    }

    /**
     *  无法获得 asserts 目录下文件
     *  但 app 中可以获得
     */
    @Test
    public void testProperties() {
        Log.d(TAG, "-------" + PropertiesUtil.loadAssetsProperties(instrumentationContext, com.fishinwater.situp.Properties.CLASS).getProperty("versionName")+"  "+
        PropertiesUtil.loadAssetsProperties(instrumentationContext, "apiurl").getProperty("memberApp")+"  "+
        PropertiesUtil.loadAssetsProperties(instrumentationContext, "apiurl").getProperty("designApp")+ "-------");
    }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("com.fishinwater.situp", appContext.getPackageName());
    }
}
