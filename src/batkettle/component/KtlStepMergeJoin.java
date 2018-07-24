package batkettle.component;

public class KtlStepMergeJoin {
	private String name = new String();
	private String joinStepName1 = new String();
	private String joinStepName2 = new String();

	// 为尽快实现增量，SelectValues组件暂时只用String变量实现。
	public KtlStepMergeJoin(String stepName, String joinStepName1, String joinStepName2) {
		this.name = stepName;
		this.joinStepName1 = joinStepName1;
		this.joinStepName2 = joinStepName2;
	}

	public String getXML() {
		String ktlStepMergeJoin = "\t<step>\n\t<name>" + this.name
				+ "</name>\n\t<type>MergeJoin</type>\n\t<description/>\n\t<distribute>Y</distribute>\n\t<custom_distribution/>\n\t<copies>1</copies>\n\t\t <partitioning>\n\t\t   <method>none</method>\n\t\t   <schema_name/>\n\t\t   </partitioning>\n<join_type>INNER</join_type>\n"
				+ "<step1>" + this.joinStepName1 + "</step1>\n" + "<step2>" + this.joinStepName2 + "</step2>\n"
				+ "\t<keys_1>\n\t  <key>c_order</key>\n\t</keys_1>\n\t<keys_2>\n\t  <key>c_order</key>\n\t</keys_2>\n\t <cluster_schema/>\n <remotesteps>   <input>   </input>   <output>   </output> </remotesteps>    <GUI>\n\t  <xloc>222</xloc>\n\t  <yloc>60</yloc>\n\t  <draw>Y</draw>\n\t  </GUI>\n\t</step>\n";
		return ktlStepMergeJoin;
	}

}