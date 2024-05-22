package org.crud.sregion;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * CRUD JAVA ---> ORACLE
 * 
 */
public class CRUDS_Region {

	static Connection connection;
	static String driver = "oracle.jdbc.driver.OracleDriver";
	static String URL = "jdbc:oracle:thin:@localhost:1521:orcl";

	public static void connectDBOracle() throws IOException, SQLException {
		try {
			Class.forName(driver).newInstance();
			System.out.println("CARGO DRIVER: ojdbc6.jar");
		} catch (Exception e) {
			System.out.println("EXCEPTION:" + e.getMessage());
		}
		try {
			connection = DriverManager.getConnection(URL, "System", "TEAM970704nv5");
			System.out.println("CONEXIÓN EXITOSA: Oracle11g");
		} catch (Exception e) {
			System.out.println("EXCEPTION:" + e.getMessage());
		}
	}

	public static void insertarS_Region(int id, String name) throws IOException, SQLException {
		try {
			connectDBOracle();
			String sql = "INSERT INTO S_REGION (ID,NAME) VALUES (?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.execute();
			System.out.println("INSERTO S_REGION:" + id + "," + name);
		} catch (Exception e) {
			System.out.println("EXCEPTION INSERT:" + e.getMessage());
		}
	}

	public static void actualizarS_Region(int id, String name) throws IOException, SQLException {
		try {
			connectDBOracle();
			// 1 2
			String sql = "UPDATE S_REGION SET NAME = ? WHERE ID = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, name);
			ps.setInt(2, id);
			ps.execute();
			System.out.println("ACTUALIZO S_REGION:" + id + "," + name);
		} catch (Exception e) {
			System.out.println("EXCEPTION ACTUALIZO:" + e.getMessage());
		}
	}

	public static void eliminarS_Region(int id) throws IOException, SQLException {
		try {
			connectDBOracle();
			// 1
			String sql = "DELETE FROM S_REGION WHERE ID = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.execute();
			System.out.println("ELIMINO S_REGION:" + id);
		} catch (Exception e) {
			System.out.println("EXCEPTION ELIMINO:" + e.getMessage());
		}
	}
	
	public static void consultaIndividualS_Region(int id) throws IOException,SQLException {
		try {
			connectDBOracle();
			//                                            1
			String sql = "SELECT * FROM S_REGION WHERE ID = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
				if (rs.next()) {
			System.out.println(rs.getInt("id")+","+rs.getString("name"));					
				}
		} catch (Exception e) {
			System.out.println("EXCEPTION CONSULTA:" + e.getMessage());
		}
	}

	public static void consultaGeneralS_Region() throws IOException,SQLException {
		try {
			connectDBOracle();
			String sql = "SELECT * FROM S_REGION";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
				while (rs.next()) {
			System.out.println(rs.getInt("id")+","+rs.getString("name"));					
				}
		} catch (Exception e) {
			System.out.println("EXCEPTION CONSULTA:" + e.getMessage());
		}
	}
	
	public static void invocaProcedimientoProc(int id, String name) throws IOException,SQLException {
		try {
			connectDBOracle();
			CallableStatement cs = connection.prepareCall("CALL proc(?,?)");
			cs.setInt(1, id);
			cs.setString(2, name);
			cs.execute();
			System.out.println("EJECUTO PROCEDIMIENTO!!!");
		} catch (Exception e) {
			System.out.println("EXCEPTION PROCEDIMIENTO:" + e.getMessage());
		}
	}
	
	public static void main(String[] args) throws IOException, SQLException {
		// insertarS_Region(14, "BAJA CALIFORNIA");
		//actualizarS_Region(7, "HEROICA DE PUEBLA");
		//eliminarS_Region(14);
		//consultaIndividualS_Region(1);
		//consultaGeneralS_Region();
		invocaProcedimientoProc(18,"GUANAJUATO");
	}

}