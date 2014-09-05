package eternal.carl.com.eternally.item;

import android.app.Application;

/**
 * Created by Administrator on 2014/9/6.
 */
public class EternalAppInfo {
    private Application appContext;

    public EternalAppInfo() {
    }

    public Application getAppContext() {
        return appContext;
    }

    public void setAppContext(Application appContext) {
        this.appContext = appContext;
    }
}
