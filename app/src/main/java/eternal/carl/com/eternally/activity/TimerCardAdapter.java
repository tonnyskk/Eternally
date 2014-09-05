package eternal.carl.com.eternally.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import eternal.carl.com.eternally.R;
import eternal.carl.com.eternally.item.TimerItem;

/**
 * Created by Administrator on 2014/9/3.
 */
public class TimerCardAdapter extends BaseAdapter {
    private List<TimerItem> data = new ArrayList<TimerItem>();
    private Context context;
    private LayoutInflater mInflater;

    public TimerCardAdapter(Context context) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
    }

    public void setTimerList(ArrayList<TimerItem> data) {
        this.data.clear();
        for (TimerItem timerItem : data) {
            timerItem.setIcon(R.drawable.ic_launcher);
            this.data.add(timerItem);
        }
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = mInflater.inflate(R.layout.card_item, null);

            holder = new ViewHolder();
            holder.titleView = (TextView) view.findViewById(R.id.cardItem0Title);
            holder.descView = (TextView) view.findViewById(R.id.cardItem0Title);
            holder.iconView = (ImageView) view.findViewById(R.id.cardItem0Image);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        TimerItem card = data.get(i);
        holder.descView.setText(card.getDesc());
        holder.titleView.setText(card.getTitle());
        holder.iconView.setImageResource(card.getIcon());

        return view;
    }

    class ViewHolder {
        TextView titleView;
        TextView descView;
        ImageView iconView;
    }
}
