package eternal.carl.com.eternally.module.main;

import java.util.ArrayList;

import eternal.carl.com.eternally.BaseActivity;
import eternal.carl.com.eternally.BaseModel;
import eternal.carl.com.eternally.ResponseStatus;
import eternal.carl.com.eternally.db.TimerDao;
import eternal.carl.com.eternally.item.TimerItem;

/**
 * Created by Administrator on 2014/9/5.
 */
public class EternalModel extends BaseModel {

    public EternalModel(BaseActivity activity) {
        super(activity);
    }

    @Override
    public ResponseStatus doInBackground(String action) {
        ResponseStatus status = new ResponseStatus();
        try {
            if (action != null) {
                switch (EternalActivity.Actions.valueOf(action)) {
                    case loadAllItems:
                        ArrayList<TimerItem> itemList = TimerDao.getInstance().getItemList();
                        this.getIntent().putParcelableArrayListExtra(EternalActivity.Keys.timerItemList.name(), itemList);
                        break;
                }
            }
        } catch (Exception e) {
            status.setErrorMessage(e.getMessage());
        }
        return status;
    }
}
