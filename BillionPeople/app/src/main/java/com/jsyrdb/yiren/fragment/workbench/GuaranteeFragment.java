package com.jsyrdb.yiren.fragment.workbench;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jsyrdb.yiren.R;
import com.jsyrdb.yiren.base.BaseFragment;

public class GuaranteeFragment extends BaseFragment {//保后
    private View view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        if (viewGroup != null) {
            viewGroup.removeAllViewsInLayout();
        }
        return view;
    }

    public void initView() {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_guarantee,null);
        TextView textView = view.findViewById(R.id.tv_guarantee);

    }
}
