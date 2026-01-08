package com.registroAPi.resgistroApi.RestController;

import com.registroAPi.resgistroApi.Entity.Registro;
import com.registroAPi.resgistroApi.Entity.ServiceResult;
import com.registroAPi.resgistroApi.Service.RegistroService;
import com.registroAPi.resgistroApi.Service.ValidationService;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api_entrevista")
public class RegistroRestController {

    @Autowired
    private RegistroService registroService;
    @Autowired
    private ValidationService validationService;

    @PostMapping("/ingresar")
    public ResponseEntity CrearRegistro(@RequestBody Registro registro) {

        ServiceResult serviceResult = new ServiceResult();
        try {
            BindingResult bindingResult = validationService.validateObjects(registro);
            if (bindingResult.hasErrors()) {
                List<String> errors = new ArrayList<>();
                for (ObjectError error : bindingResult.getAllErrors()) {
                    errors.add(error.getDefaultMessage());
                }
                serviceResult.status = 400;
                serviceResult.message = "Errores de validacion";
                serviceResult.object = bindingResult.getAllErrors();
                return ResponseEntity.badRequest().body(errors);
            } else {
                serviceResult.object = registroService.CrearRegistro(registro);
                serviceResult.status = 201;
                serviceResult.message = "Usuario creado con exito.";
            }

        } catch (Exception ex) {
            serviceResult.status = 500;
            serviceResult.message = ex.getLocalizedMessage();

        }
        return ResponseEntity.status(serviceResult.status).body(serviceResult);
    }

    @PutMapping("/ingresar/{idUsuario}")
    public ResponseEntity<?> EditarRegistro(@PathVariable int idUsuario, @RequestBody Registro registroUpdate) {

        ServiceResult serviceResult = new ServiceResult();

        try {

            BindingResult bindingResult = validationService.validateObjects(registroUpdate);

            if (bindingResult.hasErrors()) {
                List<String> errors = new ArrayList<>();
                for (ObjectError error : bindingResult.getAllErrors()) {
                    errors.add(error.getDefaultMessage());
                }

                serviceResult.status = 400;
                serviceResult.message = "Errores de validacion";
                serviceResult.object = bindingResult.getAllErrors();
                return ResponseEntity.badRequest().body(errors);
            }

            Registro registroEditado = registroService.EditarRegistro(idUsuario, registroUpdate);

            if (registroEditado == null) {
                serviceResult.status = 404;
                serviceResult.message = "Registro no encontrado";
                return ResponseEntity.status(404).body(serviceResult);
            }

            serviceResult.status = 200;
            serviceResult.message = "Registro actualizado con exito";
            serviceResult.object = registroEditado;

        } catch (Exception ex) {

            serviceResult.status = 500;
            serviceResult.message = ex.getLocalizedMessage();
        }
        return ResponseEntity.status(serviceResult.status).body(serviceResult);

    }

    @GetMapping("/consultar/{idUsuario}")
    public ResponseEntity<?> GetRegistroByid(@PathVariable int idUsuario) {
        ServiceResult serviceResult = new ServiceResult();

        Registro registro = registroService.GetRegistroByid(idUsuario);

        try {
            if (registro == null) {
                serviceResult.status = 404;
                serviceResult.message = "Registro de usuario no encontrado";
            } else {

                serviceResult.status = 200;
                serviceResult.message = "Registro encontrado";
                serviceResult.object = registro;

            }
        } catch (Exception ex) {
            serviceResult.status = 500;
            serviceResult.message = ex.getLocalizedMessage();
        }
        return ResponseEntity.status(serviceResult.status).body(serviceResult);

    }

    @GetMapping("/consultar/telefono/{telefono}")
    public ResponseEntity<?> ConsultarRegistroTelefono(@PathVariable String telefono) {

        ServiceResult serviceResult = new ServiceResult();

        String RegExp = "^[0-9]+$";

        if (telefono.matches(RegExp)) {
            Registro registro = registroService.ConsultarRegistroTelefono(telefono);

            try {
                if (registro == null) {
                    serviceResult.status = 400;
                    serviceResult.message = "Registro no encontrado por telefono";
                } else {
                    serviceResult.status = 200;
                    serviceResult.message = "Registro encontrado exitosamente";
                    serviceResult.object = registro;
                }

            } catch (Exception ex) {
                serviceResult.status = 500;
                serviceResult.message = ex.getLocalizedMessage();
            }
        }else{
            return ResponseEntity.badRequest().body("Solo se permiten caracteres numericos");
        }

        return ResponseEntity.status(serviceResult.status).body(serviceResult);
    }

    @GetMapping("/consultar")
    public ResponseEntity<?> ConsultarRegistros() {

        ServiceResult serviceResult = new ServiceResult();

        List<Registro> registros = registroService.ConsultarRegistros();

        try {
            if (registros.isEmpty()) {
                serviceResult.status = 204;
                serviceResult.message = "No existen registros";
            } else {
                serviceResult.status = 200;
                serviceResult.message = "Registros encontrados";
                serviceResult.object = registros;
            }

        } catch (Exception ex) {

            serviceResult.status = 500;
            serviceResult.message = ex.getLocalizedMessage();
        }
        return ResponseEntity.status(serviceResult.status).body(serviceResult);
    }

    @DeleteMapping("/eliminar/{idUsuario}")
    public ResponseEntity<?> EliminarRegistro(@PathVariable int idUsuario) {

        ServiceResult serviceResult = new ServiceResult();

        try {
            Registro registroEliminado = registroService.EliminarRegistro(idUsuario);
            if (registroEliminado == null) {
                serviceResult.status = 404;
                serviceResult.message = "Registro no encontrado";
            } else {
                serviceResult.status = 200;
                serviceResult.message = "Registro elimiando correctamente.";
                serviceResult.object = registroEliminado;
            }

        } catch (Exception ex) {
            serviceResult.status = 500;
            serviceResult.message = ex.getLocalizedMessage();
        }

        return ResponseEntity.status(serviceResult.status).body(serviceResult);

    }

}
