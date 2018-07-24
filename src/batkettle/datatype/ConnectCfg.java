package batkettle.datatype;


public class ConnectCfg {
	private String souceConnection ;
	private String targetConnnection ;
	private String timepointConnection ;
	private String sourceServer ;
	private String sourceDb ;
	private String sourceUser ;
	private String sourcePwd ;
	private String sourcePort;
	private String instanceName;
	public String getSourcePort() {
		return sourcePort;
	}
	public void setSourcePort(String sourcePort) {
		this.sourcePort = sourcePort;
	}
	public String getInstanceName() {
		return instanceName;
	}
	public void setInstanceName(String instanceName) {
		this.instanceName = instanceName;
	}
	public String getSouceConnection() {
		return souceConnection;
	}
	public void setSouceConnection(String souceConnection) {
		this.souceConnection = souceConnection;
	}
	public String getTargetConnnection() {
		return targetConnnection;
	}
	public void setTargetConnnection(String targetConnnection) {
		this.targetConnnection = targetConnnection;
	}
	public String getTimepointConnection() {
		return timepointConnection;
	}
	public void setTimepointConnection(String timepointConnection) {
		this.timepointConnection = timepointConnection;
	}
	public String getSourceServer() {
		return sourceServer;
	}
	public void setSourceServer(String sourceServer) {
		this.sourceServer = sourceServer;
	}
	public String getSourceDb() {
		return sourceDb;
	}
	public void setSourceDb(String sourceDb) {
		this.sourceDb = sourceDb;
	}
	public String getSourceUser() {
		return sourceUser;
	}
	public void setSourceUser(String sourceUser) {
		this.sourceUser = sourceUser;
	}
	public String getSourcePwd() {
		return sourcePwd;
	}
	public void setSourcePwd(String sourcePwd) {
		this.sourcePwd = sourcePwd;
	}

	

}



