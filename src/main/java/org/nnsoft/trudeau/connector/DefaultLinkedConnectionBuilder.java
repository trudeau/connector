package org.nnsoft.trudeau.connector;

/*
 *   Copyright 2013 The Trudeau Project
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

import static org.nnsoft.trudeau.utils.Assertions.checkNotNull;

import org.nnsoft.trudeau.api.MutableGraph;

/**
 * TODO Fillme!!
 *
 * @param <V> the Graph vertices type
 * @param <E> the Graph edges type
 * @param <G> the Graph type
 */
public final class DefaultLinkedConnectionBuilder<V, E, G extends MutableGraph<V, E>>
    implements LinkedConnectionBuilder<V, E, G>
{

    private final G graph;

    /**
     * Creates a new {@link DefaultLinkedConnectionBuilder} for the graph.
     *
     * @param graph the graph
     */
    public DefaultLinkedConnectionBuilder( G graph )
    {
        this.graph = graph;
    }

    /**
     * {@inheritDoc}
     */
    public G withConnections( GraphConnection<V, E> graphConnection )
    {
        graphConnection = checkNotNull( graphConnection, "Input graph cannot be configured with null connections" );

        GraphConnector<V, E> grapher = new DefaultGrapher<V, E>( graph );
        graphConnection.connect( grapher );

        return graph;
    }

}
