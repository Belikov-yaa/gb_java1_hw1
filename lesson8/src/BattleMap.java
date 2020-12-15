
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BattleMap extends JPanel {
    private GameWindow gameWindow;

    static final int MODE_H_VS_AI = 0;
    static final int MODE_H_VS_H = 1;

    private int mode;
    private int fieldSize;
    private int winningLength;

    private boolean isInit;

    private int cellWidth;
    private int cellHeight;


    public BattleMap(GameWindow gameWindow) {
        this.gameWindow = gameWindow;

        setBackground(Color.ORANGE);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int cellX = e.getX() / cellWidth;
                int cellY = e.getY() / cellHeight;

                if (!Logic.gameFinished) {
                    Logic.humanTurn(cellX, cellY);

                    // тут можете проверить кто победил и вывести результат графически
                    // например через gameWindow
                }
                repaint();
                if (Logic.gameFinished) {
                    StringBuilder endText = new StringBuilder();
                    endText.append("<html><h2>Игра Окончена</h2>");
                    switch (Logic.winner) {
                        case (Logic.DOT_EMPTY):
                            endText.append("Ничья!");
                            break;
                        case (Logic.DOT_O):
                            endText.append("Победили нолики!");
                            break;
                        case (Logic.DOT_X):
                            endText.append("Победили крестики!");
                    }
                    endText.append("</i></html>");
                    JOptionPane.showMessageDialog(gameWindow, endText.toString());
                }
            }
        });
    }

    public void startNewGame(int mode, int fieldSize, int winningLength) {
        this.mode = mode;
        this.fieldSize = fieldSize;
        this.winningLength = winningLength;

        isInit = true;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!isInit) {
            return;
        }

        cellHeight = getHeight() / fieldSize;
        cellWidth = getWidth() / fieldSize;

        for (int i = 1; i < fieldSize; i++) {
            int y = i * cellHeight;
            g.drawLine(0, y, getWidth(), y);
        }

        for (int i = 1; i < fieldSize; i++) {
            int x = i * cellWidth;
            g.drawLine(x, 0, x, getHeight());
        }

        for (int i = 0; i < Logic.SIZE; i++) {
            for (int j = 0; j < Logic.SIZE; j++) {
                if (Logic.map[i][j] == Logic.DOT_X) {
                    drawX(g, j, i);
                }
                if (Logic.map[i][j] == Logic.DOT_O) {
                    drawO(g, j, i);
                }
            }
        }
        if (Logic.gameFinished && Logic.winner != Logic.DOT_EMPTY) {
            drawWinLine(g, Logic.winLine, Logic.winner);
        }
    }

    private void drawX(Graphics g, int cellX, int cellY) {
        ((Graphics2D) g).setStroke(new BasicStroke(5));
        g.setColor(Color.RED);
        g.drawLine(cellX * cellWidth, cellY * cellHeight,
                (cellX + 1) * cellWidth, (cellY + 1) * cellHeight);
        g.drawLine((cellX + 1) * cellWidth, cellY * cellHeight,
                cellX * cellWidth, (cellY + 1) * cellHeight);
    }


    private void drawO(Graphics g, int cellX, int cellY) {
        ((Graphics2D) g).setStroke(new BasicStroke(5));
        g.setColor(Color.BLUE);
        g.drawOval(cellX * cellWidth, cellY * cellHeight, cellWidth, cellHeight);
    }

    private void drawWinLine(Graphics g, int[][] winLine, char winner) {
        ((Graphics2D) g).setStroke(new BasicStroke(5));
        if (winner == Logic.DOT_X)
            g.setColor(Color.RED);
        else if (winner == Logic.DOT_O)
            g.setColor(Color.BLUE);
        g.drawLine(winLine[0][1] * cellWidth + cellWidth / 2, winLine[0][0] * cellHeight + cellHeight / 2,
                winLine[1][1] * cellWidth + cellWidth / 2, winLine[1][0] * cellHeight + cellHeight / 2);
    }

}
