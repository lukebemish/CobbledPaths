package com.github.lukebemish.cobbled_paths.forge;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class RegUtilImpl {
    public static Supplier<Item> getVanillaItemSupplier(ResourceLocation rl) {
        return ()->ForgeRegistries.ITEMS.containsKey(rl)?ForgeRegistries.ITEMS.getValue(rl):null;
    }

    public static Supplier<Block> getVanillaBlockSupplier(ResourceLocation rl) {
        return ()->ForgeRegistries.BLOCKS.containsKey(rl)?ForgeRegistries.BLOCKS.getValue(rl):null;
    }
}
