package batkettle.datatype;
public class KettleParameters {
	public String getSourceSys() {
		return sourceSys;
	}
	public void setSourceSys(String sourceSys) {
		this.sourceSys = sourceSys;
	}
	public String getSourceTable() {
		return sourceTable;
	}
	public void setSourceTable(String sourceTable) {
		this.sourceTable = sourceTable;
	}
	public String getTargetTable() {
		return targetTable;
	}
	public void setTargetTable(String targetTable) {
		this.targetTable = targetTable;
	}
	public String getYljgdm() {
		return yljgdm;
	}
	public void setYljgdm(String yljgdm) {
		this.yljgdm = yljgdm;
	}
	public String getDellimit() {
		return dellimit;
	}
	public void setDellimit(String dellimit) {
		this.dellimit = dellimit;
	}
	
	public String getSource_conn() {
		return source_conn;
	}
	public void setSource_conn(String source_conn) {
		this.source_conn = source_conn;
	}
	public String getTarget_conn() {
		return target_conn;
	}
	public void setTarget_conn(String target_conn) {
		this.target_conn = target_conn;
	}
	private String sourceSys ;
	private String sourceTable ;
	private String targetTable ;
	private String yljgdm ;
	private String dellimit ;

	private String source_conn;
	private String target_conn;
	

}
