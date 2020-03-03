package codes.msr.verbapotentia.mixin;

import codes.msr.verbapotentia.server.event.ServerMessageCallback;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(ServerPlayNetworkHandler.class)
public class ServerMessageMixin {

    @Shadow
    public ServerPlayerEntity player;

    @Inject(
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/PlayerManager;broadcastChatMessage(Lnet/minecraft/text/Text;Z)V"
            ),
            method = "onChatMessage",
            cancellable = true,
            locals = LocalCapture.CAPTURE_FAILSOFT
    )
    private void onMessage(final ChatMessageC2SPacket packet, final CallbackInfo info, String message, Text text) {
        ActionResult result = ServerMessageCallback.EVENT.invoker().message(player, message);
        if (result == ActionResult.FAIL) {
            info.cancel();
        }
    }

}
