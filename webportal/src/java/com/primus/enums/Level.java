/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.enums;

/**
 *
 * @author STUDENT
 */
public enum Level {
    l100("100"),l200("200"),l300("300"),l400("400"),l500("500"),l600("600"),l700("700");
     public final String page;

        Level(String page) {
            this.page = page;
        }

        public String getPage() {
            return page;
        }
}


