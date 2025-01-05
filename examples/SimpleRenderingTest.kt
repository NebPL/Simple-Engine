package examples

import GameObjects.GameObject
import GameObjects.GameObjectManager
import GameObjects.boundingBox
import engineCode.GameContainer
import engineCode.renderer
import util.blue
import util.red
import util.white

private var manager = GameObjectManager()

private var e = GameContainer(manager)

fun main(){
    manager.addGameObject(SimpleRender())

    e.width = 300
    e.height = 300
    e.title = "Rendering"
    e.scale = 2F
    e.start()
}

class SimpleRender() : GameObject() {
    override var boundingBox: boundingBox = boundingBox(10,10,20,20,false)//The False is just for if the object was colliding it would turn it to True.


    override fun update(gc: GameContainer, dt: Float) {
        //Updates
    }

    override fun render(gc: GameContainer, r: renderer) {
        //Draw a Text
        r.drawText("Fps: " + e.fps.toString(),10,10, white())
        r.drawBorder(10,20,100,100,3, red())
        r.fillRect(10,30+100,10+100,30+100+100, blue())
    }
}