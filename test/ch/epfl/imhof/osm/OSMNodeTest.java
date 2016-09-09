package ch.epfl.imhof.osm;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import ch.epfl.imhof.Attributes;
import ch.epfl.imhof.PointGeo;

public class OSMNodeTest {

    @Test
    public void newOSMNODE() {
        PointGeo point = new PointGeo(1.5, Math.PI / 4);
        long id = 25486;
        Attributes.Builder builder = new Attributes.Builder();
        Attributes b = builder.build();
        builder.put("ali", "benlalah");
        builder.put("oussama", "abouzaid");
        builder.put("alami", "idrissi");
        OSMNode node = new OSMNode(id, point, b);
        assertEquals(point, node.position());
        assertEquals(b, node.attributes());
        assertEquals(id, node.id());
    }

    @Test(expected = IllegalStateException.class)
    public void OSMNodeBuilder() {
        PointGeo position = new PointGeo(1.5, Math.PI / 4);
        long id = 25486;
        OSMNode.Builder builder = new OSMNode.Builder(id, position);
        builder.setAttribute("ali", "benlalah");
        builder.setAttribute("oussama", "abouzaid");
        builder.setAttribute("alami", "idrissi");
        builder.setIncomplete();
        OSMNode node = builder.build();
        assertEquals(builder.getAttributes(), node.attributes());
    }

    @Test
    public void OSMNodeBuilder2() {
        PointGeo position = new PointGeo(1.5, Math.PI / 4);
        long id = 25486;
        OSMNode.Builder builder = new OSMNode.Builder(id, position);
        builder.setAttribute("ali", "benlalah");
        builder.setAttribute("oussama", "abouzaid");
        builder.setAttribute("alami", "idrissi");
        OSMNode node = builder.build();
        assertEquals(builder.getAttributes(), node.attributes());
    }

    @Test
    public void equalsTest() {
        PointGeo p1 = new PointGeo(1.5, Math.PI / 4);
        PointGeo p2 = new PointGeo(1.5, Math.PI / 5);
        
        
        Attributes.Builder builder = new Attributes.Builder();
        builder.put("ali", "benlalah");
        builder.put("oussama", "abouzaid");
        builder.put("alami", "idrissi");
        Attributes attr1 = builder.build();
        
        // Builder2
        Attributes.Builder builder2 = new Attributes.Builder();
        builder.put("a", "benlalah");
        builder.put("ma", "abouzaid");
        builder.put("mi", "idrissi");
        Attributes attr2 = builder2.build();
        
        OSMNode a = new OSMNode(12, p1, attr1);
        OSMNode b = new OSMNode(12, p1, attr2);

        assertFalse(a.equals(b));
    }
}
