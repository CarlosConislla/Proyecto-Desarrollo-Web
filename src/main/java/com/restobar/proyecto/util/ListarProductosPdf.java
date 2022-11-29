package com.restobar.proyecto.util;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.restobar.proyecto.modelo.Producto;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;


@Component("/producto/lista")
public class ListarProductosPdf extends AbstractPdfView{
	
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		 @SuppressWarnings("unchecked")
		List<Producto> productos = (List<Producto>) model.get("productos");
		 
		 Font fuenteTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD,20,Color.BLUE);
		 Font fuenteTituloColumnas = FontFactory.getFont(FontFactory.HELVETICA_BOLD ,12,Color.BLUE);
		 Font fuenteDataCeldas = FontFactory.getFont(FontFactory.COURIER ,10,Color.BLACK);
		 
		 document.setPageSize(PageSize.LETTER.rotate());
		 document.setMargins(-20, -20, 40, 20);
		 document.open();
		 
		 PdfPCell celda = null;
		 PdfPTable tablatitutlo = new PdfPTable(1);
		 
		 
		 celda = new PdfPCell(new Phrase("Listado de productos Restobar Pupuchima", fuenteTitulo));
		 celda.setBorder(0);
		 celda.setBackgroundColor(new Color(40,190,138));
		 celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		 celda.setVerticalAlignment(Element.ALIGN_CENTER);
		 celda.setPadding(30);
		 
		 tablatitutlo.addCell(celda);
		 tablatitutlo.setSpacingAfter(30);
		 
		 PdfPTable tablaproductos = new PdfPTable(5);	 
		 celda = new PdfPCell(new Phrase("ID", fuenteTituloColumnas));
		 celda.setBackgroundColor(Color.lightGray);
		 celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		 celda.setVerticalAlignment(Element.ALIGN_CENTER);
		 celda.setPadding(10);
		 tablaproductos.addCell(celda);
		 
		 celda = new PdfPCell(new Phrase("CATEGORIA", fuenteTituloColumnas));
		 celda.setBackgroundColor(Color.lightGray);
		 celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		 celda.setVerticalAlignment(Element.ALIGN_CENTER);
		 celda.setPadding(10);
		 tablaproductos.addCell(celda);
		 
		 celda = new PdfPCell(new Phrase("NOMBRE", fuenteTituloColumnas));
		 celda.setBackgroundColor(Color.lightGray);
		 celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		 celda.setVerticalAlignment(Element.ALIGN_CENTER);
		 celda.setPadding(10);
		 tablaproductos.addCell(celda);
		 
		 celda = new PdfPCell(new Phrase("CANTIDAD", fuenteTituloColumnas));
		 celda.setBackgroundColor(Color.lightGray);
		 celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		 celda.setVerticalAlignment(Element.ALIGN_CENTER);
		 celda.setPadding(10);
		 tablaproductos.addCell(celda);
		 
		 celda = new PdfPCell(new Phrase("PRECIO", fuenteTituloColumnas));
		 celda.setBackgroundColor(Color.lightGray);
		 celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		 celda.setVerticalAlignment(Element.ALIGN_CENTER);
		 celda.setPadding(10);
		 tablaproductos.addCell(celda);
		 
		 for (Producto producto  : productos) {
				celda = new PdfPCell(new Phrase(producto.getId().toString(), fuenteDataCeldas));
				celda.setPadding(5);
				tablaproductos.addCell(celda);
				
				celda = new PdfPCell(new Phrase(producto.getCategoria().getNombre(), fuenteDataCeldas));
				celda.setPadding(5);
				tablaproductos.addCell(celda);
				
				celda = new PdfPCell(new Phrase(producto.getNombre(), fuenteDataCeldas));
				celda.setPadding(5);
				tablaproductos.addCell(celda);
				
				celda = new PdfPCell(new Phrase(String.valueOf(producto.getCantidad()), fuenteDataCeldas));
				celda.setPadding(5);
				tablaproductos.addCell(celda);
				
				celda = new PdfPCell(new Phrase(String.valueOf(producto.getPrecio()), fuenteDataCeldas));
				celda.setPadding(5);
				tablaproductos.addCell(celda);
				}
		 document.add(tablatitutlo);
		 document.add(tablaproductos);
	}

}
