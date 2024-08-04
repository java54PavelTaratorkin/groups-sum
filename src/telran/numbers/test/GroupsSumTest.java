package telran.numbers.test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Random;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import telran.numbers.*;

class GroupsSumTest {
	private static final int N_GROUPS = 1000;
	private static final int GROUP_LENGTH = 1000;
	int[][] groups = { { 1, 2 }, { 3, 4 }, { 5, 6 } };
	int[][] largeGroups = getLargeGroups(N_GROUPS, GROUP_LENGTH);
	private static Random random = new Random();


	@Test
	void goupsSumTaskThreadTest() {
		GroupsSum gs = new GroupsSumTaskThread(groups);
		assertEquals(21, gs.getSum());
	}

    private int[][] getLargeGroups(int nGroups, int groupLength) {
        return Stream.generate(() -> random.ints(groupLength, 1, 100).toArray())
                     .limit(nGroups)
                     .toArray(int[][]::new);
    }

	@Test
	void goupsSumThreadPoolTest() {
		GroupsSum gs = new GroupsSumThreadPool(groups);
		assertEquals(21, gs.getSum());
	}

	@Test
	void groupsSumTaskThreadPerformance() {
		new GroupsSumTaskThread(largeGroups).getSum();
	}

	@Test
	void groupsSumTaskThreadPoolsPerformance() {
		new GroupsSumThreadPool(largeGroups).getSum();
	}
	
    @Test
    void groupsSumTaskThreadLargePerformance() {

        int[][] veryLargeGroups = getLargeGroups(100000, 20000);


        GroupsSum gsVeryLarge = new GroupsSumTaskThread(veryLargeGroups);


        assertNotNull(gsVeryLarge.getSum());
    }

    @Test
    void groupsSumThreadPoolLargePerformance() {

        int[][] veryLargeGroups = getLargeGroups(100000, 20000);


        GroupsSum gsVeryLarge = new GroupsSumThreadPool(veryLargeGroups);


        assertNotNull(gsVeryLarge.getSum());
    }

}
