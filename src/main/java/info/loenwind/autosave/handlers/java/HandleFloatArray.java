package info.loenwind.autosave.handlers.java;

import info.loenwind.autosave.IHandler;
import info.loenwind.autosave.Registry;
import info.loenwind.autosave.annotations.Store;

import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.nbt.NBTTagCompound;

public class HandleFloatArray implements IHandler<float[]> {

  public HandleFloatArray() {
  }

  @Override
  public boolean canHandle(Class<?> clazz) {
    return float[].class.isAssignableFrom(clazz);
  }

  @Override
  public boolean store(@Nonnull Registry registry, @Nonnull Set<Store.StoreFor> phase, @Nonnull NBTTagCompound nbt, @Nonnull String name,
      @Nonnull float[] object) throws IllegalArgumentException, IllegalAccessException {
    int len = 0;
    for (int i = object.length; i > 0; i--) {
      if (object[i - 1] != 0) {
        len = i;
        break;
      }
    }
    System.out.println("Saved " + ((object.length - len) * 4) + " bytes"); // TODO remove me!
    int[] tmp = new int[len];
    for (int i = 0; i < len; i++) {
      tmp[i] = Float.floatToIntBits(object[i]);
    }
    nbt.setIntArray(name, tmp);
    return true;
  }

  @Override
  public float[] read(@Nonnull Registry registry, @Nonnull Set<Store.StoreFor> phase, @Nonnull NBTTagCompound nbt, @Nonnull String name,
      @Nullable float[] object) {
    if (nbt.hasKey(name) && object != null) {
      int[] tmp = nbt.getIntArray(name);
      for (int i = 0; i < object.length; i++) {
        if (i < tmp.length) {
          object[i] = Float.intBitsToFloat(tmp[i]);
        } else {
          object[i] = 0;
        }
      }

    }
    return object;
  }

}
