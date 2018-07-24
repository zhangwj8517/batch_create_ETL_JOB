package batkettle.component;

import java.util.*;
import batkettle.datatype.*;

public class KtlStepInsertUpdate {
	private String name = new String();
	private final String type = "\t<type>InsertUpdate</type>\n";
	private String description = "\t<description/>\n";
	private String distribute = "\t<distribute>Y</distribute>\n";
	private String custom_distribution = "\t<custom_distribution/>\n";
	private String copies = "\t<copies>1</copies>\n";
	private String partitioning = "\t<partitioning>\n\t<method>none</method>\n\t<schema_name/>\n\t</partitioning>\n";
	private String connetion = new String();
	private String commit = "\t<commit>1000</commit>\n";
	private String update_bypassed = "\t<update_bypassed>N</update_bypassed>\n";
	private String lookup = new String();
	private String cluster_schema = "\t<cluster_schema/>\n";
	private String remotesteps = "<remotesteps>   <input>   </input>   <output>   </output> </remotesteps>\n";
	private String GUI = new String();// "\t\t<GUI>\n\t\t<xloc>312</xloc>\n\t\t<yloc>58</yloc>\n\t\t<draw>Y</draw>\n\t\t</GUI>\n";

	// private TableInfo table = new TableInfo();
	private String KtlTableInputStepXML;

	public KtlStepInsertUpdate(String name, String connection, TableInfo tableinfo, String xloc, String yloc) {
		String tablename;
		String columns = new String();
		String keycolumn = new String();

		// this.table =tableinfo ;

		/// 进ODS，新增字段
		ArrayList<String> updateColumns = new ArrayList<String>(tableinfo.getColumns());
		ArrayList<String> updateKeyColumns = new ArrayList<String>(tableinfo.getKeyColumn());
		updateColumns.add("yljgdm");
		updateColumns.add("sys_id");
		updateColumns.add("createtime");
		updateColumns.add("isdelete");
		/// timetemp 不可更新
		updateColumns.remove("timetemp");
		//
		updateKeyColumns.add("yljgdm");
		updateKeyColumns.add("sys_id");



		this.name = "\t<name>" + name + "</name>\n";
		this.connetion = "\t<connection>" + connection + "</connection>\n";
		tablename = "\t<table>" + tableinfo.getTableName() + "</table>\n";

		for (int i = 0; i < updateKeyColumns.size(); i++) {
			String value = updateKeyColumns.get(i).toLowerCase();
			keycolumn = keycolumn + "\t<key>\n" + "\t\t<name>" + value + "</name>\n" + "\t\t<field>" + value
					+ "</field>\n" + "\t\t<condition>&#x3d;</condition>\n" + "\t\t<name2/>\n" + "\t\t</key>\n";
		}

		for (int i = 0; i < updateColumns.size(); i++) {
			String value = updateColumns.get(i).toLowerCase();
			columns = columns + "\t<value>\n" + "\t\t<name>" + value + "</name>\n" + "\t\t<rename>" + value
					+ "</rename>\n" + "\t\t<update>Y</update>\n" + "\t\t</value>\n";
		}

		this.lookup = "\t<lookup>\n" + "\t\t<schema/>\n" + tablename + keycolumn + columns + "\t</lookup>\n";
		this.GUI = "\t\t<GUI>\n\t\t<xloc>" + xloc + "</xloc>\n\t\t<yloc>" + yloc
				+ "</yloc>\n\t\t<draw>Y</draw>\n\t\t</GUI>\n";
	}

	public String getXML() {
		this.KtlTableInputStepXML = "\t<step>\n" + this.name + this.type + this.description + this.distribute
				+ this.custom_distribution + this.copies + this.partitioning + this.connetion + this.commit
				+ this.update_bypassed + this.lookup + this.cluster_schema + this.remotesteps + this.GUI
				+ "\t</step>\n";
		return KtlTableInputStepXML;
	}

 
}
