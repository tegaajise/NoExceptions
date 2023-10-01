// Task 1. Consider the following code snippet and complete
// the code
function Car(model) {
  this.model = model
  this.speed = 0
  this.gas = 47
  // Update the following method to check if
  // there is enough gas, i.e.: gas > 0 to increase
  // speed of the car by 10. If there is enough gas, 
  // the speed is changed and the gas decreases by 0.25,
  // otherwise, nothing happens.
  this.go = function() {
  	// Write code here
  }
  this.stop = function() {
    this.speed = 0
  }
  this.toString = function() {
    return this.model + ": " + this.speed + " kmh"
  }
}
// Task 2. Write a function that defines a person object.
// A person has the following attributes:
// - a name
// - an age
// - a car (Honda Civic model)
// A person has the following methods:
// - drive the car only if person is 16 or older 
// - i.e.: Can call 'go' on car if the person is 16 or older
function Person(name, age, car) {
	// Write code here
	this.drive = function() {
		// write code here
	}
	this.toString = function() {
		return this.name + ", " + this.age + ", has a " + this.car.toString()
	}
}

// Use case
var mazda = new Car("Mazda 5")
var honda = new Car("Honda Fit")

var oliad = new Person("Oliad", 15, honda)
var sareet = new Person("Sareet", 25, mazda)

console.log(oliad.toString())
console.log(sareet.toString())
oliad.drive()
sareet.drive()
console.log(oliad.toString())
console.log(sareet.toString())
 
/* Expected result
Oliad, 15, has a Honda Fit: 0 kmh
Sareet, 25, has a Mazda 5: 0 kmh
Oliad, 15, has a Honda Fit: 0 kmh
Sareet, 25, has a Mazda 5: 10 kmh
*/

// Extra work:
// Task 3. Write a function that defines a super-hero object.
// A super-hero has the following attributes:
// - a name
// - a power
// - health (starts at 100)
// - lives (starts at 5)
