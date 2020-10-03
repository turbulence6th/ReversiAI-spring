package com.turbulence6th.reversi.controller;

import com.turbulence6th.reversi.ai.ReversiAI;
import com.turbulence6th.reversi.dto.ReversiCoordinate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@RestController
@RequestMapping(path = "/move")
public class MoveController {

    @PostMapping
    public ReversiCoordinate move(HttpServletRequest request) {
        int[][] board = Arrays.stream(request.getParameterValues("board[]"))
                .map(s -> Arrays.stream(s.split(",")).mapToInt(Integer::parseInt).toArray()).toArray(int[][]::new);

        ReversiAI ai = new ReversiAI(board);

        int[] move = ai.play();

        if (move == null) {
            return null;
        }

        return ReversiCoordinate.builder()
                .x(move[0])
                .y(move[1])
                .build();
    }
}
