package ch.epfl.imhof;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

public class AttributedTest {

    @Test
    public void valueTest() {
        HashMap<String, String> map = new HashMap<>();
        Attributes attr = new Attributes(map);
        Attributed<String> a = new Attributed<String>("attributed1", attr);
        assertEquals("attributed1", a.value());
    }

    @Test
    public void attributesTest() {
        HashMap<String, String> map = new HashMap<>();
        for (int i = 0; i < 50; i++)
            map.put("key" + i, "value" + i);
        Attributes attr = new Attributes(map);
        Attributed<String> a = new Attributed<String>("attributed1", attr);
        assertEquals(attr, a.attributes());
    }

    @Test
    public void hasAttributesTest() {
        HashMap<String, String> map = new HashMap<>();
        for (int i = 0; i < 50; i++)
            map.put("key" + i, "value" + i);
        Attributes attr = new Attributes(map);
        Attributed<String> a = new Attributed<String>("attributed1", attr);
        for (int i = 0; i < 50; i++)
            assertTrue(a.hasAttribute("key" + i));
        assertFalse(a.hasAttribute("key" + 60));

    }

    @Test
    public void attributeValueTest() {
        HashMap<String, String> map = new HashMap<>();
        for (int i = 0; i < 50; i++)
            map.put("key" + i, "value" + i);
        Attributes attr = new Attributes(map);
        Attributed<String> a = new Attributed<String>("attributed1", attr);
        for (int i = 0; i < 50; i++)
            assertEquals("value" + i, a.attributeValue("key" + i));
        assertEquals(null, a.attributeValue("fdsdfs"));
        assertEquals("ali", a.attributeValue("fdsdfs", "ali"));
        assertEquals("value0", a.attributeValue("key0", "ali"));
    }

    @Test
    public void getIntTest() {
        HashMap<String, String> map = new HashMap<>();
        for (int i = 0; i < 20; i++)
            map.put("key" + i, String.valueOf(i));
        for (int i = 20; i < 50; i++)
            map.put("key" + i, "alio" + i);

        Attributes attr = new Attributes(map);
        Attributed<String> a = new Attributed<String>("attributed1", attr);
        for (int i = 0; i < 20; i++)
            assertEquals(i, a.attributeValue("key" + i, 15));
        
        for (int i = 20; i < 50; i++)
            assertEquals(15, a.attributeValue("key" + i, 15));
        
        assertEquals(a.attributeValue("key60", 15), 15);
    }
}
