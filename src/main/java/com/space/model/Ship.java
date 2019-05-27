package com.space.model;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@Entity
public class Ship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String planet;
    @Enumerated(EnumType.STRING)
    private ShipType shipType;
    private Date prodDate;
    private Boolean isUsed;
    private Double speed;
    private Integer crewSize;
    private Double rating;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPlanet() {
        return planet;
    }

    public ShipType getShipType() {
        return shipType;
    }

    public Date getProdDate() {
        return prodDate;
    }

    public Boolean getUsed() {
        return isUsed;
    }

    public Double getSpeed() {
        return speed;
    }

    public Integer getCrewSize() {
        return crewSize;
    }

    public Double getRating() {
        return rating;

    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlanet(String planet) {
        this.planet = planet;
    }

    public void setShipType(ShipType shipType) {
        this.shipType = shipType;
    }

    public void setProdDate(Date prodDate) {
        this.prodDate = prodDate;
    }

    public void setUsed(Boolean isUsed) {
        this.isUsed = isUsed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public void setCrewSize(Integer crewSize) {
        this.crewSize = crewSize;
    }

    public void setRating(Double rating) {
        this.rating = calculateRating();
    }

    public Double calculateRating() {
        double k;
        if (isUsed){
            k = 0.5;
        }
        else {
            k = 1;
        }
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        cal.setTime(prodDate);
        int year = cal.get(Calendar.YEAR);

        return  (double) Math.round(80 * speed * k / (3019 - year + 1) *100)/100;
    }

    //устанавливает параметры
    public ResponseEntity initParams(String name, String planet, ShipType shipType,
                                     Long prodDate, Boolean isUsed, Double speed, Integer crewSize) {

        if (name != null) {
            if (name.isEmpty() || name.length() > 50) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                setName(name);
            }
        }

        //если имя планеты изменено и не превышает 50 символов
        if (planet != null) {
            if (planet.isEmpty() || planet.length() > 50) {
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            } else {
                setPlanet(planet);
            }
        }

        //если тип корабля изменен
        if (shipType != null) {
            setShipType(shipType);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        //если дата изменена и находится в промежутке от 2800 года до 3019
        if (prodDate != null) {
            if (new Date(prodDate).getYear() < 2800 || new Date(prodDate).getYear() > 3019) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                setProdDate(new Date(prodDate));
            }
        }

        //если скорость изменена и находится в промежутке от 0,1 до 0,99
        if (speed != null) {
            if (speed.toString().isEmpty() || speed < 0.01d || speed > 0.99d) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                setSpeed(speed);
            }
        }

        //если пассажироемкость изменена и находится в промежутке от 1 до 9999
        if (crewSize != null) {
            if (crewSize.toString().isEmpty() || crewSize < 1 || crewSize > 9999) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                setCrewSize(crewSize);
            }
        }

        //если использованность изменена
        if (isUsed != null) {
            if (isUsed.toString().isEmpty()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                setUsed(isUsed);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } return new ResponseEntity(HttpStatus.OK);
    }
}
