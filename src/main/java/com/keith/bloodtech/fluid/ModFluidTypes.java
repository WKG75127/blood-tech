package com.keith.bloodtech.fluid;

import com.keith.bloodtech.BloodTech;
import com.mojang.blaze3d.shaders.FogShape;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.common.SoundAction;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;

import java.util.function.Supplier;

public class ModFluidTypes {

    public static final DeferredRegister<FluidType> FLUID_TYPES =
            DeferredRegister.create(NeoForgeRegistries.FLUID_TYPES, BloodTech.MODID);

    public static final ResourceLocation WATER_STATIC_RL = ResourceLocation.withDefaultNamespace("block/water_still");
    public static final ResourceLocation WATER_FLOWING_RL = ResourceLocation.withDefaultNamespace("block/water_flowing");
    public static final ResourceLocation BLOOD_TEXTURE = ResourceLocation.fromNamespaceAndPath(BloodTech.MODID, "misc/soon_to_be_blood");


    public static final DeferredHolder<FluidType, FluidType> BLOOD_FLUID = FLUID_TYPES.register("pizza_sauce_fluid_type",
            () -> new FluidType(FluidType.Properties.create()
                    .lightLevel(2)
                    .density(1400)
                    .viscosity(1500)));

    public static void register(IEventBus eventBus) {
        FLUID_TYPES.register(eventBus);

        // Register client extensions for fluid types
        eventBus.addListener(ModFluidTypes::registerClientExtensions);
    }

    private static void registerClientExtensions(RegisterClientExtensionsEvent event) {

        // Register client extensions for pizza sauce fluid
        event.registerFluidType(new IClientFluidTypeExtensions() {
            @Override
            public @NotNull ResourceLocation getStillTexture() {
                return WATER_STATIC_RL;
            }

            @Override
            public @NotNull ResourceLocation getFlowingTexture() {
                return WATER_FLOWING_RL;
            }

            @Override
            public ResourceLocation getOverlayTexture() {
                return BLOOD_TEXTURE;
            }

            @Override
            public int getTintColor() {
                return 0xFFFFFFFF; // No tint
            }

            @Override
            public @NotNull Vector3f modifyFogColor(@NotNull Camera camera, float partialTick, @NotNull ClientLevel level, int renderDistance, float darkenWorldAmount, @NotNull Vector3f fluidFogColor) {
                return new Vector3f(0.29f, 0.0f, 0.51f); // Royal Purple fog color
            }

            @Override
            public void modifyFogRender(@NotNull Camera camera, FogRenderer.@NotNull FogMode mode, float renderDistance, float partialTick, float nearDistance, float farDistance, @NotNull FogShape shape) {
                RenderSystem.setShaderFogColor(0.29f, 0.0f, 0.51f); // Royal Purple fog color
                RenderSystem.setShaderFogStart(0.2f); // Adjust start distance
                RenderSystem.setShaderFogEnd(1.5f);   // Adjust end distance
                RenderSystem.setShaderFogShape(FogShape.CYLINDER); // Set the fog shape
            }
        }, BLOOD_FLUID.get());
    }
}