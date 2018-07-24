package batkettle.datatype;

import java.util.*;
import java.sql.*;

public class TableInfo {
	private ArrayList<String> columns = new ArrayList<String>();
	private ArrayList<String> keyColumn = new ArrayList<String>();
	private String tableName = new String();

	public String getTableName() {
		return tableName;
	}

	public void readTableInfo(Connection conn, String tableName) {
		this.tableName = tableName;
		readColumns(conn);
		readKeyColumns(conn);

		// System.out.println(this.columns);
		// System.out.println(this.keyColumn);
	}

	private void readColumns(Connection conn) {
		String sql = "select A.object_id," + "A.name table_name ," + "b.name coloumn_name," + "b.system_type_id, "
				+ "b.user_type_id, " + "b.max_length," + "b.precision, " + "b.scale  "
				+ "from  sys.tables  a , sys.all_columns  b  " + "where a.object_id = b.object_id " + "and a.name =  '"
				+ this.tableName + "' " + "order by column_id ";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				this.columns.add(rs.getString("coloumn_name"));
			}

		} catch (Exception e) {
			System.out.println("select table columns error");
			e.printStackTrace();
		}

	}

	private void readKeyColumns(Connection conn) {
		String sql = "SELECT SYSCOLUMNS.name   " + "FROM SYSCOLUMNS,SYSOBJECTS,SYSINDEXES,SYSINDEXKEYS   "
				+ "WHERE SYSCOLUMNS.id = object_id( '" + this.tableName + "')   " + "AND SYSOBJECTS.xtype = 'PK'   "
				+ "AND SYSOBJECTS.parent_obj = SYSCOLUMNS.id   " + "AND SYSINDEXES.id = SYSCOLUMNS.id   "
				+ "AND SYSOBJECTS.name = SYSINDEXES.name   " + "AND SYSINDEXKEYS.id = SYSCOLUMNS.id   "
				+ "AND SYSINDEXKEYS.indid = SYSINDEXES.indid    " + "AND SYSCOLUMNS.colid = SYSINDEXKEYS.colid  ";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				this.keyColumn.add(rs.getString("name"));
				/// System.out.println(rs.getString("table_name")+"\t"+rs.getString("coloumn_name"));
			}

		} catch (Exception e) {
			System.out.println("select table Keycolumns error");
			e.printStackTrace();
		}

	}

	public ArrayList<String> getColumns() {
		return columns;
	}

	public void setColumns(ArrayList<String> columns) {
		this.columns = columns;
	}

	public ArrayList<String> getKeyColumn() {
		return keyColumn;
	}

	public void setKeyColumn(ArrayList<String> keyColumn) {
		this.keyColumn = keyColumn;
	}

	public void addColumn(String column) {
		this.columns.add(column);
	}

	public void addKeyColumn(String Keycolumn) {
		this.keyColumn.add(Keycolumn);
	}

	public void removeColumn(String column) {
		if (this.columns.indexOf(column) > 0)
			this.columns.remove(column);
	}
}
