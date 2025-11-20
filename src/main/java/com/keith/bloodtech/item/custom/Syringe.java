package com.keith.bloodtech.item.custom;

import com.keith.bloodtech.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class Syringe extends Item {
    public Syringe(Properties properties) {
        super(properties);
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack stack = player.getItemInHand(usedHand);
        HitResult hitResult = Minecraft.getInstance().hitResult;
        if (hitResult.getType() == HitResult.Type.ENTITY){
            EntityHitResult entityHitResult = (EntityHitResult) hitResult;
            if(entityHitResult.getEntity().isAlive()){
                player.setItemInHand(usedHand,new ItemStack(ModItems.BLOOD_SYRINGE.get(),1));
                return InteractionResultHolder.success(stack);
            }
            return InteractionResultHolder.fail(stack);
        } else if (hitResult.getType() == HitResult.Type.MISS) {
            player.hurt(DamageTypes.FALL,1);
            player.setItemInHand(usedHand,new ItemStack(ModItems.BLOOD_SYRINGE.get(),1));
            return InteractionResultHolder.success(stack);
        } else{
            return InteractionResultHolder.fail(stack);
        }
    }
}
