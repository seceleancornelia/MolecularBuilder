package molecules;

import com.sun.j3d.utils.geometry.Cylinder;
import javax.media.j3d.Appearance;
import javax.media.j3d.Group;
import javax.media.j3d.Material;
import javax.vecmath.Color3f;

public class Bound extends Group {
    private static final float RADIUS=0.05f;
    public static final int SIMPLE_BOUND=1;
    public static final int DOUBLE_BOUND=2;
    public static final int TRIPLE_BOUND=3;
    float length;
    Appearance appearance;
    int type;
    public Bound(float length, int type)
    {
        this.length=length;
        this.appearance=new Appearance();
        this.appearance.setMaterial(new Material(new Color3f(0.5f, 0.5f, 0.5f), new Color3f(0.5f,0.5f,0.5f), new Color3f(0.5f,0.5f,0.5f), new Color3f(0.5f,0.5f,0.5f), 10));
        this.type=type;
        addToGroup(type);
    }
    private void addToGroup(int type)
    {
        for (int i=0;i<=type;i++)
            this.addChild(new Cylinder(RADIUS,length,appearance));
    }
}

