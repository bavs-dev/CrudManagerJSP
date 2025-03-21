package com.example.dao;

import java.util.List;

import com.example.model.Rol;

public interface RolesDAO {

	/**
	 * Metodo que elimina el rol de la BD
	 * solita el id a eliminar
	 * Ejecuta el query
	 * @param id
	 */
	void deleteRol(int id);

	/**
	 * Metodo que genera la modificacion del rol
	 * Recibe el query de modificacion
	 * Recibe el ROL
	 * @param rol
	 */
	void updateRol(Rol rol);

	/**
	 * Metodo que inserta el rol en la base  de datos
	 * Recibe el query de insercion
	 * se ve reflejado en la bd
	 * se solicita el rol
	 * @param rol
	 */
	void insertRol(Rol rol);

	/**
	 * Metodo que consulta todos los roles de sus respectiva tabla
	 * lo retorna en lista
	 * @return
	 */
	List<Rol> selectRoles();

}
