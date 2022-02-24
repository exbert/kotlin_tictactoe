//package tictactoe
import java.lang.Math.abs
import java.util.Collections
import java.util.Scanner

fun main() {
    // write your code here
    //println("Enter cells: ")
    val userInput = "_________"
    var boardArray = userInput.split("")
    var playCheckpoint = false
    var gameFinished = false
    var playerMove = "X"
    var systemMessage = ""
    drawBoardFirst(boardArray)
/*
    game@ while (!decision) {
        if (boardArray.contains("X") || boardArray.contains("O") || boardArray.contains("_")){
            for (theCount in 1..9) {
                if (boardArray[theCount] == "X") xNumber++
                if (boardArray[theCount] == "O") oNumber++
                if (boardArray[theCount] == "_") emptyNumber++
            }
            if (((xNumber == 4 || oNumber == 4) && (emptyNumber >= 2)) || abs(xNumber - oNumber) >= 2) {
                //print("Impossible")
                decision = true
                break@game
            }
            for (theIndex in 0..7) {
                var (x, y, z) = winPosition[theIndex]
                if (listOf(boardArray[x], boardArray[y], boardArray[z]).all {it == "X"}){
                    tripleXFound++
                }
                if (listOf(boardArray[x], boardArray[y], boardArray[z]).all {it == "O"}){
                    tripleOFound++
                }
            }
            if (tripleOFound + tripleXFound >= 2) {
                //print("Impossible")
                decision = true
                break@game
            }
            for (theIndex in 0..7) {
                var (x, y, z) = winPosition[theIndex]
                if (listOf(boardArray[x], boardArray[y], boardArray[z]).all {it == "X"}){
                    //print("X wins")
                    decision = true
                    break@game
                }
                if (listOf(boardArray[x], boardArray[y], boardArray[z]).all {it == "O"}){
                    //print("O wins")
                    decision = true
                    break@game
                }
            }
            if (emptyNumber != 0) {
                //print("Game not finished")
                decision = true
                break@game
            }
            if (emptyNumber == 0 && (xNumber + oNumber) == 9) {
                //print("Draw")
                decision = true
                break@game
            }

        }
    }*/
    play@ while (playCheckpoint == false || gameFinished == false) {
        //println("here55")
        print("Enter the coordinates: ")
        val theXY = readln().split(" ").map { it.toIntOrNull()}
        var (tempplayCheckpoint , tempboardArray) = checkValidInput(theXY,boardArray, playerMove)
        var (tempSystemMessage, tempGameFinished) = gameDecision(tempboardArray)
        gameFinished = tempGameFinished
        systemMessage = tempSystemMessage
        if (gameFinished == true) {
            println(systemMessage)
            break@play
        }
        playerMove = swapPlayer(playerMove)
        playCheckpoint = tempplayCheckpoint
        boardArray = tempboardArray
        //println("Ending $playCheckpoint $gameFinished $boardArray")
    }
}

