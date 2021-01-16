package com.space.repository;

import com.space.model.Ship;
import com.space.model.ShipType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ShipRepositoryImpl implements ShipRepositoryCustom {
    private EntityManager em;
    private CriteriaBuilder cb;

    public ShipRepositoryImpl(@Autowired EntityManager em) {
        this.em = em;
        this.cb = em.getCriteriaBuilder();
    }

    private CriteriaQuery<Ship> makeQuery(String name, String planet, ShipType shipType, Long after, Long before, Boolean isUsed, Double minSpeed, Double maxSpeed, Integer minCrewSize, Integer maxCrewSize, Double minRating, Double maxRating) {
        CriteriaQuery<Ship> cq = cb.createQuery(Ship.class);
        Root<Ship> ship = cq.from(Ship.class);
        List<Predicate> predicates = new ArrayList<>();

        if (name != null) {
            System.out.println("Name: " + name);
            predicates.add(cb.like(ship.get("name"), "%" + name + "%"));
        }

        if (planet != null) {
            predicates.add(cb.like(ship.get("planet"), "%" + planet + "%"));
        }

        cq.where(predicates.toArray(new Predicate[0]));
        return cq;
    }

    @Override
    public List<Ship> findByParams(String name, String planet, ShipType shipType, Long after, Long before, Boolean isUsed, Double minSpeed, Double maxSpeed, Integer minCrewSize, Integer maxCrewSize, Double minRating, Double maxRating) {
        CriteriaQuery<Ship> cq = makeQuery(name, planet, shipType, after, before, isUsed, minSpeed, maxSpeed, minCrewSize, maxCrewSize, minRating, maxRating);
        return em.createQuery(cq).getResultList();
    }

    @Override
    public long countByParams(String name, String planet, ShipType shipType, Long after, Long before, Boolean isUsed, Double minSpeed, Double maxSpeed, Integer minCrewSize, Integer maxCrewSize, Double minRating, Double maxRating) {
        CriteriaQuery<Ship> cq = makeQuery(name, planet, shipType, after, before, isUsed, minSpeed, maxSpeed, minCrewSize, maxCrewSize, minRating, maxRating);
        return em.createQuery(cq).getResultList().size();
    }
}
