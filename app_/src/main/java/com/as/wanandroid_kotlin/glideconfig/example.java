package com.as.wanandroid_kotlin.glideconfig;

/**
 * -----------------------------
 * Created by zqf on 2019/1/21.
 * ---------------------------
 */
public class example {
//     Glide.with(_mActivity)
//            .load(data.get(0).getThumb())
//            .transition(DrawableTransitionOptions.withCrossFade(1000))
//            .apply(RequestOptions.bitmapTransform(new BlurTransformation(25, 3)))
//            .into(new SimpleTarget<Drawable>() {
//        @Override
//        public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
//            resource.setColorFilter(Color.parseColor("#4D000000"), PorterDuff.Mode.DARKEN);
//            mViewBinding.imagebg.setImageDrawable(resource);
//        }
//    });


    /**
     * 关于进度的监听  例子
     *
     *
     * ProgressInterceptor.addListener(imagePath, new ProgressListener() {
     *             @Override
     *             public void onProgress(int progress) {
     *                 progressDialog.setProgress(progress);
     *             }
     *         });
     *
     * Glide.with(this)
     *                 .load(imagePath)
     *                 .apply(RequestOptions.bitmapTransform(new CircleCrop()))
     *                 .into(new DrawableImageViewTarget(mViewBinding.ivHead) {
     *                     @Override
     *                     public void onLoadStarted(@Nullable Drawable placeholder) {
     *                         super.onLoadStarted(placeholder);
     *                         progressDialog.show();
     *                     }
     *
     *                     @Override
     *                     public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
     *                         super.onResourceReady(resource, transition);
     *                         progressDialog.dismiss();
     *                         ProgressInterceptor.removeListener(imagePath);
     *                     }
     *                 });
     */
}
