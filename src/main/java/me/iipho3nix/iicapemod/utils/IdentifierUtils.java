package me.iipho3nix.iicapemod.utils;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.util.Identifier;
import org.lwjgl.BufferUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

public class IdentifierUtils {
    public static void registerBufferedImageTexture(Identifier i, BufferedImage bi) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bi, "png", baos);
            byte[] bytes = baos.toByteArray();
            ByteBuffer bb = BufferUtils.createByteBuffer(bytes.length).put(bytes);
            bb.flip();
            NativeImageBackedTexture nibt = new NativeImageBackedTexture(NativeImage.read(bb));
            MinecraftClient.getInstance().getTextureManager().registerTexture(i, nibt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}