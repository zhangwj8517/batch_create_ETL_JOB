package batkettle.component;

import java.util.ArrayList;

import batkettle.datatype.*;

public class KtlTransformation {

	private KtlInfo ktlinfo;
	/*
	 * private KtlNotepads ktlnotepads; private KtlOrder ktlorder; private
	 * KtlStepTableInput ktlstepTableInput; private KtlStepInsertUpdate
	 * ktlstepInsertUpdate;
	 */
	private KtlStepErrorHandling ktlStepErrorHandling;
	private KtlSlaveStepCopyPartitionDistribution ktlSlaveStepCopyPartitionDistribution;
	private KtlSlaveTransformation ktlSlaveTransformation;
	private String ktlTransformationXML;
	private String ktldb_connectionXML = new String();
	private TableInfo table = new TableInfo();
	private KettleParameters kettleParameters = new KettleParameters();

	/*
	 * DbInfo dbinfo = new DbInfo("ICUBizDB", "172.30.100.117", "MSSQL",
	 * "Native", "ICUBizDB", "1433", "sa",
	 * "Encrypted 2be98afc86aa7f2e4b811aa7ccc9fb6c3");
	 */
	// 根据kettlet的参数（含原表名，目标表名），及源表字段信息及连接数据库信息，生成KETTLE xml格式文件。
	public KtlTransformation(KettleParameters kettleParameters, TableInfo tableinfo, ArrayList<DbInfo> dbinfos) {
		this.kettleParameters = kettleParameters;
		this.table = tableinfo;
		this.ktlinfo = new KtlInfo(kettleParameters);

		// 拼接kettle中使用到的数据源信息成xml
		for (int i = 0; i < dbinfos.size(); i++) {
			DbInfo dbinfo = dbinfos.get(i);
			KtlConnection ktldb_connection = new KtlConnection(dbinfo);
			ktldb_connectionXML = ktldb_connectionXML + ktldb_connection.getXML();
		}

		this.ktlStepErrorHandling = new KtlStepErrorHandling();
		this.ktlSlaveStepCopyPartitionDistribution = new KtlSlaveStepCopyPartitionDistribution();
		this.ktlSlaveTransformation = new KtlSlaveTransformation();
	}

	public String getFullLoadXML() {
		KtlNotepads ktlFullLoadNotepads = new KtlNotepads(kettleParameters.getTargetTable(), "430", "67");
		String from_step = new String(kettleParameters.getSourceTable());
		String to_step = new String(kettleParameters.getTargetTable());
		// kettle 中，step不能重名
		if (from_step.equals(to_step)) {
			from_step = "from: " + from_step;
			to_step = "to: " + to_step;

		}

		KtlOrder ktlorder = new KtlOrder();
		ktlorder.addFromToRelationship(from_step, to_step);

		// 全量同步，生成from step中的SQL
		String selectStepFullLoadSql = new String(SelectStepFullLoadSql());
		KtlStepTableInput ktlstepTableInput = new KtlStepTableInput(from_step, kettleParameters.getSource_conn(),
				selectStepFullLoadSql, "Y",null, "72", "58");
		KtlStepInsertUpdate ktlstepInsertUpdate = new KtlStepInsertUpdate(to_step, kettleParameters.getTarget_conn(),
				this.table, "312", "58");

		this.ktlTransformationXML = "";
		this.ktlTransformationXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + "<transformation>\n"
				+ this.ktlinfo.getXml() + ktlFullLoadNotepads.getXml() + this.ktldb_connectionXML + ktlorder.getXML()
				+ ktlstepTableInput.getXML() + ktlstepInsertUpdate.getXML() + this.ktlStepErrorHandling.getXML()
				+ this.ktlSlaveStepCopyPartitionDistribution.getXML() + this.ktlSlaveTransformation.getXML()
				+ "</transformation>\n";

		return ktlTransformationXML;
	}

