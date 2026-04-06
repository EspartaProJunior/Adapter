package dev.wuason.adapter.plugins;

import dev.wuason.adapter.AdapterComp;
import dev.wuason.adapter.Utils;
import net.momirealms.craftengine.bukkit.api.CraftEngineBlocks;
import net.momirealms.craftengine.bukkit.api.CraftEngineFurniture;
import net.momirealms.craftengine.bukkit.api.CraftEngineItems;
import net.momirealms.craftengine.core.util.Key;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class CraftEngineImpl extends AdapterComp {

    public CraftEngineImpl(String name, String type) {
        super(name, type);
    }

    @Override
    public ItemStack getAdapterItem(String id) {
        String[] split = id.split(":");
        return split.length < 2 ? null : Optional.ofNullable(CraftEngineItems.byId(Key.of(split)))
                .map(item -> item.buildBukkitItem())
                .orElse(null);
    }

    @Override
    public boolean existItemAdapter(String id) {
        String[] split = id.split(":");
        return split.length >= 2 && CraftEngineItems.byId(Key.of(split)) != null;
    }

    @Override
    public Set<String> getAllItems() {
        return CraftEngineItems.loadedItems().keySet().stream()
                .map(key -> Utils.convert(getType(), key.toString()))
                .collect(Collectors.toUnmodifiableSet());
    }

    @Override
    public String getAdapterId(Entity entity) {
        if (!CraftEngineFurniture.isFurniture(entity)) return null;
        return CraftEngineFurniture.isSeat(entity) ? Utils.convert(getType(), CraftEngineFurniture.getLoadedFurnitureBySeat(entity).id().toString()) : Utils.convert(getType(), CraftEngineFurniture.getLoadedFurnitureByMetaEntity(entity).id().toString());
    }

    @Override
    public String getAdvancedAdapterId(ItemStack itemStack) {
        return getAdapterId(itemStack);
    }

    @Override
    public String getAdvancedAdapterId(Block block) {
        return getAdapterId(block);
    }



    @Override
    public String getAdvancedAdapterId(Entity entity) {
        return getAdapterId(entity);
    }

    @Override
    public String getAdapterId(Block block) {
        return CraftEngineBlocks.isCustomBlock(block) ? Utils.convert(getType(), CraftEngineBlocks.getCustomBlockState(block).owner().value().id().toString()) : null;
    }

    @Override
    public String getAdapterId(ItemStack itemStack) {
        return Optional.ofNullable(CraftEngineItems.getCustomItemId(itemStack)).map(k -> Utils.convert(getType(), k.toString())).orElse(null);
    }
}
