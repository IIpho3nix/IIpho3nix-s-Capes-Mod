package me.iipho3nix.iicapemod.utils;

import me.iipho3nix.iicapemod.CapesMod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.util.Identifier;
import org.lwjgl.BufferUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.function.Supplier;

public class IdentifierUtils {
    public static void registerBufferedImageTexture(Identifier i, BufferedImage bi) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bi, "png", baos);
            byte[] bytes = baos.toByteArray();
            ByteBuffer bb = BufferUtils.createByteBuffer(bytes.length).put(bytes);
            bb.flip();
            Supplier nameSupplier = () -> i.getNamespace() + ":" + i.getPath();
            NativeImageBackedTexture nibt = new NativeImageBackedTexture(nameSupplier, NativeImage.read(bb));
            MinecraftClient.getInstance().getTextureManager().registerTexture(i, nibt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}