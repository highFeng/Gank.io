package com.fangsf.gankio.ui.adapter;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fangsf.gankio.bean.VideoBean;
import com.fangsf.minddemo.R;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.NormalGSYVideoPlayer;
import com.shuyu.gsyvideoplayer.video.base.GSYBaseVideoPlayer;

/**
 * Created by fangsf on 2018/3/11.
 * Useful:
 */

public class VideoAdapter extends BaseQuickAdapter<VideoBean.TrailersBean, BaseViewHolder> {

    public NormalGSYVideoPlayer mVideoPlayer;

    public VideoAdapter(int layoutResId) {
        super(layoutResId);
    }


    @SuppressLint("ResourceAsColor")
    @Override
    protected void convert(BaseViewHolder helper, VideoBean.TrailersBean item) {

        NormalGSYVideoPlayer mVideoPlayer = helper.getView(R.id.normalGsyVideo);

        if (mVideoPlayer != null) {

            //   view.initUIState();

            // 设置封面
            ImageView imageView = new ImageView(mContext);
            Glide.with(mContext).load(item.getCoverImg()).centerCrop().into(imageView);
            mVideoPlayer.setThumbImageView(imageView);

            mVideoPlayer.setUp(item.getUrl(), false, item.getMovieName());

            mVideoPlayer.setReleaseWhenLossAudio(true);

            mVideoPlayer.setShowFullAnimation(true);

            mVideoPlayer.getTitleTextView().setTextSize(13);
            mVideoPlayer.getBackButton().setVisibility(View.GONE);

            mVideoPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mVideoPlayer.startWindowFullscreen(mContext, false, true);
                    mVideoPlayer.setLockLand(true);
                }
            });

        }


    }


}
