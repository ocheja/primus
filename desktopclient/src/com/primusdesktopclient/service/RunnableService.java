/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primusdesktopclient.service;

import com.google.gson.Gson;
import com.primusdesktopclient.enums.Action;
import com.primusdesktopclient.enums.HardwareMessageType;
import com.primusdesktopclient.gsondata.HardwareMessage;
import com.primusdesktopclient.gsondata.ServerMessage;
import com.primusdesktopclient.ui.TestUI;
import com.primusdesktopclient.util.HardwareUtil;
import com.rapplogic.xbee.api.ApiId;
import com.rapplogic.xbee.api.AtCommand;
import com.rapplogic.xbee.api.XBee;
import com.rapplogic.xbee.api.XBeeAddress16;
import com.rapplogic.xbee.api.XBeeAddress64;
import com.rapplogic.xbee.api.XBeeException;
import com.rapplogic.xbee.api.XBeeResponse;
import com.rapplogic.xbee.api.XBeeTimeoutException;
import com.rapplogic.xbee.api.zigbee.ZNetRxResponse;
import com.rapplogic.xbee.api.zigbee.ZNetTxRequest;
import com.rapplogic.xbee.api.zigbee.ZNetTxStatusResponse;
import com.rapplogic.xbee.util.ByteUtils;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Olisa
 */
public class RunnableService implements Runnable {

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(RunnableService.class);
    private volatile boolean running = true;
    XBee xbee = new XBee();
    private Gson gson = new Gson();
    FingerPrintModuleService fingerPrintModuleService = new FingerPrintModuleService();

    public void terminate() {
        running = false;
    }

    @Override
    public void run() {
        try {
            xbee.open("COM34", 9600);
            while (running) {
                int count = 0;
                HardwareMessage hardwareMessage = XbeePacketReciever();
                System.out.println("mmmmmem: " + hardwareMessage);
                if (hardwareMessage != null) {
                    if (hardwareMessage.getMessageType().equalsIgnoreCase(HardwareMessageType.CHECKN.name())) {
                        ServerMessage lecture = new ServerMessage();
                        lecture.setLecturerFingerPrintId(Long.parseLong(hardwareMessage.getMessage().trim()));
                        lecture.setTime(new GregorianCalendar());
                        lecture.setLocation(hardwareMessage.getLocation());
                        lecture.setDepartmentName(fingerPrintModuleService.findFingerPrintModuleByAdd(hardwareMessage.getMacAdress()).getLocation().getDepartmentName().name());
                        lecture = (ServerMessage) gson.fromJson((String) EndPoint.sendMessage(lecture, Action.lectureNotification), ServerMessage.class);
                        if (!lecture.getAction().equals("Successful")) {
                            while (count < 6) {// if upload to srever fails try again 6 times
                                count++;
                                Thread.sleep((long) 5);
                                lecture = (ServerMessage) gson.fromJson((String) EndPoint.sendMessage(lecture, Action.lectureNotification), ServerMessage.class);
                                if (lecture.getAction().equals("Successful")) {
                                    count = 6;
                                }
                            }
                            if (!lecture.getAction().equals("Successful")) {
                                JOptionPane.showMessageDialog(null, "Connection to Server Failed(connection TimedOut)", "ATTENTION", 1);
                            }
                        }
                    }
                }
            }
            xbee.close();
        } catch (Exception ex) {
            Logger.getLogger(RunnableService.class.getName()).log(Level.SEVERE, null, ex);
            running = false;
        } //finally {
        //  xbee.close();
        // }

    }

