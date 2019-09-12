package com.jsyrdb.yiren.utils;

import android.content.Context;
import android.view.View;
import android.widget.PopupWindow;

public class CommonPopupWindow extends PopupWindow {
    final PopupController controller;
    private OnChangeStyleListener listener;

    @Override
    public int getWidth() {
        return controller.mPopupView.getMeasuredWidth();
    }
    @Override
    public int getHeight() {
        return controller.mPopupView.getMeasuredHeight();
    }
    public interface ViewInterface {
        void getChildView(View view, int layoutResId);
    }

    public interface OnChangeStyleListener{
        void change();
    }

    public void setChangeStyleListener(OnChangeStyleListener listener) {
        this.listener = listener;
    }

    public CommonPopupWindow(Context context) {
        controller = new PopupController(context, this);
    }

    @Override
    public void dismiss() {
        super.dismiss();
        controller.setBackGroundLevel(1.0f);
        if (listener != null) {
            listener.change();
        }
    }

    public static class Builder {
        private final PopupController.PopupParams params;
        private ViewInterface listener;
        public Builder(Context context) {
            params = new PopupController.PopupParams(context);
        }

        /**
         * @param layoutResId 设置PopupWindow 布局ID
         * @return Builder
         */ public Builder setView(int layoutResId) {
             params.mView = null;
             params.layoutResId = layoutResId;
             return this;
         }

        /**
         * @param view 设置PopupWindow布局
         * @return Builder
         */
        public Builder setView(View view) {
            params.mView = view;
            params.layoutResId = 0;
            return this;
        }
        /**
         * 设置子View
         *
         * @param listener ViewInterface
         * @return Builder
         */
        public Builder setViewOnclickListener(ViewInterface listener) {
            this.listener = listener;
            return this;
        }
        /**
         * 设置宽度和高度 如果不设置 默认是wrap_content
         *
         * @param width 宽
         * @return Builder
         */
        public Builder setWidthAndHeight(int width, int height) {
            params.mWidth = width;
            params.mHeight = height;
            return this;
        }

        /**
         * 设置背景灰色程度
         *
         * @param level 0.0f-1.0f
         * @return Builder
         */
        public Builder setBackGroundLevel(float level) {
             params.isShowBg = true;
             params.bg_level = level;
             return this;
        }
        /**
         * 是否可点击Outside消失
         *
         * @param touchable 是否可点击
         * @return Builder
         */
        public Builder setOutsideTouchable(boolean touchable) {
             params.isTouchable = touchable;
             return this;
         }
         /**
         * 设置动画
         *
         * @return Builder
         */
         public Builder setAnimationStyle(int animationStyle) {
             params.isShowAnim = true;
             params.animationStyle = animationStyle;
             return this;
         }
         public CommonPopupWindow create() {
             final CommonPopupWindow popupWindow = new CommonPopupWindow(params.mContext);
             params.apply(popupWindow.controller);
             if (listener != null && params.layoutResId != 0) {
                 listener.getChildView(popupWindow.controller.mPopupView, params.layoutResId);
             }
             measureWidthAndHeight(popupWindow.controller.mPopupView); return popupWindow;
         }


        private void measureWidthAndHeight(View popupView) {
             //设置测量模式为UNSPECIFIED可以确保测量不受父View的影响
            int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            popupView.measure(w, h);
            //得到测量宽度
            int mWidth=popupView.getMeasuredWidth();
            //得到测量高度
            int mHeight=popupView.getMeasuredHeight();
         }
    }
}
