package org.apache.jsp.usersarea.lecturer;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.primus.data.JqueryDataTableParamModel;

public final class classroom_002doptions_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<div class=\"row\" id=\"courseform-arena\">\n");
      out.write("    ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/usersarea/lecturer/classmembers.jsp", out, false);
      out.write("    \n");
      out.write("</div>\n");
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
      out.write("                <h3>Select Department</h3>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"modal-body\">\n");
      out.write("                <form class=\"form-horizontal\"  method=\"get\" action=\"/Primus/student/download\" name=\"downloadresultsheet\" id=\"downloadresultsheet\" novalidate=\"novalidate\">\n");
      out.write("                    <div class=\"form-group\">\n");
      out.write("                        <label class=\"control-label\">Name of Department</label>\n");
      out.write("                        <div class=\"controls\">\n");
      out.write("                            <input type=\"hidden\" required=\"required\" id=\"departmentName\" name=\"departmentName\"/>\n");
      out.write("\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                    <input type=\"hidden\" value=\"resultsheetformat\" id=\"action\" name=\"action\"/>\n");
      out.write("                    <div class=\"form-actions\">\n");
      out.write("                        <input type=\"submit\" value=\"Submit\" id=\"create_btn\" class=\"btn btn-success btn-max\">\n");
      out.write("                        <div id=\"loading_gif\" style=\"display: none\">\n");
      out.write("                            <img src=\"");
      out.print(request.getContextPath());
      out.write("/usersarea/img/notice_spinner.gif\"/>\n");
      out.write("                            <label id=\"message_on_end\">Loading...</label>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                </form>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("</div>\n");
      out.write("<script>\n");
      out.write("    $(document).ready(function() {\n");
      out.write("        var lastResults = [];\n");
      out.write("        $('select').select2();\n");
      out.write("        $(\"#departmentName\").select2({\n");
      out.write("            placeholder: \"Search for a department name\",\n");
      out.write("            minimumInputLength: 3,\n");
      out.write("            multiple: false,\n");
      out.write("                ajax: {\n");
      out.write("                url: \"/Primus/admin/ajax\",\n");
      out.write("                dataType: 'json',\n");
      out.write("                type: \"GET\",\n");
      out.write("                    data: function(term, page) {\n");
      out.write("                        return {\n");
      out.write("                        q: term, // search term\n");
      out.write("                    action: \"departmentNames\"\n");
      out.write("                };\n");
      out.write("                },\n");
      out.write("                    results: function(data, page) {\n");
      out.write("                    lastResults = data;\n");
      out.write("                return {results: data};\n");
      out.write("                }\n");
      out.write("                },\n");
      out.write("                    createSearchChoice: function(term) {\n");
      out.write("                        var text = term + (lastResults.some(function(r) {\n");
      out.write("                    return r.text === term;\n");
      out.write("                    }) ? \"\" : \" (new)\");\n");
      out.write("            return {id: term, text: text};\n");
      out.write("        }\n");
      out.write("    });\n");
      out.write("    });\n");
      out.write("</script>\n");
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
