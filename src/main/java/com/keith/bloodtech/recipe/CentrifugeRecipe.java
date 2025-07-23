package com.keith.bloodtech.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;

public record CentrifugeRecipe(Ingredient inputItem, List<ItemStack> result) implements Recipe<CentrifugeRecipeInput> {
    //Read in a JSON file inputItem & results
    //RecipeInput is is

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();
        list.add(inputItem);
        return list;
    }

    @Override
    public boolean matches(CentrifugeRecipeInput centrifugeRecipeInput, Level level) {
        if(level.isClientSide()){
            return false;
        }

        return inputItem.test(centrifugeRecipeInput.getItem(0));
    }

    @Override
    public ItemStack assemble(CentrifugeRecipeInput centrifugeRecipeInput, HolderLookup.Provider provider) {
        return null;
    }

    @Override
    public boolean canCraftInDimensions(int i, int i1) {
        return true;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider provider) {
        return null;
    }
    public List<ItemStack> getResultItemStack(){
        return result;
    }
    public List<ItemStack> assembleMultiple(CentrifugeRecipeInput centrifugeRecipeInput, HolderLookup.Provider provider) {
        return result;
    }
    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.CENTRIFUGE_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.CENTRIFUGE_TYPE.get();
    }
    public static class Serializer implements RecipeSerializer<CentrifugeRecipe>{

        public static final MapCodec<CentrifugeRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.CODEC_NONEMPTY.fieldOf("ingredient").forGetter(CentrifugeRecipe::inputItem),
                ItemStack.CODEC.listOf().fieldOf("result").forGetter(CentrifugeRecipe::result)
        ).apply(inst, CentrifugeRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, CentrifugeRecipe> STREAM_CODEC =
                StreamCodec.composite(
                        Ingredient.CONTENTS_STREAM_CODEC, CentrifugeRecipe::inputItem,
                        ItemStack.LIST_STREAM_CODEC, CentrifugeRecipe::result,
                        CentrifugeRecipe::new);
        @Override
        public MapCodec<CentrifugeRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, CentrifugeRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
