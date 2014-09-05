package eternal.carl.com.eternally;

import android.app.Activity;
import android.os.Looper;

/**
 * Created by Administrator on 2014/9/5.
 */
public abstract class BaseActivity extends Activity {
    protected BaseModel model;
    private BaseActivityHelper helper;

    public BaseActivity() {
        helper = new BaseActivityHelper(this);
        model = createModel();
    }

    public BaseModel getModel() {
        return this.model;
    }

    public abstract BaseModel createModel();
    public abstract void onPostExecuteSuccessful(String action);
    public abstract void onPostExecuteFailed(String action);


    public final void postAsync(final String action) {
        final BaseModel model = this.getModel();
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            this.helper.postAsyncTask(model, action);
        } else {
            this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    helper.postAsyncTask(model, action);
                }
            });
        }
    }

    public final void cancelAsync(final String action) {
        this.helper.cancelAsync(action);
    }

    public boolean showErrorMessage() {
        return true;
    }
}


