package com.managementkasmasjid.utils;

import com.managementkasmasjid.dto.request.DownloadFileRequest;
import com.managementkasmasjid.entity.GlobalParam;
import com.managementkasmasjid.entity.Transaction;
import com.managementkasmasjid.service.TransactionService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ReportUtil {

    @Autowired
    private TransactionService transactionService;

    public JasperPrint exportReport(GlobalParam globalParam, DownloadFileRequest downloadFileRequest) throws IOException, JRException, JRException {
        System.out.println("downloadFileRequest = " + downloadFileRequest.toString());
        List<Transaction> transactions = transactionService.getAllByDate(globalParam, downloadFileRequest);
        //load file and compile it
        File file = ResourceUtils.getFile("classpath:Transaksi.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(transactions);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("imgPath",ResourceUtils.getFile("classpath:logo.png").getAbsolutePath());
        parameters.put("fromDate", downloadFileRequest.getFromDate());
        parameters.put("untilDate", downloadFileRequest.getUntilDate());
        parameters.put("category", downloadFileRequest.getCategory().getParamDesc());
        parameters.put("createdBy", "Simbazka Karawang");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        return jasperPrint;
    }
}
