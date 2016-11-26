package vpzomtrrfrt.creepernuggets

import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.{EnumAction, Item, ItemStack}
import net.minecraft.util.{ActionResult, EnumActionResult, EnumHand}
import net.minecraft.world.World

import scala.util.Random

object ItemCreeperNugget extends Item {
  setRegistryName("creeperNugget")
  setUnlocalizedName("creeperNugget")

  override def getItemUseAction(p_getItemUseAction_1_ : ItemStack): EnumAction = EnumAction.EAT

  override def getMaxItemUseDuration(p_getMaxItemUseDuration_1_ : ItemStack): Int = 16

  override def onItemUseFinish(stack : ItemStack, p_onItemUseFinish_2_ : World, p_onItemUseFinish_3_ : EntityLivingBase): ItemStack = {
    stack.stackSize -= 1
    if(!p_onItemUseFinish_2_.isRemote) {
      p_onItemUseFinish_3_ match {
        case player: EntityPlayer =>
          val stats = player.getFoodStats
          stats.addStats(new Random().nextInt(4) + 2, 0)
          if (!stats.needFood) {
            p_onItemUseFinish_2_.createExplosion(null, player.posX, player.posY, player.posZ, 2, true)
          }
        case _ =>
      }
    }
    if (stack.stackSize > 0) {
      stack
    } else {
      null
    }
  }

  override def onItemRightClick(p_onItemRightClick_1_ : ItemStack, p_onItemRightClick_2_ : World, player : EntityPlayer, hand : EnumHand): ActionResult[ItemStack] = {
    player.setActiveHand(hand)
    new ActionResult[ItemStack](EnumActionResult.SUCCESS, p_onItemRightClick_1_)
  }
}
