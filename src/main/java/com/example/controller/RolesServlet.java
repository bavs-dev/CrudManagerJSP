package com.example.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.dao.RolesDAO;
import com.example.dao.impl.RolesDAOImpl;
import com.example.model.Rol;

@WebServlet("/RolesServlet")
public class RolesServlet extends HttpServlet {

	private RolesDAO rolesDAO = new RolesDAOImpl();


	/**
	 * Metodo doGet que permite obtener los valores como id
	 * esos valores los utilizaremos para mandar y utlizar en el jsp
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// recibimos un parametro indicando el tipo de accion
		String accion = request.getParameter("accion");

		// si es eliminar
		if ("eliminar".equals(accion)) {
			// recuperamos el id o el paramtro a eliminar
			int id = Integer.parseInt(request.getParameter("id"));
			// realizamos la edicion de nuestro perfil
			rolesDAO.deleteRol(id);
			// realizamos el redireccionamiento
			response.sendRedirect(request.getContextPath() + "/views/app/Roles.jsp");

		} else if ("editar".equals(accion)) {
			// si la accion es editar
			// recibimo el id a editar
			int id = Integer.parseInt(request.getParameter("id"));
			Rol rol = rolesDAO.getRolById(id);
			// Enviar el rol al JSP
	        request.setAttribute("rol", rol);
	        // es importante colcoar el forward ya que permite mandar los nuevos valores al
	        // recargar la páguina nuevamente
	        request.getRequestDispatcher("/views/app/Roles.jsp").forward(request, response);
		}

	}

	/**
	 * Metodo doPost permite enviar datos importantes para poder actualizar y insertar
	 * segun sea el tipo de accion realiza la accion
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// recuperamos el parametro

		String accion = request.getParameter("accion");

		// realziamos validacion del tipo de accion

		if ("insertar".equals(accion)) {
			String nombre = request.getParameter("nombre");
			String vericador = nombre.toUpperCase().replaceAll("\\s+", "");

			Rol rol = new Rol();
			rol.setNombre(nombre);
			rol.setVerificador(vericador);
			rolesDAO.insertRol(rol);

			response.sendRedirect(request.getContextPath() + "/views/app/Roles.jsp");

		} else if ("actualizar".equals(accion)) {

			//metodo en caso de que accede permite recuperar el id y actulizar el valor
			int id = Integer.parseInt(request.getParameter("id"));

			String nombre = request.getParameter("nombre");
			String vericador = nombre.toUpperCase().replaceAll("\\s+", "");


			Rol rol = new Rol();
			rol.setId(id);
			rol.setNombre(nombre);
			rol.setVerificador(vericador);
			rolesDAO.updateRol(rol);

			response.sendRedirect(request.getContextPath() + "/views/app/Roles.jsp");

		}

	}

}
