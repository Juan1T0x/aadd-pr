package jaxb;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class Semana3 {

	public static void main(String[] args) throws JAXBException {
		
		
		// 1. Construir el contexto JAXB
		JAXBContext contexto = JAXBContext.newInstance(Acta.class);
		
		
		// Obtener el árbol de contenido de un documento XML
		Unmarshaller unmarshaller = contexto.createUnmarshaller(); 
		Acta acta = (Acta) unmarshaller.unmarshal(new File("xml/acta.xml"));
		
		double notaMedia = acta.calcularNotaMedia();
		System.out.println("Nota media de las calificaciones: " + notaMedia);
	
		
        int numDiligenciasUltimoMes = acta.contarDiligenciasUltimoMes();
        System.out.println("Número de diligencias del último mes: " + numDiligenciasUltimoMes);
        
        acta.incrementarNotas(0.5);
		
		// Empaquetado en un documento XML (marshalling)
		Marshaller marshaller = contexto.createMarshaller();
		
		marshaller.setProperty("jaxb.formatted.output", true);
		marshaller.marshal(acta, new File("xml/actaSemana3.xml"));
		
		// Crear el acta
        Acta acta2 = new Acta();
        acta2.setConvocatoria("febrero");
        acta2.setCurso(2023);
        acta2.setAsignatura(1092);

        // Crear calificaciones
        List<Calificacion> calificaciones = new ArrayList<>();
        Calificacion calif1 = new Calificacion();
        calif1.setNif("23322156M");
        calif1.setNota(10);

        Calificacion calif2 = new Calificacion();
        calif2.setNif("13322156M");
        calif2.setNombre("Pepe");
        calif2.setNota(8);

        calificaciones.add(calif1);
        calificaciones.add(calif2);
        acta2.setCalificaciones(calificaciones);

        // Crear diligencias
        List<Diligencia> diligencias = new ArrayList<>();
        Diligencia diligencia = new Diligencia();
        diligencia.setFecha("2023-09-12");
        diligencia.setNif("13322156M");
        diligencia.setNota(9);
        diligencias.add(diligencia);
        acta2.setDiligencias(diligencias);
        
        marshaller.marshal(acta2, new File("xml/actaNuevaSemana3.xml"));
	}

}
