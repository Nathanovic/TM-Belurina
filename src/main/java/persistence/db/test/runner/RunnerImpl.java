package persistence.db.test.runner;

import black.door.hate.HalRepresentation;
import persistence.db.test.runner.generated.GeneratedRunnerImpl;
import persistence.db.test.team.Team;

import java.net.URI;

/**
 * The default implementation of the {@link
 * persistence.db.test.runner.Runner}-interface.
 * <p>
 * This file is safe to edit. It will not be overwritten by the code generator.
 * 
 * @author company
 */
public final class RunnerImpl 
extends GeneratedRunnerImpl 
implements Runner {
    @Override
    public HalRepresentation.HalRepresentationBuilder representationBuilder() {
        return HalRepresentation.builder()
                .addProperty("firstName", getFirstName().orElse(""))
                .addProperty("lastName", getLastName().orElse(""))
                .addProperty("nationality", getNationality().orElse(""))
                .addProperty("specialty", getSpecialty().orElse(""))
                .addProperty("height", getHeight().orElse(-1))
                .addProperty("weight", getWeight().orElse(-1))
                .addProperty("price", getPrice().orElse(-1))
                .addLink("team", URI.create(Team.BASE_PATH + "/" + getTeamId().getAsInt()))
                .addLink("self", this);
    }

    @Override
    public URI location() {
        return URI.create(this.BASE_PATH + "/" + getRunnerId());
    }
}