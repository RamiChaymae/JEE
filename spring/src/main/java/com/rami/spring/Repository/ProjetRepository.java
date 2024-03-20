/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.rami.spring.Repository;

import com.rami.spring.model.Projet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pc
 */

@Repository
public interface ProjetRepository extends JpaRepository< Projet , Long> {
    long count() ;
    Page<Projet> findByTitreContains(String kw, Pageable pageable);
}
