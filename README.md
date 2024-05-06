
# CHESS GAME USING JAVA

Its java based chess game build using the java swing. It is build to deepen my knowledge on the Object-Oriented Programming and improve my logic building skill.After building this project now I understand why is it important to have good knowledge on the DSA as a programmer.

## Screenshots

![App Screenshot](https://github.com/AsgarGeorge/Chess_Game/blob/main/ScreenShots/Screenshot%202024-05-05%20at%205.18.00%E2%80%AFPM.png)

## Features

- Shows Legal Moves
- Check Detection
- 1v1 Offline
- 60 FPS


## Game Loop

the run method is executed while calling the thread function.
the loop has been build using variables like delta, drawInterval,and using the system current time functions.
the update and repaint method should run every 1/60 of the second to get the frame rate of 60.The `update` method deals with logic behind the pieces and the movement related to them. The `repaint` method deals with the graphics related with the game java AWT paintComponent is used to draw the board as well as pieces.All the code are run under this methods appropriately.

```bash
  public void run() {
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;


        while(gameThread != null){
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime)/drawInterval;
            lastTime = currentTime;
            if(delta >= 1) {
                update();
                repaint();

                delta--;
            }
        }
    }
 ```
## Run Locally

Clone the project

```bash
  git clone https://github.com/AsgarGeorge/Chess_Game.git
```

Go to the project directory

```bash
  cd my-project
```

Run application

```bash
run Main.java
```



## Future improvements

- make it online playable
- multiple game modes like chess 360
- connect to AI chess bots
- multiple themes


## Acknowledgements

- the game is built by watching [@RyiSnow](https://www.youtube.com/@RyiSnow)

## Contributing

Contributions are always welcome!

feel free to contribute, if intrested built related from the future improvements section.

## Feedback

If you have any feedback,or doubt related to code. please reach out to us at asgarantony@gmail.com


