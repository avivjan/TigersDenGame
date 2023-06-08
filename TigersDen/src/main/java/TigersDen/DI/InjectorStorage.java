package TigersDen.DI;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class InjectorStorage {
    private static Injector injector;

    public static Injector getInjector() {
        return injector;
    }

    public static void setInjector(Injector injector) {
        InjectorStorage.injector = injector;
    }
}
