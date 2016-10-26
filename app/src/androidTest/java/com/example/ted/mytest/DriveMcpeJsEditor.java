package com.example.ted.mytest;

import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SdkSuppress;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;
import android.util.Log;

import com.test.utf7ime.helper.Utf7ImeHelper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.action.ViewActions.click;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 18)
public class DriveMcpeJsEditor {

    private static final String TARGET = "com.ccz.jseditor";
    private static final int LAUNCH_TIMEOUT = 5000;
    private UiDevice mDevice;

    private static final String LOG_TAG = ">>>>>>>>>";

    @Before
    public void startMainActivityFromHomeScreen() throws RemoteException, InterruptedException {

        // Initialize
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        // Start from home screen
        if(!mDevice.isScreenOn()){
            mDevice.wakeUp();
            // Unlock screen
            mDevice.swipe(400, 900, 586, 900, 20);
        }
        mDevice.pressHome();

        // Wait for launcher
        final String launcherPackage = mDevice.getLauncherPackageName();
//        Log.i(LOG_TAG, launcherPackage);
        assertThat(launcherPackage, notNullValue());
        mDevice.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), LAUNCH_TIMEOUT);

        // Launch the app
        Context context = InstrumentationRegistry.getContext();
//        Log.i(LOG_TAG, context.toString());
        final Intent intent = context.getPackageManager().getLaunchIntentForPackage(TARGET);

        // Clear out any previous instances
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        Log.i(LOG_TAG, intent.toString());
        context.startActivity(intent);

        // Wait for the app to appear
        mDevice.wait(Until.hasObject(By.pkg(TARGET).depth(0)), LAUNCH_TIMEOUT);
    }

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        Log.i(LOG_TAG, appContext.toString());

        assertEquals("com.example.ted.mytest", appContext.getPackageName());

        UiObject openBtn = mDevice.findObject(new UiSelector()
                .resourceId("com.ccz.jseditor:id/open_btn")
                .className("android.widget.Button"));
        UiObject saveBtn = mDevice.findObject(new UiSelector()
                .resourceId("com.ccz.jseditor:id/save_btn")
                .className("android.widget.Button"));
        UiObject insertBtn = mDevice.findObject(new UiSelector()
                .resourceId("com.ccz.jseditor:id/btn1")
                .className("android.widget.Button"));
        UiObject addFunctionBtn = mDevice.findObject(new UiSelector()
                .resourceId("com.ccz.jseditor:id/anf")
                .className("android.widget.Button"));
        UiObject fontBtn = mDevice.findObject(new UiSelector()
                .resourceId("com.ccz.jseditor:id/ts")
                .className("android.widget.Button"));
        UiObject jumpBtn = mDevice.findObject(new UiSelector()
                .resourceId("com.ccz.jseditor:id/tz")
                .className("android.widget.Button"));
        UiObject tabBtn = mDevice.findObject(new UiSelector()
                .resourceId("com.ccz.jseditor:id/tab")
                .className("android.widget.Button"));
        UiObject jsFunctionBtn = mDevice.findObject(new UiSelector()
                .resourceId("com.ccz.jseditor:id/func_list")
                .className("android.widget.Button"));
        UiObject javaFunctionBtn = mDevice.findObject(new UiSelector()
                .resourceId("com.ccz.jseditor:id/javaf")
                .className("android.widget.Button"));
        UiObject squareIdBtn = mDevice.findObject(new UiSelector()
                .resourceId("com.ccz.jseditor:id/id_list")
                .className("android.widget.Button"));

