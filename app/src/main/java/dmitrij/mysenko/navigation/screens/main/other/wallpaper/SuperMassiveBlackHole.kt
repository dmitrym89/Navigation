package dmitrij.mysenko.navigation.screens.main.other.wallpaper


class SuperMassiveBlackHole(
    var scale: Float = 0f,
    override var z: Float = -10f
): Space{
    override fun toString(): String {
        return "SuperMassiveBlackHole(scale=$scale, z=$z)"
    }
}