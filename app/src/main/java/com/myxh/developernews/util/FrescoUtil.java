package com.myxh.developernews.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;

import com.facebook.binaryresource.BinaryResource;
import com.facebook.binaryresource.FileBinaryResource;
import com.facebook.cache.common.CacheKey;
import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.cache.DefaultCacheKeyFactory;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.core.ImagePipelineFactory;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.myxh.developernews.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * Created by asus on 2016/10/6.
 */

public class FrescoUtil {

    /**
     * 保存图片到本地
     * @param context
     * @param picUrl
     */
    public static void saveImageToDisk(Context context, String picUrl, String picName) {
        File localFile = getCachedImageOnDisk(context, picUrl);
        if (localFile != null) {
            File appDir = new File(Environment.getExternalStorageDirectory(), context.getString(R.string.app_name));
            if (!appDir.exists()) {
                appDir.mkdir();
            }
            String fileName = picName + ".jpg";//
            File file = new File(appDir, fileName);

            FileInputStream fis = null;
            FileOutputStream fos = null;
            try {
                fis = new FileInputStream(localFile);
                fos = new FileOutputStream(file);
                byte[] buffer = new byte[1024];
                if (fis.read(buffer) >= 0) {
                    fos.write(buffer,0,buffer.length);
                }
                ToastUtil.show(context,"图片已保存至"+file.getAbsolutePath());
                fos.flush();
            } catch (IOException e) {
                ToastUtil.show(context,"保存失败");
                e.printStackTrace();
            } finally {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            getCachedImageOnMemory(context, picUrl, picName);
        }
    }

    /**
     * 从硬盘获取缓存
     * @param context
     * @param picUrl
     * @return
     */
    public static File getCachedImageOnDisk(Context context, String picUrl) {
        CacheKey cacheKey = DefaultCacheKeyFactory.getInstance().getEncodedCacheKey(ImageRequest.fromUri(Uri.parse(picUrl)),context);
        File localFile = null;
        if (cacheKey != null) {
            if(ImagePipelineFactory.getInstance().getMainDiskStorageCache().hasKey(cacheKey)) {
                BinaryResource resource = ImagePipelineFactory.getInstance().getMainDiskStorageCache().getResource(cacheKey);
                localFile = ((FileBinaryResource) resource).getFile();
            } else if (ImagePipelineFactory.getInstance().getSmallImageDiskStorageCache().hasKey(cacheKey)) {
                BinaryResource resource = ImagePipelineFactory.getInstance().getSmallImageDiskStorageCache().getResource(cacheKey);
                localFile = ((FileBinaryResource) resource).getFile();
            }
        }
        return localFile;
    }

    /**
     * 从缓存中获取Bitmap
     * @param context
     * @param url
     * @param picName
     */
    public static void getCachedImageOnMemory(Context context, String url, String picName) {

        ImageRequest imageRequest;
        imageRequest = ImageRequestBuilder
                .newBuilderWithSource(Uri.parse(url))
                .setProgressiveRenderingEnabled(true)
                .build();

        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        DataSource<CloseableReference<CloseableImage>>
                dataSource = imagePipeline.fetchDecodedImage(imageRequest, context);

        dataSource.subscribe(new BaseBitmapDataSubscriber() {
            @Override
            public void onNewResultImpl(Bitmap bitmap) {
                if (bitmap == null) {
                    Toast.makeText(context, "保存图片失败啦,无法下载图片", Toast.LENGTH_SHORT).show();
                }
                File appDir = new File(Environment.getExternalStorageDirectory(), context.getString(R.string.app_name));
                if (!appDir.exists()) {
                    appDir.mkdir();
                }
                String fileName = picName + ".jpg";//
                File file = new File(appDir, fileName);
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(file);
                    assert bitmap != null;
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                    ToastUtil.show(context,"图片已保存至"+file.getAbsolutePath());
                    fos.flush();
                } catch (IOException e) {
                    ToastUtil.show(context,"保存失败");
                    e.printStackTrace();
                } finally {
                    if (fos != null) {
                        try {
                            fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                Uri uri = Uri.fromFile(file);
                // 通知图库更新
                Intent scannerIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri);
                context.sendBroadcast(scannerIntent);

            }

            @Override
            public void onFailureImpl(DataSource dataSource) {
            }
        }, CallerThreadExecutor.getInstance());
    }
}
