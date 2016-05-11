/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primusdesktopclient.service;

import com.rapplogic.xbee.api.XBee;
import com.rapplogic.xbee.api.XBeeAddress16;
import com.rapplogic.xbee.api.XBeeAddress64;
import com.rapplogic.xbee.api.XBeeTimeoutException;
import com.rapplogic.xbee.api.zigbee.ZNetTxRequest;
import com.rapplogic.xbee.api.zigbee.ZNetTxStatusResponse;
import com.rapplogic.xbee.util.ByteUtils;
import java.net.URL;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author Olisa
 */
public class XbeePacketTransmitter {

    private static final Logger log = Logger.getLogger(XbeePacketTransmitter.class);

    public void Transmit(String meg, String destination64Add)
            throws Exception {
        XBee xbee = new XBee();
        try {
            xbee.open("COM24", 9600);

            int[] payload = ByteUtils.stringToIntArray(meg);//{XBeePacket.SpecialByte.START_BYTE.getValue()};
            XBeeAddress64 destination = new XBeeAddress64(destination64Add);
            ZNetTxRequest request = new ZNetTxRequest(destination, payload);
            log.debug("zb request is " + request.getXBeePacket().getPacket());
            log.info("sending tx " + request);
            log.info("request packet bytes (base 16) " + ByteUtils.toBase16(request.getXBeePacket().getPacket()));
            long start = System.currentTimeMillis();
            try {
                ZNetTxStatusResponse response = (ZNetTxStatusResponse) xbee.sendSynchronous(request, 10000);

                request.setFrameId(xbee.getNextFrameId());
                log.info("received response " + response);
                if (response.getDeliveryStatus() == ZNetTxStatusResponse.DeliveryStatus.SUCCESS) {
                    if (response.getRemoteAddress16().equals(XBeeAddress16.ZNET_BROADCAST)) {
                        request.setDestAddr16(response.getRemoteAddress16());
                    }
                } else {
                    log.error("packet failed due to error: " + response.getDeliveryStatus());
                }
                log.info("Response in " + (System.currentTimeMillis() - start) + ", Delivery status is " + response.getDeliveryStatus() + ", 16-bit address is " + ByteUtils.toBase16(response.getRemoteAddress16().getAddress()) + ", retry count is " + response.getRetryCount() + ", discovery status is " + response.getDeliveryStatus());
            } catch (XBeeTimeoutException e) {
                log.warn("request timed out");
            }

        } finally {
            xbee.close();

        }

    }

    public static void main(String[] args)
            throws Exception {
        URL url = ClassLoader.getSystemResource("com\\primusdesktopclient\\service\\log4j.properties");
        PropertyConfigurator.configure(url);
        //0x00,0x13,0xa2,0x00,0x40,0xb9,0x70,0x96
        String add = "00 13 a2 00 40 b9 70 96";
        new XbeePacketTransmitter().Transmit("ENROLL", add);
    }
}
