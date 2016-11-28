package vpzomtrrfrt.creepernuggets

import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.{EnumAction, Item, ItemStack}
import net.minecraft.world.World

import scala.util.Random

object ItemCreeperNugget extends Item {
  setRegistryName("creeperNugget")
  setUnlocalizedName("creeperNugget")
  setCreativeTab(CreativeTabs.tabFood)

  override def getItemUseAction(p_getItemUseAction_1_ : ItemStack): EnumAction = EnumAction.EAT

  override def getMaxItemUseDuration(p_getMaxItemUseDuration_1_ : ItemStack): Int = 32

  override def onItemUseFinish(stack: ItemStack, world: World, player: EntityPlayer): ItemStack = {
    if (!world.isRemote) {
      if (!player.capabilities.isCreativeMode) {
        stack.stackSize -= 1
      }
      val stats = player.getFoodStats
      stats.addStats(new Random().nextInt(4) + 2, 0)
      if (!stats.needFood) {
        world.createExplosion(null, player.posX, player.posY, player.posZ, 2, true)
      }
    }
    if (stack.stackSize > 0) {
      stack
    } else {
      null
    }
  }

  override def onItemRightClick(stack : ItemStack, world : World, player : EntityPlayer): ItemStack = {
    player.setItemInUse(stack, getMaxItemUseDuration(stack))
    stack
  }
}
