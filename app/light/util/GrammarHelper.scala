package light.util

object GrammarHelper:
  def split(msg: String): Array[String] = msg.split("\\s", 2)
  def head(msg: String): String = split(msg).head
  def tail(msg: String): String = split(msg).tail.headOption.getOrElse("")

  def linkText(address: String): String =
    if (address.startsWith("http://")) address.substring(7)
    else if (address.startsWith("https://")) address.substring(8)
    else address

  def message(n: Int, nominative: String, genitive: String, genitivePlural: String): String =
    if (n % 100 / 10 == 1) s"$n $genitivePlural" 
    else n % 10 match
      case 1 => s"$n $nominative"
      case i if Set(2, 3, 4).contains(i) => s"$n $genitive"
      case _ => s"$n $genitivePlural"
  
end GrammarHelper

