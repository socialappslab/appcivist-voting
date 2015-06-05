package models;

import play.db.ebean.Model;

import javax.persistence.*;

/**
 * Created by Ryan on 6/4/2015.
 */

@Entity
public class Proposal extends Model {

    private Integer propId;
    private String propName;
    private String propBody;
    private String propAuthor;
    private Election election;

    @Id
    public int GetPropID() {
        return propId;
    }
    public void SetPropID(int PropID) {
        this.propId = PropID;
    }

    public String GetPropName() {
        return propName;
    }
    public void SetPropName(String NewPropName) {
        this.propName = NewPropName;
    }

    public String GetPropBody() {
        return propBody;
    }
    public void SetPropBody(String NewPropBody) {
        this.propBody = NewPropBody;
    }

    @ManyToOne
    @JoinColumn(name = "election_id")
    public Election GetElection() {
        return election;
    }
    public void SetElection(Election NewElection) {
        this.election = NewElection;
    }
}
