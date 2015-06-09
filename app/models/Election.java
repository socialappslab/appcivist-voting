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
@Table(name="ELECTION")
public class Election extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int electionId;

    private String electionName;

    private String description;

    private String location;

    private String electionType;

    private Date startDate;          // date methods will allow for start/end times of elections

    @OneToMany(targetEntity = Proposal.class, mappedBy="election", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Proposal> proposals = new ArrayList<Proposal>();

    private List<User> users = new ArrayList<User>();






    /************************************************************************************************
     * Getters & Setters
     ************************************************************************************************/

    public int getId() {
        return electionId;
    }

    public void setId(int id) { electionId = id; }



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




    public String getAddress() {
        return location;
    }

    public void setAddress(String newLocation) {
        this.location = newLocation;
    }




    public String getElectionType() { return electionType; }

    public void setElectionType(String electionType)
    { if (electionType != "Plurality") {                            // temporarily only allows plurality vote
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
        int highestVote = proplist.get(0).getPropVotes();
        int highestIndex = 0;
        for (int i = 1; i < proplist.size(); i++) {
            if (proplist.get(i).getPropVotes() > highestVote)
                highestIndex = i;
        }
        return proplist.get(highestIndex);
    }
}
