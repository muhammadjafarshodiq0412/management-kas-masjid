package com.managementkasmasjid;

import com.managementkasmasjid.constant.GlobalConstant;
import com.managementkasmasjid.dto.request.DownloadFileRequest;
import com.managementkasmasjid.entity.GlobalParam;
import com.managementkasmasjid.service.GlobalParamService;
import com.managementkasmasjid.service.TransactionService;
import com.managementkasmasjid.service.impl.TransactionServiceImpl;
import com.managementkasmasjid.utils.NominalUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

//@SpringBootTest
class ManagementKasMasjidApplicationTests {
	private TransactionServiceImpl transactionService;

//	@Test
	void contextLoads() {
		System.out.println(new SimpleDateFormat("dd-mm-yyyy").format(new Date()));
	}

	public static void main(String[] args) throws ParseException, FileNotFoundException {
//		System.out.println("NominalUtils.nominalToIdrCurrency(100000L) = " + NominalUtils.nominalToIdrCurrency(100000L));
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTimeZone(TimeZone.getTimeZone("Asia/Jakarta"));
//		calendar.setTime(new SimpleDateFormat("Y-m-dd").parse("18-08-2021"));
		String sDate2 = "31-10-2021";
		System.out.println("parse = " + new SimpleDateFormat("Y-m-dd").parse(sDate2));
//		String path = ResourceUtils.getFile("classpath:logo.png").getAbsolutePath();

//		System.out.println(path);

	}

}
