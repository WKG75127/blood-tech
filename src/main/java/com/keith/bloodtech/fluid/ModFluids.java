package com.keith.bloodtech.fluid;

import com.keith.bloodtech.BloodTech;
import com.keith.bloodtech.block.ModBlocks;
import com.keith.bloodtech.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class ModFluids {
    public  static final DeferredRegister<Fluid> FlUIDS = DeferredRegister.create(Registries.FLUID, BloodTech.MODID);
    public static final Supplier<FlowingFluid> SOURCE_BLOOD = FlUIDS.register("blood_fluid", () -> new BaseFlowingFluid.Source(ModFluids.blood_properties));
    public static final Supplier<FlowingFluid> FlOWING_BLOOD = FlUIDS.register("flowing_blood_fluid", () -> new BaseFlowingFluid.Flowing(ModFluids.blood_properties));

    public static final BaseFlowingFluid.Properties blood_properties = new BaseFlowingFluid.Properties(
            ModFluidTypes.BLOOD_FLUID, SOURCE_BLOOD, FlOWING_BLOOD)
            .block(ModBlocks.BLOOD_FLUID_BLOCK)
            .bucket(ModItems.BLOOD_BUCKET)
            .slopeFindDistance(3)
            .levelDecreasePerBlock(2)
            .tickRate(25)
            ;
    public static void register(IEventBus eventBus){
        FlUIDS.register(eventBus);
    }
}
