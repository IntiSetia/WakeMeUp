package id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.R;
import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.model.Alarm;
import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.tambahActivity;

/**
 * Created by euiko on 12/4/16.
 */

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.ViewHolder> {

    ArrayList<Alarm> alarmList;
    IalarmAdapter mIalarmAdapter;
    boolean isReload = false;

    public AlarmAdapter(Context context, ArrayList<Alarm> alarmList) {
        this.mIalarmAdapter = (IalarmAdapter) context;
        this.alarmList = alarmList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_alarm, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        isReload = true;
        if (isReload) {
            Alarm alarm = alarmList.get(position);
            holder.tvJam.setText(alarm.hours);
            holder.tvLabel.setText(alarm.memo);
            holder.tvHari.setText(Html.fromHtml(tambahActivity.getHTMLDays(alarm.days)));
            holder.isEnable.setChecked(alarm.isEnabled);
        }
        isReload = false;
    }

    @Override
    public int getItemCount() {
        if (alarmList != null) return alarmList.size();
        return 0;
    }

    public interface IalarmAdapter {
        void doClick(int pos);
        void changeSwitch(int pos, boolean state);

        void buttonMore(int pos, View view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvJam, tvHari, tvLabel;
        Switch isEnable;
        ImageButton ibMore;
        RelativeLayout rlListAlarm;

        public ViewHolder(View itemView) {
            super(itemView);
            tvJam = (TextView) itemView.findViewById(R.id.textViewTime);
            tvHari = (TextView) itemView.findViewById(R.id.textViewDay);
            tvLabel = (TextView) itemView.findViewById(R.id.textViewLabel);
            isEnable = (Switch) itemView.findViewById(R.id.switchEnable);
            ibMore = (ImageButton) itemView.findViewById(R.id.imageButtonMore);
            rlListAlarm = (RelativeLayout) itemView.findViewById(R.id.listAlarm);

            ibMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mIalarmAdapter.buttonMore(getAdapterPosition(), v);
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mIalarmAdapter.doClick(getAdapterPosition());
                }
            });

            isEnable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (!isReload) {
                        mIalarmAdapter.changeSwitch(getAdapterPosition(), isChecked);
                    }
                }
            });
        }
    }
}
