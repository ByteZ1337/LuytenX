package us.deathmarine.luyten.decompiler;

import com.strobel.assembler.metadata.TypeDefinition;
import org.jetbrains.kotlin.kotlinp.KotlinpRunner;
import us.deathmarine.luyten.Model;
import us.deathmarine.luyten.util.ProcyonUtils;

import java.io.ByteArrayInputStream;
import java.util.Map;
import java.util.Set;

public class KotlinpLinkProvider implements LinkProvider {
    
    private static final String[] ARGS = {"-verbose"};
    
    private String content;
    
    @Override
    public String getTextContent() {
        return content;
    }
    
    @Override
    public void setType(TypeDefinition type, Model model) {
        var bytes = ProcyonUtils.getContent(type, model);
        content = KotlinpRunner.INSTANCE.run(ARGS, new ByteArrayInputStream(bytes), type.getInternalName() + ".class");
    }
    
    @Override
    public void generateContent() {
    
    }
    
    @Override
    public void processLinks() {
    
    }
    
    @Override
    public Map<String, Selection> getDefinitionToSelectionMap() {
        return null;
    }
    
    @Override
    public Map<String, Set<Selection>> getReferenceToSelectionsMap() {
        return null;
    }
    
    @Override
    public boolean isLinkNavigable(String uniqueStr) {
        return false;
    }
    
    @Override
    public String getLinkDescription(String uniqueStr) {
        return null;
    }
}
