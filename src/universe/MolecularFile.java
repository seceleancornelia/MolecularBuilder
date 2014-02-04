package universe;
//import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.JsonParseException;
import org.codehaus.jackson.map.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class MolecularFile extends File {
    String pathname;
    public MolecularFile(java.lang.String pathname)
    {
        super(pathname);
        this.pathname=pathname;
    }
    public HashMap<String,Object> fileToHashMap()
    {
        HashMap<String,Object> result=null;
        try{
            result = new ObjectMapper().readValue(this, HashMap.class);
        }
        catch (JsonParseException e)
        {
            System.err.println(e.getMessage());
        }
        catch( IOException e)
        {
            System.err.println(e.getMessage());
        }
//        catch()
        return result;
    }
}
