package com.xll.mvpandretrofit.dialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

import com.xll.mvpandretrofit.R;
import com.xll.mvplib.dialog.BottomListDialog;
import com.xll.mvplib.dialog.CommonBottomDialog;
import com.xll.mvplib.dialog.CommonDialog;
import com.xll.mvplib.dialog.CommonDialogWithSpecailText;
import com.xll.mvplib.dialog.DialogClickListener;
import com.xll.mvplib.dialog.ListDialog;
import com.xll.mvplib.dialog.MentionDialog;
import com.xll.mvplib.dialog.MiddleListDialog;
import com.xll.mvplib.dialog.base.BaseDialog;
import com.xll.mvplib.view.ItemClickListener;

import java.util.List;
import java.util.Map;

/**
 * 功能：dialog管理
 * 作者：xll
 * 日期：2018/1/31
 * 邮箱：liangliang.xu1110@gmail.com
 */
public class DialogManager {

    private static DialogManager dialogManager;

    private ProgressDialog mProgressDialog;

    public BaseDialog mDialog;

    private DialogManager() {

    }

    public static DialogManager getInstance() {
        if (null == dialogManager) {
            synchronized (DialogManager.class) {
                if (null == dialogManager) {
                    dialogManager = new DialogManager();
                }
            }
        }
        return dialogManager;
    }

    /**
     * 两个普通按钮框 取消 确定
     *
     * @param content       中间信息
     * @param clickListener 按钮监听
     */
    public void showCommonDialog(Context context, String content, DialogClickListener clickListener) {
        mDialog = new CommonDialog(context, content, clickListener);
        mDialog.show();
    }

    public void showCommonDialog(Context context, String title, String content, String right){
        mDialog = new CommonDialog(context, title, content, "", right, null);
        mDialog.show();
    }

    public void showCommonDialog(Context context, String content, String left, String right, DialogClickListener clickListener, boolean rightDismiss) {
        mDialog = new CommonDialog(context, "", content, left, right, clickListener, rightDismiss);
        mDialog.show();
    }

    public void showCommonDialog(Context context, String title, String content, String left, String right, DialogClickListener clickListener) {
        mDialog = new CommonDialog(context, title, content, left, right, clickListener, true);
        mDialog.show();
    }

    public void showBottomCommonDialog(Context context, String title, String content){
        mDialog = new CommonBottomDialog(context, title, content);
        mDialog.show();
    }
    /**
     * 供应商公司列表框
     *
     * @param context
     * @param title
     * @param data
     * @param onItemClickListener
     */
    public void showProviderDialog(Context context, String title, List<Map<String, String>> data, ItemClickListener onItemClickListener) {
        mDialog = new ListDialog(context, title, data, onItemClickListener);
        mDialog.show();
    }

    public void showBottomListDialog(Context context, String[] titles, ItemClickListener itemClickListener) {
        mDialog = new BottomListDialog(context, titles, itemClickListener);
        mDialog.show();
    }

    public void showMentionDialog(Context context, int imageId, String message) {
        mDialog = new MentionDialog(context, imageId, message);
        mDialog.show();
    }

    public void showCommonDialogSpecailText(Context context, String content, String specailStr, String left, String right, DialogClickListener clickListener){
        mDialog = new CommonDialogWithSpecailText(context, content, specailStr, left, right, clickListener);
        mDialog.show();
    }


    /**
     * 测试地址选择
     *
     * @param context
     * @param titile
     * @param listData
     * @param itemClickListener
     */
    public void showAddressDialog(Context context, String titile, SparseArray<String> listData, ItemClickListener itemClickListener) {
        mDialog = new MiddleListDialog(context, titile, listData, itemClickListener);
        mDialog.show();
    }

    public void showProgressDialog(Context context) {
        dismissProgressDialog();
        mProgressDialog = new ProgressDialog(context);
        //点击提示框外面是否取消提示框
        mProgressDialog.setCanceledOnTouchOutside(false);
        //点击返回键是否取消提示框
        mProgressDialog.setCancelable(false);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.show();
        mProgressDialog.setContentView(R.layout.progress_layout);
    }

    public void showProgressDialog(Context context, String message) {
        dismissProgressDialog();
        mProgressDialog = new ProgressDialog(context);
        //点击提示框外面是否取消提示框
        mProgressDialog.setCanceledOnTouchOutside(false);
        //点击返回键是否取消提示框
        mProgressDialog.setCancelable(false);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.show();
        View view = View.inflate(context,R.layout.progress_layout, null);
        TextView tvMsg = (TextView) view.findViewById(R.id.tv_message);
        tvMsg.setText(message);
        mProgressDialog.setContentView(view);
    }

    public void showProgressDialogWithIndicator(Context context) {
        dismissProgressDialog();
        //监听下载进度
        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setProgressNumberFormat("%1d KB/0 KB");
        mProgressDialog.setTitle("下载");
        mProgressDialog.setMessage("正在下载，请稍后...");
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
    }

    public void updateProgress(long bytesRead, long contentLength, boolean done) {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.setProgressNumberFormat("%1d KB/%2d KB");
            mProgressDialog.setMax((int) (contentLength / 1024));
            mProgressDialog.setProgress((int) (bytesRead / 1024));
            if (done) {
                dismissProgressDialog();
            }
        }
    }

    public void dismissProgressDialog() {
        if (null != mProgressDialog && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }

    public void dismissDialog() {
        if (null != mDialog && mDialog.isShowing()) {
            mDialog.dismiss();
            mDialog = null;
        }
    }


}
