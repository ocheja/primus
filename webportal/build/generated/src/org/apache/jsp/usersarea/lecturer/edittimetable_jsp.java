package org.apache.jsp.usersarea.lecturer;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.primus.appstates.LecturerState;

public final class edittimetable_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.print(request.getContextPath());
      out.write("/usersarea/css/icheck/flat/green.css\" />\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<div class=\"row\" id=\"courseform-arena\">\n");
      out.write("    <div class=\"col-12\">\n");
      out.write("        <div class=\"widget-box\">\n");
      out.write("            <div class=\"widget-content nopadding\">\n");
      out.write("                <a class=\"btn btn-success btn-mini\" href=\"");
      out.print( request.getContextPath());
      out.write("/view?parent=");
      out.print(LecturerState.LecturerParentViews.ADD_NEW_TIMETABLE.name());
      out.write("\" id=\"addTimetable\" >Add To Timetable</a>\n");
      out.write("                <a class=\"btn btn-success btn-mini\" href=\"");
      out.print( request.getContextPath());
      out.write("/view?parent=");
      out.print(LecturerState.LecturerParentViews.VIEW_TIMETABLE.name());
      out.write("\" id=\"editTimetable\">View Timetable</a>\n");
      out.write("                \n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("</div>\n");
      out.write("<div id=\"addrequiredcoursesdiv\">\n");
      out.write("\n");
      out.write("</div>\n");
      out.write("<a href=\"#addRequiredCoursesModal\" id=\"addRequiredCoursesModalLink\" data-toggle=\"modal\" style=\"display:none\" class=\"btn btn-primary\"></a>\n");
      out.write("<a href=\"#myAlertH\" id=\"myAlertHModalLink\" data-toggle=\"modal\" style=\"display:none\" class=\"btn btn-primary\"></a>\n");
      out.write("<a href=\"#InformerModal\" id=\"informerModalLink\" data-toggle=\"modal\" style=\"display:none\" class=\"btn btn-primary\"></a>\n");
      out.write("<div id=\"InformerModal\" class=\"modal fade\">\n");
      out.write("    <div class=\"modal-dialog\">\n");
      out.write("        <div class=\"modal-content\">\n");
      out.write("            <div class=\"modal-header\">\n");
      out.write("                <button data-dismiss=\"modal\" class=\"close\" type=\"button\">×</button>\n");
      out.write("                <h3>Notification</h3>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"modal-body\">\n");
      out.write("                <p id=\"message_content\">Success</p>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("</div>\n");
      out.write("<div id=\"myAlertH\" class=\"modal fade\">\n");
      out.write("    <div class=\"modal-dialog\">\n");
      out.write("        <div class=\"modal-content\">\n");
      out.write("            <div class=\"modal-header\">\n");
      out.write("                <button data-dismiss=\"modal\" id=\"myAlertHClose\" class=\"close\" type=\"button\">×</button>\n");
      out.write("                <h3>Confirmation</h3>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"modal-body\">\n");
      out.write("                <p id=\"alert_content\" >Submit Form?</p>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"modal-footer\">\n");
      out.write("                <a  id=\"regConfirmed\" class=\"btn btn-primary btn-small\" onclick=\"submitForm('requiredCoursesForm', 'action=registerCourses', 'messageAlert');\" href=\"#\">YES</a>\n");
      out.write("                <a data-dismiss=\"modal\" class=\"btn btn-default btn-small\" href=\"#\">NO</a>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<script src=\"");
      out.print(request.getContextPath());
      out.write("/usersarea/js/ajax-submission.js\"></script>\n");
      out.write("<script src=\"");
      out.print(request.getContextPath());
      out.write("/usersarea/js/jquery.icheck.min.js\"></script>\n");
      out.write("<script src=\"");
      out.print(request.getContextPath());
      out.write("/usersarea/js/jquery.validate.js\"></script>\n");
      out.write("<script src=\"");
      out.print(request.getContextPath());
      out.write("/usersarea/js/select2.min.js\"></script>\n");
      out.write("<script src=\"");
      out.print(request.getContextPath());
      out.write("/usersarea/js/jquery.dataTables.min.js\"></script>\n");
      out.write("<script src=\"");
      out.print(request.getContextPath());
      out.write("/usersarea/js/unicorn.tables.js\"></script>");
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
