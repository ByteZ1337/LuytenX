package us.deathmarine.luyten.decompiler;

import com.strobel.assembler.metadata.TypeDefinition;
import org.benf.cfr.reader.api.CfrDriver;
import org.benf.cfr.reader.api.OutputSinkFactory;
import org.benf.cfr.reader.api.SinkReturns.Decompiled;
import org.benf.cfr.reader.bytecode.analysis.parse.utils.Pair;
import org.benf.cfr.reader.state.ClassFileSourceImpl;
import org.benf.cfr.reader.util.getopt.Options;
import org.benf.cfr.reader.util.getopt.OptionsImpl;
import us.deathmarine.luyten.Model;
import us.deathmarine.luyten.util.ProcyonUtils;

import java.io.IOException;
import java.util.*;
import java.util.function.Consumer;

public class CFRLinkProvider implements LinkProvider {
    
    private static final Options OPTIONS = new OptionsImpl(Collections.emptyMap());
    
    private TypeDefinition type;
    private RuntimeClassFileSource classFileSource;
    
    private String content;
    
    @Override
    public String getTextContent() {
        return content;
    }
    
    @Override
    public void setType(TypeDefinition type, Model model) {
        this.type = type;
        classFileSource = new RuntimeClassFileSource(OPTIONS, ProcyonUtils.getContent(type, model), type.getInternalName() + ".class");
    }
    
    @Override
    public void generateContent() {
        
        CfrDriver driver = new CfrDriver.Builder()
            .withClassFileSource(classFileSource)
            .withBuiltOptions(OPTIONS)
            .withOutputSink(new DecompilerOutputSinkFactory(dec -> content = dec))
            .build();
        driver.analyse(Collections.singletonList(type.getInternalName()));
    }
    
    private static class DecompilerOutputSinkFactory implements OutputSinkFactory {
        
        private Consumer<String> decompiledConsumer;
        
        public DecompilerOutputSinkFactory(Consumer<String> decompiledConsumer) {
            this.decompiledConsumer = decompiledConsumer;
        }
        
        @Override
        public List<SinkClass> getSupportedSinks(SinkType sinkType, Collection<SinkClass> collection) {
            return Collections.singletonList(SinkClass.DECOMPILED);
        }
        
        @Override
        public <T> Sink<T> getSink(SinkType sinkType, SinkClass sinkClass) {
            if (sinkType == SinkType.JAVA && sinkClass == SinkClass.DECOMPILED) {
                return res -> decompiledConsumer.accept(((Decompiled) res).getJava());
            }
            
            // Anything that isn't the finished decompiled java code can be ignored.
            return res -> {
            };
        }
        
    }
    
    private static class RuntimeClassFileSource extends ClassFileSourceImpl {
        
        private final byte[] content;
        
        private final String path;
        
        public RuntimeClassFileSource(Options options, byte[] content, String path) {
            super(options);
            this.content = content;
            this.path = path;
        }
        
        @Override
        public Pair<byte[], String> getClassFileContent(String path) throws IOException {
            if (path.equals(this.path))
                return Pair.make(content, path);
            return super.getClassFileContent(path);
        }
        
    }
    
    @Override
    public void processLinks() {
        // TODO
    }
    
    @Override
    public Map<String, Selection> getDefinitionToSelectionMap() {
        // TODO
        return Collections.emptyMap();
    }
    
    @Override
    public Map<String, Set<Selection>> getReferenceToSelectionsMap() {
        // TODO
        return Collections.emptyMap();
    }
    
    @Override
    public boolean isLinkNavigable(String uniqueStr) {
        return false;
    }
    
    @Override
    public String getLinkDescription(String uniqueStr) {
        return "";
    }
}
