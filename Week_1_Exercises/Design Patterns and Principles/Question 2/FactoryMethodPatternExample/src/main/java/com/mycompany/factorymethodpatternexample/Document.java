/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.factorymethodpatternexample;

/**
 *
 * @author shari
 */
public interface Document {
    public void createDocument(int documentID, String documentName, String createdAt);
    public int getDocumentID();
    public String getDocumentName();
    public String getCreatedAt();
}
