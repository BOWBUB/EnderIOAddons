package info.loenwind.enderioaddons.machine.drain;

import java.awt.Rectangle;

import net.minecraft.entity.player.InventoryPlayer;

import org.lwjgl.opengl.GL11;

import com.enderio.core.client.gui.widget.GuiToolTip;
import com.enderio.core.client.render.RenderUtil;

import crazypants.enderio.EnderIO;
import crazypants.enderio.fluid.Fluids;
import crazypants.enderio.machine.gui.GuiPoweredMachineBase;

public class GuiDrain extends GuiPoweredMachineBase<TileDrain> {

  public GuiDrain(InventoryPlayer par1InventoryPlayer, TileDrain te) {
    super(te, new ContainerDrain(par1InventoryPlayer, te));

    addToolTip(new GuiToolTip(new Rectangle(80, 21, 16, 47), "") {

      @Override
      protected void updateText() {
        text.clear();
        String heading = EnderIO.lang.localize("tank.tank");
        if (getTileEntity().tank.getFluid() != null) {
          heading += ": " + getTileEntity().tank.getFluid().getLocalizedName();
        }
        text.add(heading);
        text.add(Fluids.toCapactityString(getTileEntity().tank));
      }

    });
  }

  @Override
  protected boolean showRecipeButton() {
    return false;
  }

  @Override
  protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    RenderUtil.bindTexture("enderioaddons:textures/gui/drain.png");
    int sx = (width - xSize) / 2;
    int sy = (height - ySize) / 2;

    drawTexturedModalRect(sx, sy, 0, 0, xSize, ySize);

    super.drawGuiContainerBackgroundLayer(par1, par2, par3);

    RenderUtil.bindBlockTexture();
    RenderUtil.renderGuiTank(getTileEntity().tank, guiLeft + 80, guiTop + 21, zLevel, 16, 47);
  }

}
