package com.jast.escuela.mysql;

import java.sql.Connection;
import java.util.List;

import com.jast.escuela.dao.ProfesorDAO;
import com.jast.escuela.model.Profesor;

public class MySQLProfesorDAO implements ProfesorDAO{
	
	private Connection conn;

	public MySQLProfesorDAO(Connection conn) {
		this.conn = conn;
	}

	public void insertar(Profesor a) {
		// TODO Auto-generated method stub
		
	}

	public void modificar(Profesor a) {
		// TODO Auto-generated method stub
		
	}

	public void eliminar(Profesor a) {
		// TODO Auto-generated method stub
		
	}

	public List<Profesor> obtenerTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	public Profesor obtener(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
