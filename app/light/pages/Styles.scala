package light.pages

enum IconSize:
  case Small, Normal
  def apply(normal: Int, small: Int): Int =
    if (this == Normal) normal else small
end IconSize

enum ImgStyle:
  case Wide
  case Normal
  case Described
  case Left
  case Right
  case Strip
  case Custom(customCss: String)
end ImgStyle
