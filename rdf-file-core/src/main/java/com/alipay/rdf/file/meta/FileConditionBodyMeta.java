package com.alipay.rdf.file.meta;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author yujiakui
 * @version 1.0
 * Email: jkyu@haiyi-info.com
 * date: 2018/9/29 13:38
 * description：文件body带条件的列元数据
 **/
public class FileConditionBodyMeta implements Serializable{

    /**
     * serial id
     */
    private static final long serialVersionUID = -2839755091636122117L;

    /**
     * 模板名称
     */
    private String templateName;

    /**
     * 条件列元数据信息
     */
    private List<FileColumnMeta> condColMetas;

    /**
     * body 对应的列元数据集合
     */
    private List<FileColumnMeta> bodyColMetas;

    /**
     * 根据行信息，判断当前行是否满足此模板
     * @param colInfos
     * @return
     */
    public boolean satifyCnd(String[] colInfos){
        for(FileColumnMeta fileColumnMeta:condColMetas){
            if (fileColumnMeta.getColIndex() >= bodyColMetas.size() ||
                    !fileColumnMeta.getDefaultValue().equals(colInfos[fileColumnMeta.getColIndex()])){
                return false;
            }
        }
        return true;
    }

    /**
     * 根据行信息，判断当前行是否满足此模板
     * @param colInfos
     * @return
     */
    public boolean satifyCnd(Object[] colInfos){
        for(FileColumnMeta fileColumnMeta:condColMetas){
            if (fileColumnMeta.getColIndex() >= bodyColMetas.size() ||
                    !fileColumnMeta.getDefaultValue().equals(colInfos[fileColumnMeta.getColIndex()])){
                return false;
            }
        }
        return true;
    }

    /**
     * 根据行信息，判断当前行是否满足此模板
     * @param colInfoMap
     * @return
     */
    public boolean satifyCnd(Map<String,Object> colInfoMap){
        for(FileColumnMeta fileColumnMeta:condColMetas){
            if (!fileColumnMeta.getDefaultValue().equals(colInfoMap.get(fileColumnMeta.getName()))){
                return false;
            }
        }
        return true;
    }

    public List<FileColumnMeta> getCondColMetas() {
        return condColMetas;
    }

    public void setCondColMetas(List<FileColumnMeta> condColMetas) {
        this.condColMetas = condColMetas;
    }

    public List<FileColumnMeta> getBodyColMetas() {
        return bodyColMetas;
    }

    public void setBodyColMetas(List<FileColumnMeta> bodyColMetas) {
        this.bodyColMetas = bodyColMetas;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }
}
