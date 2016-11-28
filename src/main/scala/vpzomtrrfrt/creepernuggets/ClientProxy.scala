package vpzomtrrfrt.creepernuggets
import net.minecraft.client.Minecraft
import net.minecraft.client.resources.model.ModelResourceLocation
import net.minecraft.item.Item

import scala.collection.mutable.ArrayBuffer

class ClientProxy extends CommonProxy {
  val items = new ArrayBuffer[Item]()
  override def registerItem(item: Item): Unit = {
    super.registerItem(item)
    items.append(item)
  }

  override def init(): Unit = {
    for(item <- items) {
      val loc = item.getRegistryName
      Minecraft.getMinecraft.getRenderItem.getItemModelMesher.register(item, 0, new ModelResourceLocation(loc, "inventory"))
    }
  }
}
