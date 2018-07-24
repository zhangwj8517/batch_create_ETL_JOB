package batkettle.component;

import java.util.*;
import batkettle.datatype.*;

public class KtlInfo {
	private String name;
	private final String description = "\t<description/>\n";
	private final String extended_description = "\t<extended_description/>\n";
	private final String trans_version = "\t<trans_version/>\n";
	private final String trans_type = "\t<trans_type>Normal</trans_type>\n";
	private final String trans_status = "\t<trans_status>0</trans_status>\n";
	private String directory;
	private String parammeters;

	private String log;
	private String maxdate;

	private class Directory {
		private String directory;

		public Directory(String directory) {
			this.directory = directory;
		}

		public String getXML() {
			return "\t<directory>" + this.directory + "</directory>\n";
		}

	}

	private class Parameters {
		private KettleParameters kettleParameters;

		public Parameters(KettleParameters kettleParameters) {
			this.kettleParameters = kettleParameters;
		}

		public String getXML() {
			String xmlDellimit = "\t\t<parameter>\n" + "\t\t\t<name>Dellimit</name>\n" + "\t\t\t<default_value>"
					+ kettleParameters.getDellimit() + "</default_value>\n"
					+ "\t\t\t<description>&#x6e90;&#x8868;&#x5355;&#x4e8b;&#x52a1;&#x5220;&#x9664;&#x6570;&#x636e;&#x91cf;&#x8fc7;&#x5927;&#x65f6;&#xff0c;&#x8ba4;&#x4e3a;&#x662f;&#x6570;&#x636e;&#x8fc1;&#x79fb;,&#x5ffd;&#x7565;&#x6b64;&#x4e8b;&#x52a1;&#x6570;&#x636e;&#x7684;&#x540c;&#x6b65;</description>\n"
					+ "\t\t</parameter>\n";
			String xmlSourceSys = "\t\t<parameter> \n" + "\t\t\t<name>SourceSys</name>\n" + "\t\t\t<default_value>"
					+ kettleParameters.getSourceSys() + "</default_value>\n" +"\t\t\t<description/>\n" +"\t\t</parameter>\n";

			String xmlSourceTable = "\t\t<parameter>\n" + "\t\t\t<name>SourceTable</name>\n" + "\t\t\t<default_value>"
					+ kettleParameters.getSourceTable() + "</default_value>\n" +"\t\t\t<description/>\n"+ "\t\t</parameter>\n";
			String xmlTargetTable = "\t\t<parameter>\n" + "\t\t\t<name>TargetTable</name>\n" + "\t\t\t<default_value>"
					+ kettleParameters.getTargetTable() + "</default_value>\n"+"\t\t\t<description/>\n" + "\t\t</parameter>\n";
			String xmlYLJGDM = "\t\t<parameter>\n" + "\t\t\t<name>YLJGDM</name>\n" + "\t\t\t<default_value>"
					+ kettleParameters.getYljgdm() + "</default_value>\n" +"\t\t\t<description/>\n"+ "\t\t</parameter>\n";

			return "\t<parameters>\n" + xmlDellimit + xmlSourceSys + xmlSourceTable + xmlTargetTable + xmlYLJGDM
					+ "\t</parameters>\n";

		}

	}

	private class Log {
		public String logXml = new String();
		private String transLogTableXML;
		private String perfLogTableXML;
		private String channelLogTableXML;
		private String stepLogTableXML;
		private String metricsLogTableXML;

		public String getXml() {
			this.transLogTableXML = new TransLogTable().getXML();
			this.perfLogTableXML = new PerfLogTable().getXML();
			this.channelLogTableXML = new ChannelLogTable().getXML();
			this.stepLogTableXML = new StepLogTable().getXML();
			this.metricsLogTableXML = new MetricsLogTable().getXML();
			this.logXml = "\t<log>\n" + this.transLogTableXML + this.perfLogTableXML + this.channelLogTableXML
					+ this.stepLogTableXML + this.metricsLogTableXML + "\t</log>\n";

			return logXml;
		}

