package persistence.db.test.team.generated;

import com.speedment.common.annotation.GeneratedCode;
import com.speedment.runtime.config.identifier.TableIdentifier;
import com.speedment.runtime.core.manager.AbstractManager;
import com.speedment.runtime.field.Field;
import persistence.db.test.team.Team;
import persistence.db.test.team.TeamManager;

import java.util.stream.Stream;

/**
 * The generated base implementation for the manager of every {@link
 * persistence.db.test.team.Team} entity.
 * <p>
 * This file has been automatically generated by Speedment. Any changes made to
 * it will be overwritten.
 * 
 * @author Speedment
 */
@GeneratedCode("Speedment")
public abstract class GeneratedTeamManagerImpl 
extends AbstractManager<Team> 
implements GeneratedTeamManager {
    
    private final TableIdentifier<Team> tableIdentifier;
    
    protected GeneratedTeamManagerImpl() {
        this.tableIdentifier = TableIdentifier.of("test", "test", "Team");
    }
    
    @Override
    public TableIdentifier<Team> getTableIdentifier() {
        return tableIdentifier;
    }
    
    @Override
    public Stream<Field<Team>> fields() {
        return TeamManager.FIELDS.stream();
    }
    
    @Override
    public Stream<Field<Team>> primaryKeyFields() {
        return Stream.of(
            Team.TEAM_ID
        );
    }
}