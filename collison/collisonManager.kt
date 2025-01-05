package collision

import GameObjects.boundingBox

class collisonManager {

    var boundingBoxes = ArrayList<boundingBox>()

    fun addBoundingBox(b: boundingBox) {
        boundingBoxes.add(b)
    }

    fun removeBoundingBox(b: boundingBox) {
        boundingBoxes.remove(b)
    }

    fun findBoundingBox(b: boundingBox): boundingBox? {
        for (i in 0 until boundingBoxes.size) {
            if (b == boundingBoxes[i]) {
                return boundingBoxes[i]
            }
        }
        error("Bounding box nicht gefunden!")
        return null
    }

    fun checkCollision() {
        for (i in 0 until boundingBoxes.size) {
            val boxA = boundingBoxes[i]
            for (j in i + 1 until boundingBoxes.size) {
                val boxB = boundingBoxes[j]

                if (isColliding(boxA, boxB)) {
                    resolveCollision(boxA, boxB)
                }
            }
        }
    }

    private fun isColliding(a: boundingBox, b: boundingBox): Boolean {
        return a.x < b.x + b.width &&
                a.x + a.width > b.x &&
                a.y < b.y + b.height &&
                a.y + a.height > b.y
    }

    private fun resolveCollision(a: boundingBox, b: boundingBox) {
        val overlapX = if (a.x + a.width / 2 < b.x + b.width / 2) {
            (a.x + a.width) - b.x
        } else {
            a.x - (b.x + b.width)
        }

        val overlapY = if (a.y + a.height / 2 < b.y + b.height / 2) {
            (a.y + a.height) - b.y
        } else {
            a.y - (b.y + b.height)
        }

        // Weniger Überlappung korrigieren
        if (Math.abs(overlapX) < Math.abs(overlapY)) {
            a.x -= overlapX
        } else {
            a.y -= overlapY
        }
    }
}