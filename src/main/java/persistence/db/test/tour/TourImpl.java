package persistence.db.test.tour;

import black.door.hate.HalRepresentation;
import persistence.db.test.team.Team;
import persistence.db.test.tour.generated.GeneratedTourImpl;

import java.net.URI;

/**
 * The default implementation of the {@link
 * persistence.db.test.tour.Tour}-interface.
 * <p>
 * This file is safe to edit. It will not be overwritten by the code generator.
 * 
 * @author company
 */
public final class TourImpl 
extends GeneratedTourImpl 
implements Tour {
    @Override
    public HalRepresentation.HalRepresentationBuilder representationBuilder() {
        return HalRepresentation.builder()
                .addProperty("name", getName())
                .addProperty("year", getYear().orElse(-1))
                .addLink("self", this)
                //.addLink("teams", URI.create(TeamController.getUrl(Team.TOUR_ID, getTourId())));
                .addLink("teams", URI.create(this.location().toString() + Team.BASE_PATH));
    }

    @Override
    public URI location() {
        return URI.create(BASE_PATH + "/" + getTourId());
    }
}