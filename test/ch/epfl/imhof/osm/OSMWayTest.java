package ch.epfl.imhof.osm;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import ch.epfl.imhof.Attributes;
import ch.epfl.imhof.PointGeo;

public class OSMWayTest {

    @Test(expected = IllegalArgumentException.class)
    public void ConstructorTest() {
        long id = 16421;
        List<OSMNode> nodes = new ArrayList<>();

        PointGeo f = new PointGeo(0.0005, 1.2);
        Attributes.Builder builder = new Attributes.Builder();
        Attributes attr = builder.build();
        builder.put("ali", "benlalah");
        builder.put("oussama", "abouzaid");
        builder.put("alami", "idrissi");
        OSMNode o = new OSMNode(id, f, attr);

        nodes.add(o);
        OSMWay obj = new OSMWay(id, nodes, attr);
    }

    @Test
    public void nonRepeatingNodesTest() {
        long id = 16421;
        PointGeo f = new PointGeo(0.0005, 1.2);

        // Builder1
        Attributes.Builder builder = new Attributes.Builder();
        builder.put("ali", "benlalah");
        builder.put("oussama", "abouzaid");
        builder.put("alami", "idrissi");
        Attributes attr = builder.build();
        
        // Builder2
        Attributes.Builder builder2 = new Attributes.Builder();
        builder.put("a", "benlalah");
        builder.put("ma", "abouzaid");
        builder.put("mi", "idrissi");
        Attributes attr2 = builder2.build();
        
        //Liste de noeuds
        List<OSMNode> nodes = new ArrayList<>();
        List<OSMNode> nodes1 = new ArrayList<>();
        
        //Objets a ajouter
        OSMNode o = new OSMNode(id, f, attr);
        OSMNode o1 = new OSMNode(id, f, attr2);
        
        //Ajoutd'objets
        nodes.add(o);
        nodes.add(o1);
        nodes.add(o);
        
        nodes1.add(o);
        nodes1.add(o1);
        
        
        // OSMNodes
        OSMWay obj = new OSMWay(id, nodes, attr);
        List<OSMNode> nodes2 = obj.nonRepeatingNodes();
        
        
        assertTrue(obj.isClosed());
       
        OSMWay obj2 = new OSMWay(id, nodes2, attr);
        assertEquals(o, obj2.firstNode());
        assertEquals(o1, obj2.lastNode());
        
        assertFalse(obj2.isClosed());
        
        for (int i = 0; i < nodes2.size(); i++) {
            assertEquals(nodes1.get(i), nodes2.get(i));
        }
    }
    
    @Test
    public void builderTest() {
        OSMWay.Builder builder = new OSMWay.Builder(258845);
        // Builder1
        Attributes.Builder builder1 = new Attributes.Builder();
        builder1.put("ali", "benlalah");
        builder1.put("oussama", "abouzaid");
        builder1.put("alami", "idrissi");
        Attributes attr1 = builder1.build();

        // id + pt géo
        PointGeo f = new PointGeo(0.0005, 1.2);
        long id = 16421;
        
        // Builder2
        Attributes.Builder builder2 = new Attributes.Builder();
        builder2.put("a", "df");
        builder2.put("ma", "dfdd");
        builder2.put("mi", "idrissi");
        Attributes attr2 = builder2.build();

        // Objets a ajouter
        OSMNode o = new OSMNode(id, f, attr1);
        OSMNode o1 = new OSMNode(id, f, attr2);
        
        builder.addNode(o);
        builder.addNode(o1);
        OSMWay a = builder.build();
        
        List<OSMNode> nodesC = a.nodes();
        List<OSMNode> nodes = new ArrayList<>();
        print(nodesC.size());
        print(nodes.size());
        
    }

    @Test(expected = IllegalStateException.class)
    public void buildTest() {
        OSMWay.Builder builder = new OSMWay.Builder(258845);
        // Builder1
        Attributes.Builder builder1 = new Attributes.Builder();
        builder1.put("ali", "benlalah");
        builder1.put("oussama", "abouzaid");
        builder1.put("alami", "idrissi");
        Attributes attr = builder1.build();

        // id + pt géo
        PointGeo f = new PointGeo(0.0005, 1.2);
        long id = 16421;

        // Objets a ajouter
        OSMNode o = new OSMNode(id, f, attr);
        builder.addNode(o);
        builder.build();

    }

    @Test(expected = IllegalStateException.class)
    public void buildTest2() {
        OSMWay.Builder builder = new OSMWay.Builder(258845);
        // Builder1
        Attributes.Builder builder1 = new Attributes.Builder();
        builder1.put("ali", "benlalah");
        builder1.put("oussama", "abouzaid");
        builder1.put("alami", "idrissi");
        Attributes attr = builder1.build();

        // Builder2
        Attributes.Builder builder2 = new Attributes.Builder();
        builder2.put("a", "df");
        builder2.put("ma", "dfdd");
        builder2.put("mi", "idrissi");
        Attributes attr2 = builder2.build();

        // id + pt géo
        PointGeo f = new PointGeo(0.0005, 1.2);
        long id = 16421;

        // Objets a ajouter
        OSMNode o = new OSMNode(id, f, attr);
        OSMNode o1 = new OSMNode(id, f, attr2);
        builder.addNode(o);
        builder.addNode(o1);
        builder.setIncomplete();
        builder.build();

    }
    public void print(Object o){
        System.out.println(o);
    }

}
