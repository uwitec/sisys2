package com.sisys.test;

import java.io.*;
import java.text.*;
import java.util.*;

import com.sisys.dao.*;
import com.sisys.bean.*;

public class test {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws IOException {
		
		
	/*	File f = new File("E:/360data/java3/sisys_beta2.0/input.tex");
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
		System.out.println(df.format(new Date()));*/
		
	
	
		//String command = "cmd.exe /k start scgd input.tex gd.pdf>a.txt";
		/*test t = new test();
		t.test();*/
		String sql = "select * from product";
		ProductDAO prod = new ProductDAO();
		List<Product> pro = prod.findEntityByList(sql);
		for (int i = 0; i < pro.size(); i++) {
			ProHash proh = new ProHash();
			proh.setProNo(pro.get(i).getProNo());
			proh.setDate("");
			ProHashDAO prohd = new ProHashDAO();
			prohd.create(proh);
		}
		/*String sql = "select * from prohash";
		ProHashDAO prohd = new ProHashDAO();
		List<ProHash> prohl = prohd.findEntityByList(sql);
		for (int i = 0; i < prohl.size(); i++) {
			prohl.get(i).setId(prohl.get(i).getId() - 2660);
			ProHashDAO prohdao = new ProHashDAO();
			prohdao.create(prohl.get(i));
		}*/
			
	}
	public void test() {
		String command = "cmd.exe /k tasklist";
		Runtime rt = Runtime.getRuntime();
			try {
				Process process = rt.exec(command);
				InputStream in = process.getInputStream();      
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
				String s;
				while ((s = br.readLine()) != null) {    
		            System.out.println(s);    
		        }    
		        in.close();  
		        process.waitFor();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}