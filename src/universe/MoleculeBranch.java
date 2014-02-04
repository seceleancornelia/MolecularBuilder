package universe;

import molecules.Atom;
import molecules.Bound;

import javax.media.j3d.AmbientLight;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.DirectionalLight;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

public class MoleculeBranch extends BranchGroup{
    Atom hydrogen=new Atom(1.,1f,1,.3f,new Color3f(0.5f,0.5f,0.5f));
    Bound bound=new Bound(1,Bound.SIMPLE_BOUND);
    public MoleculeBranch()
    {
        setCapabilities();
        this.addChild(bound);
        this.addLight();
    }
    private void setCapabilities()
    {
        this.setCapability(BranchGroup.ALLOW_BOUNDS_READ);
        this.setCapability(BranchGroup.ALLOW_BOUNDS_WRITE);
        this.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
        this.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
        this.setCapability(BranchGroup.ALLOW_DETACH);
    }
    public void addElement(String el)
    {
        if(el.equals("Hydrogen"))
            this.addChild(hydrogen.getSphere());
    }
    private void addLight ()
    {
        BoundingSphere boundingsphere = new BoundingSphere(new Point3d(0.0f,0.0f,0.0f), 100);
        Color3f lightColor = new Color3f(1.0f, 1.0f, 1.0f);
        Vector3f lightdiection = new Vector3f(-1.0f, -1.0f,-1.0f);
        DirectionalLight light = new DirectionalLight(lightColor, lightdiection);
        light.setInfluencingBounds(boundingsphere);
        this.addChild(light);
    }
}
