package com.managementkasmasjid.controller.report;

import com.managementkasmasjid.constant.GlobalConstant;
import com.managementkasmasjid.dto.request.DownloadFileRequest;
import com.managementkasmasjid.entity.GlobalParam;
import com.managementkasmasjid.entity.Transaction;
import com.managementkasmasjid.service.GlobalParamService;
import com.managementkasmasjid.service.TransactionService;
import com.managementkasmasjid.utils.ReportUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.ParseException;
import java.util.List;

@Controller
@RequestMapping("/report/transaction")
public class TransactionReportController {
    @Autowired
    private ReportUtil reportUtil;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private GlobalParamService globalParamService;

    @GetMapping("")
    public String showDownloadDocument(@ModelAttribute("data") DownloadFileRequest request, Model model) {
        List<GlobalParam> params = globalParamService.getAllByCondition(GlobalConstant.CATEGORY_TRANSACTION);
        model.addAttribute("params", params);
        return "pages/report/transaction/index";
    }

    @PostMapping("/view-report")
    public String viewReport(@ModelAttribute("data") DownloadFileRequest request, Model model) {
        GlobalParam param = globalParamService.getByParamConditionAndParamDesc(GlobalConstant.CATEGORY_TRANSACTION, request.getCategory().getParamDesc());
        List<Transaction> data = transactionService.getAllByDate(param, request);
        if (request.getCategory().getParamDesc().equalsIgnoreCase(GlobalConstant.PENERIMAAN)) {
            model.addAttribute("category", "Penerimaan");
        }
        if (request.getCategory().getParamDesc().equalsIgnoreCase(GlobalConstant.PENGELUARAN)) {
            model.addAttribute("category", "Pengeluaran");
        }
        String fromDate = request.getFromDate();
        String untilDate = request.getUntilDate();
        model.addAttribute("data", data);
        model.addAttribute("fromDate", fromDate);
        model.addAttribute("untilDate", untilDate);
        return "pages/report/transaction/view";
    }

    @GetMapping("/{category}/{format}/{from}/{until}")
    public void downloadDocument(@PathVariable("category") String category,
                                 @PathVariable("format") String format,
                                 @PathVariable("from") String from,
                                 @PathVariable("until") String until,
                                 HttpServletResponse response) throws IOException, JRException, ParseException {
        //PARAMETER
        GlobalParam param = globalParamService.getByParamConditionAndParamDesc(GlobalConstant.CATEGORY_TRANSACTION, category.toUpperCase());
        DownloadFileRequest request = new DownloadFileRequest();
        request.setFromDate(from);
        request.setUntilDate(until);
        request.setCategory(param);
        //SET RESPONSE
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String fileName = "Laporan Transaksi";
        String headerValue = "attachement; filename=" + fileName.concat("." + format);
        response.setHeader(headerKey, headerValue);

        JasperPrint jasperPrint = this.reportUtil.exportReport(param, request);
        OutputStream outStream = response.getOutputStream();
        if (format.equalsIgnoreCase("xlsx")) {
            JRXlsExporter exporter = new JRXlsExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outStream));
            SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
            configuration.setOnePagePerSheet(true);
            configuration.setDetectCellType(true);
            configuration.setCollapseRowSpan(false);
            exporter.setConfiguration(configuration);
            exporter.exportReport();
        }
        if (format.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, String.valueOf(outStream));
        }
        if (format.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);

        }
        outStream.close();
    }

}
