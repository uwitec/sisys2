package com.sisys.test;

import java.io.*;
import java.text.*;
import java.util.*;

public class test {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws IOException {
		/*String cmdString[] = { "D://PowerCmd//PowerCmd.exe", "ipconfig" };
		// String command="cmd /k dir";
		System.out.println("本程序将执行定时提醒功能");
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		System.out.println(c.get(Calendar.YEAR) + "年"
				+ (c.get(Calendar.MONTH) + 1) + "月" + c.get(Calendar.DATE)
				+ "日");
		System.out.println(c.get(Calendar.HOUR_OF_DAY) + ":"
				+ c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND));
		int h = date.getDate();
		int m = date.getMinutes();
		int s = date.getSeconds();
		System.out.println("h=" + h + ":m=" + m + ":s=" + s);

		// Runtime.getRuntime().exec("cmd.exe /c D://Program/"
		// /"Files//Tencent//QQMusic//QQMusic.exe") ;
		// Runtime.getRuntime().exec("regedit.exe");
		// Runtime.getRuntime().exec("notepad.exe");
		// Runtime.getRuntime().exec("cmd.exe /c E://软件工程.doc");

		String command = "netstat";*/
		File f = new File("E:/360data/java3/sisys_beta2.0/input.tex");
		String outputPath = "E:/360data/java3/sisys_beta2.0/gd.pdf";
		FileWriter fw =  new FileWriter(f);
		fw.write("010104\r\n368曲轴正时皮带轮\r\n2012081302\r\n2001\r\n李一三五");
		fw.close();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date(f.lastModified());
		String command = "cmd.exe /c start scgd input.tex gd.pdf";
		Runtime rt = Runtime.getRuntime();
		Process p = rt.exec(command);
		try{
		    Thread.sleep(15000);
		}catch(Exception e){
		}
		File f2 = new File(outputPath);
		Date date1 = new Date(f2.lastModified());
		System.out.println(df.format(date1));
		System.out.println(df.format(new Date()));
		
		// Process p=r.exec(command);

		/*BufferedReader br = new BufferedReader(new InputStreamReader(
				p.getInputStream()));

		StringBuffer sb = new StringBuffer();
		String inline;
		while (null != (inline = br.readLine())) {
			sb.append(inline).append("/n");
		}
		System.out.println(sb.toString());*/
	}
}
