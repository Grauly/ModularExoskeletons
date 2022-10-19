package grauly.screen.modification;

import com.mojang.blaze3d.systems.RenderSystem;
import grauly.ModularExos;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModificationTableScreen extends HandledScreen<ModificationTableScreenHandler> {

    private static final Identifier TEXTURE = new Identifier(ModularExos.MODID, "textures/gui/container/modification_table.png");
    private static final int TEXT_COLOR = 0x404040;

    public ModificationTableScreen(ModificationTableScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        drawTexture(matrices, x, y, 0, 0, backgroundWidth, backgroundHeight);
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        drawMouseoverTooltip(matrices, mouseX, mouseY);
        String capacity = this.handler.getCurrentCapacity() + " / " + this.handler.getMaxCapacity();
        String energy = this.handler.getCurrentEnergy() + " / " + this.handler.getMaxEnergy();
        matrices.push();
        RenderSystem.applyModelViewMatrix();
        int x = (width - backgroundWidth) / 2 + backgroundWidth / 6;
        int y = (height - backgroundHeight) / 2;
        textRenderer.draw(matrices, capacity, x, y + backgroundHeight / 8f, TEXT_COLOR);
        textRenderer.draw(matrices, energy, x, y + backgroundHeight / 3f, TEXT_COLOR);
        matrices.pop();
    }

    @Override
    protected void init() {
        super.init();
        // Center the title
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
    }
}
