package codes.msr.verbapotentia;

import codes.msr.verbapotentia.server.handler.MessageHandler;
import net.fabricmc.api.ModInitializer;

public class VerbaPotentia implements ModInitializer {

	@Override
	public void onInitialize() {
        MessageHandler.init();
	}

}
