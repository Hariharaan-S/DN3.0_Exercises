/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.factorymethodpatternexample;

/**
 *
 * @author shari
 */
public class DocumentFactory {
    public Document createADocument(String type) {
        return switch (type) {
            case "Word" -> new WordDocumentClass();
            case "PDF" -> new PDFDocumentClass();
            case "Excel" -> new ExcelDocumentClass();
            default -> null;
        };
    }
}
