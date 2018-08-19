package persistence.db.test.runner.generated;

import com.speedment.common.annotation.GeneratedCode;
import com.speedment.runtime.config.identifier.TableIdentifier;
import com.speedment.runtime.core.manager.Manager;
import com.speedment.runtime.field.Field;
import persistence.db.test.runner.Runner;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;

/**
 * The generated base interface for the manager of every {@link
 * persistence.db.test.runner.Runner} entity.
 * <p>
 * This file has been automatically generated by Speedment. Any changes made to
 * it will be overwritten.
 * 
 * @author Speedment
 */
@GeneratedCode("Speedment")
public interface GeneratedRunnerManager extends Manager<Runner> {
    
    TableIdentifier<Runner> IDENTIFIER = TableIdentifier.of("test", "test", "Runner");
    List<Field<Runner>> FIELDS = unmodifiableList(asList(
        Runner.RUNNER_ID,
        Runner.FIRST_NAME,
        Runner.LAST_NAME,
        Runner.NATIONALITY,
        Runner.SPECIALTY,
        Runner.HEIGHT,
        Runner.WEIGHT,
        Runner.PRICE,
        Runner.TEAM_ID,
        Runner.RUNNER_CATEGORY
    ));
    
    @Override
    default Class<Runner> getEntityClass() {
        return Runner.class;
    }
}