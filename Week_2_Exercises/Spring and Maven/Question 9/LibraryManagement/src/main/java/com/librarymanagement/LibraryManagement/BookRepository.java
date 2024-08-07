/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.librarymanagement.LibraryManagement;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 *
 * @author shari
 */
public interface BookRepository extends JpaRepository<Book, Long> {
    
}
