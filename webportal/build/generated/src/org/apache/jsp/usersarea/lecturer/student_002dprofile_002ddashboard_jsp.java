package org.apache.jsp.usersarea.lecturer;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class student_002dprofile_002ddashboard_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<link rel=\"stylesheet\" href=\"");
      out.print(request.getContextPath());
      out.write("/usersarea/css/custom-style.css\" />\n");
      out.write("\n");
      out.write("<div class=\"col-12\">\n");
      out.write("    <div> \n");
      out.write("        <div class=\"circular\" style=\"background: url('");
      out.print(request.getContextPath());
      out.write("/usersarea/img/avatar.png?sz=120') no-repeat;\">\n");
      out.write("        </div> \n");
      out.write("        <div class=\"profile-centered\">\n");
      out.write("            <div class=\"row\">\n");
      out.write("                <div class=\"span6 col-sm-6\" >\n");
      out.write("                    <span class=\"label-name\">Full Name</span>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"span6 col-sm-6\" >\n");
      out.write("                    <span class=\"label-value\">Ocheja Patrick Ileanwa</span>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"row\">\n");
      out.write("                <div class=\"span6 col-sm-6\" >\n");
      out.write("                    <span class=\"label-name\">Faculty</span>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"span6 col-sm-6\" >\n");
      out.write("                    <span class=\"label-value\">Engineering</span>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"row\">\n");
      out.write("                <div class=\"span6 col-sm-6\" >\n");
      out.write("                    <span class=\"label-name\">Matriculation Number</span>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"span6 col-sm-6\" >\n");
      out.write("                    <span class=\"label-value\">2009/163866</span>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"row\">\n");
      out.write("                <div class=\"span6 col-sm-6\" >\n");
      out.write("                    <span class=\"label-name\">Level</span>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"span6 col-sm-6\" >\n");
      out.write("                    <span class=\"label-value\">500</span>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("    <table class=\"table table-bordered table-striped table-hover\">\n");
      out.write("        <thead>\n");
      out.write("            <tr>\n");
      out.write("                <th>   </th>\n");
      out.write("                <th>Starts</th>\n");
      out.write("                <th>Ends</th>\n");
      out.write("                <th>Status</th>\n");
      out.write("            </tr>\n");
      out.write("        </thead>\n");
      out.write("        <tbody>\n");
      out.write("            <tr>\n");
      out.write("                <td>Clearance</td>\n");
      out.write("                <td>   </td>\n");
      out.write("                <td>   </td>\n");
      out.write("                <td>   </td>\n");
      out.write("            </tr>\n");
      out.write("            <tr>\n");
      out.write("                <td>Registration</td>\n");
      out.write("                <td>   </td>\n");
      out.write("                <td>   </td>\n");
      out.write("                <td>   </td>\n");
      out.write("            </tr>\n");
      out.write("            <tr>\n");
      out.write("                <td>Lecture</td>\n");
      out.write("                <td>   </td>\n");
      out.write("                <td>   </td>\n");
      out.write("                <td>   </td>\n");
      out.write("            </tr>\n");
      out.write("            <tr>\n");
      out.write("                <td>Examination</td>\n");
      out.write("                <td>   </td>\n");
      out.write("                <td>   </td>\n");
      out.write("                <td>   </td>\n");
      out.write("            </tr>\n");
      out.write("        </tbody>\n");
      out.write("    </table>\n");
      out.write("</div>\n");
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
