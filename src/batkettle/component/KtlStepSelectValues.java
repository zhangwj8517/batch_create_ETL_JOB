package batkettle.component;

public class KtlStepSelectValues {
	// 为尽快实现增量，SelectValues组件暂时只用String变量实现。
	String stepSelectStep1 = new String ();
	String stepSelectStep2 = new String ();
	
	
	public KtlStepSelectValues(String selectStep1, String selectStep2){
		this.stepSelectStep1=selectStep1;
		this.stepSelectStep2=selectStep2;
	}
	public String getXML() {
		String ktlStepSelectValuesXML = "\t<step>\n"
				+ "\t<name>"+this.stepSelectStep1+"</name>\n"
				+ "\t<type>SelectValues</type>\n\t<description/>\n\t<distribute>Y</distribute>\n\t<custom_distribution/>\n\t<copies>1</copies>\n\t\t <partitioning>\n\t\t   <method>none</method>\n\t\t   <schema_name/>\n\t\t   </partitioning>\n\t<fields>      <field>        <name>begin_lsn</name>\n\t\t<rename/>\n\t\t<length>-2</length>\n\t\t<precision>-2</precision>\n\t  </field>      <field>        <name>end_lsn</name>\n\t\t<rename/>\n\t\t<length>-2</length>\n\t\t<precision>-2</precision>\n\t  </field>      <field>        <name>begin_lsn</name>\n\t\t<rename>begin_lsn2</rename>\n\t\t<length>-2</length>\n\t\t<precision>-2</precision>\n\t  </field>      <field>        <name>end_lsn</name>\n\t\t<rename>end_lsn2</rename>\n\t\t<length>-2</length>\n\t\t<precision>-2</precision>\n\t  </field>        <select_unspecified>N</select_unspecified>\n\t</fields>     <cluster_schema/>\n <remotesteps>   <input>   </input>   <output>   </output> </remotesteps>    <GUI>\n\t  <xloc>320</xloc>\n\t  <yloc>60</yloc>\n\t  <draw>Y</draw>\n\t  </GUI>\n\t</step>\n\n  "
				+ "<step>\n"
				+ "\t<name>"+this.stepSelectStep2+"</name>\n"
				+ "\t<type>SelectValues</type>\n\t<description/>\n\t<distribute>Y</distribute>\n\t<custom_distribution/>\n\t<copies>1</copies>\n\t\t <partitioning>\n\t\t   <method>none</method>\n\t\t   <schema_name/>\n\t\t   </partitioning>\n\t<fields>      <field>        <name>source_system_name</name>\n\t\t<rename/>\n\t\t<length>-2</length>\n\t\t<precision>-2</precision>\n\t  </field>      <field>        <name>source_table_name</name>\n\t\t<rename/>\n\t\t<length>-2</length>\n\t\t<precision>-2</precision>\n\t  </field>      <field>        <name>target_table_name</name>\n\t\t<rename/>\n\t\t<length>-2</length>\n\t\t<precision>-2</precision>\n\t  </field>      <field>        <name>end_lsn</name>\n\t\t<rename/>\n\t\t<length>-2</length>\n\t\t<precision>-2</precision>\n\t  </field>      <field>        <name>last_updatetime</name>\n\t\t<rename/>\n\t\t<length>-2</length>\n\t\t<precision>-2</precision>\n\t  </field>        <select_unspecified>N</select_unspecified>\n\t</fields>     <cluster_schema/>\n <remotesteps>   <input>   </input>   <output>   </output> </remotesteps>    <GUI>\n\t  <xloc>753</xloc>\n\t  <yloc>187</yloc>\n\t  <draw>Y</draw>\n\t  </GUI>\n\t</step>\n";
		return ktlStepSelectValuesXML;
	}

}
