package com.jsyrdb.yiren.widget.wheelview;

import android.view.View;


import com.jsyrdb.yiren.R;

import java.util.Arrays;
import java.util.List;

public class WheelMain {

	private View view;
	private WheelView wv_year;
	private WheelView wv_month;
	private WheelView wv_day;
	private WheelView wv_hours;
	private WheelView wv_mins;
	public int screenheight;
	private boolean hasSelectTime;
	private static int START_YEAR = 2000, END_YEAR = 2050;
    private boolean showDay = true;
	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}

	public  int getSTART_YEAR() {
		return START_YEAR;
	}

	public  void setSTART_YEAR(int sTART_YEAR) {
		START_YEAR = sTART_YEAR;
	}

	public  int getEND_YEAR() {
		return END_YEAR;
	}

	public  void setEND_YEAR(int eND_YEAR) {
		END_YEAR = eND_YEAR;
	}

	public WheelMain(View view) {
		super();
		this.view = view;
		hasSelectTime = false;
		setView(view);
	}

	public WheelMain(View view, boolean hasSelectTime) {
		super();
		this.view = view;
		this.hasSelectTime = hasSelectTime;
		setView(view);
	}
	
	public void initDateTimePickerNoDay(int year, int month) {
		showDay = false;
		this.initDateTimePicker(year, month, 0, 0, 0);
	}

	public void initDateTimePicker(int year, int month, int day) {
		this.initDateTimePicker(year, month, day, 0, 0);
	}
	
	public void initDateTimePicker(int hour, int minute) {
		this.initDateTimePicker(0, 0, 0, hour, minute);
	}

	public void initDateTimePicker(int year, int month, int day,int hour) {
		this.initDateTimePicker(year, month, day, hour, 0);
	}

	/**
	 * @Description: TODO 弹出日期时间选择�?
	 */
	public void initDateTimePicker(int year, int month, int day, int h, int m) {
		// int year = calendar.get(Calendar.YEAR);
		// int month = calendar.get(Calendar.MONTH);
		// int day = calendar.get(Calendar.DATE);
		// 添加大小月月份并将其转换为list,方便之后的判�?
		String[] months_big = { "1", "3", "5", "7", "8", "10", "12" };
		String[] months_little = { "4", "6", "9", "11" };

		final List<String> list_big = Arrays.asList(months_big);
		final List<String> list_little = Arrays.asList(months_little);

		// 年
		wv_year = (WheelView) view.findViewById(R.id.year);
		wv_year.setAdapter(new NumericWheelAdapter(START_YEAR, END_YEAR));// 设置"�?"的显示数�?
		wv_year.setCyclic(true);// 可循环滚�?
		wv_year.setLabel("年");// 添加文字
		wv_year.setCurrentItem(year - START_YEAR);// 初始化时显示的数�?

		// 月
		wv_month = (WheelView) view.findViewById(R.id.month);
		wv_month.setAdapter(new NumericWheelAdapter(1, 12));
		wv_month.setCyclic(true);
		wv_month.setLabel("月");
		wv_month.setCurrentItem(month);

		// 日
		wv_day = (WheelView) view.findViewById(R.id.day);
		wv_day.setCyclic(true);
		// 判断大小月及是否闰年,用来确定"�?"的数�?
		if (list_big.contains(String.valueOf(month + 1))) {
			wv_day.setAdapter(new NumericWheelAdapter(1, 31));
		} else if (list_little.contains(String.valueOf(month + 1))) {
			wv_day.setAdapter(new NumericWheelAdapter(1, 30));
		} else {
			// 闰年
			if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)
				wv_day.setAdapter(new NumericWheelAdapter(1, 29));
			else
				wv_day.setAdapter(new NumericWheelAdapter(1, 28));
		}
		
		wv_day.setLabel("日");
		wv_day.setCurrentItem(day - 1);

		wv_hours = (WheelView) view.findViewById(R.id.hour);
		wv_mins = (WheelView) view.findViewById(R.id.min);
		
		if (hasSelectTime) {
			wv_hours.setVisibility(View.VISIBLE);
			wv_mins.setVisibility(View.VISIBLE);
			wv_year.setVisibility(View.VISIBLE);
			wv_month.setVisibility(View.VISIBLE);
			wv_day.setVisibility(View.VISIBLE);
            
			wv_hours.setAdapter(new NumericWheelAdapter(0, 23));
			wv_hours.setCyclic(true);// 可循环滚�?
			wv_hours.setLabel("时");// 添加文字
			wv_hours.setCurrentItem(h);
			//if(m!=0) {
				wv_mins.setAdapter(new NumericWheelAdapter(00, 59));
				wv_mins.setCyclic(true);// 可循环滚�?
				wv_mins.setLabel("分");// 添加文字
				wv_mins.setCurrentItem(m);
				wv_mins.setVisibility(View.VISIBLE);
//			}else{
//				wv_mins.setVisibility(View.GONE);
//			}
		} else {
			wv_year.setVisibility(View.VISIBLE);
			wv_month.setVisibility(View.VISIBLE);
			wv_day.setVisibility(View.VISIBLE);
			wv_hours.setVisibility(View.GONE);
			wv_mins.setVisibility(View.GONE);
		}
		
		if(day==0){
			wv_year.setVisibility(View.VISIBLE);
			wv_month.setVisibility(View.VISIBLE);
			wv_day.setVisibility(View.GONE);
			wv_hours.setVisibility(View.GONE);
			wv_mins.setVisibility(View.GONE);
		}

		// 添加"�?"监听
		OnWheelChangedListener wheelListener_year = new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				int year_num = newValue + START_YEAR;
				// 判断大小月及是否闰年,用来确定"�?"的数�?
				if (list_big
						.contains(String.valueOf(wv_month.getCurrentItem() + 1))) {
					wv_day.setAdapter(new NumericWheelAdapter(1, 31));
				} else if (list_little.contains(String.valueOf(wv_month
						.getCurrentItem() + 1))) {
					wv_day.setAdapter(new NumericWheelAdapter(1, 30));
				} else {
					if ((year_num % 4 == 0 && year_num % 100 != 0)
							|| year_num % 400 == 0)
						wv_day.setAdapter(new NumericWheelAdapter(1, 29));
					else
						wv_day.setAdapter(new NumericWheelAdapter(1, 28));
				}
			}
		};
		// 添加"�?"监听
		OnWheelChangedListener wheelListener_month = new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				int month_num = newValue + 1;
				// 判断大小月及是否闰年,用来确定"�?"的数�?
				if (list_big.contains(String.valueOf(month_num))) {
					wv_day.setAdapter(new NumericWheelAdapter(1, 31));
				} else if (list_little.contains(String.valueOf(month_num))) {
					wv_day.setAdapter(new NumericWheelAdapter(1, 30));
				} else {
					if (((wv_year.getCurrentItem() + START_YEAR) % 4 == 0 && (wv_year
							.getCurrentItem() + START_YEAR) % 100 != 0)
							|| (wv_year.getCurrentItem() + START_YEAR) % 400 == 0)
						wv_day.setAdapter(new NumericWheelAdapter(1, 29));
					else
						wv_day.setAdapter(new NumericWheelAdapter(1, 28));
				}
			}
		};
		wv_year.addChangingListener(wheelListener_year);
		wv_month.addChangingListener(wheelListener_month);

		// 根据屏幕密度来指定�?�择器字体的大小(不同屏幕可能不同)
		int textSize = 0;
		if (hasSelectTime)
			textSize = (screenheight / 100) * 3;
		else
			textSize = (screenheight / 100) * 4;
		wv_day.TEXT_SIZE = textSize;
		wv_month.TEXT_SIZE = textSize;
		wv_year.TEXT_SIZE = textSize;
		wv_hours.TEXT_SIZE = textSize;
		wv_mins.TEXT_SIZE = textSize;

	}

	public String getTime() {
		StringBuffer sb = new StringBuffer();
		if (!hasSelectTime){
			if(!showDay){
				sb.append((wv_year.getCurrentItem() + START_YEAR)).append("-");
				if(wv_month.getCurrentItem()<9) {
					sb.append("0"+(wv_month.getCurrentItem() + 1));
				}else{
					sb.append(wv_month.getCurrentItem() + 1 + "");
				}

			}else{
			sb.append((wv_year.getCurrentItem() + START_YEAR)).append("-");
				if(wv_month.getCurrentItem()<9) {
					sb.append("0"+(wv_month.getCurrentItem() + 1)).append("-");
				}else{
					sb.append((wv_month.getCurrentItem() + 1)).append("-");
				}

				if(wv_day.getCurrentItem()<9) {
					sb.append("0"+(wv_day.getCurrentItem() + 1));
				}else{
					sb.append((wv_day.getCurrentItem() + 1));
				}
			}
		}
		else{
			if(!showDay){
				sb.append((wv_year.getCurrentItem() + START_YEAR)).append("-");
				if(wv_month.getCurrentItem()<9) {
					sb.append("0"+(wv_month.getCurrentItem() + 1));
				}else{
					sb.append((wv_month.getCurrentItem() + 1));
				}
			}else{
			sb.append((wv_year.getCurrentItem() + START_YEAR)).append("-");
				if(wv_month.getCurrentItem()<9) {
					sb.append("0"+(wv_month.getCurrentItem() + 1)).append("-");
				}else{
					sb.append((wv_month.getCurrentItem() + 1)).append("-");
				}

				if(wv_day.getCurrentItem()<9) {
					sb.append("0"+(wv_day.getCurrentItem() + 1)).append(" ");
				}else{
					sb.append((wv_day.getCurrentItem() + 1)).append(" ");
				}
			
			if(wv_hours.getCurrentItem()<=9){
			sb.append("0"+wv_hours.getCurrentItem()).append(":");
			}
			else{
				sb.append(wv_hours.getCurrentItem()).append(":");
			}
			if(wv_mins.getCurrentItem()<=9){
			
			  sb.append("0"+wv_mins.getCurrentItem());
			}
			else{
				  sb.append(wv_mins.getCurrentItem());
			}
			}
		}
		return sb.toString();
	}
}
