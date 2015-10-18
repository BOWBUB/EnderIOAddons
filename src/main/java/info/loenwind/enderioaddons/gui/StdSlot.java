package info.loenwind.enderioaddons.gui;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public final class StdSlot extends Slot {

  private int realx;

  public StdSlot(IInventory inventory, int slotIndex, int x, int y) {
    super(inventory, slotIndex, x, y);
    realx = x;
  }

  @Override
  public boolean isItemValid(ItemStack itemStack) {
    return inventory.isItemValidForSlot(getSlotIndex(), itemStack);
  }

  public void enable(boolean enable) {
    if (enable) {
      xDisplayPosition = realx;
    } else {
      xDisplayPosition = -3000;
    }
  }
}