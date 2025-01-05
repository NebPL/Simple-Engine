package engineCode

import Image
import de.Neb.gfx.ImageTile
import de.Neb.gfx.font

import java.awt.image.DataBufferInt

class renderer(gc: GameContainer)  {
    var mouseX = Input(gc).mouseX
    var mouseY = Input(gc).mouseY
    var pW: Int = gc.width
    var pH: Int = gc.height
    var p: IntArray
    var font: font = font("/textfont.png") // Initialisierung der Font-Instanz

    init {
        p = (gc.window!!.image.raster.dataBuffer as DataBufferInt).data
    }



    fun clear(){
        for (i in p.indices) {
            p[i] = 0x000000 // Setzt jedes Pixel auf Schwarz
        }
    }

    fun setPixel(x:Int,y:Int,value:Int){
        if((x<0 || x>= pW || y< 0||y>=pH)|| value == 0xffff00ff.toInt()){
            return;
        }

        p[x + y*pW] = value
    }

    fun fillRect(offX: Int, offY: Int, x2: Int, y2: Int, color: Int) {
        val startX = minOf(offX, x2)
        val endX = maxOf(offX, x2)
        val startY = minOf(offY, y2)
        val endY = maxOf(offY, y2)

        for (y in startY until endY) {
            for (x in startX until endX) {
                setPixel(x, y, color)
            }
        }
    }

    fun drawBorder(x: Int, y: Int, width: Int, height: Int, lineThickness: Int, color: Int) {
        // Obere Linie
        fillRect(x, y, x + width, y + lineThickness, color)

        // Untere Linie
        fillRect(x, y + height - lineThickness, x + width, y + height, color)

        // Linke Linie
        fillRect(x, y, x + lineThickness, y + height, color)

        // Rechte Linie
        fillRect(x + width - lineThickness, y, x + width, y + height, color)
    }


    fun drawImage(image: Image, offX:Int, offY:Int){
        //not Render
        if(offX< -image.w) return
        if(offY< -image.h) return
        if(offX >= pW) return
        if(offY >= pH) return

        var newX:Int = 0
        var newY:Int = 0
        var newWidth:Int = image.w
        var newHeight:Int = image.h

        //Cliping code
        if(offX <0){
            newX -= offX
        }
        if(offY <0){
            newY -= offY

        }

        if(newWidth + offX > pW){
            newWidth -= (newWidth +offX- pW)

        }
        if(newHeight + offY > pH){
            newHeight -= (newHeight +offY- pH)

        }
        for (y in newY until newHeight) {

            for (x in newX until newWidth) {
                setPixel(x+offX,y+offY,image.p[y+x*image.w])
            }
        }
    }

    fun drawLine(x0: Int, y0: Int, x1: Int, y1: Int, color: Int) {
        var dx = Math.abs(x1 - x0)
        var dy = Math.abs(y1 - y0)
        var sx = if (x0 < x1) 1 else -1
        var sy = if (y0 < y1) 1 else -1
        var err = dx - dy

        var x = x0
        var y = y0

        while (true) {
            setPixel(x, y, color)

            if (x == x1 && y == y1) break

            val e2 = 2 * err
            if (e2 > -dy) {
                err -= dy
                x += sx
            }
            if (e2 < dx) {
                err += dx
                y += sy
            }
        }
    }

    fun drawCircle(centerX: Int, centerY: Int, radius: Int, color: Int) {
        var x = radius
        var y = 0
        var err = 0

        while (x >= y) {
            setPixel(centerX + x, centerY + y, color)
            setPixel(centerX + y, centerY + x, color)
            setPixel(centerX - y, centerY + x, color)
            setPixel(centerX - x, centerY + y, color)
            setPixel(centerX - x, centerY - y, color)
            setPixel(centerX - y, centerY - x, color)
            setPixel(centerX + y, centerY - x, color)
            setPixel(centerX + x, centerY - y, color)

            y++
            if (err <= 0) {
                err += 2 * y + 1
            }
            if (err > 0) {
                x--
                err -= 2 * x + 1
            }
        }
    }


    fun drawText(text: String, offX: Int, offY: Int, color: Int) {
        var textUpper = text.toUpperCase()
        var offset: Int = 0
        for (i in textUpper.indices) {
            val unicode: Int = textUpper.codePointAt(i) - 32

            if (unicode < 0 || unicode >= font.width.size) {
                println("Warning: Unsupported character '${textUpper[i]}' with unicode $unicode")
                continue  // Überspringe das Zeichen, wenn es außerhalb des gültigen Bereichs liegt
            }

            for (y in 0 until font.fontImage.h) {
                for (x in 0 until font.width[unicode]) {
                    val pixelIndex = (x + font.offsets[unicode]) + y * font.fontImage.w
                    if (font.fontImage.p[pixelIndex] == 0xFFFFFFFF.toInt()) {
                        setPixel(x + offX + offset, y + offY, color)
                    }
                }
            }
            offset += font.width[unicode]
        }
    }

    fun drawImgeTile(image: ImageTile, offX: Int, offY: Int, tileX: Int, tileY: Int) {
//        // Überprüfe die Grenzen des Bildes
//        if (offX < -image.tileW || offY < -image.tileH || offX >= pW || offY >= pH) return

        val tileWidth = image.tileW
        val tileHeight = image.tileH

        var newX = 0
        var newY = 0
        var newWidth = image.tileW
        var newHeight = image.tileH
//
//        // Clipping-Code
//        if (offX < 0) {
//            newX -= offX
//        }
//        if (offY < 0) {
//            newY -= offY
//        }
//        if (newWidth + offX > pW) {
//            newWidth -= (newWidth + offX - pW)
//        }
//        if (newHeight + offY > pH) {
//            newHeight -= (newHeight + offY - pH)
//        }
        // Berechnung des Tile-Startpunkts im Tilemap-Bild

        val tileStartX = tileX * tileWidth
        val tileStartY = tileY * tileHeight

        for (y in newY until newHeight) {
            for (x in newX until newWidth) {
                // Berechnung der Position innerhalb des Tiles
                val tileXInTile = x % tileWidth
                val tileYInTile = y % tileHeight

                // Berechnung des Index im Pixel-Array des Tilemap-Bildes
                val pixelIndex = (tileStartY + tileYInTile) * image.w + (tileStartX + tileXInTile)

                // Überprüfen, ob der Index innerhalb des Bildes liegt
                if (pixelIndex in image.p.indices) {

                    setPixel(x + offX, y + offY, image.p[(x+tileX * image.tileW) +(y+tileY * image.tileH) * image.w])
                }
            }
        }
    }
}