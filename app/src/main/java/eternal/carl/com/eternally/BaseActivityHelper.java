package eternal.carl.com.eternally;

import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.widget.Toast;

import java.util.HashMap;

/**
 * Created by Administrator on 2014/9/5.
 */
public class BaseActivityHelper {
    private BaseActivity baseActivity;
    private HashMap<String, Loader> asyncTaskHashMap = new HashMap<String, Loader>();

    public BaseActivityHelper(BaseActivity activity) {
        this.baseActivity = activity;
    }

    public BaseActivity getBaseActivity() {
        return this.baseActivity;
    }

    public void postAsyncTask(final BaseModel model, final String action) {
        Loader loader = new AsyncTaskLoader<ResponseStatus>(getBaseActivity()) {
            @Override
            public ResponseStatus loadInBackground() {
                if (model != null) {
                    ResponseStatus responseStatus = model.doInBackground(action);

                    return responseStatus;
                }
                return null;
            }

            @Override
            public void deliverResult(ResponseStatus result) {
                onPostExecute(action, result);
                asyncTaskHashMap.remove(action);
            }

            @Override
            public void onCanceled(ResponseStatus result) {
                asyncTaskHashMap.remove(action);
            }
        };
        asyncTaskHashMap.put(action, loader);
        loader.forceLoad();
    }

    public void cancelAsync(String action) {
        if (asyncTaskHashMap.containsKey(action)) {
            Loader loader = asyncTaskHashMap.get(action);
            if (loader != null) {
                ((AsyncTaskLoader) loader).cancelLoad();
            }
            asyncTaskHashMap.remove(action);
        }
    }

    boolean isAsyncCancelled(String action) {
        return !asyncTaskHashMap.containsKey(action);
    }

    public void onPostExecute(String action, ResponseStatus responseStatus) {
        if (responseStatus != null) {
            if (responseStatus.getErrorMessage() != null) {
                if (baseActivity.showErrorMessage()) {
                    Toast.makeText(baseActivity, responseStatus.getErrorMessage(), Toast.LENGTH_SHORT).show();
                }
                baseActivity.onPostExecuteFailed(action);
            } else {
                baseActivity.onPostExecuteSuccessful(action);
            }
        } else {
            baseActivity.onPostExecuteSuccessful(action);
        }
    }
}
