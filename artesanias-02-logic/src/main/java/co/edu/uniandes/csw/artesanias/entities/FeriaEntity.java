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

import co.edu.uniandes.csw.artesanias.entities.asociaciones.ArtesanoFeriaAssociation;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * Entidad de las ferias.
 * @author ma.trujillo10
 */
@Entity
public class FeriaEntity implements Serializable {
    
    /**
     * Id de la feria.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre de la feria.
     */
    @Column(nullable = false)
    private String nombre;
    
    /**
     * Descuentos que ofrece la feria.
     * descuentos[0] = descuento para menores
     * descuentos[1] = descuento regular
     * descuentos[2] = descuento para mayores
     * descuentos[x] = 1-descuento; [0.0, 1.0]
     */
    @Column(nullable = false)
    private Double descuentoMenores;

    @Column(nullable = false)
    private Double descuentoRegular;
    
    @Column(nullable = false)
    private Double descuentoMayores;
    
    /**
     * El total de boletas que ofrece la feria
     */
    @Column(nullable = false)
    private Integer totalBoletas;

    /**
     * Fecha de inicio de la feria.
     */
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(nullable = false)
    private Date inicio;

    /**
     * Fecha de fin de la feria.
     */
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(nullable = false)
    private Date fin;

    private String image;
    
    /**
     * Espacio donde se da la feria.
     */
    @PodamExclude
    @ManyToOne(targetEntity = EspacioEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "espacio_id")
    private EspacioEntity espacio;

    /**
     * Conjunto de artesanos que asistirán a la feria.
     */
    @OneToMany(cascade = CascadeType.ALL, targetEntity = ArtesanoFeriaAssociation.class, fetch = FetchType.LAZY, mappedBy = "feria")
    @PodamExclude
    private List<ArtesanoFeriaAssociation> asociaciones;
    
    /**
     * Conjunto de organizadores de la feria.
     */
    @ManyToMany(targetEntity = OrganizadorEntity.class, fetch = FetchType.LAZY)
    @PodamExclude
    private List<OrganizadorEntity> organizadores = new LinkedList<OrganizadorEntity>();

    /**
     * Conjunto de boletas vendidas de la feria.
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "feria",
            targetEntity = BoletaEntity.class, fetch = FetchType.LAZY)
    @PodamExclude
    private List<BoletaEntity> boletas;

    /**
     * Conjunto de conferencias que se van a realizar en la feria.
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "feria",
            targetEntity = ConferenciaEntity.class, fetch = FetchType.LAZY)
    @PodamExclude
    private List<ConferenciaEntity> conferencias;

    //--------------------------------------------------------------------------
    // Métodos
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

    public Double getDescuentoMenores() {
        return descuentoMenores;
    }

    public void setDescuentoMenores(Double descuentoMenores) {
        this.descuentoMenores = descuentoMenores;
    }

    public Double getDescuentoRegular() {
        return descuentoRegular;
    }

    public void setDescuentoRegular(Double descuentoRegular) {
        this.descuentoRegular = descuentoRegular;
    }

    public Double getDescuentoMayores() {
        return descuentoMayores;
    }

    public void setDescuentoMayores(Double descuentoMayores) {
        this.descuentoMayores = descuentoMayores;
    }

    /**
     * Devuelve el total de boletas que ofrece la feria.
     * @return total de boletas que ofrece la feria.
     */
    public Integer getTotalBoletas() {
        return totalBoletas;
    }

    /**
     * Cambia el total de boletas que ofrece la feria.
     * post: Se cambió el total de boletas que ofrece la feria.
     * @param totalBoletas nuevo total de boletas que ofrece la feria.
     */
    public void setTotalBoletas(Integer totalBoletas) {
        this.totalBoletas = totalBoletas;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    /**
     * Devuelve el espacio donde se realiza la feria.
     * @return espacio donde se realiza la feria.
     */
    public EspacioEntity getEspacio() {
        return espacio;
    }

    /**
     * Cambia el espacio donde se realiza la feria.
     * post: Se cambió el espacio donde se realiza la feria.
     * @param espacio nuevo espacio donde se realiza la feria.
     */
    public void setEspacio(EspacioEntity espacio) {
        this.espacio = espacio;
    }

    /**
     * Devuelve el conjunto de artesanos que asistirán o asistieron a la feria.
     * @return conjunto de artesanos que asistirán a la feria.
     */
    public List<ArtesanoFeriaAssociation> getAsociacion() {
        return asociaciones;
    }

    /**
     * Cambia el conjunto de artesanos que asistirán o asistieron a la feria.
     * post: Se cambió el conjunto de artesanos que asistirán o asistieron a la 
     *      feria.
     * @param asociaciones nuevo conjunto de artesanos que asistirán o asistieron 
     *      a la feria.
     */
    public void setAsociacion(List<ArtesanoFeriaAssociation> asociaciones) {
        this.asociaciones = asociaciones;
    }

    /**
     * Devuelve el conjunto de organizadores que organizan la feria.
     * @return conjunto de organizadores que organizan la feria.
     */
    public List<OrganizadorEntity> getOrganizadores() {
        return organizadores;
    }

    /**
     * Cambia el conjunto de organizadores que organizan la feria.
     * post: Se cambió el conjunto de organizadores que organizan la feria.
     * @param organizadores nuevo conjunto de organizadores que organizan la feria.
     */
    public void setOrganizadores(List<OrganizadorEntity> organizadores) {
        this.organizadores = organizadores;
    }
    
    /**
     * Devuelve el conjunto de boletas vendidas de la feria.
     * @return conjunto de boletas vendidas de la feria.
     */
    public List<BoletaEntity> getBoletas() {
        return boletas;
    }

    /**
     * Cambia el conjuto de las boletas vendidas de la feria.
     * post: Se cambió el conjunto de boletas vendidas de la feria.
     * @param boletas nuevo conjunto de boletas vendidas de la feria.
     */
    public void setBoletas(List<BoletaEntity> boletas) {
        this.boletas = boletas;
    }

    /**
     * Devuelve el conjunto de conferencias realizadas o a realizar de la feria.
     * @return conjunto de conferencias realizadas o a realizar de la feria.
     */
    public List<ConferenciaEntity> getConferencias() {
        return conferencias;
    }

    /**
     * Cambia el conjunto de conferencias realizadas o a realizar de la feria.
     * post: Se cambió el conjunto de conferencias realizadas o a realizar de la
     *      feria.
     * @param conferencias nuevo conjunto de conferencias realizadas o a 
     *      realizar de la feria.
     */
    public void setConferencias(List<ConferenciaEntity> conferencias) {
        this.conferencias = conferencias;
    }
    
    //--------------------------------------------------------------------------
    // Métodos del objeto
    //--------------------------------------------------------------------------
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)    return true;
        if (obj == null)    return false;
        if (getClass() != obj.getClass())   return false;
        final FeriaEntity other = (FeriaEntity) obj;
        return Objects.equals(this.id, other.id);
    }
}
