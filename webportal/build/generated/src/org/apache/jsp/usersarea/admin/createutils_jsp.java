package org.apache.jsp.usersarea.admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.primus.appstates.AdministratorState;

public final class createutils_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<input type=\"hidden\" id=\"nodeView\" name=\"nodeView\" value=\"create\">\n");
      out.write("<div class=\"row\">\n");
      out.write("    <div class=\"col-12\">\n");
      out.write("        <div class=\"widget-box collapsible\">\n");
      out.write("            <a href=\"#sessionForm\" data-toggle=\"collapse\">\n");
      out.write("                <div class=\"widget-title\">\n");
      out.write("                    <span class=\"icon\">\n");
      out.write("                        <i class=\"glyphicon glyphicon-align-justify\"></i>\t\t\t\t\t\t\t\t\t\n");
      out.write("                    </span>\n");
      out.write("                    <h5>New Session Form</h5>\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("            </a>\n");
      out.write("            <div class=\"widget-content nopadding collapse\" id=\"sessionForm\">\n");
      out.write("                <form class=\"form-horizontal\" method=\"post\" action=\"\" name=\"create_session_form\" id=\"create_session_form\" novalidate=\"novalidate\">\n");
      out.write("                    <div class=\"row\">\n");
      out.write("                        <div class=\"col-12 col-sm-6 form-group\">\n");
      out.write("                            <label class=\"control-label\">Start Year</label>\n");
      out.write("                            <div class=\"controls\">\n");
      out.write("                                <input type=\"text\" class=\" required digits form-control input-small\" name=\"startYear\" id=\"startYear\">\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"col-12 col-sm-6 form-group\">\n");
      out.write("                            <label class=\"control-label\">End Year</label>\n");
      out.write("                            <div class=\"controls\">\n");
      out.write("                                <input type=\"text\" class=\" required digits form-control input-small\" name=\"endYear\" id=\"endYear\">\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"row\">\n");
      out.write("                        <div class=\"col-12 col-sm-6 form-group\">\n");
      out.write("                            <label class=\"control-label\">Use Existing Grading Scheme</label>\n");
      out.write("                            <div class=\"controls\">\n");
      out.write("                                <input type=\"checkbox\" class=\"form-control\" name=\"useExistingGradingScheme\" id=\"useExistingGradingScheme\">\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"col-12 col-sm-6 form-group\">\n");
      out.write("                            <label class=\"control-label\">Select Result Grading Scheme</label>\n");
      out.write("                            <div class=\"controls\">\n");
      out.write("                                <select id=\"gradingscheme\" name=\"gradingscheme\" class=\"required\" disabled>\n");
      out.write("                                    <option value=\"\" selected></option>\n");
      out.write("                                    ");
      if (_jspx_meth_c_forEach_0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("                                </select>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"row\">\n");
      out.write("                        <div class=\"col-12 col-sm-12 form-group\">\n");
      out.write("                            <label class=\"control-label\">Enter Number of Grade Letters (e.g A,B,C,D,E,F means 6 grade letters)</label>\n");
      out.write("                            <div class=\"controls\">\n");
      out.write("                                <input type=\"text\" class=\"form-control required\" name=\"numberOfGradeLetters\" id=\"numberOfGradeLetters\">\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"row\">\n");
      out.write("                        <div class=\"col-12 col-sm-6 form-group\">\n");
      out.write("                            <label class=\"control-label\">Fail Grade Letter </label>\n");
      out.write("                            <div class=\"controls\">\n");
      out.write("                                <input type=\"text\" class=\"form-control required\" name=\"failgradeletter\" id=\"failgradeletter\">\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"col-12 col-sm-6 form-group\">\n");
      out.write("                            <label class=\"control-label\">Maximum Total Units Load </label>\n");
      out.write("                            <div class=\"controls\">\n");
      out.write("                                <input type=\"number\" class=\"form-control required digits\" name=\"maximumUnits\" id=\"maximumUnits\">\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                    <div id=\"gradeschemefields\">\n");
      out.write("\n");
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
      out.write("        <div class=\"widget-box collapsible\">\n");
      out.write("            <a href=\"#degreerequirmentformdiv\" data-toggle=\"collapse\">\n");
      out.write("                <div class=\"widget-title\">\n");
      out.write("                    <span class=\"icon\">\n");
      out.write("                        <i class=\"glyphicon glyphicon-align-justify\"></i>\t\t\t\t\t\t\t\t\t\n");
      out.write("                    </span>\n");
      out.write("                    <h5>New Degree Requirement Form</h5>\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("            </a>\n");
      out.write("            <div class=\"widget-content nopadding collapse\" id=\"degreerequirmentformdiv\">\n");
      out.write("                <form class=\"form-horizontal\" method=\"post\" action=\"\" name=\"create_degree_requirement_form\" id=\"create_degree_requirement_form\" novalidate=\"novalidate\">\n");
      out.write("                    <div class=\"row\">\n");
      out.write("                        <div class=\"col-12 col-sm-6 form-group\">\n");
      out.write("                            <label class=\"control-label\">Title of Degree</label>\n");
      out.write("                            <div class=\"controls\">\n");
      out.write("                                <select id=\"titleofdegree\" name=\"titleofdegree\" class=\"required\">\n");
      out.write("                                    <option value=\"\" selected></option>\n");
      out.write("                                    ");
      if (_jspx_meth_c_forEach_1(_jspx_page_context))
        return;
      out.write("\n");
      out.write("                                </select>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"col-12 col-sm-6 form-group\">\n");
      out.write("                            <label class=\"control-label\">Department</label>\n");
      out.write("                            <div class=\"controls\">\n");
      out.write("                                <select id=\"department\" name=\"department\" class=\"required\">\n");
      out.write("                                    <option value=\"\" selected></option>\n");
      out.write("                                    ");
      if (_jspx_meth_c_forEach_2(_jspx_page_context))
        return;
      out.write("\n");
      out.write("                                </select>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"row\">\n");
      out.write("                        <div class=\"col-12 col-sm-6 form-group\">\n");
      out.write("                            <label class=\"control-label\">Min. Year</label>\n");
      out.write("                            <div class=\"controls\">\n");
      out.write("                                <input type=\"text\" class=\" required digits form-control input-small\" name=\"minYear\" id=\"minYear\">\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"col-12 col-sm-6 form-group\">\n");
      out.write("                            <label class=\"control-label\">Max. Year</label>\n");
      out.write("                            <div class=\"controls\">\n");
      out.write("                                <input type=\"text\" class=\" required digits form-control input-small\" name=\"maxYear\" id=\"maxYear\">\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"row\">\n");
      out.write("                        <div class=\"col-12 col-sm-12 form-group\">\n");
      out.write("                            <label class=\"control-label\">Enter Number of Levels (max. 7)</label>\n");
      out.write("                            <div class=\"controls\">\n");
      out.write("                                <input type=\"text\" class=\"form-control required\" name=\"numberOfLevels\" id=\"numberOfLevels\">\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                    <div id=\"levelfields\">\n");
      out.write("\n");
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
      out.write("<div class=\"row\">\n");
      out.write("    <div class=\"col-12\">\n");
      out.write("        <div class=\"widget-box collapsible\">\n");
      out.write("            <a href=\"#batchutilsForms\" data-toggle=\"collapse\">\n");
      out.write("                <div class=\"widget-title\">\n");
      out.write("                    <span class=\"icon\">\n");
      out.write("                        <i class=\"glyphicon glyphicon-align-justify\"></i>\t\t\t\t\t\t\t\t\t\n");
      out.write("                    </span>\n");
      out.write("                <h5>Batch Utils Creation (EXCEL)</h5>\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("            </a>\n");
      out.write("\n");
      out.write("            <div class=\"widget-content nopadding collapse\" id=\"batchutilsForms\">\n");
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
      out.write("                    </div>\n");
      out.write("            </div>\n");
      out.write("            </form>\n");
      out.write("        </div>\n");
      out.write("    </div>\t\t\t\n");
      out.write("</div>\n");
      out.write("<script>\n");
      out.write("    $(document).ready(function() {\n");
      out.write("        $('select').select2();\n");
      out.write("        $('#numberOfLevels').bind('blur keyup change', function() {\n");
      out.write("            var n = this.value || 0;\n");
      out.write("            var data = 'action=levelFields&numberOfLevels=' + n;\n");
      out.write("            if (n + 1) {\n");
      out.write("                $.ajax\n");
      out.write("                        ({\n");
      out.write("                            url: '/Primus/admin/pull',\n");
      out.write("                            data: data,\n");
      out.write("                            async: false,\n");
      out.write("                            type: 'GET',\n");
      out.write("                            success: function(resp, status, xhr) {\n");
      out.write("                                var ct = xhr.getResponseHeader(\"content-type\") || \"\";\n");
      out.write("                                if (ct.indexOf('html') > -1) {\n");
      out.write("                                    var d = $(resp);\n");
      out.write("                                    var htmlStr = \"\";\n");
      out.write("                                    for (i = 0; i < d.length; i++) {\n");
      out.write("                                        var node = d[i];\n");
      out.write("                                        if (node.id === 'levelfields') {\n");
      out.write("                                            htmlStr = $(node).html();\n");
      out.write("                                            break;\n");
      out.write("                                        }\n");
      out.write("                                    }\n");
      out.write("                                    if (htmlStr.length > 0) {\n");
      out.write("                                        $('#levelfields').html(htmlStr);\n");
      out.write("                                        $('.coursesforlevel').select2();\n");
      out.write("                                    } else {\n");
      out.write("\n");
      out.write("                                    }\n");
      out.write("                                }\n");
      out.write("                                if (ct.indexOf('json') > -1) {\n");
      out.write("                                    console.log(resp);\n");
      out.write("                                }\n");
      out.write("                                return false;\n");
      out.write("                            },\n");
      out.write("                            error: function(request, status, errorThrown) {\n");
      out.write("                                console.log(errorThrown);\n");
      out.write("                                var d = $(status);\n");
      out.write("                            }\n");
      out.write("                        });\n");
      out.write("            }\n");
      out.write("        });\n");
      out.write("        $('#numberOfGradeLetters').bind('blur keyup change', function() {\n");
      out.write("            var n = this.value || 0;\n");
      out.write("            var data = 'action=gradeSchemeFields&numberOfGradeLetters=' + n;\n");
      out.write("            if (n + 1) {\n");
      out.write("                $.ajax\n");
      out.write("                        ({\n");
      out.write("                            url: '/Primus/admin/pull',\n");
      out.write("                            data: data,\n");
      out.write("                            async: false,\n");
      out.write("                            type: 'GET',\n");
      out.write("                            success: function(resp, status, xhr) {\n");
      out.write("                                var ct = xhr.getResponseHeader(\"content-type\") || \"\";\n");
      out.write("                                if (ct.indexOf('html') > -1) {\n");
      out.write("                                    var d = $(resp);\n");
      out.write("                                    var htmlStr = \"\";\n");
      out.write("                                    for (i = 0; i < d.length; i++) {\n");
      out.write("                                        var node = d[i];\n");
      out.write("                                        if (node.id === 'gradeschemefields') {\n");
      out.write("                                            htmlStr = $(node).html();\n");
      out.write("                                            break;\n");
      out.write("                                        }\n");
      out.write("                                    }\n");
      out.write("                                    if (htmlStr.length > 0) {\n");
      out.write("                                        $('#gradeschemefields').html(htmlStr);\n");
      out.write("                                    } else {\n");
      out.write("\n");
      out.write("                                    }\n");
      out.write("                                }\n");
      out.write("                                if (ct.indexOf('json') > -1) {\n");
      out.write("                                    console.log(resp);\n");
      out.write("                                }\n");
      out.write("                                return false;\n");
      out.write("                            },\n");
      out.write("                            error: function(request, status, errorThrown) {\n");
      out.write("                                console.log(errorThrown);\n");
      out.write("                                var d = $(status);\n");
      out.write("                            }\n");
      out.write("                        });\n");
      out.write("            }\n");
      out.write("        });\n");
      out.write("\n");
      out.write("        $(\"#useExistingGradingScheme\").change(function() {\n");
      out.write("            toggleFields();\n");
      out.write("        });\n");
      out.write("    });\n");
      out.write("    function toggleFields() {\n");
      out.write("        if (document.getElementById('useExistingGradingScheme').checked) {\n");
      out.write("            $(\"#gradingscheme\").removeAttr('disabled');\n");
      out.write("            $(\"#numberOfGradeLetters\").attr('value', '');\n");
      out.write("            $(\"#numberOfGradeLetters\").blur();\n");
      out.write("            $(\"#numberOfGradeLetters\").attr('disabled', 'true');\n");
      out.write("//\t\t\t$(\"#studentfields\").hide();\n");
      out.write("        } else {\n");
      out.write("            $(\"#gradingscheme\").attr('disabled', 'true');\n");
      out.write("            ;\n");
      out.write("            $(\"#gradingscheme\").attr('selected', '');\n");
      out.write("            ;\n");
      out.write("            $(\"#numberOfGradeLetters\").removeAttr('disabled');\n");
      out.write("//\t    \t$(\"#studentfields\").hide();\n");
      out.write("        }\n");
      out.write("    }\n");
      out.write("</script>\n");
      out.write("\n");
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

  private boolean _jspx_meth_c_forEach_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_forEach_0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _jspx_tagPool_c_forEach_var_items.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_forEach_0.setPageContext(_jspx_page_context);
    _jspx_th_c_forEach_0.setParent(null);
    _jspx_th_c_forEach_0.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${gradingschemes}", java.lang.Object.class, (PageContext)_jspx_page_context, null));
    _jspx_th_c_forEach_0.setVar("gradingscheme");
    int[] _jspx_push_body_count_c_forEach_0 = new int[] { 0 };
    try {
      int _jspx_eval_c_forEach_0 = _jspx_th_c_forEach_0.doStartTag();
      if (_jspx_eval_c_forEach_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\n");
          out.write("                                        <option value=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${gradingscheme.id}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("\" > ");
          if (_jspx_meth_c_out_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_forEach_0, _jspx_page_context, _jspx_push_body_count_c_forEach_0))
            return true;
          out.write(" </option>\n");
          out.write("                                    ");
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
    _jspx_th_c_out_0.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${gradingscheme.academicSession.startYear}/${gradingscheme.academicSession.endYear}", java.lang.Object.class, (PageContext)_jspx_page_context, null));
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
    _jspx_th_c_forEach_1.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${titleofdegrees}", java.lang.Object.class, (PageContext)_jspx_page_context, null));
    _jspx_th_c_forEach_1.setVar("titleofdegree");
    int[] _jspx_push_body_count_c_forEach_1 = new int[] { 0 };
    try {
      int _jspx_eval_c_forEach_1 = _jspx_th_c_forEach_1.doStartTag();
      if (_jspx_eval_c_forEach_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\n");
          out.write("                                        <option value=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${titleofdegree}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("\" > ");
          if (_jspx_meth_c_out_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_forEach_1, _jspx_page_context, _jspx_push_body_count_c_forEach_1))
            return true;
          out.write(" </option>\n");
          out.write("                                    ");
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
    _jspx_th_c_out_1.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${titleofdegree}", java.lang.Object.class, (PageContext)_jspx_page_context, null));
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
    _jspx_th_c_forEach_2.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${departments}", java.lang.Object.class, (PageContext)_jspx_page_context, null));
    _jspx_th_c_forEach_2.setVar("department");
    int[] _jspx_push_body_count_c_forEach_2 = new int[] { 0 };
    try {
      int _jspx_eval_c_forEach_2 = _jspx_th_c_forEach_2.doStartTag();
      if (_jspx_eval_c_forEach_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\n");
          out.write("                                        <option value=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${department.id}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("\" > ");
          if (_jspx_meth_c_out_2((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_forEach_2, _jspx_page_context, _jspx_push_body_count_c_forEach_2))
            return true;
          out.write(" </option>\n");
          out.write("                                    ");
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
    _jspx_th_c_out_2.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${department.departmentName.name}", java.lang.Object.class, (PageContext)_jspx_page_context, null));
    int _jspx_eval_c_out_2 = _jspx_th_c_out_2.doStartTag();
    if (_jspx_th_c_out_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_out_value_nobody.reuse(_jspx_th_c_out_2);
      return true;
    }
    _jspx_tagPool_c_out_value_nobody.reuse(_jspx_th_c_out_2);
    return false;
  }
}
