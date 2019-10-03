package org.akinramirez.excepciones;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.akinramirez.restful.modelo.mensajeError;

@Provider
public class DatosNoEncontradosMapper implements ExceptionMapper<DatosNoEncontradosExcepcion>{

  @Override
  public Response toResponse(DatosNoEncontradosExcepcion exception) {
    mensajeError mensaje = new mensajeError("Datos no encontrados", 404, 
            "Esta informacion no se encuentra");
    return Response.status(Response.Status.NOT_FOUND)
            .entity(mensaje).build();
  }
  
}
