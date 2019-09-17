package org.akinramirez.restful.recursos;

import java.util.List;
import javax.validation.constraints.Past;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.akinramirez.restful.modelo.Articulo;
import org.akinramirez.restful.servicio.ArticuloServicio;

@Path("/articulos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ArticuloRecurso {

  ArticuloServicio artServicio = new ArticuloServicio();

  @GET
  public List<Articulo> getArticulos(
          @QueryParam("autor") String autor,
          @QueryParam("year") int year,
          @QueryParam("month") int month) {
    if (autor != null && autor.length() > 0) {
      return artServicio.getArticuloPorAutor(autor);
    }
    if (year > 0 && month > 0) {
      return artServicio.getArticuloPorFecha(year, month);
    }
    return artServicio.getArticulos();
  }

  @GET
  @Path("/{articuloId}")
  public Articulo getArticulo(@PathParam("articuloId") int id) {
    return artServicio.getArticulo(id);
  }

  @POST
  public Articulo addArticulo(Articulo articulo) {
    return artServicio.addArticulo(articulo);
  }

  @DELETE
  @Path("/{articuloId}")
  public void deleteArticulo(@PathParam("articuloId") int id) {
    artServicio.deleteArticulo(id);
  }

  @PUT
  @Path("/{articuloId}")
  public Articulo updateArticulo(@PathParam("articuloId") int id, Articulo articulo) {
    articulo.setId(id);
    return artServicio.updateArticulo(articulo);
  }
  
  @Path("/{articuloId}/comentarios")
  public ComentarioRecurso getComentarios(){
    return new ComentarioRecurso();
  }

}
