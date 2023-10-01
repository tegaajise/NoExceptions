function Mario() {
  var gameUI = GameUI.getInstance();

  this.type = 'small';
  this.x;
  this.y;
  this.width = 32;
  this.height = 44;
  this.speed = 3;
  this.velX = 0;
  this.velY = 0;
  this.jumping = false;
  this.grounded = false;
  this.invulnerable = false;
  this.sX = 0; // sprite x
  this.sY = 4; // sprite y
  this.frame = 0;

  // Task 2: Why do we set the value of var this to that?
  /*Easy to distinguish the initializations from the mutilizations in the methods below*/

  var that = this;

  this.init = function() {
    that.x = 10;
    that.y = gameUI.getHeight() - 40 - 40;

    marioSprite = new Image();
    marioSprite.src = 'images/mario-sprites.png';
  };

  this.draw = function() {
    // Task 3: Set sprite x to width multiplied by the frame
    that.sX = that.width * that.frame;
    gameUI.draw(marioSprite, that.sX, that.sY, that.width, that.height, that.x, that.y, that.width, that.height);
  };

  this.checkMarioType = function() {
    // Task 4: If the type of sprite is big and the sprite is invulnerable, set the sprite Y to 276 (show //transparent Mario) and the height to 60. Else if the type of sprite is big and the sprite is not //invulnerable, set the sprite Y to 90 and the height to 60.
    // Task 5: If the type of sprite is small and the sprite is invulnerable, set the sprite Y to 222 (show //transparent Mario) and the height to 44. Else if the type of sprite is big and the sprite is not //invulnerable, set the sprite Y to 4 and the height to 44. 
    // Correct - subitted equivalent code in Repl.it
    if (that.type == 'big') {
      that.height = 60;

      //big mario sprite position
      if (that.invulnerable) {
        that.sY = 276; //if invulnerable, show transparent mario
      } else {
        that.sY = 90;
      }
    } else if (that.type == 'small') {
      that.height = 44;

      //small mario sprite
      if (that.invulnerable) {
        that.sY = 222; //if invulnerable, show transparent mario
      } else {
        that.sY = 4;
      }
    } 
    // Task 6: If the type of sprite is fire, set the height to 60 and sprite Y to 150. 
    else if (that.type == 'fire') {
      that.height = 60;

      //fire mario sprite
      that.sY = 150;
    }
  };

  this.resetPos = function() {
    // Task 7: Set the value of x to the width of canvas divided by 10.
    that.x = that.width/10
    // Task 8: Set the value of y to the height of canvas minus 40.
    that.y = that.height-40
    // Task 9: Reset the frame to 0.
    that.frame = 0;
  };
}
