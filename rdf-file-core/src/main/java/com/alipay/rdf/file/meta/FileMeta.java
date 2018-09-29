package com.alipay.rdf.file.meta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alipay.rdf.file.exception.RdfErrorEnum;
import com.alipay.rdf.file.exception.RdfFileException;
import com.alipay.rdf.file.interfaces.RowValidator;
import com.alipay.rdf.file.model.FileDataTypeEnum;
import com.alipay.rdf.file.util.RdfFileUtil;

/**
 * Copyright (C) 2013-2018 Ant Financial Services Group
 * <p>
 * 文件元数据
 *
 * @author hongwei.quhw
 * @version $Id: FileMeta.java, v 0.1 2016-12-20 下午4:08:31 hongwei.quhw Exp $
 */
public class FileMeta {
    /**
     * 文件总笔数
     */
    public static final String DEFAULT_TOTALCOUNTKEY = "totalCount";
    /**
     * 协议
     */
    private String protocol;
    /**
     * 头部字段
     */
    private final List<FileColumnMeta> headColumns = new ArrayList<FileColumnMeta>();
    /**
     * 行记录字段
     */
    private final List<FileConditionBodyMeta> bodyCondMetaColumns = new ArrayList<FileConditionBodyMeta>();

    /**
     * body 模板名称元数据映射，其中key为模板名称
     */
    private final Map<String, FileConditionBodyMeta> bodyTemplateNameMetaMap = new HashMap<String, FileConditionBodyMeta>();

    /**
     * 尾部字段
     */
    private final List<FileColumnMeta> tailColumns = new ArrayList<FileColumnMeta>();

    /**
     * 模板路径
     */
    private String templatePath;

    private final List<SummaryPairMeta> summaryPairs = new ArrayList<SummaryPairMeta>();

    private final List<RowValidator> validators = new ArrayList<RowValidator>();

    private String lineBreak;

    private String columnSplit;
    /**
     * 文件编码
     */
    private String fileEncoding;

    private String totalCountKey = DEFAULT_TOTALCOUNTKEY;

    private Map<FileDataTypeEnum, Boolean> startWithSplit = new HashMap<FileDataTypeEnum, Boolean>();

    private Map<FileDataTypeEnum, Boolean> endWithSplit = new HashMap<FileDataTypeEnum, Boolean>();

    public boolean isStartWithSplit(FileDataTypeEnum rowType) {
        Boolean startSplit = startWithSplit.get(rowType);

        if (null == startSplit) {
            return false;
        }

        return startSplit;
    }

    public boolean isEndWithSplit(FileDataTypeEnum rowType) {
        Boolean endSplit = endWithSplit.get(rowType);

        if (null == endSplit) {
            return false;
        }

        return endSplit;
    }

    public void setStartWithSplit(FileDataTypeEnum rowType, boolean startSplit) {
        startWithSplit.put(rowType, startSplit);
    }

    public void setEndWithSplit(FileDataTypeEnum rowType, boolean endSplit) {
        endWithSplit.put(rowType, endSplit);
    }

    /**
     * 获取当前对应的body模板，根据列的值
     * @param colInfos
     * @return
     */
    public List<FileColumnMeta> getCurBodyTemplateColMetas(String[] colInfos){

        for (FileConditionBodyMeta fileConditionBodyMeta:bodyCondMetaColumns){
            if(fileConditionBodyMeta.satifyCnd(colInfos)){
                return fileConditionBodyMeta.getBodyColMetas();
            }
        }

        throw new RdfFileException("没有对应的行body模板=" + colInfos,
                RdfErrorEnum.UNSUPPORTED_OPERATION);
    }

    /**
     * 获取当前对应的body模板，根据列的值
     * @param colInfos
     * @return
     */
    public FileConditionBodyMeta getCurBodyTemplateMetas(String[] colInfos){

        for (FileConditionBodyMeta fileConditionBodyMeta:bodyCondMetaColumns){
            if(fileConditionBodyMeta.satifyCnd(colInfos)){
                return fileConditionBodyMeta;
            }
        }

        throw new RdfFileException("没有对应的行body模板=" + colInfos,
                RdfErrorEnum.UNSUPPORTED_OPERATION);
    }

    /**
     * 获取当前对应的body模板，根据列的值
     * @param colInfos
     * @return
     */
    public List<FileColumnMeta> getCurBodyTemplateColMetas(Object[] colInfos){

        for (FileConditionBodyMeta fileConditionBodyMeta:bodyCondMetaColumns){
            if(fileConditionBodyMeta.satifyCnd(colInfos)){
                return fileConditionBodyMeta.getBodyColMetas();
            }
        }

        throw new RdfFileException("没有对应的行body模板=" + colInfos,
                RdfErrorEnum.UNSUPPORTED_OPERATION);
    }

