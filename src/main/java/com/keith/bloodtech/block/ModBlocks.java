package com.keith.bloodtech.block;

import com.keith.bloodtech.block.custom.CentrifugeBlock;
import com.keith.bloodtech.block.custom.ResistanceGenBlock;
import com.keith.bloodtech.fluid.ModFluids;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.WaterFluid;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.keith.bloodtech.BloodTech.MODID;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);


    public static final DeferredBlock<Block> CENTRIFUGE_BLOCK = BLOCKS.register("centrifuge",() -> new CentrifugeBlock(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> RESISTANCE_GENERATOR = BLOCKS.register("resistance_generator",() -> new ResistanceGenBlock(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> MECHANICAL_HEART = BLOCKS.registerSimpleBlock("heartmachine", BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY));
    public static final DeferredBlock<LiquidBlock> BLOOD_FLUID_BLOCK = BLOCKS.register("blood_fluid_block",
            () -> new LiquidBlock(ModFluids.SOURCE_BLOOD.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).noCollission()));

}
