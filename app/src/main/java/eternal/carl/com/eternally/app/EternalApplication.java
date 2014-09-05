package eternal.carl.com.eternally.app;

import android.app.Application;

/**
 * Created by Administrator on 2014/9/6.
 */
public class EternalApplication extends Application {
    public final void onCreate() {
        EternalContextHelper.getInstance().getAppInfo().setAppContext(this);
    }
}
