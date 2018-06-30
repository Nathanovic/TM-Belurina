package persistence.db.test.userteam.generated;

import com.speedment.common.annotation.GeneratedCode;
import com.speedment.runtime.config.identifier.TableIdentifier;
import com.speedment.runtime.core.manager.AbstractManager;
import com.speedment.runtime.field.Field;
import persistence.db.test.userteam.UserTeam;
import persistence.db.test.userteam.UserTeamManager;

import java.util.stream.Stream;

/**
 * The generated base implementation for the manager of every {@link
 * persistence.db.test.userteam.UserTeam} entity.
 * <p>
 * This file has been automatically generated by Speedment. Any changes made to
 * it will be overwritten.
 * 
 * @author Speedment
 */
@GeneratedCode("Speedment")
public abstract class GeneratedUserTeamManagerImpl 
extends AbstractManager<UserTeam> 
implements GeneratedUserTeamManager {
    
    private final TableIdentifier<UserTeam> tableIdentifier;
    
    protected GeneratedUserTeamManagerImpl() {
        this.tableIdentifier = TableIdentifier.of("test", "test", "UserTeam");
    }
    
    @Override
    public TableIdentifier<UserTeam> getTableIdentifier() {
        return tableIdentifier;
    }
    
    @Override
    public Stream<Field<UserTeam>> fields() {
        return UserTeamManager.FIELDS.stream();
    }
    
    @Override
    public Stream<Field<UserTeam>> primaryKeyFields() {
        return Stream.of(
            UserTeam.USER_ID,
            UserTeam.RUNNER_ID
        );
    }
}