package modelo;

import java.awt.Image;
import java.util.List;

public class SitioTuristicoCompleto extends SitioTuristico{
	
	private String resumen;
	private List<String> categorias;
	private List<String> enlacesComplementarios;
	private Image imagen;

	public SitioTuristicoCompleto(String nombre, String descripcion, double distancia, String urlWikipedia, String resumen, List<String> categorias, List<String> enlacesComplementarios, Image imagen) {
		super(nombre, descripcion, distancia, urlWikipedia);
		
		this.resumen = resumen;
		this.categorias = categorias;
		this.enlacesComplementarios = enlacesComplementarios;
		this.imagen = imagen;
	}

}
