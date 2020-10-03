package com.turbulence6th.reversi.ai;

import lombok.Getter;
import lombok.Setter;

public class Node implements Comparable<Node> {

    @Getter
    private final int point;

    @Getter
    @Setter
    private Move move;

    public Node(int point) {
        this.point = point;
    }

    @Override
    public int compareTo(Node node) {
        return point - node.point;
    }

    @Override
    public String toString() {
        return String.format("[point: %d, move: %s]", point, move);
    }
}