	public String getCDCXML() {
		KtlNotepads ktlFullLoadNotepads = new KtlNotepads(kettleParameters.getTargetTable(), "788", "60");
		KtlStepTableInput stepLastUpDateTime = new KtlStepTableInput("LastUpDateTime",
				kettleParameters.getTarget_conn(), this.SelectLastUpdateTimeSql(), "Y",null, "63", "60");
		KtlStepTableInput stepCurrentUpdateTime = new KtlStepTableInput("CurrentUpDateTime",
				kettleParameters.getSource_conn(), this.SelectCurrentUpdateTimeSql(), "N", null,"222", "187");
		KtlStepTableInput stepCDCSource = new KtlStepTableInput(
				"fn_cdc_get_net_changes_dbo_" + kettleParameters.getSourceTable(), kettleParameters.getSource_conn(),
				this.SelectStepCDCSql(), "Y","SelectValues1", "510", "60");
		KtlStepInsertUpdate ktlstepInsertUpdate = new KtlStepInsertUpdate(kettleParameters.getTargetTable(),
				kettleParameters.getTarget_conn(), this.table, "750", "60");
		KtlStepMergeJoin stepMergeJoin = new KtlStepMergeJoin("MergeJoin", "LastUpDateTime", "CurrentUpDateTime");
		KtlStepSelectValues stepSelectValues = new KtlStepSelectValues("SelectValues1", "SelectValues2");
		KtlStepBlockUntilStepsFinish stepBlock = new KtlStepBlockUntilStepsFinish("BlockUntilStepsFinish",
				kettleParameters.getTargetTable(), "628", "187");

		KtlStepUpdate stepUpControl = new KtlStepUpdate("UPdate cdc_to_ods_table_control",
				kettleParameters.getTarget_conn());

		KtlOrder ktlorderCDC = new KtlOrder();
		ktlorderCDC.addFromToRelationship("CurrentUpDateTime", "BlockUntilStepsFinish");
		ktlorderCDC.addFromToRelationship("CurrentUpDateTime", "MergeJoin");
		ktlorderCDC.addFromToRelationship("MergeJoin", "SelectValues1");
		ktlorderCDC.addFromToRelationship("SelectValues1",
				"fn_cdc_get_net_changes_dbo_" + kettleParameters.getSourceTable());
		ktlorderCDC.addFromToRelationship("fn_cdc_get_net_changes_dbo_" + kettleParameters.getSourceTable(),
				kettleParameters.getTargetTable());
		ktlorderCDC.addFromToRelationship("BlockUntilStepsFinish", "SelectValues2");
		ktlorderCDC.addFromToRelationship("SelectValues2", "UPdate cdc_to_ods_table_control");
		ktlorderCDC.addFromToRelationship("LastUpDateTime", "MergeJoin");

		this.ktlTransformationXML = "";
		this.ktlTransformationXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + "<transformation>\n"
				+ this.ktlinfo.getXml() + ktlFullLoadNotepads.getXml() + this.ktldb_connectionXML + ktlorderCDC.getXML()
				+ stepCDCSource.getXML() + stepSelectValues.getXML() + ktlstepInsertUpdate.getXML()
				+ stepUpControl.getXML() + stepLastUpDateTime.getXML() + stepCurrentUpdateTime.getXML()
				+ stepMergeJoin.getXML() + stepBlock.getXML() + this.ktlStepErrorHandling.getXML()
				+ this.ktlSlaveStepCopyPartitionDistribution.getXML() + this.ktlSlaveTransformation.getXML()
				+ "</transformation>\n";

		return ktlTransformationXML;
	}

	private String SelectStepFullLoadSql() {
		String sqlStart = "select  &#xd;&#xa;";
		String sqlEnd = "&#xd;&#xa;&#xd;&#xa;&#xd;&#xa;    ,&#x27;&#x24;&#x7b;YLJGDM&#x7d;&#x27;  AS  yljgdm&#xd;&#xa;    ,&#x27;&#x24;&#x7b;SourceSys&#x7d;&#x27; as sys_id&#xd;&#xa;	,getdate&#x28;&#x29;       as    createtime          -- &#x66f4;&#x65b0;&#x65f6;&#x95f4;      &#xd;&#xa; 	,0 as isdelete&#xd;&#xa;&#xd;&#xa;  FROM  &#x24;&#x7b;SourceTable&#x7d;&#xd;&#xa;&#xd;&#xa;&#xd;&#xa;";
		String strColumns = new String();

		for (int i = 0; i < this.table.getColumns().size(); i++) {
			String column_blank = new String();
			String value = this.table.getColumns().get(i);
			// 第一个字段前不加逗号。
			if (0 == i) {
				column_blank = "     ";
			} else
				column_blank = "    ,";
			strColumns = strColumns + column_blank + "&#x5b;" + value + "&#x5d;     as &#x5b;" + value.toLowerCase()
					+ "&#x5d;  &#xd;&#xa;";
		}
		return sqlStart + strColumns + sqlEnd;
	}

