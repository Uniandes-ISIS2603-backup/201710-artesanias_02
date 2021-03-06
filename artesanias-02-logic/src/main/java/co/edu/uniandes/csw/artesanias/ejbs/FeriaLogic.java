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
package co.edu.uniandes.csw.artesanias.ejbs;

import co.edu.uniandes.csw.artesanias.entities.BoletaEntity;
import co.edu.uniandes.csw.artesanias.entities.ConferenciaEntity;
import co.edu.uniandes.csw.artesanias.entities.FeriaEntity;
import co.edu.uniandes.csw.artesanias.entities.OrganizadorEntity;
import co.edu.uniandes.csw.artesanias.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.artesanias.persistence.FeriaPersistence;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

/**
 *
 * @author Miller
 */
@Stateless
public class FeriaLogic {
    
    //--------------------------------------------------------------------------
    // Atributos
    //--------------------------------------------------------------------------
    
    /**
     * Persistencia de FeriaEntity
     */
    @Inject 
    private FeriaPersistence persistence;
    
    @Inject 
    OrganizadorLogic organizadorLogic;
    
    @Inject
    private BoletaLogic boletaLogic;
    
    @Inject
    private ConferenciaLogic conferenciaLogic;
    
    //--------------------------------------------------------------------------
    // Métodos
    //--------------------------------------------------------------------------
    
    /**
     * Se devuelve la entidad feria con el id dado.
     * @param id de la feria a buscar.
     * @return entidad feria con el id dado.
     */
    public FeriaEntity getFeria(Long id) {
        FeriaEntity r = persistence.find(id);
        return r;
    }
    
    /**
     * Devuelve el conjunto de ferias.
     * @return lista de ferias.
     */
    public List<FeriaEntity> getFerias() {
        return persistence.findAll();
    }
    
    /**
     * Crea una nueva entidad feria.
     * @param entity Entidad feria a ser ingresada en la base de datos
     * @return la nueva entidad feria.
     * @throws BusinessLogicException Si no cumple con alguna regla de negocio.
     */
    public FeriaEntity createFeria(FeriaEntity entity) throws BusinessLogicException {
        checkData(entity);
        checkData(entity);
        return persistence.create(entity);
    }
    
    /**
     * Actualiza los valores de la feria con el id dado.
     * @param entity entidad con la información para actualizar la feria.
     * @return la entidad de la feria actualizada.
     * @throws BusinessLogicException si los datos que se desean actualizar no son válidos.
     */
    public FeriaEntity updateFeria(FeriaEntity entity) throws BusinessLogicException {
        FeriaEntity e = persistence.find(entity.getId());
        if (entity.getNombre() == null || e.getNombre().isEmpty())
            entity.setNombre(e.getNombre());
        if (entity.getTotalBoletas() == null)
            entity.setTotalBoletas(e.getTotalBoletas());
        if (entity.getDescuentoMenores() == null)
            entity.setDescuentoMenores(e.getDescuentoMenores());
        if (entity.getDescuentoRegular() == null)
            entity.setDescuentoRegular(e.getDescuentoRegular());
        if (entity.getDescuentoMayores() == null)
            entity.setDescuentoMayores(e.getDescuentoMayores());
        if (entity.getEspacio() == null)
            entity.setEspacio(e.getEspacio());
        if (entity.getInicio() == null)
            entity.setInicio(e.getInicio());
        if (entity.getFin() == null)
            entity.setFin(e.getFin());
        if (entity.getAsociacion() == null)
            entity.setAsociacion(e.getAsociacion());
        if (entity.getBoletas() == null || entity.getBoletas().isEmpty())
            entity.setBoletas(e.getBoletas());
        if (entity.getOrganizadores() == null || entity.getOrganizadores().isEmpty())
            entity.setOrganizadores(e.getOrganizadores());
        if (entity.getConferencias() == null || entity.getConferencias().isEmpty())
            entity.setConferencias(e.getConferencias());
        checkData(entity);
        return persistence.update(entity);
    }
    
    /**
     * Elimina la feria con el id dado.
     * @param id de la feria.
     */
    public void deleteFeria(Long id) {
        persistence.delete(id);
    }
    
    //--------------------------------------------------------------------------
    // Métodos de organizador
    //--------------------------------------------------------------------------
    
    public OrganizadorEntity getOrganizador(Long idFeria, Long idOrganizador) {
        for (OrganizadorEntity o : persistence.find(idFeria).getOrganizadores()) {
            if (o.getId().equals(idOrganizador))
                return o;
        }
        throw new IllegalArgumentException("El organizador no está asociado a la feria");
    }
    
