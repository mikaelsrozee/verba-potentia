package codes.msr.verbapotentia;

import codes.msr.verbapotentia.server.event.ServerMessageCallback;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.ActionResult;

public class VerbaPotentia implements ModInitializer {
	@Override
	public void onInitialize() {
        ServerMessageCallback.EVENT.register((player, message) -> {

            if (message.equals("yolo")) {
                System.out.println(player.getName().asString() + " said a bad word.");
                return ActionResult.FAIL;
            }

            System.out.println(player.getDisplayName().asString() + " said " + message);

            return ActionResult.SUCCESS;
        });

	}
}
