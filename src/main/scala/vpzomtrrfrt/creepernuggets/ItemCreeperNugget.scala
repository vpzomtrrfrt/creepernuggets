package vpzomtrrfrt.creepernuggets

import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.{EnumAction, Item, ItemStack}
import net.minecraft.util.{ActionResult, EnumActionResult, EnumHand}
import net.minecraft.world.World

import scala.util.Random

object ItemCreeperNugget extends Item {
  setRegistryName("creeperNugget")
  setUnlocalizedName("creeperNugget")
  setCreativeTab(CreativeTabs.FOOD)

  override def getItemUseAction(p_getItemUseAction_1_ : ItemStack): EnumAction = EnumAction.EAT

  override def getMaxItemUseDuration(p_getMaxItemUseDuration_1_ : ItemStack): Int = 32

  override def onItemUseFinish(stack: ItemStack, p_onItemUseFinish_2_ : World, p_onItemUseFinish_3_ : EntityLivingBase): ItemStack = {
    if (!p_onItemUseFinish_2_.isRemote) {
      p_onItemUseFinish_3_ match {
        case player: EntityPlayer =>
          if (!player.capabilities.isCreativeMode) {
            stack.func_190918_g(1)
          }
          val stats = player.getFoodStats
          stats.addStats(new Random().nextInt(4) + 2, 0)
          if (!stats.needFood) {
            p_onItemUseFinish_2_.createExplosion(null, player.posX, player.posY, player.posZ, 2, true)
          }
        case _ =>
      }
    }
    stack
  }

  override def onItemRightClick(world : World, player : EntityPlayer, hand : EnumHand): ActionResult[ItemStack] = {
    player.setActiveHand(hand)
    new ActionResult[ItemStack](EnumActionResult.SUCCESS, player.getHeldItem(hand))
  }
}
