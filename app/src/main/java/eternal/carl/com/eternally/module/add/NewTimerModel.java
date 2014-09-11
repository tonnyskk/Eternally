package eternal.carl.com.eternally.module.add;

import java.util.ArrayList;

import eternal.carl.com.eternally.BaseActivity;
import eternal.carl.com.eternally.BaseModel;
import eternal.carl.com.eternally.R;
import eternal.carl.com.eternally.ResponseStatus;
import eternal.carl.com.eternally.db.TimerDao;
import eternal.carl.com.eternally.item.TimerItem;
import eternal.carl.com.eternally.module.main.EternalActivity;

/**
 * Created by Administrator on 2014/9/11.
 */
public class NewTimerModel extends BaseModel {
    public NewTimerModel(BaseActivity activity) {
        super(activity);
    }

    @Override
    public ResponseStatus doInBackground(String action) {
        ResponseStatus status = new ResponseStatus();
        try {
            if (action != null) {
                switch (NewTimerActivity.Actions.valueOf(action)) {
                    case SaveTimer:

                        TimerItem item = this.getIntent().getParcelableExtra(NewTimerActivity.Keys.TimerItem.name());

                        if (item != null) {
                            TimerDao.getInstance().saveItem(item);
                        } else {
                            status.setErrorMessage(getActivity().getResources().getString(R.string.error_msg_unknown_item)  );
                        }
                        break;
                }
            }
        } catch (Exception e) {
            status.setErrorMessage(e.getMessage());
        }
        return status;
    }
}
