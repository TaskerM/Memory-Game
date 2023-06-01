package memorygame;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;

class Card extends JButton {
    private static AtomicInteger nextId = new AtomicInteger();
    private final int id;
    private final ImageIcon icon;
    private CardButtonState state;

    Card(File file, ActionListener actionListener, String name) {
        id = nextId.incrementAndGet();
        state = CardButtonState.REAR;
        icon = new ImageIcon(String.valueOf(file));
        this.addActionListener(actionListener);
        this.setName(name);
        this.setContentAreaFilled(false);
    }

    void turn() {
        if (state == CardButtonState.REAR) {
            state = CardButtonState.FRONT;
            this.setIcon(icon);
        } else if (state == CardButtonState.FRONT) {
            CardWorker cw = new CardWorker(this);
            cw.execute();
            state = CardButtonState.REAR;
        }
    }

    void deactivate() {
        this.state = CardButtonState.NOTACTIVE;
        this.setEnabled(false);
    }

    int getId() {
        return id;
    }

    CardButtonState getState() {
        return state;
    }
}
