package controllers;

import models.Election;
import play.*;
import play.data.Form;
import play.db.ebean.Model;
import play.mvc.*;

import views.html.*;

import java.util.List;

import static play.libs.Json.toJson;

public class Application extends Controller {

    public static Result index() {

        return ok(index.render(" for appcivist"));

        //
    }

    public static Result addElection() {

        Election election = Form.form(Election.class).bindFromRequest().get();

        Integer nextId = election.findNextID();

        if (election.getId() != null
                && (election.getElectionURL() == null || election.getElectionURL() == "")) {
            election.setElectionURL(request().host() + "/" + nextId);
        }

        Election.create(election);

        Logger.info("Election has been saved. New election with id = " + election.getId());
        return redirect(routes.Application.index());

    }

    public static Result getElections() {
        List<Election> elections = new Model.Finder(String.class, Election.class).all();
        return ok(toJson(elections));
    }

    public static Result getElectionID(int electionID) {

        return redirect(routes.Application.getElectionID(electionID));
    }

}