package com.space.repository;

import com.space.model.Ship;
import com.space.model.ShipType;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ShipSpecifications {

    //по имени
    public static Specification<Ship> getShipByNameSpec(String name){
        return new Specification<Ship>() {
            @Override
            public Predicate toPredicate(Root<Ship> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {
                Predicate equalPredicate = criteriaBuilder.like(root.<String> get("name"), '%'+name+'%');
                return equalPredicate;
            }
        };
    }
    //по планете
    public static Specification<Ship> getShipByPlanetSpec(String planet){
        return new Specification<Ship>() {
            @Override
            public Predicate toPredicate(Root<Ship> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {
                Predicate equalPredicate = criteriaBuilder.like(root.<String> get("planet"), '%'+planet+'%');
                return equalPredicate;
            }
        };
    }
    //по пассожироемкости
    public static Specification<Ship> getShipByCrewSpec(Integer minCrewSize, Integer maxCrewSize){
        return new Specification<Ship>() {
            @Override
            public Predicate toPredicate(Root<Ship> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {


                Predicate equalPredicate = criteriaBuilder.between(root.<Integer> get("crewSize"), minCrewSize, maxCrewSize);
                return equalPredicate;
            }
        };
    }
    //по скорости
    public static Specification<Ship> getShipBySpeedSpec(Double minSpeed, Double maxSpeed){
        return new Specification<Ship>() {
            @Override
            public Predicate toPredicate(Root<Ship> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {


                Predicate equalPredicate = criteriaBuilder.between(root.<Double> get("speed"), minSpeed, maxSpeed);
                return equalPredicate;
            }
        };
    }
    //по рейтингу
    public static Specification<Ship> getShipByRatingSpec(Double minRating, Double maxRating){
        return new Specification<Ship>() {
            @Override
            public Predicate toPredicate(Root<Ship> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {


                Predicate equalPredicate = criteriaBuilder.between(root.<Double>get("rating"), minRating, maxRating);
                return equalPredicate;
            }
        };
    }
    //по типу корабля
    public static Specification<Ship> getShipByTypeSpec(ShipType shipType){
        return new Specification<Ship>() {
            @Override
            public Predicate toPredicate(Root<Ship> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {

                Predicate equalPredicate = criteriaBuilder.equal(root.<ShipType>get("shipType"), shipType);
                return equalPredicate;
            }
        };
    }
    //по использованности
    public static Specification<Ship> getShipByUsedSpec(Boolean isUsed){
        return new Specification<Ship>() {
            @Override
            public Predicate toPredicate(Root<Ship> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {
                Predicate equalPredicate = criteriaBuilder.equal(root.<Boolean>get("isUsed"), isUsed);
                return equalPredicate;
            }
        };
    }
    //по дате
    public static Specification<Ship> getShipByYearSpec(Long after, Long before)  {
        return new Specification<Ship>() {
            @Override
            public Predicate toPredicate(Root<Ship> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {

                String pattern = "yyyy-MM-dd";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                Date resultAfter = new Date(after);
                Date resultBefore = new Date(before);



                Predicate equalPredicate = criteriaBuilder.between(root.<Date> get("prodDate"), resultAfter, resultBefore);
                return equalPredicate;
            }
        };
    }
}