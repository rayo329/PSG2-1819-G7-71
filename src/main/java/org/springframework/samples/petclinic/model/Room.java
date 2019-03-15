/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

/**
 * Simple JavaBean domain object representing a visit.
 *
 * @author Ken Krebs
 */
@Entity
@Table(name = "rooms")
public class Room extends BaseEntity {

    /**
     * Holds value of property date.
     */
    @Column(name = "start_date")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate start;

    /**
     * Holds value of property date.
     */
    @Column(name = "end_date")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate end;

    /**
     * Holds value of property pet.
     */
    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;


    /**
     * Creates a new instance of Room for the current date
     */
    public Room() {
        this.start = LocalDate.now();
        this.end = LocalDate.now().plusDays(1);
    }


    /**
     * Getter for property start.
     *
     * @return Value of property date.
     */
    public LocalDate getStart() {
        return this.start;
    }

    /**
     * Setter for property date.
     *
     * @param date New value of property start.
     */
    public void setStart(LocalDate start) {
        this.start = start;
    }

    /**
     * Getter for property end.
     *
     * @return Value of property date.
     */
    public LocalDate getEnd() {
        return this.end;
    }

    /**
     * Setter for property end.
     *
     * @param date New value of property start.
     */
    public void setEnd(LocalDate end) {
        this.end = end;
    }

    /**
     * Getter for property pet.
     *
     * @return Value of property pet.
     */
    public Pet getPet() {
        return this.pet;
    }

    /**
     * Setter for property pet.
     *
     * @param pet New value of property pet.
     */
    public void setPet(Pet pet) {
        this.pet = pet;
    }

}
