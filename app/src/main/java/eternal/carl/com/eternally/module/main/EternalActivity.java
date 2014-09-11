package eternal.carl.com.eternally.module.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import java.util.ArrayList;

import eternal.carl.com.eternally.BaseActivity;
import eternal.carl.com.eternally.BaseModel;
import eternal.carl.com.eternally.R;
import eternal.carl.com.eternally.item.TimerItem;
import eternal.carl.com.eternally.module.add.NewTimerActivity;


public class EternalActivity extends BaseActivity {

    enum Actions {
        loadAllItems
    }

    enum Keys {
        timerItemList
    }
    private GridView gridView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = (GridView) findViewById(R.id.gridView);
        TimerCardAdapter adapter = new TimerCardAdapter(this);
        gridView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {

            Intent intent = new Intent(this, NewTimerActivity.class);
            this.startActivity(intent);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public BaseModel createModel() {
        return new EternalModel(this);
    }

    @Override
    public void onPostExecuteSuccessful(String action) {
        ArrayList<TimerItem> items = this.getIntent().getParcelableArrayListExtra(Keys.timerItemList.name());
        if (items != null) {
            TimerCardAdapter adapter = (TimerCardAdapter)gridView.getAdapter();

            if (adapter == null) {
                adapter = new TimerCardAdapter(this);
            }

            adapter.setTimerList(items);
        }
    }

    @Override
    public void onPostExecuteFailed(String action) {

    }

    @Override
    public void onResume() {
        super.onResume();

        this.postAsync(Actions.loadAllItems.name());
    }
}
