package batkettle.component;

public class KtlStepUpdate {

	private String stepName = new String();
	private String stepConnName = new String();
	private String ktlStepUpdateXML = new String();

	public  KtlStepUpdate(String StepName, String connName) {
		this.stepName = StepName;
		this.stepConnName = connName;
	}

	public String getXML() {

		ktlStepUpdateXML = "\t<step>\n\t\t<name>" + this.stepName
				+ "</name>\n\t\t<type>Update</type>\n\t\t<description/>\n\t\t<distribute>Y</distribute>\n\t\t<custom_distribution/>\n\t\t<copies>1</copies>\n\t\t\t\t <partitioning>\n\t\t\t\t\t <method>none</method>\n\t\t\t\t\t <schema_name/>\n\t\t\t\t\t </partitioning>\n"
				+ "\t\t<connection>" + this.stepConnName
				+ "</connection>\n\t\t<skip_lookup>N</skip_lookup>\n\t\t<commit>1</commit>\n\t\t<use_batch>N</use_batch>\n\t\t<error_ignored>N</error_ignored>\n\t\t<ignore_flag_field/>\n\t\t<lookup>\n\t\t\t<schema/>\n\t\t\t<table>cdc_to_ods_table_control</table>\n\t\t\t<key>\n\t\t\t\t<name>source_system_name</name>\n\t\t\t\t<field>source_system_name</field>\n\t\t\t\t<condition>&#x3d;</condition>\n\t\t\t\t<name2/>\n\t\t\t\t</key>\n\t\t\t<key>\n\t\t\t\t<name>source_table_name</name>\n\t\t\t\t<field>source_table_name</field>\n\t\t\t\t<condition>&#x3d;</condition>\n\t\t\t\t<name2/>\n\t\t\t\t</key>\n\t\t\t<key>\n\t\t\t\t<name>target_table_name</name>\n\t\t\t\t<field>target_table_name</field>\n\t\t\t\t<condition>&#x3d;</condition>\n\t\t\t\t<name2/>\n\t\t\t\t</key>\n\t\t\t<value>\n\t\t\t\t<name>lsn</name>\n\t\t\t\t<rename>end_lsn</rename>\n\t\t\t\t</value>\n\t\t\t<value>\n\t\t\t\t<name>last_updatetime</name>\n\t\t\t\t<rename>last_updatetime</rename>\n\t\t\t\t</value>\n\t\t\t</lookup>\n\t\t <cluster_schema/>\n <remotesteps>\t <input>\t </input>\t <output>\t </output> </remotesteps>\t\t<GUI>\n\t\t\t<xloc>900</xloc>\n\t\t\t<yloc>187</yloc>\n\t\t\t<draw>Y</draw>\n\t\t\t</GUI>\n\t\t</step>\n";
		return ktlStepUpdateXML;
	}

}
