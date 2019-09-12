package com.jsyrdb.yiren.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jsyrdb.yiren.R;
import com.jsyrdb.yiren.model.Attribute;
import com.jsyrdb.yiren.model.SelectParent;

import org.w3c.dom.Text;

import java.util.List;

public class SelectTagAdapter extends BaseExpandableListAdapter {
    private Context context;
    private LayoutInflater inflater;
    private List<Attribute.ResultBean.SelectOptionsBean> list;

    public SelectTagAdapter(Context context,List<Attribute.ResultBean.SelectOptionsBean> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return list.get(groupPosition).getChildren().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return list.get(groupPosition).getChildren().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolderParent viewHolderParent;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.expand_select_parent,null);
            viewHolderParent = new ViewHolderParent(convertView);
            convertView.setTag(viewHolderParent);
        } else {
            viewHolderParent = (ViewHolderParent) convertView.getTag();
        }
        viewHolderParent.tv_parent.setText(list.get(groupPosition).getValue());
        if (isExpanded) {
            viewHolderParent.iv_expand.setImageResource(R.drawable.downarrow);
        } else {
            viewHolderParent.iv_expand.setImageResource(R.drawable.right_arrow);
        }

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolderChild viewHolderChild;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.expand_select_child,null);
            viewHolderChild = new ViewHolderChild(convertView);
            convertView.setTag(viewHolderChild);
        } else {
            viewHolderChild = (ViewHolderChild) convertView.getTag();
        }
        viewHolderChild.tv_child.setText(list.get(groupPosition).getChildren().get(childPosition).getValue());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public class ViewHolderParent{
        TextView tv_parent;
        ImageView iv_expand;
        public ViewHolderParent(View view) {
            tv_parent = view.findViewById(R.id.tv_parent);
            iv_expand = view.findViewById(R.id.iv_expand);
        }
    }

    public class ViewHolderChild{
        TextView tv_child;
        public ViewHolderChild(View view) {
            tv_child = view.findViewById(R.id.tv_child);
        }
    }
}
