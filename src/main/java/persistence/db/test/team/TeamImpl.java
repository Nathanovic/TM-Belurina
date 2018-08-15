package persistence.db.test.team;

import black.door.hate.HalRepresentation;
import persistence.db.test.runner.Runner;
import persistence.db.test.team.generated.GeneratedTeamImpl;

import java.net.URI;

/**
 * The default implementation of the {@link
 * persistence.db.test.team.Team}-interface.
 * <p>
 * This file is safe to edit. It will not be overwritten by the code generator.
 * 
 * @author company
 */
public final class TeamImpl 
extends GeneratedTeamImpl
implements Team {
    @Override
    public HalRepresentation.HalRepresentationBuilder representationBuilder() {
        return HalRepresentation.builder()
                .addProperty("name", getName())
                .addLink("runners", URI.create(location().toString() + Runner.BASE_PATH))
                .addLink("self", this);
    }

    @Override
    public URI location() {
        return URI.create(this.BASE_PATH + "/" + getTeamId());
    }
}