		private class TransLogTable {
			private final String connection = "<connection>&#x24;&#x7b;KETTLE_TRANS_LOG_DB&#x7d;</connection>\n";
			private final String schema = "<schema/>\n";
			private final String table = "<table>&#x24;&#x7b;KETTLE_TRANS_LOG_TABLE&#x7d;</table>\n";
			private final String size_limit_lines = "<size_limit_lines/>\n";
			private final String interval = "<interval/>\n";
			private final String timeout_days = "<timeout_days>&#x24;&#x7b;KETTLE_LOG_LINE_TIMEOUT_DAYS&#x7d;</timeout_days>\n";
			private final String fields = "<field><id>ID_BATCH</id><enabled>Y</enabled><name>ID_BATCH</name></field><field><id>CHANNEL_ID</id><enabled>Y</enabled><name>CHANNEL_ID</name></field><field><id>TRANSNAME</id><enabled>Y</enabled><name>TRANSNAME</name></field><field><id>STATUS</id><enabled>Y</enabled><name>STATUS</name></field><field><id>LINES_READ</id><enabled>Y</enabled><name>LINES_READ</name><subject/></field><field><id>LINES_WRITTEN</id><enabled>Y</enabled><name>LINES_WRITTEN</name><subject/></field><field><id>LINES_UPDATED</id><enabled>Y</enabled><name>LINES_UPDATED</name><subject/></field><field><id>LINES_INPUT</id><enabled>Y</enabled><name>LINES_INPUT</name><subject/></field><field><id>LINES_OUTPUT</id><enabled>Y</enabled><name>LINES_OUTPUT</name><subject/></field><field><id>LINES_REJECTED</id><enabled>Y</enabled><name>LINES_REJECTED</name><subject/></field><field><id>ERRORS</id><enabled>Y</enabled><name>ERRORS</name></field><field><id>STARTDATE</id><enabled>Y</enabled><name>STARTDATE</name></field><field><id>ENDDATE</id><enabled>Y</enabled><name>ENDDATE</name></field><field><id>LOGDATE</id><enabled>Y</enabled><name>LOGDATE</name></field><field><id>DEPDATE</id><enabled>Y</enabled><name>DEPDATE</name></field><field><id>REPLAYDATE</id><enabled>Y</enabled><name>REPLAYDATE</name></field><field><id>LOG_FIELD</id><enabled>Y</enabled><name>LOG_FIELD</name></field><field><id>EXECUTING_SERVER</id><enabled>N</enabled><name>EXECUTING_SERVER</name></field><field><id>EXECUTING_USER</id><enabled>N</enabled><name>EXECUTING_USER</name></field><field><id>CLIENT</id><enabled>N</enabled><name>CLIENT</name></field>\n";

			public String getXML() {
				return "<trans-log-table>" + connection + schema + table + size_limit_lines + interval + timeout_days
						+ fields + "</trans-log-table>\n";
			}

		}

		private class PerfLogTable {
			private final String connection = "<connection/>\n";
			private final String schema = "<schema/>\n";
			private final String table = "<table/>\n";
			private final String interval = "<interval/>\n";
			private final String timeout_days = "<timeout_days/>\n";
			private final String fields = "<field><id>ID_BATCH</id><enabled>Y</enabled><name>ID_BATCH</name></field><field><id>SEQ_NR</id><enabled>Y</enabled><name>SEQ_NR</name></field><field><id>LOGDATE</id><enabled>Y</enabled><name>LOGDATE</name></field><field><id>TRANSNAME</id><enabled>Y</enabled><name>TRANSNAME</name></field><field><id>STEPNAME</id><enabled>Y</enabled><name>STEPNAME</name></field><field><id>STEP_COPY</id><enabled>Y</enabled><name>STEP_COPY</name></field><field><id>LINES_READ</id><enabled>Y</enabled><name>LINES_READ</name></field><field><id>LINES_WRITTEN</id><enabled>Y</enabled><name>LINES_WRITTEN</name></field><field><id>LINES_UPDATED</id><enabled>Y</enabled><name>LINES_UPDATED</name></field><field><id>LINES_INPUT</id><enabled>Y</enabled><name>LINES_INPUT</name></field><field><id>LINES_OUTPUT</id><enabled>Y</enabled><name>LINES_OUTPUT</name></field><field><id>LINES_REJECTED</id><enabled>Y</enabled><name>LINES_REJECTED</name></field><field><id>ERRORS</id><enabled>Y</enabled><name>ERRORS</name></field><field><id>INPUT_BUFFER_ROWS</id><enabled>Y</enabled><name>INPUT_BUFFER_ROWS</name></field><field><id>OUTPUT_BUFFER_ROWS</id><enabled>Y</enabled><name>OUTPUT_BUFFER_ROWS</name></field>\n";

			public String getXML() {
				return "<perf-log-table>" + connection + schema + table + interval + timeout_days + fields
						+ "</perf-log-table>\n";
			}
		}

