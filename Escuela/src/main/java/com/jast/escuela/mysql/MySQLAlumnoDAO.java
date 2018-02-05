package com.jast.escuela.mysql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jast.escuela.dao.AlumnoDAO;
import com.jast.escuela.dao.DAOException;
import com.jast.escuela.model.Alumno;

public class MySQLAlumnoDAO implements AlumnoDAO{
	
	final String INSERT = "INSERT INTO alumnos(id_alumno, nombre, apellidos, fecha_nac) VALUE(?, ?, ?, ?)";
	final String UPDATE = "UPDATE alumnos SET nombre = ?, apellidos = ?, fecha_nac = ? WHERE id_alumno = ?";
	final String DELETE = "DELETE FROM alumnos WHERE id_alumno = ?";
	final String GETALL = "SELECT id_alumno, nombre, apellidos, fecha_nac FROM alumnos";
	final String GETONE = "SELECT id_alumno, nombre, apellidos, fecha_nac FROM alumnos WHERE id_alumno = ?";
	
	private Connection conn;
	
	public MySQLAlumnoDAO(Connection conn) {
		this.conn = conn;
	}

	public void insertar(Alumno a) throws DAOException{
		PreparedStatement statement = null;
		try {
			statement = conn.prepareStatement(INSERT);
			statement.setLong(1, a.getId());
			statement.setString(2, a.getNombre());
			statement.setString(3, a.getApellidos());
			statement.setDate(4, new Date(a.getFechaNacimiento().getTime()));
			if (statement.executeUpdate() == 0) {
				throw new DAOException("Puede que no se haya  guardado");
			}
		} catch (SQLException sqlex) {
			throw new DAOException("Error en SQl", sqlex);
		} finally {
			if (statement != null) {
				try {
					statement.close();
				}catch (SQLException e) {
					throw new DAOException("Error en SQl", e);
				}
			}
		}
	}

	public void modificar(Alumno a) throws DAOException{
		PreparedStatement statement = null;
		try {
			statement = conn.prepareStatement(UPDATE);
			statement.setString(1, a.getNombre());
			statement.setString(2, a.getApellidos());
			statement.setDate(3, new Date(a.getFechaNacimiento().getTime()));
			statement.setLong(4, a.getId());
			if (statement.executeUpdate() == 0) {
				throw new DAOException("Puede que no se haya Actualizado");
			}
		} catch (SQLException sqlex) {
			throw new DAOException("Error en SQl", sqlex);
		} finally {
			if (statement != null) {
				try {
					statement.close();
				}catch (SQLException ex) {
					throw new DAOException("Error en SQl", ex);
				}
			}
		}
	}

	public void eliminar(Alumno a) throws DAOException{
		PreparedStatement statement = null;
		try {
			statement = conn.prepareStatement(DELETE);
			statement.setLong(1, a.getId());
			if (statement.executeUpdate() == 0) {
				throw new DAOException("Puede que no se haya Borrado");
			}
		} catch (SQLException sqlex) {
			throw new DAOException("Error en SQl", sqlex);
		} finally {
			if (statement != null) {
				try {
					statement.close();
				}catch (SQLException ex) {
					throw new DAOException("Error en SQl", ex);
				}
			}
		}
	}
	
	private Alumno convertir(ResultSet resultSet) throws SQLException{
		String nombre = resultSet.getString("nombre");
		String apellidos = resultSet.getString("apellidos");
		Date fechaNacimiento = resultSet.getDate("fecha_nac");
		Alumno alumno = new Alumno(nombre, apellidos, fechaNacimiento);
		alumno.setId(resultSet.getLong("id_alumno"));
		return alumno;
	}

	public List<Alumno> obtenerTodos() throws DAOException{
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Alumno> alumnos = new ArrayList<Alumno>();
		try {
			statement = conn.prepareStatement(GETALL);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				alumnos.add(convertir(resultSet));
			}
		} catch (SQLException e) {
			throw new DAOException("Error en SQL", e);
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					throw new DAOException("Error en SQL", e);
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					throw new DAOException("Error en SQL", e);
				}
			}
		}
		return alumnos;
	}

	public Alumno obtener(Long id)  throws DAOException{
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Alumno alumno = null;
		try {
			statement = conn.prepareStatement(GETONE);
			statement.setLong(1, id);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				alumno = convertir(resultSet);
			} else {
				throw new DAOException("no se ha encontrado ese registro");
			}
		} catch (SQLException e) {
			throw new DAOException("Error en SQL", e);
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					throw new DAOException("Error en SQL", e);
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					throw new DAOException("Error en SQL", e);
				}
			}
		}
		return alumno;
	}
	
	public static void main(String[] arg) throws SQLException, DAOException{
		
		Connection conn = null;
		try {
			String jdbc = "jdbc:mysql://localhost:3306/ejemplo";
			conn = DriverManager.getConnection(jdbc, "root", "12345");
			AlumnoDAO dao = new MySQLAlumnoDAO(conn);
			List<Alumno> alumnos = dao.obtenerTodos();
			for (Alumno alumno : alumnos) {
				System.out.println(alumno.toString());
			}
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
}

