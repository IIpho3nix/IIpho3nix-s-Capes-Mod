package me.iipho3nix.iicapemod.mixin;

import me.iipho3nix.iicapemod.CapesMod;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.util.SkinTextures;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractClientPlayerEntity.class)
@Environment(EnvType.CLIENT)
public class AbstractClientPlayerEntityMixin {
    @Inject(at = @At("RETURN"), method = "getSkinTextures", cancellable = true)
    private void getSkinTextures(CallbackInfoReturnable<SkinTextures> cir) {
        if (((AbstractClientPlayerEntity) (Object) this).getUuid().equals(MinecraftClient.getInstance().player.getUuid()) && CapesMod.capeCacheIdentifier != null && !FabricLoader.getInstance().isModLoaded("skinshuffle")) {
            SkinTextures textures = cir.getReturnValue();
            cir.setReturnValue(new SkinTextures(
                    textures.texture(),
                    textures.textureUrl(),
                    CapesMod.capeCacheIdentifier,
                    textures.elytraTexture(),
                    textures.model(),
                    textures.secure()
            ));
        }
    }
}