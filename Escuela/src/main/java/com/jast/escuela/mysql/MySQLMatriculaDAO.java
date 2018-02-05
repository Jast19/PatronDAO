package com.jast.escuela.mysql;

import java.sql.Connection;
import java.util.List;

import com.jast.escuela.dao.MatriculaDAO;
import com.jast.escuela.model.Asignatura;

public class MySQLMatriculaDAO implements MatriculaDAO{
	
	private Connection conn;

	public MySQLMatriculaDAO(Connection conn) {
		this.conn = conn;
	}

	public void insertar(Asignatura a) {
		// TODO Auto-generated method stub
		
	}

	public void modificar(Asignatura a) {
		// TODO Auto-generated method stub
		
	}

	public void eliminar(Asignatura a) {
		// TODO Auto-generated method stub
		
	}

	public List<Asignatura> obtenerTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	public Asignatura obtener(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
