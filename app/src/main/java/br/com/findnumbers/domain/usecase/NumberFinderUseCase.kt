package br.com.findnumbers.domain.usecase

import br.com.findnumbers.domain.entity.Position

class NumberFinderUseCase {
    private val directions = listOf(
        0 to 1,   // right
        0 to -1,  // left
        1 to 0,   // down
        -1 to 0   // up
    )
    private val root = TrieNode()
    private val foundNumbersSet = mutableSetOf<String>()
    private val results = mutableListOf<Position>()

    operator fun invoke(
        matrix: List<List<String>>,
        targets: List<String>
    ): List<Position> {

        targets.forEach { insertNumber(it) }

        val rows = matrix.size
        val cols = if (rows > 0) matrix[0].size else 0

        for (r in 0 until rows) {
            for (c in 0 until cols) {
                val startDigit = matrix[r][c]
                if (root.children.containsKey(startDigit)) {
                    searchFromCell(matrix, r, c)
                }
            }
        }

        return results.sortedBy { it.number.length }
    }

    private fun insertNumber(number: String) {
        var current = root
        for (i in number.indices) {
            val digit = number[i].toString()
            current = current.children.getOrPut(digit) { TrieNode() }
        }
        current.numberCompleted = number
    }

    private inner class TrieNode {
        val children = mutableMapOf<String, TrieNode>()
        var numberCompleted: String? = null
    }

    private fun searchFromCell(
        matrix: List<List<String>>,
        row: Int,
        col: Int
    ) {
        for ((dr, dc) in directions) {
            dfs(
                matrix = matrix,
                r = row,
                c = col,
                dr = dr,
                dc = dc,
                node = root
            )
        }
    }

    private fun dfs(
        matrix: List<List<String>>,
        r: Int,
        c: Int,
        dr: Int,
        dc: Int,
        node: TrieNode
    ) {
        if (r !in matrix.indices || c !in matrix[r].indices) return

        val digit = matrix[r][c]

        val nextNode = node.children[digit] ?: return

        if (nextNode.numberCompleted != null) {
            val foundNum = nextNode.numberCompleted!!

            if (!foundNumbersSet.contains(foundNum)) {
                foundNumbersSet.add(foundNum)
                val length = foundNum.length
                val startRow = r - (dr * (length - 1))
                val startCol = c - (dc * (length - 1))

                results.add(
                    Position(
                        initial = startRow to startCol,
                        final = r to c,
                        number = foundNum
                    )
                )
            }
        }
        dfs(matrix, r + dr, c + dc, dr, dc, nextNode)
    }
}