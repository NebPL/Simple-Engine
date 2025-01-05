package engineCode

import java.awt.event.*
//class Input(gc:GameContainer):KeyListener, MouseListener, MouseMotionListener, MouseWheelListener{
//
//
//
//    override fun keyTyped(e: KeyEvent?) {
//    }
//
//    override fun keyPressed(e: KeyEvent?) {
//    }
//
//    override fun keyReleased(e: KeyEvent?) {
//    }
//
//    override fun mouseClicked(e: MouseEvent?) {
//    }
//
//    override fun mousePressed(e: MouseEvent?) {
//    }
//
//    override fun mouseReleased(e: MouseEvent?) {
//    }
//
//    override fun mouseEntered(e: MouseEvent?) {
//    }
//
//    override fun mouseExited(e: MouseEvent?) {
//    }
//
//    override fun mouseDragged(e: MouseEvent?) {
//    }
//
//    override fun mouseMoved(e: MouseEvent?) {
//    }
//
//    override fun mouseWheelMoved(e: MouseWheelEvent?) {
//
//    }
//
//
//}


class Input(gc: GameContainer) :KeyListener,MouseListener,MouseMotionListener,MouseWheelListener{
    var gc = gc

    val NUMBER_KEYS = 256
    var keys = Array(NUMBER_KEYS) {false}
    var keysLast = Array(NUMBER_KEYS) {false}

    val NUMBER_BUTTONS = 5
    var buttons = Array(NUMBER_KEYS) {false}
    var buttonLast = Array(NUMBER_KEYS) {false}

    var mouseX:Int = 0
    var mouseY:Int = 0
    var scroll:Int = 0
    init {
        this.gc = gc

        gc.window?.canvas?.addKeyListener(this)
        gc.window?.canvas?.addMouseListener(this)
        gc.window?.canvas?.addMouseMotionListener(this)
        gc.window?.canvas?.addMouseWheelListener(this)
    }

    fun update(){
        for(i in 0 until NUMBER_KEYS){
            keysLast[i] = keys[i]
        }
        for(i in 0 until NUMBER_BUTTONS){
            buttonLast[i] = buttons[i]
        }
    }

    fun isKey(keyCode:Int):Boolean{
        return keys[keyCode]
    }
    fun isKeyUp( keyCode:Int):Boolean{
        return !keys[keyCode] && keysLast[keyCode ]
    }
    fun isKeyDown( keyCode:Int):Boolean{
        return keys[keyCode] && !keysLast[keyCode ]
    }

    fun isButton( keyCode:Int):Boolean{
        return buttons[keyCode]
    }
    fun isButtonUp( keyCode:Int):Boolean{
        return !buttons[keyCode] && buttonLast[keyCode ]
    }
    fun isButtonDown( keyCode:Int):Boolean{
        return buttons[keyCode] && !buttonLast[keyCode ]
    }


    override fun keyTyped(e: KeyEvent?) {

    }

    override fun keyPressed(e: KeyEvent?) {
        keys[e?.keyCode!!] = true
    }

    override fun keyReleased(e: KeyEvent?) {
        keys[e?.keyCode!!] = false
    }

    override fun mouseClicked(e: MouseEvent?) {
    }

    override fun mousePressed(e: MouseEvent?) {
        buttons[e?.button!!] = true
    }

    override fun mouseReleased(e: MouseEvent?) {
        buttons[e?.button!!] = false
    }

    override fun mouseEntered(e: MouseEvent?) {
    }

    override fun mouseExited(e: MouseEvent?) {
    }

    override fun mouseDragged(e: MouseEvent?) {
        mouseY = e?.y!! / gc.scale.toInt()
        mouseX = e.x/ gc.scale.toInt()
    }

    override fun mouseMoved(e: MouseEvent?) {
        mouseY = e?.y!! / gc.scale.toInt()
        mouseX = e.x / gc.scale.toInt()
    }

    override fun mouseWheelMoved(e: MouseWheelEvent?) {
        scroll = e?.wheelRotation!!
    }

}