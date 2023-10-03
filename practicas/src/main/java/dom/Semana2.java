package dom;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


public class Semana2 {
	public static void main(String[] args) throws Exception {
		// 1. Obtener una factoría
		DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
		// 2. Pedir a la factoría la construcción del analizador
		DocumentBuilder analizador = factoria.newDocumentBuilder();
		// 3. Analizar el documento
		Document documento = analizador.parse("xml/acta.xml");
		
		// EJERCICIO 1 - Contar el número de diligencias DEL ULTIMO MES
		
		System.out.println("Ejercicio 1 - Diligencias del ultimo mes:");
		
		
        Date fechaActual = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        int numDili = 0;

        NodeList diligencias = documento.getElementsByTagName("diligencia");
        for (int i = 0; i < diligencias.getLength(); i++) {
            Element diligencia = (Element) diligencias.item(i);
            String fechaStr = diligencia.getAttribute("fecha");
            Date fechaDiligencia = sdf.parse(fechaStr);
            if (isDateInLastMonth(fechaActual, fechaDiligencia)) {
                System.out.println("Diligencia del último mes: " + diligencia.getTextContent());
                numDili++;
            }
        }
        
        System.out.println("Número de diligencias del último mes: " + numDili + "\n");
		
		// EJERCICIO 2 - Hacer la nota media solo SOLO con las calificaciones
		
		System.out.println("Ejercicio 2 - Nota media de las calificaciones");
		
        int totalNotas = 0;
        int sumaNotas = 0;
		
        NodeList calificaciones = documento.getElementsByTagName("calificacion");
		for (int i = 0; i < calificaciones.getLength() ; i++) {   
            Element calificacion = (Element) calificaciones.item(i);
            NodeList notaElements = calificacion.getElementsByTagName("nota");
            if (notaElements.getLength() > 0) {
                
            	Element notaElement = (Element) notaElements.item(0);
                String notaStr = notaElement.getTextContent();
                System.out.println("Nota: " + notaStr);
                
                int nota = Integer.parseInt(notaStr);
                sumaNotas += nota;
                totalNotas++;
                
            } else {
                System.out.println("No se encontró la nota para la calificación " + i);
            }
		}
		
        if (totalNotas > 0) {
            double notaMedia = (double) sumaNotas / totalNotas;
            System.out.println("Nota media: " + notaMedia);
        } else {
            System.out.println("No se encontraron notas.");
        }
		
        // EJERCICIO 3 - Incrementar en 0.5 la calificacion de todos los alumnos (No superar el 10)
        
        System.out.println("\nEjercicio 3 - Incrementar calificaciones");

		for (int i = 0; i < calificaciones.getLength() ; i++) {   
            Element calificacion = (Element) calificaciones.item(i);
            NodeList notaElements = calificacion.getElementsByTagName("nota");
            if (notaElements.getLength() > 0) {
                
            	Element notaElement = (Element) notaElements.item(0);
                String notaStr = notaElement.getTextContent();
                double nota = Double.parseDouble(notaStr);
                nota = Math.min(nota + 0.5, 10.0);
                DecimalFormat df = new DecimalFormat("#.#");
                String nuevaNotaStr = df.format(nota);
                notaElement.setTextContent(nuevaNotaStr);
                notaStr = notaElement.getTextContent();
                
                System.out.println("Nueva nota: " + notaStr);
                
            } else {
                System.out.println("No se encontró la nota para la calificación " + i);
            }
		}
		
		// Como el API no ofrece funcionalidad para modificar el fichero original, se crea otro con el contenido modificado
		
		// 1. Construye la factoría de transformación y obtiene un // transformador
		TransformerFactory tFactoria = TransformerFactory.newInstance(); 
		Transformer transformacion = tFactoria.newTransformer();
		// 2. Establece la entrada, como un árbol DOM
		Source input = new DOMSource(documento);
		// 3. Establece la salida, un fichero en disco
		Result output = new StreamResult("xml/acta-modificada.xml");
		// 4. Aplica la transformación
		transformacion.transform(input, output);
		
		System.out.println("\nFin del programa.");
		
	}
	
    private static boolean isDateInLastMonth(Date fechaActual, Date fechaDiligencia) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        String fechaActualStr = sdf.format(fechaActual);
        String fechaDiligenciaStr = sdf.format(fechaDiligencia);
        return fechaActualStr.equals(fechaDiligenciaStr);
    }
}
