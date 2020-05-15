package com.uia.example.my;
 
import android.widget.ListView;
import android.widget.Switch;
 
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
 
public class LaunchSettings extends UiAutomatorTestCase {
 
    // TODO 
    public void testDemo() throws UiObjectNotFoundException {
 
        // 模拟 HOME 键点击事件
        getUiDevice().pressHome();
 
        UiObject allAppsButton = new UiObject(new UiSelector().description("Apps"));
 
        // 模拟点击所有应用按钮，并等待所有应用界面起来
        allAppsButton.clickAndWaitForNewWindow();
 
        // 找到 Apps tab 按钮
        UiObject appsTab = new UiObject(new UiSelector().text("Apps"));
 
        // 模拟点击 Apps tab.
        appsTab.click();
 
        UiScrollable appViews = new UiScrollable(new UiSelector().scrollable(true));
 
        // 设置滚动模式为水平滚动(默认为垂直滚动)
        appViews.setAsHorizontalList();
 
        if (allAppsButton.exists() && allAppsButton.isEnabled()) {
            allAppsButton.click();
        }
        // 查找时钟应用并点击
        UiObject settingsApp = appViews.getChildByText(
                new UiSelector().className(android.widget.TextView.class.getName()), "Clock");
        settingsApp.clickAndWaitForNewWindow();
 
        // 验证当前显示 的应用包名为时钟
 
        UiObject settingsValidation = new UiObject(new UiSelector().packageName("com.google.android.deskclock"));
        // 如果不存在则出错提示
        assertTrue("Unable to detect Clock", settingsValidation.exists());
 
        // 模拟点击时间tab
        UiObject clock = new UiObject(new UiSelector().description("Clock"));
        clock.clickAndWaitForNewWindow();
        // 模拟点击下方的闹钟图标
        UiObject alarms = new UiObject(new UiSelector().description("Alarms"));
        alarms.clickAndWaitForNewWindow();
 
        UiScrollable list = new UiScrollable(new UiSelector().className(ListView.class.getName()));
        if (list.getChildCount() > 0) {
            UiObject listIndex0 = list.getChild(new UiSelector().index(0));
            UiObject switchBtn = listIndex0.getChild(new UiSelector().className(Switch.class.getName()));
 
            boolean isChecked = switchBtn.isChecked();
 
            switchBtn.click();
        }
        // 模拟点击返回键
        getUiDevice().pressBack();
 
        UiObject timer = new UiObject(new UiSelector().description("Timer"));
        timer.clickAndWaitForNewWindow();
 
    }
}