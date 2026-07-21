package com.lo2ay;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

            int n = scanner.nextInt();
            PumGame game = new PumGame(n);

            game.play();

    }

    static class PumGame {
        private int lines;

        public PumGame(int lines) {
            this.lines = lines;
        }

        public void play() {
            int counter = 1;

            for (int i = 0; i < this.lines; i++) {

                System.out.println(counter + " " + (counter + 1) + " " + (counter + 2) + " PUM");

                counter += 4;
            }
        }
    }
}