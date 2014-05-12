package universe;

import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.behaviors.mouse.MouseTranslate;
import com.sun.j3d.utils.behaviors.mouse.MouseWheelZoom;
import com.sun.j3d.utils.behaviors.mouse.MouseZoom;
import molecules.Atom;
import molecules.Bound;

import javax.media.j3d.*;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

public class MoleculeBranch extends BranchGroup{
    Atom hydrogen=new Atom(1.,1f,1,.3f,new Color3f(0.5f,0.5f,0.5f));
    TransformGroup transform=new TransformGroup();
    public MoleculeBranch()
    {
        setCapabilities();
//        this.addBound(0);
//        this.addBound(109.5);
//        this.rotate(translateTo(new Bound(1.089f, Bound.SIMPLE_BOUND),0,0.5445f,0),0, 109.5);
        //Note that 109.5 degrees=1.91113553 radians. The function works with radians.
        this.rotate(translateTo(new Bound(1.089f, Bound.SIMPLE_BOUND),0,0.5445f,0),0, 0);
        this.rotate(translateTo(new Bound(1.089f, Bound.SIMPLE_BOUND),0,0.5445f,0),1.91113553, 0);
        this.rotate(translateTo(new Bound(1.089f, Bound.SIMPLE_BOUND),0,0.5445f,0),0, -1.91113553);
        this.rotate(translateTo(new Bound(1.089f, Bound.SIMPLE_BOUND),0,0.5445f,0),1.91113553, 1.91113553);
        this.addAtoms();
        this.addLight();
        this.addRotation();
        this.addZoom();
        this.addMovement();
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
//        if(el.equals("Hydrogen"))
//        {
            transform.addChild(new Atom(1.,1f,1,.3f,new Color3f(0.5f,0.5f,0.5f)).getSphere());
//        }
    }
    public void addAtoms()
    {
        transform.addChild(translateTo(newHydrogen(), 0,(float)Math.sin(1.91113553)*1.089f,0));
//        transform.addChild(translateTo(newHydrogen(), 0,(float)Math.cos(Math.toRadians(109.5))*(float)Math.sin(Math.toRadians(109.5))*1.089f,0));
        transform.addChild(translateTo(newHydrogen(), -(float)Math.sin(1.91113553)*1.089f,0,-0.3338068583535408f*1.089f));
//        transform.addChild(translateTo(newHydrogen(), -(float)Math.cos(1.91113553)*1.089f,0,0));
        transform.addChild(translateTo(newHydrogen(), 0,0,-(float)Math.sin(-1.91113553)*1.089f));
//        transform.addChild(translateTo(newHydrogen(), 0,0,(float)Math.cos(-1.91113553)*1.08   9f));

        transform.addChild(newCarbon());
    }
//    private float
    private Node newHydrogen()
    {
        return new Atom(1.,1f,1,.1f,new Color3f(0.5f,0.5f,0.5f)).getSphere();
    }
    private Node newCarbon()
    {
        return new Atom(1.,1f,1,.3f,new Color3f(0.1f,0.1f,0.1f)).getSphere();
    }
    private void addLight()
    {
        BoundingSphere boundingsphere = new BoundingSphere(new Point3d(0.0f,0.0f,0.0f), 100);
        Color3f lightColor = new Color3f(1.0f, 1.0f, 1.0f);
        Vector3f lightdiection = new Vector3f(-1.0f, -1.0f,-1.0f);
        DirectionalLight light = new DirectionalLight(lightColor, lightdiection);
        light.setInfluencingBounds(boundingsphere);
        this.addChild(light);
    }
    private void addRotation()
    {
        transform.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        transform.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        transform.setCapability(TransformGroup.ALLOW_CHILDREN_EXTEND);
        transform.setCapability(TransformGroup.ALLOW_CHILDREN_READ);
        transform.setCapability(TransformGroup.ALLOW_CHILDREN_WRITE);
        this.addChild(transform);
        MouseRotate myMouseRotate = new MouseRotate();
        myMouseRotate.setTransformGroup(transform);
        myMouseRotate.setSchedulingBounds(new BoundingSphere());
        this.addChild(myMouseRotate);
    }
    private void addZoom()
    {
        MouseWheelZoom myMouseZoom = new MouseWheelZoom();
        myMouseZoom.setTransformGroup(transform);
        myMouseZoom.setSchedulingBounds(new BoundingSphere());
        this.addChild(myMouseZoom);
    }
    private void addMovement()
    {
        MouseTranslate myMouseTranslate = new MouseTranslate();
        myMouseTranslate.setTransformGroup(transform);
        myMouseTranslate.setSchedulingBounds(new BoundingSphere());
        this.addChild(myMouseTranslate);
    }
    private void addBound(double angle)
    {
        Transform3D transform3D=new Transform3D();
        TransformGroup trans=new TransformGroup();
        Transform3D rotation=new Transform3D();
        trans.addChild(new Bound(1, Bound.SIMPLE_BOUND));
        transform3D.setTranslation(new Vector3f(1f, 1f, 1f));
        trans.setTransform(transform3D);
        rotation.rotX(Math.toRadians(angle));
        transform3D.mul(rotation);
        transform3D.setTranslation(new Vector3f(0f, 0f, 0.5f));
        trans.setTransform(transform3D);
        transform.addChild(trans);
    }

    /**
     *
     * @param node the node to be inserted
     * @param xAngle Angle of rotation around the X axis, in radians
     * @param zAngle Angle of rotation around the Z axis, in radians
     */
    void rotate(Node node, double xAngle, double zAngle)
    {
        TransformGroup rotatedGroup = new TransformGroup();
        Transform3D tiltAxisXform = new Transform3D();
        Transform3D tempTiltAxisXform = new Transform3D();
        tiltAxisXform.rotX(xAngle);
        tempTiltAxisXform.rotZ(zAngle);
        tiltAxisXform.mul(tempTiltAxisXform);
        rotatedGroup.setTransform(tiltAxisXform);
        rotatedGroup.addChild(node);
        transform.addChild(rotatedGroup);
        System.out.println(tiltAxisXform);
//        System.out.println(tiltAxisXform);
    }
    TransformGroup translateTo(Node node, float x, float y, float z)
    {
        Transform3D transform3D=new Transform3D();
        TransformGroup trans=new TransformGroup();
        trans.addChild(node);
        transform3D.setTranslation(new Vector3f(x, y, z));
        trans.setTransform(transform3D);
        return trans;
    }
}
