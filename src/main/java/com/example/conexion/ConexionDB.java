package com.example.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// clase que realiza la conexion de la base de datos
public class ConexionDB {

	// paramtros de conexion de la base datos
	private static final String URL = "jdbc:mysql://localhost:3306/CrudManagerJSPMySQL";
	private static final String USUARIO = "root";
	private static final String CONTRASENA = "admin";

	private static final Logger logger = LogManager.getLogger(ConexionDB.class);

	/**
	 * costructor que permite la conexion de la base de datos
	 */
	public static Connection getConexionDB() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// creacion del logger
			logger.info("Conexión exitosa.");
			return DriverManager.getConnection(URL, USUARIO, CONTRASENA);

		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException("Error en la conexión con la base de datos");

		}

	}

//	/**
//	 * metodo que sirve para cerrar la conexion
//	 */
//	public void cerrarConexion() {
//		try {
//			if (conexion != null && !conexion.isClosed()) {
//				conexion.close();
//				logger.info("Conexión cerrada.");
//
//			}
//		} catch (SQLException e) {
//			logger.error("Error al cerrar conexión. {}",e.getMessage());
//
//		}
//	}

}
