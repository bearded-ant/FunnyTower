package com.minigames.funnytower.game

class Board(private val width: Int) {
    companion object {
        private val BLANK: Char? = null
    }

    private var numberOfSpaces: Int = 0
    private var numberOfBlanks: Int = 0
    private var numberOfOccupied: Int = 0
    private val blankSpaces: MutableList<Pair<Int, Int>>
    private val board: Array<Array<Char?>>

    init {
        numberOfSpaces = width * width
        numberOfBlanks = numberOfSpaces
        numberOfOccupied = 0
        blankSpaces = squareArray((0 until width).toList()).toMutableList()
        board = Array(width) { Array(width) { BLANK } }
    }

    fun isBlank(): Boolean {
        return numberOfBlanks == numberOfSpaces
    }

    fun placePiece(piece: Char, space: Int): Board {
        if (piece != BLANK) {
            val row = space / width
            val col = space % width
            board[row][col] = piece
            numberOfBlanks--
            numberOfOccupied++
            blankSpaces.remove(Pair(row, col))
        }
        return this
    }

    fun contentsOf(space: Pair<Int, Int>): Char? {
        return board[space.first][space.second]
    }

    fun toArray(): List<Char?> {
        return board.flatten()
    }

    private fun squareArray(array: List<Int>): List<Pair<Int, Int>> {
        return array.flatMap { i -> array.map { j -> Pair(i, j) } }
    }
}