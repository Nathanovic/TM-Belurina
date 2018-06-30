package persistence.db.test.team.generated;

import com.speedment.common.annotation.GeneratedCode;
import com.speedment.runtime.core.manager.Manager;
import persistence.db.test.team.Team;
import persistence.db.test.tour.Tour;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * The generated base implementation of the {@link
 * persistence.db.test.team.Team}-interface.
 * <p>
 * This file has been automatically generated by Speedment. Any changes made to
 * it will be overwritten.
 * 
 * @author Speedment
 */
@GeneratedCode("Speedment")
public abstract class GeneratedTeamImpl implements Team {
    
    private int teamId;
    private String name;
    private int tourId;
    
    protected GeneratedTeamImpl() {}
    
    @Override
    public int getTeamId() {
        return teamId;
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public int getTourId() {
        return tourId;
    }
    
    @Override
    public Team setTeamId(int teamId) {
        this.teamId = teamId;
        return this;
    }
    
    @Override
    public Team setName(String name) {
        this.name = name;
        return this;
    }
    
    @Override
    public Team setTourId(int tourId) {
        this.tourId = tourId;
        return this;
    }
    
    @Override
    public Tour findTourId(Manager<Tour> foreignManager) {
        return foreignManager.stream().filter(Tour.TOUR_ID.equal(getTourId())).findAny().orElse(null);
    }
    
    @Override
    public String toString() {
        final StringJoiner sj = new StringJoiner(", ", "{ ", " }");
        sj.add("teamId = " + Objects.toString(getTeamId()));
        sj.add("name = "   + Objects.toString(getName()));
        sj.add("tourId = " + Objects.toString(getTourId()));
        return "TeamImpl " + sj.toString();
    }
    
    @Override
    public boolean equals(Object that) {
        if (this == that) { return true; }
        if (!(that instanceof Team)) { return false; }
        final Team thatTeam = (Team)that;
        if (this.getTeamId() != thatTeam.getTeamId()) { return false; }
        if (!Objects.equals(this.getName(), thatTeam.getName())) { return false; }
        if (this.getTourId() != thatTeam.getTourId()) { return false; }
        return true;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Integer.hashCode(getTeamId());
        hash = 31 * hash + Objects.hashCode(getName());
        hash = 31 * hash + Integer.hashCode(getTourId());
        return hash;
    }
}