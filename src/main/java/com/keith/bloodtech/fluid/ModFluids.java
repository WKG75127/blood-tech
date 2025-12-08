package com.keith.bloodtech.fluid;

import com.keith.bloodtech.BloodTech;
import com.keith.bloodtech.block.ModBlocks;
import com.keith.bloodtech.item.ModItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModFluids {
    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(BuiltInRegistries.FLUID, BloodTech.MODID);

    public static final Supplier<FlowingFluid> SOURCE_BLOOD = FLUIDS.register("source_blood_water",
            () -> new BaseFlowingFluid.Source(ModFluids.BLOOD_PROPERTIES));
    public static final Supplier<FlowingFluid> FLOWING_BLOOD = FLUIDS.register("flowing_blood_water",
            () -> new BaseFlowingFluid.Flowing(ModFluids.BLOOD_PROPERTIES));

    public static final DeferredBlock<LiquidBlock> BLOOD_BLOCK = ModBlocks.BLOCKS.register("blood_block",
            () -> new LiquidBlock(ModFluids.SOURCE_BLOOD.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).noLootTable()));

    public static final BaseFlowingFluid.Properties BLOOD_PROPERTIES = new BaseFlowingFluid.Properties(
            ModFluidTypes.BLOOD_FLUID_TYPE, SOURCE_BLOOD, FLOWING_BLOOD)
            .slopeFindDistance(2).levelDecreasePerBlock(1)
            .block(ModFluids.BLOOD_BLOCK).bucket(ModItems.BLOOD_BUCKET);


    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}
