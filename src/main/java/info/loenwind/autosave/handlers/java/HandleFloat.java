package info.loenwind.autosave.handlers.java;

import info.loenwind.autosave.IHandler;
import info.loenwind.autosave.Registry;
import info.loenwind.autosave.annotations.Store;

import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.nbt.NBTTagCompound;

public class HandleFloat implements IHandler<Float> {

  public HandleFloat() {
  }

  @Override
  public boolean canHandle(Class<?> clazz) {
    return Float.class.isAssignableFrom(clazz) || float.class.isAssignableFrom(clazz);
  }

  @Override
  public boolean store(@Nonnull Registry registry, @Nonnull Set<Store.StoreFor> phase, @Nonnull NBTTagCompound nbt, @Nonnull String name, @Nonnull Float object)
      throws IllegalArgumentException, IllegalAccessException {
    nbt.setFloat(name, object);
    return true;
  }

  @Override
  public Float read(@Nonnull Registry registry, @Nonnull Set<Store.StoreFor> phase, @Nonnull NBTTagCompound nbt, @Nonnull String name, @Nullable Float object) {
    return nbt.hasKey(name) ? nbt.getFloat(name) : object != null ? object : 0f;
  }

}
