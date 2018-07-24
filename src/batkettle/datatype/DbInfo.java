package batkettle.datatype;

import java.util.LinkedHashMap;

public class DbInfo {
	public String getName() {
		return name;
	}

	public String getServer() {
		return server;
	}

	public String getType() {
		return type;
	}

	public String getAccess() {
		return access;
	}

	public String getDatabase() {
		return database;
	}

	public String getPort() {
		return port;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getServername() {
		return servername;
	}

	public String getData_tablespace() {
		return data_tablespace;
	}

	public String getIndex_tablespace() {
		return index_tablespace;
	}

	public LinkedHashMap<String, String> getAttribute() {
		return attribute;
	}

	public String getInstanceName() {
		return instanceName;
	}

	private String name;
	private String server;
	private String type;
	private String access;
	private String database;
	private String port;
	private String instanceName;

	private String username;
	private String password;
	private String servername;
	private String data_tablespace;
	private String index_tablespace;

	private LinkedHashMap<String, String> attribute = new LinkedHashMap<String, String>();

	private DbInfo() {
		// not dbinfo is not promise
	}

	public DbInfo(String name, String server, String type, String access, String database,  
			String instanceName, String username, String password) {
		this.name = name;
		this.server = server;
		this.type = type;
		this.access = access;
		this.database = database;
	//	this.port = port;

		this.instanceName = instanceName;
		this.username = username;
		this.password = password;

		this.servername = null;
		this.data_tablespace = null;
		this.index_tablespace = null;

		this.attribute.clear();
		this.attribute.put("FORCE_IDENTIFIERS_TO_LOWERCASE", "N");
		this.attribute.put("FORCE_IDENTIFIERS_TO_UPPERCASE", "N");
		this.attribute.put("IS_CLUSTERED", "N");
		this.attribute.put("MSSQL_DOUBLE_DECIMAL_SEPARATOR", "N");
		this.attribute.put("PORT_NUMBER", "1433");
		this.attribute.put("PRESERVE_RESERVED_WORD_CASE", "N");
		this.attribute.put("QUOTE_ALL_FIELDS", "N");
		this.attribute.put("SUPPORTS_BOOLEAN_DATA_TYPE", "Y");
		this.attribute.put("SUPPORTS_TIMESTAMP_DATA_TYPE", "Y");
		this.attribute.put("USE_POOLING", "N");

	}
}