fun checkValidInput (theInput: List<Int?>, theBoard: List<String>, playerMove: String): Pair < Boolean, List<String>> {
    var theXY = theInput
    var checkpoint = false
    var boardcheck = false
    var tempTheBoard = theBoard
    var newPlayerMove = playerMove
    //println("checkValidInput")
    while (checkpoint == false) {
        if(theXY[0] in 1..3 && theXY[1] in 1..3) {
            val (temp_boardcheck: Boolean, theBoardSecond: List<String> ) = checkEmptyPosition(theXY, tempTheBoard, playerMove)
            //println("$temp_boardcheck $theBoardSecond")
            boardcheck = temp_boardcheck
            tempTheBoard = theBoardSecond
        }

        //boardcheck = temp_boardcheck
        //println("here the $boardcheck")
        when {
            theXY[0] == null -> {
                println("You should enter numbers!")
                print("Enter the coordinates: ")
                theXY = readln().split(" ").map { it.toIntOrNull() }

            }
            (theXY[0] !in 1..3 || theXY[1] !in 1..3) -> {
                println("Coordinates should be from 1 to 3!")
                print("Enter the coordinates: ")
                theXY = readln().split(" ").map { it.toIntOrNull() }
            }
            (!boardcheck) -> {
                //println("I am here $boardcheck")
                print("Enter the coordinates: ")
                theXY = readln().split(" ").map { it.toIntOrNull() }
            }
            else -> {
                //println("everything is ok")
                //checkEmptyPosition(theXY, theBoard)
                //println("going to draw the board")
                //println("checkValidInput PlayerMove = $newPlayerMove")
                var ( theBoard, tempPlayerMove ) = drawBoard(theXY, tempTheBoard, newPlayerMove)
                tempTheBoard = theBoard
                newPlayerMove = tempPlayerMove
                checkpoint = true
            }

        }
    }
    return Pair(checkpoint,tempTheBoard)
}
fun checkEmptyPosition (theXY: List<Int?> ,theTempBoard: List<String>, playerMove: String): Pair <Boolean,List<String>> {
    //println(theXY)
    var checkpoint = false
    var theBoard = theTempBoard.toMutableList()
    //println("checkEmptyPosition")
    //println("$theBoard")
    check@ while (checkpoint == false) {
        when  {
            theXY[0] == 1 && theXY[1] == 1 -> {
                if (theBoard[1] == "_" || theBoard[1] == " ") {
                    theBoard[1] = playerMove
                    //drawBoard(theBoard)
                    checkpoint = true
                } else {
                    println("This cell is occupied! Choose another one!")
                    break@check
                }

            }
            theXY[0] == 1 && theXY[1] == 2 ->  {
                if (theBoard[2] == "_" || theBoard[2] == " ") {
                    theBoard[2] = playerMove
                    //drawBoard(theBoard)
                    checkpoint = true
                } else {
                    println("This cell is occupied! Choose another one!")
                    break@check
                }

            }
            theXY[0] == 1 && theXY[1] == 3 ->  {
                if (theBoard[3] == "_" || theBoard[3] == " ") {
                    theBoard[3] = playerMove
                    //drawBoard(theBoard)
                    checkpoint = true
                } else {
                    println("This cell is occupied! Choose another one!")
                    break@check
                }

            }
            theXY[0] == 2 && theXY[1] == 1 ->  {
                if (theBoard[4] == "_" || theBoard[4] == " ") {
                    theBoard[4] = playerMove
                    //drawBoard(theBoard)
                    checkpoint = true
                } else {
                    println("This cell is occupied! Choose another one!")
                    break@check
                }

            }
            theXY[0] == 2 && theXY[1] == 2 ->  {
                if (theBoard[5] == "_" || theBoard[5] == " ") {
                    theBoard[5] = playerMove
                    //drawBoard(theBoard)
                    checkpoint = true
                } else {
                    println("This cell is occupied! Choose another one!")
                    break@check
                }

            }
            theXY[0] == 2 && theXY[1] == 3 ->  {
                if (theBoard[6] == "_" || theBoard[6] == " ") {
                    theBoard[6] = playerMove
                    //drawBoard(theBoard)
                    checkpoint = true
                } else {
                    println("This cell is occupied! Choose another one!")
                    break@check
                }

            }
            theXY[0] == 3 && theXY[1] == 1 ->  {
                if (theBoard[7] == "_" || theBoard[7] == " ") {
                    theBoard[7] = playerMove
                    //drawBoard(theBoard)
                    checkpoint = true
                } else {
                    println("This cell is occupied! Choose another one!")
                    break@check
                }

            }
            theXY[0] == 3 && theXY[1] == 2 ->  {
                if (theBoard[8] == "_" || theBoard[8] == " ") {
                    theBoard[8] = playerMove
                    //drawBoard(theBoard)
                    checkpoint = true
                } else {
                    println("This cell is occupied! Choose another one!")
                    break@check
                }

            }
            theXY[0] == 3 && theXY[1] == 3 ->  {
                if (theBoard[9] == "_" || theBoard[9] == " ") {
                    theBoard[9] = playerMove
                    //drawBoard(theBoard)
                    checkpoint = true
                } else {
                    println("This cell is occupied! Choose another one!")
                    break@check
                }
            }
        }
    }
    //println("Returning from empty check $checkpoint and $theBoard")
    //println("checkEmptyPosition PlayerMove = $playerMove")
    return Pair(checkpoint,theBoard)
}

