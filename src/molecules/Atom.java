package molecules;

import com.sun.j3d.utils.geometry.Sphere;

import javax.media.j3d.Appearance;
import javax.media.j3d.Material;
import javax.vecmath.Color3f;
import java.awt.*;

public class Atom {
    /**
     *
     */
    protected double weight;
    /**
     * The Pauling electronegativity
     */
    float electronegativity;
    int valence;
    private int newvalence;
    float radius;
    Sphere sphere;
    Appearance appearence;
//    protected boolean radioactive;
    public Atom()
    {}
    /**
     *
     * @param weight the standard atomic weight
     * @param electronegativity the Pauling electronegativity
     * @param valence the valence of the Atom
     * @param radius the radius of the Atom
     * @param emissiveColor the color of the Atom
     */
    public Atom(double weight, float electronegativity, int valence, float radius,Color3f emissiveColor)
    {
        this.weight=weight;
        this.electronegativity=electronegativity;
        this.valence=valence;
        this.newvalence=valence;
        this.radius=radius;
        this.appearence=new Appearance();
        this.appearence.setMaterial(new Material(new Color3f(1f, 1f, 1f),emissiveColor, new Color3f(1.0f, 1f, 1.0f), new Color3f(0, 0, 0), 64));
        sphere=new Sphere(radius,1,4000);
        sphere.setAppearance(appearence);
    }
    public Atom(double weight, float electronegativity, int valence, float radius, Appearance appearence)
    {
        this.weight=weight;
        this.electronegativity=electronegativity;
        this.valence=valence;
        this.newvalence=valence;
        this.radius=radius;
        this.appearence=appearence;
    }
    public Atom(double weight, float electronegativity, int valence, float radius, Material material)
    {
        this.weight=weight;
        this.electronegativity=electronegativity;
        this.valence=valence;
        this.newvalence=valence;
        this.radius=radius;
        this.appearence=new Appearance();
        this.appearence.setMaterial(material);
    }
    public Atom(double weight, float electronegativity, int valence, float radius,
                Color3f ambientColor, Color3f emissiveColor, Color3f diffuseColor, Color3f specularColor, float shininess)
    {
        this.weight=weight;
        this.electronegativity=electronegativity;
        this.valence=valence;
        this.newvalence=valence;
        this.radius=radius;
        this.appearence=new Appearance();
        this.appearence.setMaterial(new Material(ambientColor,emissiveColor,diffuseColor,specularColor,shininess));
    }
    public double getWeight()
    {
        return weight;
    }
    public float getElectronegativity()
    {
        return electronegativity;
    }
    public Sphere getSphere()
    {
        return sphere;
    }
    /**
     *
     * @param valence of the atom that is attached
     * @return if it can be attached
     */
    //TODO: this is a very fucking basic level of verifying if two atoms are compatible, thing which should be studied.
    //Todo: this should work until then, fuck it. whatever
    public boolean attachAtom(int valence)
    {
        if(this.newvalence-valence>0)
        {
            this.newvalence-=valence;
            return true;
        }
        return false;
    }
}