		private class ChannelLogTable {
			private final String connection = "<connection/>\n";
			private final String schema = "<schema/>\n";
			private final String table = "<table/>\n";
			private final String timeout_days = "<timeout_days/>\n";
			private final String fields = "<field><id>ID_BATCH</id><enabled>Y</enabled><name>ID_BATCH</name></field><field><id>CHANNEL_ID</id><enabled>Y</enabled><name>CHANNEL_ID</name></field><field><id>LOG_DATE</id><enabled>Y</enabled><name>LOG_DATE</name></field><field><id>LOGGING_OBJECT_TYPE</id><enabled>Y</enabled><name>LOGGING_OBJECT_TYPE</name></field><field><id>OBJECT_NAME</id><enabled>Y</enabled><name>OBJECT_NAME</name></field><field><id>OBJECT_COPY</id><enabled>Y</enabled><name>OBJECT_COPY</name></field><field><id>REPOSITORY_DIRECTORY</id><enabled>Y</enabled><name>REPOSITORY_DIRECTORY</name></field><field><id>FILENAME</id><enabled>Y</enabled><name>FILENAME</name></field><field><id>OBJECT_ID</id><enabled>Y</enabled><name>OBJECT_ID</name></field><field><id>OBJECT_REVISION</id><enabled>Y</enabled><name>OBJECT_REVISION</name></field><field><id>PARENT_CHANNEL_ID</id><enabled>Y</enabled><name>PARENT_CHANNEL_ID</name></field><field><id>ROOT_CHANNEL_ID</id><enabled>Y</enabled><name>ROOT_CHANNEL_ID</name></field>\n";

			public String getXML() {
				return "<channel-log-table>" + connection + schema + table + timeout_days + fields
						+ "</channel-log-table>\n";
			}

		}

		private class StepLogTable {
			private final String connection = "<connection/>\n";
			private final String schema = "<schema/>\n";
			private final String table = "<table/>\n";
			private final String timeout_days = "<timeout_days/>\n";
			private final String fields = "<field><id>ID_BATCH</id><enabled>Y</enabled><name>ID_BATCH</name></field><field><id>CHANNEL_ID</id><enabled>Y</enabled><name>CHANNEL_ID</name></field><field><id>LOG_DATE</id><enabled>Y</enabled><name>LOG_DATE</name></field><field><id>TRANSNAME</id><enabled>Y</enabled><name>TRANSNAME</name></field><field><id>STEPNAME</id><enabled>Y</enabled><name>STEPNAME</name></field><field><id>STEP_COPY</id><enabled>Y</enabled><name>STEP_COPY</name></field><field><id>LINES_READ</id><enabled>Y</enabled><name>LINES_READ</name></field><field><id>LINES_WRITTEN</id><enabled>Y</enabled><name>LINES_WRITTEN</name></field><field><id>LINES_UPDATED</id><enabled>Y</enabled><name>LINES_UPDATED</name></field><field><id>LINES_INPUT</id><enabled>Y</enabled><name>LINES_INPUT</name></field><field><id>LINES_OUTPUT</id><enabled>Y</enabled><name>LINES_OUTPUT</name></field><field><id>LINES_REJECTED</id><enabled>Y</enabled><name>LINES_REJECTED</name></field><field><id>ERRORS</id><enabled>Y</enabled><name>ERRORS</name></field><field><id>LOG_FIELD</id><enabled>N</enabled><name>LOG_FIELD</name></field>\n";

			public String getXML() {
				return "<step-log-table>" + connection + schema + table + timeout_days + fields + "</step-log-table>\n";
			}

		}

		private class MetricsLogTable {
			private final String connection = "<connection/>\n";
			private final String schema = "<schema/>\n";
			private final String table = "<table/>\n";
			private final String timeout_days = "<timeout_days/>\n";
			private final String fields = "<field><id>ID_BATCH</id><enabled>Y</enabled><name>ID_BATCH</name></field><field><id>CHANNEL_ID</id><enabled>Y</enabled><name>CHANNEL_ID</name></field><field><id>LOG_DATE</id><enabled>Y</enabled><name>LOG_DATE</name></field><field><id>METRICS_DATE</id><enabled>Y</enabled><name>METRICS_DATE</name></field><field><id>METRICS_CODE</id><enabled>Y</enabled><name>METRICS_CODE</name></field><field><id>METRICS_DESCRIPTION</id><enabled>Y</enabled><name>METRICS_DESCRIPTION</name></field><field><id>METRICS_SUBJECT</id><enabled>Y</enabled><name>METRICS_SUBJECT</name></field><field><id>METRICS_TYPE</id><enabled>Y</enabled><name>METRICS_TYPE</name></field><field><id>METRICS_VALUE</id><enabled>Y</enabled><name>METRICS_VALUE</name></field>\n";

