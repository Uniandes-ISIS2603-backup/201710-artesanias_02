/*
 * The MIT License
 *
 * Copyright 2017 Miller.
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
package co.edu.uniandes.csw.artesanias.entities;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.*;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * @author ja.espinoza
 */
@Entity
@Table(name= "pabellon")
public class PabellonEntity implements Serializable
{
        /**
        * Id único de cada pabellón
        */
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long id;
	
        /**
         * Tipo del pabellón
         */
        @Column(nullable = false)
	private String tipo;
	
        /**
         * capacidad del pabellón
         */
        @Column(nullable = false)
	private Integer capacidad;
        
        private String imagen;
	
        @PodamExclude
        @ManyToOne(targetEntity = EspacioEntity.class, fetch = FetchType.LAZY)
        @JoinColumn(name = "id_espacio")
        private EspacioEntity espacio;
        
        /**
         * Lista de stands en el pabellon
         */
        @PodamExclude
	@OneToMany( cascade = CascadeType.ALL, mappedBy = "pabellon", targetEntity = StandEntity.class, fetch = FetchType.LAZY )
	private List<StandEntity> stands = new LinkedList<>( );
	
        /**
         * lista de salones en el pabellon
         */
        @PodamExclude
	@OneToMany( cascade = CascadeType.ALL, mappedBy = "pabellon", targetEntity = SalonEntity.class, fetch = FetchType.LAZY )
	private List<SalonEntity> salones = new LinkedList<>( );
        
        public String getImagen()
        {
            return imagen;
        }
	
        public void setImagen( String pImage)
        {
            this.imagen = pImage;
        }
        
	/**
	 * Retrieves the salones of the PabellonEntity
	 *
	 * @return The salones of the PabellonEntity
	 */
	public List<SalonEntity> getSalones( )
	{
		return salones;
	}
	
	/**
	 * Updates the salones of the PabellonEntity by the one given by parameter
	 *
	 * @param salones The new salones of the PabellonEntity
	 */
	public void setSalones( List<SalonEntity> salones )
	{
		this.salones = salones;
	}
	
        public EspacioEntity getEspacio()
        {
            return espacio;
        }
        
        public void setEspacio( EspacioEntity espacio )
        {
            this.espacio = espacio;
        }
        
	/**
	 * Retrieves the stands of the PabellonEntity
	 *
	 * @return The stands of the PabellonEntity
	 */
	public List<StandEntity> getStands( )
	{
		return stands;
	}
	
	/**
	 * Updates the stands of the PabellonEntity by the one given by parameter
	 *
	 * @param stands The new stands of the PabellonEntity
	 */
	public void setStands( List<StandEntity> stands )
	{
		this.stands = stands;
	}
	
	/**
	 * @return the id
	 */
	public Long getId( )
	{
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId( Long id )
	{
		this.id = id;
	}
	
	/**
	 * @return the tipo
	 */
	public String getTipo( )
	{
		return tipo;
	}
	
	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo( String tipo )
	{
		this.tipo = tipo;
	}
	
	/**
	 * @return the capacidad
	 */
	public Integer getCapacidad( )
	{
		return capacidad;
	}
	
	/**
	 * @param capacidad the capacidad to set
	 */
	public void setCapacidad( Integer capacidad )
	{
		this.capacidad = capacidad;
	}
	
	@Override
	public boolean equals( Object obj )
	{
		if( this.getId( ) != null )
		{
			return this.getId( ).equals( ( ( PabellonEntity ) obj ).getId( ) );
		}
		return super.equals( obj );
	}
	
	public int hashCode( )
	{
		if( this.getId( ) != null )
		{
			return this.getId( ).hashCode( );
		}
		return super.hashCode( );
	}
}