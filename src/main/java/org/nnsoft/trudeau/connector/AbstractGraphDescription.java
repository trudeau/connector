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

/**
 * TODO fill me!!
 *
 * @param <V> the Graph vertices type
 */
public abstract class AbstractGraphDescription<V>
    implements GraphDescription<V>
{

    private Grapher<V> grapher;

    /**
     * {@inheritDoc}
     */
    public final void describe( Grapher<V> connector )
    {
        if ( this.grapher != null )
        {
            throw new IllegalStateException( "Re-entry not allowed!" );
        }
        this.grapher = connector;

        try
        {
            describe();
        }
        finally
        {
            this.grapher = null;
        }
    }

    /**
     * Adds a new vertex to graph connector.
     *
     * @param <N> the Graph vertex type
     * @param node the vertex to add
     * @return the vertex added
     */
    protected final <N extends V> N addNode( N node )
    {
        return grapher.addNode( node );
    }

    protected final <N extends V> TailConnector<V> connect( N node )
    {
        return grapher.connect( node );
    }

    /**
     * Connects the graph.
     */
    public abstract void describe();

}
