package GameObjects

class GameObjectManager {

    var GameObjectList = ArrayList<GameObject>();

    fun addGameObject(o:GameObject){
        GameObjectList.add(o)
    }

    fun removeGameObject(o:GameObject){
        GameObjectList.remove(o)
    }
}