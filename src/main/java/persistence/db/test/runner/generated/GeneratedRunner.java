package persistence.db.test.runner.generated;

import com.speedment.common.annotation.GeneratedCode;
import com.speedment.runtime.config.identifier.ColumnIdentifier;
import com.speedment.runtime.config.identifier.TableIdentifier;
import com.speedment.runtime.core.manager.Manager;
import com.speedment.runtime.core.util.OptionalUtil;
import com.speedment.runtime.field.ComparableField;
import com.speedment.runtime.field.ComparableForeignKeyField;
import com.speedment.runtime.field.IntField;
import com.speedment.runtime.field.StringField;
import com.speedment.runtime.typemapper.TypeMapper;
import persistence.db.test.runner.Runner;
import persistence.db.test.team.Team;

import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;

/**
 * The generated base for the {@link
 * persistence.db.test.runner.Runner}-interface representing entities of the
 * {@code Runner}-table in the database.
 * <p>
 * This file has been automatically generated by Speedment. Any changes made to
 * it will be overwritten.
 * 
 * @author Speedment
 */
@GeneratedCode("Speedment")
public interface GeneratedRunner {
    
    /**
     * This Field corresponds to the {@link Runner} field that can be obtained
     * using the {@link Runner#getRunnerId()} method.
     */
    IntField<Runner, Integer> RUNNER_ID = IntField.create(
        Identifier.RUNNER_ID,
        Runner::getRunnerId,
        Runner::setRunnerId,
        TypeMapper.primitive(),
        true
    );
    /**
     * This Field corresponds to the {@link Runner} field that can be obtained
     * using the {@link Runner#getFirstName()} method.
     */
    StringField<Runner, String> FIRST_NAME = StringField.create(
        Identifier.FIRST_NAME,
        o -> OptionalUtil.unwrap(o.getFirstName()),
        Runner::setFirstName,
        TypeMapper.identity(),
        false
    );
    /**
     * This Field corresponds to the {@link Runner} field that can be obtained
     * using the {@link Runner#getLastName()} method.
     */
    StringField<Runner, String> LAST_NAME = StringField.create(
        Identifier.LAST_NAME,
        o -> OptionalUtil.unwrap(o.getLastName()),
        Runner::setLastName,
        TypeMapper.identity(),
        false
    );
    /**
     * This Field corresponds to the {@link Runner} field that can be obtained
     * using the {@link Runner#getNationality()} method.
     */
    StringField<Runner, String> NATIONALITY = StringField.create(
        Identifier.NATIONALITY,
        o -> OptionalUtil.unwrap(o.getNationality()),
        Runner::setNationality,
        TypeMapper.identity(),
        false
    );
    /**
     * This Field corresponds to the {@link Runner} field that can be obtained
     * using the {@link Runner#getSpecialty()} method.
     */
    StringField<Runner, String> SPECIALTY = StringField.create(
        Identifier.SPECIALTY,
        o -> OptionalUtil.unwrap(o.getSpecialty()),
        Runner::setSpecialty,
        TypeMapper.identity(),
        false
    );
    /**
     * This Field corresponds to the {@link Runner} field that can be obtained
     * using the {@link Runner#getHeight()} method.
     */
    ComparableField<Runner, Double, Double> HEIGHT = ComparableField.create(
        Identifier.HEIGHT,
        o -> OptionalUtil.unwrap(o.getHeight()),
        Runner::setHeight,
        TypeMapper.identity(),
        false
    );
    /**
     * This Field corresponds to the {@link Runner} field that can be obtained
     * using the {@link Runner#getWeight()} method.
     */
    ComparableField<Runner, Double, Double> WEIGHT = ComparableField.create(
        Identifier.WEIGHT,
        o -> OptionalUtil.unwrap(o.getWeight()),
        Runner::setWeight,
        TypeMapper.identity(),
        false
    );
    /**
     * This Field corresponds to the {@link Runner} field that can be obtained
     * using the {@link Runner#getPrice()} method.
     */
    ComparableField<Runner, Double, Double> PRICE = ComparableField.create(
        Identifier.PRICE,
        o -> OptionalUtil.unwrap(o.getPrice()),
        Runner::setPrice,
        TypeMapper.identity(),
        false
    );
    /**
     * This Field corresponds to the {@link Runner} field that can be obtained
     * using the {@link Runner#getTeamId()} method.
     */
    ComparableForeignKeyField<Runner, Integer, Integer, Team> TEAM_ID = ComparableForeignKeyField.create(
        Identifier.TEAM_ID,
        o -> OptionalUtil.unwrap(o.getTeamId()),
        Runner::setTeamId,
        Team.TEAM_ID,
        TypeMapper.identity(),
        false
    );
    /**
     * This Field corresponds to the {@link Runner} field that can be obtained
     * using the {@link Runner#getRunnerCategory()} method.
     */
    StringField<Runner, String> RUNNER_CATEGORY = StringField.create(
        Identifier.RUNNER_CATEGORY,
        o -> OptionalUtil.unwrap(o.getRunnerCategory()),
        Runner::setRunnerCategory,
        TypeMapper.identity(),
        false
    );
    
    /**
     * Returns the runnerId of this Runner. The runnerId field corresponds to
     * the database column test.test.Runner.RunnerId.
     * 
     * @return the runnerId of this Runner
     */
    int getRunnerId();
    
    /**
     * Returns the firstName of this Runner. The firstName field corresponds to
     * the database column test.test.Runner.FirstName.
     * 
     * @return the firstName of this Runner
     */
    Optional<String> getFirstName();
    
