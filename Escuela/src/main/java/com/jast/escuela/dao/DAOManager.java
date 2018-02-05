package com.jast.escuela.dao;

public interface DAOManager {

	AlumnoDAO getAlumnoDAO();

	AsignaturaDAO getAsignaturaDAO();

	MatriculaDAO getMatriculaDAO();

	ProfesorDAO getProfesorDAO(); 
}
