package com.minigames.funnytower.game


private const val DIAGONAL_ID = "d"
private const val REVERSE_DIAGONAL_ID = "rd"
private const val COLUMN_PREFIX = "c"
private const val ROW_PREFIX = "r"
private const val WIDTH = 1

class GameState(private val playerPiece: Char, private val opponentPiece: Char) {

    lateinit var board: Board
    private val maxIndex: Int
    private val minimumMovesRequiredToWin: Int
    private var impossibleLines: MutableList<String> = mutableListOf()

    var winner: Char? = null

    init {
        board = Board(WIDTH)
        maxIndex = WIDTH - 1
        minimumMovesRequiredToWin = (2 * WIDTH) - 1
    }

    fun makeMove(space: Int): GameState {
        val newState = GameState(opponentPiece, playerPiece)
        newState.board = board.placePiece(playerPiece, space)
        newState.impossibleLines = ArrayList(impossibleLines)
        newState.checkForWin()
        return newState
    }

//todo а надо ли?
    val availableMoves: List<Int> = board.blankSpaces

    val cornerSpaces: List<Pair<Int, Int>>
        get() = listOf(
            Pair(0, 0),
            Pair(0, maxIndex),
            Pair(maxIndex, 0),
            Pair(maxIndex, maxIndex)
        )

    val isUnplayed: Boolean = board.isAllBlank()

    val finalMove: Int? = if (board.numberOfBlanks == 1) board.blankSpaces[0] else null

    val isOver: Boolean
        get() = winner != null || isDraw

    val isDraw: Boolean = board.numberOfBlanks == 0 && winner == null

    fun winnerExists(): Boolean {
        return winner != null
    }

    fun lost(piece: Char): Boolean {
        return winner != null && winner != piece
    }

    fun won(piece: Char): Boolean {
        return piece == winner
    }

    fun winningLine(): List<Pair<Int, Int>> {
        val gatherLine = true
        checkForWin()
        return board.getWinningLine(gatherLine)
    }

    private fun checkForWin() {
        if (board.numberOfOccupied >= minimumMovesRequiredToWin) {
            winner =
                winningRow() ?: winningColumn() ?: winningDiagonal() ?: winningReverseDiagonal()
        }
    }

    private fun winningRow(): Char? {
        for (rowIndex in 0 until WIDTH) {
            val candidate = checkLine(Pair(rowIndex, 0), "${ROW_PREFIX}${rowIndex}") { index ->
                Pair(
                    rowIndex,
                    index
                )
            }
            if (candidate != null) return candidate
        }
        return null
    }

    private fun winningColumn(): Char? {
        for (columnIndex in 0 until WIDTH) {
            val candidate =
                checkLine(Pair(0, columnIndex), "${COLUMN_PREFIX}${columnIndex}") { index ->
                    Pair(
                        index,
                        columnIndex
                    )
                }
            if (candidate != null) return candidate
        }
        return null
    }

    private fun winningDiagonal(): Char? {
        return checkLine(Pair(0, 0), DIAGONAL_ID) { index -> Pair(index, index) }
    }

    private fun winningReverseDiagonal(): Char? {
        return checkLine(Pair(0, maxIndex), REVERSE_DIAGONAL_ID) { index ->
            Pair(
                index,
                maxIndex - index
            )
        }
    }

    private fun checkLine(
        candidateSpace: Pair<Int, Int>,
        lineId: String,
        getNextSpace: (Int) -> Pair<Int, Int>
    ): Char? {
        if (impossibleLines.contains(lineId)) return null
        val candidate = board.getContentsOf(candidateSpace)
        if (candidate == null) return null
        val line = mutableListOf(candidateSpace)
        for (index in 1..maxIndex) {
            val space = getNextSpace(index)
            val testSpace = board.getContentsOf(space)
            if (testSpace != candidate) {
                impossibleLines.add(lineId)
                return null
            }
            line.add(space)
        }
        board.setWinningLine(line)
        return candidate
    }
}
