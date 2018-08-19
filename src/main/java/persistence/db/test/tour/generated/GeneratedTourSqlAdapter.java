package persistence.db.test.tour.generated;

import com.speedment.common.annotation.GeneratedCode;
import com.speedment.runtime.config.identifier.TableIdentifier;
import com.speedment.runtime.core.component.SqlAdapter;
import com.speedment.runtime.core.db.SqlFunction;
import persistence.db.test.tour.Tour;
import persistence.db.test.tour.TourImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.speedment.runtime.core.internal.util.sql.ResultSetUtil.getInt;

/**
 * The generated Sql Adapter for a {@link persistence.db.test.tour.Tour} entity.
 * <p>
 * This file has been automatically generated by Speedment. Any changes made to
 * it will be overwritten.
 * 
 * @author Speedment
 */
@GeneratedCode("Speedment")
public abstract class GeneratedTourSqlAdapter implements SqlAdapter<Tour> {
    
    private final TableIdentifier<Tour> tableIdentifier;
    
    protected GeneratedTourSqlAdapter() {
        this.tableIdentifier = TableIdentifier.of("test", "test", "Tour");
    }
    
    protected Tour apply(ResultSet resultSet, int offset) throws SQLException {
        return createEntity()
            .setTourId(            resultSet.getInt(1 + offset))
            .setName(              resultSet.getString(2 + offset))
            .setYear(              getInt(resultSet, 3 + offset))
            .setEndOfRegistration( resultSet.getTimestamp(4 + offset))
            ;
    }
    
    protected TourImpl createEntity() {
        return new TourImpl();
    }
    
    @Override
    public TableIdentifier<Tour> identifier() {
        return tableIdentifier;
    }
    
    @Override
    public SqlFunction<ResultSet, Tour> entityMapper() {
        return entityMapper(0);
    }
    
    @Override
    public SqlFunction<ResultSet, Tour> entityMapper(int offset) {
        return rs -> apply(rs, offset);
    }
}