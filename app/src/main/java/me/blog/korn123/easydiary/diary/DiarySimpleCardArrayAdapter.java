package me.blog.korn123.easydiary.diary;

import android.app.Activity;
import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import me.blog.korn123.commons.constants.Constants;
import me.blog.korn123.commons.utils.CommonUtils;
import me.blog.korn123.commons.utils.DateUtils;
import me.blog.korn123.commons.utils.FontUtils;
import me.blog.korn123.easydiary.R;

/**
 * Created by CHO HANJOONG on 2017-03-16.
 */

public class DiarySimpleCardArrayAdapter extends ArrayAdapter<DiaryDto> {
    private final Context context;
    private final List<DiaryDto> list;
    private final int layoutResourceId;

    public DiarySimpleCardArrayAdapter(Context context, int layoutResourceId, List<DiaryDto> list) {
        super(context, layoutResourceId, list);
        this.context = context;
        this.list = list;
        this.layoutResourceId = layoutResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = null;
        if (row == null) {
            LayoutInflater inflater = ((Activity)this.context).getLayoutInflater();
            row = inflater.inflate(this.layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.textView1 = ((TextView)row.findViewById(R.id.text1));
            holder.imageView = ((ImageView) row.findViewById(R.id.weather));
            initFontStyle(holder);
            row.setTag(holder);
        } else {
            holder = (ViewHolder)row.getTag();
        }

        float fontSize = CommonUtils.loadFloatPreference(context, "font_size", 0);
        if (fontSize > 0) {
            holder.textView1.setTextSize(TypedValue.COMPLEX_UNIT_PX, fontSize);
        }

        DiaryDto diaryDto = (DiaryDto)this.list.get(position);
        holder.textView1.setText(diaryDto.getTitle());
        holder.imageView.setVisibility(View.VISIBLE);
        switch (diaryDto.getWeather()) {
            case 0:
                holder.imageView.setImageResource(0);
                holder.imageView.setVisibility(View.GONE);
                break;
            case Constants.SUN:
                holder.imageView.setImageResource(R.drawable.ic_sun);
                break;
            case Constants.SUN_AND_CLOUD:
                holder.imageView.setImageResource(R.drawable.ic_cloud);
                break;
            case Constants.RAIN:
                holder.imageView.setImageResource(R.drawable.ic_rain);
                break;
            case Constants.THUNDER_BOLT:
                holder.imageView.setImageResource(R.drawable.ic_storm);
                break;
            case Constants.SNOW:
                holder.imageView.setImageResource(R.drawable.ic_snow_2);
                break;
        }

        return row;
    }

    private void initFontStyle(ViewHolder holder) {
        FontUtils.setTypeface(context.getAssets(), holder.textView1);
    }

    private static class ViewHolder {
        TextView textView1;
        ImageView imageView;
    }
}
