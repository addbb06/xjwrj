package com.winphone.xjwrj.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtilsBean;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created with IntelliJ IDEA.
 * Package: com.h3c.bim.common.utils
 * Description : TODO
 * User: zhou
 * Date: 2017/9/2114:09
 * version 1.0.0
 * see:
 */

public class ObjectUtils {


//    public static  <T extends Object> List<T> converList(List objList,T t){
//
//        List<T> lists = new ArrayList<>();
//        objList.forEach(entity ->{
//            try {
//                T toClass = (T)t.getClass().newInstance();
//                BeanUtils.copyProperties(toClass,entity);
//                lists.add(toClass);
//            } catch (InstantiationException e) {
//                e.printStackTrace();
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//        });
//        return lists;
//    }

    //将javabean实体类转为map类型，然后返回一个map类型的值
    public static Map<String, Object> beanToMap(Object obj) {
        Map<String, Object> params = new HashMap<String, Object>(0);
        try {
            PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
            PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(obj);
            for (int i = 0; i < descriptors.length; i++) {
                String name = descriptors[i].getName();
                if (!"class".equals(name)) {
                    params.put(name, propertyUtilsBean.getNestedProperty(obj, name));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return params;
    }

    /**
     *  对象转对象
     * @param obj 初始对象
     * @param cla 目标class
     * @param <T>
     * @return
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public <T> T convertClass(Object obj,Class<T> cla) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {

        Map<String,Object> maps = new HashMap<>();
        T  dataBean = null;
        if(null==obj) {
            return null;
        }
        Class<?> cls = obj.getClass();
        dataBean = cla.newInstance();
        Field[] fields = cls.getDeclaredFields();
        Field[] beanFields = cla.getDeclaredFields();
        for(Field field:fields){
            String fieldName = field.getName();
            String strGet = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1, fieldName.length());
            Method methodGet = cls.getDeclaredMethod(strGet);
            Object object = methodGet.invoke(obj);
            maps.put(fieldName,object==null?"":object);
        }

        for(Field field:beanFields){
            field.setAccessible(true);
            String fieldName = field.getName();
            Class<?> fieldType = field.getType();
            String fieldValue = maps.get(fieldName)==null?null:maps.get(fieldName).toString();
            if(fieldValue!=null){
                try {
                    if(String.class.equals(fieldType)){
                        field.set(dataBean, fieldValue);
                    }else if(byte.class.equals(fieldType)){
                        field.setByte(dataBean, Byte.parseByte(fieldValue));

                    }else if(Byte.class.equals(fieldType)){
                        field.set(dataBean, Byte.valueOf(fieldValue));

                    }else if(boolean.class.equals(fieldType)){
                        field.setBoolean(dataBean, Boolean.parseBoolean(fieldValue));

                    }else if(Boolean.class.equals(fieldType)){
                        field.set(dataBean, Boolean.valueOf(fieldValue));

                    }else if(short.class.equals(fieldType)){
                        field.setShort(dataBean, Short.parseShort(fieldValue));

                    }else if(Short.class.equals(fieldType)){
                        field.set(dataBean, Short.valueOf(fieldValue));

                    }else if(int.class.equals(fieldType)){
                        field.setInt(dataBean, Integer.parseInt(fieldValue));

                    }else if(Integer.class.equals(fieldType)){
                        field.set(dataBean, Integer.valueOf(fieldValue));

                    }else if(long.class.equals(fieldType)){
                        field.setLong(dataBean, Long.parseLong(fieldValue));

                    }else if(Long.class.equals(fieldType)){
                        field.set(dataBean, Long.valueOf(fieldValue));

                    }else if(float.class.equals(fieldType)){
                        field.setFloat(dataBean, Float.parseFloat(fieldValue));

                    }else if(Float.class.equals(fieldType)){
                        field.set(dataBean, Float.valueOf(fieldValue));

                    }else if(double.class.equals(fieldType)){
                        field.setDouble(dataBean, Double.parseDouble(fieldValue));

                    }else if(Double.class.equals(fieldType)){
                        field.set(dataBean, Double.valueOf(fieldValue));

                    }else if(Date.class.equals(fieldType)){
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                        field.set(dataBean, sdf.parse(fieldValue));
                    }
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        return dataBean;
    }

    /**
     * @Description: List&lt;Biz&gt; to List&lt;Vo&gt;
     * @author: liangqun.yu
     * @Params: [bizList, targetClazz]
     * @Return: java.util.List<Vo>
     * @Date:   2017/9/27
     */
    public static <Vo extends Object,Biz extends Object> List<Vo> copylist(List<Biz> bizList,Class targetClazz){
        List<Vo> voList = new ArrayList<Vo>();
        bizList.forEach(biz -> {
            try {
                Vo vo = (Vo)targetClazz.newInstance();
                BeanUtils.copyProperties(vo,biz);
                voList.add(vo);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        });
        return voList;
    }

//    public static void main(String[] args){
//        List<UserInfo> userInfos = new ArrayList<>();
//
//        UserInfo userInfo = new UserInfo();
//
//        userInfo.setName("abcdefg");
//
//        userInfos.add(userInfo);
//
//        List<UserVo> list = ObjectUtils.copylist(userInfos, UserVo.class);
//
//        list.forEach(userVo -> {
//            System.out.print(userVo.getName());
//        });
//    }
}
