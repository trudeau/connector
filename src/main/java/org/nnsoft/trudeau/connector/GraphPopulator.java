package org.nnsoft.trudeau.connector;

import static org.nnsoft.trudeau.utils.Assertions.checkNotNull;

import org.nnsoft.trudeau.api.MutableGraph;

public final class GraphPopulator
{

    /**
     * Allows populate the given {@link MutableGraph}.
     *
     * @param <V> the Graph vertices type
     * @param <E> the Graph edges type
     * @param <G> the Graph type
     * @param graph the graph has to be populated
     * @return the builder to configure vertices connection
     */
    public static <V, E, G extends MutableGraph<V, E>> LinkedConnectionBuilder<V, E, G> populate( final G graph )
    {
        G checkedGraph = checkNotNull( graph, "Impossible to populate a null Graph." );
        return new DefaultLinkedConnectionBuilder<V, E, G>( checkedGraph );
    }

    /**
     * Hidden constructor, this class cannot be instantiated.
     */
    private GraphPopulator()
    {
        // does nothing
    }

}
