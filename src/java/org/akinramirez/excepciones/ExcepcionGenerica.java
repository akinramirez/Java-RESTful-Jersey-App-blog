package org.akinramirez.excepciones;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.akinramirez.restful.modelo.mensajeError;

@Provider
public class ExcepcionGenerica implements ExceptionMapper<Throwable> {

  @Override
  public Response toResponse(Throwable exception) {
    mensajeError mensaje = new mensajeError(exception.getMessage(), 500,
            exception.getCause().toString());
    return Response.status(Response.Status.NOT_FOUND).entity(mensaje).build();
  }

}
