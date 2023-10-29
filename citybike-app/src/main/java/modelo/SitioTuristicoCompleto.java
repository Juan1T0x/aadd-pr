package modelo;

import java.awt.Image;
import java.util.List;

public class SitioTuristicoCompleto extends SitioTuristico {

	private String resumen;
	private List<String> categorias;
	private List<String> enlacesComplementarios;
	private String imagen;
	private String id;

	public SitioTuristicoCompleto(String nombre, String descripcion, double distancia, String urlWikipedia,
			String resumen, List<String> categorias, List<String> enlacesComplementarios, String imagen, String id) {
		super(nombre, descripcion, distancia, urlWikipedia);
		this.resumen = resumen;
		this.categorias = categorias;
		this.enlacesComplementarios = enlacesComplementarios;
		this.imagen = imagen;
		this.id = id;
	}

	public SitioTuristicoCompleto() {
		super();
	}

	public String getResumen() {
		return resumen;
	}

	public void setResumen(String resumen) {
		this.resumen = resumen;
	}

	public List<String> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<String> categorias) {
		this.categorias = categorias;
	}

	public List<String> getEnlacesComplementarios() {
		return enlacesComplementarios;
	}

	public void setEnlacesComplementarios(List<String> enlacesComplementarios) {
		this.enlacesComplementarios = enlacesComplementarios;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return super.toString() + "\nSitioTuristicoCompleto [resumen=" + resumen + ", categorias=" + categorias
				+ ", enlacesComplementarios=" + enlacesComplementarios + ", imagen=" + imagen + ", id=" + id + "]";
	}

}
