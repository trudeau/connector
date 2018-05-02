package org.nnsoft.trudeau.connector;

import com.google.common.graph.MutableGraph;

/**
 * TODO Fillme!!!
 *
 * @param <N> the Graph nodes type
 * @param <E> the Graph edges type
 * @param <G> the Graph type
 */
public interface LinkedGraphBuilder<N, G extends MutableGraph<N>>
{

    /**
     * Connects the graph.
     *
     * @param graphDescription the {@link ValueGraphDescription}
     * @return the {@link org.nnsoft.trudeau.Graph} built from the connections.
     */
    G createConnections( GraphDescription<N> graphDescription );

}
