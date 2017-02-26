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

import co.edu.uniandes.csw.artesanias.entities.CiudadEntity;
import co.edu.uniandes.csw.artesanias.persistence.CiudadPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Logica del recurso ciudad.
 * @author Miller
 */
@Stateless
public class CiudadLogic {
    
    @Inject private CiudadPersistence persistence;
    
    public CiudadEntity getCiudad(Long id) {
        return persistence.find(id);
    }
    
    public List<CiudadEntity> getCiudades() {
        return persistence.findAll();
    }
    
    public CiudadEntity createCiudad(CiudadEntity entity) {
        return persistence.create(entity);
    }
    
    public CiudadEntity updateCiudad(CiudadEntity entity) {
        return persistence.update(entity);
    }
    
    public void deleteCiudad(Long id) {
        persistence.delete(id);
    }
}
