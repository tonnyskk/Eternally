package eternal.carl.com.eternally;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import eternal.carl.com.eternally.module.TimerCard;
import eternal.carl.com.eternally.module.TimerCardAdapter;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
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
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onResume() {
            super.onResume();

            GridView gridView = (GridView)getActivity().findViewById(R.id.gridView);
            TimerCardAdapter adapter = new TimerCardAdapter(this.getActivity(), getTimerList());
            gridView.setAdapter(adapter);
        }

        private List<TimerCard> getTimerList() {
            List<TimerCard> timerCardList = new ArrayList<TimerCard>();

            for(int i =0 ; i < 20; i ++) {
                TimerCard card = new TimerCard();

                card.setTitle("Card " + i);
                card.setDesc("Desc for " + i);
                card.setIcon(R.drawable.ic_launcher);
                timerCardList.add(card);
            }

            return timerCardList;
        }
    }
}
