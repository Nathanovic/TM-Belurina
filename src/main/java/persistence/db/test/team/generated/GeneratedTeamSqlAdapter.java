package persistence.db.test.team.generated;

import com.speedment.common.annotation.GeneratedCode;
import com.speedment.runtime.config.identifier.TableIdentifier;
import com.speedment.runtime.core.component.SqlAdapter;
import com.speedment.runtime.core.db.SqlFunction;
import persistence.db.test.team.Team;
import persistence.db.test.team.TeamImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The generated Sql Adapter for a {@link persistence.db.test.team.Team} entity.
 * <p>
 * This file has been automatically generated by Speedment. Any changes made to
 * it will be overwritten.
 * 
 * @author Speedment
 */
@GeneratedCode("Speedment")
public abstract class GeneratedTeamSqlAdapter implements SqlAdapter<Team> {
    
    private final TableIdentifier<Team> tableIdentifier;
    
    protected GeneratedTeamSqlAdapter() {
        this.tableIdentifier = TableIdentifier.of("test", "test", "Team");
    }
    
    protected Team apply(ResultSet resultSet, int offset) throws SQLException {
        return createEntity()
            .setTeamId( resultSet.getInt(1 + offset))
            .setName(   resultSet.getString(2 + offset))
            .setTourId( resultSet.getInt(3 + offset))
            ;
    }
    
    protected TeamImpl createEntity() {
        return new TeamImpl();
    }
    
    @Override
    public TableIdentifier<Team> identifier() {
        return tableIdentifier;
    }
    
    @Override
    public SqlFunction<ResultSet, Team> entityMapper() {
        return entityMapper(0);
    }
    
    @Override
    public SqlFunction<ResultSet, Team> entityMapper(int offset) {
        return rs -> apply(rs, offset);
    }
}