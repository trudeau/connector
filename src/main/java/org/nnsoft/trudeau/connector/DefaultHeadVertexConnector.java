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

final class DefaultHeadVertexConnector<V, E>
    implements HeadVertexConnector<V, E>
{

    private final MutableGraph<V, E> graph;

    private final E edge;

    public DefaultHeadVertexConnector( MutableGraph<V, E> graph, E edge )
    {
        this.graph = graph;
        this.edge = edge;
    }

    public <H extends V> TailVertexConnector<V, E> from( H head )
    {
        head = checkNotNull( head, "Null head vertex not admitted" );
        return new DefaultTailVertexConnector<V, E>( graph, edge, head );
    }

}
