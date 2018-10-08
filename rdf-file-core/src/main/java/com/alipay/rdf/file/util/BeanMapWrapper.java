package com.alipay.rdf.file.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.alipay.rdf.file.exception.RdfErrorEnum;
import com.alipay.rdf.file.exception.RdfFileException;

/**
 * Copyright (C) 2013-2018 Ant Financial Services Group
 * 
 * @author hongwei.quhw
 * @version $Id: BeanMapWrapper.java, v 0.1 2017年4月7日 下午2:56:25 hongwei.quhw Exp $
 */
public class BeanMapWrapper {
    private Map<String, PropertyDescriptor> BEANINFO_CACHE = new ConcurrentHashMap<String, PropertyDescriptor>();
    private final Object                    bean;
    private boolean                         isMap;

    public BeanMapWrapper(Object bean) {
        this.bean = bean;
        isMap = Map.class.isAssignableFrom(bean.getClass());
        if (!isMap) {
            initBeanInfo(bean.getClass());
        }
    }

    public BeanMapWrapper(Class<?> clazz) {
        try {
            bean = clazz.newInstance();

            isMap = Map.class.isAssignableFrom(clazz);
            if (!isMap) {
                initBeanInfo(clazz);
            }
        } catch (Exception e) {
            throw new RdfFileException("ref-file# class=" + clazz.getName() + " 对象实例化错误", e,
                RdfErrorEnum.INSTANTIATION_ERROR);
        }
    }

    private void initBeanInfo(Class<?> clazz) {
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor pd : propertyDescriptors) {
                // yjk 修改 属性名称全部大写
                BEANINFO_CACHE.put(pd.getName().toUpperCase(), pd);
            }
        } catch (Exception e) {
            throw new RdfFileException(e, RdfErrorEnum.INSTANTIATION_ERROR);
        }
    }

    public void setProperties(Map<String, Object> values) {
        for (String key : values.keySet()) {
            setProperty(key, values.get(key));
        }
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void setProperty(String propertyName, Object value) {
        if (isMap) {
            ((Map) bean).put(propertyName, value);
        } else {
            // yjk 修改 属性名称全部大写
            PropertyDescriptor pd = BEANINFO_CACHE.get(propertyName.toUpperCase());
            if (null == pd || null == pd.getWriteMethod()) {
                throw new RuntimeException(
                    bean.getClass().getName() + "没有" + propertyName + "属性对应的写方法");
            }

            try {
                // yjk 判断新增
                if (null != value) {
                    pd.getWriteMethod().invoke(bean, new Object[]{value});
                }
            } catch (Exception e) {
                // yjk 新增
                System.out.println(pd);
                throw new RdfFileException(e, RdfErrorEnum.TYPE_CONVERTION_ERROR);
            }
        }
    }

    @SuppressWarnings("rawtypes")
    public Object getProperty(String propertyName) {
        if (isMap) {
            return ((Map) bean).get(propertyName);
        } else {
            PropertyDescriptor pd = BEANINFO_CACHE.get(propertyName);
            if (null == pd || null == pd.getReadMethod()) {
                throw new RuntimeException(
                    bean.getClass().getName() + "没有" + propertyName + "属性对应的读方法");
            }

            try {
                return pd.getReadMethod().invoke(bean, new Object[0]);
            } catch (Exception e) {
                throw new RdfFileException(e, RdfErrorEnum.TYPE_CONVERTION_ERROR);
            }
        }
    }

    public Object getBean() {
        return bean;
    }
}
