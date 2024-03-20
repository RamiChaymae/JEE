/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.rami.spring.Repository;


import com.rami.spring.model.Publication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author pc
 */
public interface PublicationRepository  extends JpaRepository< Publication , Long > {
    long count() ;
    Page<Publication> findByTitreContains(String kw, Pageable pageable);
}
