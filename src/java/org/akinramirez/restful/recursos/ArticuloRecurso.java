package org.akinramirez.restful.recursos;

import java.net.URI;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.akinramirez.restful.modelo.Articulo;
import org.akinramirez.restful.modelo.Navegacion;
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
  public Articulo getArticulo(@PathParam("articuloId") int id,
          @Context UriInfo uriInfo) {
    Articulo respuesta = artServicio.getArticulo(id);
    String linkSelf = uriInfo.getAbsolutePath().toString();
    String linkComm = linkSelf+"/comentarios";
    Navegacion self = new Navegacion("Recurso", linkSelf, "Locacion del recurso");
    Navegacion comm = new Navegacion("Comentario", linkComm, "Locacion de los comentarios");
    respuesta.getNavegacion().add(self);
    respuesta.getNavegacion().add(comm);
    return respuesta;
  }

  @POST
  public Response addArticulo(Articulo articulo, @Context UriInfo uriInfo) {
    Articulo respuesta = artServicio.addArticulo(articulo);
    //return Response.status(Response.Status.CREATED).entity(respuesta).build();
    URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(respuesta.getId())).build();
    return Response.created(uri).entity(respuesta).build();
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
  public ComentarioRecurso getComentarios() {
    return new ComentarioRecurso();
  }

}
