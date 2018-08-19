package persistence.db.test.user;

import black.door.hate.HalResource;
import persistence.db.test.user.generated.GeneratedUser;

/**
 * The main interface for entities of the {@code User}-table in the database.
 * <p>
 * This file is safe to edit. It will not be overwritten by the code generator.
 * 
 * @author company
 */
public interface User extends GeneratedUser, HalResource {
    String BASE_PATH = "/user";
}