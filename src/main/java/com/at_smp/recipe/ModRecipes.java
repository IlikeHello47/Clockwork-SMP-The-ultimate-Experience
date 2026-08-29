package com.at_smp.recipe;

import com.at_smp.item.ModItems;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.fml.common.EventBusSubscriber;

import java.util.List;

@EventBusSubscriber(modid = "at_smp")
public class ModRecipes {

    @SubscribeEvent
    public static void onItemTick(EntityTickEvent.Pre event) {
        if (event.getEntity() instanceof ItemEntity itemEntity) {
            Level level = itemEntity.level();
            if (level.isClientSide()) {
                return;
            }
            if (itemEntity.isInWater() && itemEntity.isAlive()) {
                ItemStack stack = itemEntity.getItem();
                if (stack.is(ModItems.MOLTEN_METEORITE.get())) {
                    ItemStack resultStack = new ItemStack(ModItems.METEORITE_INGOT.get(), stack.getCount());
                    ItemEntity newEntity = new ItemEntity(level, itemEntity.getX(), itemEntity.getY(), itemEntity.getZ(), resultStack);

                    newEntity.setDeltaMovement(itemEntity.getDeltaMovement());

                    itemEntity.discard();
                    level.addFreshEntity(newEntity);

                    if (level instanceof ServerLevel serverLevel) {
                        serverLevel.sendParticles(
                                ParticleTypes.LARGE_SMOKE,
                                itemEntity.getX(), itemEntity.getY() + 0.2, itemEntity.getZ(),
                                8,
                                0.1, 0.1, 0.1,
                                0.05
                        );
                        serverLevel.sendParticles(
                                ParticleTypes.BUBBLE,
                                itemEntity.getX(), itemEntity.getY(), itemEntity.getZ(),
                                5, 0.1, 0.1, 0.1, 0.01
                        );
                    }
                    level.playSound(
                            null,
                            itemEntity.getX(), itemEntity.getY(), itemEntity.getZ(),
                            SoundEvents.LAVA_EXTINGUISH,
                            SoundSource.BLOCKS,
                            0.5F,
                            2.6F
                    );
                }
            }
        }
    }
}
