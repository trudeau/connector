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

import static org.junit.Assert.assertEquals;
import static org.nnsoft.trudeau.connector.GraphConnector.populate;

import org.junit.Test;

import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;
import com.google.common.graph.MutableValueGraph;
import com.google.common.graph.ValueGraphBuilder;

public final class GraphConnectorTestCase
{

    @Test
    public void verifyMutableGraphesAreEquals()
    {
        final MutableGraph<String> expected = GraphBuilder.undirected().build();

        // building Graph with traditional APIs...

        String start = "start";
        String a = "a";
        String b = "b";
        String c = "c";
        String d = "d";
        String e = "e";
        String goal = "goal";

        expected.addNode( start );
        expected.addNode( a );
        expected.addNode( b );
        expected.addNode( c );
        expected.addNode( d );
        expected.addNode( e );
        expected.addNode( goal );

        expected.putEdge( start, a );
        expected.putEdge( start, d );

        expected.putEdge( a, b );
        expected.putEdge( b, c );
        expected.putEdge( c, goal );

        expected.putEdge( d, e );
        expected.putEdge( e, goal );

        // ... and using the EDSL :)

        final MutableGraph<String> actual = GraphBuilder.undirected().build();

        populate( actual ).withConnections( new AbstractMutableGraphConnection<String>()
        {

            public void connect()
            {
                String start = addNode( "start" );
                String a = addNode( "a" );
                String b = addNode( "b" );
                String c = addNode( "c" );
                String d = addNode( "d" );
                String e = addNode( "e" );
                String goal = addNode( "goal" );

                connect( start ).to( a );
                connect( start ).to( d );

                connect( a ).to( b );
                connect( b ).to( c );
                connect( c ).to( goal );

                connect( d ).to( e );
                connect( e ).to( goal );
            }

        } );

        assertEquals( expected, actual );
    }

    @Test
    public void verifyMutableValueGraphesAreEquals()
    {
        final MutableValueGraph<String, String> expected = ValueGraphBuilder.undirected().build();

        // building Graph with traditional APIs...

        String start = "start";
        String a = "a";
        String b = "b";
        String c = "c";
        String d = "d";
        String e = "e";
        String goal = "goal";

        expected.addNode( start );
        expected.addNode( a );
        expected.addNode( b );
        expected.addNode( c );
        expected.addNode( d );
        expected.addNode( e );
        expected.addNode( goal );

        expected.putEdgeValue( start, a, "start <-> a" );
        expected.putEdgeValue( start, d, "start <-> d" );

        expected.putEdgeValue( a, b, "a <-> b" );
        expected.putEdgeValue( b, c, "b <-> c" );
        expected.putEdgeValue( c, goal, "c <-> goal" );

        expected.putEdgeValue( d, e, "d <-> e" );
        expected.putEdgeValue( e, goal, "e <-> goal" );

        // ... and using the EDSL :)

        final MutableValueGraph<String, String> actual = ValueGraphBuilder.undirected().build();

        populate( actual ).withConnections( new AbstractMutableValueGraphConnection<String, String>()
        {

            public void connect()
            {
                String start = addNode( "start" );
                String a = addNode( "a" );
                String b = addNode( "b" );
                String c = addNode( "c" );
                String d = addNode( "d" );
                String e = addNode( "e" );
                String goal = addNode( "goal" );

                putEdgeValue( "start <-> a" ).from( start ).to( a );
                putEdgeValue( "start <-> d" ).from( start ).to( d );

                putEdgeValue( "a <-> b" ).from( a ).to( b );
                putEdgeValue( "b <-> c" ).from( b ).to( c );
                putEdgeValue( "c <-> goal" ).from( c ).to( goal );

                putEdgeValue( "d <-> e" ).from( d ).to( e );
                putEdgeValue( "e <-> goal" ).from( e ).to( goal );
            }

        } );

        assertEquals( expected, actual );
    }

}
