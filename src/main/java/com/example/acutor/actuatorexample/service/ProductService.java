package com.example.acutor.actuatorexample.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.acutor.actuatorexample.model.Product;
import com.example.acutor.actuatorexample.repository.ProductRepositroy;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepositroy productRepositroy;

	public ProductService(ProductRepositroy productRepositroy) {
		super();
		this.productRepositroy = productRepositroy;
	}
	
	public ByteArrayOutputStream genratepdf() throws Exception {
//		List<Product> product = productRepositroy.findByPriceGreaterThan(40000);
		List<Product> product = productRepositroy.findAll();
		//System.out.println(product.toString());
		ByteArrayOutputStream byteArrayOutputStream =new ByteArrayOutputStream();
		PdfDocument pdfDocument =new PdfDocument(new PdfWriter(byteArrayOutputStream));
		Document document = new Document(pdfDocument);
		
		Table table = new Table(new float[] {2,2,2,2});
		table.setBackgroundColor(new DeviceRgb(135, 206, 250));
		
		Style style = new Style()
				.setBackgroundColor(DeviceRgb.BLUE)
				.setFontColor(ColorConstants.WHITE)
				.setBold();
		
		
		Cell cell = new  Cell();
		cell.add(new Paragraph("Product").setFontSize(12).setBold());
		table.addHeaderCell(cell);
		
		cell = new  Cell();
		cell.add(new Paragraph("Product Colour").setFontSize(12).setBold());
		table.addHeaderCell(cell);
		
		cell = new  Cell();
		cell.add(new Paragraph("Product Model").setFontSize(12).setBold());
		table.addHeaderCell(cell);
		
		cell = new  Cell();
		cell.add(new Paragraph("Product Price").setFontSize(12).setBold());
		table.addHeaderCell(cell);
		
		for(Product product2 : product) {
			table.addCell(product2.getName());
			table.addCell(product2.getColour());
			table.addCell(product2.getType());
			table.addCell(String.valueOf(product2.getPrice()));
			
		}
		document.add(table);
		document.close();
			
		return byteArrayOutputStream;
		
	}

}
