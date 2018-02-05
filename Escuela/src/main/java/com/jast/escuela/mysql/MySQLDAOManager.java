package com.jast.escuela.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import com.jast.escuela.dao.AlumnoDAO;
import com.jast.escuela.dao.AsignaturaDAO;
import com.jast.escuela.dao.DAOException;
import com.jast.escuela.dao.DAOManager;
import com.jast.escuela.dao.MatriculaDAO;
import com.jast.escuela.dao.ProfesorDAO;
import com.jast.escuela.model.Alumno;

public class MySQLDAOManager implements DAOManager{
	
	private Connection conn;
	
	private AlumnoDAO alumnoDAO =null;
	private AsignaturaDAO asignaturaDAO = null;
	private MatriculaDAO matriculaDAO = null;
	private ProfesorDAO profesorDAO = null;

	public MySQLDAOManager(String host, String username, String password, String database) throws SQLException {
		conn = DriverManager.getConnection("jdbc:mysql://"+host+"/"+database, username, password);
	}

	public AlumnoDAO getAlumnoDAO() {
		if (alumnoDAO == null) {
			alumnoDAO = new MySQLAlumnoDAO(conn);
		}
		return alumnoDAO;
	}

	public AsignaturaDAO getAsignaturaDAO() {
		if (asignaturaDAO == null) {
			asignaturaDAO = new MySQLAsignaturaDAO(conn);
		}
		return asignaturaDAO;
	}

	public MatriculaDAO getMatriculaDAO() {
		if (matriculaDAO == null) {
			matriculaDAO = new MySQLMatriculaDAO(conn);
		}
		return matriculaDAO;
	}

	public ProfesorDAO getProfesorDAO() {
		if (profesorDAO == null) {
			profesorDAO = new MySQLProfesorDAO(conn);
		}
		return profesorDAO;
	}
	
	public static void main(String[] arg) throws SQLException, DAOException{
		MySQLDAOManager manager = new MySQLDAOManager("localhost", "root", "12345", "ejemplo");
		List<Alumno> alumnos = manager.getAlumnoDAO().obtenerTodos();
		System.out.println(alumnos);
	}

}
