package persistence.db.test.team;

import black.door.hate.HalResource;
import persistence.db.test.team.generated.GeneratedTeam;

/**
 * The main interface for entities of the {@code Team}-table in the database.
 * <p>
 * This file is safe to edit. It will not be overwritten by the code generator.
 * 
 * @author company
 */
public interface Team extends GeneratedTeam, HalResource {
    public static final String BASE_PATH = "/teams";
}