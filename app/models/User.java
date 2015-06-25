package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;
import java.util.ArrayList;
import assets.Pair;


/**
 * Created by Ryan on 6/8/2015.
 */

@Entity
public class User {

    @Id
    private Integer userID;
    private String userName;

    private ArrayList elections;
    private ArrayList hasVoted; // list containing elections which the user has voted in

    /************************************************************************************************
     * Getters & Setters
     ************************************************************************************************/



    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }




    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }



    public ArrayList getElections() {
        return elections;
    }

    public void overrideElections(ArrayList<Pair<Election, Boolean>> elections) {
        this.elections = elections;
    }

    public void addElection(Election newElection) {
        this.elections.add(newElection);
    }



    public void addtoHasVoted(Election votedOnElection) {
        this.hasVoted.add(votedOnElection);
    }

    /************************************************************************************************
     * Methods
     ************************************************************************************************/

    public void Vote(Election activeElection, Proposal voteProposal) {

        if (activeElection.getUsers().contains(this)) {

            if (activeElection.getProposals().contains(voteProposal)) {

                if (this.hasVoted.contains(activeElection)) {
                    throw new IllegalArgumentException("You have already voted in this election!");
                }
                else {
                    voteProposal.setPropVotes((voteProposal.getPropVotes() + 1));
                    this.addtoHasVoted(activeElection);
                }
            }
            else  { throw new IllegalArgumentException("Bad proposal for this election"); }
        }
        else throw new IllegalArgumentException("You are not allowed to vote in this election");
    }


}
