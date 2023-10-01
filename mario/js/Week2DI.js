var budget = 50 // change
var totalSpent = 0

var saltPrice = 5
var vegOilPrice = 20
var pineapplePrice = 10

console.log("Before shopping")
console.log("Wallet: $" + budget)
console.log("Total spent: $" + totalSpent)

budget = budget - saltPrice
totalSpent = totalSpent + saltPrice
console.log("\nBought salt")
console.log("Wallet: $" + budget)
console.log("Total spent: $" + totalSpent)

budget = budget - vegOilPrice
totalSpent = totalSpent + vegOilPrice
console.log("\nBought veg. oil")
console.log("Wallet: $" + budget)
console.log("Total spent: $" + totalSpent)

budget = budget - pineapplePrice
totalSpent = totalSpent + pineapplePrice
console.log("\nBought pineapple")
console.log("Wallet: $" + budget)
console.log("Total spent: $" + totalSpent)

console.log("\nAfter shopping")
console.log("Wallet: $" + budget)
console.log("Total spent: $" + totalSpent)

if (totalSpent > budget) {
  console.log("\nYou've overspent!")
} else {
  console.log("\nWell done, you've kept to your budget.")
}
