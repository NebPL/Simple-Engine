package examples

import GameObjects.GameObject
import GameObjects.GameObjectManager
import GameObjects.boundingBox
import engineCode.GameContainer
import engineCode.renderer
import util.blue
import util.green
import util.red
import util.white
import java.awt.event.KeyEvent

private var manager = GameObjectManager()
private var e = GameContainer(manager)

fun main(){
    manager.addGameObject(Player())
    manager.addGameObject(Wall())

    e.width = 300
    e.height = 300
    e.title = "Collison"
    e.scale = 2F
    e.start()
}

class Player() : GameObject() {
    override var boundingBox: boundingBox = boundingBox(10,10,20,20,false)

    init {
        e.collisonManager.addBoundingBox(boundingBox)
    }
    override fun update(gc: GameContainer, dt: Float) {
        //Collison
        if(gc.input?.isKey(KeyEvent.VK_A)!!){
            boundingBox.x -= 3

        }
        if(gc.input?.isKey(KeyEvent.VK_D)!!){
            boundingBox.x+= 3

        }
        if(gc.input?.isKey(KeyEvent.VK_W)!!){
            boundingBox.y -= 3

        }
        if(gc.input?.isKey(KeyEvent.VK_S)!!){
            boundingBox.y += 3
        }
    }

    override fun render(gc: GameContainer, r: renderer) {
        //Draw a Text
        r.fillRect(boundingBox.x,boundingBox.y,boundingBox.x + boundingBox.width,boundingBox.y + boundingBox.height, white())

    }
}

class Wall() : GameObject() {
    override var boundingBox: boundingBox = boundingBox(100,100,20,20,false)

    init {
        e.collisonManager.addBoundingBox(boundingBox)
    }

    override fun update(gc: GameContainer, dt: Float) {
        //Update
    }

    override fun render(gc: GameContainer, r: renderer) {
        if(boundingBox.colliding){
            r.fillRect(boundingBox.x,boundingBox.y,boundingBox.x + boundingBox.width,boundingBox.y + boundingBox.height, green())
        }else r.fillRect(boundingBox.x,boundingBox.y,boundingBox.x + boundingBox.width,boundingBox.y + boundingBox.height, white())
    }
}
