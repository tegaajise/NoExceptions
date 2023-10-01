function functionName(parameter1, parameter2) {
	// instructions that function executes when called
}

function isEven(number) {
	if (number%2 == 0) {
		return true
	} else {
		return false
	}
}


var numberOfPlayers = [35, 23, 32, 30, 30, 31, 28, 27, 33, 24]
var useTieBreaker = []
for (var i=0; i<numberOfStudents.length; i++) {
	useTieBreaker.push(isEven(numberOfStudents[i]));
}

// code here can NOT use carName

function myFunction() {
  var carName = "Volvo";

  // code here CAN use carName

}

var carName = "Volvo";

// code here can use carName

function myFunction() {

  // code here can also use carName

}

// function callbacks
function isEven(number) {
	return number%2 == 0;
}
var numbers = [1, 2, 4, 7, 3, 5, 6]
var evenNumbers = numbers.filter(isEven)
console.log(evenNumbers) // [2, 4, 6]

var totalFoodSupply = 1000

function Character(name) {
  this.name = name
  this.health = 100
  this.eat = function() {
    this.health = this.health+1
    totalFoodSupply--
  }
  this.run = function() {
    this.health = this.health-1
  }
  this.report = function() {
      return this.name + ", " + this.health + "% health"
  }
}

function attack(character, damage) {
  var message
  character.health-=damage
  if (character.health <= 0) {
  	message = character.name + " has no life left. It's game over for" + character.name
  } else if (0 < character.health && character.health < 25) {
    message = character.name + " has less than a quarter of life left. You must find food close by before you get attacked again or you could die."
  } else if (25 <= character.health && character.health < 50) {
    message = character.name + " has less than a half of life left. You should find food before you get attacked again."
  } else {
    message = character.name + " is in decent shape but maintain your health by keeping a good source of food available."
  }
  return message
}

// game simulation
var dormin = new Character("Dormin")
var lukan = new Character("Lukan")
var dorminMessage = "A new player just entered the game: " + dormin.report()
var lukanMessage = "A new player just entered the game: " + lukan.report()
console.log(dorminMessage)
console.log(lukanMessage)
for (var i=0; i<30; i++) {
	dormin.run()
}
for (var i=0; i<15; i++) {
	dormin.run()
}
console.log(attack(dormin))
console.log(attack(lukan))
console.log(dormin.report())
console.log(lukan.report())