package ch.epfl.imhof;

import static org.junit.Assert.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;

public class AttributesTest {

    @Test
    public void isEmptyTest() {
        HashMap<String, String> map = new HashMap<>();
        map.put("key0", "value");
        Attributes attr = new Attributes(map);
        assertFalse(attr.isEmpty());
    }

    @Test
    public void isEmpty1() {
        HashMap<String, String> map = new HashMap<>();
        Attributes attr = new Attributes(map);
        assertTrue(attr.isEmpty());
    }

    @Test
    public void containsTest() {
        HashMap<String, String> map = new HashMap<>();
        for (int i = 0; i < 50; i++)
            map.put("key" + i, "value" + i);
        Attributes attr = new Attributes(map);
        assertFalse(attr.contains("key100"));
        for (int i = 0; i < 50; i++)
            assertTrue(attr.contains("key" + i));
    }

    @Test
    public void getTest() {
        HashMap<String, String> map = new HashMap<>();
        for (int i = 0; i < 50; i++)
            map.put("key" + i, "value" + i);
        Attributes attr = new Attributes(map);
        for (int i = 0; i < 50; i++)
            assertEquals("value" + i, attr.get("key" + i));
        assertEquals(null, attr.get("fdsdfs"));
        assertEquals("ali", attr.get("fdsdfs", "ali"));
        assertEquals("value0",attr.get("key0", "ali"));
    }

    @Test
    public void getIntTest() {
        HashMap<String, String> map = new HashMap<>();
        for (int i = 0; i < 20; i++)
            map.put("key" + i, String.valueOf(i));
        for (int i = 20; i < 50; i++)
            map.put("key" + i, "alio" + i);

        Attributes attr = new Attributes(map);
        for (int i = 0; i < 20; i++)
            assertEquals( i, attr.get("key" + i, 15));
        for (int i = 20; i < 50; i++)
            assertEquals( 15, attr.get("key" + i, 15));
        assertEquals( 15, attr.get("key60", 15));
    }

    @Test
    public void keepOnlyKeys() {
        HashMap<String, String> map = new HashMap<>();
        for (int i = 0; i < 20; i++)
            map.put("key" + i, String.valueOf(i));
        for (int i = 20; i < 50; i++)
            map.put("key" + i, "alio" + i);

        Attributes attr = new Attributes(map);
        Set<String> map1 = new HashSet<>();
        for (int i = 20; i < 50; i++)
            map1.add("key" + i);

        Attributes a = attr.keepOnlyKeys(map1);
        for (int i = 0; i < 20; i++)
            assertFalse(a.contains("key" + i));
        for (int i = 20; i < 50; i++)
            assertTrue(a.contains("key" + i));
    }

    @Test
    public void builderTest() {
        Attributes.Builder a = new Attributes.Builder();
        for (int i = 0; i < 5; i++)
            a.put("key" + i, "value" + i);
        Attributes a1 = a.build();
        for (int i = 0; i < 5; i++)
            assertEquals("value" + i, a1.get("key" + i));
    }
    
    @Test
    public void builderEmptyTest() {
        Attributes.Builder a = new Attributes.Builder();
        Attributes a1 = a.build();
        assertTrue(a1.isEmpty());
    }
    
    @Test
    public void equalsTest(){
        Attributes.Builder a = new Attributes.Builder();
        for (int i = 0; i < 15; i++)
            a.put("key" + i, "value" + i);
        Attributes a1 = a.build();
        
        Attributes.Builder b = new Attributes.Builder();
        for (int i = 0; i < 5; i++)
            b.put("key" + i, "value" + i);
        Attributes b1 = b.build();
        
        assertFalse(b1.equals(a1));
    }
}