//        Log.i(LOG_TAG, openBtn.toString());
//        Log.i(LOG_TAG, saveBtn.toString());
//        Log.i(LOG_TAG, insertBtn.toString());
//        Log.i(LOG_TAG, addFunctionBtn.toString());
//        Log.i(LOG_TAG, fontBtn.toString());
//        Log.i(LOG_TAG, jumpBtn.toString());
//        Log.i(LOG_TAG, tabBtn.toString());
//        Log.i(LOG_TAG, jsFunctionBtn.toString());
//        Log.i(LOG_TAG, javaFunctionBtn.toString());
//        Log.i(LOG_TAG, squareIdBtn.toString());

        // openBtn
        if(openBtn.exists() && openBtn.isEnabled()) {
            openBtn.clickAndWaitForNewWindow(LAUNCH_TIMEOUT);

            UiScrollable folderList = new UiScrollable(new UiSelector().
                    resourceId("android:id/select_dialog_listview"));
            UiObject okBtn = mDevice.findObject(new UiSelector().resourceId("android:id/button1"));
            UiObject cancelBtn = mDevice.findObject(new UiSelector().resourceId("android:id/button2"));

            UiObject chosenFolder = folderList.getChildByText(
                    new UiSelector().className("android.widget.TextView"), "￼youku");
            chosenFolder.clickAndWaitForNewWindow(LAUNCH_TIMEOUT);

            UiScrollable folderList2 = new UiScrollable(new UiSelector().
                    resourceId("android:id/select_dialog_listview"));
            UiObject okBtn2 = mDevice.findObject(new UiSelector().resourceId("android:id/button1"));
            UiObject cancelBtn2 = mDevice.findObject(new UiSelector().resourceId("android:id/button2"));

            cancelBtn2.click();
        }

        // addFunctionBtn
        assertTrue(LOG_TAG, addFunctionBtn.exists() && addFunctionBtn.isEnabled());
        addFunctionBtn.clickAndWaitForNewWindow(LAUNCH_TIMEOUT);

//        UiScrollable insertList = new UiScrollable(new UiSelector()
//                .resourceId("android:id/select_dialog_listview"));
//        UiObject chosenInsert = insertList.getChildByInstance(
//                new UiSelector().className("android.widget.TextView"), 4);
//        chosenInsert.click();
        mDevice.findObject(new UiSelector()
                .className("android.widget.TextView")
                .text("钩子函数"))
                .clickAndWaitForNewWindow(LAUNCH_TIMEOUT);

//        UiScrollable addEvent = new UiScrollable(new UiSelector()
//                .resourceId("android:id/select_dialog_listview"));
//        UiObject chosenEvent = addEvent.getChildByInstance(
//                new UiSelector().className("android.widget.TextView"), 5);
//        chosenEvent.click();
        mDevice.findObject(new UiSelector()
                .className("android.widget.TextView")
                .text("生物死亡"))
                .clickAndWaitForNewWindow(LAUNCH_TIMEOUT);

        UiObject textBox = mDevice.findObject(new UiSelector()
                .className("android.widget.EditText")
                .resourceId("com.ccz.jseditor:id/edt1"));
        String text = textBox.getText();
//        char[] textChar = text.toCharArray();
//        Log.i(LOG_TAG, String.valueOf(textChar.length));
//        for (int i = 0; i < textChar.length; i++) {
//            Log.i(LOG_TAG, String.format(("%d %c"), i, textChar[i]));
//        }
//        Log.i(LOG_TAG, text);
//        Log.i(LOG_TAG, String.valueOf(text.length()));

        // Recompose text
        text = text.replace("\n}", "\tFakedFunc(a,b,c);\n}");
//        Log.i(LOG_TAG, text);
//        Log.i(LOG_TAG, String.valueOf(text.length()));

//        Utf7ImeHelper.e(text);
//
//        textBox.clearTextField();
//        for (int i = 0; i < text.length(); i++){
//            Log.i(LOG_TAG, String.format("%d %d %c", i, (int)text.charAt(i), text.charAt(i)));
//            mDevice.pressKeyCode((int)text.charAt(i));
//        }
//        mDevice.openQuickSettings();
//        textBox.setText("Chinese New Year");

        // Export recomposed text
        textBox.setText(Utf7ImeHelper.e(text));
    }
}
