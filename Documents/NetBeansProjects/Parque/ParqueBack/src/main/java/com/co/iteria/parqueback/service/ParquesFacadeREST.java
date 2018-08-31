/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.co.iteria.parqueback.service;

import com.co.iteria.parque.back.entity.Parques;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Usuario
 */
@Stateless
@Path("parques")
public class ParquesFacadeREST extends AbstractFacade<Parques> {

    Parques pq = new Parques();
    
    @PersistenceContext(unitName = "com.co.iteria_ParqueBack_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public ParquesFacadeREST() {
        super(Parques.class);
    }

    @POST
    @Override
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Parques create(Parques entity) {
        
       
        super.create(entity);
        //response.setStatus();  
       
        return entity;
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") String id, Parques entity) {
        super.edit(entity);
        
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public JsonObject find(@PathParam("id") String id) {
        Parques pq = super.find(id);
        
        JsonObject json= Json.createObjectBuilder()
                .add("id", pq.getId())
                .add("name", pq.getName())
                .add("state", pq.getState())
                .add("capacity", pq.getCapacity())
                .add("status", pq.getStatus())
                .build();
        return json;
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
     public JsonArray obtenerParques() {
     
        List<Parques> listaparques = super.findAll();
        JsonArrayBuilder pqArray = Json.createArrayBuilder();
        for (Parques parque : listaparques) {
            JsonObjectBuilder json = Json.createObjectBuilder()
                    .add("id", parque.getId())
                    .add("name", parque.getName())
                    .add("state", parque.getState())
                    .add("capacity", parque.getCapacity())
                    .add("status", parque.getStatus());
            pqArray.add(json);
        }
 
        return pqArray.build();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Parques> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
