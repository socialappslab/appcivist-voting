package models;

import play.db.ebean.Model;

import javax.persistence.*;

import play.mvc.Result;
import play.data.Form;


import java.util.ArrayList;
import java.util.List;

@Entity
public class Election extends Model {

    @Id
    private String electionId;
    private String electionName;



    private String description;
    private String city;
    private String address;



    /************************************************************************************************
     * Getters & Setters
     ************************************************************************************************/

    public String getId() {
        return electionId;
    }
    public void setId(String id) {
        electionId = id;
    }

    public String getElectionName() {
        return electionName;
    }
    public void setElectionName(String electionName) {
        this.electionName = electionName;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    @OneToMany(targetEntity = Proposal.class, mappedBy="election", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Proposal> proposal = new ArrayList<Proposal>();

}
