package org.apache.jsp.usersarea.admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.primus.appstates.AdministratorState;

public final class createadmin_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\n");
      out.write("<input type=\"hidden\" id=\"nodeView\" name=\"nodeView\" value=\"create\">\n");
      out.write("<div class=\"row\">\n");
      out.write("    <div class=\"col-12\">\n");
      out.write("        <div class=\"widget-box\">\n");
      out.write("            <div class=\"widget-title\">\n");
      out.write("                <span class=\"icon\">\n");
      out.write("                    <i class=\"glyphicon glyphicon-align-justify\"></i>\t\t\t\t\t\t\t\t\t\n");
      out.write("                </span>\n");
      out.write("                <h5>New Administrator Form</h5>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"widget-content nopadding\">\n");
      out.write("                <form class=\"form-horizontal\" method=\"post\" enctype=\"multipart/form-data\" action=\"/Primus/admin\" name=\"create_admin_form\" id=\"create_admin_form\" novalidate=\"novalidate\">\n");
      out.write("                    <input type=\"hidden\" id=\"action\" name=\"action\" value=\"registerNewAdmin\">\n");
      out.write("                    <div class=\"row\">\n");
      out.write("                        <div class=\"col-12 col-sm-6 form-group\">\n");
      out.write("                            <label class=\"control-label\">First Name</label>\n");
      out.write("                            <div class=\"controls\">\n");
      out.write("                                <input type=\"text\" class=\" required form-control input-small\" name=\"firstName\" id=\"firstName\">\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"col-12 col-sm-6 form-group\">\n");
      out.write("                            <label class=\"control-label\">Surname</label>\n");
      out.write("                            <div class=\"controls\">\n");
      out.write("                                <input type=\"text\" class=\" required form-control input-small\" name=\"surname\" id=\"surname\">\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"row\">\n");
      out.write("                        <div class=\"col-12 col-sm-6 form-group\">\n");
      out.write("                            <label class=\"control-label\">Middle Name</label>\n");
      out.write("                            <div class=\"controls\">\n");
      out.write("                                <input type=\"text\" class=\"form-control input-small\" name=\"middleName\" id=\"middleName\">\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"col-12 col-sm-6 form-group\">\n");
      out.write("                            <label class=\"control-label\">Email</label>\n");
      out.write("                            <div class=\"controls\">\n");
      out.write("                                <input type=\"text\" class=\"form-control input-small\" name=\"email\" id=\"email\">\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"row\">\n");
      out.write("                        <div class=\"col-12 col-sm-6 form-group\">\n");
      out.write("                            <label class=\"control-label\">Password</label>\n");
      out.write("                            <div class=\"controls\">\n");
      out.write("                                <input type=\"password\" class=\"form-control input-small\" name=\"password\" id=\"password\">\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"col-12 col-sm-6 form-group\">\n");
      out.write("                            <label class=\"control-label\">Confirm password</label>\n");
      out.write("                            <div class=\"controls\">\n");
      out.write("                                <input type=\"password\" class=\"form-control input-small\" name=\"confirmPassword\" id=\"confirmPassword\">\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"row\">\n");
      out.write("                        <div class=\"col-12 col-sm-6 form-group\">\n");
      out.write("                            <label class=\"control-label\">Passport Photograph</label>\n");
      out.write("                            <div class=\"controls\">\n");
      out.write("                                <input type=\"file\" name=\"profileimage\" id=\"profileimage\">\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"col-12 col-sm-6 form-group\">\n");
      out.write("                            <label class=\"control-label\">Scan Finger Print</label>\n");
      out.write("                            <div class=\"controls\">\n");
      out.write("                                <input type=\"file\" name=\"fingerPrint\" id=\"fingerPrint\" disabled=\"disabled\">\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"row\">\n");
      out.write("                        <div class=\"col-12 col-sm-6 form-group\">\n");
      out.write("                            <label class=\"control-label\">Phone Number</label>\n");
      out.write("                            <div class=\"controls\">\n");
      out.write("                                <input type=\"text\" class=\"form-control input-small\" name=\"phoneNumber\" id=\"phoneNumber\">\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"col-12 col-sm-6 form-group\">\n");
      out.write("                            <label class=\"control-label\">Admin Type</label>\n");
      out.write("                            <div class=\"controls\">\n");
      out.write("                                <label><input type=\"radio\" name=\"adminType\"  class=\"required\" id=\"adminTypeSuper\" value=\"superAdmin\" /> Super Admin</label>\n");
      out.write("                                <label><input type=\"radio\" name=\"adminType\" id=\"adminTypeModerator\"  class=\"required\" value=\"moderatorAdmin\" /> Moderator</label>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"row\">\n");
      out.write("                        <div class=\"col-12 col-sm-6 form-actions\">\n");
      out.write("                            <input type=\"submit\" value=\"Create\" id=\"create_btn\" class=\"btn btn-success btn-max\">\n");
      out.write("                            <div id=\"loading_gif\" style=\"display: none\">\n");
      out.write("                                <img src=\"");
      out.print(request.getContextPath());
      out.write("/usersarea/img/notice_spinner.gif\"/>\n");
      out.write("                                <label id=\"message_on_end\">Loading...</label>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                </form>\n");
      out.write("            </div>\n");
      out.write("        </div>\t\t\t\n");
      out.write("    </div>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<div class=\"row\">\n");
      out.write("    <div class=\"col-12\">\n");
      out.write("        <div class=\"widget-box\">\n");
      out.write("            <div class=\"widget-title\">\n");
      out.write("                <span class=\"icon\">\n");
      out.write("                    <i class=\"glyphicon glyphicon-align-justify\"></i>\t\t\t\t\t\t\t\t\t\n");
      out.write("                </span>\n");
      out.write("                <h5>Batch Administrator Creation (EXCEL)</h5>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"widget-content nopadding\">\n");
      out.write("                <form class=\"form-horizontal\" method=\"post\" action=\"/Primus/admin\" name=\"create_batch_admin_form\" id=\"create_batch_admin_form\" novalidate=\"novalidate\">\n");
      out.write("\n");
      out.write("                    <div class=\"form-group\">\n");
      out.write("                        <label class=\"control-label\">Excel file</label>\n");
      out.write("                        <div class=\"controls\">\n");
      out.write("                            <input type=\"file\" name=\"excelFile\" id=\"excelFile\">\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"form-actions\">\n");
      out.write("                        <input type=\"submit\" value=\"Create\" id=\"create_btn\" class=\"btn btn-success btn-max\">\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                </form>\n");
      out.write("            </div>\n");
      out.write("        </div>\t\t\t\n");
      out.write("    </div>\n");
      out.write("</div>");
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
