package eternal.carl.com.eternally;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by Administrator on 2014/9/5.
 */
public abstract class BaseModel {
    private BaseActivity baseActivity;

    public BaseModel(BaseActivity activity) {
        this.baseActivity = activity;
    }

    public Intent getIntent() {
        return this.baseActivity.getIntent();
    }

    public abstract ResponseStatus doInBackground(String action);
}
