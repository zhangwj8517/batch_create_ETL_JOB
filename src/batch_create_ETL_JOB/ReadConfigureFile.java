package batch_create_ETL_JOB;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

import batkettle.datatype.*;

public class ReadConfigureFile {

	public static ArrayList<KettleParameters> readTableListInfo(String filePath) {
		ArrayList<KettleParameters> tableListInfo = new ArrayList<KettleParameters>();
		try {
			String encoding = "GBK";
			String[] TableInfo;

			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					KettleParameters kettleParameters = new KettleParameters();
					TableInfo = lineTxt.split("\t");
					if (TableInfo[0].equals("SourceSys"))
						continue;
					kettleParameters.setSourceSys(TableInfo[0]);
					kettleParameters.setSourceTable(TableInfo[1]);
					kettleParameters.setTargetTable(TableInfo[2]);
					kettleParameters.setYljgdm(TableInfo[3]);
					kettleParameters.setDellimit(TableInfo[4]);
					kettleParameters.setSource_conn(TableInfo[5]);
					kettleParameters.setTarget_conn(TableInfo[6]);
					tableListInfo.add(kettleParameters);
					// System.out.println(lineTxt);
				}
				read.close();
				return tableListInfo;
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}

		return null;
	}

	public static ConnectCfg readConnInfo(String filePath) {
		//

		try {
			String encoding = "GBK";

			String lineInfo[];
			ConnectCfg connectcfg = new ConnectCfg();

			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;

				while ((lineTxt = bufferedReader.readLine()) != null) {

					lineInfo = lineTxt.split("=");
					if (lineInfo.length == 2) {
						if (lineInfo[0].equals("souce_connection")) {
							connectcfg.setSouceConnection(lineInfo[1]);
						}
						if (lineInfo[0].equals("target_connnection")) {
							connectcfg.setTargetConnnection(lineInfo[1]);
						}
						if (lineInfo[0].equals("timepoint_connection")) {
							connectcfg.setTimepointConnection(lineInfo[1]);
						}
						if (lineInfo[0].equals("source_server")) {
							connectcfg.setSourceServer(lineInfo[1]);
						}
						if (lineInfo[0].equals("instanceName")) {
							connectcfg.setSourcePort(lineInfo[1]);
						}
						if (lineInfo[0].equals("source_db")) {
							connectcfg.setSourceDb(lineInfo[1]);
						}
						if (lineInfo[0].equals("source_user")) {
							connectcfg.setSourceUser(lineInfo[1]);
						}
						if (lineInfo[0].equals("source_pwd")) {
							connectcfg.setSourcePwd(lineInfo[1]);
						}

						// System.out.println(lineInfo[1]);

					}

				}
				read.close();
				return connectcfg;
			} else {
				System.out.println("找不到指定的文件");

			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}
		return null;

	}

	public static ArrayList<DbInfo> readDataBasesInfo(String filePath) {
		ArrayList<DbInfo> databasesInfo = new ArrayList<DbInfo>();
		try {
			String encoding = "GBK";
			String[] TableInfo;

			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					// DbInfo DbInfo = new DbInfo();

					TableInfo = lineTxt.split("\t");
					if (TableInfo[0].equals("name"))
						continue;

					String name = TableInfo[0];
					String server = TableInfo[1];
					String type = TableInfo[2];
					String access = TableInfo[3];
					String database = TableInfo[4];
			//		String port = TableInfo[5];
					String instanceName = TableInfo[5];
					String username = TableInfo[6];
					String password = TableInfo[7];
					DbInfo dbInfo = new DbInfo(name, server, type, access, database,   instanceName,username, password);
					databasesInfo.add(dbInfo);
					// System.out.println(lineTxt);
				}
				read.close();
				return databasesInfo;
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}

		return null;
	}
	
}
