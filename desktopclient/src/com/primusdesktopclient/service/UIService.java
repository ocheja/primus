/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primusdesktopclient.service;

import com.google.gson.Gson;
import com.primusdesktopclient.Iservice.FingerprintInterface;
import com.primusdesktopclient.data.FingerPrintModule;
import com.primusdesktopclient.gsondata.Lecture;
import com.primusdesktopclient.enums.Action;
import com.primusdesktopclient.enums.HardwareMessageType;
import com.primusdesktopclient.gsondata.HardwareMessage;
import com.primusdesktopclient.gsondata.LectureList;
import com.primusdesktopclient.gsondata.Lecturer;
import com.primusdesktopclient.util.HardwareUtil;
import com.primusdesktopclient.util.RunnableRequest;
import com.primusdesktopclient.util.RunnableResponse;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Olisa
 */
public class UIService implements FingerprintInterface {

    Gson gson = new Gson();
    FingerPrintModuleService fingerPrintModuleService = new FingerPrintModuleService();
    // XbeePacketTransmitter xbeePacketTransmitter = new XbeePacketTransmitter();
    public RunnableService runnableService = new RunnableService();
    private Thread thread;

    @Override
    public boolean registerLecturer(String ScannerLocation, Long LecturerId) {
        boolean status = false;
        FingerPrintModule fingerPrintModule = fingerPrintModuleService.findFingerPrintModule(ScannerLocation);
        HardwareMessage hardwareMessage = null;
        Lecturer lecturer = new Lecturer();
        EnrollService enrollService = new EnrollService();
        try {
            hardwareMessage = enrollService.enroll(HardwareMessageType.ENROLL.name(), fingerPrintModule.getMacAddress());

            if (hardwareMessage.getMessageType().equalsIgnoreCase(HardwareMessageType.ENROLL.name())) {
                lecturer.setLecturerID(String.valueOf(LecturerId));
                lecturer.setFingerPrintID(hardwareMessage.getMessage());
                lecturer.setLocation(hardwareMessage.getLocation());
                lecturer = (Lecturer) gson.fromJson((String) EndPoint.sendMessage(lecturer, Action.editLecturer), Lecturer.class);
                if (lecturer.getAction().equals("Successful")) {
                    status = true;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(UIService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }

    private int[] getDHandDL(Long Address) {
        String macAddress = Long.toString(Address);
        int add[] = {0, 0};
        add[0] = Integer.parseInt(macAddress.substring(8, 15));
        add[1] = Integer.parseInt(macAddress.substring(0, 7));
        return add;
    }

    @Override
    public Lecturer getLecturer(String searchParam) throws Exception {
        Object obj;
        Lecturer lecturer = new Lecturer();
        lecturer.setEmailAddress(searchParam);
        lecturer.setName(searchParam);
        obj = EndPoint.sendMessage(lecturer, Action.getLecturer);
        lecturer = gson.fromJson((String) obj, Lecturer.class);
        return lecturer;
    }

    @Override
    public List<String> getAllScanners() {
        List<String> scannerAddress = new ArrayList<>();
        List<FingerPrintModule> scanners = fingerPrintModuleService.findFingerPrintModuleEntities();
        for (FingerPrintModule scanner : scanners) {
            scannerAddress.add(scanner.getLocation().getAddress());
        }
        return scannerAddress;
    }

    @Override
    public List<Lecture> getLectures(String departmentName, String dayOfWeek) {
        Lecture lecture = new Lecture();
        lecture.setDayOfweek(dayOfWeek);
        lecture.setDepartmentName(departmentName);
        lecture.setLectureNoticeTime(new GregorianCalendar());
        LectureList lectures = new LectureList();
        try {
            lectures = (LectureList) gson.fromJson((String) EndPoint.sendMessage(lecture, Action.getLecturesForDay), LectureList.class);
            if (lectures.getAction().equals("Successful")) {
                return lectures.getLectures();
            }
     } catch (Exception ex) {
            Logger.getLogger(UIService.class.getName()).log(Level.SEVERE, null, ex);
        }
   //     System.out.println("lectures  :   " +lectures.getLectures());
        return lectures.getLectures();

    }

    @Override
    public List<FingerPrintModule> getFingerPrintModules() {
        return fingerPrintModuleService.findFingerPrintModuleEntities();
    }

    @Override
    public void registerFingerprintModule(FingerPrintModule fingerPrintModule) throws Exception {
        fingerPrintModule.setMacAddress(HardwareUtil.formatMacAddress(fingerPrintModule.getMacAddress()));
        fingerPrintModuleService.registerFingerprintModule(fingerPrintModule);
    }

    public static void start() {
        RunnableRequest request = new RunnableRequest();
        RunnableResponse response = new RunnableResponse();
        UIService uiService = new UIService();
        uiService.runnableService = new RunnableService();
        uiService.thread = new Thread(uiService.runnableService);
        System.out.println("Starting thread: " + uiService.thread);
        uiService.thread.start();
        //fingerPrintModuleService.registerFingerprintModule(fingerPrintModule);
    }
}
