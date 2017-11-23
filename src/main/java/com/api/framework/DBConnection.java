package com.api.framework;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
//import org.postgresql.jdbc2.optional.ConnectionPool;

interface DataBaseConnection {

}

public class DBConnection {

	public static String Database_url = "";

	/*
	 * public static String UserName="ihisblank"; public static String
	 * Password="ihisblank";
	 */

	public static String UserName = "root";
	public static String Password = "root";
	public static String FileName = "/Users/nisum/Downloads/APIAutomation/Nisum.xls";
	public static String url = ResourceReader.getProperty("InMemoryConnenctionURL");
	public static String JdbcDriver = ResourceReader.getProperty("DriverName");

	private static List<List<String>> dataList = new ArrayList<List<String>>();

	public static List<List<String>> executeQuery(String Query) throws Exception {
		dataList.removeAll(dataList);
		// DBConnection.UserName=ExcelLib.getExcelData("DBInput", 1, 1);
		// DBConnection.Password=ExcelLib.getExcelData("DBInput", 1, 2);
		// try {
		// // Class.forName(JdbcDriver).newInstance();
		//
		// } catch (ClassNotFoundException e11) {
		//
		// //e11.printStackTrace();
		// }
		try {
			Connection con = InMemoryDBUtil.getInMemoryDBConnection(true);
			InMemoryDBUtil.executeQueriesFromFile("queryList");
			Statement stmnt = con.createStatement();
			System.err.println("[Database: Query]" + Query);
			ResultSet set = stmnt.executeQuery(Query);
			ResultSetMetaData metadata = set.getMetaData();
			int count = metadata.getColumnCount();
			System.out.println("[Database] Number of Columns:" + count);
			// System.out.println(metadata);

			String data = "";
			List<String> colmns = new ArrayList<String>();
			for (int i = 1; i <= count; i++) {
				data = "";
				data = metadata.getColumnName(i);
				colmns.add(data);
			}

			System.out.println("The coloumn is" + colmns);

			dataList.add(colmns);
			while (set.next()) {
				List<String> list = new ArrayList<String>();

				for (int j = 1; j <= count; j++) {
					list.add(set.getString(j));

				}
				dataList.add(list);
				// System.out.println();
			}

			con.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		System.out.println("The datalst is" + dataList);
		return dataList;
	}

	public static void copyToExcelFromData(String Query) {
		try {
			// try {
			//
			// Class.forName(JdbcDriver).newInstance();
			//
			// } catch (ClassNotFoundException e11) {
			//
			// e11.printStackTrace();
			// }
			int count = 0;
			try {

				Connection con = InMemoryDBUtil.getInMemoryDBConnection(true);
				InMemoryDBUtil.executeQueriesFromFile("queryList");
				Statement st = con.createStatement();
				ResultSet set = st.executeQuery(Query);
				System.out.println(set);

				ResultSetMetaData metadata = set.getMetaData();
				count = metadata.getColumnCount();
				System.out.println("[Database] Number of Columns:" + count);
				System.out.println(metadata);

				String data = "";
				List<String> colmns = new ArrayList<String>();
				colmns.retainAll(colmns);
				for (int i = 1; i <= count; i++) {
					data = "";
					data = metadata.getColumnName(i);
					colmns.add(data);
				}

				System.out.println("The columns are" + colmns);
				FileInputStream fis = new FileInputStream(FileName);
				HSSFWorkbook workbook = (HSSFWorkbook) WorkbookFactory.create(fis);
				HSSFSheet sheet = workbook.getSheet("DBOutput");

				HSSFRow rowhead = sheet.createRow((short) 0);
				for (int i = 0; i < count; i++) {

					rowhead.createCell((short) +i).setCellValue(colmns.get(i));

				}

				int m = 1;

				while (set.next()) {

					HSSFRow row = sheet.createRow((short) +m);
					for (int j = 0; j < count; j++) {
						try {
							row.createCell((short) +j).setCellValue(set.getString(colmns.get(j)));
						} catch (Exception e) {
							try {
								row.createCell((short) +j).setCellValue(set.getInt(colmns.get(j)));
							} catch (Exception e1) {
								try {
									row.createCell((short) +j).setCellValue(set.getDate(colmns.get(j)));
								} catch (Exception e2) {
									row.createCell((short) +j).setCellValue(set.getFloat(colmns.get(j)));

								}
							}

						}

					}
					// row.createCell((short)
					// 0).setCellValue(rs.getString("rc_domain"));
					m++;
				}

				// String yemi =
				// "/Users/nisum/Documents/Projects/AutomationFramework/Result/NetLock.xls";
				FileOutputStream fileOut;

				fileOut = new FileOutputStream(FileName);
				System.out.println("Data copied to Excel!!!");
				workbook.write(fileOut);
				fileOut.close();
			} catch (Exception e23) {
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
