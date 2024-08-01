/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.factorymethodpatternexample;

/**
 *
 * @author shari
 */
public class ExcelDocumentClass implements Document {

    private int documentID;
    private String documentName = "";
    private String createdAt = "";
    String documentType = "Excel";
    @Override
    public void createDocument(int documentID, String documentName, String createdAt ) {
        this.documentID = documentID;
        this.documentName = documentName;
        this.createdAt = createdAt;
    }

    /**
     * @return the documentID
     */
    @Override
    public int getDocumentID() {
        return documentID;
    }

    /**
     * @return the documentName
     */
    @Override
    public String getDocumentName() {
        return documentName;
    }

    /**
     * @return the createdAt
     */
    @Override
    public String getCreatedAt() {
        return createdAt;
    }
}
