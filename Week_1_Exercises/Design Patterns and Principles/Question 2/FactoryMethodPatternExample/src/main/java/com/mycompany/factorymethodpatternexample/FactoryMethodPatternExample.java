/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.factorymethodpatternexample;

/**
 *
 * @author shari
 */
public class FactoryMethodPatternExample {

    public static void main(String[] args) {
        DocumentFactory documentFactory = new DocumentFactory();
        Document wordDocument1 = documentFactory.createADocument("Word");
        wordDocument1.createDocument(0, "Word File 1", "30/07/2024");

        Document pdfDocument1 = documentFactory.createADocument("PDF");
        pdfDocument1.createDocument(1, "PDF File 1", "01/08/2024");

        Document excelDocument1 = documentFactory.createADocument("Excel");
        excelDocument1.createDocument(2, "Excel File 1", "01/08/2024");

        System.out.println("Word Document details: " + wordDocument1.getDocumentID() + " " + wordDocument1.getDocumentName() + " " + wordDocument1.getCreatedAt());
        System.out.println("PDF Document details: " + pdfDocument1.getDocumentID() + " " + pdfDocument1.getDocumentName() + " " + pdfDocument1.getCreatedAt());
        System.out.println("Excel Document details: " + excelDocument1.getDocumentID() + " " + excelDocument1.getDocumentName() + " " + excelDocument1.getCreatedAt());

    }
}
