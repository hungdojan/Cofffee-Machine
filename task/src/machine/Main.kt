package machine

import java.util.*

var waterInMachine = 400 // ml
var milkInMachine = 540 // ml
var beansInMachine = 120 // g
var cups = 9 // ks
var money = 550 // $

fun main() {
    val sc = Scanner(System.`in`)
    printMachineState()
    var continueProgram: Boolean
    do {
        continueProgram = inputAction(sc)
    } while (continueProgram)
}

fun printMachineState() {
    println("The coffee machine has:")
    println("$waterInMachine of water")
    println("$milkInMachine of milk")
    println("$beansInMachine of coffee beans")
    println("$cups of disposable cups")
    println("$$money of money")
}

fun inputAction(sc: Scanner): Boolean {
    print("Write action (buy, fill, take, remaining, exit): ")
    when (sc.nextLine()) {
        "buy" -> makeCoffee(sc)
        "fill" -> refillIngredients(sc)
        "take" -> takeMoney()
        "remaining" -> printMachineState()
        "exit" -> return false
        else -> println("Unknown action!")
    }
    return true
}

fun makeCoffee(sc: Scanner) {
    print("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ")
    when (sc.nextLine()) {
        "1" -> makeEspresso()
        "2" -> makeLatte()
        "3" -> makeCappuccino()
        "back" -> return
        else -> println("Unknown action!")
    }
}

fun refillIngredients(sc: Scanner) {
    print("Write how many ml of water do you want to add: ")
    waterInMachine += sc.nextInt()
    print("Write how many ml of milk do you want to add: ")
    milkInMachine += sc.nextInt()
    print("Write how many grams of coffee beans do you want to add: ")
    beansInMachine += sc.nextInt()
    print("Write how many disposable cups of coffee do you want to add: ")
    cups += sc.nextInt()
}

fun takeMoney() {
    println("I gave you $$money")
    money = 0
}

fun makeEspresso() {
    val waterNeeded = 250
    val beansNeeded = 16
    val price = 4
    controlIngredients(waterNeeded = waterNeeded, beansNeeded = beansNeeded, price = price)
}

fun makeLatte() {
    val waterNeeded = 350
    val milkNeeded = 75
    val beansNeeded = 20
    val price = 7
    controlIngredients(waterNeeded, milkNeeded, beansNeeded, price)
}

fun makeCappuccino() {
    val waterNeeded = 200
    val milkNeeded = 100
    val beansNeeded = 12
    val price = 6
    controlIngredients(waterNeeded, milkNeeded, beansNeeded, price)
}

fun controlIngredients(waterNeeded: Int = 0, milkNeeded: Int = 0, beansNeeded: Int = 0, price: Int){
    when {
        waterNeeded > waterInMachine -> println("Sorry, not enough water!")
        milkNeeded > milkInMachine -> println("Sorry, not enough milk!")
        beansNeeded > beansInMachine -> println("Sorry, not enough coffee beans!")
        cups <= 0 -> println("Sorry, not enough disposable cups!")
        else -> {
            waterInMachine -= waterNeeded
            milkInMachine -= milkNeeded
            beansInMachine -= beansNeeded
            cups--
            money += price
            println("I have enough resources, making you a coffee!")
        }
    }
}