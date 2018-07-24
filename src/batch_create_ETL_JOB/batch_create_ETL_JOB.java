package batch_create_ETL_JOB;

import java.sql.*;
import java.io.FileOutputStream;
import batkettle.datatype.*;
import java.util.*;
import batkettle.component.*;
import java.io.*;

public class batch_create_ETL_JOB {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final String runPath = "D:\\ETL_ODS方案优化\\batch_create_ETL_JOB\\";
		//final String runPath = "./";
		final String connectionCfgPath = runPath + "connection.cfg.txt";
		final String tableListCfgPath = runPath + "table_list.cfg.txt";
		final String DbCfgPath = runPath + "database.cfg.txt";

		try {
			// read connection configure info
			ConnectCfg connectCfg = ReadConfigureFile.readConnInfo(connectionCfgPath);

			// read tableListCfgPath configure info for every kettle job
			// parameters
			ArrayList<KettleParameters> tableListKettleParameters = ReadConfigureFile
					.readTableListInfo(tableListCfgPath);

			// read DataBaseCfg info
			ArrayList<DbInfo> dbinfos = ReadConfigureFile.readDataBasesInfo(DbCfgPath);

			// create connection info
			Connection conn = dbConn.get_conn(connectCfg.getSourceServer(), connectCfg.getSourcePort(), connectCfg.getInstanceName(),
					connectCfg.getSourceDb(), connectCfg.getSourceUser(), connectCfg.getSourcePwd());

			// 按表清单逐表组合kettle job xml文件
			for (int i = 0; i < tableListKettleParameters.size(); i++) {
				String fullLoadFile = runPath + tableListKettleParameters.get(i).getTargetTable() + ".full.xml";
				String cdcLoadFile = runPath + tableListKettleParameters.get(i).getTargetTable() + ".cdc.xml";
				TableInfo tableInfo = new TableInfo();
				tableInfo.readTableInfo(conn, tableListKettleParameters.get(i).getSourceTable());

				KtlTransformation ktlTransformation = new KtlTransformation(tableListKettleParameters.get(i), tableInfo,
						dbinfos);
				// full load xml

				String outString = ktlTransformation.getFullLoadXML();
				//System.out.println(outString);
				FileOutputStream outSTr = new FileOutputStream(new File(fullLoadFile));
				outSTr.write(outString.getBytes("utf-8"));
				outSTr.close();

				// cdc xml
				String cdcoutString = ktlTransformation.getCDCXML();
				FileOutputStream cdcoutSTr = new FileOutputStream(new File(cdcLoadFile));
				cdcoutSTr.write(cdcoutString.getBytes("utf-8"));
				cdcoutSTr.close();
			}

			//

		} catch (Exception e) {
			System.out.println(e);

		}

	}

}
