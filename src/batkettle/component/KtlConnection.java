package batkettle.component;

import batkettle.datatype.*;
import java.util.*;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class KtlConnection {
	private String connectionXML;
	private String name;
	public String getName() {
		return name;
	}

	private String server;
	private String type;
	private String access;
	private String database;
	private String port;
	private String username;
	private String password;
	private String servername;
	private String data_tablespace;
	private String index_tablespace;
	private String attributes;
	private DbInfo dbinfo;

	private String getAttributesXML() {
		String attributesXML = new String();

		Iterator iterator = dbinfo.getAttribute().entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry entry = (Map.Entry) iterator.next();
			Object key = entry.getKey();
			Object value = entry.getValue();
			attributesXML = attributesXML + "\t\t<attribute><code>" + key.toString() + "</code><attribute>"
					+ value.toString() + "</attribute></attribute>\n";
		}
		attributesXML = "\t<attributes>\n" + attributesXML + "\t</attributes>\n";
		return attributesXML;
	}

	public KtlConnection(DbInfo dbinfo) {
		this.dbinfo = dbinfo;
	}

	public String getXML() {
		this.name = "\t<name>" + dbinfo.getName() + "</name>\n";
		this.server = "\t<server>" + dbinfo.getServer() + "</server>\n";
		this.type = "\t<type>" + dbinfo.getType() + "</type>\n";
		this.access = "\t<access>" + dbinfo.getAccess() + "</access>\n";
		this.database = "\t<database>" + dbinfo.getDatabase() + "</database>\n";
		this.port = "\t<port>" + dbinfo.getPort() + "</port>\n";
		this.username = "\t<username>" + dbinfo.getUsername() + "</username>\n";
		this.password = "\t<password>" + dbinfo.getPassword() + "</password>\n";
		this.servername = "\t<servername/>\n";
		this.data_tablespace = "\t<data_tablespace/>\n";
		this.index_tablespace = "\t<index_tablespace/>\n";
		this.attributes = this.getAttributesXML();

		this.connectionXML = "<connection>\n" + this.name + this.server + this.type + this.access + this.database
				+ this.port + this.username + this.password +this.servername+ this.data_tablespace + this.index_tablespace
				+ this.attributes + "</connection>\n";
		return connectionXML;
	}

}
