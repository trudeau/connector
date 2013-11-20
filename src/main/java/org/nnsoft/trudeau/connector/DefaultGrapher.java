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

final class DefaultGrapher<V, E>
    implements GraphConnector<V, E>
{

    private final MutableGraph<V, E> graph;

    public DefaultGrapher( MutableGraph<V, E> graph )
    {
        this.graph = graph;
    }

    public <N extends V> N addVertex( N node )
    {
        node = checkNotNull( node, "Null vertex not admitted" );
        graph.addVertex( node );
        return node;
    }

    public <A extends E> HeadVertexConnector<V, E> addEdge( A arc )
    {
        arc = checkNotNull( arc, "Null edge not admitted" );
        return new DefaultHeadVertexConnector<V, E>( graph, arc );
    }

}
