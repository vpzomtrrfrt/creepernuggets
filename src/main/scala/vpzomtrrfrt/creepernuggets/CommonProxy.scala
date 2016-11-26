package vpzomtrrfrt.creepernuggets

import net.minecraft.item.Item
import net.minecraftforge.fml.common.registry.GameRegistry

class CommonProxy {
  def preInit(): Unit = {
    registerItem(ItemCreeperNugget)
  }

  def init(): Unit = {

  }

  def registerItem(item : Item): Unit = {
    GameRegistry.register(item)
  }
}
