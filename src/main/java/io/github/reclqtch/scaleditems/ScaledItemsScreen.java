package io.github.reclqtch.scaleditems;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.client.config.GuiSlider;

public class ScaledItemsScreen extends GuiScreen {
    public static final ItemStack SWORD = new ItemStack(Items.diamond_sword);
    public GuiSlider sliderS;
    public GuiSlider sliderX;
    public GuiSlider sliderY;
    public GuiSlider sliderZ;
    public GuiSlider sliderRX;
    public GuiSlider sliderRY;
    public GuiSlider sliderRZ;

    @Override
    public void initGui() {
        this.buttonList.add(sliderS = new GuiSlider(0, this.width / 2 - 100, 22, 200, 20, "Scale: ", "", 0.0, 2.0, ScaledItems.scale, true, true, s -> ScaledItems.scale = (float) s.getValue()));
        this.buttonList.add(sliderX = new GuiSlider(1, this.width / 2 - 100, 44, 200, 20, "X Offset: ", "", -2.0, 2.0, ScaledItems.transX, true, true, s -> ScaledItems.transX = (float) s.getValue()));
        this.buttonList.add(sliderY = new GuiSlider(2, this.width / 2 - 100, 66, 200, 20, "Y Offset: ", "", -2.0, 2.0, ScaledItems.transY, true, true, s -> ScaledItems.transY = (float) s.getValue()));
        this.buttonList.add(sliderZ = new GuiSlider(3, this.width / 2 - 100, 88, 200, 20, "Z Offset: ", "", -2.0, 2.0, ScaledItems.transZ, true, true, s -> ScaledItems.transZ = (float) s.getValue()));
        this.buttonList.add(sliderRX = new GuiSlider(4, this.width / 2 - 100, 110, 200, 20, "X Rotation: ", "", -180.0, 180.0, ScaledItems.rotateX, true, true, s -> ScaledItems.rotateX = (float) s.getValue()));
        this.buttonList.add(sliderRY = new GuiSlider(5, this.width / 2 - 100, 132, 200, 20, "Y Rotation: ", "", -180.0, 180.0, ScaledItems.rotateY, true, true, s -> ScaledItems.rotateY = (float) s.getValue()));
        this.buttonList.add(sliderRZ = new GuiSlider(6, this.width / 2 - 100, 154, 200, 20, "Z Rotation: ", "", -180.0, 180.0, ScaledItems.rotateZ, true, true, s -> ScaledItems.rotateZ = (float) s.getValue()));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void onGuiClosed() {
        ScaledItems.saveConfig();
    }

}
