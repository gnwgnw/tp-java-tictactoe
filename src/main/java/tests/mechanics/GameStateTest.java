package tests.mechanics;

import mechanics.GameState;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameStateTest {
    final GameState gameState = new GameState();

    final int signX = 1;
    final  int signO = 4;
    final int winnerDefault = 0;
    final int size = 3;

    @Test
    public void isChosenWinnerCross() throws Exception {
        assertEquals(true, gameState.isChosenWinner(signX * size));
        assertEquals(signX, gameState.getWinner());
    }

    @Test
    public void isChosenWinnerZero() throws Exception {
        assertEquals(true, gameState.isChosenWinner(signO * size));
        assertEquals(signO, gameState.getWinner());
    }

    @Test
    public void isChosenWinnerDefault() throws Exception {
        assertEquals(false, gameState.isChosenWinner(12345));
        assertEquals(winnerDefault, gameState.getWinner());
    }

    @Test
    public void checkLinesTrue() throws Exception {
        final int[] field1 = {  signX, signX, signX,
                                signO, signO, signX,
                                signX, signO, signO };

        final int[] field2 = {  signX, signO, signO,
                                signX, signX, signX,
                                signO, signX, signO };

        final int[] field3 = {  signX, signO, signO,
                                signO, signX, signO,
                                signX, signX, signX };

        final int[] field4 = {  signO, signO, signO,
                                signX, signX, signO,
                                signO, signX, signX };

        final int[] field5 = {  signO, signX, signX,
                                signO, signO, signO,
                                signX, signO, signX };

        final int[] field6 = {  signO, signX, signX,
                                signX, signO, signX,
                                signO, signO, signO };

        assertEquals(true, gameState.checkLines(field1));
        assertEquals(true, gameState.checkLines(field2));
        assertEquals(true, gameState.checkLines(field3));
        assertEquals(true, gameState.checkLines(field4));
        assertEquals(true, gameState.checkLines(field5));
        assertEquals(true, gameState.checkLines(field6));
    }

    @Test
    public void checkLinesFalse() throws Exception {
    final int[] field1 = {      signX, signO, signX,
                                signO, signX, signO,
                                signX, signO, signX };

        final int[] field2 = {  signX, signO, signX,
                                signX, signO, signX,
                                signX, signO, signX };

        final int[] field3 = {  signX, signO, signX,
                                signO, signO, signX,
                                signO, signX, signO };

        assertEquals(false, gameState.checkLines(field1));
        assertEquals(false, gameState.checkLines(field2));
        assertEquals(false, gameState.checkLines(field3));
    }

    @Test
    public void checkRowsTrue() throws Exception {
        final int[] field1 = {  signX, signX, signO,
                                signX, signO, signX,
                                signX, signO, signO };

        final int[] field2 = {  signX, signX, signO,
                                signO, signX, signX,
                                signO, signX, signO };

        final int[] field3 = {  signX, signO, signX,
                                signO, signX, signX,
                                signO, signO, signX };

        final int[] field4 = {  signO, signO, signX,
                                signO, signX, signO,
                                signO, signX, signX };

        final int[] field5 = {  signO, signO, signX,
                                signX, signO, signO,
                                signX, signO, signX };

        final int[] field6 = {  signO, signX, signO,
                                signX, signO, signO,
                                signX, signX, signO };

        assertEquals(true, gameState.checkRows(field1));
        assertEquals(true, gameState.checkRows(field2));
        assertEquals(true, gameState.checkRows(field3));
        assertEquals(true, gameState.checkRows(field4));
        assertEquals(true, gameState.checkRows(field5));
        assertEquals(true, gameState.checkRows(field6));
    }

    @Test
    public void checkRowsFalse() throws Exception {
        final int[] field1 = {  signX, signO, signX,
                                signO, signX, signO,
                                signX, signO, signX };

        final int[] field2 = {  signX, signO, signO,
                                signX, signX, signX,
                                signO, signO, signX };

        final int[] field3 = {  signX, signO, signX,
                                signO, signO, signX,
                                signO, signX, signO };

        assertEquals(false, gameState.checkRows(field1));
        assertEquals(false, gameState.checkRows(field2));
        assertEquals(false, gameState.checkRows(field3));
    }

    @Test
    public void checkPriDiagonalsTrue() throws Exception {
        final int[] field1 = {  signX, signO, signO,
                                signX, signX, signO,
                                signO, signX, signX };

        final int[] field2 = {  signO, signX, signX,
                                signO, signO, signX,
                                signX, signO, signO };

        assertEquals(true, gameState.checkPriDiagonals(field1));
        assertEquals(true, gameState.checkPriDiagonals(field2));
    }

    @Test
    public void checkPriDiagonalsFalse() throws Exception {
        final int[] field1 = {  signO, signO, signX,
                                signO, signX, signO,
                                signX, signO, signX };

        final int[] field2 = {  signO, signX, signX,
                                signX, signO, signX,
                                signO, signO, signX };

        final int[] field3 = {  signX, signO, signX,
                                signO, signO, signO ,
                                signX, signX, signO };

        assertEquals(false, gameState.checkPriDiagonals(field1));
        assertEquals(false, gameState.checkPriDiagonals(field2));
        assertEquals(false, gameState.checkPriDiagonals(field3));
    }

    @Test
    public void checkAddDiagonalsTrue() throws Exception {
        final int[] field1 = {  signO, signO, signX,
                                signO, signX, signX,
                                signX, signX, signO };

        final int[] field2 = {  signX, signX, signO,
                                signO, signO, signX,
                                signO, signO, signX };

        assertEquals(true, gameState.checkAddDiagonals(field1));
        assertEquals(true, gameState.checkAddDiagonals(field2));
    }

    @Test
    public void checkAddDiagonalsFalse() throws Exception {
        final int[] field1 = {  signX, signO, signO,
                                signO, signX, signO,
                                signX, signO, signX };

        final int[] field2 = {  signO, signX, signX,
                                signX, signO, signX,
                                signO, signO, signX };

        final int[] field3 = {  signX, signO, signX,
                                signO, signO, signO ,
                                signX, signX, signO };

        assertEquals(false, gameState.checkAddDiagonals(field1));
        assertEquals(false, gameState.checkAddDiagonals(field2));
        assertEquals(false, gameState.checkAddDiagonals(field3));
    }

    @Test
    public void checkIsFullTrue() throws Exception {
        final int[] field1 = {  signO, signO, signX,
                                signO, signX, signX,
                                signX, signX, signO };

        final int[] field2 = {  signX, signX, signO,
                                signO, signO, signX,
                                signO, signO, signX };

        assertEquals(true, gameState.checkAddDiagonals(field1));
        assertEquals(true, gameState.checkAddDiagonals(field2));
    }

    @Test
    public void checkIsFullFalse() throws Exception {
        final int[] field1 = {  signX, signO, signO,
                                signO, signX, signX,
                                signX, signO, 0 };
        assertEquals(false, gameState.checkAddDiagonals(field1));
    }


}