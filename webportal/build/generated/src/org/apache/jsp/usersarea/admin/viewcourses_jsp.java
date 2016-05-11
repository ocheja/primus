package org.apache.jsp.usersarea.admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.primus.appstates.AdministratorState;

public final class viewcourses_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_out_value_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_forEach_var_items;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_c_out_value_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_forEach_var_items = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_c_out_value_nobody.release();
    _jspx_tagPool_c_forEach_var_items.release();
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
      out.write("/usersarea/js/jquery.dataTables.min.js\"></script>\n");
      out.write("<script src=\"");
      out.print(request.getContextPath());
      out.write("/usersarea/js/jquery.jeditable.js\"></script>\n");
      out.write("<script src=\"");
      out.print(request.getContextPath());
      out.write("/usersarea/js/jquery.dataTables.editable.js\"></script>\n");
      out.write("<input type=\"hidden\" id=\"nodeView\" name=\"nodeView\" value=\"view\">\n");
      out.write("<div class=\"row\">\n");
      out.write("    <div class=\"col-12\">\n");
      out.write("\n");
      out.write("        <div class=\"widget-box\">\n");
      out.write("            <div class=\"widget-title\">\n");
      out.write("                <span class=\"icon\">\n");
      out.write("                    <i class=\"glyphicon glyphicon-th\"></i>\n");
      out.write("                </span>\n");
      out.write("                <h5>Courses</h5>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"widget-content\">\n");
      out.write("                <table id=\"courseData\" class=\"display\" cellspacing=\"0\" width=\"100%\">\n");
      out.write("                    <thead>\n");
      out.write("                        <tr>\n");
      out.write("                            <th>Serial No.</th>\n");
      out.write("                            <th>Course Title</th>\n");
      out.write("                            <th>Unit Load</th>\n");
      out.write("                            <th>Semester</th>\n");
      out.write("                            <th>Description</th>\n");
      out.write("                            <th>Department</th>\n");
      out.write("                            <th>Level</th>\n");
      out.write("                            <th>Prerequisites</th>\n");
      out.write("                            <th>Action</th>\n");
      out.write("                        </tr>\n");
      out.write("                    </thead>\n");
      out.write("                    <tbody>\n");
      out.write("\n");
      out.write("                    </tbody>\n");
      out.write("                    <tfoot>\n");
      out.write("                        <tr>\n");
      out.write("                            <th>Serial No.</th>\n");
      out.write("                            <th>Course Title</th>\n");
      out.write("                            <th>Unit Load</th>\n");
      out.write("                            <th>Semester</th>\n");
      out.write("                            <th>Description</th>\n");
      out.write("                            <th>Department</th>\n");
      out.write("                            <th>Level</th>\n");
      out.write("                            <th>Prerequisites</th>\n");
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
      out.write("/usersarea/js/datatable/coursedata.js\"></script>\n");
      out.write("<a href=\"#addPreCourseModal\" id=\"addpreCourseModalLink\" data-toggle=\"modal\" style=\"display:none\" class=\"btn btn-primary\"></a>\n");
      out.write("<a href=\"#PreCourseModal\" id=\"preCourseModalLink\" data-toggle=\"modal\" style=\"display:none\" class=\"btn btn-primary\"></a>\n");
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
      out.write("<div id=\"addPreCourseModal\" class=\"modal fade\">\n");
      out.write("    <div class=\"modal-dialog\">\n");
      out.write("        <div class=\"modal-content\">\n");
      out.write("            <div class=\"modal-header\">\n");
      out.write("                <button data-dismiss=\"modal\" class=\"close\" id=\"viewCoursesCloseXtra\" type=\"button\">×</button>\n");
      out.write("                <h3>New Prerequisite Course for <h4 id=\"owning_course_space\" ></h4></h3>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"modal-body\">\n");
      out.write("                <form class=\"form-horizontal\" method=\"post\" action=\"\" name=\"add_pre_course_form\" id=\"add_pre_course_form\" novalidate=\"novalidate\">\n");
      out.write("\n");
      out.write("                    <input type=\"hidden\" id=\"owningCourse\" name=\"owningCourse\"/>\n");
      out.write("\n");
      out.write("                    <div class=\"form-group\">\n");
      out.write("                        <label class=\"control-label\">Pre-requisite (s)</label>\n");
      out.write("                        <div class=\"controls\">\n");
      out.write("                            <select multiple id=\"precourses\" name=\"precourses\" class=\"required\">\n");
      out.write("                                ");
      if (_jspx_meth_c_forEach_0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("                            </select>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"form-actions\">\n");
      out.write("                        <input type=\"submit\" value=\"Create\" id=\"create_btn\" class=\"btn btn-success btn-max\">\n");
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
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<div id=\"precoursesdiv\">\n");
      out.write("\n");
      out.write("</div>\n");
      out.write("<script>\n");
      out.write("    $(document).ready(function (){\n");
      out.write("        $('select').select2();\n");
      out.write("    });\n");
      out.write("    function preparePreCourseForm(modalID, courseID, courseTitle) {\n");
      out.write("        $('#preViewClose').click();\n");
      out.write("        $(\"#owning_course_space\").text(courseTitle);\n");
      out.write("        document.getElementById('owningCourse').value = courseID;\n");
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
      out.write("                    drawTable();\n");
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
      out.write("    \n");
      out.write("    function deletePreRow(data, extra) {\n");
      out.write("    $.ajax\n");
      out.write("            ({\n");
      out.write("                url: '/Primus/admin/ajax',\n");
      out.write("                data: data,\n");
      out.write("                async: false,\n");
      out.write("                type: 'POST',\n");
      out.write("                dataType: 'json',\n");
      out.write("                beforeSend: function() {\n");
      out.write("                },\n");
      out.write("                success: function(resp) {\n");
      out.write("                    $(\"#message_content\").text(\"Action completed. Status --- \" + resp.message);\n");
      out.write("                    if (extra !== null) {\n");
      out.write("                        $(\"#\"+extra).click();\n");
      out.write("                    }\n");
      out.write("                    $(\"#informerModalLink\").click();\n");
      out.write("                    return false;\n");
      out.write("                },\n");
      out.write("                error: function(request, status, errorThrown) {\n");
      out.write("                    console.log(errorThrown);\n");
      out.write("                    var d = $(status);\n");
      out.write("                }\n");
      out.write("            });\n");
      out.write("    return false;\n");
      out.write("}\n");
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

  private boolean _jspx_meth_c_forEach_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_forEach_0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _jspx_tagPool_c_forEach_var_items.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_forEach_0.setPageContext(_jspx_page_context);
    _jspx_th_c_forEach_0.setParent(null);
    _jspx_th_c_forEach_0.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${courses}", java.lang.Object.class, (PageContext)_jspx_page_context, null));
    _jspx_th_c_forEach_0.setVar("course");
    int[] _jspx_push_body_count_c_forEach_0 = new int[] { 0 };
    try {
      int _jspx_eval_c_forEach_0 = _jspx_th_c_forEach_0.doStartTag();
      if (_jspx_eval_c_forEach_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\n");
          out.write("                                    <option value=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${course.id}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("\" > ");
          if (_jspx_meth_c_out_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_forEach_0, _jspx_page_context, _jspx_push_body_count_c_forEach_0))
            return true;
          out.write(" </option>\n");
          out.write("                                ");
          int evalDoAfterBody = _jspx_th_c_forEach_0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_forEach_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_forEach_0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_forEach_0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_forEach_0.doFinally();
      _jspx_tagPool_c_forEach_var_items.reuse(_jspx_th_c_forEach_0);
    }
    return false;
  }

  private boolean _jspx_meth_c_out_0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_forEach_0, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.rt.core.OutTag _jspx_th_c_out_0 = (org.apache.taglibs.standard.tag.rt.core.OutTag) _jspx_tagPool_c_out_value_nobody.get(org.apache.taglibs.standard.tag.rt.core.OutTag.class);
    _jspx_th_c_out_0.setPageContext(_jspx_page_context);
    _jspx_th_c_out_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_forEach_0);
    _jspx_th_c_out_0.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${course.courseTitle} (${course.courseCode})", java.lang.Object.class, (PageContext)_jspx_page_context, null));
    int _jspx_eval_c_out_0 = _jspx_th_c_out_0.doStartTag();
    if (_jspx_th_c_out_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_out_value_nobody.reuse(_jspx_th_c_out_0);
      return true;
    }
    _jspx_tagPool_c_out_value_nobody.reuse(_jspx_th_c_out_0);
    return false;
  }
}
