package us.deathmarine.luyten.decompiler;

import com.strobel.assembler.metadata.TypeDefinition;
import us.deathmarine.luyten.Model;

import java.util.Map;
import java.util.Set;

public interface LinkProvider {
    
    void generateContent();
    
    String getTextContent();
    
    void processLinks();
    
    Map<String, Selection> getDefinitionToSelectionMap();
    
    Map<String, Set<Selection>> getReferenceToSelectionsMap();
    
    boolean isLinkNavigable(String uniqueStr);
    
    String getLinkDescription(String uniqueStr);
    
    void setType(TypeDefinition type, Model model);
    
}
