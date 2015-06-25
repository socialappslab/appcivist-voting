package models;

import java.util.ArrayList;
import play.db.ebean.Model;
import javax.persistence.*;

import play.mvc.Result;
import play.data.Form;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
//@Table(name="ELECTION")
public class Election extends Model {

    @Id
    @GeneratedValue
    private Integer electionId;

    private String electionName;

    private String description;

    private String location;

    private String electionType;

    private String electionURL;

    private Date startDate;          // date methods will allow for start/end times of elections

    @OneToMany(mappedBy="election", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Proposal> proposals = new ArrayList<Proposal>();

    private List<User> users = new ArrayList<User>();


    public static Model.Finder<Integer, Election> find = new Model.Finder<Integer, Election>(
        Integer.class, Election.class);

    public static Election read(Integer electionId) {
        return find.ref(electionId);
    }

    public static Election createObject(Election election) {
        election.save();
        return election;
    }

    public static void delete(Integer id) {
        find.ref(id).delete();
    }

    public static void update(Integer id) {
        find.ref(id).update();
    }

    public static Integer findNextID() {
        return find.nextId();
    }

    public static void create(Election election) {
        election.save();
        election.refresh();
    }

    /************************************************************************************************
     * Getters & Setters
     ************************************************************************************************/

    public Integer getId() {
        return electionId;
    }

    public void setId(Integer id) { electionId = id; }



    public String getElectionName() {
        return electionName;
    }

    public void setElectionName(String electionName) {
        this.electionName = electionName;
    }


    public String getElectionURL() {
        return electionURL;
    }

    public void setElectionURL(String electionURL) {
        this.electionURL = electionURL;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }




    public String getAddress() {
        return location;
    }

    public void setAddress(String newLocation) {
        this.location = newLocation;
    }




    public String getElectionType() { return electionType; }

    public void setElectionType(String electionType)
    { if (electionType != "Plurality") {
        throw new IllegalArgumentException("Bad election type");
        }
        else { this.electionType = electionType; }}




    public List<Proposal> getProposals() {return proposals;}

    public void overrideProposals(List<Proposal> proposals) {this.proposals = proposals;}

    public void addProposal(Proposal newProp) { this.proposals.add(newProp);}




    public List<User> getUsers() { return users; }

    public void overrideUsers(List<User> users) { this.users = users; }

    public void addUser(User newUser) {
        newUser.addElection(this);
        this.users.add(newUser);}




    /************************************************************************************************
     * Methods
     ************************************************************************************************/



    public Proposal getWinningProposal(List<Proposal> proplist) {
        Integer highestVote = proplist.get(0).getPropVotes();
        Integer highestIndex = 0;
        for (Integer i = 1; i < proplist.size(); i++) {
            if (proplist.get(i).getPropVotes() > highestVote)
                highestIndex = i;
        }
        return proplist.get(highestIndex);
    }


}
