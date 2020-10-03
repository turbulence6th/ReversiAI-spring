package com.turbulence6th.reversi.ai;

import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

@Getter
public class Move implements Comparable<Move> {

    private final int[] position;

    private final List<int[]> flips;

    private final List<int[]> origins;

    public Move(int[] position) {
        this.position = position;
        this.flips = new LinkedList<>();
        this.origins = new LinkedList<>();
    }

    @Override
    public int compareTo(Move move) {
        return move.flips.size() - flips.size();
    }

    @Override
    public String toString() {
        return String.format("[x: %d, y: %d]", position[0], position[1]);
    }
}