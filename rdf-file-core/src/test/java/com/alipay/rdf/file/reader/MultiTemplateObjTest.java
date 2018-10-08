package com.alipay.rdf.file.reader;

import com.alipay.rdf.file.interfaces.FileFactory;
import com.alipay.rdf.file.interfaces.FileReader;
import com.alipay.rdf.file.interfaces.FileSplitter;
import com.alipay.rdf.file.model.FileConfig;
import com.alipay.rdf.file.model.FileSlice;
import com.alipay.rdf.file.model.StorageConfig;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yujiakui
 * @version 1.0
 * Email: jkyu@haiyi-info.com
 * date: 2018/10/8 10:31
 * description：
 **/
public class MultiTemplateObjTest {

    @Test
    public void testObj(){
        String filePath = File.class.getResource("/reader/sp/data/multi_template_data.txt").getPath();

        FileConfig config = new FileConfig(filePath, "/reader/sp/template/template5.json",
                new StorageConfig("nas"));

        FileSplitter splitter = FileFactory.createSplitter(config.getStorageConfig());

        FileSlice headSlice = splitter.getHeadSlice(config);

        FileConfig headConfig = config.clone();
        headConfig.setPartial(headSlice.getStart(), headSlice.getLength(),
                headConfig.getFileDataType());
        FileReader headReader = FileFactory.createReader(headConfig);
        try {
            SeHeaderInfo seHeaderInfo = headReader.readHead(SeHeaderInfo.class);
            System.out.println(seHeaderInfo);
        } finally {
            headReader.close();
        }

        FileSlice bodySlice = splitter.getBodySlice(config);
        FileConfig bodyConfig = config.clone();
        bodyConfig.setPartial(bodySlice.getStart(), bodySlice.getLength(),
                bodySlice.getFileDataType());
        FileReader bodyReader = FileFactory.createReader(bodyConfig);
        try {
            Map<String, Object> row = null;
            while (null != (row = bodyReader.readRow(HashMap.class))) {
                System.out.println(row);
            }
        } finally {
            bodyReader.close();
        }

        FileSlice tailSlice = splitter.getTailSlice(config);
        FileConfig tailConfig = config.clone();
        tailConfig.setPartial(tailSlice.getStart(), tailSlice.getLength(),
                tailSlice.getFileDataType());
        FileReader tailReader = FileFactory.createReader(tailConfig);
        try {
            Map<String, Object> tail = tailReader.readTail(HashMap.class);
            System.out.println(tail);
        } finally {
            tailReader.close();
        }
    }
}
