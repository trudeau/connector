connector
=========

EDSL to build Graph instances using fluent APIs.

# Usage

While developing _Trudeau_ APIs, we soon realized that having Collections-alike APIs for populating Graphes forces developers on writing boilerplate and boring code, so rather than having

```
import org.nnsoft.trudeau.inmemory.UndirectedMutableGraph;
import org.nnsoft.trudeau.inmemory.labeled.BaseLabeledVertex;
import org.nnsoft.trudeau.inmemory.labeled.BaseLabeledWeightedEdge;

...

UndirectedMutableGraph<BaseLabeledVertex, BaseLabeledWeightedEdge<Double>> expected =
    new UndirectedMutableGraph<BaseLabeledVertex, BaseLabeledWeightedEdge<Double>>();

BaseLabeledVertex start = new BaseLabeledVertex( "start" );
BaseLabeledVertex a = new BaseLabeledVertex( "a" );
BaseLabeledVertex b = new BaseLabeledVertex( "b" );
BaseLabeledVertex c = new BaseLabeledVertex( "c" );
BaseLabeledVertex d = new BaseLabeledVertex( "d" );
BaseLabeledVertex e = new BaseLabeledVertex( "e" );
BaseLabeledVertex goal = new BaseLabeledVertex( "goal" );

expected.addVertex( start );
expected.addVertex( a );
expected.addVertex( b );
expected.addVertex( c );
expected.addVertex( d );
expected.addVertex( e );
expected.addVertex( goal );

expected.addEdge( start, new BaseLabeledWeightedEdge<Double>( "start <-> a", 1.5D ), a );
expected.addEdge( start, new BaseLabeledWeightedEdge<Double>( "start <-> d", 2D ), d );

expected.addEdge( a, new BaseLabeledWeightedEdge<Double>( "a <-> b", 2D ), b );
expected.addEdge( b, new BaseLabeledWeightedEdge<Double>( "b <-> c", 3D ), c );
expected.addEdge( c, new BaseLabeledWeightedEdge<Double>( "c <-> goal", 3D ), goal );

expected.addEdge( d, new BaseLabeledWeightedEdge<Double>( "d <-> e", 3D ), e );
expected.addEdge( e, new BaseLabeledWeightedEdge<Double>( "e <-> goal", 2D ), goal );
```

using the `connector` module, users can write that same procedure in a more elegant way:

```
import static org.nnsoft.trudeau.connector.GraphPopulator.populate;

import org.nnsoft.trudeau.inmemory.UndirectedMutableGraph;
import org.nnsoft.trudeau.inmemory.labeled.BaseLabeledVertex;
import org.nnsoft.trudeau.inmemory.labeled.BaseLabeledWeightedEdge;

...

UndirectedMutableGraph<BaseLabeledVertex, BaseLabeledWeightedEdge<Double>> actual =
    populate( new UndirectedMutableGraph<BaseLabeledVertex, BaseLabeledWeightedEdge<Double>>() )
    .withConnections( new AbstractGraphConnection<BaseLabeledVertex, BaseLabeledWeightedEdge<Double>>()
{

    public void connect()
    {
        BaseLabeledVertex start = addVertex( new BaseLabeledVertex( "start" ) );
        BaseLabeledVertex a = addVertex( new BaseLabeledVertex( "a" ) );
        BaseLabeledVertex b = addVertex( new BaseLabeledVertex( "b" ) );
        BaseLabeledVertex c = addVertex( new BaseLabeledVertex( "c" ) );
        BaseLabeledVertex d = addVertex( new BaseLabeledVertex( "d" ) );
        BaseLabeledVertex e = addVertex( new BaseLabeledVertex( "e" ) );
        BaseLabeledVertex goal = addVertex( new BaseLabeledVertex( "goal" ) );

        addEdge( new BaseLabeledWeightedEdge<Double>( "start <-> a", 1.5D ) ).from( start ).to( a );
        addEdge( new BaseLabeledWeightedEdge<Double>( "start <-> d", 2D ) ).from( start ).to( d );

        addEdge( new BaseLabeledWeightedEdge<Double>( "a <-> b", 2D ) ).from( a ).to( b );
        addEdge( new BaseLabeledWeightedEdge<Double>( "b <-> c", 3D ) ).from( b ).to( c );
        addEdge( new BaseLabeledWeightedEdge<Double>( "c <-> goal", 3D ) ).from( c ).to( goal );

        addEdge( new BaseLabeledWeightedEdge<Double>( "d <-> e", 3D ) ).from( d ).to( e );
        addEdge( new BaseLabeledWeightedEdge<Double>( "e <-> goal", 2D ) ).from( e ).to( goal );
    }

} );
```