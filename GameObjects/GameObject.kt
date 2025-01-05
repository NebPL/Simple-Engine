package GameObjects

import engineCode.GameContainer
import engineCode.renderer

abstract class GameObject {


    abstract var boundingBox:boundingBox

    fun updateBoundingBox_X(x: Int) {
        boundingBox.x += x
    }

    fun updateBoundingBox_Y(y: Int) {
        boundingBox.y += y
    }

    abstract fun update(gc: GameContainer, dt:Float)
    abstract fun render(gc: GameContainer, r: renderer)
}