package com.jsyrdb.yiren.activity.cases;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jsyrdb.yiren.R;
import com.jsyrdb.yiren.activity.main.MainNewsActivity;
import com.jsyrdb.yiren.activity.main.MainSearchActivity;
import com.jsyrdb.yiren.adapter.SelectTagAdapter;
import com.jsyrdb.yiren.base.BaseActivity;
import com.jsyrdb.yiren.model.Attribute;
import com.jsyrdb.yiren.model.SelectParent;

import java.util.ArrayList;
import java.util.List;

public class SelectTagActivity extends BaseActivity implements View.OnClickListener {

    //标题栏
//    private TextView tv_title;
//    private ImageView iv_message,iv_search;
//    private LinearLayout ll_back;

    private ExpandableListView expand_select;
    private SelectTagAdapter adapter;
    private List<Attribute.ResultBean.SelectOptionsBean> parentList;

    private String parentValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_tag);

        setStatusBarColor(getResources().getColor(R.color.green_item));
        setStatusBarDarkTheme(false);

        parentList = (List<Attribute.ResultBean.SelectOptionsBean>) getIntent().getSerializableExtra("select_options");
        initView();
        initListener();
        setTitle("选择属性");
        setData();
    }

    public void initView() {
//        tv_title = findViewById(R.id.tv_title);
//        tv_title.setText("选择属性");
//        iv_message = findViewById(R.id.iv_message);
//        iv_search = findViewById(R.id.iv_search);
//        ll_back = findViewById(R.id.ll_back);
        expand_select = findViewById(R.id.expand_select);

        expand_select.setGroupIndicator(null);

        expand_select.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if (parentList.get(groupPosition).getChildren() != null && parentList.get(groupPosition).getChildren().size() > 0) {
                    parentValue = parentList.get(groupPosition).getValue();
                } else {
                    parentValue = parentList.get(groupPosition).getValue();
                    Intent intent = new Intent();
                    intent.putExtra("selectValue",parentValue);
                    setResult(RESULT_OK,intent);
                    finish();
                }
                return false;
            }
        });

        expand_select.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(parentValue).append(",").append(parentList.get(groupPosition).getChildren().get(childPosition).getValue());
                Intent intent = new Intent();
                intent.putExtra("selectValue",stringBuilder.toString());
                setResult(RESULT_OK,intent);
                finish();
                return false;
            }
        });
    }

    public void setData() {
//        parentList = new ArrayList<>();
//        for (int i = 0;i < 5;i++) {
//            Attribute.ResultBean.SelectOptionsBean selectOptionsBean = new Attribute.ResultBean.SelectOptionsBean();
//            selectOptionsBean.setValue("测试" + i);
//            List<Attribute.ResultBean.SelectOptionsBean.ChildrenBean> childrenBeanList = new ArrayList<>();
//            for (int j = 0;j < 3;j++) {
//                Attribute.ResultBean.SelectOptionsBean.ChildrenBean childrenBean = new Attribute.ResultBean.SelectOptionsBean.ChildrenBean();
//                childrenBean.setValue("测试子项"+j);
//                childrenBeanList.add(childrenBean);
//            }
//            selectOptionsBean.setChildren(childrenBeanList);
//            parentList.add(selectOptionsBean);
//        }
        adapter = new SelectTagAdapter(this,parentList);
        expand_select.setAdapter(adapter);
    }

    public void initListener() {
//        ll_back.setOnClickListener(this);
//        iv_message.setOnClickListener(this);
//        iv_search.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.ll_back:
//                finish();
//                break;
//            case R.id.iv_message:
//                Intent intent = new Intent(this, MainNewsActivity.class);
//                startActivity(intent);
//                break;
//            case R.id.iv_search:
//                Intent intent1 = new Intent(this, MainSearchActivity.class);
//                startActivity(intent1);
//        }
    }
}
