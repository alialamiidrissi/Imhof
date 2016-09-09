package ch.epfl.imhof.osm;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import ch.epfl.imhof.Attributes;
import ch.epfl.imhof.PointGeo;
import ch.epfl.imhof.osm.OSMRelation.Member;
import ch.epfl.imhof.osm.OSMRelation.Member.Type;

public class OSMRelationTest {

    @Test
    public void ConstructorTest() {
        long id = 2569888;
        List<Member> members = new ArrayList<>();
        Type t = Type.NODE;
        
        PointGeo f = new PointGeo(0.0005, 1.2);
        Attributes.Builder builder = new Attributes.Builder();
        Attributes attr = builder.build();
        builder.put("ali", "benlalah");
        builder.put("oussama", "abouzaid");
        builder.put("alami", "idrissi");
        OSMEntity o = new OSMNode(id, f, attr);
        
        members.add(new Member(t, "highway", o));
        OSMRelation osm = new OSMRelation(id, members, attr);
        assertEquals(members, osm.members());
        assertEquals(attr, osm.attributes());
        assertEquals(id, osm.id());
    }
    
    @Test(expected=IllegalStateException.class)
    public void BuilderTest() {
        long id = 23156;
        OSMRelation.Builder builder = new OSMRelation.Builder(id);
        builder.addMember(Type.NODE, "Highway", new OSMNode(1546, new PointGeo(
                0.0005, 1.2), new Attributes(new HashMap<String, String>())));
        builder.setIncomplete();
        builder.build();
    }
    
    @Test
    public void BuilderTest1() {
        long id = 23156;
        OSMRelation.Builder builder = new OSMRelation.Builder(id);
        builder.addMember(Type.NODE, "Highway", new OSMNode(1546, new PointGeo(
                0.0005, 1.2), new Attributes(new HashMap<String, String>())));
        OSMRelation r = builder.build();
        assertEquals(builder.getAttributes(), r.attributes());
        assertEquals(null, r.attributeValue("dfasd"));
    }
}
