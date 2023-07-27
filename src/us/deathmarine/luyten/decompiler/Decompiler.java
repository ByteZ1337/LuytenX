package us.deathmarine.luyten.decompiler;

import java.util.Arrays;
import java.util.function.Supplier;

public enum Decompiler {
    PROCYON("Procyon", ProcyonLinkProvider::new),
    CFR("CFR", CFRLinkProvider::new),
    Vineflower("Vineflower", VineflowerLinkProvider::new),
    KOTLINP("Kotlinp", KotlinpLinkProvider::new);
    
    public static final Decompiler[] VALUES = values();
    
    private final String properName;
    public final Supplier<LinkProvider> linkProviderSupplier;
    
    Decompiler(String properName, Supplier<LinkProvider> linkProviderSupplier) {
        this.properName = properName;
        this.linkProviderSupplier = linkProviderSupplier;
    }
    
    public String getProperName() {
        return properName;
    }
    
    public static Decompiler getDecompiler(final String s) {
        return Arrays.stream(VALUES)
            .filter(decompiler -> decompiler.name().equalsIgnoreCase(s))
            .findFirst().orElse(null);
    }
    
}
