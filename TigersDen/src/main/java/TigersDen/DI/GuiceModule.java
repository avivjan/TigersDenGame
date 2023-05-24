package TigersDen.DI;

import com.google.inject.AbstractModule;

import TigersDen.A;
import TigersDen.B;
import TigersDen.IA;
import TigersDen.IB;
import TigersDen.DAL.BussinessLogic.GenericAccessor;
import TigersDen.DAL.Contract.IGenericAccessor;

public class GuiceModule extends AbstractModule {
    @Override
    protected void configure() {
        // Bind your classes/interfaces here
        bind(IGenericAccessor.class).to(GenericAccessor.class);
    }
}
