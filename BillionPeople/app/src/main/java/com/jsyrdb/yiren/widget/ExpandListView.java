package com.jsyrdb.yiren.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class ExpandListView extends ListView {
    boolean expanded = true;

    public ExpandListView(Context context) {
        super(context);
    }

    public ExpandListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ExpandListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public boolean isExpanded()
    {
        return expanded;
    }

    /**
     * 设置是否延展高度
     * @param expanded
     */
    public void setExpanded(boolean expanded)
    {
        this.expanded = expanded;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if(isExpanded()){
            int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2
                    , MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, expandSpec);
            android.view.ViewGroup.LayoutParams params = getLayoutParams();
            params.height = getMeasuredHeight();
        }else{
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
