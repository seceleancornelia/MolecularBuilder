package molecules;

import junit.framework.TestCase;
import org.junit.Test;

import javax.vecmath.Color3f;

public class AtomTest extends TestCase {
    Atom hydrogen=new Atom(1d,1f,1,.53f,new Color3f(1f,1f,1f));
    @Test
    public void testGetWeight() throws Exception {

        assertEquals("Is weight of hydrogen 1?", 1.0, hydrogen.getWeight());
    }

    @Test
    public void testGetElectronegativity() throws Exception {

    }

    @Test
    public void testAttachAtom() throws Exception {

    }
}
