package org.nnsoft.trudeau.connector;

/**
 * TODO fill me!!
 *
 * @param <V> the Graph vertices type
 * @param <E> the Graph edges type
 */
public abstract class AbstractValueGraphDescription<V, E>
    implements ValueGraphDescription<V, E>
{

    private ValueGrapher<V, E> grapher;

    /**
     * {@inheritDoc}
     */
    public final void describe( ValueGrapher<V, E> grapher )
    {
        if ( this.grapher != null )
        {
            throw new IllegalStateException( "Re-entry not allowed!" );
        }
        
        this.grapher = grapher;

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

    protected final <N extends V> ValueTailConnector<V, E> connect( N node )
    {
        return grapher.connect( node );
    }

    

    /**
     * Connects the graph.
     */
    public abstract void describe();

}
