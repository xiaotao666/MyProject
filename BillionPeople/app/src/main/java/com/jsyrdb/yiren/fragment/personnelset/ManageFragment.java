package com.jsyrdb.yiren.fragment.personnelset;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.jsyrdb.yiren.R;
import com.jsyrdb.yiren.adapter.ManagerAdapter;
import com.jsyrdb.yiren.base.BaseFragment;
import com.jsyrdb.yiren.model.Manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ManageFragment extends BaseFragment implements View.OnClickListener {

    private View view;
    private ListView lv_person;
    private Button btn_clear,btn_commit;

    private ManagerAdapter adapter;
    private List<Manager> list;
    private List<Manager> checkdList = new ArrayList<>();//选中的列表
    private HashMap<Integer, Boolean> isSelected;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        initListener();
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

    private void initView() {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_personner_set,null);
        lv_person = view.findViewById(R.id.lv_person);
        btn_clear = view.findViewById(R.id.btn_clear);
        btn_commit = view.findViewById(R.id.btn_commit);

    }

    public void initData() {
        list = new ArrayList<>();
        for (int i = 0; i < 20;i++) {
            Manager manager = new Manager();
            manager.setName("某某某某");
            manager.setPhone("15588888888");
            list.add(manager);
        }
        isSelected = new HashMap<>();
        adapter = new ManagerAdapter(getActivity(),list,isSelected);
        lv_person.setAdapter(adapter);
    }

    public void initListener() {
        btn_clear.setOnClickListener(this);
        btn_commit.setOnClickListener(this);
    }

    public void saveCheckList() {
        checkdList.clear();
        isSelected = ManagerAdapter.getIsSelected();
        if (isSelected != null && isSelected.size() > 0) {
            for (int i = 0; i < isSelected.size(); i++) {
                if (isSelected.get(i).equals(true)) {
                    checkdList.add(list.get(i));
                }
            }
            Log.e("check", checkdList.size() + "");
        }
    }

    public void clearCheckList() {
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                adapter.getIsSelected().put(i, false);
            }
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_commit:
                saveCheckList();
                break;
            case R.id.btn_clear:
                clearCheckList();
                break;
        }
    }
}
