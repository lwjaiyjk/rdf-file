package com.alipay.rdf.file.meta;

import java.io.Serializable;
import java.util.List;

/**
 * @author yujiakui
 * @version 1.0
 * Email: jkyu@haiyi-info.com
 * date: 2018/9/29 13:30
 * description：body 模板配置
 **/
public class BodyTemplateConfig implements Serializable{

    /**
     * serial id
     */
    private static final long serialVersionUID = -8950567270014570783L;

    /**
     * body模板名称
     */
    private String name;

    /**
     * 模板条件
     */
    private List<String> templateCnds;

    /**
     * 模板列信息
     */
    private List<String> templateColInfos;

    public List<String> getTemplateCnds() {
        return templateCnds;
    }

    public void setTemplateCnds(List<String> templateCnds) {
        this.templateCnds = templateCnds;
    }

    public List<String> getTemplateColInfos() {
        return templateColInfos;
    }

    public void setTemplateColInfos(List<String> templateColInfos) {
        this.templateColInfos = templateColInfos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
