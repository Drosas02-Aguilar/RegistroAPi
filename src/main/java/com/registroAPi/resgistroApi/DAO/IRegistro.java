/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroAPi.resgistroApi.DAO;

import com.registroAPi.resgistroApi.Entity.Registro;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRegistro extends JpaRepository<Registro, Integer>{
  
    Optional<Registro>findByTelefono(String telefono);
    
}
