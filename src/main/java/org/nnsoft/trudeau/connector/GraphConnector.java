package org.nnsoft.trudeau.connector;

/*
 *   Copyright 2013 - 2018 The Trudeau Project
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

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.graph.MutableGraph;
import com.google.common.graph.MutableValueGraph;

public final class GraphConnector
{

    public static <V, G extends MutableGraph<V>> LinkedMutableGraphBuilder<V, G> populate( final G graph )
    {
        final G checkedGraph = checkNotNull( graph, "Impossible to populate a null MutableGraph." );
        return new LinkedMutableGraphBuilder<V, G>()
        {

            @Override
            public G withConnections( MutableGraphConnection<V> graphConnection )
            {
                final MutableGraphConnection<V> checkedGraphConnection =
                    checkNotNull( graphConnection, "Input graph cannot be configured with null connections" );
                checkedGraphConnection.connect( new MutableGraphConnector<V>()
                {

                    @Override
                    public <N extends V> N addNode( N node )
                    {
                        final N checkedNode = checkNotNull( node, "Null vertex not admitted" );
                        graph.addNode( checkedNode );
                        return checkedNode;
                    }

                    @Override
                    public <H extends V> TailVertexConnector<V> connect( H head )
                    {
                        final H checkedHead = checkNotNull( head, "Null head vertex not admitted" );
                        return new TailVertexConnector<V>()
                        {

                            @Override
                            public <T extends V> void to( final T tail )
                            {
                                T checkedTail = checkNotNull( tail, "Null tail vertex not admitted" );
                                graph.putEdge( checkedHead, checkedTail );
                            }

                        };
                    }

                } );

                return checkedGraph;
            }

        };
    }

    /**
     * Allows populate the given {@link MutableValueGraph}.
     *
     * @param <V> the Graph vertices type
     * @param <E> the Graph edges type
     * @param <G> the Graph type
     * @param graph the graph has to be populated
     * @return the builder to configure vertices connection
     */
    public static <V, E, G extends MutableValueGraph<V, E>> LinkedMutableValueGraphBuilder<V, E, G> populate( final G graph )
    {
        final G checkedGraph = checkNotNull( graph, "Impossible to populate a null MutableValueGraph." );
        return new LinkedMutableValueGraphBuilder<V, E, G>() {

            @Override
            public G withConnections( final MutableValueGraphConnection<V, E> graphConnection )
            {
                final MutableValueGraphConnection<V, E>  checkedGraphConnection =
                    checkNotNull( graphConnection, "Input graph cannot be configured with null connections" );
                checkedGraphConnection.connect( new MutableValueGraphConnector<V, E>()
                {

                    @Override
                    public <N extends V> N addNode( final N node )
                    {
                        final N checkedNode = checkNotNull( node, "Null vertex not admitted" );
                        graph.addNode( checkedNode );
                        return checkedNode;
                    }

                    @Override
                    public <A extends E> HeadVertexConnector<V> addEdge( final A arc )
                    {
                        final A checkedArc = checkNotNull( arc, "Null edge not admitted" );
                        return new HeadVertexConnector<V>()
                        {

                            @Override
                            public <H extends V> TailVertexConnector<V> from( final H head )
                            {
                                final H checkedHead = checkNotNull( head, "Null head vertex not admitted" );
                                return new TailVertexConnector<V>()
                                {

                                    @Override
                                    public <T extends V> void to( final T tail )
                                    {
                                        T checkedTail = checkNotNull( tail, "Null tail vertex not admitted" );
                                        graph.putEdgeValue( checkedHead, checkedTail, checkedArc );
                                    }

                                };
                            }

                        };
                    }

                } );

                return checkedGraph;
            }

        };
    }

    /**
     * Hidden constructor, this class cannot be instantiated.
     */
    private GraphConnector()
    {
        // does nothing
    }

}
