package persistence.db.test.runner;

import black.door.hate.HalResource;
import persistence.db.test.runner.generated.GeneratedRunner;

/**
 * The main interface for entities of the {@code Runner}-table in the database.
 * <p>
 * This file is safe to edit. It will not be overwritten by the code generator.
 * 
 * @author company
 */
public interface Runner extends GeneratedRunner, HalResource {
    String BASE_PATH = "/runners";
}