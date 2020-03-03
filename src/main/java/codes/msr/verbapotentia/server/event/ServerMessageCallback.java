package codes.msr.verbapotentia.server.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;

/**
 * Callback for when the server receives a chat message
 * Called before the message is broadcast
 * Upon return:
 *  - SUCCESS cancels further processing and continues with normal message behaviour
 *  - PASS falls back to default processing and defaults to SUCCESS if no other listeners are available
 *  - FAIL cancels further processing and does not send the message
 */
public interface ServerMessageCallback {

    Event<ServerMessageCallback> EVENT = EventFactory.createArrayBacked(ServerMessageCallback.class,
            (listeners) -> (player, message) -> {
                for (ServerMessageCallback event : listeners) {
                    ActionResult result = event.message(player, message);
                    if (result != ActionResult.PASS) {
                        return result;
                    }
                }

                return ActionResult.PASS;
            });

    ActionResult message(ServerPlayerEntity player, String message);

}