			public String getXML() {
				return "<metrics-log-table>" + connection + schema + table + timeout_days + fields
						+ "</metrics-log-table>\n";
			}
		}

	}

	private class Maxdate {
		private String maxdateXML;

		public String getXML() {

			this.maxdateXML = "\t<maxdate>\n" + "\t\t<connection/>\n" + "\t\t<table/>\n" + "\t\t<field/>\n"
					+ "\t\t<offset>0.0</offset>\n" + "\t\t<maxdiff>0.0</maxdiff>\n" + "\t</maxdate>\n";
			return maxdateXML;

		}

	}

	private String size_rowset = "\t<size_rowset>10000</size_rowset>\n";
	private String sleep_time_empty = "\t<sleep_time_empty>50</sleep_time_empty>\n";
	private String sleep_time_full = "\t<sleep_time_full>50</sleep_time_full>\n";
	private String unique_connections = "\t<unique_connections>N</unique_connections>\n";
	private String feedback_shown = "\t<feedback_shown>Y</feedback_shown>\n";
	private String feedback_size = "\t<feedback_size>50000</feedback_size>\n";
	private String using_thread_priorities = "\t<using_thread_priorities>Y</using_thread_priorities>\n";
	private String shared_objects_file = "\t<shared_objects_file/>\n";
	private String capture_step_performance = "\t<capture_step_performance>N</capture_step_performance>\n";
	private String step_performance_capturing_delay = "\t<step_performance_capturing_delay>1000</step_performance_capturing_delay>\n";
	private String step_performance_capturing_size_limit = "\t<step_performance_capturing_size_limit>100</step_performance_capturing_size_limit>\n";
	private String dependencies = "\t<dependencies>\n\t</dependencies>\n";
	private String partitionschemas = "\t<partitionschemas>\n\t</partitionschemas>\n";
	private String slaveservers = "\t<slaveservers>\n\t</slaveservers>\n";
	private String clusterschemas = "\t<clusterschemas>\n\t</clusterschemas>\n";
	private String created_user = "\t<created_user>-</created_user>\n";
	private String created_date = "\t<created_date>2017&#x2f;03&#x2f;23 15&#x3a;11&#x3a;50.623</created_date>\n";
	private String modified_user = "\t<modified_user>admin</modified_user>\n";
	private String modified_date = "\t<modified_date>2017&#x2f;04&#x2f;12 14&#x3a;12&#x3a;06.377</modified_date>\n";
	private String key_for_session_key = "\t<key_for_session_key>H4sIAAAAAAAAAAMAAAAAAAAAAAA&#x3d;</key_for_session_key>\n";
	private String is_key_private = "\t<is_key_private>N</is_key_private>\n";

	private String infoXML;

	public KtlInfo(KettleParameters kettleParameters) {
		this.name = "\t<name>" + kettleParameters.getSourceSys() + "_" + kettleParameters.getTargetTable()
				+ "</name>\n";
		this.directory = new Directory("/" + kettleParameters.getSourceSys()).getXML();
		this.parammeters = new Parameters(kettleParameters).getXML();
		this.log = new Log().getXml();
		this.maxdate = new Maxdate().getXML();
		
	}

	public String getXml() {

		this.infoXML = "\t<info>\n" 
		+ this.name 
		+ this.description 
		+ this.extended_description 
		+ this.trans_version
		+ this.trans_type 
		+ this.trans_status 
		+ this.directory
		+ this.parammeters
		+ this.log
		+ this.maxdate
		+this.size_rowset
		+ this.sleep_time_empty
		+ this.sleep_time_full
		+ this.unique_connections
		+ this.feedback_shown
		+ this.feedback_size
		+ this.using_thread_priorities
		+ this.shared_objects_file
		+ this.capture_step_performance
		+ this.step_performance_capturing_delay
		+ this.step_performance_capturing_size_limit
		+ this.dependencies
		+ this.partitionschemas
		+ this.slaveservers
		+ this.clusterschemas
		+ this.created_user
		+ this.created_date
		+ this.modified_user
		+ this.modified_date
		+ this.key_for_session_key
		+ this.is_key_private
		+"\t</info>\n";

		return this.infoXML;

	}

}
