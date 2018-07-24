package batkettle.component;

import batkettle.datatype.TableInfo;

public class KtlStepTableInput {
	private String stepName = new String();
	private final String type = "\t<type>TableInput</type>\n";
	private String description = "\t<description/>\n";
	private String distribute = new String();
	private String custom_distribution = "\t<custom_distribution/>\n";
	private String copies = "\t<copies>1</copies>\n";
	private String partitioning = "\t<partitioning>\n\t<method>none</method>\n\t<schema_name/>\n\t</partitioning>\n";
	private String connetion = new String();
	private String sql = new String();
	private String limit = "\t<limit>0</limit>\n";
	private String lookup = new String();// "\t<lookup/>\n";
	private String execute_each_row = "\t<execute_each_row>N</execute_each_row>\n";
	private String variables_active = "\t<variables_active>Y</variables_active>\n";
	private String lazy_conversion_active = "\t<lazy_conversion_active>N</lazy_conversion_active>\n";
	private String cluster_schema = "\t<cluster_schema/>\n";
	private String remotesteps = "<remotesteps>   <input>   </input>   <output>   </output> </remotesteps>\n";
	private String GUI = new String();// "\t\t<GUI>\n\t\t<xloc>72</xloc>\n\t\t<yloc>58</yloc>\n\t\t<draw>Y</draw>\n\t\t</GUI>\n";
	// private TableInfo table = new TableInfo();
	private String KtlTableInputStepXML;

	public KtlStepTableInput(String stepName, String connection, String sql, String description, String lookupStep,
			String xloc, String yloc) {
		this.distribute = "\t<distribute>" + description + "</distribute>\n";
		this.stepName = "\t<name>" + stepName + "</name>\n";
		this.connetion = "\t<connection>" + connection + "</connection>\n";
		this.sql = "\t<sql>" + sql + "</sql>\n";

		if (lookupStep != null) {
			this.lookup = "\t<lookup>" + lookupStep + "</lookup>\n";
		} else {
			this.lookup = "\t<lookup/>\n";
		}

		this.GUI = "\t\t<GUI>\n\t\t<xloc>" + xloc + "</xloc>\n\t\t<yloc>" + yloc
				+ "</yloc>\n\t\t<draw>Y</draw>\n\t\t</GUI>\n";
	}

	public String getXML() {
		this.KtlTableInputStepXML = "\t<step>\n" + this.stepName + this.type + this.description + this.distribute
				+ this.custom_distribution + this.copies + this.partitioning + this.connetion + this.sql + this.limit
				+ this.lookup + this.execute_each_row + this.variables_active + this.lazy_conversion_active
				+ this.cluster_schema + this.remotesteps + this.GUI + "\t</step>\n";
		return KtlTableInputStepXML;
	}

}
