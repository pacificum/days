package light.pages

import controllers.routes

trait AssetDir:
  def assetDir: String
end AssetDir

object AssetDir:
  val default = "content"
end AssetDir

object Files:
  val legacyStorageUrl = s"//assets.pacificum.org"
  def assetAt(fileName: String): String = routes.Assets.at(fileName).toString
  def at(dir: AssetDir, fileName: String): String = s"$legacyStorageUrl/${dir.assetDir}/$fileName"
  def icon(fileName: String): String = assetAt(s"icons/$fileName")
end Files
