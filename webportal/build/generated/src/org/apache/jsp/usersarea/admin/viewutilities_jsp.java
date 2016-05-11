package org.apache.jsp.usersarea.admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.primus.appstates.AdministratorState;

public final class viewutilities_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<link rel=\"stylesheet\" href=\"");
      out.print(request.getContextPath());
      out.write("/usersarea/css/jquery.dataTables.css\" />\n");
      out.write("<script src=\"");
      out.print(request.getContextPath());
      out.write("/usersarea/js/unicorn.interface.js\"></script>\n");
      out.write("<script src=\"");
      out.print(request.getContextPath());
      out.write("/usersarea/js/jquery.jeditable.js\"></script>\n");
      out.write("<script src=\"");
      out.print(request.getContextPath());
      out.write("/usersarea/js/jquery.dataTables.editable.js\"></script>\n");
      out.write("<script src=\"");
      out.print(request.getContextPath());
      out.write("/usersarea/js/jquery.dataTables.min.js\"></script>\n");
      out.write("<input type=\"hidden\" id=\"nodeView\" name=\"nodeView\" value=\"view\">\n");
      out.write("<div class=\"row\">\n");
      out.write("    <div class=\"col-12\">\n");
      out.write("\n");
      out.write("        <div class=\"widget-box\">\n");
      out.write("            <div class=\"widget-title\">\n");
      out.write("                <span class=\"icon\">\n");
      out.write("                    <i class=\"glyphicon glyphicon-th\"></i>\n");
      out.write("                </span>\n");
      out.write("                <h5>Sessions</h5>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"widget-content\">\n");
      out.write("                <table id=\"sessionData\" class=\"display\" cellspacing=\"0\" width=\"100%\">\n");
      out.write("                    <thead>\n");
      out.write("                        <tr>\n");
      out.write("                            <th>Serial No.</th>\n");
      out.write("                            <th>Start Year</th>\n");
      out.write("                            <th>End Year</th>\n");
      out.write("                            <th>Current Session</th>\n");
      out.write("                            <th>Action</th>\n");
      out.write("                        </tr>\n");
      out.write("                    </thead>\n");
      out.write("                    <tbody>\n");
      out.write("\n");
      out.write("                    </tbody>\n");
      out.write("                    <tfoot>\n");
      out.write("                        <tr>\n");
      out.write("                            <th>Serial No.</th>\n");
      out.write("                            <th>Start Year</th>\n");
      out.write("                            <th>End Year</th>\n");
      out.write("                            <th>Current Session</th>\n");
      out.write("                            <th>Action</th>\n");
      out.write("                        </tr>\n");
      out.write("                    </tfoot>\n");
      out.write("                </table>\n");
      out.write("            </div>\n");
      out.write("        </div>  \t\t\n");
      out.write("    </div>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<div class=\"row\">\n");
      out.write("    <div class=\"col-12\">\n");
      out.write("\n");
      out.write("        <div class=\"widget-box\">\n");
      out.write("            <div class=\"widget-title\">\n");
      out.write("                <span class=\"icon\">\n");
      out.write("                    <i class=\"glyphicon glyphicon-th\"></i>\n");
      out.write("                </span>\n");
      out.write("                <h5>Degree Requirements</h5>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"widget-content\">\n");
      out.write("                <table id=\"DegreeReqData\" class=\"display\" cellspacing=\"0\" width=\"100%\">\n");
      out.write("                    <thead>\n");
      out.write("                        <tr>\n");
      out.write("                            <th>Serial No.</th>\n");
      out.write("                            <th>Title of Degree</th>\n");
      out.write("                            <th>Department</th>\n");
      out.write("                            <th>Minimum Year(s)</th>\n");
      out.write("                            <th>Maximum Year(s)</th>\n");
      out.write("                            <th>Courses for each Level</th>\n");
      out.write("                            <th>Action</th>\n");
      out.write("                        </tr>\n");
      out.write("                    </thead>\n");
      out.write("                    <tbody>\n");
      out.write("\n");
      out.write("                    </tbody>\n");
      out.write("                    <tfoot>\n");
      out.write("                        <tr>\n");
      out.write("                            <th>Serial No.</th>\n");
      out.write("                            <th>Title of Degree</th>\n");
      out.write("                            <th>Department</th>\n");
      out.write("                            <th>Minimum Year(s)</th>\n");
      out.write("                            <th>Maximum Year(s)</th>\n");
      out.write("                            <th>Courses for each Level</th>\n");
      out.write("                            <th>Action</th>\n");
      out.write("                        </tr>\n");
      out.write("                    </tfoot>\n");
      out.write("                </table>\n");
      out.write("            </div>\n");
      out.write("        </div>  \t\t\n");
      out.write("    </div>\n");
      out.write("</div>\n");
      out.write("<script src=\"");
      out.print(request.getContextPath());
      out.write("/usersarea/js/datatable/utilitydata.js\"></script>\n");
      out.write("<a href=\"#DegreeCourseModal\" id=\"DegreeCourseModalLink\" data-toggle=\"modal\" style=\"display:none\" class=\"btn btn-primary\"></a>\n");
      out.write("<a href=\"#InformerModal\" id=\"informerModalLink\" data-toggle=\"modal\" style=\"display:none\" class=\"btn btn-primary\"></a>\n");
      out.write("<a href=\"#myAlert\" id=\"myAlertLink\" data-toggle=\"modal\" style=\"display:none\" class=\"btn btn-danger\">Alert</a>\n");
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
      out.write("<div id=\"myAlert\" class=\"modal fade\">\n");
      out.write("    <div class=\"modal-dialog\">\n");
      out.write("        <div class=\"modal-content\">\n");
      out.write("            <div class=\"modal-header\">\n");
      out.write("                <button data-dismiss=\"modal\" class=\"close\" type=\"button\">×</button>\n");
      out.write("                <h3>Confirmation</h3>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"modal-body\">\n");
      out.write("                <p id=\"alert_content\" >Confirm Delete</p>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"modal-footer\">\n");
      out.write("                <a data-dismiss=\"modal\" id=\"deleteConfirmed\" class=\"btn btn-primary btn-small\" href=\"#\">Confirm</a>\n");
      out.write("                <a data-dismiss=\"modal\" class=\"btn btn-default btn-small\" href=\"#\">Cancel</a>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("</div>\n");
      out.write("<div id=\"degreecoursesdiv\">\n");
      out.write("\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<script>\n");
      out.write("    $(document).ready(function() {\n");
      out.write("        $('select').select2();\n");
      out.write("    });\n");
      out.write("    function prepareDegreeCourseForm(modalID, degreeID, degreeTitle, preViewBoxToClose) {\n");
      out.write("        $('#' + preViewBoxToClose).click();\n");
      out.write("        var owningDegreeSpaceElem = document.getElementsByClassName('owning_degree_space');\n");
      out.write("        var owningDegreeElem = document.getElementsByName('owningDegree');\n");
      out.write("        for (i = 0; i < owningDegreeElem.length; i++) {\n");
      out.write("            var nodey = owningDegreeElem[i];\n");
      out.write("            nodey.value = degreeID;\n");
      out.write("        }\n");
      out.write("        for (i = 0; i < owningDegreeSpaceElem.length; i++) {\n");
      out.write("            var nodex = owningDegreeSpaceElem[i];\n");
      out.write("            $(nodex).text(degreeTitle);\n");
      out.write("        }\n");
      out.write("        $(\"#\" + modalID).click();\n");
      out.write("        $(\"#deleteConfirmed\").click(function() {\n");
      out.write("            $.ajax({\n");
      out.write("                url: \"/Primus/admin/delete\",\n");
      out.write("                data: \"&id=\" + courseID,\n");
      out.write("                async: false,\n");
      out.write("                dataType: 'json',\n");
      out.write("                success: function(resp) {\n");
      out.write("                    $(\"#message_content\").text(\"Delete action finished. Status - \" + resp.message);\n");
      out.write("                    $(\"#informerModalLink\").click();\n");
      out.write("                },\n");
      out.write("                error: function(request, status, errorThrown) {\n");
      out.write("                    $(\"#message_content\").text(\"Delete action finished. Status - \" + status);\n");
      out.write("                    $(\"#informerModalLink\").click();\n");
      out.write("\n");
      out.write("                }\n");
      out.write("            });\n");
      out.write("        });\n");
      out.write("        return false;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    function deleteDegreeCourseRow(data, extra) {\n");
      out.write("        $.ajax\n");
      out.write("                ({\n");
      out.write("                    url: '/Primus/admin/ajax',\n");
      out.write("                    data: data,\n");
      out.write("                    async: false,\n");
      out.write("                    type: 'POST',\n");
      out.write("                    dataType: 'json',\n");
      out.write("                    beforeSend: function() {\n");
      out.write("                    },\n");
      out.write("                    success: function(resp) {\n");
      out.write("                        $(\"#message_content\").text(\"Action completed. Status --- \" + resp.message);\n");
      out.write("                        if (extra !== null) {\n");
      out.write("                            $(\"#\" + extra).click();\n");
      out.write("                        }\n");
      out.write("                        $(\"#informerModalLink\").click();\n");
      out.write("                        return false;\n");
      out.write("                    },\n");
      out.write("                    error: function(request, status, errorThrown) {\n");
      out.write("                        console.log(errorThrown);\n");
      out.write("                        var d = $(status);\n");
      out.write("                    }\n");
      out.write("                });\n");
      out.write("        return false;\n");
      out.write("    }\n");
      out.write("</script>");
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
