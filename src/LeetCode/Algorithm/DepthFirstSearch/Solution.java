package LeetCode.Algorithm.DepthFirstSearch;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by warn on 3/6/2016.
 * Store solution to complete binary tree
 */
public class Solution {

    /**
     * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each
     * other.
     * Given an integer n, return all distinct solutions to the n-queens puzzle.
     * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both
     * indicate a queen and an empty space respectively.
     * For example,
     * There exist two distinct solutions to the 4-queens puzzle:
     * <p>
     * [
     * [".Q..",  // Solution 1
     * "...Q",
     * "Q...",
     * "..Q."],
     * ["..Q.",  // Solution 2
     * "Q...",
     * "...Q",
     * ".Q.."]
     * ]
     *
     * @param n how many queues
     * @return the list of all solution to n-queues
     */
    public List<List<String>> solveNQueens(int n) {
        if (n <= 0) return null;
        List<List<String>> solutions = new ArrayList<>();

        // represent the board, used to represent which position in the board is being attacked.
        boolean[][] isAttacked = new boolean[n][n];

        // An array to mark that the Y position of each queen in every row.
        int[] queenYPosition = new int[n];
        solveNQueens(0, n, isAttacked, queenYPosition, solutions);
        return solutions;
    }

    private void solveNQueens(int index, int nQueens, boolean[][] isAttacked, int[] queenYPosition,
                              List<List<String>> solutions) {
        if (index == nQueens) solutions.add(generateSolutionFromQueenYPosition(queenYPosition, nQueens));
        else {
            ArrayList<int[]> changedBoard = new ArrayList<>();
            for (int i = 0; i < nQueens; i++) {
                if (!isAttacked[index][i]) {
                    changedBoard.clear();
                    changeAttackList(isAttacked, index, i, nQueens, changedBoard);
                    queenYPosition[index] = i;
                    solveNQueens(index + 1, nQueens, isAttacked, queenYPosition, solutions);
                    recoverIsAttackedArray(isAttacked, changedBoard);
                }
            }
        }
    }

    private List<String> generateSolutionFromQueenYPosition(int[] queenYPosition, int nQueens) {
        ArrayList<String> solution = new ArrayList<>(nQueens);
        for (int i = 0; i < nQueens; i++) {
            StringBuilder row = new StringBuilder();
            for (int j = 0; j < nQueens; j++) {
                if (queenYPosition[i] == j) row.append('Q');
                else row.append('.');
            }
            solution.add(row.toString());
        }
        return solution;
    }

    private void changeAttackList(boolean[][] isAttacked, int x, int y, int nQueens, ArrayList<int[]> changedIndex) {
        // add all positions that being attacked below x, y to is attacked array, as I did this from top to bottom,
        // there is no need to change values in the same and above row.
        for (int i = x + 1; i < nQueens; i++) {
            if (!isAttacked[i][y]) {
                isAttacked[i][y] = true;
                changedIndex.add(new int[]{i, y});
            }
        }

        for (int i = x + 1, j = y + 1; i < nQueens && j < nQueens; i++, j++) {
            if (!isAttacked[i][j]) {
                isAttacked[i][j] = true;
                changedIndex.add(new int[]{i, j});
            }
        }

        for (int i = x + 1, j = y - 1; i < nQueens && j > -1; i++, j--) {
            if (!isAttacked[i][j]) {
                isAttacked[i][j] = true;
                changedIndex.add(new int[]{i, j});
            }
        }
    }

    private void recoverIsAttackedArray(boolean[][] isAttacked, ArrayList<int[]> changedIndex) {
        for (int[] changedPosition : changedIndex) isAttacked[changedPosition[0]][changedPosition[1]] = false;
    }
}
