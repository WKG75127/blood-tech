package com.keith.bloodtech.block;

import com.keith.bloodtech.block.custom.CentrifugeBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.keith.bloodtech.BloodTech.MODID;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);

    public static final DeferredBlock<Block> CENTRIFUGE_BLOCK = BLOCKS.register("centrifuge",() -> new CentrifugeBlock(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> MECHANICAL_HEART = BLOCKS.registerSimpleBlock("heartmachine", BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY));


}
