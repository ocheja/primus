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
import com.rapplogic.xbee.api.XBeeException;
import com.rapplogic.xbee.api.XBeeResponse;
import com.rapplogic.xbee.api.zigbee.ZNetRxResponse;
import com.rapplogic.xbee.util.ByteUtils;
import java.net.URL;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author Olisa
 */
public class XbeePacketReceiver {

    private static final Logger log = Logger.getLogger(XbeePacketReceiver.class);
    private long last = System.currentTimeMillis();

    public HardwareMessage Receive()
            throws Exception {
        HardwareMessage hardwareMessage = new HardwareMessage();
        XBee xbee = new XBee();
        try {
            xbee.open("COM34", 9600);
            try {
               for (;;) {
                    System.out.println("hello olisa ");
                    XBeeResponse response = xbee.getResponse();
                    System.out.println("hello olisa44444 ");
                  //  log.info("received response " + response.toString());
                    // ZNetRxResponse rx = (ZNetRxResponse)response;
                    //  log.info("Received RX packet, option is " + rx.getOption() + ", sender 64 address is " + ByteUtils.toBase16(rx.getRemoteAddress64().getAddress()) + ", remote 16-bit address is " + ByteUtils.toBase16(rx.getRemoteAddress16().getAddress()) + ", data is " + ByteUtils.toBase16(rx.getData()));
                    if (response.getApiId() == ApiId.ZNET_RX_RESPONSE) {
                        ZNetRxResponse rx = (ZNetRxResponse) response;

                       // log.info("Received RX packet, option is " + rx.getOption() + ", sender 64 address is " + ByteUtils.toBase16(rx.getRemoteAddress64().getAddress()) + ", remote 16-bit address is " + ByteUtils.toBase16(rx.getRemoteAddress16().getAddress()) + ", data is " + ByteUtils.toString(rx.getData()));
                        System.out.println("Recieved data: " + ByteUtils.toString(rx.getData()));
                        hardwareMessage.setMacAdress(HardwareUtil.formatMacAddress((ByteUtils.toBase16(rx.getRemoteAddress64().getAddress()))));
                        String meg = ByteUtils.toString(rx.getData()).trim();
                        hardwareMessage.setMessageType(meg.substring(0, 6));
                        hardwareMessage.setBatteryLevel(Integer.parseInt(meg.substring(6, 8)));
                        String locationString = meg.substring(8, 28);
                       if(locationString.contains("#")) {
                            hardwareMessage.setLocation(locationString.replaceAll("#", ""));
                       }else{
                        hardwareMessage.setLocation(locationString);
                       }
                        hardwareMessage.setMessage(meg.substring(28));
                        System.out.println("megType: "+ hardwareMessage.getMessageType()+" BatLevel:  "+ hardwareMessage.getBatteryLevel()+" MACadd: "+ hardwareMessage.getMacAdress() +" message: "+ hardwareMessage.getMessage()+" location: "+ hardwareMessage.getLocation());
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
                }
            } catch (XBeeException | NumberFormatException e) {
                log.error(e);
                
                System.err.println(e);
            }
        } finally {
            xbee.close();
        }
        return hardwareMessage;
    }

    public static void main(String[] args)
            throws Exception {
        URL url = ClassLoader.getSystemResource("com\\primusdesktopclient\\service\\log4j.properties");
        PropertyConfigurator.configure(url);
         new XbeePacketReceiver().Receive();
    }
}