	private String SelectStepCDCSql() {
		String sqlStart = "select  &#xd;&#xa;";
		String sqlEnd = "&#xd;&#xa;    ,&#x27;&#x24;&#x7b;YLJGDM&#x7d;&#x27;  AS  yljgdm&#xd;&#xa;    ,&#x27;&#x24;&#x7b;SourceSys&#x7d;&#x27; as sys_id&#xd;&#xa;	,getdate&#x28;&#x29;       as    createtime          -- &#x66f4;&#x65b0;&#x65f6;&#x95f4;       &#xd;&#xa;	,case &#xd;&#xa;		when &#x5b;__&#x24;operation&#x5d;  &#x3d; 1 then 1 &#xd;&#xa;		else 0&#xd;&#xa;	 end as    isdelete                        --&#x6e90;&#x6570;&#x636e;&#x662f;&#x5426;&#x5df2;&#x5220;&#x9664;&#xd;&#xa;&#xd;&#xa; FROM  &#x5b;cdc&#x5d;.&#x5b;fn_cdc_get_net_changes_dbo_&#x24;&#x7b;SourceTable&#x7d;&#x5d; &#x28; dbo.hexstr2varbin&#x28;&#x3f;&#x29; , dbo.hexstr2varbin&#x28;&#x3f;&#x29; ,&#x27;ALL&#x27;&#x29; NOLOCK&#xd;&#xa;where  &#x5b;__&#x24;start_lsn&#x5d; not in &#x28;&#xd;&#xa;	select &#x5b;__&#x24;start_lsn&#x5d; &#xd;&#xa;      from &#x5b;cdc&#x5d;.&#x5b;fn_cdc_get_net_changes_dbo_&#x24;&#x7b;SourceTable&#x7d;&#x5d; &#x28; dbo.hexstr2varbin&#x28;&#x3f;&#x29; , dbo.hexstr2varbin&#x28;&#x3f;&#x29; ,&#x27;ALL&#x27;&#x29; NOLOCK&#xd;&#xa;	 where &#x5b;__&#x24;operation&#x5d;  &#x3d; 1&#xd;&#xa;	group by &#x5b;__&#x24;start_lsn&#x5d;&#xd;&#xa;	having count&#x28;&#x5b;__&#x24;start_lsn&#x5d;&#x29;  &#x3e;&#x3d; &#x24;&#x7b;Dellimit&#x7d;&#xd;&#xa;&#x29;&#xd;&#xa;&#xd;&#xa;";
		String strColumns = new String();

		for (int i = 0; i < this.table.getColumns().size(); i++) {
			String column_blank = new String();
			String value = this.table.getColumns().get(i);
			// 第一个字段前不加逗号。
			if (0 == i) {
				column_blank = "     ";
			} else
				column_blank = "    ,";
			strColumns = strColumns + column_blank + "&#x5b;" + value + "&#x5d;     as &#x5b;" + value.toLowerCase()
					+ "&#x5d;  &#xd;&#xa;";
		}
		return sqlStart + strColumns + sqlEnd;
	}

	private String SelectLastUpdateTimeSql() {
		String LastUpTimeSql = "select &#xd;&#xa;  t.lsn   as begin_lsn&#xd;&#xa;,1 as  c_order&#xd;&#xa;from cdc_to_ods_table_control t&#xd;&#xa;where source_system_name &#x3d; &#x27;&#x24;&#x7b;SourceSys&#x7d;&#x27;&#xd;&#xa;and source_table_name &#x3d; &#x27;&#x24;&#x7b;SourceTable&#x7d;&#x27;&#xd;&#xa;and  target_table_name  &#x3d; &#x27;&#x24;&#x7b;TargetTable&#x7d;&#x27;&#xd;&#xa;";
		return LastUpTimeSql;
	}

	private String SelectCurrentUpdateTimeSql() {
		String currentUpdateTimeSql = "select top 1 &#xd;&#xa;  sys.fn_varbintohexstr&#x28;max&#x28;t.__&#x24;start_lsn&#x29;&#x29;  as  end_lsn, &#xd;&#xa; 1 as c_order , &#xd;&#xa; &#x27;&#x24;&#x7b;SourceSys&#x7d;&#x27; as source_system_name,&#xd;&#xa; &#x27;&#x24;&#x7b;SourceTable&#x7d;&#x27; as source_table_name,&#xd;&#xa; &#x27;&#x24;&#x7b;TargetTable&#x7d;&#x27; as target_table_name,&#xd;&#xa; getdate&#x28;&#x29;  as  last_updatetime&#xd;&#xa;from &#x5b;cdc&#x5d;.dbo_&#x24;&#x7b;SourceTable&#x7d;_CT t&#xd;&#xa;";
		return currentUpdateTimeSql;
	}

}
