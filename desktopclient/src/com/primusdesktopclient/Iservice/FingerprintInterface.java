/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primusdesktopclient.Iservice;

import com.primusdesktopclient.data.FingerPrintModule;
import com.primusdesktopclient.gsondata.Lecture;
import com.primusdesktopclient.gsondata.Lecturer;
import java.util.List;

/**
 *
 * @author Ocheja Patrick Ileanwa
 */
public interface FingerprintInterface {
   public boolean registerLecturer(String ScannerLocation,Long LecturerId);
    public Lecturer getLecturer(String searchParam) throws Exception;
    public List<String> getAllScanners();
    public List<Lecture> getLectures(String departmentName,String dayOfWeek);
    public List<FingerPrintModule> getFingerPrintModules();
    public void registerFingerprintModule(FingerPrintModule fingerPrintModule) throws Exception;
}