    /**
     * Returns the lastName of this Runner. The lastName field corresponds to
     * the database column test.test.Runner.LastName.
     * 
     * @return the lastName of this Runner
     */
    Optional<String> getLastName();
    
    /**
     * Returns the nationality of this Runner. The nationality field corresponds
     * to the database column test.test.Runner.Nationality.
     * 
     * @return the nationality of this Runner
     */
    Optional<String> getNationality();
    
    /**
     * Returns the specialty of this Runner. The specialty field corresponds to
     * the database column test.test.Runner.Specialty.
     * 
     * @return the specialty of this Runner
     */
    Optional<String> getSpecialty();
    
    /**
     * Returns the height of this Runner. The height field corresponds to the
     * database column test.test.Runner.Height.
     * 
     * @return the height of this Runner
     */
    OptionalDouble getHeight();
    
    /**
     * Returns the weight of this Runner. The weight field corresponds to the
     * database column test.test.Runner.Weight.
     * 
     * @return the weight of this Runner
     */
    OptionalDouble getWeight();
    
    /**
     * Returns the price of this Runner. The price field corresponds to the
     * database column test.test.Runner.Price.
     * 
     * @return the price of this Runner
     */
    OptionalDouble getPrice();
    
    /**
     * Returns the teamId of this Runner. The teamId field corresponds to the
     * database column test.test.Runner.TeamId.
     * 
     * @return the teamId of this Runner
     */
    OptionalInt getTeamId();
    
    /**
     * Returns the runnerCategory of this Runner. The runnerCategory field
     * corresponds to the database column test.test.Runner.RunnerCategory.
     * 
     * @return the runnerCategory of this Runner
     */
    Optional<String> getRunnerCategory();
    
    /**
     * Sets the runnerId of this Runner. The runnerId field corresponds to the
     * database column test.test.Runner.RunnerId.
     * 
     * @param runnerId to set of this Runner
     * @return         this Runner instance
     */
    Runner setRunnerId(int runnerId);
    
    /**
     * Sets the firstName of this Runner. The firstName field corresponds to the
     * database column test.test.Runner.FirstName.
     * 
     * @param firstName to set of this Runner
     * @return          this Runner instance
     */
    Runner setFirstName(String firstName);
    
    /**
     * Sets the lastName of this Runner. The lastName field corresponds to the
     * database column test.test.Runner.LastName.
     * 
     * @param lastName to set of this Runner
     * @return         this Runner instance
     */
    Runner setLastName(String lastName);
    
    /**
     * Sets the nationality of this Runner. The nationality field corresponds to
     * the database column test.test.Runner.Nationality.
     * 
     * @param nationality to set of this Runner
     * @return            this Runner instance
     */
    Runner setNationality(String nationality);
    
    /**
     * Sets the specialty of this Runner. The specialty field corresponds to the
     * database column test.test.Runner.Specialty.
     * 
     * @param specialty to set of this Runner
     * @return          this Runner instance
     */
    Runner setSpecialty(String specialty);
    
    /**
     * Sets the height of this Runner. The height field corresponds to the
     * database column test.test.Runner.Height.
     * 
     * @param height to set of this Runner
     * @return       this Runner instance
     */
    Runner setHeight(Double height);
    
    /**
     * Sets the weight of this Runner. The weight field corresponds to the
     * database column test.test.Runner.Weight.
     * 
     * @param weight to set of this Runner
     * @return       this Runner instance
     */
    Runner setWeight(Double weight);
    
    /**
     * Sets the price of this Runner. The price field corresponds to the
     * database column test.test.Runner.Price.
     * 
     * @param price to set of this Runner
     * @return      this Runner instance
     */
    Runner setPrice(Double price);
    
    /**
     * Sets the teamId of this Runner. The teamId field corresponds to the
     * database column test.test.Runner.TeamId.
     * 
     * @param teamId to set of this Runner
     * @return       this Runner instance
     */
    Runner setTeamId(Integer teamId);
    
    /**
     * Sets the runnerCategory of this Runner. The runnerCategory field
     * corresponds to the database column test.test.Runner.RunnerCategory.
     * 
     * @param runnerCategory to set of this Runner
     * @return               this Runner instance
     */
    Runner setRunnerCategory(String runnerCategory);
    
    /**
     * Queries the specified manager for the referenced Team. If no such Team
     * exists, an {@code NullPointerException} will be thrown.
     * 
     * @param foreignManager the manager to query for the entity
     * @return               the foreign entity referenced
     */
    Optional<Team> findTeamId(Manager<Team> foreignManager);
    
    enum Identifier implements ColumnIdentifier<Runner> {
        
        RUNNER_ID       ("RunnerId"),
        FIRST_NAME      ("FirstName"),
        LAST_NAME       ("LastName"),
        NATIONALITY     ("Nationality"),
        SPECIALTY       ("Specialty"),
        HEIGHT          ("Height"),
        WEIGHT          ("Weight"),
        PRICE           ("Price"),
        TEAM_ID         ("TeamId"),
        RUNNER_CATEGORY ("RunnerCategory");
        
        private final String columnId;
        private final TableIdentifier<Runner> tableIdentifier;
        
        Identifier(String columnId) {
            this.columnId        = columnId;
            this.tableIdentifier = TableIdentifier.of(    getDbmsId(), 
                getSchemaId(), 
                getTableId());
        }
        
        @Override
        public String getDbmsId() {
            return "test";
        }
        
        @Override
        public String getSchemaId() {
            return "test";
        }
        
        @Override
        public String getTableId() {
            return "Runner";
        }
        
        @Override
        public String getColumnId() {
            return this.columnId;
        }
        
        @Override
        public TableIdentifier<Runner> asTableIdentifier() {
            return this.tableIdentifier;
        }
    }
}