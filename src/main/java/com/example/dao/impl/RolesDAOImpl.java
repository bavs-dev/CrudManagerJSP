package com.example.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.conexion.ConexionDB;
import com.example.dao.RolesDAO;
import com.example.model.Rol;

public class RolesDAOImpl implements RolesDAO {

	// implementacion de log

	private static final Logger logger = LogManager.getLogger(RolesDAOImpl.class);

	private static final String INSERT_ROL = "INSERT INTO roles (nombre,verificador) VALUES (?,?)";
	private static final String UPDATE_ROL = "UPDATE roles SET nombre =?, verificador = ? WHERE id = ?";
	private static final String DELETE_ROl = "DELETE FROM roles WHERE id = ?";
	private static final String SELECT = "SELECT * FROM roles";
	private static final String SELECTROLID = "SELECT * FROM roles WHERE id = ?";

	/**
	 * Metodo que consulta todos los roles de sus respectiva tabla lo retorna en
	 * lista
	 *
	 * @return
	 */
	@Override
	public List<Rol> selectRoles() {
		List<Rol> rol = new ArrayList<>();
		try (Connection conn = ConexionDB.getConexionDB(); PreparedStatement stmt = conn.prepareStatement(SELECT)) {

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				rol.add(new Rol(rs.getInt("id"), rs.getString("nombre"), rs.getString("verificador")));

			}

			logger.info("Se consulto todos los roles correctamente total {}", rol.size());

		} catch (Exception e) {
			// TODO: handle exception

			e.printStackTrace();
			logger.error("Error al consultar los roles {} ", e.getMessage());
		}

		return rol;
	}

	/**
	 * Metodo que inserta el rol en la base de datos Recibe el query de insercion se
	 * ve reflejado en la bd se solicita el rol
	 *
	 * @param rol
	 */
	@Override
	public void insertRol(Rol rol) {
		try (Connection conn = ConexionDB.getConexionDB(); PreparedStatement stmt = conn.prepareStatement(INSERT_ROL)) {

			stmt.setString(1, rol.getNombre());
			stmt.setString(2, rol.getVerificador());
			stmt.executeUpdate();
			logger.info("El rol se inserto correctamente. ");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("Error al insertar el rol {} ", e.getMessage());
		}
	}

	/**
	 * Metodo que genera la modificacion del rol Recibe el query de modificacion
	 * Recibe el ROL
	 *
	 * @param rol
	 */
	@Override
	public void updateRol(Rol rol) {
		try (Connection conn = ConexionDB.getConexionDB(); PreparedStatement stmt = conn.prepareStatement(UPDATE_ROL)) {

			stmt.setString(1, rol.getNombre());
			stmt.setString(2, rol.getVerificador());
			stmt.setInt(3, rol.getId());
			stmt.executeUpdate();
			logger.info("El rol se modifico correctamente. ");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("Error al modificar el rol {} ", e.getMessage());
		}
	}

	/**
	 * Metodo que elimina el rol de la BD solita el id a eliminar Ejecuta el query
	 *
	 * @param id
	 */
	@Override
	public void deleteRol(int id) {
		try (Connection conn = ConexionDB.getConexionDB(); PreparedStatement stmt = conn.prepareStatement(DELETE_ROl)) {

			stmt.setInt(1, id);
			stmt.executeUpdate();
			logger.info("El rol se elimino correctamente. ");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("Error al eliminar el rol {} ", e.getMessage());
		}
	}

	/**
	 * Metodo que permite consultar el rol por su id retorna un objeto de tipo Rol
	 * que utilizaremos en el jsp
	 */
	@Override
	public Rol getRolById(int id) {
		try (Connection conn = ConexionDB.getConexionDB();
				PreparedStatement stmt = conn.prepareStatement(SELECTROLID)) {

			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				Rol rol = new Rol();
				rol.setId(rs.getInt("id"));
				rol.setNombre(rs.getString("nombre"));
				rol.setVerificador(rs.getString("verificador"));
				logger.info("El rol se encontro correctamente");
				return rol;
			}

			logger.info("El rol se elimino correctamente. ");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("Error al eliminar el rol {} ", e.getMessage());
		}
		return null;
	}

}
