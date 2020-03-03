package codes.msr.verbapotentia.server.handler;

import codes.msr.verbapotentia.server.event.ServerMessageCallback;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;

public class MessageHandler {

    public static void init() {
        ServerMessageCallback.EVENT.register(MessageHandler::onMessage);
    }

    private static ActionResult onMessage(ServerPlayerEntity player, String message) {
        if (message.equals("yolo")) {
            System.out.println(player.getName().asString() + " said a bad word.");
            return ActionResult.FAIL;
        }

        System.out.println(player.getDisplayName().asString() + " said " + message);

        return ActionResult.SUCCESS;
    }

}
