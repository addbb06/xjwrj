package com.winphone.xjwrj.utils.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author: zhou
 * @Description: 图片压缩
 * @Date:Create in 2018/01/15
 * @Modified By:
 */

public class ImageUtils {

    /**
     * 采用指定宽度、高度或压缩比例 的方式对图片进行压缩
     * @param imgsrc 源图片地址
     * @param imgdist 目标图片地址
     * @param widthdist 压缩后图片宽度（当rate==null时，必传）
     * @param heightdist 压缩后图片高度（当rate==null时，必传）
     * @param rate 压缩比例
     */
    public static void reduceImg(String imgsrc, String imgdist, int widthdist,
                                 int heightdist, Float rate) {
        try {
            File srcfile = new File(imgsrc);
            // 检查文件是否存在
            if (!srcfile.exists()) {
                return;
            }
            // 如果rate不为空说明是按比例压缩
            if (rate != null && rate > 0) {
                // 获取文件高度和宽度
                int[] results = getImgWidth(srcfile);
                if (results == null || results[0] == 0 || results[1] == 0) {
                    return;
                } else {
                    widthdist = (int) (results[0] * rate);
                    heightdist = (int) (results[1] * rate);
                }
            }
            // 开始读取文件并进行压缩
            Image src = javax.imageio.ImageIO.read(srcfile);
            BufferedImage tag = new BufferedImage((int) widthdist,
                    (int) heightdist, BufferedImage.TYPE_INT_RGB);

            tag.getGraphics().drawImage(
                    src.getScaledInstance(widthdist, heightdist,
                            Image.SCALE_SMOOTH), 0, 0, null);

            String formatName = imgdist.substring(imgdist.lastIndexOf(".") + 1);
            ImageIO.write(tag, formatName, new File(imgdist));

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 采用指定宽度、高度或压缩比例 的方式对图片进行压缩
     * @param srcfile 源图片地址
     * @param imgdistfile 目标图片地址
     * @param widthdist 压缩后图片宽度（当rate==null时，必传）
     * @param heightdist 压缩后图片高度（当rate==null时，必传）
     * @param rate 压缩比例
     */
    public static void reduceImg(File srcfile, File imgdistfile, int widthdist,
                                 int heightdist, Float rate) {
        try {
            // 检查文件是否存在
            if (!srcfile.exists()) {
                return;
            }
            // 如果rate不为空说明是按比例压缩
            if (rate != null && rate > 0) {
                // 获取文件高度和宽度
                int[] results = getImgWidth(srcfile);
                if (results == null || results[0] == 0 || results[1] == 0) {
                    return;
                } else {
                    widthdist = (int) (results[0] * rate);
                    heightdist = (int) (results[1] * rate);
                }
            }
            // 开始读取文件并进行压缩
            Image src = ImageIO.read(srcfile);
            BufferedImage tag = new BufferedImage((int) widthdist,
                    (int) heightdist, BufferedImage.TYPE_INT_RGB);

            tag.getGraphics().drawImage(
                    src.getScaledInstance(widthdist, heightdist,
                            Image.SCALE_SMOOTH), 0, 0, null);

            String formatName = "png";
            try {
                formatName = srcfile.getName().substring(srcfile.getName().lastIndexOf(".") + 1);
            } catch (Exception ignored){
                formatName = "png";
            }
            ImageIO.write(tag, formatName, imgdistfile);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 获取图片宽度
     *
     * @param file
     *            图片文件
     * @return 宽度
     */
    public static int[] getImgWidth(File file) {
        InputStream is = null;
        BufferedImage src = null;
        int result[] = { 0, 0 };
        try {
            is = new FileInputStream(file);
            src = javax.imageio.ImageIO.read(is);
            result[0] = src.getWidth(null); // 得到源图宽
            result[1] = src.getHeight(null); // 得到源图高
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


/*    public static void main(String[] args) {
        *//**
         * d://3.jpg 源图片
         * d://31.jpg 目标图片
         * 压缩宽度和高度都是1000
         *
         *//*
        System.out.println("压缩图片开始...");
        File srcfile = new File("I:/IMG_20170326_145210.jpg");
        System.out.println("压缩前srcfile size:" + srcfile.length());
        reduceImg("I:/IMG_20170326_145210.jpg", "I://123_1.jpg", 0, 0,0.3f);
        File distfile = new File("I://123_1.jpg");
        System.out.println("压缩后distfile size:" + distfile.length());

    }*/

}