fun drawBoard (theXY: List<Int?>, tempBoardArray: List<String>, playerMove: String): Pair<List<String>, String> {
    val boardArray = tempBoardArray.toMutableList()
    var newPlayerMove = playerMove
    //println("drawBoard")
    when  {
        theXY[0] == 1 && theXY[1] == 1 -> {
            if (boardArray[1] == "_") {
                boardArray[1] = playerMove
            }
        }
        theXY[0] == 1 && theXY[1] == 2 ->  {
            if (boardArray[2] == "_") {
                boardArray[2] = playerMove
            }
        }
        theXY[0] == 1 && theXY[1] == 3 ->  {
            if (boardArray[3] == "_") {
                boardArray[3] = playerMove
            }
        }
        theXY[0] == 2 && theXY[1] == 1 ->  {
            if (boardArray[4] == "_") {
                boardArray[4] = playerMove
            }
        }
        theXY[0] == 2 && theXY[1] == 2 ->  {
            if (boardArray[5] == "_") {
                boardArray[5] = playerMove
            }
        }
        theXY[0] == 2 && theXY[1] == 3 ->  {
            if (boardArray[6] == "_") {
                boardArray[6] = playerMove
            }
        }
        theXY[0] == 3 && theXY[1] == 1 ->  {
            if (boardArray[7] == "_") {
                boardArray[7] = playerMove
            }
        }
        theXY[0] == 3 && theXY[1] == 2 ->  {
            if (boardArray[8] == "_") {
                boardArray[8] = playerMove
            }
        }
        theXY[0] == 3 && theXY[1] == 3 ->  {
            if (boardArray[9] == "_") {
                boardArray[9] = playerMove
            }
        }
    }
    if(boardArray.contains("_")) {
        Collections.replaceAll(boardArray, "_"," ")
    }
    println("---------")
    println("| ${boardArray[1]} ${boardArray[2]} ${boardArray[3]} |")
    println("| ${boardArray[4]} ${boardArray[5]} ${boardArray[6]} |")
    println("| ${boardArray[7]} ${boardArray[8]} ${boardArray[9]} |")
    println("---------")
    if (playerMove == "X") return Pair(boardArray, "O") else return Pair(boardArray, "X")
}

fun drawBoardFirst (tempBoardArray: List<String>) {
    val boardArray = tempBoardArray.toMutableList()
    //println("drawBoardFirst")
    if(boardArray.contains("_")) {
        Collections.replaceAll(boardArray, "_"," ")
    }
    println("---------")
    println("| ${boardArray[1]} ${boardArray[2]} ${boardArray[3]} |")
    println("| ${boardArray[4]} ${boardArray[5]} ${boardArray[6]} |")
    println("| ${boardArray[7]} ${boardArray[8]} ${boardArray[9]} |")
    println("---------")
}

fun swapPlayer (playerMove: String):String {
    if (playerMove == "X") return "O" else return "X"
}

fun gameDecision(boardArray: List<String>): Pair < String, Boolean> {
    var decision = false
    var xNumber = 0
    var oNumber = 0
    var emptyNumber = 0
    var tripleXFound = 0
    var tripleOFound = 0
    var currentText = ""
    val winPosition = mutableListOf(
        mutableListOf<Int>(1,4,7),
        mutableListOf<Int>(2,5,8),
        mutableListOf<Int>(3,6,9),
        mutableListOf<Int>(1,2,3),
        mutableListOf<Int>(4,5,6),
        mutableListOf<Int>(7,8,9),
        mutableListOf<Int>(1,5,9),
        mutableListOf<Int>(3,5,7)
    )
    //println("Game decision the Board $boardArray")
    game@ while (!decision) {

        if (boardArray.contains("X") || boardArray.contains("O") || boardArray.contains("_")){
            for (theCount in 1..9) {
                if (boardArray[theCount] == "X") xNumber++
                if (boardArray[theCount] == "O") oNumber++
                if (boardArray[theCount] == "_" || boardArray[theCount] == " ") emptyNumber++
            }
            /*if (((xNumber == 4 || oNumber == 4) && (emptyNumber >= 2)) || abs(xNumber - oNumber) >= 2) {
                currentText = "Impossible"
                //decision = true
                break@game
            }*/
            for (theIndex in 0..7) {
                var (x, y, z) = winPosition[theIndex]
                if (listOf(boardArray[x], boardArray[y], boardArray[z]).all {it == "X"}){
                    tripleXFound++
                }
                if (listOf(boardArray[x], boardArray[y], boardArray[z]).all {it == "O"}){
                    tripleOFound++
                }
            }
            /*if (tripleOFound + tripleXFound >= 2) {
                currentText = "Impossible"
                //decision = true
                break@game
            }*/
            for (theIndex in 0..7) {
                var (x, y, z) = winPosition[theIndex]
                if (listOf(boardArray[x], boardArray[y], boardArray[z]).all {it == "X"}){
                    currentText = "X wins"
                    decision = true
                    break@game
                }
                if (listOf(boardArray[x], boardArray[y], boardArray[z]).all {it == "O"}){
                    currentText = "O wins"
                    decision = true
                    break@game
                }
            }
            if (emptyNumber != 0) {
                currentText = "Game not finished"
                //decision = true
                break@game
            }
            if (emptyNumber == 0 && (xNumber + oNumber) == 9) {
                currentText = "Draw"
                decision = true
                break@game
            }

        }
    }
    //println("Exiting GameDecision")
    return Pair(currentText,decision)
}