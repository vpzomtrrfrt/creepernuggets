package vpzomtrrfrt.creepernuggets;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.FoodStats;
import net.minecraft.util.Hand;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class ItemCreeperNugget extends Item {
    public static ItemCreeperNugget INSTANCE = new ItemCreeperNugget();

    private ItemCreeperNugget() {
        super(new Item.Properties().group(ItemGroup.FOOD));

        setRegistryName(CreeperNuggets.MODID, "creeper_nugget");
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.EAT;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 32;
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        if(!worldIn.isRemote) {
            if(entityLiving instanceof PlayerEntity) {
                PlayerEntity player = (PlayerEntity) entityLiving;
                if(!player.isCreative()) stack.shrink(1);

                FoodStats stats = player.getFoodStats();
                stats.addStats(worldIn.getRandom().nextInt(4) + 2, 0);
                if(!stats.needFood()) {
                    worldIn.createExplosion(null, player.getPosX(), player.getPosY(), player.getPosZ(), 2, Explosion.Mode.DESTROY);
                }
            }
        }

        return stack;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);
        if(playerIn.canEat(false)) {
            playerIn.setActiveHand(handIn);
            return ActionResult.resultSuccess(stack);
        }
        else {
            return ActionResult.resultFail(stack);
        }
    }
}