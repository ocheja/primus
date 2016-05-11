package org.apache.jsp.usersarea.admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class register_002dcourse_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\n");
      out.write("<link rel=\"stylesheet\" href=\"css/icheck/flat/blue.css\" />\n");
      out.write("<div id=\"content\">\n");
      out.write("    <div id=\"content-header\">\n");
      out.write("        <h1>Course Form wizard</h1>\n");
      out.write("        <div class=\"btn-group\">\n");
      out.write("            <a class=\"btn\" title=\"Manage Files\"><i class=\"glyphicon glyphicon-file\"></i></a>\n");
      out.write("            <a class=\"btn\" title=\"Manage Users\"><i class=\"glyphicon glyphicon-user\"></i></a>\n");
      out.write("            <a class=\"btn\" title=\"Manage Comments\"><i class=\"glyphicon glyphicon-comment\"></i><span class=\"label label-danger\">5</span></a>\n");
      out.write("            <a class=\"btn\" title=\"Manage Orders\"><i class=\"glyphicon glyphicon-shopping-cart\"></i></a>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("    <div id=\"breadcrumb\">\n");
      out.write("        <a href=\"#\" title=\"Go to Home\" class=\"tip-bottom\"><i class=\"glyphicon glyphicon-home\"></i> Home</a>\n");
      out.write("        <a href=\"#\">Form elements</a>\n");
      out.write("        <a href=\"#\" class=\"current\">Form wizard</a>\n");
      out.write("    </div>\n");
      out.write("    <div class=\"row\">\n");
      out.write("        <div class=\"col-12\">\n");
      out.write("            <div class=\"widget-box\">\n");
      out.write("                <div class=\"widget-title\">\n");
      out.write("                    <span class=\"icon\">\n");
      out.write("                        <i class=\"glyphicon glyphicon-pencil\"></i>\n");
      out.write("                    </span>\n");
      out.write("                    <h5>Form wizard with validation</h5>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"widget-content nopadding\">\n");
      out.write("                    <form id=\"form-wizard\" class=\"form-horizontal\" method=\"post\">\n");
      out.write("                        <div id=\"form-wizard-1\" class=\"step\">\n");
      out.write("                            <div class=\"form-group\">\n");
      out.write("                                <label class=\"control-label\">Username</label>\n");
      out.write("                                <div class=\"controls\">\n");
      out.write("                                    <input class=\"form-control input-small\" id=\"username\" type=\"text\" name=\"username\" />\n");
      out.write("                                </div>\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"form-group\">\n");
      out.write("                                <label class=\"control-label\">Password</label>\n");
      out.write("                                <div class=\"controls\">\n");
      out.write("                                    <input class=\"form-control input-small\" id=\"password\" type=\"password\" name=\"password\" />\n");
      out.write("                                </div>\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"form-group\">\n");
      out.write("                                <label class=\"control-label\">Confirm Password</label>\n");
      out.write("                                <div class=\"controls\">\n");
      out.write("                                    <input class=\"form-control input-small\" id=\"password2\" type=\"password\" name=\"password2\" />\n");
      out.write("                                </div>\n");
      out.write("                            </div>\n");
      out.write("\n");
      out.write("                        </div>\n");
      out.write("                        <div id=\"form-wizard-2\" class=\"step\">\n");
      out.write("                            <div class=\"form-group\">\n");
      out.write("                                <label class=\"control-label\">Email</label>\n");
      out.write("                                <div class=\"controls\">\n");
      out.write("                                    <input class=\"form-control input-small\" id=\"email\" type=\"text\" name=\"email\" />\n");
      out.write("                                </div>\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"form-group\">\n");
      out.write("                                <label class=\"control-label\">EULA Acceptation</label>\n");
      out.write("                                <div class=\"controls\">\n");
      out.write("                                    <input id=\"eula\" type=\"checkbox\" name=\"eula\" />\n");
      out.write("                                </div>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"form-actions\">\n");
      out.write("                            <input id=\"back\" class=\"btn btn-primary\" type=\"reset\" value=\"Back\" />\n");
      out.write("                            <input id=\"next\" class=\"btn btn-primary\" type=\"submit\" value=\"Next\" />\n");
      out.write("                            <div id=\"status\"></div>\n");
      out.write("                        </div>\n");
      out.write("                        <div id=\"submitted\"></div>\n");
      out.write("                    </form>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("\n");
      out.write("<script src=\"js/jquery.icheck.min.js\"></script>\n");
      out.write("<script src=\"js/jquery.validate.js\"></script>\n");
      out.write("<script src=\"js/jquery.wizard.js\"></script>\n");
      out.write("<script src=\"js/unicorn.wizard.js\"></script>");
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
