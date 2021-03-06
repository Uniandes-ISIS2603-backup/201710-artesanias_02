/*
 * The MIT License
 *
 * Copyright 2017 d.narvaez11.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package co.edu.uniandes.csw.artesanias.ejbs;

import co.edu.uniandes.csw.artesanias.entities.ReviewEntity;
import co.edu.uniandes.csw.artesanias.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.artesanias.persistence.ReviewPersistence;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author d.narvaez11
 */
@Stateless
public class ReviewLogic
{
	@Inject
	private ReviewPersistence persistence;
	
	/**
	 * Creates an Review in the Data Base
	 *
	 * @param entity Objet from ReviewEntity with the new data.
	 * @return Objet from ReviewEntity with the new data and ID.
	 */
	public ReviewEntity createReview( ReviewEntity entity ) throws BusinessLogicException
	{
		check( entity );
		return persistence.create( entity );
	}
	
	/**
	 * Retrieves the list of the registers of Review.
	 *
	 * @return Collection of objects of ReviewEntity.
	 */
	public List<ReviewEntity> getReviews( )
	{
		return persistence.findAll( );
	}
	
	public List<ReviewEntity> getReviewsFromArtesano( Long id )
	{
		return persistence.findAllFromArtesano( id );
	}
	
	/**
	 * Retrieves the data of an instance of Review by its ID.
	 *
	 * @param id Identifier of the instance to consult.
	 * @return Instance of ReviewEntity with the data from the Review consulted.
	 */
	public ReviewEntity getReview( Long artesanoId, Long id ) throws BusinessLogicException
	{
		ReviewEntity res = persistence.find( artesanoId, id );
		if( res != null )
		{
			return res;
		}
		throw new BusinessLogicException( String.format( "El review %s no pertenece al artesano %s ", id, artesanoId ), Response.Status.NOT_FOUND );
	}
	
	/**
	 * Updates the information from an instance of Review.
	 *
	 * @param entity Instance of ReviewEntity with the new data.
	 * @return Instance of ReviewEntity with the updated data.
	 */
	public ReviewEntity updateReview( ReviewEntity entity ) throws BusinessLogicException
	{
		check( entity );
		return persistence.update( entity );
	}
	
	/**
	 * Deletes an instance of Review from the Data Base.
	 *
	 * @param id Identifier of the instance to remove.
	 */
	public void deleteReview( Long artesanoId, Long id ) throws BusinessLogicException
	{
		try
		{
			getReview( artesanoId, id ); // Verificar Exception
			persistence.delete( id );
		}
		catch( BusinessLogicException e )
		{
			throw new BusinessLogicException( String.format( "El review %s no pertenece al artesano %s", id, artesanoId ), Response.Status.FORBIDDEN );
		}
	}
	
	private void check( ReviewEntity entity ) throws BusinessLogicException
	{
		if( entity.getPuntuacion( ) < 0 || entity.getPuntuacion( ) > 5 )
		{
			throw new BusinessLogicException( "La puntuación debe estar en un rango de 0 a 5", Response.Status.BAD_REQUEST );
		}
	}
}