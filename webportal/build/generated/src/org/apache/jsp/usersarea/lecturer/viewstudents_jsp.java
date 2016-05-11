package org.apache.jsp.usersarea.lecturer;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.primus.data.JqueryDataTableParamModel;
import com.primus.appstates.AdministratorState;

public final class viewstudents_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<input type=\"hidden\" id=\"nodeView\" name=\"nodeView\" value=\"view\">\n");
      out.write("<div class=\"row\" id=\"courseform-arena\">\n");
      out.write("    <div class=\"col-12\">\n");
      out.write("\n");
      out.write("        <div class=\"widget-box\">\n");
      out.write("            <div class=\"widget-title\">\n");
      out.write("                <span class=\"icon\">\n");
      out.write("                    <i class=\"glyphicon glyphicon-th\"></i>\n");
      out.write("                </span>\n");
      out.write("                <h5>List of Students</h5>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"widget-content\">\n");
      out.write("                <a class=\"btn btn-success btn-mini\" href=\"#myAlert\" id=\"myAlertLink\" data-toggle=\"modal\">Download Result Sheet Template</a>\n");
      out.write("                <table id=\"studentData\" class=\"display\" cellspacing=\"0\" width=\"100%\">\n");
      out.write("                    <thead>\n");
      out.write("                        <tr>\n");
      out.write("                            <th>Serial No.</th>\n");
      out.write("                            <th>Full Name</th>\n");
      out.write("                            <th>Reg Number</th>\n");
      out.write("                            <th>Department</th>\n");
      out.write("                        </tr>\n");
      out.write("                    </thead>\n");
      out.write("                    <tbody>\n");
      out.write("\n");
      out.write("                    </tbody>\n");
      out.write("                    <tfoot>\n");
      out.write("                        <tr>\n");
      out.write("                            <th>Serial No.</th>\n");
      out.write("                            <th>Full Name</th>\n");
      out.write("                            <th>Reg Number</th>\n");
      out.write("                            <th>Department</th>\n");
      out.write("                        </tr>\n");
      out.write("                    </tfoot>\n");
      out.write("                </table>\n");
      out.write("            </div>\n");
      out.write("        </div>  \t\t\n");
      out.write("    </div>\n");
      out.write("</div>\n");
      out.write("<script src=\"");
      out.print(request.getContextPath());
      out.write("/usersarea/js/datatable/viewstudentcoursesdata.js\"></script>\n");
      out.write("<a href=\"#addLecturerCourseModal\" id=\"addLecturerCourseModalLink\" data-toggle=\"modal\" style=\"display:none\" class=\"btn btn-primary\"></a>\n");
      out.write("<a href=\"#addLecturerPositionModal\" id=\"addLecturerPositionModalLink\" data-toggle=\"modal\" style=\"display:none\" class=\"btn btn-primary\"></a>\n");
      out.write("<a href=\"#LecturerCourseModal\" id=\"LecturerCourseModalLink\" data-toggle=\"modal\" style=\"display:none\" class=\"btn btn-primary\"></a>\n");
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
      out.write("<div id=\"addLecturerCourseModal\" class=\"modal fade\">\n");
      out.write("    <div class=\"modal-dialog\">\n");
      out.write("        <div class=\"modal-content\">\n");
      out.write("            <div class=\"modal-header\">\n");
      out.write("                <button data-dismiss=\"modal\" class=\"close\" id=\"viewCoursesCloseXtra\" type=\"button\">×</button>\n");
      out.write("                <h3>New Course (s) for <h4 class=\"owning_lecturer_space\" ></h4>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"modal-body\">\n");
      out.write("                <form class=\"form-horizontal\" method=\"post\" action=\"\" name=\"add_lecturer_course_form\" id=\"add_lecturer_course_form\" novalidate=\"novalidate\">\n");
      out.write("\n");
      out.write("                    <input type=\"hidden\" id=\"owningLecturer\" name=\"owningLecturer\"/>\n");
      out.write("\n");
      out.write("                    <div class=\"form-group\">\n");
      out.write("                        <label class=\"control-label\">Course (s)</label>\n");
      out.write("                        <div class=\"controls\">\n");
      out.write("                            <select multiple id=\"courses\" name=\"courses\" class=\"required\">\n");
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
      out.write("<div id=\"addLecturerPositionModal\" class=\"modal fade\">\n");
      out.write("    <div class=\"modal-dialog\">\n");
      out.write("        <div class=\"modal-content\">\n");
      out.write("            <div class=\"modal-header\">\n");
      out.write("                <button data-dismiss=\"modal\" class=\"close\" id=\"viewPositionCloseXtra\" type=\"button\">×</button>\n");
      out.write("                <h3>New Position (s) for <h4 class=\"owning_lecturer_space\" ></h4>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"modal-body\">\n");
      out.write("                <form class=\"form-horizontal\" method=\"post\" action=\"\" name=\"add_lecturer_position_form\" id=\"add_lecturer_position_form\" novalidate=\"novalidate\">\n");
      out.write("\n");
      out.write("                    <input type=\"hidden\" id=\"owningLecturer\" name=\"owningLecturer\"/>\n");
      out.write("\n");
      out.write("                    <div class=\"form-group\">\n");
      out.write("                        <label class=\"control-label\">Position (s)</label>\n");
      out.write("                        <div class=\"controls\">\n");
      out.write("                            <select multiple id=\"positions\" name=\"positions\" class=\"required\">\n");
      out.write("                                ");
      if (_jspx_meth_c_forEach_1(_jspx_page_context))
        return;
      out.write("\n");
      out.write("                            </select>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"form-group\">\n");
      out.write("                        <label class=\"control-label\">Level</label>\n");
      out.write("                        <div class=\"controls\">\n");
      out.write("                            <select id=\"academiclevel\" name=\"academiclevel\" disabled=\"disabled\" class=\"required\">\n");
      out.write("                                <option value=\"\" selected=\"\"></option>\n");
      out.write("                                ");
      if (_jspx_meth_c_forEach_2(_jspx_page_context))
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
      out.write("<div id=\"studentcoursesdiv\">\n");
      out.write("\n");
      out.write("</div>\n");
      out.write("<div id=\"lecturerpositionsdiv\">\n");
      out.write("\n");
      out.write("</div>\n");
      out.write("<script>\n");
      out.write("    $(document).ready(function() {\n");
      out.write("        $('select').select2();\n");
      out.write("        $(\"#positions\").change(function() {\n");
      out.write("            toggleacademiclevelFields();\n");
      out.write("        });\n");
      out.write("    });\n");
      out.write("    function prepareLecturerCourseForm(modalID, lecturerID, lecturerName, preViewBoxToClose) {\n");
      out.write("        $('#' + preViewBoxToClose).click();\n");
      out.write("        var owningLectSpaceElem = document.getElementsByClassName('owning_lecturer_space');\n");
      out.write("        var owningLectElem = document.getElementsByName('owningLecturer');\n");
      out.write("        for (i = 0; i < owningLectElem.length; i++) {\n");
      out.write("            var nodey = owningLectElem[i];\n");
      out.write("            nodey.value = lecturerID;\n");
      out.write("        }\n");
      out.write("        for (i = 0; i < owningLectSpaceElem.length; i++) {\n");
      out.write("            var nodex = owningLectSpaceElem[i];\n");
      out.write("            $(nodex).text(lecturerName);\n");
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
      out.write("    function deleteLecturerCourseRow(data, extra) {\n");
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
      out.write("\n");
      out.write("    function toggleacademiclevelFields() {\n");
      out.write("        var selectedItems = [];\n");
      out.write("        var containsPosition = false;\n");
      out.write("//selector uses id substring\n");
      out.write("        $(\"#positions option:selected\").each(function(i, obj) {\n");
      out.write("            \n");
      out.write("            console.log('position:'+$(this).val());\n");
      out.write("            if($(this).val()==='ACADEMIC_ADVISER'){\n");
      out.write("                containsPosition = true;\n");
      out.write("        }\n");
      out.write("        });\n");
      out.write("        if(containsPosition){\n");
      out.write("            $(\"#academiclevel\").removeAttr('disabled');\n");
      out.write("        }else {\n");
      out.write("            $(\"#academiclevel\").attr('disabled', 'true');\n");
      out.write("            $(\"#academiclevel\").attr('value', '');\n");
      out.write("        }\n");
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

  private boolean _jspx_meth_c_forEach_1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_forEach_1 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _jspx_tagPool_c_forEach_var_items.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_forEach_1.setPageContext(_jspx_page_context);
    _jspx_th_c_forEach_1.setParent(null);
    _jspx_th_c_forEach_1.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${positions}", java.lang.Object.class, (PageContext)_jspx_page_context, null));
    _jspx_th_c_forEach_1.setVar("position");
    int[] _jspx_push_body_count_c_forEach_1 = new int[] { 0 };
    try {
      int _jspx_eval_c_forEach_1 = _jspx_th_c_forEach_1.doStartTag();
      if (_jspx_eval_c_forEach_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\n");
          out.write("                                    <option value=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${position}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("\" > ");
          if (_jspx_meth_c_out_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_forEach_1, _jspx_page_context, _jspx_push_body_count_c_forEach_1))
            return true;
          out.write(" </option>\n");
          out.write("                                ");
          int evalDoAfterBody = _jspx_th_c_forEach_1.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_forEach_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_forEach_1[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_forEach_1.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_forEach_1.doFinally();
      _jspx_tagPool_c_forEach_var_items.reuse(_jspx_th_c_forEach_1);
    }
    return false;
  }

  private boolean _jspx_meth_c_out_1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_forEach_1, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_forEach_1)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.rt.core.OutTag _jspx_th_c_out_1 = (org.apache.taglibs.standard.tag.rt.core.OutTag) _jspx_tagPool_c_out_value_nobody.get(org.apache.taglibs.standard.tag.rt.core.OutTag.class);
    _jspx_th_c_out_1.setPageContext(_jspx_page_context);
    _jspx_th_c_out_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_forEach_1);
    _jspx_th_c_out_1.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${position}", java.lang.Object.class, (PageContext)_jspx_page_context, null));
    int _jspx_eval_c_out_1 = _jspx_th_c_out_1.doStartTag();
    if (_jspx_th_c_out_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_out_value_nobody.reuse(_jspx_th_c_out_1);
      return true;
    }
    _jspx_tagPool_c_out_value_nobody.reuse(_jspx_th_c_out_1);
    return false;
  }

  private boolean _jspx_meth_c_forEach_2(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_forEach_2 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _jspx_tagPool_c_forEach_var_items.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_forEach_2.setPageContext(_jspx_page_context);
    _jspx_th_c_forEach_2.setParent(null);
    _jspx_th_c_forEach_2.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${levels}", java.lang.Object.class, (PageContext)_jspx_page_context, null));
    _jspx_th_c_forEach_2.setVar("level");
    int[] _jspx_push_body_count_c_forEach_2 = new int[] { 0 };
    try {
      int _jspx_eval_c_forEach_2 = _jspx_th_c_forEach_2.doStartTag();
      if (_jspx_eval_c_forEach_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\n");
          out.write("                                    <option value=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${level}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("\" > ");
          if (_jspx_meth_c_out_2((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_forEach_2, _jspx_page_context, _jspx_push_body_count_c_forEach_2))
            return true;
          out.write(" </option>\n");
          out.write("                                ");
          int evalDoAfterBody = _jspx_th_c_forEach_2.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_forEach_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_forEach_2[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_forEach_2.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_forEach_2.doFinally();
      _jspx_tagPool_c_forEach_var_items.reuse(_jspx_th_c_forEach_2);
    }
    return false;
  }

  private boolean _jspx_meth_c_out_2(javax.servlet.jsp.tagext.JspTag _jspx_th_c_forEach_2, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_forEach_2)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.rt.core.OutTag _jspx_th_c_out_2 = (org.apache.taglibs.standard.tag.rt.core.OutTag) _jspx_tagPool_c_out_value_nobody.get(org.apache.taglibs.standard.tag.rt.core.OutTag.class);
    _jspx_th_c_out_2.setPageContext(_jspx_page_context);
    _jspx_th_c_out_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_forEach_2);
    _jspx_th_c_out_2.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${level}", java.lang.Object.class, (PageContext)_jspx_page_context, null));
    int _jspx_eval_c_out_2 = _jspx_th_c_out_2.doStartTag();
    if (_jspx_th_c_out_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_out_value_nobody.reuse(_jspx_th_c_out_2);
      return true;
    }
    _jspx_tagPool_c_out_value_nobody.reuse(_jspx_th_c_out_2);
    return false;
  }
}
