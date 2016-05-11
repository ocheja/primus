/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.util;

import com.lowagie.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

/**
 *
 * @author Olisa
 */
public class IreportService {

    public synchronized byte[] getReport(String reportRelativePath, Map parameters, Collection dataSource1, ServletContext servletContext) throws Exception {
        Map parameter = new HashMap();
        String jasperPath = servletContext.getRealPath(reportRelativePath);
        //  this.log.debug("MAIN REPORT REAL PATH " + jasperPath);

        JasperDesign jasperDesign = JRXmlLoader.load(new File(jasperPath));
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        // jasperReport =  JRLoader.loadObject(new File(jasperPath));
        JRParameter[] jRParameters = jasperReport.getParameters();
        for (JRParameter jRParameter : jRParameters) {
            if (!jRParameter.isSystemDefined()) {
                if (parameters.containsKey(jRParameter.getName())) {
                    parameter.put(jRParameter.getName(), parameters.get(jRParameter.getName()));
                }
            }
        }
        JRDataSource datasource = new JRBeanCollectionDataSource(dataSource1, true);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, datasource);
        JRPdfExporter exporter = new JRPdfExporter();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        exporter.setParameter(JRPdfExporterParameter.OUTPUT_STREAM, baos);
        exporter.setParameter(JRPdfExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRPdfExporterParameter.IS_ENCRYPTED, Boolean.TRUE);
        exporter.setParameter(JRPdfExporterParameter.IS_128_BIT_KEY, Boolean.TRUE);
        exporter.setParameter(JRPdfExporterParameter.PERMISSIONS, new Integer(PdfWriter.ALLOW_PRINTING));
        exporter.exportReport();

          return baos.toByteArray();
       // return JasperRunManager.runReportToPdf(jasperReport, parameter, datasource);
    }
}
