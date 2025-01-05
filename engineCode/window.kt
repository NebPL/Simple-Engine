package engineCode

import java.awt.BorderLayout
import java.awt.Canvas
import java.awt.Dimension
import java.awt.Graphics
import java.awt.image.BufferStrategy
import java.awt.image.BufferedImage
import javax.swing.JFrame

class Window(gc: GameContainer) {
    var frame: JFrame = JFrame()
    var image: BufferedImage = BufferedImage(gc.width, gc.height, BufferedImage.TYPE_INT_RGB)
    var canvas: Canvas = Canvas()
    var bs: BufferStrategy? = null
    var g: Graphics? = null

    init {
        val s = Dimension(gc.width * gc.scale.toInt(), gc.height * gc.scale.toInt())

        canvas.preferredSize = s
        canvas.minimumSize = s
        canvas.maximumSize = s

        frame = JFrame(gc.title)
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.layout = BorderLayout()
        frame.add(canvas, BorderLayout.CENTER)
        frame.pack()
        frame.setLocationRelativeTo(null)
        frame.isResizable = false
        frame.isVisible = true

        canvas.createBufferStrategy(2)
        bs = canvas.bufferStrategy
        g = bs?.drawGraphics
    }

    fun update() {
        g?.drawImage(image, 0, 0, canvas.width, canvas.height, null)
        bs?.show()
    }
}
