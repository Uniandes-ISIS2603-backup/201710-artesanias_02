package co.edu.uniandes.csw.artesanias.resources;

import co.edu.uniandes.csw.artesanias.dtos.ArtesaniaDTO;
import co.edu.uniandes.csw.artesanias.dtos.ArtesanoDTO;
import co.edu.uniandes.csw.artesanias.ejbs.ArtesaniaLogic;
import co.edu.uniandes.csw.artesanias.entities.ArtesaniaEntity;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * @author d.narvaez11
 */
public class ArtesaniasResource
{
	@Inject
	private ArtesaniaLogic logic;
	
	@Context
	private HttpServletResponse response;
	
	@QueryParam( "page" )
	private Integer page;
	
	@QueryParam( "limit" )
	private Integer maxRec;
	
	@POST
	public ArtesaniaDTO createArtesania( ArtesaniaDTO dto )
	{
		return new ArtesaniaDTO( logic.createArtesania( dto.toEntity( ) ) );
	}
	
	@GET
	public List<ArtesaniaDTO> getArtesanias( @PathParam( "artesanoId" ) Long id )
	{
		return listEntity2DTO( logic.getArtesaniasFromArtesano( id ) );
	}
	
	@GET
	@Path( "/{id_art}" )
	public String get( @PathParam( "artesanoId" ) Long artesanoId, @PathParam( "id_art" ) Long id )
	{
		return "Artesano: " + artesanoId + ": \n\tArtesania: " + id;
	}
	
	@PUT
	@Path( "{id: \\d+}" )
	public ArtesaniaDTO updateArtesania( @PathParam( "id" ) Long id, ArtesaniaDTO dto )
	{
		ArtesaniaEntity entity = dto.toEntity( );
		entity.setId( id );
		return new ArtesaniaDTO( logic.updateArtesania( entity ) );
	}
	
	@DELETE
	@Path( "{id: \\d+}" )
	public void deleteArtesania( @PathParam( "id" ) Long id )
	{
		logic.deleteArtesania( id );
	}
	
	private List<ArtesaniaDTO> listEntity2DTO( List<ArtesaniaEntity> entityList )
	{
		List<ArtesaniaDTO> list = new ArrayList<>( );
		for( ArtesaniaEntity entity : entityList )
		{
			list.add( new ArtesaniaDTO( entity ) );
		}
		return list;
	}
}