    public void xbeePacketTransmitter(String meg, String destination64Add) {
        int[] payload = ByteUtils.stringToIntArray(meg);//{XBeePacket.SpecialByte.START_BYTE.getValue()};
        XBeeAddress64 destination = new XBeeAddress64(destination64Add);
        ZNetTxRequest znetRequest = new ZNetTxRequest(destination, payload);

        log.debug("zb request is " + znetRequest.getXBeePacket().getPacket());
        log.info("sending tx " + znetRequest);
        log.info("request packet bytes (base 16) " + ByteUtils.toBase16(znetRequest.getXBeePacket().getPacket()));

        long start = System.currentTimeMillis();
        try {
            ZNetTxStatusResponse znetResponse = (ZNetTxStatusResponse) xbee.sendSynchronous(znetRequest, 1000);

            znetRequest.setFrameId(xbee.getNextFrameId());
            log.info("received response " + znetResponse);
            if (znetResponse.getDeliveryStatus() == ZNetTxStatusResponse.DeliveryStatus.SUCCESS) {
                if (znetResponse.getRemoteAddress16().equals(XBeeAddress16.ZNET_BROADCAST)) {
                    znetRequest.setDestAddr16(znetResponse.getRemoteAddress16());
                }
            } else {
                log.error("packet failed due to error: " + znetResponse.getDeliveryStatus());
            }
            log.info("Response in " + (System.currentTimeMillis() - start) + ", Delivery status is " + znetResponse.getDeliveryStatus() + ", 16-bit address is " + ByteUtils.toBase16(znetResponse.getRemoteAddress16().getAddress()) + ", retry count is " + znetResponse.getRetryCount() + ", discovery status is " + znetResponse.getDeliveryStatus());
        } catch (XBeeTimeoutException e) {
            log.warn("request timed out");
        } catch (XBeeException ex) {
            Logger.getLogger(RunnableService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return the hardwareMessage Returns null if Response is not a
     * ZNET_RX_RESPONSE
     */
    public HardwareMessage XbeePacketReciever() {
        HardwareMessage hardwareMessage = null;
        try {
            System.out.println("Before recieving");
            XBeeResponse xbeeResponse = xbee.getResponse(1000);
            System.out.println("After recieving ");
            log.info("received response " + xbeeResponse.toString());
            if (xbeeResponse.getApiId() == ApiId.ZNET_RX_RESPONSE) {
                ZNetRxResponse rx = (ZNetRxResponse) xbeeResponse;

                log.info("Received RX packet, option is " + rx.getOption() + ", sender 64 address is " + ByteUtils.toBase16(rx.getRemoteAddress64().getAddress()) + ", remote 16-bit address is " + ByteUtils.toBase16(rx.getRemoteAddress16().getAddress()) + ", data is " + ByteUtils.toString(rx.getData()));
                System.out.println("Recieved data: " + ByteUtils.toString(rx.getData()));
                hardwareMessage = new HardwareMessage();
                hardwareMessage.setMacAdress(HardwareUtil.formatMacAddress((ByteUtils.toBase16(rx.getRemoteAddress64().getAddress()))));
                String meg = ByteUtils.toString(rx.getData()).trim();
                hardwareMessage.setMessageType(meg.substring(0, 6));
                hardwareMessage.setBatteryLevel((int)(Integer.parseInt(meg.substring(6, 8))/0.333333));
                String locationString = meg.substring(8, 28);
                if (locationString.contains("#")) {
                    hardwareMessage.setLocation(locationString.replaceAll("#", ""));
                } else {
                    hardwareMessage.setLocation(locationString);
                }
                hardwareMessage.setMessage(meg.substring(28));
                System.out.println("megType: " + hardwareMessage.getMessageType() + " BatLevel:  " + hardwareMessage.getBatteryLevel() + " MACadd: " + hardwareMessage.getMacAdress() + " message: " + hardwareMessage.getMessage() + " location: " + hardwareMessage.getLocation());
//              TestUI.logArea.append("\nmegType: " + hardwareMessage.getMessageType() + " BatLevel:  " + hardwareMessage.getBatteryLevel() + " MACadd: " + hardwareMessage.getMacAdress() + " message: " + hardwareMessage.getMessage() + " location: " + hardwareMessage.getLocation());

                FingerPrintModuleService moduleService = new FingerPrintModuleService();
                moduleService.updateModule(hardwareMessage);

                AtCommand at = new AtCommand("DB");
                xbee.sendAsynchronous(at);
                XBeeResponse atResponse = xbee.getResponse();
                if (atResponse.getApiId() == ApiId.AT_RESPONSE) {
                    log.info("RSSI of last response is " + -((com.rapplogic.xbee.api.AtCommandResponse) atResponse).getValue()[0]);
                } else {
                    log.info("expected RSSI, but received " + atResponse.toString());
                }
            } else {
                log.debug("received unexpected packet " + xbeeResponse.toString());
            }
        } catch (XBeeException | NumberFormatException e) {
            log.error(e);
        }
        return hardwareMessage;
    }
}
