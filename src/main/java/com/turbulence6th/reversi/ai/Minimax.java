package com.turbulence6th.reversi.ai;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public abstract class Minimax<T extends MinimaxMove> {

    public abstract List<T> getValidMoves(int[][] board, List<int[]> spaces, int player);
    public abstract int[][] makeMove(int[][] board, T reversiMove, int player);

    public MinimaxNode<T> minimax(int player, int[][] board, double alpha, double beta, int depth) {

        if (isFinished(board)) {
            return new MinimaxNode<>(point(board));
        }

        else if (depth == 0) {
            return new MinimaxNode<>(edgePoint(board));
        }

        MinimaxNode<T> score = null, temp = null;
        List<T> validReversiMoves = getValidMoves(board, spaces, player);

        Collections.shuffle(validReversiMoves);

        if (validReversiMoves.isEmpty()) {
            if (player == 1) {
                temp = minimax(-1, board, spaces, alpha, beta, depth - 1);

                if (temp.getPoint() > alpha) {
                    alpha = temp.getPoint();
                    score = temp;
                }

                /**
                 * Alpha Beta Prunning
                 */
                if (alpha >= beta) {
                    return score;
                }
            }

            else if (player == -1) {
                temp = minimax(1, board, spaces, alpha, beta, depth - 1);

                if (temp.getPoint() < beta) {
                    beta = temp.getPoint();
                    score = temp;
                }

                /**
                 * Alpha Beta Prunning
                 */
                if (alpha >= beta) {
                    return score;
                }
            }
        }

        for (T minimaxMove : validReversiMoves) {

            int[][] playedBoard = makeMove(board, minimaxMove, player);

            if (player == 1) {
                temp = minimax(-1, playedBoard, alpha, beta, depth - 1);

                if (temp.getPoint() > alpha) {
                    alpha = temp.getPoint();
                    score = temp;
                    score.setMove(minimaxMove);
                }

                /**
                 * Alpha Beta Prunning
                 */
                if (alpha >= beta) {
                    return score;
                }
            }

            else if (player == -1) {
                temp = minimax(1, playedBoard, alpha, beta, depth - 1);

                if (temp.getPoint() < beta) {
                    beta = temp.getPoint();
                    score = temp;
                    score.setMove(minimaxMove);
                }

                /**
                 * Alpha Beta Prunning
                 */
                if (alpha >= beta) {
                    return score;
                }
            }
        }

        return score != null ? score : temp;
    }
}
