/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.appstates;

import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ocheja Patrick Ileanwa
 */

public class ApplicationState implements Serializable {
    
    private Object currentUserState;
    private View view;
    
    public static ApplicationState getInstance(HttpServletRequest httpServletRequest){
        HttpSession httpSession = httpServletRequest.getSession();
        ApplicationState applicationState = (ApplicationState) httpSession.getAttribute(ApplicationState.class.getName());
        
        if(applicationState == null){
            applicationState = new ApplicationState();
            httpSession.setAttribute(ApplicationState.class.getName(), applicationState);
        }
        return applicationState;
    }

    /**
     * @return the currentUserState
     */
    public Object getCurrentUserState() {
        return currentUserState;
    }

    /**
     * @param currentUserState the currentUserState to set
     */
    public void setCurrentUserState(Object currentUserState) {
        this.currentUserState = currentUserState;
    }

    /**
     * @return the view
     */
    public View getView() {
        return view;
    }

    /**
     * @param view the view to set
     */
    public void setView(View view) {
        this.view = view;
    }
    
    public static enum View{
        GENERAL_HOMEPAGE("/html/login.jsp"), USER_AREA(
				"/userarea/index.jsp"), ERROR_PAGE(
				"/error.jsp"), INFO_PAGE(
				"/info.jsp");
        private final String page;
        
        View(String page){
            this.page  = page;
        }
        public String getPage(){
            return page;
        }
    }
}
