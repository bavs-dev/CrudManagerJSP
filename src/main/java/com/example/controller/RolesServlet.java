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
public class RolesServlet  extends HttpServlet{

	private RolesDAO rolesDAO = new RolesDAOImpl();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

		 String nombre = request.getParameter("nombre");
	     String vericador = nombre.toUpperCase().replaceAll("\\s+", "");

	     Rol rol = new Rol();
	     rol.setNombre(nombre);
	     rol.setVerificador(vericador);
		rolesDAO.insertRol(rol);

		response.sendRedirect(request.getContextPath() + "/views/app/Roles.jsp");

	}

}
