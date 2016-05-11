package org.apache.jsp.usersarea.admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.primus.appstates.AdministratorState;

public final class coursemanager_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("        <link rel=\"stylesheet\" href=\"");
      out.print(request.getContextPath());
      out.write("/usersarea/css/select2.css\" />\n");
      out.write("        <link rel=\"stylesheet\" href=\"");
      out.print(request.getContextPath());
      out.write("/usersarea/css/jquery-ui.css\" />\n");
      out.write("<!DOCTYPE html>\n");

    AdministratorState administratorState = AdministratorState.getInstance(request);
    
      out.write("\n");
      out.write("<input type=\"hidden\" id=\"parentView\" name=\"parentView\" value=\"course_mgr\">\n");
      out.write("<div id=\"content\">\n");
      out.write("            <div id=\"content-header\">\n");
      out.write("                <h1>Course Manager</h1>\n");
      out.write("            </div>\n");
      out.write("            <div id=\"breadcrumb\">\n");
      out.write("                <a href=\"");
      out.print(request.getContextPath());
      out.write("/#\" title=\"Go to Home\" class=\"tip-bottom\"><i class=\"glyphicon glyphicon-home\"></i> Course Manager</a>\n");
      out.write("                <a href=\"");
      out.print(request.getContextPath());
      out.write("/#\" class=\"current\">Home</a>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"container-fluid\">\n");
      out.write("        ");

            if(administratorState.getSuccessMessage()!=null){
        
      out.write("\n");
      out.write("        <div class=\"row\" style=\"margin-top:20px\">\n");
      out.write("            <div class=\"col-12\">\n");
      out.write("                <div class=\"alert alert-success\">\n");
      out.write("                    <button class=\"close\" data-dismiss=\"alert\">×</button>\n");
      out.write("                    <strong>Success!</strong> ");
      out.print( administratorState.getSuccessMessage());
      out.write("\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("        ");

                administratorState.setSuccessMessage(null);
                request.getSession().setAttribute(AdministratorState.class.getName(), administratorState);
            }
            else if(administratorState.getErrorMessage()!=null){
        
      out.write("\n");
      out.write("        <div class=\"row\" style=\"margin-top:20px\">\n");
      out.write("            <div class=\"col-12\">\n");
      out.write("                <div class=\"alert alert-error\">\n");
      out.write("                    <button class=\"close\" data-dismiss=\"alert\">×</button>\n");
      out.write("                    <strong>Error!</strong> ");
      out.print( administratorState.getErrorMessage());
      out.write("\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("        ");

                administratorState.setErrorMessage(null);
                request.getSession().setAttribute(AdministratorState.class.getName(), administratorState);
            }
        
      out.write("\n");
      out.write("                ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response,  administratorState.getAdminNodeViews().getPage(), out, true);
      out.write("\n");
      out.write("\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("        </div>\n");
      out.write("        <script src=\"");
      out.print(request.getContextPath());
      out.write("/usersarea/js/jquery.validate.js\"></script>\n");
      out.write("        <script src=\"");
      out.print(request.getContextPath());
      out.write("/usersarea/js/ajax-submission.js\"></script>\n");
      out.write("        <script src=\"");
      out.print(request.getContextPath());
      out.write("/usersarea/js/unicorn.form_validation.js\"></script>\n");
      out.write("        <script src=\"");
      out.print(request.getContextPath());
      out.write("/usersarea/js/select2.js\"></script>\n");
      out.write("        <script src=\"");
      out.print(request.getContextPath());
      out.write("/usersarea/js/additional-methods.js\"></script>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
