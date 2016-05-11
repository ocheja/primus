package org.apache.jsp.usersarea.admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class gradeschemefields_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\n");
      out.write("<div id=\"gradeschemefields\">\n");
      out.write("    \n");
      out.write("    ");

        int count = (Integer)request.getAttribute("numberofletters");
        for (int i = 1; i <= count; i++) {
    
      out.write("\n");
      out.write("\n");
      out.write("    <div class=\"row\">\n");
      out.write("        <div class=\"col-12 col-sm-6\">\n");
      out.write("            <label class=\"control-label\">");
      out.print(i);
      out.write("). Grade Letter</label>\n");
      out.write("            <div class=\"controls\">\n");
      out.write("                <input type=\"text\" class=\" required form-control input-box\" name=\"gradeletter");
      out.print(i);
      out.write("\" id=\"gradeletter");
      out.print(i);
      out.write("\">\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("        <div class=\"col-12 col-sm-6\">\n");
      out.write("            <label class=\"control-label\">Min. Mark</label>\n");
      out.write("            <div class=\"controls\">\n");
      out.write("                <input type=\"number\" class=\" required form-control input-box\" name=\"minmark");
      out.print(i);
      out.write("\" id=\"minmark");
      out.print(i);
      out.write("\">\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("        <div class=\"col-12 col-sm-6\">\n");
      out.write("            <label class=\"control-label\">Grade Point</label>\n");
      out.write("            <div class=\"controls\">\n");
      out.write("                <input type=\"number\" class=\" required form-control\" name=\"gradepoint");
      out.print(i);
      out.write("\" id=\"gradepoint");
      out.print(i);
      out.write("\">\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("        <div class=\"col-12 col-sm-6\">\n");
      out.write("            <label class=\"control-label\">Description</label>\n");
      out.write("            <div class=\"controls\">\n");
      out.write("                <input type=\"text\" class=\" required form-control\" name=\"gradeletterdescription");
      out.print(i);
      out.write("\" id=\"gradeletterdescription");
      out.print(i);
      out.write("\">\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("    ");
        }
    
      out.write("\n");
      out.write("    <input type=\"hidden\" name=\"numberofgradeletters\" id=\"numberofgradeletters\" value=\"");
      out.print(count);
      out.write("\"/>\n");
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