    /**
     * 获取当前对应的body模板，根据列的值
     * @param colInfos
     * @return
     */
    public FileConditionBodyMeta getCurBodyTemplateMetas(Object[] colInfos){

        for (FileConditionBodyMeta fileConditionBodyMeta:bodyCondMetaColumns){
            if(fileConditionBodyMeta.satifyCnd(colInfos)){
                return fileConditionBodyMeta;
            }
        }

        throw new RdfFileException("没有对应的行body模板=" + colInfos,
                RdfErrorEnum.UNSUPPORTED_OPERATION);
    }

    /**
     * 获取当前对应的body模板，根据列的值
     * @param colInfoMap
     * @return
     */
    public List<FileColumnMeta> getCurBodyTemplateColMetas(Map<String,Object> colInfoMap){

        for (FileConditionBodyMeta fileConditionBodyMeta:bodyCondMetaColumns){
            if(fileConditionBodyMeta.satifyCnd(colInfoMap)){
                return fileConditionBodyMeta.getBodyColMetas();
            }
        }

        throw new RdfFileException("没有对应的行body模板=" + colInfoMap,
                RdfErrorEnum.UNSUPPORTED_OPERATION);
    }

    /**
     * 获取当前对应的body模板，根据列的值
     * @param colInfoMap
     * @return
     */
    public FileConditionBodyMeta getCurBodyTemplateMetas(Map<String,Object> colInfoMap){

        for (FileConditionBodyMeta fileConditionBodyMeta:bodyCondMetaColumns){
            if(fileConditionBodyMeta.satifyCnd(colInfoMap)){
                return fileConditionBodyMeta;
            }
        }

        throw new RdfFileException("没有对应的行body模板=" + colInfoMap,
                RdfErrorEnum.UNSUPPORTED_OPERATION);
    }

    public boolean hasColumns(FileDataTypeEnum rowType) {
        switch (rowType) {
            case HEAD:
                return hasHead();
            case BODY:
                return hasBody();
            case TAIL:
                return hasTail();
            default:
                throw new RdfFileException("不支持类似rowType=" + rowType.name(),
                        RdfErrorEnum.UNSUPPORTED_OPERATION);
        }
    }

    public List<FileColumnMeta> getColumns(FileDataTypeEnum rowType, String bodyTempalteName) {
        switch (rowType) {
            case HEAD:
                return getHeadColumns();
            case BODY:
                return getBodyColumns(bodyTempalteName);
            case TAIL:
                return getTailColumns();
            default:
                throw new RdfFileException("不支持类似rowType=" + rowType.name(),
                        RdfErrorEnum.UNSUPPORTED_OPERATION);
        }
    }

    /**
     * 文件是否有头
     *
     * @return
     */
    public boolean hasHead() {
        return headColumns.size() > 0;
    }

    /**
     * 文件是否有文件体
     *
     * @return
     */
    public boolean hasBody() {
        return bodyCondMetaColumns.size() > 0;
    }

    /**
     * 文件是否有文件尾
     *
     * @return
     */
    public boolean hasTail() {
        return tailColumns.size() > 0;
    }

    /**
     * 增加文件头
     *
     * @param column
     */
    public void addHeadColumn(FileColumnMeta column) {
        headColumns.add(column);
    }

    public FileColumnMeta getHeadColumn(String name) {
        for (FileColumnMeta colMeta : headColumns) {
            if (colMeta.getName().equals(name)) {
                return colMeta;
            }
        }

        throw new RdfFileException("rdf-file#FileMeta.getHeadColumn(name=" + name + ") 有没有定义",
                RdfErrorEnum.COLUMN_NOT_DEFINED);
    }

    /**
     * 增加文件体
     *
     * @param column
     */
    public void addBodyColumn(FileConditionBodyMeta column) {
        bodyCondMetaColumns.add(column);
    }

    /**
     * 获取body 对应的列列表
     *
     * @param bodyTemplateName
     * @return
     */
    public List<FileColumnMeta> getBodyColumns(String bodyTemplateName) {
        FileConditionBodyMeta fileConditionBodyMeta = bodyTemplateNameMetaMap.get(bodyTemplateName);
        if (null != fileConditionBodyMeta) {
            return fileConditionBodyMeta.getBodyColMetas();
        }
        throw new RdfFileException("rdf-file#FileMeta.getBodyColumns(bodyTemplateName=" + bodyTemplateName + ") 有没有定义",
                RdfErrorEnum.COLUMN_NOT_DEFINED);
    }

