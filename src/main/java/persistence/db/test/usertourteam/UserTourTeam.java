package persistence.db.test.usertourteam;

import persistence.db.test.user.User;
import persistence.db.test.usertourteam.generated.GeneratedUserTourTeam;

/**
 * The main interface for entities of the {@code UserTourTeam}-table in the
 * database.
 * <p>
 * This file is safe to edit. It will not be overwritten by the code generator.
 * 
 * @author company
 */
public interface UserTourTeam extends GeneratedUserTourTeam {
    String BASE_PATH = User.BASE_PATH + "/team";
}