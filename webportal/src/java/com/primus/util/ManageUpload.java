package com.primus.util;

import com.primus.data.Administrator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.inject.Named;
import org.apache.commons.fileupload.FileItem;

@Named("manageUpload")
public class ManageUpload {

    Map<String, Object> fieldMap = new HashMap<>();
    private String fileName;
    private String state;
    private Integer studentId;

    public String getFileName() {
        return fileName;
    }

    public  String getState() {
        return state;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public  Map<String, Object> administratorDataExtractor(List<?> items) throws Exception {
        fieldMap = new HashMap<>();
        byte[] photo;
        Administrator administrator;
        Iterator<?> iterator = items.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            FileItem item = (FileItem) iterator.next();
            if (item.isFormField()) {
                String name = item.getFieldName();
                String value = item.getString();
                if (fieldMap.containsKey(name)) {
                    fieldMap.put(name + i, value);
                    i++;
                }else
                    fieldMap.put(name, value);
                    
            } else {
                String name = item.getFieldName();
                photo = FileUtil.fileToByteArray(item.getInputStream());
                fieldMap.put(name, photo);
            }
        }
        return fieldMap;
    }
}
