/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primusdesktopclient.service;

/**
 *
 * @author Olisa
 */
import com.google.gson.Gson;
import com.primusdesktopclient.enums.Action;
import com.primusdesktopclient.gsondata.ServerMessage;
import org.apache.http.*;
import org.apache.http.client.*;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.client.methods.*;  //HttpHead, HttpPut, HttpGet, etc...
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

public class EndPoint {

    public static Object sendMessage(Object obj,Action action) throws Exception {
        Gson gson = new Gson();
        URIBuilder builder = new URIBuilder("http://localhost:8080/Primus/DesktopClientServlet")
                .addParameter("action",action.name());
        
        StringEntity stringEntity = new StringEntity(gson.toJson(obj).toString());
         stringEntity.setContentType("application/json;charset=UTF-8");
         stringEntity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,"application/json;charset=UTF-8"));
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost request = new HttpPost(builder.build());
        // request.setHeader("Accept", "application/json");
         request.setEntity(stringEntity);
      //  HttpGet httpget = new HttpGet(builder.build());
      request.setHeader("Content-type", "application/json");

        HttpResponse response = httpclient.execute( request);
        System.out.println(response.getStatusLine().toString());
        HttpEntity entity = response.getEntity();
        System.out.println();
        String gsonString = EntityUtils.toString(entity).trim();
      // ServerMessage serverMessage = gson.fromJson(gsonString, ServerMessage.class);
      // System.out.println(serverMessage.getEndTime() + "  :  " + serverMessage.getStartTime() + "  :  " + serverMessage.getMessage());
    return gsonString;
    }

    public static void main(String[] args) {
        ServerMessage serverMessage = new ServerMessage();
        serverMessage.setMessage("hi user");
        
        try {
            sendMessage(serverMessage,Action.lectureNotification);
        } catch (Exception ioe) {
            System.out.println(ioe);
        }
    }
}
