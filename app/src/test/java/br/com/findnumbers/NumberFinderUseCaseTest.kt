package br.com.findnumbers

import br.com.findnumbers.domain.entity.Position
import br.com.findnumbers.domain.usecase.NumberFinderUseCase
import junit.framework.TestCase.assertEquals
import org.junit.Test

class NumberFinderUseCaseTest {

    val numbers = listOf(
        "0031",
        "4600",
        "5630",
        "08220",
        "16786",
        "92100",
        "99000",
        "045658",
        "296000",
        "430000",
        "438281",
        "509006",
        "850302",
        "893079",
        "901899",
        "995000",
        "0871801",
        "1241584",
        "1618685",
        "1740067"
    )

    val matrix = listOf(
        //0 , 1 , 2 , 3 , 4 , 5 , 6 , 7 , 8 , 9 , 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21
        listOf("4","4","7","8","5","1","8","2","9","9","0","9","4","3","1","6","7","4","1","7","6","8"), // 0 check
        listOf("0","5","3","4","0","2","8","6","1","4","8","6","2","7","0","2","8","8","1","3","8","8"), // 1 check
        listOf("6","1","8","8","4","4","7","8","6","2","8","0","9","0","5","9","3","2","0","6","2","2"), // 2 check
        listOf("1","2","9","0","7","6","3","8","3","5","4","0","5","9","9","6","0","4","7","2","7","3"), // 3 check
        listOf("7","7","2","2","5","8","5","9","0","1","8","9","9","3","6","6","6","0","0","3","1","8"), // 4 check <-- check 0031 Position()
        listOf("0","1","7","9","2","1","0","0","9","5","4","7","2","6","0","7","5","3","4","9","6","2"), // 5 check <-- check 92100 Position
        listOf("8","7","0","3","0","6","0","9","9","3","7","5","0","8","6","0","6","2","5","6","0","9"), // 6 check
        listOf("7","4","0","4","3","1","7","6","1","6","0","6","4","2","9","3","7","9","6","4","9","9"), // 7 check
        listOf("9","0","8","7","5","2","3","3","0","4","8","3","1","7","6","8","8","6","5","7","7","0"), // 8 check
        listOf("3","0","2","0","1","7","1","1","6","7","8","6","6","0","2","8","1","0","8","4","0","0"), // 9 check <-- 850302 Position(initial (9, 10), (14,10))
        listOf("2","6","2","1","3","0","6","4","7","3","5","9","7","1","1","8","1","0","3","3","2","0"), // 10 check
        listOf("9","7","0","2","4","5","8","6","7","2","0","9","3","1","3","4","8","0","8","0","3","4"), // 11 check
        listOf("4","3","8","2","8","1","2","0","2","5","3","1","3","4","6","1","5","6","6","0","4","5"), // 12 check
        listOf("3","1","7","7","4","1","2","0","6","1","0","5","0","7","5","6","0","9","1","0","5","2"), // 13 check
        listOf("1","9","3","1","2","5","7","4","0","5","2","5","6","3","0","1","8","1","6","0","3","6"), // 14 check
        listOf("4","2","5","2","9","6","3","7","5","1","1","8","9","3","0","7","9","8","9","6","3","9"), // 15 check
        listOf("0","0","1","8","1","0","2","3","9","9","5","0","0","0","9","1","4","2","0","7","5","6"), // 16 check
        listOf("4","8","9","4","9","2","1","2","4","1","5","8","4","7","1","9","3","0","2","5","0","8"), // 17 check
        listOf("0","7","6","4","0","9","0","9","3","6","9","8","9","7","6","1","3","5","3","5","8","3"), // 18 check
        listOf("4","1","8","6","1","4","7","9","4","1","4","7","5","5","8","3","1","6","7","9","4","8"), // 19 check
        listOf("8","8","4","8","2","6","8","4","9","8","3","0","1","5","4","1","2","7","1","3","4","8"), // 20 check
        listOf("6","0","9","7","3","7","6","5","9","6","9","5","0","9","0","0","6","2","6","8","3","0"), // 21 check
        listOf("7","1","4","0","2","1","4","0","8","8","0","5","5","7","9","9","4","1","7","4","9","5"), // 22 check
        listOf("9","7","4","5","8","9","8","5","9","5","0","5","8","8","8","9","1","4","9","9","8","3")  // 23 check
    )

    @Test
    fun `WHEN call getPositions THEN should returns except positions`() {
        val mockExpected = listOf(
            Position(
                initial = Pair(4, 17),
                final = Pair(4, 20),
                number = "0031"
            ),
            Position(
                initial = Pair(10,7),
                final = Pair(13,7),
                number = "4600"
            ),
            Position(
                initial = Pair(14,11),
                final = Pair(14,14),
                number = "5630"
            ),
            Position(
                initial = Pair(7,2),
                final = Pair(11,2),
                number = "08220"
            ),
            Position(
                initial = Pair(9,7),
                final = Pair(9,11),
                number = "16786"
            ),
            Position(
                initial = Pair(5,3),
                final = Pair(5,7),
                number = "92100"
            ),
            Position(
                initial = Pair(6,21),
                final = Pair(10,21),
                number = "99000"
            ),
            Position(
                initial = Pair(4,18),
                final = Pair(9,18),
                number = "045658"
            ),
            Position(
                initial = Pair(6,17),
                final = Pair(11,17),
                number = "296000"
            ),
            Position(
                initial = Pair(9,19),
                final = Pair(14,19),
                number = "430000"
            ),
            Position(
                initial = Pair(12,0),
                final = Pair(12,5),
                number = "438281"
            ),
            Position(
                initial = Pair(21,11),
                final = Pair(21,16),
                number = "509006"
            ),
            Position(
                initial = Pair(9,10),
                final = Pair(14,10),
                number = "850302"
            ),
            Position(
                initial = Pair(15,11),
                final = Pair(15,16),
                number = "893079"
            ),
            Position(
                initial = Pair(4,7),
                final = Pair(4,12),
                number = "901899"
            ),
            Position(
                initial = Pair(16,8),
                final = Pair(16,13),
                number = "995000"
            ),
            Position(
                initial = Pair(16,1),
                final = Pair(22,1),
                number = "0871801"
            ),
            Position(
                initial = Pair(17,6),
                final = Pair(17,12),
                number = "1241584"
            ),
            Position(
                initial = Pair(17,9),
                final = Pair(23,9),
                number = "1618685"
            ),
            Position(
                initial = Pair(5,1),
                final = Pair(11,1),
                number = "1740067"
            ),
        )

        val comparator = compareBy<Position>(
            { it.number },
            { it.initial.first },
            { it.initial.second },
            { it.final.first },
            { it.final.second }
        )

        val useCase = NumberFinderUseCase()
        val result = useCase(matrix, numbers)

        assertEquals(
            mockExpected.sortedWith(comparator),
            result.sortedWith(comparator)
        )
    }
}