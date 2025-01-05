# A Simple Engine
This is a Engine from a Youtube Tutorial. 
The Youtuber is: Majoolwip (Link: https://www.youtube.com/watch?v=4iPEjFUZNsw&list=PL7dwpoQd3a8j6C9p5LqHzYFSkii6iWPZF&ab_channel=Majoolwip).

This documentation has some visuel Problem look in the examples folder.


Documentation: Creating a Simple Game Engine

This documentation explains the core concepts of rendering, handling input, adding game objects, and implementing collision detection in a basic game engine using Kotlin. We'll use the example of creating and managing simple rectangles, one movable and one stationary.

1. Adding Game Objects

Game objects represent entities in the game world. You can create and add them to the game using a GameObjectManager.

Key Components

GameObject: Base class for all game objects.

GameObjectManager: Manages a collection of game objects.

Example

var manager: GameObjectManager = GameObjectManager()

manager.addGameObject(renderTest())
manager.addGameObject(wall())

Steps to Add a Game Object

Create a new class inheriting from GameObject.

Implement the required methods: update and render.

Instantiate the object and add it to the GameObjectManager using addGameObject.

Complete Example

class renderTest() : GameObject() {
    override var boundingBox: boundingBox = boundingBox(10, 10, 20, 20)

    override fun update(gc: GameContainer, dt: Float) {
        // Handle input or game logic
    }

    override fun render(gc: GameContainer, r: renderer) {
        r.fillRect(boundingBox.x, boundingBox.y, boundingBox.x + boundingBox.width, boundingBox.y + boundingBox.height, white())
    }
}

manager.addGameObject(renderTest())

2. Rendering

Rendering is the process of drawing shapes, objects, or other elements on the screen. Here's how you can render rectangles in this project:

Key Components

renderer: Handles all rendering operations.

fillRect: Draws a filled rectangle.

Example

override fun render(gc: GameContainer, r: renderer) {
    r.fillRect(boundingBox.x, boundingBox.y, boundingBox.x + boundingBox.width, boundingBox.y + boundingBox.height, white())
}

In this code:

boundingBox specifies the position and size of the rectangle.

white() is the color used for the rectangle.

Steps to Render an Object

Define a boundingBox with position and size.

Use the renderer object to draw the rectangle using the fillRect method.

3. Handling Input

Input handling allows the player to interact with the game. In this project, keyboard inputs are used to move a rectangle.

Key Components

input: Manages user inputs.

isKey: Checks if a specific key is pressed.

Example

if (gc.input?.isKey(KeyEvent.VK_A) == true) {
    boundingBox.x -= 3
}
if (gc.input?.isKey(KeyEvent.VK_D) == true) {
    boundingBox.x += 3
}
if (gc.input?.isKey(KeyEvent.VK_W) == true) {
    boundingBox.y -= 3
}
if (gc.input?.isKey(KeyEvent.VK_S) == true) {
    boundingBox.y += 3
}

Steps to Handle Input

Access the input object from the GameContainer.

Use isKey to check if a key is pressed.

Adjust the position of the object based on the key pressed.

4. Collision Detection

Collision detection determines if two objects in the game overlap or interact.

Key Components

boundingBox: Defines the area of an object.

Collision Manager: Manages all bounding boxes and detects overlaps.

Example

e.collisionManager.addBoundingBox(boundingBox)

Here:

The boundingBox of each object is registered with the collision manager.

Although the current implementation doesn't handle collision responses, adding bounding boxes to the manager sets up the framework for future collision detection.

Steps for Collision Detection

Define a boundingBox for each object.

Register the bounding box with the collision manager.

Implement logic to check for overlaps (to be added).

5. Building a Complete Game Loop

A game loop handles updates and rendering continuously.

Key Components

Update: Handles game logic like movement and interactions.

Render: Draws objects to the screen.

Example

override fun update(gc: GameContainer, dt: Float) {
    // Handle input or game logic
}

override fun render(gc: GameContainer, r: renderer) {
    // Draw game objects
}

Steps

Define an update method to handle game logic.

Define a render method to draw objects.

Call these methods continuously in the game loop.

Conclusion

This guide provides the basics of rendering, handling input, adding game objects, and setting up collision detection in a simple game engine. By following these steps, you can expand the project to include more advanced features like animations, physics, and complex interactions.


