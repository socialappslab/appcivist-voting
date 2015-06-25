package models;

import net.sf.ehcache.config.PersistenceConfiguration;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;

/**
 * Created by Ryan on 6/4/2015.
 */

@Entity
//@Table
public class Proposal extends Model {

    @Id
    @GeneratedValue
    private Integer propId;

    private String propName;

    private String propBody;

    private User propAuthor;

    private Integer propVotes;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Election election;



    /************************************************************************************************
     * Getters & Setters
     ************************************************************************************************/


    public User getPropAuthor() { return propAuthor; }
    public void setPropAuthor(User propAuthor) { this.propAuthor = propAuthor; }

    public Integer getPropId() {
        return propId;
    }
    public void setPropID(Integer PropID) {
        this.propId = PropID;
    }

    public String getPropName() {
        return propName;
    }
    public void setPropId(String NewPropName) {
        this.propName = NewPropName;
    }

    public String getPropBody() {
        return propBody;
    }
    public void setPropBody(String NewPropBody) {
        this.propBody = NewPropBody;
    }

    public Election getElection() {
        return election;
    }
    public void setElection(Election NewElection) {
        this.election = NewElection;
    }

    public Integer getPropVotes() {
        return propVotes;
    }

    public void setPropVotes(Integer propVotes) {
        this.propVotes = propVotes;
    }

    /************************************************************************************************
     * Methods
     ************************************************************************************************/
}
