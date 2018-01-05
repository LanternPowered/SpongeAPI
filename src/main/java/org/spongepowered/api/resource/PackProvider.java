package org.spongepowered.api.resource;

import java.util.Map;

@FunctionalInterface
public interface PackProvider {

    /**
     * Returns a collection of available packs. An available pack could be
     * active or not. The key in the returned map should correspond it its id.
     *
     * @return A collection of available packs.
     */
    Map<String, Pack> getPacks();

}
