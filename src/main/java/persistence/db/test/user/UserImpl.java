package persistence.db.test.user;

import black.door.hate.HalRepresentation;
import persistence.db.test.user.generated.GeneratedUserImpl;

import java.net.URI;

/**
 * The default implementation of the {@link
 * persistence.db.test.user.User}-interface.
 * <p>
 * This file is safe to edit. It will not be overwritten by the code generator.
 * 
 * @author company
 */
public final class UserImpl 
extends GeneratedUserImpl
implements User {
    @Override
    public HalRepresentation.HalRepresentationBuilder representationBuilder() {
        return HalRepresentation.builder()
                .addProperty("username", getName())
                .addProperty("email", getEmail())
                .addLink("team", URI.create(BASE_PATH + "/team"))
                .addLink("self", this);
    }

    @Override
    public URI location() {
        return URI.create(BASE_PATH);
    }
}