    public List<OrganizadorEntity> getOrganizadores(Long idFeria) {
        return persistence.find(idFeria).getOrganizadores();
    }
    
    public void removeOrganizador(Long idFeria, Long idOrganizador) {
        organizadorLogic.removeFeria(idFeria, idOrganizador);
    }
    
    public OrganizadorEntity addOrganizador(Long idFeria, Long idOrganizador) {
        organizadorLogic.addFeria(idFeria, idOrganizador);
        return organizadorLogic.getOrganizador(idOrganizador);
    }
    
    //--------------------------------------------------------------------------
    // Métodos de boleta
    //--------------------------------------------------------------------------
    
    public BoletaEntity getBoleta(Long idFeria, Long idBoleta) {
        for (BoletaEntity b : persistence.find(idFeria).getBoletas()) {
            if (b.getId().equals(idBoleta))
                return b;
        }
        throw new IllegalArgumentException("La boleta no está asociado a la feria");
    }
    
    public List<BoletaEntity> getBoletas(Long id) {
        return persistence.find(id).getBoletas();
    }
    
    public void removeBoleta(Long idFeria, Long idBoleta) {
        BoletaEntity be = boletaLogic.getBoleta(idBoleta);
        be.setFeria(null);
        persistence.find(idFeria).getBoletas().remove(be);
    }
    
    public BoletaEntity addBoleta(Long idFeria, Long idBoleta) {
        BoletaEntity be = boletaLogic.getBoleta(idBoleta);
        be.setFeria(persistence.find(idFeria));
        return be;
    }
    
    //--------------------------------------------------------------------------
    // Métodos de conferencia
    //--------------------------------------------------------------------------
    
    public ConferenciaEntity getConferencia(Long idFeria, Long idConferencia) {
        for (ConferenciaEntity b : persistence.find(idFeria).getConferencias()) {
            if (b.getId().equals(idConferencia))
                return b;
        }
        throw new IllegalArgumentException("La conferencia no está asociado a la feria");
    }
    
    public List<ConferenciaEntity> getConferencias(Long id) {
        return persistence.find(id).getConferencias();
    }
    
    public void removeConferencia(Long idFeria, Long idConferencia) {
        ConferenciaEntity be = conferenciaLogic.getConferencia(idConferencia);
        be.setFeria(null);
        persistence.find(idFeria).getConferencias().remove(be);
    }
    
    public ConferenciaEntity addConferencia(Long idFeria, Long idConferencia) {
        ConferenciaEntity be = conferenciaLogic.getConferencia(idConferencia);
        be.setFeria(persistence.find(idFeria));
        return be;
    }
    
    //--------------------------------------------------------------------------
    // Métodos Auxiliares
    //--------------------------------------------------------------------------
    
    /**
     * Revisa las reglas de negocio.
     * @param e entidad a ser revisada.
     * @throws BusinessLogicException si no se cumple alguna regla de negocio.
     */
    private void checkData(FeriaEntity e) throws BusinessLogicException {
        if (e.getNombre() == null || e.getNombre().isEmpty())
            throw new BusinessLogicException("La feria debe tener un nombre", Response.Status.NOT_FOUND);
        if (e.getTotalBoletas() == null)
            throw new BusinessLogicException("La feria debe tener un número de boletas", Response.Status.NOT_FOUND);
        if (e.getTotalBoletas() <= 0)
            throw new BusinessLogicException("La feria debe tener un número estrictamente mayor a 0 de boletas", Response.Status.NOT_FOUND);
        if (e.getDescuentoMenores() == null || e.getDescuentoRegular() == null || e.getDescuentoMayores() == null)
            throw new BusinessLogicException("La feria debe tener los descuentos estableciodos", Response.Status.NOT_FOUND);
        if (e.getEspacio() == null)
            throw new BusinessLogicException("La feria debe tener un espacio donde se realiza", Response.Status.NOT_FOUND);
        if (e.getInicio() == null)
            throw new BusinessLogicException("La feria debe tener una fecha de inicio", Response.Status.NOT_FOUND);
        if (e.getInicio().compareTo(new Date()) < 0)
            throw new BusinessLogicException("La feria debe tener una fecha mayor a la actual", Response.Status.NOT_FOUND);
        if (e.getFin() == null)
            throw new BusinessLogicException("La feria debe tener una fecha de fin", Response.Status.NOT_FOUND);
        if (e.getFin().compareTo(e.getInicio()) <= 0)
            throw new BusinessLogicException("La fecha de fin de la feria debe ser estrictamente mayor a la de inicio", Response.Status.NOT_FOUND);
    }
    
}
