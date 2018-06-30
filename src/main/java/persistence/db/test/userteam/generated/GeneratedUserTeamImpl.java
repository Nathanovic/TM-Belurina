package persistence.db.test.userteam.generated;

import com.speedment.common.annotation.GeneratedCode;
import com.speedment.runtime.core.manager.Manager;
import persistence.db.test.runner.Runner;
import persistence.db.test.user.User;
import persistence.db.test.userteam.UserTeam;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * The generated base implementation of the {@link
 * persistence.db.test.userteam.UserTeam}-interface.
 * <p>
 * This file has been automatically generated by Speedment. Any changes made to
 * it will be overwritten.
 * 
 * @author Speedment
 */
@GeneratedCode("Speedment")
public abstract class GeneratedUserTeamImpl implements UserTeam {
    
    private int userId;
    private int runnerId;
    
    protected GeneratedUserTeamImpl() {}
    
    @Override
    public int getUserId() {
        return userId;
    }
    
    @Override
    public int getRunnerId() {
        return runnerId;
    }
    
    @Override
    public UserTeam setUserId(int userId) {
        this.userId = userId;
        return this;
    }
    
    @Override
    public UserTeam setRunnerId(int runnerId) {
        this.runnerId = runnerId;
        return this;
    }
    
    @Override
    public User findUserId(Manager<User> foreignManager) {
        return foreignManager.stream().filter(User.USER_ID.equal(getUserId())).findAny().orElse(null);
    }
    
    @Override
    public Runner findRunnerId(Manager<Runner> foreignManager) {
        return foreignManager.stream().filter(Runner.RUNNER_ID.equal(getRunnerId())).findAny().orElse(null);
    }
    
    @Override
    public String toString() {
        final StringJoiner sj = new StringJoiner(", ", "{ ", " }");
        sj.add("userId = "   + Objects.toString(getUserId()));
        sj.add("runnerId = " + Objects.toString(getRunnerId()));
        return "UserTeamImpl " + sj.toString();
    }
    
    @Override
    public boolean equals(Object that) {
        if (this == that) { return true; }
        if (!(that instanceof UserTeam)) { return false; }
        final UserTeam thatUserTeam = (UserTeam)that;
        if (this.getUserId() != thatUserTeam.getUserId()) { return false; }
        if (this.getRunnerId() != thatUserTeam.getRunnerId()) { return false; }
        return true;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Integer.hashCode(getUserId());
        hash = 31 * hash + Integer.hashCode(getRunnerId());
        return hash;
    }
}