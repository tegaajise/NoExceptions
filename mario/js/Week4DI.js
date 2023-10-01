// Week 4
// Code snippet 1
function Mario() {
	this.size = 'small';
	this.height = 50;
	this.jumping = true;
}

var mario = new Mario()
var element = 4
if (element.type != 5) {
	mario.jumping = false;
}

// Code snippet 2
function Car(model) {
  this.model = model
  this.speed = 0
  this.go = function(speed) {
    this.speed = speed
  }
  this.stop = function() {
    this.speed = 0
  }
  this.report = function() {
    console.log(this.model+": "+this.speed+" kmh")
  }
}

var car = new Car("Audi R8")
car.report()
car.go(100)
car.report()
car.go(60)
car.report()

var audi = new Car("Audi R8")
var bmw = new Car("BMW M5")
var mazda = new Car("Mazda 5")
var honda = new Car("Honda Fit")
audi.report()
bmw.report()
mazda.report()
honda.report()

car.go = function(delta) {
	this.speed+=delta
}
car.go(40) // "go" behaves differently
car.report()
car.stop()
car.report()

// Code snippet 3
var car = {
  model: "no model yet",
  speed: 0,
  go(speed) {
    this.speed = speed
  },
  stop() {
    this.speed = 0
  },
  report() {
    console.log(this.model+": "+this.speed+" kmh")
  }
}
