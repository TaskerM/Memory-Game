package memorygame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class PlayState implements ActionListener {
    private int size = 4;
    private ArrayList<File> imageFiles;
    private ArrayList<Card> cards;
    private int currClick;
    private Card prevCard;
    private byte currPairsNum;
    private JPanel panel;

    PlayState() {
        loadImages();
    }

    public void update(JPanel gamePanel) {
        this.panel = gamePanel;
        resetValues();
        size = 4;
        gamePanel.removeAll();
        gamePanel.setLayout(new BorderLayout());
        JPanel cardsPanel = new JPanel(new GridLayout(size, size));
        JPanel menuPanel = new JPanel();
        JMenuBar menuBar = new JMenuBar();
        menuPanel.add(menuBar);
        menuPanel.setPreferredSize(new Dimension(500, 30));
        for (int i = 0; i < (size * size) / 2; i++) {
            cards.add(new Card(imageFiles.get(i), this, Integer.toString(i)));
            cards.add(new Card(imageFiles.get(i), this, Integer.toString(i)));
        }
        Collections.shuffle(cards);
        for (int i = 0; i < size * size; i++) {
            cardsPanel.add(cards.get(i));
        }

        gamePanel.add(menuPanel, BorderLayout.SOUTH);
        gamePanel.add(cardsPanel, BorderLayout.CENTER);
        gamePanel.updateUI();

    }

    private void resetValues() {
        cards = new ArrayList<>();
        currClick = 1;
        currPairsNum = 0;
    }

    private void loadImages() {
        File dir = new File("src/main/resources");
        imageFiles = new ArrayList<>(Arrays.asList(dir.listFiles()));
        Collections.shuffle(imageFiles);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Card pressedCard = (Card) actionEvent.getSource();
        if (pressedCard.getState() == CardButtonState.FRONT)
            return;
        if (numOfCardsWithIconNonNull() >= 2)
            return;
        pressedCard.turn();

        if (currClick == 1) {
            currClick = 2;
        } else if (currClick == 2) {
            currClick = 1;
            if ((pressedCard.getName().equals(prevCard.getName()) && (pressedCard.getId() != prevCard.getId()))) {
                prevCard.deactivate();
                pressedCard.deactivate();
                if (currPairsNum != ((size * size) / 2) - 1) {
                    currPairsNum++;
                } else {
                    this.update(panel);
                }
            } else {
                prevCard.turn();
                pressedCard.turn();
            }
        }

        prevCard = pressedCard;
    }

    private int numOfCardsWithIconNonNull() {
        int nullIcons = 0;
        for (Card card : cards) {
            if (card.isEnabled() && card.getIcon() != null)
                nullIcons++;
        }
        return nullIcons;
    }
}
