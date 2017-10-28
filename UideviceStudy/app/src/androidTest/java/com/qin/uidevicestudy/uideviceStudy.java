package com.qin.uidevicestudy;

import android.Manifest;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class uideviceStudy {
    private UiDevice mDevice;
    private Instrumentation instrumentation;

    @Before
    public void setUp() {
        instrumentation = InstrumentationRegistry.getInstrumentation();
        mDevice = UiDevice.getInstance(instrumentation);
    }

    @Test
    public void testStudyuidevice() throws IOException, UiObjectNotFoundException {

//获取当前页面层级到输出流
        File file = new File(Environment.getExternalStorageDirectory() + File.separator + "dump.xml");
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
        OutputStream outputStream = new FileOutputStream(file);
        mDevice.dumpWindowHierarchy(outputStream);

//String executeShellCommand(String cmd)执行一个shell命令
//（如打开QQ应用，可直接在手机端打开应用，然后adb shell,dumpsys window即可查看包名和activity）
        mDevice.executeShellCommand("am start -n com.tencent.mobileqq/com.tencent.mobileqq.activity.SplashActivity");

//wait(SearchCondition<R> condition, long timeout): 等待的条件得到满足
        mDevice.wait(Until.findObject(By.desc("电话")),2000);
//findObject(BySelector selector)
        UiObject2 uiObject2=mDevice.findObject(By.desc("电话"));
        uiObject2.click();

// findObject(UiSelector selector)
        UiObject uiObject=mDevice.findObject(new UiSelector().description("电话"));
        uiObject.click();

// List<UiObject2>	findObjects(BySelector selector): 返回所有匹配条件的对象
        List <UiObject2> uiObject21=mDevice.findObjects(By.clazz(TextView.class));
        Bundle bundle=new Bundle();
        for (UiObject2 a:uiObject21){
            bundle.putString("TextView",a.getText());
            Log.e("Tag",a.getText());
        }

//输出到结果报告中（可在adb shell中输入am instrument -w -r -e test 123 -e class com.qin.uidevicestudy.uideviceStudy com.qin.uidevicestudy.test/android.support.test.runner.AndroidJUnitRunner）
//很少用
      instrumentation.sendStatus(123,bundle);




    }
}


