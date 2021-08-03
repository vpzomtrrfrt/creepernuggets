package vpzomtrrfrt.creepernuggets;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

public class ItemCreeperNugget extends Item {
    public static ItemCreeperNugget INSTANCE = new ItemCreeperNugget();

    private ItemCreeperNugget() {
        super(new Item.Settings().group(ItemGroup.FOOD));
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.EAT;
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 32;
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (user.canConsume(false)) {
            user.setCurrentHand(hand);
            return TypedActionResult.consume(itemStack);
        } else {
            return TypedActionResult.fail(itemStack);
        }
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if(world.isClient) return stack;

        world.playSound(
                null,
                user.getX(),
                user.getY(),
                user.getZ(),
                user.getEatSound(stack),
                SoundCategory.NEUTRAL,
                1.0F,
                1.0F + (world.random.nextFloat() - world.random.nextFloat()) * 0.4F
        );
        if(!(user instanceof PlayerEntity) || !((PlayerEntity) user).abilities.creativeMode) {
            stack.decrement(1);
        }
        if(user instanceof PlayerEntity) {
            ((PlayerEntity) user).getHungerManager().add(world.random.nextInt(4) + 2, 0);
            if(!((PlayerEntity) user).getHungerManager().isNotFull()) {
                world.createExplosion(null, user.getX(), user.getY(), user.getZ(), 2, Explosion.DestructionType.DESTROY);
            }
        }

        return stack;
    }
}
