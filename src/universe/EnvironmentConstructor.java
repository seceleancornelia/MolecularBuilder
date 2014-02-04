package universe;

import com.sun.j3d.utils.universe.SimpleUniverse;
import javax.media.j3d.Canvas3D;

public class EnvironmentConstructor extends SimpleUniverse{
    MoleculeBranch moleculeBranch= new MoleculeBranch();
    public EnvironmentConstructor(){
        attachBranch();
        this.getViewingPlatform().setNominalViewingTransform();
    }
    public EnvironmentConstructor(Canvas3D canvas){
        super(canvas);
        attachBranch();
        /**
         * Sets the view towards the elements
         */
        this.getViewingPlatform().setNominalViewingTransform();
    }
    private void addNewElementBranch(String type)
    {
        if(type.equals("Hydrogen"))
            moleculeBranch.addElement("Hydrogen");
    }
    private void attachBranch(){
        /**
         * Adds the branch to the universe
         */
        this.getLocale().addBranchGraph(moleculeBranch);
    }
    private void detachBranch(){
        /**
         * Removes the branch from the universe
         */
        this.getLocale().removeBranchGraph(moleculeBranch);
    }
    public void addnewElement(String type)
    {
        detachBranch();
        addNewElementBranch(type);
        attachBranch();
    }

}
