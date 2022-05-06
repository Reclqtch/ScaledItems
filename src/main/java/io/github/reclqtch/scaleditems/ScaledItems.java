package io.github.reclqtch.scaleditems;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;

@Mod(modid = "scaleditems", name = "Scaled Items", version = "1.8.9", clientSideOnly = true)
public class ScaledItems {
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    public static Path config;
    public static boolean showGui = false;
    public static float scale = 1.0F;
    public static float transX = 0.0F;
    public static float transY = 0.0F;
    public static float transZ = 0.0F;
    public static float rotateX = 0.0F;
    public static float rotateY = 0.0F;
    public static float rotateZ = 0.0F;

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        ClientCommandHandler.instance.registerCommand(new ScaledItemsCommand());
        MinecraftForge.EVENT_BUS.register(this);
        config = Loader.instance().getConfigDir().toPath().resolve("scaleditems.json");
        loadConfig();
    }

    public static void loadConfig() {
        try (BufferedReader reader = Files.newBufferedReader(config)) {
            JsonObject obj = GSON.fromJson(reader, JsonObject.class);
            if (obj.has("scale")) scale = obj.get("scale").getAsFloat();
            if (obj.has("transX")) transX = obj.get("transX").getAsFloat();
            if (obj.has("transY")) transY = obj.get("transY").getAsFloat();
            if (obj.has("transZ")) transZ = obj.get("transZ").getAsFloat();
            if (obj.has("rotateX")) rotateX = obj.get("rotateX").getAsFloat();
            if (obj.has("rotateY")) rotateY = obj.get("rotateY").getAsFloat();
            if (obj.has("rotateZ")) rotateZ = obj.get("rotateZ").getAsFloat();
        } catch (Throwable e) {
            saveConfig();
        }
    }

    public static void saveConfig() {
        try (BufferedWriter writer = Files.newBufferedWriter(config)) {
            JsonObject obj = new JsonObject();
            obj.addProperty("scale", scale);
            obj.addProperty("transX", transX);
            obj.addProperty("transY", transY);
            obj.addProperty("transZ", transZ);
            obj.addProperty("rotateX", rotateX);
            obj.addProperty("rotateY", rotateY);
            obj.addProperty("rotateZ", rotateZ);
            GSON.toJson(obj, writer);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @SubscribeEvent
    public void showGui(TickEvent.ClientTickEvent e) {
        if (showGui) {
            Minecraft.getMinecraft().displayGuiScreen(new ScaledItemsScreen());
            showGui = false;
        }
    }

    public static void itemPreTransform() {
        GlStateManager.translate(transX, transY, transZ);
        GlStateManager.rotate(rotateX, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotate(rotateY, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(rotateZ, 0.0F, 0.0F, 1.0F);
        GlStateManager.scale(scale, scale, scale);
    }
}
