package com.jast.escuela.model;

public class Matricula {

	private long alumno;
	private long asignatura;
	private int year;
	private Integer nota = null;

	public Matricula(long alumno, long asignatura, int year) {
		super();
		this.alumno = alumno;
		this.asignatura = asignatura;
		this.year = year;
	}

	public long getAlumno() {
		return alumno;
	}

	public void setAlumno(long alumno) {
		this.alumno = alumno;
	}

	public long getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(long asignatura) {
		this.asignatura = asignatura;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Integer getNota() {
		return nota;
	}

	public void setNota(Integer nota) {
		this.nota = nota;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (alumno ^ (alumno >>> 32));
		result = prime * result + (int) (asignatura ^ (asignatura >>> 32));
		result = prime * result + ((nota == null) ? 0 : nota.hashCode());
		result = prime * result + year;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Matricula other = (Matricula) obj;
		if (alumno != other.alumno)
			return false;
		if (asignatura != other.asignatura)
			return false;
		if (nota == null) {
			if (other.nota != null)
				return false;
		} else if (!nota.equals(other.nota))
			return false;
		if (year != other.year)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Matricula [alumno=" + alumno + ", asignatura=" + asignatura + ", year=" + year + ", nota=" + nota + "]";
	}
}
