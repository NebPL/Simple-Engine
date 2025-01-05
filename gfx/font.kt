package de.Neb.gfx

import Image

class font(path:String){




    var fontImage: Image
    var offsets = IntArray(90) // Array, das die Offset-Positionen jedes Zeichens speichert
    var width = IntArray(90)   // Array, das die Breite jedes Zeichens speichert

    init {
        fontImage = Image(path) // Bild mit der Schriftart laden

        var unieCode: Int = 0

        // Schleife durch jedes Pixel im Bild
        for (i in 0 until fontImage.w) {
            // Wenn der Pixel blau ist (Start des Zeichens)
            if (fontImage.p[i] == 0xff0000ff.toInt()) {
                offsets[unieCode] = i // Speichern des Offsets (Start des Zeichens)
            }
            // Wenn der Pixel gelb ist (Ende des Zeichens)
            if (fontImage.p[i] == 0xffffff00.toInt()) {
                // Berechne die Breite des Zeichens
                width[unieCode] = i - offsets[unieCode]
                unieCode++ // Gehe zum nächsten Zeichen
            }
        }
    }
}