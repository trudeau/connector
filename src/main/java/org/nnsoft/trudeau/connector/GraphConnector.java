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

import static java.util.Objects.requireNonNull;

import com.google.common.graph.MutableGraph;
import com.google.common.graph.MutableValueGraph;

public final class GraphConnector
{

    public static <N, G extends MutableGraph<N>> LinkedGraphBuilder<N, G> on( final G graph )
    {
        final G checkedGraph = requireNonNull( graph, "Impossible to populate a null MutableGraph." );
        return new LinkedGraphBuilder<N, G>()
        {

            @Override
            public G createConnections( GraphDescription<N> graphConnection )
            {
                final GraphDescription<N> checkedGraphConnection =
                    requireNonNull( graphConnection, "Input graph cannot be configured with null connections" );
                checkedGraphConnection.describe( new Grapher<N>()
                {

                    @Override
                    public <V extends N> V addNode( V node )
                    {
                        final V checkedNode = requireNonNull( node, "Null vertex not admitted" );
                        graph.addNode( checkedNode );
                        return checkedNode;
                    }

                    @Override
                    public <H extends N> TailConnector<N> connect( H head )
                    {
                        final H checkedHead = requireNonNull( head, "Null head vertex not admitted" );
                        return new TailConnector<N>()
                        {

                            @Override
                            public <T extends N> void to( final T tail )
                            {
                                T checkedTail = requireNonNull( tail, "Null tail node not admitted" );
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
    public static <V, E, G extends MutableValueGraph<V, E>> LinkedValueGraphBuilder<V, E, G> on( final G graph )
    {
        final G checkedGraph = requireNonNull( graph, "Impossible to populate a null MutableValueGraph." );
        return new LinkedValueGraphBuilder<V, E, G>() {

            @Override
            public G createConnections( final ValueGraphDescription<V, E> graphConnection )
            {
                final ValueGraphDescription<V, E>  checkedGraphConnection =
                    requireNonNull( graphConnection, "Input graph cannot be configured with null connections" );
                checkedGraphConnection.describe( new ValueGrapher<V, E>()
                {

                    @Override
                    public <N extends V> N addNode( final N node )
                    {
                        final N checkedNode = requireNonNull( node, "Null vertex not admitted" );
                        graph.addNode( checkedNode );
                        return checkedNode;
                    }

                    @Override
                    public <H extends V> ValueTailConnector<V, E> connect( H head )
                    {
                        final H checkedHead = requireNonNull( head, "Null head vertex not admitted" );
                        return new ValueTailConnector<V, E>()
                        {

                            @Override
                            public <T extends V> EdgeConnector<E> to( T tail )
                            {
                                final T checkedTail = requireNonNull( tail, "Null tail node not admitted" );
                                return new EdgeConnector<E>()
                                {

                                    public <A extends E> void via( A arc )
                                    {
                                        final A checkedArc = requireNonNull( arc, "Null arc not admitted" );
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
