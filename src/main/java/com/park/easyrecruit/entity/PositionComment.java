/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.park.easyrecruit.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Silvan
 */
@Entity
@Table(name = "PositionComment")
public class PositionComment implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    private Position position;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String creatorUser;
    private String text;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getCreatorUser() {
        return creatorUser;
    }

    public void setCreatorUser(String creatorUser) {
        this.creatorUser = creatorUser;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
  
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof PositionComment )) return false;
        return id != null && id.equals(((PositionComment) object).getId());
    }

    @Override
    public String toString() {
        return "PositionComment{" + "position=" + position + ", id=" + id + ", creatorUser=" + creatorUser + ", text=" + text + '}';
    }


    
}
