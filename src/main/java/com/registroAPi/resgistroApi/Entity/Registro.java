package com.registroAPi.resgistroApi.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "REGISTRO")
public class Registro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idusuario")
    private int idusuario;

    @NotBlank(message = "El usuario no puede estar vacio")
    @Size(max = 60, message = "El usuario no puede exceder de los 60 caracteres")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑs ]+$",
            message = "El usuario debe contener solo caracteres alfabeticos")
    @Column(name = "usuario", nullable = false, length = 55)
    private String usuario;

    @NotBlank(message = "EL telefono no puede estar vacio")
    @Pattern(regexp = "^[0-9]+$",
            message = "El telefono solo debe contener numeros")
    @Size(max = 10, message = "El telefono debe de tener 10 digitos ")
    @Column(name = "telefono", nullable = false, length = 60)
    private String telefono;

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
            message = "El formato del correo no es valido")
    @Size(max = 100, message = "El email no puede exceder los 100 caracteres")
    @Column(name = "email", nullable = true, length = 100)
    private String email;

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

  

}
