package com.turbulence6th.reversi.ai;

import lombok.Getter;
import lombok.Setter;

public class Node implements Comparable<Node> {

    @Getter
    private final double point;

    @Getter
    @Setter
    private Move move;

    public Node(double point) {
        this.point = point;
    }

    @Override
    public int compareTo(Node node) {
        return (int) (point - node.point);
    }

    @Override
    public String toString() {
        return String.format("[point: %f, move: %s]", point, move);
    }
}