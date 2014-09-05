package eternal.carl.com.eternally.app;

import eternal.carl.com.eternally.item.EternalAppInfo;

/**
 * Created by Administrator on 2014/9/6.
 */
public class EternalContextHelper {
    private static EternalContextHelper instance = new EternalContextHelper();

    private EternalAppInfo appInfo = null;

    private EternalContextHelper() {

    }

    public  EternalAppInfo getAppInfo() {
        if (appInfo == null) {
            appInfo = new EternalAppInfo();
        }
        return appInfo;
    }

    public void setAppInfo(EternalAppInfo appInfo) {
        this.appInfo = appInfo;
    }

    public static EternalContextHelper getInstance() {
        return instance;
    }
}
