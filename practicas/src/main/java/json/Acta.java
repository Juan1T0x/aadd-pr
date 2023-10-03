package json;

import javax.json.bind.annotation.JsonbProperty;

public class Acta {
    @JsonbProperty("@convocatoria")
    private String convocatoria;

    @JsonbProperty("@curso")
    private String curso;

    @JsonbProperty("@asignatura")
    private String asignatura;

    @JsonbProperty("calificacion")
    private Calificacion[] calificaciones;

    @JsonbProperty("diligencia")
    private Diligencia diligencia;
    
    public Acta() {
        // Constructor vacío requerido para JSON-B
    }

    public Acta(String convocatoria, String curso, String asignatura, Calificacion[] calificaciones, Diligencia diligencia) {
        this.convocatoria = convocatoria;
        this.curso = curso;
        this.asignatura = asignatura;
        this.calificaciones = calificaciones;
        this.diligencia = diligencia;
    }
    
    
    
    public String getConvocatoria() {
		return convocatoria;
	}

	public void setConvocatoria(String convocatoria) {
		this.convocatoria = convocatoria;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(String asignatura) {
		this.asignatura = asignatura;
	}

	public Calificacion[] getCalificaciones() {
		return calificaciones;
	}

	public void setCalificaciones(Calificacion[] calificaciones) {
		this.calificaciones = calificaciones;
	}

	public Diligencia getDiligencia() {
		return diligencia;
	}

	public void setDiligencia(Diligencia diligencia) {
		this.diligencia = diligencia;
	}



	// Clase interna para representar una calificación
    public static class Calificacion {
        @JsonbProperty("nif")
        private String nif;

        @JsonbProperty("nombre")
        private String nombre;

        @JsonbProperty("nota")
        private String nota;

		public String getNif() {
			return nif;
		}

		public void setNif(String nif) {
			this.nif = nif;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public String getNota() {
			return nota;
		}

		public void setNota(String nota) {
			this.nota = nota;
		}
  
    }

    // Clase interna para representar una diligencia
    public static class Diligencia {
        @JsonbProperty("@fecha")
        private String fecha;

        @JsonbProperty("nif")
        private String nif;

        @JsonbProperty("nota")
        private String nota;

		public String getFecha() {
			return fecha;
		}

		public void setFecha(String fecha) {
			this.fecha = fecha;
		}

		public String getNif() {
			return nif;
		}

		public void setNif(String nif) {
			this.nif = nif;
		}

		public String getNota() {
			return nota;
		}

		public void setNota(String nota) {
			this.nota = nota;
		}
  
    }
}
