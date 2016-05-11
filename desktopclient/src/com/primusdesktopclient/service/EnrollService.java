/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primusdesktopclient.service;

import com.primusdesktopclient.gsondata.HardwareMessage;
import com.primusdesktopclient.util.HardwareUtil;
import com.rapplogic.xbee.api.ApiId;
import com.rapplogic.xbee.api.AtCommand;
import com.rapplogic.xbee.api.XBee;
import com.rapplogic.xbee.api.XBeeAddress64;
import com.rapplogic.xbee.api.XBeeException;
import com.rapplogic.xbee.api.XBeeResponse;
import com.rapplogic.xbee.api.zigbee.ZNetRxResponse;
import com.rapplogic.xbee.api.zigbee.ZNetTxRequest;
import com.rapplogic.xbee.util.ByteUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author Olisa
 */
public class EnrollService {

    private static final Logger log = Logger.getLogger(EnrollService.class);

    public HardwareMessage enroll(String meg, String destination64Add) {
        HardwareMessage hardwareMessage = new HardwareMessage();

        XBee xbee = new XBee();
        try {
            xbee.open("COM34", 9600);
            int[] payload = ByteUtils.stringToIntArray(meg);//{XBeePacket.SpecialByte.START_BYTE.getValue()};
            XBeeAddress64 destination = new XBeeAddress64(destination64Add);
            ZNetTxRequest request = new ZNetTxRequest(destination, payload);
            xbee.sendAsynchronous(request);

            System.out.println("hello olisa ");
            XBeeResponse response = xbee.getResponse();
            System.out.println("hello olisa44444 ");
            if (response.getApiId() == ApiId.ZNET_TX_STATUS_RESPONSE) {
                System.out.println("hello olisa again ");
                response = xbee.getResponse();
            }

            if (response.getApiId() == ApiId.ZNET_RX_RESPONSE) {
                ZNetRxResponse rx = (ZNetRxResponse) response;
                System.out.println("Recieved data: " + ByteUtils.toString(rx.getData()));
                hardwareMessage.setMacAdress(HardwareUtil.formatMacAddress((ByteUtils.toBase16(rx.getRemoteAddress64().getAddress()))));
                String megData = ByteUtils.toString(rx.getData()).trim();
                 hardwareMessage.setMessageType(megData.substring(0, 6));
                        hardwareMessage.setBatteryLevel((int)(Integer.parseInt(megData.substring(6, 8))/0.33333333));
                        String locationString = megData.substring(8, 28);
                       if(locationString.contains("#")) {
                            hardwareMessage.setLocation(locationString.replaceAll("#", ""));
                       }else{
                        hardwareMessage.setLocation(locationString);
                       }
                        hardwareMessage.setMessage(megData.substring(28));
                FingerPrintModuleService moduleService = new FingerPrintModuleService();
                moduleService.updateModule(hardwareMessage);

                System.out.println("megType: " + hardwareMessage.getMessageType() + " BatLevel:  " + hardwareMessage.getBatteryLevel() + " MACadd: " + hardwareMessage.getMacAdress() + " message: " + hardwareMessage.getMessage() + " location: " + hardwareMessage.getLocation());
                AtCommand at = new AtCommand("DB");
                xbee.sendAsynchronous(at);
                XBeeResponse atResponse = xbee.getResponse();
                if (atResponse.getApiId() == ApiId.AT_RESPONSE) {
                    log.info("RSSI of last response is " + ((com.rapplogic.xbee.api.AtCommandResponse) atResponse).getValue()[0]);
                } else {
                    log.info("expected RSSI, but received " + atResponse.toString());
                }
            } else {
                log.debug("received unexpected packet " + response.toString());
            }
            //}
        } catch (XBeeException | NumberFormatException e) {
            log.error(e);
        } finally {
            xbee.close();
        }
        return hardwareMessage;
    }
}
