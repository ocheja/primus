/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.util;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

/**
 *
 * @author Ocheja Patrick Ileanwa
 */
public class CustomEntityAdapter<T> extends TypeAdapter<T>{

    @Override
    public void write(JsonWriter out, T value) throws IOException {
        if (value == null) {
            out.nullValue();
            return;
        }
        out.value(value.toString());//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public T read(JsonReader in) throws IOException {
        return null;//To change body of generated methods, choose Tools | Templates.
    }

    
}
