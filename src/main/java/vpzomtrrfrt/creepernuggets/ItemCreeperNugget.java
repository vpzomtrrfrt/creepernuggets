package vpzomtrrfrt.creepernuggets;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.FoodStats;
import net.minecraft.world.World;

public class ItemCreeperNugget extends Item {
    public static ItemCreeperNugget INSTANCE = new ItemCreeperNugget();

    private ItemCreeperNugget() {
        super(new Item.Properties().group(ItemGroup.FOOD));

        setRegistryName(CreeperNuggets.MODID, "creeper_nugget");
    }

    @Override
    public EnumAction getUseAction(ItemStack stack) {
        return EnumAction.EAT;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 32;
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        if(!worldIn.isRemote) {
            if(entityLiving instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) entityLiving;
                if(!player.isCreative()) stack.shrink(1);

                FoodStats stats = player.getFoodStats();
                stats.addStats(worldIn.getRandom().nextInt(4) + 2, 0);
                if(!stats.needFood()) {
                    worldIn.createExplosion(null, player.posX, player.posY, player.posZ, 2, true);
                }
            }
        }

        return stack;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);
        if(playerIn.canEat(false)) {
            playerIn.setActiveHand(handIn);
            return ActionResult.newResult(EnumActionResult.SUCCESS, stack);
        }
        else {
            return ActionResult.newResult(EnumActionResult.FAIL, stack);
        }
    }
}