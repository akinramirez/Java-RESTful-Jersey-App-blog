package org.akinramirez.restful.recursos;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.akinramirez.restful.modelo.Comentario;
import org.akinramirez.restful.servicio.ComentarioServicio;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ComentarioRecurso {
  
  ComentarioServicio servicio = new ComentarioServicio();
  
  @GET
  public List<Comentario> getComentarios(@PathParam("articuloId") int articuloId){
    return servicio.getComentarios(articuloId);
  }
  
  @GET
  @Path("/{comentarioId}")
  public Comentario getComentario(@PathParam("articuloId") int articuloId,
                                  @PathParam("comentarioId") int comentarioId){
    return servicio.getComentario(articuloId, comentarioId);
  }
  
  @POST
  public Comentario addComentario(@PathParam("articuloId") int articuloId, Comentario comentario){
    return servicio.addComentario(articuloId, comentario);
  }
  
  @DELETE
  @Path("/{comentarioId}")
  public void deleteArticulo(@PathParam("articuloId") int articuloId,
                             @PathParam("comentarioId") int comentarioId){
    servicio.deleteComentario(articuloId, comentarioId);
  }
  
  @PUT
  @Path("/{comentarioId}")
  public Comentario updateArticulo(@PathParam("articuloId") int articuloId,
                             @PathParam("comentarioId") int comentarioId,
                             Comentario comentario){
    return servicio.updateComentario(articuloId, comentario);
  } 
  
}
