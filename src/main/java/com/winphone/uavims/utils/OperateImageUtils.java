package com.winphone.xjwrj.utils;

import com.winphone.xjwrj.common.Constant;
import com.winphone.xjwrj.common.result.Result;
import com.winphone.xjwrj.utils.image.ImageUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * description 图片上传工具
 * package     com.h3c.bim.common.utils
 * author      LiaoJunJie
 * date        2017-11-08 17:18
 * version     V1.0
 */
public class OperateImageUtils {
    private static final Log logger = LogFactory.getLog(OperateImageUtils.class);

    /**
     *  上传文件
     * @param file      文件
     * @param filePath  文件保存路径
     * @return          成功：文件名；失败：null
     */
    public static String uploadImage(MultipartFile file,String filePath){
        // 获取文件名
        String fileName = file.getOriginalFilename();
        logger.info("上传的文件名为：" + fileName);
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        logger.info("上传的后缀名为：" + suffixName);
        // 解决中文问题，liunx下中文路径，图片显示问题
        String uuid = UUID.randomUUID().toString().replaceAll("\\-", "");
        fileName = uuid  + suffixName;
        File dest = new File(filePath + "/temp/" + fileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }

        File destScale = new File(filePath+uuid+".png");
        // 检测是否存在目录
        if (!destScale.getParentFile().exists()) {
            destScale.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
//            ThumbnailatorUtils.scaleQuality(dest,destScale);
            ImageUtils.reduceImg(dest, destScale, 0, 0,0.5f);
            logger.info("图片上传成功");
            deleteFile(dest);

            return uuid + ".png";
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /** 删除单个文件
     *
     * @param file 要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(File file) {
        try {
            // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
            if (file.exists() && file.isFile()) {
                if (file.delete()) {
                    System.out.println("删除单个文件" + file.getName() + "成功！");
                    return true;
                } else {
                    System.out.println("删除单个文件" + file.getName() + "失败！");
                    return false;
                }
            } else {
                System.out.println("删除单个文件失败：文件不存在！");
                return false;
            }
        }catch (Exception e){
            return false;
        }
    }

    /**
     * @param im            原始图像
     * @param width         缩略图宽
     * @param height        缩略图高
     * @return              返回处理后的图像
     */
    public static BufferedImage zoomImage(BufferedImage im,int width, int height) {
        /*原始图像的宽度和高度*/

        /*调整后的图片的宽度和高度*/
        int toWidth=width;
        int toHeight=height;
        /*新生成结果图片*/
        BufferedImage result = new BufferedImage(toWidth, toHeight, BufferedImage.TYPE_INT_RGB);

        result.getGraphics().drawImage(im.getScaledInstance(toWidth, toHeight, java.awt.Image.SCALE_SMOOTH), 0, 0, null);
        return result;
    }

    /**
     * @param im            原始图像
     * @param resizeTimes   倍数,比如0.5就是缩小一半,0.98等等double类型
     * @return              返回处理后的图像
     */
    public static BufferedImage zoomImage(BufferedImage im, float resizeTimes) {
        /*原始图像的宽度和高度*/
        int width = im.getWidth();
        int height = im.getHeight();

        /*调整后的图片的宽度和高度*/
        int toWidth = (int) (Float.parseFloat(String.valueOf(width)) * resizeTimes);
        int toHeight = (int) (Float.parseFloat(String.valueOf(height)) * resizeTimes);

        /*新生成结果图片*/
        BufferedImage result = new BufferedImage(toWidth, toHeight, BufferedImage.TYPE_INT_RGB);

        result.getGraphics().drawImage(im.getScaledInstance(toWidth, toHeight, java.awt.Image.SCALE_SMOOTH), 0, 0, null);
        return result;
    }

    /**
     * @param path  要转化的图像的文件夹,就是存放图像的文件夹路径
     * @param type  图片的后缀名组成的数组
     * @return
     */
    public List<BufferedImage> getImageList(String path, String[] type) throws IOException{
        Map<String,Boolean> map = new HashMap<String, Boolean>();
        for(String s : type) {
            map.put(s,true);
        }
        List<BufferedImage> result = new ArrayList<BufferedImage>();
        File[] fileList = new File(path).listFiles();
        assert fileList != null;
        for (File f : fileList) {
            if(f.length() == 0){
                continue;
            }
            if(map.get(getExtension(f.getName())) == null){
                continue;
            }

            result.add(ImageIO.read(f));
        }
        return result;
    }

    public static BufferedImage getImage(String path) throws IOException{
        return ImageIO.read(new File(path));
    }

    /**
     * 把图片写到磁盘上
     * @param im
     * @param path     eg: C://home// 图片写入的文件夹地址
     * @param fileName DCM1987.jpg  写入图片的名字
     * @return
     */
    public static boolean writeToDisk(BufferedImage im, String path, String fileName) {
        String fileType = getExtension(fileName);
        if (fileType == null){
            return false;
        }
        try {
            File f = new File(path + fileName);
            // 检测是否存在目录
            if (!f.getParentFile().exists()) {
                f.getParentFile().mkdirs();
            }
            ImageIO.write(im, fileType, f);
            im.flush();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * 返回文件的文件后缀名
     * @param fileName
     * @return
     */
    public static String getExtension(String fileName) {
        try {
            return fileName.split("\\.")[fileName.split("\\.").length - 1];
        } catch (Exception e) {
            return null;
        }
    }

/*
    //测试
    public static void main(String[] args) throws Exception{

   	System.out.println(123);
        String inputFoler = "F:\\testimages\\yuan";
         //这儿填写你存放要缩小图片的文件夹全地址
        String outputFolder = "F:\\testimages\\ys";
        //这儿填写你转化后的图片存放的文件夹
        float times = 0.5f;
        //这个参数是要转化成的倍数,如果是1就是转化成1倍


        OperateImageUtils r = new OperateImageUtils();

        List<BufferedImage> imageList = r.getImageList(inputFoler,new String[] {"png"});
        for(BufferedImage i : imageList) {

         r.writeHighQuality(r.zoomImage(i,times),outputFolder);
         System.out.println("...");
        }
        OperateImageUtils r=new OperateImageUtils();
        String filepath="E:\\file\\";
        String filename="1.jpg";
        BufferedImage im=r.getImage(filepath+filename);
        r.writeHighQuality(r.zoomImage(im,100,100), filepath+"s_"+filename);//为防止覆盖原图片,加s_区分是压缩以后的图片
    }
*/


    /**
     * 保存单个图片
     * @param file          图片文件
     * @param filePath      存放路径
     * @return
     */
    public static Result saveImage(MultipartFile file, String filePath){
        Result result = new Result();
        result.setSuccess(false);
        try {
            //限制图片大小
            if (file.getSize() > Constant.MAX_IMAGE_SIZE) {
                result.setMsg("图片大小超过限制");
                result.setCode(Result.OTHER);
                return result;
            }
            //判断文件格式
            if (!Constant.IMAGE_TYPE_LIST.contains(CheckoutFileType.getFileType(file))) {
                result.setMsg("图片格式不正确");
                result.setCode(Result.OTHER);
                return result;
            }
            //上传文件
            String fileName = OperateImageUtils.uploadImage(file, filePath);
            if (!StringUtils.isNotBlank(fileName)) {
                result.setMsg("图片上传失败");
                result.setCode(Result.OTHER);
                return result;
            }
            result.setSuccess(true);
            result.setObj(fileName);
            return result;
        } catch (Exception e) {
            logger.error("save single img audit error");
            result.setMsg("图片上传失败");
            result.setCode(Result.OTHER);
            return result;
        }

    }


}