    public FileColumnMeta getBodyColumn(String bodyTemplateName, String name) {

        for (FileColumnMeta colMeta : getBodyColumns(bodyTemplateName)) {
            if (colMeta.getName().equals(name)) {
                return colMeta;
            }
        }

        throw new RdfFileException("rdf-file#FileMeta.getBodyColumn(name=" + name + ") 有没有定义",
                RdfErrorEnum.COLUMN_NOT_DEFINED);
    }

    /**
     * 增加文件尾
     *
     * @param column
     */
    public void addTailColumn(FileColumnMeta column) {
        tailColumns.add(column);
    }

    public FileColumnMeta getTailColumn(String name) {
        for (FileColumnMeta colMeta : tailColumns) {
            if (colMeta.getName().equals(name)) {
                return colMeta;
            }
        }

        throw new RdfFileException("rdf-file#FileMeta.getTailColumn(name=" + name + ") 有没有定义",
                RdfErrorEnum.COLUMN_NOT_DEFINED);
    }

    public List<FileConditionBodyMeta> getBodyCondMetaColumns() {
        return bodyCondMetaColumns;
    }

    /**
     * Getter method for property <tt>columnSplit</tt>.
     *
     * @return property value of columnSplit
     */
    public String getColumnSplit() {
        return columnSplit;
    }

    /**
     * Setter method for property <tt>columnSplit</tt>.
     *
     * @param columnSplit value to be assigned to property columnSplit
     */
    public void setColumnSplit(String columnSplit) {
        this.columnSplit = columnSplit;
    }

    /**
     * Getter method for property <tt>headColumns</tt>.
     *
     * @return property value of headColumns
     */
    public List<FileColumnMeta> getHeadColumns() {
        return headColumns;
    }


    /**
     * Getter method for property <tt>tailColumns</tt>.
     *
     * @return property value of tailColumns
     */
    public List<FileColumnMeta> getTailColumns() {
        return tailColumns;
    }

    /**
     * Getter method for property <tt>templatePath</tt>.
     *
     * @return property value of templatePath
     */
    public String getTemplatePath() {
        return templatePath;
    }

    /**
     * Setter method for property <tt>templatePath</tt>.
     *
     * @param templatePath value to be assigned to property templatePath
     */
    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }

    /**
     * Getter method for property <tt>encoding</tt>.
     *
     * @return property value of encoding
     */
    public String getFileEncoding() {
        return fileEncoding;
    }

    /**
     * Setter method for property <tt>encoding</tt>.
     *
     * @param fileEncoding value to be assigned to property encoding
     */
    public void setFileEncoding(String fileEncoding) {
        this.fileEncoding = fileEncoding;
    }

    /**
     * Getter method for property <tt>totalCountKey</tt>.
     *
     * @return property value of totalCountKey
     */
    public String getTotalCountKey() {
        return totalCountKey;
    }

    /**
     * Setter method for property <tt>totalCountKey</tt>.
     *
     * @param totalCountKey value to be assigned to property totalCountKey
     */
    public void setTotalCountKey(String totalCountKey) {
        this.totalCountKey = totalCountKey;
    }

    /**
     * Getter method for property <tt>summaryPairs</tt>.
     *
     * @return property value of summaryPairs
     */
    public List<SummaryPairMeta> getSummaryPairMetas() {
        return summaryPairs;
    }

    /**
     * 增加合计列参数
     *
     * @param pair
     */
    public void addSummaryColumnPair(SummaryPairMeta pair) {
        this.summaryPairs.add(pair);
    }

    /**
     * Setter method for property <tt>protocol</tt>.
     *
     * @param protocol value to be assigned to property protocol
     */
    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    /**
     * Getter method for property <tt>protocol</tt>.
     *
     * @return property value of protocol
     */
    public String getProtocol() {
        return protocol;
    }

    /**
     * Getter method for property <tt>validators</tt>.
     *
     * @return property value of validators
     */
    public List<RowValidator> getValidators() {
        return validators;
    }

    public void addRowValidator(RowValidator rowValidator) {
        validators.add(rowValidator);
    }

    public String getLineBreak() {
        return lineBreak;
    }

    public void setLineBreak(String lineBreak) {
        if ("\r\n".equals(lineBreak) || "\n".equals(lineBreak) || "\r".equals(lineBreak)) {
            this.lineBreak = lineBreak;
        } else if (RdfFileUtil.isNotBlank(lineBreak)) {
            throw new RdfFileException("rdf-file# 不支持换行符 lineBreak=" + lineBreak,
                    RdfErrorEnum.UNSUPPORT_LINEBREAK);
        }
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("FileMeta[");
        sb.append("protocol=" + protocol);
        sb.append(",templatePath=" + templatePath);
        sb.append("]");
        return sb.toString();
    }

    public Map<String, FileConditionBodyMeta> getBodyTemplateNameMetaMap() {
        return bodyTemplateNameMetaMap;
    }
}
