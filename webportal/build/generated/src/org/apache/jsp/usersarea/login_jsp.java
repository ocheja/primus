package org.apache.jsp.usersarea;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<html lang=\"en\">\n");
      out.write("    <head>\n");
      out.write("        <title>Login Page...UNN</title>\n");
      out.write("\t\t<meta charset=\"UTF-8\" />\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n");
      out.write("\t\t<link rel=\"stylesheet\" href=\"");
      out.print(request.getContextPath());
      out.write("/usersarea/css/bootstrap.min.css\" />\n");
      out.write("        <link rel=\"stylesheet\" href=\"");
      out.print(request.getContextPath());
      out.write("/usersarea/css/bootstrap-glyphicons.css\" />\n");
      out.write("        <link rel=\"stylesheet\" href=\"");
      out.print(request.getContextPath());
      out.write("/usersarea/css/unicorn.login.css\" />\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <div id=\"container\">\n");
      out.write("            <div id=\"logo\">\n");
      out.write("                <a href=\"/Primus\"><img src=\"");
      out.print(request.getContextPath());
      out.write("/usersarea/img/logo.png\" alt=\"\" /></a>\n");
      out.write("            </div>\n");
      out.write("            <div id=\"loginbox\">            \n");
      out.write("                <form id=\"loginform\" action=\"/Primus/login\" method=\"post\">\n");
      out.write("    \t\t\t\t<p>Enter username and password to continue.</p>\n");
      out.write("                    <div class=\"input-group\">\n");
      out.write("                        <span class=\"input-group-addon\"><i class=\"glyphicon glyphicon-user\"></i></span>\n");
      out.write("                        <input class=\"form-control\" type=\"text\" id=\"userId\" name=\"userId\" required=\"required\" placeholder=\"email or registration number\" />\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"input-group\">\n");
      out.write("                        <span class=\"input-group-addon\"><i class=\"glyphicon glyphicon-lock\"></i></span>\n");
      out.write("                        <input class=\"form-control\"  required=\"required\" name=\"loginPassword\" id=\"loginPassword\" type=\"password\" placeholder=\"Password\" />\n");
      out.write("                    </div>\n");
      out.write("                        <!-- <div class=\"row\">\n");
      out.write("                            <div class=\"col-4 col-lg-4\"><a class=\"btn btn-small btn-block btn-primary\" href=\"#\">Facebook</a></div>\n");
      out.write("                            <div class=\"col-4 col-lg-4\"><a class=\"btn btn-small btn-block btn-info\" href=\"#\"><i class=\"glyphicon glyphicon-sign-in\"></i> Twitter</a></div>\n");
      out.write("                            <div class=\"col-4 col-lg-4\"><a class=\"btn btn-small btn-block btn-danger\" href=\"#\">Google Plus</a></div>\n");
      out.write("                        </div> -->\n");
      out.write("                    <hr />\n");
      out.write("                    <div class=\"form-actions\">\n");
      out.write("                        <div class=\"pull-left\">\n");
      out.write("                            <a href=\"#\" class=\"flip-link to-recover\">Lost password?</a><br />\n");
      out.write("                            <a href=\"#\" class=\"flip-link to-register\">Need account? Register here!</a>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"pull-right\"><input type=\"submit\" class=\"btn btn-default\" value=\"Login\" /></div>\n");
      out.write("                    </div>\n");
      out.write("                </form>\n");
      out.write("                <form id=\"recoverform\" action=\"#\">\n");
      out.write("    \t\t\t\t<p>Enter your e-mail address below and we will send you instructions how to recover a password.</p>\n");
      out.write("    \t\t\t\t<div class=\"input-group\">\n");
      out.write("                        <span class=\"input-group-addon\"><i class=\"glyphicon glyphicon-envelope\"></i></span><input class=\"form-control\" type=\"text\" placeholder=\"E-mail address\" />\n");
      out.write("                    </div>\n");
      out.write("                    <hr />\n");
      out.write("                    <div class=\"form-actions\">\n");
      out.write("                        <div class=\"pull-left\">\n");
      out.write("                            <a href=\"#\" class=\"flip-link to-login\">&laquo; Back to login</a><br />\n");
      out.write("                            <a href=\"#\" class=\"flip-link to-register\">Need account? Register here!</a>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"pull-right\"><input type=\"submit\" class=\"btn btn-default\" value=\"Recover\" /></div>\n");
      out.write("                    </div>\n");
      out.write("                </form>\n");
      out.write("                <form id=\"registerform\" action=\"#\">\n");
      out.write("                    <p>Enter information required to register:</p>\n");
      out.write("                    <div class=\"input-group\">\n");
      out.write("                        <span class=\"input-group-addon\"><i class=\"glyphicon glyphicon-user\"></i></span><input class=\"form-control\" type=\"text\" placeholder=\"Enter Username\" />\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"input-group\">\n");
      out.write("                        <span class=\"input-group-addon\"><i class=\"glyphicon glyphicon-lock\"></i></span><input class=\"form-control\" type=\"password\" placeholder=\"Choose Password\" />\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"input-group\">\n");
      out.write("                        <span class=\"input-group-addon\"><i class=\"glyphicon glyphicon-lock\"></i></span><input class=\"form-control\" type=\"password\" placeholder=\"Confirm password\" />\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"input-group\">\n");
      out.write("                        <span class=\"input-group-addon\"><i class=\"glyphicon glyphicon-envelope\"></i></span><input class=\"form-control\" type=\"text\" placeholder=\"Enter E-mail address\" />\n");
      out.write("                    </div>\n");
      out.write("                    <hr />\n");
      out.write("                    <div class=\"form-actions\">\n");
      out.write("                        <div class=\"pull-left\">\n");
      out.write("                            <a href=\"#\" class=\"flip-link to-login\">&laquo; Back to login</a><br />\n");
      out.write("                            <a href=\"#\" class=\"flip-link to-recover\">Lost password?</a>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"pull-right\"><input type=\"submit\" class=\"btn btn-default\" value=\"Register\" /></div>\n");
      out.write("                    </div>\n");
      out.write("                </form>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("        \n");
      out.write("        <script src=\"");
      out.print(request.getContextPath());
      out.write("/usersarea/js/jquery.min.js\"></script>  \n");
      out.write("        <script src=\"");
      out.print(request.getContextPath());
      out.write("/usersarea/js/unicorn.login.js\"></script> \n");
      out.write("    </body>\n");
      out.write("</html>\n");
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
