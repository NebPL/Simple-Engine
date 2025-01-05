import java.awt.image.BufferedImage
import javax.imageio.ImageIO

open class Image(path: String) {



    var w: Int = 0
    var h: Int = 0
    var p: IntArray
    var image: BufferedImage? = null

    init {

        val resourceStream = Image::class.java.getResourceAsStream(path)
        if (resourceStream != null) {
            image = ImageIO.read(resourceStream)
            w = image?.width ?: 0
            h = image?.height ?: 0
            p = IntArray(w * h)
            image?.getRGB(0, 0, w, h, p, 0, w)
        } else {
            println("Image resource not found: $path")
            p = IntArray(0) // Initialisiert p auch im Fehlerfall
        }

        // Setzen der richtigen Werte für w und h
        if (image != null) {
            w = image!!.width
            h = image!!.height
            p = image!!.getRGB(0, 0, w, h, null, 0, w)
        }

        image?.flush()
    }
}
