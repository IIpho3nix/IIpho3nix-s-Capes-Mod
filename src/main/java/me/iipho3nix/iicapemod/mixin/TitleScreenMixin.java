package me.iipho3nix.iicapemod.mixin;

import me.iipho3nix.iicapemod.CapesMod;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
@Environment(EnvType.CLIENT)
public class TitleScreenMixin extends Screen {
    protected TitleScreenMixin(Text title) {
        super(title);
    }

    @Inject(at = @At("TAIL"), method = "init")
    private void init(CallbackInfo ci) {
            this.addDrawableChild(ButtonWidget.builder(Text.literal("Change Cape"), (button) -> {
                if (CapesMod.list.indexOf(CapesMod.cape) + 1 == CapesMod.list.size()) {
                    CapesMod.cape = CapesMod.list.get(0);
                    CapesMod.capeCacheIdentifier = null;
                }else{
                    CapesMod.cape = CapesMod.list.get(CapesMod.list.indexOf(CapesMod.cape) + 1);
                };
            }).dimensions((this.width / 2) - 205, (this.height / 4) + 96, 100, 20).build());
    }

    @Inject(at = @At("TAIL"), method = "render")
    private void render(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;
        context.drawText(textRenderer, "Cape: " + CapesMod.cape, 10, 10, 0xffffffff, true);
    }
}