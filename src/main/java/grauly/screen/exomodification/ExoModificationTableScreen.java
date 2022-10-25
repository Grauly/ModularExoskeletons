package grauly.screen.exomodification;

import com.mojang.blaze3d.systems.RenderSystem;
import grauly.ModularExos;
import grauly.util.Constants;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static grauly.util.Constants.TEXT_COLOR;

public class ExoModificationTableScreen extends HandledScreen<ExoModificationTableScreenHandler> {

    private static final Identifier TEXTURE = new Identifier(ModularExos.MODID, "textures/gui/container/exo_modification_table.png");

    public ExoModificationTableScreen(ExoModificationTableScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        backgroundWidth = 211;
    }

    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        drawTexture(matrices, x - 35, y, 0, 0, backgroundWidth, backgroundHeight);
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        drawMouseoverTooltip(matrices, mouseX, mouseY);
        String capacity = this.handler.statsPropertyDelegate.getCurrentCapacity() + " / " + this.handler.statsPropertyDelegate.getMaxCapacity();
        String energy = this.handler.statsPropertyDelegate.getCurrentEnergy() + " / " + this.handler.statsPropertyDelegate.getMaxEnergy();

        matrices.push();
        //note: this little trick aligns the coordinates with the texture pixels, making it easy to position stuff
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        matrices.translate(x, y, 0.0);
        RenderSystem.applyModelViewMatrix();

        textRenderer.draw(matrices, capacity, 29, 29 - textRenderer.fontHeight, TEXT_COLOR);
        textRenderer.draw(matrices, energy, 29, 65 - textRenderer.fontHeight, TEXT_COLOR);

        if (this.handler.statsPropertyDelegate.getSelectedSlot() != -1) {
            RenderSystem.setShaderTexture(0, TEXTURE);
            DrawableHelper.drawTexture(matrices, -30, 5 + 18 * this.handler.statsPropertyDelegate.getSelectedSlot(), 219, 8, 22, 22, 256, 256);
            var slot = this.getScreenHandler().getSlot(56 - (5 - this.handler.statsPropertyDelegate.getSelectedSlot()));
            if (slot.hasStack()) {
                var item = slot.getStack();
                drawItem(item,61,34,"");
            }
        }

        matrices.pop();

    }

    private void drawItem(ItemStack stack, int x, int y, String amountText) {
        int x1 = (width - backgroundWidth) / 2;
        int y1 = (height - backgroundHeight) / 2;
        MatrixStack matrixStack = RenderSystem.getModelViewStack();
        matrixStack.translate(x1 - 35, y1, 32.0);
        RenderSystem.applyModelViewMatrix();
        this.setZOffset(200);
        this.itemRenderer.zOffset = 200.0f;
        this.itemRenderer.renderInGuiWithOverrides(stack, x, y);
        this.itemRenderer.renderGuiItemOverlay(this.textRenderer, stack, x, y, amountText);
        this.setZOffset(0);
        this.itemRenderer.zOffset = 0.0f;
    }

    protected void onButtonPressed(int slot) {
        PacketByteBuf buffer = PacketByteBufs.create();
        buffer.writeInt(slot);
        ClientPlayNetworking.send(Constants.SLOT_SELECT_CHANNEL, buffer);
    }

    @Override
    protected void init() {
        super.init();
        // Center the title
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
        //draw the buttons
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            ButtonWidget buttonWidget = new TexturedButtonWidget(205, 97 + i * 18, 8, 8, 211, 0, 8, TEXTURE, (p) -> {
                ExoModificationTableScreen.this.onButtonPressed(finalI);
            });
            this.addDrawableChild(buttonWidget);
        }
    }
}
