package org.nnsoft.trudeau.connector;

import com.google.common.graph.MutableGraph;

/**
 * TODO Fillme!!!
 *
 * @param <V> the Graph vertices type
 * @param <E> the Graph edges type
 * @param <G> the Graph type
 */
public interface LinkedMutableGraphBuilder<V, G extends MutableGraph<V>>
{

    /**
     * Connects the graph.
     *
     * @param graphConnection the {@link MutableValueGraphConnection}
     * @return the {@link org.nnsoft.trudeau.Graph} built from the connections.
     */
    G withConnections( MutableGraphConnection<V> graphConnection );

}
