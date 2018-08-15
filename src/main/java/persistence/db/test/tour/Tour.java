package persistence.db.test.tour;

import black.door.hate.HalResource;
import persistence.db.test.tour.generated.GeneratedTour;

/**
 * The main interface for entities of the {@code Tour}-table in the database.
 * <p>
 * This file is safe to edit. It will not be overwritten by the code generator.
 * 
 * @author company
 */
public interface Tour extends GeneratedTour, HalResource {
    String BASE_PATH = "/tours";
}