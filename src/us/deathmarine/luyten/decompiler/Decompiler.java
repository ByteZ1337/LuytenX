package us.deathmarine.luyten.decompiler;

import java.util.Arrays;
import java.util.function.Supplier;

public enum Decompiler {
    PROCYON(ProcyonLinkProvider::new),
    CFR(CFRLinkProvider::new),
    Quiltflower(QuiltflowerLinkProvider::new);
    
    public static final Decompiler[] VALUES = values();
    
    public final Supplier<LinkProvider> linkProviderSupplier;
    
    Decompiler(Supplier<LinkProvider> linkProviderSupplier) {
        this.linkProviderSupplier = linkProviderSupplier;
    }
    
    public static Decompiler getDecompiler(final String s) {
        return Arrays.stream(VALUES)
            .filter(decompiler -> decompiler.name().equalsIgnoreCase(s))
            .findFirst().orElse(null);
    }
    
}
