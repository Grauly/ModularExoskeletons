package grauly.screen.modification;

import com.mojang.blaze3d.systems.RenderSystem;
import grauly.ModularExos;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static grauly.util.Constants.TEXT_COLOR;

public class ModificationTableScreen extends HandledScreen<ModificationTableScreenHandler> {

    private static final Identifier TEXTURE = new Identifier(ModularExos.MODID, "textures/gui/container/modification_table.png");

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
        //note: this little trick aligns the coordinates with the texture pixels, making it easy to position stuff
        int x = (width - backgroundWidth) / 2 ;
        int y = (height - backgroundHeight) / 2;
        matrices.translate(x,y,0.0);
        RenderSystem.applyModelViewMatrix();

        textRenderer.draw(matrices, capacity, 29, 29 - textRenderer.fontHeight, TEXT_COLOR);
        textRenderer.draw(matrices, energy, 29, 65 - textRenderer.fontHeight, TEXT_COLOR);
        matrices.pop();
    }

    @Override
    protected void init() {
        super.init();
        // Center the title
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
    }
}
