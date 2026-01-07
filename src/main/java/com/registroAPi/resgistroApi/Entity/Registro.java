
package com.registroAPi.resgistroApi.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="REGISTRO")
public class Registro {
    
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idusuario")
  private int idusaurio;
  
  @Column(name="usuario", nullable = false, length = 55)
  private String usuario;
  
  @Column (name="telefono", nullable = false, length =60)
  private String telefono;
  
   @Column (name="correo", nullable =  true, length = 100)
   private String corrreo;
   
   

    public int getIdusaurio() {
        return idusaurio;
    }

    public void setIdusaurio(int idusaurio) {
        this.idusaurio = idusaurio;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorrreo() {
        return corrreo;
    }

    public void setCorrreo(String corrreo) {
        this.corrreo = corrreo;
    }
   
   
   
}
