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
package co.edu.uniandes.csw.artesanias.dtos;

import co.edu.uniandes.csw.artesanias.entities.FeriaEntity;
import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Miller
 */
@XmlRootElement
public class FeriaDTO implements Serializable {
    
    //--------------------------------------------------------------------------
    // Atributos
    //--------------------------------------------------------------------------

    /**
     * Id de la feria.
     */
    private Long id;

    /**
     * Nombre de la feria.
     */
    private String nombre;

    /**
     * Fecha de inicio de la feria.
     */
    private Date inicio;

    /**
     * Fecha de fin de la feria.
     */
    private Date fin;
    
    
    //--------------------------------------------------------------------------
    // Constructor
    //--------------------------------------------------------------------------
    
    public FeriaDTO() {}
    
    /**
     * Crea un objeto FeriaDTO a partir de un objeto FeriaEntity.
     * @param entity entidad feria desde la cual se va a crear el nuevo objeto.
     */
    public FeriaDTO(FeriaEntity entity) {
        if (entity == null) return;
        this.id = entity.getId();
        this.nombre = entity.getNombre();
        this.inicio = entity.getInicio();
        this.fin = entity.getFin();
    }
    
    //--------------------------------------------------------------------------
    // Métodos Getter y Setters
    //--------------------------------------------------------------------------

    /**
     * Devuelve el id de la feria.
     * @return id de la feria.
     */
    public Long getId() {
        return id;
    }

    /**
     * Cambia el id de la feria.
     * post: Se cambió el id de la feria.
     * @param id nuevo id de la feria.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Devuelve el nombre de la feria.
     * @return nombre de la feria.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Cambia el nombre de la feria.
     * @param nombre nuevo nombre de la feria.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve la fecha de inicio de la feria.
     * @return fecha de inicio de la feria.
     */
    public Date getInicio() {
        return inicio;
    }

    /**
     * Cambia la fecha de inicio de la feria.
     * post: Se cambió la fecha de inicio de la feria.
     * @param inicio nueva fecha de inicio de la feria.
     */
    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    /**
     * Devuelve la fecha en que termina la feria.
     * @return fecha en que termina la feria.
     */
    public Date getFin() {
        return fin;
    }

    /**
     * Cambia la fecha en que termina la feria.
     * post: Se cambió la fecha en que termina la feria.
     * @param fin nueva fecha en que termina la feria.
     */
    public void setFin(Date fin) {
        this.fin = fin;
    }
    
    //--------------------------------------------------------------------------
    // Métodos
    //--------------------------------------------------------------------------
    
    /**
     * Convierte un objeto FeriaDTO a FeriaEntity.
     * @return Nuevo objeto FeriaEntity.
     */
    public FeriaEntity toEntity() {
        FeriaEntity entity = new FeriaEntity();
        entity.setId(this.id);
        entity.setNombre(this.nombre);
        entity.setInicio(this.inicio);
        entity.setFin(this.fin);
        return entity;
    }
}
