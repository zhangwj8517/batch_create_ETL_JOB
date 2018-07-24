package batkettle.component;

public class KtlStepBlockUntilStepsFinish {

	private String stepName = new String();
	private final String type = "\t<type>BlockUntilStepsFinish</type>\n";
	private String description = "\t<description/>\n";
	private String distribute = "\t<distribute>Y</distribute>\n";
	private String custom_distribution = "\t<custom_distribution/>\n";
	private String copies = "\t<copies>1</copies>\n";
	private String partitioning = "\t<partitioning>\n\t<method>none</method>\n\t<schema_name/>\n\t</partitioning>\n";

	private String cluster_schema = "\t<cluster_schema/>\n";
	private String remotesteps = "<remotesteps>   <input>   </input>   <output>   </output> </remotesteps>\n";
	private String GUI = new String();
	// private TableInfo table = new TableInfo();
	private String KtlTableInputStepXML;
	private String blockSteps = new String();

	public KtlStepBlockUntilStepsFinish(String stepName, String blockStep, String xloc, String yloc) {

		this.stepName = "\t<name>" + stepName + "</name>\n";

		this.blockSteps = "\t<steps>\n\t\t<step>\n\t\t<name>" + blockStep
				+ "</name>\n\t\t<CopyNr>0</CopyNr>\n\t\t</step>\n\t\t</steps>\n";
		this.GUI = "\t\t<GUI>\n\t\t<xloc>" + xloc + "</xloc>\n\t\t<yloc>" + yloc
				+ "</yloc>\n\t\t<draw>Y</draw>\n\t\t</GUI>\n";
	}

	public String getXML() {
		this.KtlTableInputStepXML = "\t<step>\n" + this.stepName + this.type + this.description + this.distribute
				+ this.custom_distribution + this.copies + this.partitioning + this.blockSteps + this.cluster_schema
				+ this.remotesteps + this.GUI + "\t</step>\n";
		return KtlTableInputStepXML;
	}

}
