package restservice.util;

import black.door.hate.HalRepresentation;
import black.door.hate.HalResource;

import java.net.URI;

public class EmptyHalResource implements HalResource {
    public static <T> T instantiate() {
        return (T) new EmptyHalResource();
    }

    @Override
    public HalRepresentation.HalRepresentationBuilder representationBuilder() {
        return HalRepresentation.builder();
    }

    @Override
    public URI location() {
        return null;
    }
}
