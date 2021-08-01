package generic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class DBUtils {

	public static ArrayList<LinkedHashMap<String, String>> getDataFromMySQL(String dbURL,String un,String pw,String query) {
		ArrayList<LinkedHashMap<String, String>> listMap=new ArrayList<LinkedHashMap<String, String>>();
		try
		{
			Connection connection = DriverManager.getConnection(dbURL,un,pw);
			Statement sqlStatement = connection.createStatement();
			ResultSet resultSet = sqlStatement.executeQuery(query);
			ResultSetMetaData allColumns = resultSet.getMetaData();
			int columnCount = allColumns.getColumnCount();
				while(resultSet.next())
				{
					LinkedHashMap<String, String> map=new LinkedHashMap<String, String>();
					for(int i=1;i<=columnCount;i++)
					{
						String columnName = allColumns.getColumnName(i);
						String value=resultSet.getString(i);
						map.put(columnName,value);
					}
					listMap.add(map);
				}

				connection.close();
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return listMap;
	}

	public static void executeDMLInMySql(String dbURL,String un,String pw,String query)
	{
		try 
		{
			Connection connection = DriverManager.getConnection(dbURL,un,pw);
			Statement sqlStatement = connection.createStatement();
			sqlStatement.executeUpdate(query);
			connection.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
