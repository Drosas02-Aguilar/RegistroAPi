package com.registroAPi.resgistroApi.Service;

import com.registroAPi.resgistroApi.DAO.IRegistro;
import com.registroAPi.resgistroApi.Entity.Registro;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistroService {

    @Autowired
    private IRegistro iRegistro;

    
    public List<Registro> ConsultarRegistros(){
        List<Registro> registro = iRegistro.findAll();
        return registro;
        
    }
    
    
    public Registro GetRegistroByid(int idusuario) {
        Optional<Registro> registro = iRegistro.findById(idusuario);
        
        return registro.isPresent() ? registro.get() : null;
    
    }
    
    
    public Registro ConsultarRegistroTelefono(String telefono){
        Optional<Registro> registro = iRegistro.findByTelefono(telefono);
        return registro.isPresent() ? registro.get() : null;
    
    }
    
    
    @Transactional
    public Registro CrearRegistro(Registro registro) {
        
        return iRegistro.save(registro);
    }
    
        
   @Transactional
   public Registro EditarRegistro(int idusuario, Registro registroUpdate){
       Optional<Registro> registroPtional = iRegistro.findById(idusuario);
       
       if(registroPtional.isPresent()){
           Registro registro = registroPtional.get();
           registro.setUsuario(registroUpdate.getUsuario());
           registro.setTelefono(registroUpdate.getTelefono());
           registro.setEmail(registroUpdate.getEmail());
           
            return iRegistro.save(registro); 
       } 
       
       return null;
   }
   
   
   @Transactional
   public Registro EliminarRegistro(int idusuario){
       Optional<Registro> registroOptional = iRegistro.findById(idusuario);
       if(registroOptional.isPresent()){
           Registro registro = registroOptional.get();
           iRegistro.delete(registro);
           return registro;
       }
       
       return null;
   }
   
   
   
    
    
}
